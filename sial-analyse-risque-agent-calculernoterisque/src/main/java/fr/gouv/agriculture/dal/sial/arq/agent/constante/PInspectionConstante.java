package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante de la note d'inspection.
 */
public final class PInspectionConstante {

	/**
	 * Constructeur privée
	 */
	private PInspectionConstante() {
	}

	/**
	 * Requête pour récupérer la pondération d'inspection.
	 */
	public final static String REQUETE_SELECT_INSP = //
	"SELECT" //
			+ "  ni.nomen_rfa, ni.eval_rfa, " // Clef du cache
			+ "  ni.pni_poids_nb2" + " FROM" + "  analyse_risque.ponderation_note_inpection ni WHERE ni.dt_rfa = ?";
}
