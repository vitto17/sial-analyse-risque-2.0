/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.orion.action.BusinessAction;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import fr.gouv.agriculture.orion.navigation.Transition;
import java.util.Collection;

/**
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueDuplicateAction extends BusinessAction {

    PonderationRisqueTheorique ponderation;
    
    /**
     * serial
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Object execute() throws Exception {

        SlaveListController controller = (SlaveListController) getBaseController();
        
        Collection<PonderationRisqueTheorique> selection = controller.getSelection();
        PonderationRisqueTheorique ponderationInit = selection.iterator().next();
        ponderation = ponderationInit.duplicate();

        //formuleRisque.getPonderationRisqueTheoriques().add(index, ponderation);


                
        return super.execute();
    }

    @Override
    public Transition getTransition() {

        Transition transition = super.getTransition();
        FormController formController = (FormController) getController().getContainer();
        FormuleRisque formuleRisque = (FormuleRisque) formController.getFormModel().getObject();

        if (!isReplaying() && formuleRisque != null) {
            transition.getSelection().clear();
            transition.getSelection().add(ponderation);
            
//            formController.getView().setModified(true);
        }

        return transition;
    }
}
