package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante de la Zone Geographique.
 */
public final class PZoneConstante {

	/**
	 * Constructeur privée
	 */
	private PZoneConstante() {
	}

    /**
     * Colonne zone_rfa
     */
    public final static String ZONE_RFA = "zone_rfa";

    /**
     * Constante pour la requete de select de la valeur max de la zone geographique.
     */
    public final static String REQUETE_SELECT_ZONE = //
            "SELECT" //
            + "  zone.z_rfa as " + ZONE_RFA + ", " // clef du cache
            + "  zone.pzone_poids_nb2 " //
            + " FROM"
            + "  analyse_risque.ponderation_zone zone " //
            + " WHERE"
            + "    zone.form_id = ?";

}
