/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.orion.action.business.SaveAction;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.navigation.NavigationContext;
import fr.gouv.agriculture.orion.navigation.Transition;
import fr.gouv.agriculture.orion.navigation.TransitionStack;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 * Permet de réaliser une sauvegarde et d'afficher le formulaire, réinitialisé
 * pour la création d'une nouvelle pondération risque théorique.
 * 
 * @author pegaltier
 * @author jodurand
 * @author pbarrau
 */
public class PonderationRisqueTheoriqueSaveAndNewAction extends SaveAction {

	private static final long serialVersionUID = 1L;

	/** L'objet créé par l'action */
	protected Object createdObject;

	@Override
	public Object execute() throws Exception {

		Object outcome;

		if (!isReplaying()) {

			// Initialise un nouveau objet et le set dans le formController
			FormController fCtrl = (FormController) getController();
			FormuleRisque formuleRisque = ((PonderationRisqueTheorique) fCtrl.getFormModel().getObject())
					.getFormuleRisque();
			try {
				BusinessContext businessContext = fCtrl.createBusinessContext(BusinessContext.class);
				Object object = fCtrl.getBusinessDao().create(businessContext, fCtrl.getBusinessClass());
				((PonderationRisqueTheorique) object).setFormuleRisque(formuleRisque);
				createdObject = object;

			} catch (PersistenceException ex) {
				Logger.getLogger(PonderationRisqueTheoriqueSaveAndNewAction.class.getName())
						.log(Level.SEVERE, null, ex);
			}
		}
		outcome = super.execute();

		/**
		 * On enlève la dernière transition = formulaire de l'objet que l'on
		 * vient de créer. Permet par la suite, lors du clic sur le bouton
		 * "Retour", de revenir sur la liste des valeur de la nomenclature et
		 * non sur le formulaire de création de la nomenclature créée
		 */
		NavigationContext navigationContext = getController().getNavigationContext();
		TransitionStack transitionStack = navigationContext.getTransitionStack();
		if (transitionStack != null) {
			int index = transitionStack.size();
			transitionStack.remove(index - 1);
		}

		return outcome;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Transition getTransition() {
		Transition transition = super.getTransition();
		transition.getSelection().clear();
		transition.getSelection().add(createdObject);
		createdObject = null;
		return transition;
	}
}
