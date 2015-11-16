package fr.gouv.agriculture.dal.sial.arq.business.bean;

import java.io.Serializable;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.orion.business.BaseEntity;

/**
 * Ce bean contient les objets nécessaire pour l'écran de duplication de
 * formules de risque.
 * 
 * @author jodurand
 * 
 */
public class DuplicationFormuleRisqueBean extends BaseEntity {

	/** serial */
	private static final long serialVersionUID = 9130485119888776589L;

	/** Campagne */
	private VCampagne campagne;
	/** Liste de formules de risques */
	private List<FormuleRisque> formuleRisqueList;

	/**
	 * Campagne
	 * @return Campagne
	 */
	public VCampagne getCampagne() {
		return campagne;
	}

	/**
	 * Campagne
	 * @param campagne Campagne
	 */
	public void setCampagne(VCampagne campagne) {
		this.campagne = campagne;
	}

	/**
	 * Liste de Formule de Risque
	 * @return Liste de Formule de Risque
	 */
	public List<FormuleRisque> getFormuleRisqueList() {
		return formuleRisqueList;
	}

	/**
	 * Liste de Formule de Risque
	 * @param formuleRisqueList Liste de Formule de Risque
	 */
	public void setFormuleRisqueList(List<FormuleRisque> formuleRisqueList) {
		this.formuleRisqueList = formuleRisqueList;
	}

	/**
	 * Identifiant du bean
	 *  @return Identifiant du bean
	 */
	@Override
	public Serializable getIdentifier() {
		return null;
	}

}
