package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.service.ExportNotesRisquesService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.ApplicationSession;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.InternalMessage;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.Messenger;
import fr.gouv.agriculture.orion.report.ReportException;
import fr.gouv.agriculture.orion.report.processor.ReportProcessor;

/**
 * Classe pour les export des Notes de Risques.
 * 
 * @author fjperez
 */
public class ExportNotesRisquesAction extends AbstractExportAction {

	@Inject
	private ExportNotesRisquesService exportNotesRisquesService;

	@Inject(value = "calculEtResultatsLC")
	private ListController calculEtResultatsLC;



	/**
	 * @see fr.gouv.agriculture.dal.sial.methodes.action.AbstractExportAction#listeIsEmpty(BusinessSearchContext,
	 *      Controller)
	 * @param businessSearchContext
	 * @param controller
	 * @return
	 */
	@Override
	public Boolean listeIsEmpty(BusinessSearchContext businessSearchContext,
			Controller controller) {
        boolean isEmpty = calculEtResultatsLC.getModel().isEmpty();
		// Si la liste est vide
        if (isEmpty) {
            // Ajout du message d'erreur
            final ApplicationSession applicationSession = controller.getApplicationSession();
            final Messenger messenger = applicationSession.getMessenger();
            messenger.addMessage(new InternalMessage(Messages.getMessage("commons.export.isEmpty"),
                    MessageSeverity.ERROR));
            isEmpty = true;
        }
        return isEmpty;
		
	}
	
	

	@Override
	public Controller getExportController() {
		return getController();
	}

	@Override
	public BusinessSearchContext getExportBusinessSearchContext() {
		return getBusinessSearchContext();
	}
}
