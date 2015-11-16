package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.PRisqueConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao d'accès au risque théorique.
 */
public class PRisqueDAO extends BaseJBDCTemplateDAO {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PRisqueDAO.class);

    protected static final String[] TOUTES_VALEURS = new String[] {};
    protected static final String CLEF_TOUTES_VALEURS = clef(TOUTES_VALEURS);

    protected static Map<String, Map<String, Map<String, Map<String, Float>>>> cachePoidsRisque;

    protected static void setCachePoidsRisque(Map<String, Map<String, Map<String, Map<String, Float>>>> cache) {
        cachePoidsRisque = cache;
    }

    private static final int CACHE_INITIAL_CAPACITY = 100;

    protected static String clef(String[] valeurs) {
        StringBuilder clef = new StringBuilder("");
        for (String valeur : valeurs) {
            clef.append(clef(valeur));
        }
        return clef.toString();
    }

    protected static String clef(String valeur) {
        return "<" + valeur + ">";
    }

    protected static String[] valeurs(String clef) {
        return clef.replaceFirst("^<", "").replaceFirst(">$", "").split("><");
    }

    /**
     * Chargement du cache.
     *
     * @param formule
     *            la formule de risque
     * @throws SQLException
     *             si un problème survient
     */
    public void chargeCache(FormuleRisque formule) throws Exception {

        cachePoidsRisque = new HashMap<String, Map<String, Map<String, Map<String, Float>>>>(CACHE_INITIAL_CAPACITY);

        // StopWatch watch = new StopWatch();
        // watch.start();
        String sql = PRisqueConstante.REQUETE_SELECT_P_RISQUE;
        // LOGGER.debug("Execution de la requete : {}", sql);
        ResultSet resultSet = this.performQuery(sql, formule.getFormId());

        try {
            while (resultSet.next()) {
                // ta_rfa,rappr_rfa,proc_rfa,prod_rfa,prisqtheo_poids_nb2
                String typeActRfa = resultSet.getString(1);
                String[] approbationsRfa = (String[]) resultSet.getArray(2).getArray();
                String[] procedesRfa = (String[]) resultSet.getArray(3).getArray();
                String[] produitsRfa = (String[]) resultSet.getArray(4).getArray();
                Float poids = resultSet.getFloat(5);

                String clefTypeActRfa = clef(typeActRfa);
                if (!cachePoidsRisque.containsKey(clefTypeActRfa)) {
                    cachePoidsRisque.put(clefTypeActRfa, new HashMap<String, Map<String, Map<String, Float>>>(CACHE_INITIAL_CAPACITY));
                }
                Map<String, Map<String, Map<String, Float>>> cacheTypeActivite = cachePoidsRisque.get(clefTypeActRfa);

                String clefApprobations = clef(approbationsRfa);
                if (!cacheTypeActivite.containsKey(clefApprobations)) {
                    cacheTypeActivite.put(clefApprobations, new HashMap<String, Map<String, Float>>(CACHE_INITIAL_CAPACITY));
                }
                Map<String, Map<String, Float>> cacheApprobations = cacheTypeActivite.get(clefApprobations);

                String clefProcedes = clef(procedesRfa);
                if (!cacheApprobations.containsKey(clefProcedes)) {
                    cacheApprobations.put(clefProcedes, new HashMap<String, Float>(CACHE_INITIAL_CAPACITY));
                }
                Map<String, Float> cacheProcedes = cacheApprobations.get(clefProcedes);

                String clefProduits = clef(produitsRfa);
                cacheProcedes.put(clefProduits, poids);
                LOGGER.info("Cache ++ : ( {}, {}, {}, {} ) |-> {}", new String[] { clefTypeActRfa, clefApprobations, clefProcedes, clefProduits,
                        poids.toString() });
            }
        } finally {
            resultSet.close();
        }
        // watch.stop();
        // LOGGER.debug("Temps de chargement du cache PRisque : {}",
        // watch.toString());
    }

    /**
     * Pondération de la note de risque de l'UA.
     *
     * @param ua
     *            L'unité d'activité
     * @return Le poids du risque théorique
     * @throws SQLException
     *             l'exception
     */
    /*
     * Cf. le calcul de la colonne "NOTE_P_RISQU_NB2" dans ARQ_V1.0_68_SFD_AnalysedeRisque_T4.1 §11.5
     */
    public Float getPRisque(UniteActivite ua) throws Exception {

        Float poidsStrict = poidsRechercheStricte(ua);
        if (poidsStrict != null) {
            return poidsStrict;
        }

        // On clone le cache car on ne veut pas que les méthodes de filtre
        // modifie les données du cache.
        Map<String, Map<String, Map<String, Map<String, Float>>>> ensemble = cloneCache(cachePoidsRisque);

        /* (Cf. la JavaDoc de chaque méthode pour l'ago général.) */
        ensemble = filtreTypeActivite(ensemble, ua.getTaRfa());
        ensemble = filtreApprobations(ensemble, ua.getApprobations());
        ensemble = filtreProcedes(ensemble, ua.getProcedes());
        ensemble = filtreProduits(ensemble, ua.getProduits());

        /*
         * Si la pondération du risque théorique ne fait pas partie des critères de la formule de risque pour le couple domaine technique et campagne
         * sélectionné, alors ne pas renseigner la valeur du champ.
         */
        // Fait au niveau supérieur (avant).
        Float poidsMax = poidsMax(ensemble);

        /*
         * Si le poids de l’UA n’est pas été retrouvé dans la table de pondération des risques théoriques pour la formule de risque, mettre la valeur
         * 1 pour ce champ.
         */
        Float poids = (poidsMax == null ? 1F : poidsMax); 
        
        return poids;
    }

    /*
     * 1) On récupère toutes les pondérations ayant le même type de d'activité => « Ensemble 1 »
     */
    private Map<String, Map<String, Map<String, Map<String, Float>>>> filtreTypeActivite(
            Map<String, Map<String, Map<String, Map<String, Float>>>> ensemble, String typeActiviteUA) {
        Map<String, Map<String, Map<String, Map<String, Float>>>> filtreTypeActivite;

        String clefTypeActiviteUA = clef(typeActiviteUA);

        filtreTypeActivite = new HashMap<String, Map<String, Map<String, Map<String, Float>>>>(1);
        if (ensemble.containsKey(clefTypeActiviteUA)) {
            filtreTypeActivite.put(clefTypeActiviteUA, ensemble.get(clefTypeActiviteUA));
        }

        return filtreTypeActivite;
    }

    /*
     * 2) Dans l' « Ensemble 1 » récupéré, on sélectionne les pondérations ayant la même combinaison d'approbations que l'UA et les pondérations dont
     * l’approbation est non renseignées => « Ensemble 2 »
     */
    private Map<String, Map<String, Map<String, Map<String, Float>>>> filtreApprobations(
            Map<String, Map<String, Map<String, Map<String, Float>>>> ensemble, String[] approbationsUA) {
        
        // >>> FDA+FRL 2015/09 Mantis 196 (surement à retirer en V2.0)
        // ARQ_023_Règles_RestrictionPérimètre [ARQ_V1.0_68_SFD_AnalysedeRisque_V7.0]
        String[] rfaApprosUA = new String[approbationsUA.length];
        for (int cptRfaAppro = 1; cptRfaAppro <= approbationsUA.length; cptRfaAppro ++ ) {
            String rfaApproUA = approbationsUA[cptRfaAppro-1];
            rfaApproUA = rfaApproUA.substring(0, rfaApproUA.indexOf(";")); 
            rfaApprosUA[cptRfaAppro-1] = rfaApproUA;
        }
        // <<< FDA+FRL 2015/09 Mantis 196 (surement à retirer en V2.0)
        
        String clefApprobationsUA = clef(rfaApprosUA);

        for (String clefTypeActiviteMap : ensemble.keySet()) {
            Map<String, Map<String, Map<String, Float>>> filtreApprobations = new HashMap<String, Map<String, Map<String, Float>>>();
            for (String clefApprobationsMap : ensemble.get(clefTypeActiviteMap).keySet()) {
                if (!clefApprobationsUA.equals(clefApprobationsMap) && !CLEF_TOUTES_VALEURS.equals(clefApprobationsMap)) {
                    continue;
                }
                filtreApprobations.put(clefApprobationsMap, ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap));
            }
            ensemble.put(clefTypeActiviteMap, filtreApprobations);

        }
        return ensemble;
    }

    /*
     * 3) Dans l' « Ensemble 2» récupéré, si des pondérations ayant des combinaisons de procédés identiques à celle de l'UA existent, on les
     * sélectionne ainsi que les pondérations dont les procédés sont non renseignés. Sinon on sélectionne les pondérations dont le procédé unitaire
     * fait partie des procédés de l'UA ainsi que les pondérations dont les procédés sont non renseignés => « Ensemble 3 »
     */
    private Map<String, Map<String, Map<String, Map<String, Float>>>> filtreProcedes(
            Map<String, Map<String, Map<String, Map<String, Float>>>> ensemble, String[] procedesUA) {
        String clefProcedesUA = clef(procedesUA);
        boolean clefProcedesUAStricteTrouvee = false;
        for (String clefTypeActiviteMap : ensemble.keySet()) {
            for (String clefApprobationsMap : ensemble.get(clefTypeActiviteMap).keySet()) {
                for (String clefProcedesMap : ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).keySet()) {
                    if (clefProcedesUA.equals(clefProcedesMap)) {
                        clefProcedesUAStricteTrouvee = true;
                        continue;
                    }
                    if (CLEF_TOUTES_VALEURS.equals(clefProcedesMap)) {
                        continue;
                    }
                    String[] procedesMap = valeurs(clefProcedesMap);
                    if ((procedesMap.length == 1) && clefProcedesUA.contains(procedesMap[0])) {
                        continue;
                    }
                    ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).remove(clefProcedesMap);
                }
            }
        }
        if (!clefProcedesUAStricteTrouvee) {
            // Rien à faire.
        } else if (procedesUA.length >= 2) {
            // Il faut filtrer de l'ensemble chacun des procédés unitaires de l'UA :
            for (String clefTypeActiviteMap : ensemble.keySet()) {
                for (String clefApprobationsMap : ensemble.get(clefTypeActiviteMap).keySet()) {
                    for (String procedeUA : procedesUA) {
                        ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).remove(clef(procedeUA));
                    }
                }
            }
        }
        return ensemble;
    }

    /*
     * 4) Dans l' « Ensemble 3» récupéré, si des pondérations ayant des combinaisons de produits identiques à celle de l'UA existent, on les
     * sélectionne ainsi que les pondérations dont les produits sont non renseignés. Sinon on sélectionne les pondérations dont le produit unitaire
     * fait partie des produits de l'UA ainsi que les pondérations dont les produits sont non renseignés => « Ensemble 4 »
     */

    private Map<String, Map<String, Map<String, Map<String, Float>>>> filtreProduits(
            Map<String, Map<String, Map<String, Map<String, Float>>>> ensemble, String[] produitsUA) {
        String clefProduitsUA = clef(produitsUA);
        boolean clefProduitsUAStricteTrouvee = false;
        for (String clefTypeActiviteMap : ensemble.keySet()) {
            for (String clefApprobationsMap : ensemble.get(clefTypeActiviteMap).keySet()) {
                for (String clefProcedesMap : ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).keySet()) {
                    for (String clefProduitsMap : ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).get(clefProcedesMap).keySet()) {
                        if (clefProduitsUA.equals(clefProduitsMap)) {
                            clefProduitsUAStricteTrouvee = true;
                            continue;
                        }
                        if (CLEF_TOUTES_VALEURS.equals(clefProduitsMap)) {
                            continue;
                        }
                        String[] produitsMap = valeurs(clefProduitsMap);
                        if ((produitsMap.length == 1) && clefProduitsUA.contains(produitsMap[0])) {
                            continue;
                        }
                        ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).get(clefProcedesMap).remove(clefProduitsMap);
                    }
                }
            }
        }
        if (!clefProduitsUAStricteTrouvee) {
            // Rien à faire.
        } else if (produitsUA.length >= 2) {
            // Il faut filtrer de l'ensemble chacun des produits unitaires de l'UA :
            for (String clefTypeActiviteMap : ensemble.keySet()) {
                for (String clefApprobationsMap : ensemble.get(clefTypeActiviteMap).keySet()) {
                    for (String clefProcedesMap : ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).keySet()) {
                        for (String produitUA : produitsUA) {
                            ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).get(clefProcedesMap).remove(clef(produitUA));
                        }
                    }
                }
            }
        }
        return ensemble;
    }

    /*
     * 5) Dans l' « Ensemble 4» récupéré, on compare les poids et on applique le plus élevé à l'UA.
     */
    protected Float poidsMax(Map<String, Map<String, Map<String, Map<String, Float>>>> ensemble) {
        Float poidsMax = null;
        for (String clefTypeActiviteMap : ensemble.keySet()) {
            for (String clefApprobationsMap : ensemble.get(clefTypeActiviteMap).keySet()) {
                for (String clefProcedesMap : ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).keySet()) {
                    for (String clefProduitsMap : ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).get(clefProcedesMap).keySet()) {
                        Float poids = ensemble.get(clefTypeActiviteMap).get(clefApprobationsMap).get(clefProcedesMap).get(clefProduitsMap);
                        if (poidsMax == null || poids > poidsMax) {
                            poidsMax = poids;
                        }
                    }
                }
            }
        }
        return poidsMax;
    }

    /**
     * Clone le cache d'origine, en utilisant des ConcurrentHashMap au lieu de l'habituel HashMap car on modifie le cache quand on le parcoure.
     *
     * @param cacheOrigine
     * @return
     */
    protected Map<String, Map<String, Map<String, Map<String, Float>>>> cloneCache(
            Map<String, Map<String, Map<String, Map<String, Float>>>> cacheOrigine) {
        Map<String, Map<String, Map<String, Map<String, Float>>>> clone = new ConcurrentHashMap<String, Map<String, Map<String, Map<String, Float>>>>(
                cacheOrigine.size());
        for (String key1 : cacheOrigine.keySet()) {
            clone.put(key1, new ConcurrentHashMap<String, Map<String, Map<String, Float>>>());
            for (String key2 : cacheOrigine.get(key1).keySet()) {
                clone.get(key1).put(key2, new ConcurrentHashMap<String, Map<String, Float>>());
                for (String key3 : cacheOrigine.get(key1).get(key2).keySet()) {
                    clone.get(key1).get(key2).put(key3, new ConcurrentHashMap<String, Float>());
                    for (String key4 : cacheOrigine.get(key1).get(key2).get(key3).keySet()) {
                        Float f = cacheOrigine.get(key1).get(key2).get(key3).get(key4);
                        clone.get(key1).get(key2).get(key3).put(key4, new Float(f));
                    }
                }
            }
        }
        return clone;
    }

    private Float poidsRechercheStricte(UniteActivite ua) {
        String clefTypeActiv = clef(ua.getTaRfa());
        if (!cachePoidsRisque.containsKey(clefTypeActiv)) {
            return null;
        }
        String clefApprobations = clef(ua.getApprobations());
        if (!cachePoidsRisque.get(clefTypeActiv).containsKey(clefApprobations)) {
            return null;
        }
        String clefProcedes = clef(ua.getProcedes());
        if (!cachePoidsRisque.get(clefTypeActiv).get(clefApprobations).containsKey(clefProcedes)) {
            return null;
        }
        String clefProduits = clef(ua.getProduits());
        if (!cachePoidsRisque.get(clefTypeActiv).get(clefApprobations).get(clefProcedes).containsKey(clefProduits)) {
            return null;
        }
        return cachePoidsRisque.get(clefTypeActiv).get(clefApprobations).get(clefProcedes).get(clefProduits);
    }
}
