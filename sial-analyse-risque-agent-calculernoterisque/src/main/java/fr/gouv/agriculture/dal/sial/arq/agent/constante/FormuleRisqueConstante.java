package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Les constantes d'accès à la formule de risque
 *
 */
public final class FormuleRisqueConstante {

    /**
     * Constructeur privée
     */
    private FormuleRisqueConstante() {
    }

    /**
     * Colonne form_id
     */
    public final static String FORM_ID = "form_id";
    /**
     * Colonne from_crit_risquetheorique_on
     */
    public final static String RISQUE_THEORIQUE = "from_crit_risquetheorique_on";
    /**
     * Colonne from_crit_zone_on
     */
    public final static String ZONE = "from_crit_zone_on";
    /**
     * Colonne from_crit_volume_on
     */
    public final static String VOLUME = "from_crit_volume_on";
    /**
     * Colonne from_crit_diffusion_on
     */
    public final static String DIFFUSION = "from_crit_diffusion_on";
    /**
     * Colonne from_crit_destination_on
     */
    public final static String DESTINATION = "from_crit_destination_on";
    /**
     * Colonne from_crit_note_inspection_on
     */
    public final static String INSPECTION = "from_crit_note_inspection_on";

    /**
     * Nom de la colonne qui remonte le code de la campagne.
     */
    public final static String CAMP_RFA = "camp_rfa";

    /**
     * Constante pour la requete de select des formules
     */
    public final static String REQUETE_SELECT_FORM = "select form_id, from_crit_risquetheorique_on, "
            + "from_crit_zone_on, from_crit_volume_on, " + "from_crit_diffusion_on, from_crit_destination_on, "
            + "from_crit_note_inspection_on, " //
            + "camp_rfa as " + CAMP_RFA //
            + " from analyse_risque.formule_risque" + " where dt_rfa = ? "
            + "and camp_rfa = ?";

}
