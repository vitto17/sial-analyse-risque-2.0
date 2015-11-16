package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.UniteActiviteConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.NoteInspection;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.NoteRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.VolumeProduction;
import fr.gouv.agriculture.o2.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao des unites d'activites.
 *
 */
public class UniteActiviteDAO extends BaseJBDCTemplateDAO {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UniteActiviteDAO.class);

    /**
     * Recupération des unites d'activites selon la campagne et le Domaine
     * Technique.
     *
     * @param dtRfa le code Rfa du domaine technique
     * @param campRfa le code Rfa de la campagne
     *
     * @return Liste des valeurs retournées
     * @throws Exception l'exception
     *
     */
    @Transactional
    public List<UniteActivite> getUAFromDT(String dtRfa, String campRfa) throws Exception {
        StopWatch watch = new StopWatch();
        watch.start();
        String sql = UniteActiviteConstante.REQUETE_SELECT_UA;
        // LOGGER.debug("Execution de la requete : {}", sql);

        ResultSet resultSet = this.performQuery(sql, campRfa, dtRfa);
        List<UniteActivite> listeUA = new ArrayList<UniteActivite>(200000);
        try {
            while (resultSet.next()) {
                UniteActivite unite = new UniteActivite();
                unite.setUaId(resultSet.getInt(UniteActiviteConstante.UA_ID));
                // LOGGER.debug("Résultat de la requete select UA id : " +
                // resultSet.getInt(UniteActiviteConstante.UA_ID));
                unite.setUaRfa(resultSet.getString(UniteActiviteConstante.UA_RFA));
                // LOGGER.debug("Résultat de la requete select UA rfa : "+
                // resultSet.getString(UniteActiviteConstante.UA_RFA));
                unite.setTaRfa(resultSet.getString(UniteActiviteConstante.TA_RFA));
                unite.setDiffusionRfa(resultSet.getString(UniteActiviteConstante.DIFFUSION_RFA));
                unite.setDestinations((String[]) (resultSet.getArray(UniteActiviteConstante.DESTINATION_RFA_ARRAY)
                        .getArray()));
                unite.setApprobations((String[]) (resultSet.getArray(UniteActiviteConstante.APPROBATION_RFA_ARRAY)
                        .getArray()));
                unite.setProcedes((String[]) (resultSet.getArray(UniteActiviteConstante.PROCEDE_RFA_ARRAY).getArray()));
                unite.setProduits((String[]) (resultSet.getArray(UniteActiviteConstante.PRODUIT_RFA_ARRAY).getArray()));
                unite.setVolumesProduction(volumesProd((String[]) (resultSet
                        .getArray(UniteActiviteConstante.VOL_PROD_ARRAY).getArray())));
                unite.setZonesRfa((String[]) (resultSet.getArray(UniteActiviteConstante.ZONE_RFA_ARRAY).getArray()));
                unite.setNoteDerniereInspection(noteInspection(resultSet
                        .getString(UniteActiviteConstante.DERN_NOTE_INSP_CLEF)));
                if (resultSet.getObject(UniteActiviteConstante.NOTE_ACTUELLE_ID) == null) {
                    unite.setNoteActuelle(null);
                } else {
                    unite.setNoteActuelle(new NoteRisque());
                    unite.getNoteActuelle().setId(resultSet.getLong(UniteActiviteConstante.NOTE_ACTUELLE_ID));
                    unite.getNoteActuelle().setNoteValeur(
                            resultSet.getLong(UniteActiviteConstante.NOTE_ACTUELLE_VALEUR));
                }

                listeUA.add(unite);
            }
        } finally {
            resultSet.close();
        }
        watch.stop();
        // LOGGER.info("Temps de chargement des {} UAs du Domaine Technique '{}' : {}",
        // new String[] {
        // listeUA.size() + "", dtRfa, watch.toString() });
        return listeUA;
    }

    /**
     * Recupération des unites d'activites selon la structure.
     *
     * @param dtRfa le rfa du domaine technique
     * @param campRfa le code Rfa de la campagne
     * @param structs la liste des structures
     *
     * @return Liste des valeurs retournées
     * @throws Exception l'exception
     *
     */
    public List<UniteActivite> getUAFromStructs(String dtRfa, String campRfa, String structs) throws Exception {
        StopWatch watch = new StopWatch();
        watch.start();
        String sql = UniteActiviteConstante.REQUETE_SELECT_VIA_STRUCT;
        StringBuilder inClause = new StringBuilder(100);
        // LOGGER.debug("Execution de la requete : {}", sql);
        List<UniteActivite> listeUA = new ArrayList<UniteActivite>(100000);
        Object[] params;
        if (StringUtils.isNotBlank(structs)) {
            String[] array = structs.split(", ");
            params = new Object[array.length + 2];
            params[0] = campRfa;
            ResultSet resultSet;
            for (int i = 0; i < array.length; i++) {
                inClause.append("?,");
                params[i + 1] = array[i];
            }
            sql = sql.replace("?REPLACE?", inClause.substring(0, inClause.length() - 1));
            params[params.length - 1] = dtRfa;
            LOGGER.info("SQL APRES REMPLACEMENT=" + sql);
            resultSet = this.performQuery(sql, params);
            try {
                while (resultSet.next()) {
                    UniteActivite unite = new UniteActivite();
                    unite.setUaId(resultSet.getInt(UniteActiviteConstante.UA_ID));
                    // LOGGER.debug("Résultat de la requete select UA id : " +
                    // resultSet.getInt(UniteActiviteConstante.UA_ID));
                    unite.setUaRfa(resultSet.getString(UniteActiviteConstante.UA_RFA));
                    // LOGGER.debug("Résultat de la requete select UA rfa : "+
                    // resultSet.getString(UniteActiviteConstante.UA_RFA));
                    unite.setTaRfa(resultSet.getString(UniteActiviteConstante.TA_RFA));
                    unite.setDiffusionRfa(resultSet.getString(UniteActiviteConstante.DIFFUSION_RFA));
                    unite.setDestinations((String[]) (resultSet.getArray(UniteActiviteConstante.DESTINATION_RFA_ARRAY)
                            .getArray()));
                    unite.setApprobations((String[]) (resultSet.getArray(UniteActiviteConstante.APPROBATION_RFA_ARRAY)
                            .getArray()));
                    unite.setProcedes((String[]) (resultSet.getArray(UniteActiviteConstante.PROCEDE_RFA_ARRAY)
                            .getArray()));
                    unite.setProduits((String[]) (resultSet.getArray(UniteActiviteConstante.PRODUIT_RFA_ARRAY)
                            .getArray()));
                    unite.setVolumesProduction(volumesProd((String[]) (resultSet
                            .getArray(UniteActiviteConstante.VOL_PROD_ARRAY).getArray())));
                    unite.setZonesRfa((String[]) (resultSet.getArray(UniteActiviteConstante.ZONE_RFA_ARRAY).getArray()));
                    unite.setNoteDerniereInspection(noteInspection(resultSet
                            .getString(UniteActiviteConstante.DERN_NOTE_INSP_CLEF)));
                    if (resultSet.getObject(UniteActiviteConstante.NOTE_ACTUELLE_ID) == null) {
                        unite.setNoteActuelle(null);
                    } else {
                        unite.setNoteActuelle(new NoteRisque());
                        unite.getNoteActuelle().setId(resultSet.getLong(UniteActiviteConstante.NOTE_ACTUELLE_ID));
                        unite.getNoteActuelle().setNoteValeur(
                                resultSet.getLong(UniteActiviteConstante.NOTE_ACTUELLE_VALEUR));
                    }

                    listeUA.add(unite);
                }
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        }
        watch.stop();
        LOGGER.info("Temps de chargement des UA des structures {} : {}", structs, watch.toString());
        return listeUA;
    }

    private Set<VolumeProduction> volumesProd(String[] volumes) throws Exception {
        Set<VolumeProduction> volumesProd = new HashSet<VolumeProduction>(10);

        if (volumes != null) {
            for (String volume : volumes) {
                if (StringUtils.isNotBlank(volume)) {
                    VolumeProduction volProd = new VolumeProduction();

                    String uniteRfa = volume.substring(0, volume.indexOf(";"));
                    volProd.setUniteRfa(uniteRfa);

                    String volumeNbr = volume.substring(volume.indexOf(";") + 1, volume.length());
                    try {
                        volProd.setVolume(new BigDecimal(volumeNbr));
                    } catch (NumberFormatException nfe) {
                        throw new Exception("Volumne incorrect pour l'unité d'activité '" + uniteRfa + "'.", nfe);
                    }
                    volumesProd.add(volProd);
                }
            }
        }

        return volumesProd;
    }

    private NoteInspection noteInspection(String note) {
        if (StringUtils.isNotBlank(note)) {
            NoteInspection ni = new NoteInspection();

            String nomenRfa = note.substring(0, note.indexOf(";"));
            ni.setNomenRfa(nomenRfa);

            String evalRfa = note.substring(note.indexOf(";") + 1, note.length());
            ni.setEvalRfa(evalRfa);

            return ni;
        } else {
            return null;
        }
    }
}
