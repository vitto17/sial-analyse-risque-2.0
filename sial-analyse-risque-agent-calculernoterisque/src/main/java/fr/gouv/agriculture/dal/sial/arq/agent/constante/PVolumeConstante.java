package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante des Volumes.
 */
public final class PVolumeConstante {

    /**
     * Constructeur privée
     */
    private PVolumeConstante() {
    }

    /**
     * Requête des volumes.
     */
    public final static String REQUETE_SELECT_VOLUME = //
            "SELECT" //
            + "  pond.ta_rfa, pond.uprod_rfa, " // Clefs du cache
            + "  pond.pvol_s1_nb2, " //
            + "  pond.pvol_s2_nb2, " //
            + "  pond.pvol_s3_nb2, " //
            + "  pond.pvol_s4_nb2 " //
            + " FROM "
            + "  analyse_risque.ponderation_volume pond " //
            + " WHERE"
            + "    pond.form_id = ?";

    /**
     * Colonne pvol_s1_nb2.
     */
    public final static String SEUIL_ONE = "pvol_s1_nb2";

    /**
     * Colonne pvol_s2_nb2.
     */
    public final static String SEUIL_TWO = "pvol_s2_nb2";

    /**
     * Colonne pvol_s3_nb2.
     */
    public final static String SEUIL_THREE = "pvol_s3_nb2";

    /**
     * Colonne pvol_s4_nb2.
     */
    public final static String SEUIL_FOUR = "pvol_s4_nb2";

    /**
     * Colonne prod_volume_nb.
     */
    public final static String VOLUME = "prod_volume_nb";

    /**
     * Colonne unite_rfa.
     */
    public final static String UNITE_RFA = "unite_rfa";

}
