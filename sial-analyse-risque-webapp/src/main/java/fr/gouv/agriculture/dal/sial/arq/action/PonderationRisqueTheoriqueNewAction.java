/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationRisqueTheoriqueDAO;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.BusinessAction;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.navigation.Transition;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet de rajouter un element a la slavelist des ponderations risques
 * theoriques et ensuite rediriger sur le form afin de completer cet éelément.
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueNewAction extends BusinessAction {

    @Inject
    private PonderationRisqueTheoriqueDAO ponderationRisqueTheoriqueDAO;

    @Override
    public Transition getTransition() {

        Transition transition = super.getTransition();
        FormController fCtrl = (FormController) getController().getContainer();
        FormuleRisque formuleRisque = (FormuleRisque) fCtrl.getFormModel().getObject();

        if (!isReplaying() && formuleRisque != null) {
            try {
            	transition.getSelection().clear();
            	
                PonderationRisqueTheorique ponderationRisqueTheorique = (PonderationRisqueTheorique) ponderationRisqueTheoriqueDAO.create(fCtrl.getBusinessContext(), PonderationRisqueTheorique.class);
                //formuleRisque.getPonderationRisqueTheoriques().add(ponderationRisqueTheorique);
                        
                
                transition.getSelection().add(ponderationRisqueTheorique);
            } catch (Exception ex) {
                Logger.getLogger(PonderationRisqueTheoriqueNewAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return transition;
    }
}
