package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante de la Diffusion.
 */
public final class PDiffConstante {

	/**
	 * Constructeur privée
	 */
	private PDiffConstante() {
	}

	/**
	 * Constante pour la requete de select de la valeur max de la diffusion.
	 */
    public final static String REQUETE_SELECT_DIFF = //
            "select dif_rfa, pdiff_poids_nb2" +//
            " from analyse_risque.ponderation_diffusion" +//
			" where form_id = ?";

}
