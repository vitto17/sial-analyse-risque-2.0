package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.Collection;

import fr.gouv.agriculture.dal.sial.arq.action.rule.ARQ003SupprimerFormulesRule;
import fr.gouv.agriculture.orion.action.business.DeleteAction;
import fr.gouv.agriculture.orion.business.rule.AbstractBusinessRule;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.navigation.confirmation.JavascriptConfirmationHandler;
import fr.gouv.agriculture.orion.rule.RuleException;

/**
 * On surcharge l'action "DeleteAction" afin d'ajouter des Rule avant son
 * éxecution.
 * 
 * @author jodurand
 * 
 */
public class FormuleRisqueListDeleteAction extends DeleteAction {

	private static final long serialVersionUID = 1L;

	/**
	 * On change le message d'avertissement qui est affiché quand on clique sur
	 * le bouton.
	 */
	public FormuleRisqueListDeleteAction() {
		super();
		setConfirmationHandler(new JavascriptConfirmationHandler(
				Messages.getMessage("list.formuleRisque.button.suppr.warning")));
	}

	/**
	 * On modifie la méthode "execute" de DeleteLineAction afin de rajouter la
	 * vérification de l'échéance de la campagne des formules de risque
	 * sélectionnées pour la suppression.
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object execute() throws Exception {
		AbstractBusinessRule rule = new ARQ003SupprimerFormulesRule();

		Collection selection = getController().getSelection();
		RuleReport ruleReport = rule.validate(selection);

		if (!ruleReport.isEmpty()) {
			throw new RuleException(ruleReport);
		}

		return super.execute();
	}

}
