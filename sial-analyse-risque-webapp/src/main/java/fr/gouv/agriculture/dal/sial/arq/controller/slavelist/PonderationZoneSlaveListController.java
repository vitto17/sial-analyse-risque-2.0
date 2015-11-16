package fr.gouv.agriculture.dal.sial.arq.controller.slavelist;

import java.util.ArrayList;
import java.util.Collection;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.Generic2ColumnListComparator;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VZone;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.controller.AssignmentDatas;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.RuleException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * Controller de la slavelist : pondération des zones
 * @author sopra
 *
 */
public class PonderationZoneSlaveListController extends SlaveListController {
	
	/** The default business dao. */
	@Inject
	private BusinessDAO businessDao;
	
	/**appel pour une nouvelle ponderation*/
	private boolean newPonderation = true;
	
        @Override
        protected Collection loadEntities(Stack stack) throws Exception {
            final List<PonderationZone> list = (List<PonderationZone>) super.loadEntities(stack);
            Collections.sort(list, new Generic2ColumnListComparator());

            return list;
        }
    
	@SuppressWarnings("unchecked")
	@Override
	protected void assign(AssignmentDatas assignmentDatas) throws Exception {
		
		
		Collection<VZone> selection = assignmentDatas.getSelection();

		if (!CommonHelper.isEmpty(selection)) {
			
			FormuleRisque formuleRisque  = (FormuleRisque) getCurrentParentObject();
			BusinessContext businessContext = createBusinessContext(BusinessContext.class);
			
			// Cas de l'ajout
			if (isNewPonderation()) {
				for (VZone zone : selection) {

					PonderationZone ponderation = (PonderationZone) businessDao
							.create(businessContext, PonderationZone.class);
					ponderation.setZone(zone);
					ponderation.setFormuleRisque(formuleRisque);

					if (formuleRisque.getPonderationZones() == null) {
						formuleRisque
								.setPonderationZones(new ArrayList<PonderationZone>());
					}
					formuleRisque.getPonderationZones().add(ponderation);

				}
			} else { // Cas de la modification
				PonderationZone ponderation = (PonderationZone)assignmentDatas.getTargetObject();
				ponderation.setZone(selection.iterator().next());
			}
			
		} else {
			RuleReport ruleReport = new RuleReport();
			RuleMessage ruleMessage = new RuleMessage(Messages.getMessage("guide.selection.empty.err"));
			ruleReport.addMessage(ruleMessage);
			throw new RuleException(ruleReport);
		}
		
	}


	/**
	 * @return the newPonderation
	 */
	public boolean isNewPonderation() {
		return newPonderation;
	}


	/**
	 * @param newPonderation the newPonderation to set
	 */
	public void setNewPonderation(boolean newPonderation) {
		this.newPonderation = newPonderation;
	}
}
