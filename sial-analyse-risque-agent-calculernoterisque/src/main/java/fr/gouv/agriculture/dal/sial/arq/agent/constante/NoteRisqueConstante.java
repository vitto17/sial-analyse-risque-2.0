package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante de la classe de note de risque.
 */
public final class NoteRisqueConstante {

	/**
	 * Constructeur privée
	 */
	private NoteRisqueConstante() {
	}

	/**
	 * Requete pour récupérer la note de risque existante en base.
	 */
    public final static String REQUETE_SELECT_NOTE_RISQUE = "SELECT note_id, note_val_nb from analyse_risque.note_risque "
         			+ "where ua_rfa = ? " + "and dt_rfa = ? " + "and camp_rfa = ?";

	/**
	 * Requete pour insérer la note de risque.
	 */
    public final static String REQUETE_INSERT_NOTE_RISQUE = "INSERT INTO analyse_risque.note_risque(note_rfa, ua_rfa, dt_rfa, camp_rfa, note_val_nb, note_date_calcul_ds, "
         			+ "note_p_risqu_nb2, note_p_vol_nb2, note_p_zone_nb2, note_p_diff_nb2, "
			+ "note_p_dest_nb2, note_p_ni_nb2, note_prec_val_nb) "
			+ "VALUES (?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, "
			+ "?, ?, ?)";

	/**
	 * Requete pour updater la note de risque.
	 */
    public final static String REQUETE_UPDATE_NOTE_RISQUE = "UPDATE analyse_risque.note_risque "
         			+ "SET note_val_nb=?, note_date_calcul_ds=?, note_p_risqu_nb2=?, note_p_vol_nb2=?, "
			+ "note_p_zone_nb2=?, note_p_diff_nb2=?, note_p_dest_nb2=?, note_p_ni_nb2=?, " + "note_prec_val_nb=? "
			+ "WHERE note_id=?";

	/**
	 * Colonne note_id
	 */
	public final static String NOTE_ID = "note_id";

	/**
	 * Colonne note_val_nb
	 */
	public final static String NOTE_VAL_NB = "note_val_nb";
}
