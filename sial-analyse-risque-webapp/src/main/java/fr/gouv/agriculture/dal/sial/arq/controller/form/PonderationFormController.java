package fr.gouv.agriculture.dal.sial.arq.controller.form;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.ModificationPonderation;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.Generic1ColumnListComparator;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.Generic2ColumnListComparator;
import fr.gouv.agriculture.dal.sial.arq.constante.TypePonderation;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.business.BaseEntity;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.Rule;
import fr.gouv.agriculture.orion.rule.RuleException;
import java.util.Collections;

/**
 * Controlleur pour les ponderations
 * @author pbarreau
 *
 */
public class PonderationFormController extends FormController {

	/** nom du getter pour les ponderations*/
	private String getterName;
	/** Nom de la rule à appliquer */
	private String ruleName;
	/** Type de ponderation recherché */
	private String typePonderation;
	
	/** Service de gestion de l'utilisateur*/
	@Inject
	private HabilitationsService habilitationsService;
	
	/** Surcharge de la methode save*/
	@Override
	protected void save() throws Exception {
		
		Class ruleClass = Class.forName(ruleName);
		Rule rule = (Rule) ruleClass.newInstance();

		List<? extends BaseEntity> ponderations = getPonderations();
		
		RuleReport report = new RuleReport();
		
		
		if(ponderations !=null && !ponderations.isEmpty()){
			for(BaseEntity entite:ponderations){
				report.addReport(rule.validate(entite));
			}
		}

		if (!report.isEmpty()) {
			throw new RuleException(report);
		}
		
		
		if(getFormView().isModified()){
			customSave();
                        orderPonderation();
		}
                super.save();
	}
	
    /**
     * Recuperation de l'utilisateur courant.
     *
     * @return libellé de l'utilisateur courant
     */
    public String getCurrentUserLb() {
        return habilitationsService.getUtilisateurConnecte().getLogin();
    }
	
	/**
	 * Gestion du suivi de modification
	 */
	protected void customSave() {

		//String typePonderation = ModificationPonderation.TYPE_POND_ZONE;
		
		FormuleRisque formuleRisque = (FormuleRisque) getFormModel()
				.getObject();
		
		List<ModificationPonderation> modificationsToute = formuleRisque.getModificationPonderations();
		if(modificationsToute == null){
			formuleRisque.setModificationPonderations(new ArrayList<ModificationPonderation>());
		}
		
		List<ModificationPonderation> modifications = formuleRisque
				.getModificationPonderationsByType(typePonderation);
		
		ModificationPonderation currentMod;
		
		//Cas de la creation
		if(modifications.isEmpty()){
			modifications = new ArrayList<ModificationPonderation>();
			
			currentMod = new ModificationPonderation();
			currentMod.setFormuleRisque(formuleRisque);
			currentMod.setModpodCreaTs(new Date());
			currentMod.setModpodTypepondLb(typePonderation);
			currentMod.setModpodUtiCreaLb(getCurrentUserLb());
			formuleRisque.getModificationPonderations().add(currentMod);
		} else {
			
			currentMod = modifications.iterator().next();
			//Cas de re-création
			if(StringUtils.isBlank(currentMod.getModpodUtiCreaLb())){
				currentMod.setModpodCreaTs(new Date());
				currentMod.setModpodUtiCreaLb(getCurrentUserLb());
				
			} else { //Cas de modification
				currentMod.setModpodModTs(new Date());
				currentMod.setModpodUtiModLb(getCurrentUserLb());
			}
		}

	}
	
	
	/**
	 * Recuperation des ponderation
	 * @return liste des ponderations
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<? extends BaseEntity> getPonderations() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		List<? extends BaseEntity> result = null;
		FormuleRisque formuleRisque = (FormuleRisque) getFormModel()
				.getObject();
		
		String nomSetterMajuscule = "GET" + getGetterName().toUpperCase();

        Method[] methods = formuleRisque.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(nomSetterMajuscule)) {
            	result = (List<? extends BaseEntity>)method.invoke(formuleRisque);
                break;
            }
        }
        
        return result;
	}

	/**
	 * @return the typePonderation
	 */
	public String getTypePonderation() {
		return typePonderation;
	}


	/**
	 * @param typePonderation the typePonderation to set
	 */
	public void setTypePonderation(String typePonderation) {
		this.typePonderation = typePonderation;
	}

	/**
	 * @return the getterName
	 */
	public String getGetterName() {
		return getterName;
	}


	/**
	 * @param getterName the getterName to set
	 */
	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}


	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}


	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

        private void orderPonderation() {

            FormuleRisque formuleRisque = (FormuleRisque) getFormModel()
                                            .getObject();

            if (typePonderation.equals(TypePonderation.TYPE_POND_DESTINATION)) {
                Collections.sort(formuleRisque.getPonderationDestinations(), new Generic2ColumnListComparator());
            }
            if (typePonderation.equals(TypePonderation.TYPE_POND_DIFFUSION)) {
                Collections.sort(formuleRisque.getPonderationDiffusions(), new Generic1ColumnListComparator());
            }
            if (typePonderation.equals(TypePonderation.TYPE_POND_VOLUME)) {
                Collections.sort(formuleRisque.getPonderationVolumes(), new Generic2ColumnListComparator());
            }
            if (typePonderation.equals(TypePonderation.TYPE_POND_ZONE)) {
                Collections.sort(formuleRisque.getPonderationZones(), new Generic2ColumnListComparator());
            }
        }
        
}
