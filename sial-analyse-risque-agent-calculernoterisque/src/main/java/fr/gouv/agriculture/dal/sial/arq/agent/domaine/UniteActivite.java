package fr.gouv.agriculture.dal.sial.arq.agent.domaine;

import java.util.Set;

/**
 * La classe récupérant les données nécessaires des Unités d'activités de
 * l'ensemble.
 * 
 */
public class UniteActivite {

	/** Identifiant de l'unité d'activité */
    private int uaId;

	/** Code Rfa de l'unité d'activité */
	private String uaRfa;

	/**
	 * Type d'activité
	 */
	private String taRfa;

	/**
	 * Diffusion
	 */
	private String diffusionRfa;

	/**
	 * Les destinations
	 */
	private String[] destinations;

	/**
	 * Les procédés
	 */
	private String[] procedes;

	/**
	 * Les produits
	 */
	private String[] produits;

	/**
	 * Les approbations
	 */
	private String[] approbations;

	/**
	 * La note de risque courante.
	 */
	private NoteRisque noteActuelle;

	/**
	 * Les volumes de production.
	 */
	private Set<VolumeProduction> volumesProduction;

	/**
	 * Les zones
	 */
	private String[] zonesRfa;

	/**
	 * Note de la dernière inspection.
	 */
	private NoteInspection noteDerniereInspection;

	/**
	 * @return the uaId
	 */
    public int getUaId() {
		return uaId;
	}

	/**
	 * @param uaId
	 *            the uaId to set
	 */
    public void setUaId(int uaId) {
		this.uaId = uaId;
	}

	/**
	 * @return the uaRfa
	 */
	public String getUaRfa() {
		return uaRfa;
	}

	/**
	 * @param uaRfa
	 *            the uaRfa to set
	 */
	public void setUaRfa(String uaRfa) {
		this.uaRfa = uaRfa;
	}

	/**
	 * @return the taRfa
	 */
	public String getTaRfa() {
		return taRfa;
	}

	/**
	 * @param taRfa
	 *            the taRfa to set
	 */
	public void setTaRfa(String taRfa) {
		this.taRfa = taRfa;
	}

	/**
	 * @return the diffusionRfa
	 */
	public String getDiffusionRfa() {
		return diffusionRfa;
	}

	/**
	 * @param diffusionRfa
	 *            the diffusionRfa to set
	 */
	public void setDiffusionRfa(String diffusionRfa) {
		this.diffusionRfa = diffusionRfa;
	}

	/**
	 * @return the destinations
	 */
	public String[] getDestinations() {
		return destinations;
	}

	/**
	 * @param destinations
	 *            the destinations to set
	 */
	public void setDestinations(String[] destinations) {
		this.destinations = destinations;
	}

	/**
	 * @return the procedes
	 */
	public String[] getProcedes() {
		return procedes;
	}

	/**
	 * @param procedes
	 *            the procedes to set
	 */
	public void setProcedes(String[] procedes) {
		this.procedes = procedes;
	}

	/**
	 * @return the produits
	 */
	public String[] getProduits() {
		return produits;
	}

	/**
	 * @param produits
	 *            the produits to set
	 */
	public void setProduits(String[] produits) {
		this.produits = produits;
	}

	/**
	 * @return the approbations
	 */
	public String[] getApprobations() {
		return approbations;
	}

	/**
	 * @param approbations
	 *            the approbations to set
	 */
	public void setApprobations(String[] approbations) {
		this.approbations = approbations;
	}

	/**
	 * @return the noteActuelle
	 */
	public NoteRisque getNoteActuelle() {
		return noteActuelle;
	}

	/**
	 * @param noteActuelle
	 *            the noteActuelle to set
	 */
	public void setNoteActuelle(NoteRisque noteActuelle) {
		this.noteActuelle = noteActuelle;
	}

	/**
	 * @return the volumesProduction
	 */
	public Set<VolumeProduction> getVolumesProduction() {
		return volumesProduction;
	}

	/**
	 * @param volumesProduction
	 *            the volumesProduction to set
	 */
	public void setVolumesProduction(Set<VolumeProduction> volumesProduction) {
		this.volumesProduction = volumesProduction;
	}

	/**
	 * @return the zonesRfa
	 */
	public String[] getZonesRfa() {
		return zonesRfa;
	}

	/**
	 * @param zonesRfa
	 *            the zonesRfa to set
	 */
	public void setZonesRfa(String[] zonesRfa) {
		this.zonesRfa = zonesRfa;
	}

	/**
	 * @return the noteDerniereInspection
	 */
	public NoteInspection getNoteDerniereInspection() {
		return noteDerniereInspection;
	}

	/**
	 * @param noteDerniereInspection
	 *            the noteDerniereInspection to set
	 */
	public void setNoteDerniereInspection(NoteInspection noteDerniereInspection) {
		this.noteDerniereInspection = noteDerniereInspection;
	}

}
