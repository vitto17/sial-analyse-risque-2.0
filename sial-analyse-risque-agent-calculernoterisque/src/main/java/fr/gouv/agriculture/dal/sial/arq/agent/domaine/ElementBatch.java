package fr.gouv.agriculture.dal.sial.arq.agent.domaine;

/**
 * La classe des éléments récupérés par le batch.
 * 
 */
public class ElementBatch {

	/** Identifiant de la ligne du batch */
	private int batchId;
	/** Code Rfa de la campagne de la ligne du batch */
	private String campagneRfa;
	/** Code Rfa du Domaine Technique de la ligne du batch */
	private String dtRfa;
	/** Code Rfa des Structures de la ligne du batch */
	private String listStructure;
	/** Note de risque finale */
	private int nbNoteRisque;

	/**
	 * @return the batchId
	 */
	public final int getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId
	 *            the batchId to set
	 */
	public final void setBatchId(final int batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the campagneRfa
	 */
	public final String getCampagneRfa() {
		return campagneRfa;
	}

	/**
	 * @param campagneRfa
	 *            the campagneRfa to set
	 */
	public final void setCampagneRfa(final String campagneRfa) {
		this.campagneRfa = campagneRfa;
	}

	/**
	 * @return the dtRfa
	 */
	public final String getDtRfa() {
		return dtRfa;
	}

	/**
	 * @param dtRfa
	 *            the dtRfa to set
	 */
	public final void setDtRfa(final String dtRfa) {
		this.dtRfa = dtRfa;
	}

	/**
	 * @return the listStructure
	 */
	public final String getListStructure() {
		return listStructure;
	}

	/**
	 * @param listStructure
	 *            the listStructure to set
	 */
	public final void setListStructure(final String listStructure) {
		this.listStructure = listStructure;
	}

	/**
	 * @return the nbNoteRisque
	 */
	public final int getNbNoteRisque() {
		return nbNoteRisque;
	}

	/**
	 * @param nbNoteRisque
	 *            the nbNoteRisque to set
	 */
	public final void setNbNoteRisque(final int nbNoteRisque) {
		this.nbNoteRisque = nbNoteRisque;
	}

}
