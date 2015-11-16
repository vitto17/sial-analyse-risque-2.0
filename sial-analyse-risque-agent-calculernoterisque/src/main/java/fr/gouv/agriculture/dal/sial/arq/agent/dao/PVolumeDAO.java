package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.PVolumeConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.VolumeProduction;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao pour le calcul de la pondération du volume.
 *
 */
public class PVolumeDAO extends BaseJBDCTemplateDAO {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PVolumeDAO.class);

    private static final int CACHE_INITIAL_CAPACITY = 1000;

    private Map<String, Map<String, BigDecimal[]>> CACHE_P_VOLUME;

    /**
     * Chargement du cache.
     *
     * @param formule la formule de risque
     * @throws SQLException si un problème survient
     */
    public void chargeCache(FormuleRisque formule) throws SQLException, ParseException {

        CACHE_P_VOLUME = new HashMap<String, Map<String, BigDecimal[]>>(CACHE_INITIAL_CAPACITY);

        // StopWatch watch = new StopWatch();
        // watch.start();
        String sql = PVolumeConstante.REQUETE_SELECT_VOLUME;
        //LOGGER.debug("Execution de la requete : {}", sql);
        ResultSet resultSet = this.performQuery(sql, formule.getFormId());

        try {
            while (resultSet.next()) {
                String typeActRfa = resultSet.getString(1);
                String uniteProd = resultSet.getString(2);
                BigDecimal vol1 = new BigDecimal(resultSet.getLong(3));
                BigDecimal vol2 = new BigDecimal(resultSet.getLong(4));
                BigDecimal vol3 = new BigDecimal(resultSet.getLong(5));
                BigDecimal vol4 = new BigDecimal(resultSet.getLong(6));
                BigDecimal[] volumes = new BigDecimal[]{vol1, vol2, vol3, vol4};

                if (!CACHE_P_VOLUME.containsKey(typeActRfa)) {
                    CACHE_P_VOLUME.put(typeActRfa, new HashMap<String, BigDecimal[]>(50));
                }
                Map<String, BigDecimal[]> cacheTypeActivite = CACHE_P_VOLUME.get(typeActRfa);

                cacheTypeActivite.put(uniteProd, volumes);

                LOGGER.info("Cache ++ : ( {}, {} ) |-> ( {}, {}, {}, {} )", new String[]{typeActRfa, uniteProd,
                    vol1 + "", vol2 + "", vol3 + "", vol4 + ""});
            }
        } finally {
            resultSet.close();
        }
        // watch.stop();
        // LOGGER.debug("Temps de chargement du cache PVolume : {}",
        // watch.toString());
    }

    /**
     * Recupération du poids du volume.
     *
     * @param ua L'unité d'activité
     * @param formule La formule de risque
     *
     * @return Liste des valeurs
     * @throws SQLException l'exception
     */
    public Float getPVolume(UniteActivite ua, FormuleRisque formule) throws SQLException {

        Map<String, BigDecimal[]> cacheTypeActivite = CACHE_P_VOLUME.get(ua.getTaRfa());
        Set<VolumeProduction> volumesProduction = ua.getVolumesProduction();

        if (cacheTypeActivite == null || volumesProduction == null || volumesProduction.size() == 0) {
            return 1F;
        }

        Float poidsCumule = 0F;
        for (VolumeProduction volProd : volumesProduction) {
            BigDecimal[] seuils = cacheTypeActivite.get(volProd.getUniteRfa());
            if (seuils == null) {
                // Cf. règle de gestion NOTE_P_VOL_NB2
                return 1F;
            }

            long seuilVolProd = getPoids(seuils, volProd.getVolume());
            poidsCumule += seuilVolProd;
        }

        return poidsCumule;
    }

    /**
     * Calcul le poids de la note à partir du volume et des seuils récupérées.
     *
     * @param seuils les seuils
     * @param volume le volume
     * @return le poids du volume.
     */
    private int getPoids(BigDecimal[] seuils, BigDecimal volume) {
        int poids = 1;

        if (volume.compareTo(seuils[3]) > 0 && !seuils[3].equals(BigDecimal.ZERO)) {
            poids = 20;
        } else if (volume.compareTo(seuils[2]) > 0 && !seuils[2].equals(BigDecimal.ZERO)) {
            poids = 8;
        } else if (volume.compareTo(seuils[1]) > 0 && !seuils[1].equals(BigDecimal.ZERO)) {
            poids = 4;
        } else if (volume.compareTo(seuils[0]) >= 0) {
            poids = 1;
        }
        return poids;
    }
}
