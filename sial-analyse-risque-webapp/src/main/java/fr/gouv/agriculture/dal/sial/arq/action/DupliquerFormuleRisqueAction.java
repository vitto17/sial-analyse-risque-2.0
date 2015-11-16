package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.Collection;

import fr.gouv.agriculture.orion.action.NavigationAction;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.RuleException;

/**
 * Cette action vérifie juste que des éléments ont été sélectionnés dans la
 * liste parente avant de les envoyer vers la page de duplication d'une formule
 * de risque.
 * 
 * @author jodurand
 * 
 */
public class DupliquerFormuleRisqueAction extends NavigationAction {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public Object execute() throws Exception {
		Collection selection = getController().getSelection();
		if (selection.isEmpty()) {
			RuleReport ruleReport = new RuleReport();
			RuleMessage ruleMessage = new RuleMessage(Messages.getMessage("message.Messages.emptySelectionError"));
			ruleReport.addMessage(ruleMessage);
			throw new RuleException(ruleReport);
		}

		return super.execute();
	}
}
