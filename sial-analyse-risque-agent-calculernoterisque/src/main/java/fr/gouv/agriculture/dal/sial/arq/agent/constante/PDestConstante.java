package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante de la Destination.
 */
public final class PDestConstante {

	/**
	 * Constructeur privée
	 */
	private PDestConstante() {
	}

	/**
	 * Constante pour la requete de select de la valeur max de la destination.
	 */
    public final static String REQUETE_SELECT_DEST = //
            "SELECT" + //
            "    pond.dest_rfa, pond.ta_rfa, pond.pdest_poids_nb2" + //
            " FROM" + //
            "    analyse_risque.ponderation_destination pond " + //
            " WHERE" + //
            "    pond.form_id = ?";
}
