package fr.gouv.agriculture.dal.sial.arq.agent.domaine;

/**
 * Formule de risque du couple Domaine Tech, Campagne
 * 
 */
public class FormuleRisque {

	/** Identifiant de la formule de risque */
	private long formId;
	/** Boolean concernant le critère Volume de la formule de risque */
	private boolean pVolActif;
	/** Boolean concernant le critère Risque Pondération de la formule de risque */
	private boolean pRisqueActif;
	/** Boolean concernant le critère Zone de la formule de risque */
	private boolean pZoneActif;
	/** Boolean concernant le critère Diffusion de la formule de risque */
	private boolean pDiffActif;
	/** Boolean concernant le critère Destination de la formule de risque */
	private boolean pDestActif;
	/** Boolean concernant le critère Inspection de la formule de risque */
	private boolean pNiActif;
	/**
	 * Le code de la campagne.
	 */
	private String campRfa;

	/**
	 * @return the formId
	 */
	public final long getFormId() {
		return formId;
	}

	/**
	 * @param formId
	 *            the formId to set
	 */
	public final void setFormId(final long formId) {
		this.formId = formId;
	}

	/**
	 * @return the isPVolActif
	 */
	public final boolean isPVolActif() {
		return pVolActif;
	}

	/**
	 * @param isPVolActif
	 *            the isPVolActif to set
	 */
	public final void setPVolActif(final boolean isPVolActif) {
		this.pVolActif = isPVolActif;
	}

	/**
	 * @return the isPRisqueActif
	 */
	public final boolean isPRisqueActif() {
		return pRisqueActif;
	}

	/**
	 * @param isPRisqueActif
	 *            the isPRisqueActif to set
	 */
	public final void setPRisqueActif(final boolean isPRisqueActif) {
		this.pRisqueActif = isPRisqueActif;
	}

	/**
	 * @return the isPZoneActif
	 */
	public final boolean isPZoneActif() {
		return pZoneActif;
	}

	/**
	 * @param isPZoneActif
	 *            the isPZoneActif to set
	 */
	public final void setPZoneActif(final boolean isPZoneActif) {
		this.pZoneActif = isPZoneActif;
	}

	/**
	 * @return the isPDiffActif
	 */
	public final boolean isPDiffActif() {
		return pDiffActif;
	}

	/**
	 * @param isPDiffActif
	 *            the isPDiffActif to set
	 */
	public final void setPDiffActif(final boolean isPDiffActif) {
		this.pDiffActif = isPDiffActif;
	}

	/**
	 * @return the isPDestActif
	 */
	public final boolean isPDestActif() {
		return pDestActif;
	}

	/**
	 * @param isPDestActif
	 *            the isPDestActif to set
	 */
	public final void setPDestActif(final boolean isPDestActif) {
		this.pDestActif = isPDestActif;
	}

	/**
	 * @return the isPNiActif
	 */
	public final boolean isPNiActif() {
		return pNiActif;
	}

	/**
	 * @param isPNiActif
	 *            the isPNiActif to set
	 */
	public final void setPNiActif(final boolean isPNiActif) {
		this.pNiActif = isPNiActif;
	}

	/**
	 * @return the campRfa
	 */
	public String getCampRfa() {
		return campRfa;
	}

	/**
	 * @param campRfa
	 *            the campRfa to set
	 */
	public void setCampRfa(String campRfa) {
		this.campRfa = campRfa;
	}

}
