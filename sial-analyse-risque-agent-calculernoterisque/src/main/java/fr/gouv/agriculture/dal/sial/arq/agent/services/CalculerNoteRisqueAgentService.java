package fr.gouv.agriculture.dal.sial.arq.agent.services;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.ElementBatchConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.constante.FormuleRisqueConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.ApprobationValideDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.BatchDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.NoteRisqueDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.PDestDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.PDiffDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.PInspectionDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.PRisqueDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.PVolumeDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.PZoneDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.dao.UniteActiviteDAO;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.ElementBatch;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.NoteRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.o2.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.apache.camel.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service métier.
 *
 * <p>
 * Cette classe de service métier utilise l'annotation @Transactionnal pour
 * gérer les transactions avec la base de données.
 *
 * @author olivier-pillaudtirard
 * @author frederic.danna
 */
@Transactional
public class CalculerNoteRisqueAgentService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculerNoteRisqueAgentService.class);

    /**
     * Dao des Unites d'activité
     */
    @Inject
    private UniteActiviteDAO uaDAO;
    /**
     * Dao des formules de risques
     */
    @Inject
    private FormuleRisqueDAO formuleDAO;
    /**
     * Dao des Poids des volumes
     */
    @Inject
    private PVolumeDAO volumeDAO;
    /**
     * Dao des Poids des Risques
     */
    @Inject
    private PRisqueDAO risqueDAO;
    /**
     * Dao des Poids des Zones
     */
    @Inject
    private PZoneDAO zoneDAO;
    /**
     * Dao des Poids des diffusions
     */
    @Inject
    private PDiffDAO diffDAO;
    /**
     * Dao des Poids des destinations
     */
    @Inject
    private PDestDAO destDAO;
    /**
     * Dao des Poids des Inspections
     */
    @Inject
    private PInspectionDAO inspDAO;
    /**
     * Dao des Poids des Notes de Risques
     */
    @Inject
    private NoteRisqueDAO noteRisqueDao;
    /**
     * Dao des Approbations Valides
     */
    @Inject
    private ApprobationValideDAO approValideDAO;

    /**
     * Dao de la table Batch
     */
    @Inject
    private BatchDAO batchDao;

    /**
     * Nombre de lot a traités lors d'une transaction
     */
    @Inject
    private int lotCommitUaTraitees;

    /**
     * Mise à jour du statut de la table Batch "running"
     */
    private void updateStartBatch(ElementBatch donnees) throws Exception {
        batchDao.updateStartBatch(donnees.getBatchId(), 2);
    }

    /**
     * Mise à jour du statut de la table Batch "OK"
     */
    private void updateStopBatchOK(ElementBatch donnees) throws Exception {
        batchDao.updateStopBatch(donnees.getBatchId(), 1);
    }

    /**
     * Mise à jour du statut de la table Batch "KO"
     */
    private void updateStopBatchKO(ElementBatch donnees) throws Exception {
        batchDao.updateStopBatch(donnees.getBatchId(), 3);
    }

    /**
     * Traitement principale du scénario TOUTES. Récupère la liste des campagnes
     * actives et traites toutes les Unités d'activités concernées.
     *
     * @param camelMessage le message Camel
     * @throws Exception l'exception
     */
    @SuppressWarnings("unchecked")
    public void traitementToutes(Message camelMessage) throws Exception {

        List<Map<String, Object>> list = camelMessage.getBody(List.class);
        for (Map<String, Object> map : list) {

            ElementBatch donnees = new ElementBatch();
            donnees.setCampagneRfa((String) map.get(ElementBatchConstante.CAMP_RFA));
            donnees.setDtRfa((String) map.get(ElementBatchConstante.DT_RFA));
            LOGGER.info("Traitement toutes : campagne {}, domaine technique {}", donnees.getCampagneRfa(), donnees.getDtRfa());

            FormuleRisque formule = new FormuleRisque();
            formule.setFormId((Integer) map.get(FormuleRisqueConstante.FORM_ID));
            formule.setPDestActif((Boolean) map.get(FormuleRisqueConstante.DESTINATION));
            formule.setPDiffActif((Boolean) map.get(FormuleRisqueConstante.DIFFUSION));
            formule.setPNiActif((Boolean) map.get(FormuleRisqueConstante.INSPECTION));
            formule.setPRisqueActif((Boolean) map.get(FormuleRisqueConstante.RISQUE_THEORIQUE));
            formule.setPVolActif((Boolean) map.get(FormuleRisqueConstante.VOLUME));
            formule.setPZoneActif((Boolean) map.get(FormuleRisqueConstante.ZONE));

            // Chargement des caches de données :
            chargeCachesDonnees(formule, donnees);

            // Récupération des UA.
            List<UniteActivite> listUA = uaDAO.getUAFromDT(donnees.getDtRfa(), donnees.getCampagneRfa());
            // Découpe des éléments en liste de 'lotCommitUaTraitees' éléments.
            int min = 0;
            int max = lotCommitUaTraitees;

            while (max < listUA.size()) {
                traitementCalcul(formule, listUA.subList(min, max), donnees);
                min += lotCommitUaTraitees;
                max += lotCommitUaTraitees;
            }
            // Dernier traitement
            traitementCalcul(formule, listUA.subList(min, listUA.size()), donnees);
        }
    }

    /**
     * Traitement Principal du scénario A LA DEMANDE. Récupère les informations
     * de la table batch et les traite ligne par ligne.
     *
     * @param camelMessage le message Camel
     * @throws Exception l'exception
     */
    @SuppressWarnings("unchecked")
    public void traitement(Message camelMessage) throws Exception {
        StopWatch watch = new StopWatch();
        watch.start();
        Map<String, Object> map = camelMessage.getBody(Map.class);
        ElementBatch donnees = new ElementBatch();
        donnees.setBatchId((Integer) map.get(ElementBatchConstante.BATCH_ID));
        donnees.setCampagneRfa((String) map.get(ElementBatchConstante.CAMP_RFA));
        donnees.setDtRfa((String) map.get(ElementBatchConstante.DT_RFA));
        donnees.setListStructure((String) map.get(ElementBatchConstante.LIST_STRUCT_RFA));
        donnees.setNbNoteRisque(0);
        // Mise à jour du statut de la table Batch En cours
        updateStartBatch(donnees);
        try {
            LOGGER.info("Traitement batch {}", donnees.getBatchId());
            traitementBatch(donnees);
            // Mise à jour du statut de la table Batch OK
            updateStopBatchOK(donnees);
        } catch (Exception e) {
            // Mise à jour du statut de la table Batch KO
            updateStopBatchKO(donnees);
            LOGGER.error("Problème sur le batch pour le batch_id " + donnees.getBatchId(), e);
        }
        watch.stop();
        LOGGER.info("Temps complet pour le traitement du batch {} - DT '{}', CAMP '{}' : {}.", new String[]{
            donnees.getBatchId() + "", donnees.getDtRfa(), donnees.getCampagneRfa(), watch.toString()
        });

    }

    /**
     * Récupére les éléments à mettre à jour et appelle le traitement du calcul
     * et de la note.
     *
     * @param element l'element contenant les informations du batch
     * @throws Exception l'exception survenue durant le traitement
     */
    private void traitementBatch(ElementBatch element) throws Exception {

        // Récupération de la formule de risque
        FormuleRisque formule = formuleDAO.recupFormuleRisque(element.getDtRfa(), element.getCampagneRfa());

        // Chargement des caches de données :
        chargeCachesDonnees(formule, element);

        // Récupération des UA.
        List<UniteActivite> listUA;
        String structures = element.getListStructure();
        if (StringUtils.isNotBlank(structures)) {
            listUA = uaDAO.getUAFromStructs(element.getDtRfa(), element.getCampagneRfa(), structures);
        } else {
            listUA = uaDAO.getUAFromDT(element.getDtRfa(), element.getCampagneRfa());
        }

        // Mise à jour du nombre d'ua traitées
        updateNbUaBatch(element, listUA);

        // Découpe des éléments en liste de 100 éléments.
        int min = 0;
        int max = lotCommitUaTraitees;

        while (max < listUA.size()) {
            traitementCalcul(formule, listUA.subList(min, max), element);
            // Mise Ã  jour du nombre d'Ua traitées
            updateNbUaTraiteesBatch(element, max);
            // LOGGER.info("Batch {} : {} UAs traitées", element.getBatchId(),
            // max);
            min += lotCommitUaTraitees;
            max += lotCommitUaTraitees;

        }
        // Dernier traitement
        traitementCalcul(formule, listUA.subList(min, listUA.size()), element);
        updateNbUaTraiteesBatch(element, listUA.size());
    }

    /**
     * Récupère les données du calcul et effectue le calcul de la note finale
     *
     * @param formule la formule de risque.
     * @param listUA la liste des UA a traitées
     * @param element l'element contenant les données du batch
     *
     * @throws Exception
     */
    private void traitementCalcul(FormuleRisque formule, List<UniteActivite> listUA, ElementBatch element)
            throws Exception {

        initStatements();

        StopWatch watch = new StopWatch();
        watch.start();
        long cptUATraitees = 0;
        for (UniteActivite unite : listUA) {
            try {
                cptUATraitees++;

                // >>> FDA+FRL 2015/09 Mantis 196 (surement à retirer en V2.0)
                // ARQ_023_Règles_RestrictionPérimètre [ARQ_V1.0_68_SFD_AnalysedeRisque_V7.0]
                if (element.getDtRfa().equals("SSA1")) {

                    boolean uaATraiter = false;
                    for (String approUA : unite.getApprobations()) {
                        // approUA == vra.rappr_rfa || ';' || vra.VREA_STATUT_LB || ';' || vra.DT_RFA

                        String rfaRAppr = approUA.substring(0, approUA.indexOf(";"));
                        approUA = approUA.substring(approUA.indexOf(";") + 1);

                        String libStatutAppro = approUA.substring(0, approUA.indexOf(";"));
                        approUA = approUA.substring(approUA.indexOf(";") + 1);

                        String rfaDTApproUA = approUA;

                        if (rfaDTApproUA.equals("SSA1") && approValideDAO.contains(libStatutAppro)) {
                            // On traite cette UA.
                            uaATraiter = true;
                            break;
                        }
                    }
                    if (!uaATraiter) {
                        continue; // UA suivante.
                    }
                }
                // <<< FDA+FRL 2015/09 Mantis 196 (surement à retirer en V2.0)

                NoteRisque noteRisque = new NoteRisque();

                // Récupération Note_P_Vol_Nb2
                if (formule.isPVolActif()) {
                    noteRisque.setNoteVolume(volumeDAO.getPVolume(unite, formule));
                }

                // Récupération Note_P_Risque_Nb2
                if (formule.isPRisqueActif()) {
                    noteRisque.setNoteRisquePond(risqueDAO.getPRisque(unite));
                }

                // Récupération Note_P_Zone_Nb2
                if (formule.isPZoneActif()) {
                    noteRisque.setNoteZone(zoneDAO.getPZone(unite));
                }

                // Récupération Note_P_Diff_Nb2
                if (formule.isPDiffActif()) {
                    noteRisque.setNoteDiff(diffDAO.getPDiff(unite));
                }

                // Récupération Note_P_Dest_Nb2
                if (formule.isPDestActif()) {
                    // String[] uaDestRa = unite.getDestinations();
                    noteRisque.setNoteDest(destDAO.getPDest(unite));
                }

                // Récupération Note_P_Ni_Nb2
                if (formule.isPNiActif()) {
                    noteRisque.setNoteInspection(inspDAO.getPInsp(unite));
                }

                calculNote(noteRisque);
                // Mise Ã  jour de la table Note Risque
                element.setNbNoteRisque(noteRisqueDao.updateNoteRisque(noteRisque, element, unite,
                        element.getNbNoteRisque(), cptUATraitees, listUA.size()));
            } catch (Exception e) {
                LOGGER.error("Erreur survenue sur l'UA : " + unite.getUaId(), e);
                throw e;
            }
        }

        assert cptUATraitees == listUA.size();
        noteRisqueDao.sauvegardeNotesSiNecessaire(element.getNbNoteRisque(), cptUATraitees, listUA.size());

        watch.stop();
        LOGGER.info("Temps pour les {} UA : {}", listUA.size(), watch.toString());
    }

    /**
     * Calcul la note final du risque Ã  partir des notes récupérées.
     *
     * @param noteRisque l'objet contenant les notes.
     */
    private void calculNote(NoteRisque noteRisque) {
        double result = 1;
        if (noteRisque.getNoteVolume() != null) {
            result *= noteRisque.getNoteVolume();
        }
        if (noteRisque.getNoteRisquePond() != null) {
            result *= noteRisque.getNoteRisquePond();
        }
        if (noteRisque.getNoteZone() != null) {
            result *= noteRisque.getNoteZone();
        }
        if (noteRisque.getNoteDiff() != null) {
            result *= noteRisque.getNoteDiff();
        }
        if (noteRisque.getNoteDest() != null) {
            result *= noteRisque.getNoteDest();
        }
        if (noteRisque.getNoteInspection() != null) {
            result *= noteRisque.getNoteInspection();
        }
        noteRisque.setNoteValeur(Math.round(result));

    }

    /**
     * @param lotCommitUaTraitees the lotCommitUaTraitees to set
     */
    public void setLotCommitUaTraitees(int lotCommitUaTraitees) {
        this.lotCommitUaTraitees = lotCommitUaTraitees;
    }

    private void chargeCachesDonnees(FormuleRisque formule, ElementBatch element) throws Exception {
        destDAO.chargeCache(formule);
        diffDAO.chargeCache(formule);
        inspDAO.chargeCache(element.getDtRfa());
        risqueDAO.chargeCache(formule);
        volumeDAO.chargeCache(formule);
        zoneDAO.chargeCache(formule);
        approValideDAO.chargeCache();
    }

    /**
     * Mise à jour du nombre d'UA à traiter
     */
    private void updateNbUaBatch(ElementBatch element, List<UniteActivite> listUA) throws Exception {
        batchDao.updateNbUaBatch(element.getBatchId(), listUA.size());
    }

    /**
     * Mise à jour du nombre d'UA traitées
     */
    private void updateNbUaTraiteesBatch(ElementBatch element, int max) throws Exception {
        batchDao.updateNbUaTraiteesBatch(element.getBatchId(), max);
    }

    private void initStatements() throws Exception {
        batchDao.initStatements();
        noteRisqueDao.initStatements();
    }
}
