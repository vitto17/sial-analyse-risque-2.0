/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.slavelist;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProcede;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VProcede;
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
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueProcedesSLC extends SlaveListController {
    
	/** The default business dao. */
	@Inject
	private BusinessDAO businessDao;
        
	@SuppressWarnings("unchecked")
	@Override
	protected void assign(AssignmentDatas assignmentDatas) throws Exception {
		
		final FormController formController = (FormController) getContainer();
		Collection<VProcede> selection = assignmentDatas.getSelection();

		if (!CommonHelper.isEmpty(selection)) {
			
			PonderationRisqueTheorique ponderationRisqueTheorique  = (PonderationRisqueTheorique) getCurrentParentObject();
			BusinessContext businessContext = createBusinessContext(BusinessContext.class);
                        if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes() == null) {
                            ponderationRisqueTheorique.setPonderationRisqueTheoriqueProcedes(new ArrayList<PonderationRisqueTheoriqueProcede>());
                        }
                        for (VProcede procede : selection) {

                            // Règles_Trans_006_ComportementRetourGuideMultiSelection
                            if (!ponderationRisqueTheorique.isPonderationRisqueTheoriqueProcedesContainsProcede(procede)) {
                                PonderationRisqueTheoriqueProcede ponderation = (PonderationRisqueTheoriqueProcede) businessDao
                                                .create(businessContext, PonderationRisqueTheoriqueProcede.class);
                                ponderation.setProcede(procede);
                                ponderation.setPonderationRisqueTheorique(ponderationRisqueTheorique);

                                ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes().add(ponderation);
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
