package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante pour la classe Element Batch
 */
public final class ElementBatchConstante {

	/**
	 * Constructeur privé.
	 */
	private ElementBatchConstante() {
	}

	/**
	 * Le header pour la la variable batch_id
	 */
	public final static String BATCH_ID = "batch_id";

	/**
	 * Le header pour la la variable camp_rfa
	 */
	public final static String CAMP_RFA = "camp_rfa";

	/**
	 * Le header pour la la variable dt_rfa
	 */
	public final static String DT_RFA = "dt_rfa";

	/**
	 * Le header pour la la variable batch_list_struct_rfa
	 */
	public final static String LIST_STRUCT_RFA = "batch_list_struct_rfa";

	/**
	 * Constante A_LA_DEMANDE
	 */
	public final static String A_LA_DEMANDE = "A_LA_DEMANDE";

	/**
	 * Constante TOUTES
	 */
	public final static String TOUTES = "TOUTES";

	/**
	 * Requête pour la mise à jour de la table Batch lors du démarrage.
	 */
    public final static String UPDATE_START_BATCH = "UPDATE analyse_risque.batch "
         			+ "SET statut_id=?, batch_debut_ts=?, batch_fin_ts=NULL " + "WHERE batch_id=?";

	/**
	 * Requête pour la mise à jour de la table Batch lors du démarrage.
	 */
    public final static String UPDATE_STOP_BATCH = "UPDATE analyse_risque.batch " + "SET statut_id=?, batch_fin_ts=? "
         			+ "WHERE batch_id=?";

	/**
	 * Requête pour la mise à jour du nombre d'Ua de la table Batch.
	 */
    public final static String UPDATE_NB_UA_BATCH = "UPDATE analyse_risque.batch "
         			+ "SET batch_nbr_ua_total_nb=?, batch_nbr_ua_traite_nb=0" + "WHERE batch_id=?";

	/**
	 * Requête pour la mise à jour du nombre d'Ua de la table Batch.
	 */
    public final static String UPDATE_NB_UA_TRAITE_BATCH = "UPDATE analyse_risque.batch " + "SET batch_nbr_ua_traite_nb=? "
         			+ "WHERE batch_id=?";
}
