package fr.gouv.agriculture.dal.sial.arq.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.dal.sial.arq.business.rule.FormuleRisqueRule;
import fr.gouv.agriculture.dal.sial.arq.constante.TypePonderation;
import fr.gouv.agriculture.dal.sial.arq.helper.Formatter;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class FormuleRisque.
 *
 * @author Hibernate CodeGenerator Renderer:
 *         fr.gouv.agriculture.orion.automation
 *         .persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class FormuleRisque extends fr.gouv.agriculture.orion.business.BaseEntity {

	/**  Service utilitaire. */
	@Inject
	private Formatter formatter;

	/**  serial. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant OUI. */
	private static final String OUI = "oui";
	
	/** The Constant NON. */
	private static final String NON = "";

	/**  identifier field. */
	private Integer formId;

	/**  persistent field. */
	private VDomaineTechnique domaineTechnique;

	/**  persistent field. */
	private VCampagne campagne;

	/**  persistent field. */
	private String fromAuteurCreationLb;

	/**  persistent field. */
	private Date formCreationDt;

	/**  nullable persistent field. */
	private String formAuteurModifLb;

	/**  nullable persistent field. */
	private Date formDerniereModifTs;

	/**  persistent field. */
	private boolean fromCritRisquetheoriqueOn;

	/**  persistent field. */
	private boolean fromCritZoneOn;

	/**  persistent field. */
	private boolean fromCritVolumeOn;

	/**  persistent field. */
	private boolean fromCritDiffusionOn;

	/**  persistent field. */
	private boolean fromCritDestinationOn;

	/**  persistent field. */
	private boolean fromCritNoteInspectionOn;

	/**  persistent field. */
	private List<PonderationDestination> ponderationDestinations;

	/**  persistent field. */
	private List<PonderationRisqueTheorique> ponderationRisqueTheoriques;

	/**  persistent field. */
	private List<ModificationPonderation> modificationPonderations;

	/**  persistent field. */
	private List<PonderationZone> ponderationZones;

	/**  persistent field. */
	private List<PonderationVolume> ponderationVolumes;

	/**  persistent field. */
	private List<PonderationDiffusion> ponderationDiffusions;

	/**
	 *  Empty constructor.
	 */
	public FormuleRisque() {
		super();
	}

	/**
	 * Gets the form id.
	 *
	 * @return the form id
	 */
	public Integer getFormId() {
		return this.formId;
	}

	/**
	 * Sets the form id.
	 *
	 * @param formId the new form id
	 */
	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	/**
	 * Gets the from auteur creation lb.
	 *
	 * @return the from auteur creation lb
	 */
	public String getFromAuteurCreationLb() {
		return this.fromAuteurCreationLb;
	}

	/**
	 * Sets the from auteur creation lb.
	 *
	 * @param fromAuteurCreationLb the new from auteur creation lb
	 */
	public void setFromAuteurCreationLb(String fromAuteurCreationLb) {
		this.fromAuteurCreationLb = fromAuteurCreationLb;
	}

	/**
	 * Gets the form creation dt.
	 *
	 * @return the form creation dt
	 */
	public Date getFormCreationDt() {
		return this.formCreationDt;
	}

	/**
	 * Sets the form creation dt.
	 *
	 * @param formCreationDt the new form creation dt
	 */
	public void setFormCreationDt(Date formCreationDt) {
		this.formCreationDt = formCreationDt;
	}

	/**
	 * Gets the form auteur modif lb.
	 *
	 * @return the form auteur modif lb
	 */
	public String getFormAuteurModifLb() {
		return this.formAuteurModifLb;
	}

	/**
	 * Sets the form auteur modif lb.
	 *
	 * @param formAuteurModifLb the new form auteur modif lb
	 */
	public void setFormAuteurModifLb(String formAuteurModifLb) {
		this.formAuteurModifLb = formAuteurModifLb;
	}

	/**
	 * Gets the form derniere modif ts.
	 *
	 * @return the form derniere modif ts
	 */
	public Date getFormDerniereModifTs() {
		return this.formDerniereModifTs;
	}

	/**
	 * Sets the form derniere modif ts.
	 *
	 * @param formDerniereModifTs the new form derniere modif ts
	 */
	public void setFormDerniereModifTs(Date formDerniereModifTs) {
		this.formDerniereModifTs = formDerniereModifTs;
	}

	/**
	 * Checks if is from crit risquetheorique on.
	 *
	 * @return true, if is from crit risquetheorique on
	 */
	public boolean isFromCritRisquetheoriqueOn() {
		return this.fromCritRisquetheoriqueOn;
	}

	/**
	 * Sets the from crit risquetheorique on.
	 *
	 * @param fromCritRisquetheoriqueOn the new from crit risquetheorique on
	 */
	public void setFromCritRisquetheoriqueOn(boolean fromCritRisquetheoriqueOn) {
		this.fromCritRisquetheoriqueOn = fromCritRisquetheoriqueOn;
	}

	/**
	 * Checks if is from crit zone on.
	 *
	 * @return true, if is from crit zone on
	 */
	public boolean isFromCritZoneOn() {
		return this.fromCritZoneOn;
	}

	/**
	 * Sets the from crit zone on.
	 *
	 * @param fromCritZoneOn the new from crit zone on
	 */
	public void setFromCritZoneOn(boolean fromCritZoneOn) {
		this.fromCritZoneOn = fromCritZoneOn;
	}

	/**
	 * Checks if is from crit volume on.
	 *
	 * @return true, if is from crit volume on
	 */
	public boolean isFromCritVolumeOn() {
		return this.fromCritVolumeOn;
	}

	/**
	 * Sets the from crit volume on.
	 *
	 * @param fromCritVolumeOn the new from crit volume on
	 */
	public void setFromCritVolumeOn(boolean fromCritVolumeOn) {
		this.fromCritVolumeOn = fromCritVolumeOn;
	}

	/**
	 * Checks if is from crit diffusion on.
	 *
	 * @return true, if is from crit diffusion on
	 */
	public boolean isFromCritDiffusionOn() {
		return this.fromCritDiffusionOn;
	}

	/**
	 * Sets the from crit diffusion on.
	 *
	 * @param fromCritDiffusionOn the new from crit diffusion on
	 */
	public void setFromCritDiffusionOn(boolean fromCritDiffusionOn) {
		this.fromCritDiffusionOn = fromCritDiffusionOn;
	}

	/**
	 * Checks if is from crit destination on.
	 *
	 * @return true, if is from crit destination on
	 */
	public boolean isFromCritDestinationOn() {
		return this.fromCritDestinationOn;
	}

	/**
	 * Sets the from crit destination on.
	 *
	 * @param fromCritDestinationOn the new from crit destination on
	 */
	public void setFromCritDestinationOn(boolean fromCritDestinationOn) {
		this.fromCritDestinationOn = fromCritDestinationOn;
	}

	/**
	 * Checks if is from crit note inspection on.
	 *
	 * @return true, if is from crit note inspection on
	 */
	public boolean isFromCritNoteInspectionOn() {
		return this.fromCritNoteInspectionOn;
	}

	/**
	 * Sets the from crit note inspection on.
	 *
	 * @param fromCritNoteInspectionOn the new from crit note inspection on
	 */
	public void setFromCritNoteInspectionOn(boolean fromCritNoteInspectionOn) {
		this.fromCritNoteInspectionOn = fromCritNoteInspectionOn;
	}

	/**
	 * Gets the domaine technique.
	 *
	 * @return the domaineTechnique
	 */
	public VDomaineTechnique getDomaineTechnique() {
		return domaineTechnique;
	}

	/**
	 * Sets the domaine technique.
	 *
	 * @param domaineTechnique            the domaineTechnique to set
	 */
	public void setDomaineTechnique(VDomaineTechnique domaineTechnique) {
		this.domaineTechnique = domaineTechnique;
	}

	/**
	 * Gets the ponderation destinations.
	 *
	 * @return the ponderation destinations
	 */
	public List<PonderationDestination> getPonderationDestinations() {
		return ponderationDestinations;
	}

	/**
	 * Sets the ponderation destinations.
	 *
	 * @param ponderationDestinations the new ponderation destinations
	 */
	public void setPonderationDestinations(List<PonderationDestination> ponderationDestinations) {
		this.ponderationDestinations = ponderationDestinations;
	}

	/**
	 * Gets the ponderation risque theoriques.
	 *
	 * @return the ponderation risque theoriques
	 */
	public List<PonderationRisqueTheorique> getPonderationRisqueTheoriques() {
		return ponderationRisqueTheoriques;
	}

	/**
	 * Sets the ponderation risque theoriques.
	 *
	 * @param ponderationRisqueTheoriques the new ponderation risque theoriques
	 */
	public void setPonderationRisqueTheoriques(List<PonderationRisqueTheorique> ponderationRisqueTheoriques) {
		this.ponderationRisqueTheoriques = ponderationRisqueTheoriques;
	}

	/**
	 * Gets the modification ponderations.
	 *
	 * @return the modification ponderations
	 */
	public List<ModificationPonderation> getModificationPonderations() {
		return modificationPonderations;
	}

	/**
	 * Sets the modification ponderations.
	 *
	 * @param modificationPonderations the new modification ponderations
	 */
	public void setModificationPonderations(List<ModificationPonderation> modificationPonderations) {
		this.modificationPonderations = modificationPonderations;
	}

	/**
	 * Gets the ponderation zones.
	 *
	 * @return the ponderation zones
	 */
	public List<PonderationZone> getPonderationZones() {
		return ponderationZones;
	}

	/**
	 * Sets the ponderation zones.
	 *
	 * @param ponderationZones the new ponderation zones
	 */
	public void setPonderationZones(List<PonderationZone> ponderationZones) {
		this.ponderationZones = ponderationZones;
	}

	/**
	 * Gets the ponderation volumes.
	 *
	 * @return the ponderation volumes
	 */
	public List<PonderationVolume> getPonderationVolumes() {
		return ponderationVolumes;
	}

	/**
	 * Sets the ponderation volumes.
	 *
	 * @param ponderationVolumes the new ponderation volumes
	 */
	public void setPonderationVolumes(List<PonderationVolume> ponderationVolumes) {
		this.ponderationVolumes = ponderationVolumes;
	}

	/**
	 * Gets the ponderation diffusions.
	 *
	 * @return the ponderation diffusions
	 */
	public List<PonderationDiffusion> getPonderationDiffusions() {
		return ponderationDiffusions;
	}

	/**
	 * Sets the ponderation diffusions.
	 *
	 * @param ponderationDiffusions the new ponderation diffusions
	 */
	public void setPonderationDiffusions(List<PonderationDiffusion> ponderationDiffusions) {
		this.ponderationDiffusions = ponderationDiffusions;
	}

	/**
	 * Gets the campagne.
	 *
	 * @return the campagne
	 */
	public VCampagne getCampagne() {
		return campagne;
	}

	/**
	 * Sets the campagne.
	 *
	 * @param campagne            the campagne to set
	 */
	public void setCampagne(VCampagne campagne) {
		this.campagne = campagne;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getFormId());
		return toStringBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	@Override
	public Serializable getIdentifier() {
		return formId;
	}

	/**
	 * Derniere modification formattée.
	 *
	 * @return derniere modification formatté
	 */
	public String getFormattedModification() {

		return formatter.formatSuiviModification(getFormAuteurModifLb(), getFormDerniereModifTs());

	}

	/**
	 * Données de création formattées.
	 *
	 * @return Données de création formattés
	 */
	public String getFormattedCreation() {
		return formatter.formatSuiviModification(getFromAuteurCreationLb(), getFormCreationDt());
	}

	/**
	 * Derniere modification formattée.
	 *
	 * @return derniere modification formatté
	 */
	public String getPonderationZoneFormattedModification() {

		return getPonderationFormattedModification(TypePonderation.TYPE_POND_ZONE);

	}

	/**
	 * Données de création formattées.
	 *
	 * @return Données de création formattés
	 */
	public String getPonderationZoneFormattedCreation() {
		return getPonderationFormattedCreation(TypePonderation.TYPE_POND_ZONE);
	}

	/**
	 * Derniere modification formattée.
	 *
	 * @return derniere modification formatté
	 */
	public String getPonderationVolumeFormattedModification() {

		return getPonderationFormattedModification(TypePonderation.TYPE_POND_VOLUME);

	}

	/**
	 * Données de création formattées.
	 *
	 * @return Données de création formattés
	 */
	public String getPonderationVolumeFormattedCreation() {
		return getPonderationFormattedCreation(TypePonderation.TYPE_POND_VOLUME);
	}

	/**
	 * Derniere modification formattée.
	 *
	 * @return derniere modification formatté
	 */
	public String getPonderationDiffusionFormattedModification() {

		return getPonderationFormattedModification(TypePonderation.TYPE_POND_DIFFUSION);

	}

	/**
	 * Données de création formattées.
	 *
	 * @return Données de création formattés
	 */
	public String getPonderationDiffusionFormattedCreation() {
		return getPonderationFormattedCreation(TypePonderation.TYPE_POND_DIFFUSION);
	}

	/**
	 * Derniere modification formattée.
	 *
	 * @return derniere modification formatté
	 */
	public String getPonderationDestinationFormattedModification() {

		return getPonderationFormattedModification(TypePonderation.TYPE_POND_DESTINATION);

	}

	/**
	 * Données de création formattées.
	 *
	 * @return Données de création formattés
	 */
	public String getPonderationDestinationFormattedCreation() {
		return getPonderationFormattedCreation(TypePonderation.TYPE_POND_DESTINATION);
	}
        
	/**
	 * Derniere modification formattée.
	 *
	 * @return derniere modification formatté
	 */
	public String getPonderationRisqueTheoriqueFormattedModification() {

		return getPonderationFormattedModification(TypePonderation.TYPE_POND_RISQUE_THEO);

	}
        
	/**
	 * Données de création formattées.
	 *
	 * @return Données de création formattés
	 */
	public String getPonderationRisqueTheoriqueFormattedCreation() {
		return getPonderationFormattedCreation(TypePonderation.TYPE_POND_RISQUE_THEO);
	}

	/**
	 * Données de modification formattés pour la pondération.
	 *
	 * @param typePonderation            type de la ponderation recherchée
	 * @return Données de modification formattés pour la pondération
	 */
	public String getPonderationFormattedModification(String typePonderation) {
		List<ModificationPonderation> modifications = getModificationPonderationsByType(typePonderation);
		String result = "";
		if (!modifications.isEmpty()) {
			ModificationPonderation modification = modifications.iterator().next();
			result = formatter.formatSuiviModification(modification.getModpodUtiModLb(), modification.getModpodModTs());
		}
		return result;
	}

	/**
	 * Données de création formattés pour la pondération.
	 *
	 * @param typePonderation            type de la ponderation recherché
	 * @return Données de création formattés pour la pondération
	 */
	public String getPonderationFormattedCreation(String typePonderation) {
		List<ModificationPonderation> modifications = getModificationPonderationsByType(typePonderation);
		String result = "";
		if (!modifications.isEmpty()) {
			ModificationPonderation modification = modifications.iterator().next();
			result = formatter.formatSuiviModification(modification.getModpodUtiCreaLb(),
					modification.getModpodCreaTs());
		}
		return result;
	}

	/**
	 * Retourne les modification d'un type de ponderation.
	 *
	 * @param type            de la ponderation
	 * @return liste des modifications suivant le type de ponderation
	 */
	public List<ModificationPonderation> getModificationPonderationsByType(String type) {

		List<ModificationPonderation> list = new ArrayList<ModificationPonderation>();

		if (StringUtils.isNotBlank(type) && modificationPonderations != null && !modificationPonderations.isEmpty()) {
			for (ModificationPonderation modification : modificationPonderations) {
				if (StringUtils.equals(type, modification.getModpodTypepondLb())) {
					list.add(modification);
				}
			}
		}

		return list;
	}

	/**
	 * Gets the from crit risquetheorique on lb.
	 *
	 * @return the from crit risquetheorique on lb
	 */
	public String getfromCritRisquetheoriqueOnLb() {
		return (this.fromCritRisquetheoriqueOn) ? OUI : NON;
	}

	/**
	 * Gets the from crit zone on lb.
	 *
	 * @return the from crit zone on lb
	 */
	public String getfromCritZoneOnLb() {
		return (this.fromCritZoneOn) ? OUI : NON;
	}

	/**
	 * Gets the from crit volume on lb.
	 *
	 * @return the from crit volume on lb
	 */
	public String getfromCritVolumeOnLb() {
		return (this.fromCritVolumeOn) ? OUI : NON;
	}

	/**
	 * Gets the from crit diffusion on lb.
	 *
	 * @return the from crit diffusion on lb
	 */
	public String getfromCritDiffusionOnLb() {
		return (this.fromCritDiffusionOn) ? OUI : NON;
	}

	/**
	 * Gets the from crit destination on lb.
	 *
	 * @return the from crit destination on lb
	 */
	public String getfromCritDestinationOnLb() {
		return (this.fromCritDestinationOn) ? OUI : NON;
	}

	/**
	 * Gets the from crit note inspection on lb.
	 *
	 * @return the from crit note inspection on lb
	 */
	public String getfromCritNoteInspectionOnLb() {
		return (this.fromCritNoteInspectionOn) ? OUI : NON;
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.BaseEntity#buildRules()
	 */
	@Override
	public void buildRules() {
		super.buildRules();
		addRule(new FormuleRisqueRule());
	}
        
        /**
         * Permet d'utiliser ValidatorUtils.java
         *
         * @return the formatter
         */
        public String getFormatter() {
            return "NULL";
        }

}
