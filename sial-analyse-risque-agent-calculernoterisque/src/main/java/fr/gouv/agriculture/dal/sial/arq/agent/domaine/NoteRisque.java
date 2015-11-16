package fr.gouv.agriculture.dal.sial.arq.agent.domaine;

/**
 * La classe Note Risque
 * 
 */
public class NoteRisque {

	private long id;
	/** Note du volume de la note de risque */
	private Float noteVolume;
	/** Note du Risque Pondération de la note de risque */
	private Float noteRisquePond;
	/** Note de zone de la note de risque */
	private Float noteZone;
	/** Note de diffusion de la note de risque */
	private Float noteDiff;
	/** Note de la destination de la note de risque */
	private Float noteDest;
	/** Note d'inspection de la note de risque */
	private Float noteInspection;
	/** Note Finale de la note de risque */
	private Long noteValeur;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the noteVolume
	 */
	public final Float getNoteVolume() {
		return noteVolume;
	}

	/**
	 * @param noteVolume
	 *            the noteVolume to set
	 */
	public final void setNoteVolume(final Float noteVolume) {
		this.noteVolume = noteVolume;
	}

	/**
	 * @return the noteRisque
	 */
	public final Float getNoteRisquePond() {
		return noteRisquePond;
	}

	/**
	 * @param noteRisque
	 *            the noteRisque to set
	 */
	public final void setNoteRisquePond(final Float noteRisque) {
		this.noteRisquePond = noteRisque;
	}

	/**
	 * @return the noteZone
	 */
	public final Float getNoteZone() {
		return noteZone;
	}

	/**
	 * @param noteZone
	 *            the noteZone to set
	 */
	public final void setNoteZone(final Float noteZone) {
		this.noteZone = noteZone;
	}

	/**
	 * @return the noteDiff
	 */
	public final Float getNoteDiff() {
		return noteDiff;
	}

	/**
	 * @param noteDiff
	 *            the noteDiff to set
	 */
	public final void setNoteDiff(final Float noteDiff) {
		this.noteDiff = noteDiff;
	}

	/**
	 * @return the noteDest
	 */
	public final Float getNoteDest() {
		return noteDest;
	}

	/**
	 * @param noteDest
	 *            the noteDest to set
	 */
	public final void setNoteDest(final Float noteDest) {
		this.noteDest = noteDest;
	}

	/**
	 * @return the noteInspection
	 */
	public final Float getNoteInspection() {
		return noteInspection;
	}

	/**
	 * @param noteInspection
	 *            the noteInspection to set
	 */
	public final void setNoteInspection(final Float noteInspection) {
		this.noteInspection = noteInspection;
	}

	/**
	 * @return the noteValeur
	 */
	public final Long getNoteValeur() {
		return noteValeur;
	}

	/**
	 * @param noteValeur
	 *            the noteValeur to set
	 */
	public final void setNoteValeur(final Long noteValeur) {
		this.noteValeur = noteValeur;
	}

	// /**
	// * Reset la note.
	// */
	// public void reset() {
	// this.setNoteDest(null);
	// this.setNoteDiff(null);
	// this.setNoteInspection(null);
	// this.setNoteRisquePond(null);
	// this.setNoteValeur(null);
	// this.setNoteVolume(null);
	// this.setNoteZone(null);
	// }

}
