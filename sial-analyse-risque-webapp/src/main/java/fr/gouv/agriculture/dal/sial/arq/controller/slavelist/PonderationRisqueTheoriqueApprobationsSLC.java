/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.slavelist;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueApprobation;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.controller.AssignmentDatas;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.RuleException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *Slave List Controlleur pour les ponderation de risque Theorique
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueApprobationsSLC extends SlaveListController {
    
	/** The default business dao. */
	@Inject
	private BusinessDAO businessDao;
        
	@SuppressWarnings("unchecked")
	@Override
	protected void assign(AssignmentDatas assignmentDatas) throws Exception {
		
		final FormController formController = (FormController) getContainer();
		Collection<VRefApprobation> selection = assignmentDatas.getSelection();

		if (!CommonHelper.isEmpty(selection)) {
			
			PonderationRisqueTheorique ponderationRisqueTheorique  = (PonderationRisqueTheorique) getCurrentParentObject();
			BusinessContext businessContext = createBusinessContext(BusinessContext.class);
                        if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations()== null) {
                                ponderationRisqueTheorique.setPonderationRisqueTheoriqueApprobations(new ArrayList<PonderationRisqueTheoriqueApprobation>());
                        }
                        for (VRefApprobation approbation : selection) {

                            // Règles_Trans_006_ComportementRetourGuideMultiSelection
                            if (!ponderationRisqueTheorique.isPonderationRisqueTheoriqueApprobationsContainsApprobation(approbation)) {
                                PonderationRisqueTheoriqueApprobation ponderation = (PonderationRisqueTheoriqueApprobation) businessDao
                                                .create(businessContext, PonderationRisqueTheoriqueApprobation.class);
                                ponderation.setApprobation(approbation);
                                ponderation.setPonderationRisqueTheorique(ponderationRisqueTheorique);

                                ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations().add(ponderation);
                            }
                        }
                        getView().setModified(true); 
                        formController.getView().setModified(true);
                
                } else {
			RuleReport ruleReport = new RuleReport();
			RuleMessage ruleMessage = new RuleMessage(Messages.getMessage("guide.selection.empty.err"));
			ruleReport.addMessage(ruleMessage);
			throw new RuleException(ruleReport);
		}
        }
        
}
