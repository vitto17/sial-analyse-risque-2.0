package fr.gouv.agriculture.dal.sial.arq.action;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ;
import fr.gouv.agriculture.orion.action.BusinessAction;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;

/**
 * Action de duplication des Ponderations par defaut
 * @author pbarreau
 *
 */
public abstract class AbstractPonderationDuplicateAction<T> extends BusinessAction {

	
	/**serial */
	private static final long serialVersionUID = 36054143662406608L;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object execute() throws Exception {
		
		SlaveListController controller = (SlaveListController)getBaseController();
		FormController formController = (FormController) controller.getContainer();
		 
		Collection<PonderationARQ<T>> selection = controller.getSelection();
		PonderationARQ<T> ponderationInit = selection.iterator().next();
		int index = controller.getListModel().findObject(ponderationInit);
		
		FormuleRisque formuleRisque  = ponderationInit.getFormuleRisque();
		
		T ponderation = ponderationInit.duplicate();
		int size = getPonderations(formuleRisque).size();
		getPonderations(formuleRisque).add(index + 1,ponderation);
		
                controller.getView().setModified(true);
		formController.getView().setModified(true);
                
		return super.execute();
	}
	
	/**
	 * Retourne la liste des ponderations concernée par la duplication.
	 * @param formuleRisque parent
	 * @return la liste des ponderations concernée par la duplication.
	 */
	public abstract List<T> getPonderations(FormuleRisque formuleRisque);
	
}
