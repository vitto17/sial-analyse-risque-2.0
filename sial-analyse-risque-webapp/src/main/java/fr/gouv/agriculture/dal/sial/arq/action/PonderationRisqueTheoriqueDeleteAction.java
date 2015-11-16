/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.BusinessAction;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.event.ControllerEvent;
import fr.gouv.agriculture.orion.event.Events;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.InternalMessage;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.Messenger;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.navigation.confirmation.JavascriptConfirmationHandler;
import fr.gouv.agriculture.orion.rule.RuleException;
import java.util.Collection;

/**
 * Action reprise de DeleteAction mais permet en plus de rajouter un synchronise
 * pour persister la supression directement. J'ai préféré reprendre le code de
 * DeleteAction au lieu de herité directement cette classe, cela permet de mieux
 * gerer les conflits dans le cas ou une formule risque n'est pas au norme lors
 * du synchronise (voir try/catch).
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueDeleteAction extends BusinessAction {

    /**
     * Injection du DAO utilisé pour persister la supression.
     */
    @Inject(value = "fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO")
    private FormuleRisqueDAO formuleRisqueDAO;

    /**
     * Constructeur
     */
    public PonderationRisqueTheoriqueDeleteAction() {
        super();
        setOutcome(null);
        //setConfirmationHandler(new JavascriptConfirmationHandler(fr.gouv.agriculture.orion.i18n.Messages.getMessage("action.business.DeleteAction.suppressConfiramtion"))); //$NON-NLS-1$
        setConfirmationHandler(new JavascriptConfirmationHandler(Messages.getMessage("PonderationRisqueTheorique.supr.confirmation.title")));
        setLabel(Messages.getMessage("action.business.DeleteAction.label")); //$NON-NLS-1$
        setTooltip(Messages.getMessage("action.business.DeleteAction.tooltip")); //$NON-NLS-1$
        setIcon(Messages.getMessage("action.business.DeleteAction.icon")); //$NON-NLS-1$
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object execute() throws Exception {
        Collection selection = getController().getSelection();
        if (selection.isEmpty()) {
            RuleReport ruleReport = new RuleReport();
            RuleMessage ruleMessage = new RuleMessage(Messages
                    .getMessage("message.Messages.emptySelectionError"));
            ruleReport.addMessage(ruleMessage);
            throw new RuleException(ruleReport);
        }

        try {
            ControllerEvent controllerEvent = new ControllerEvent(getController(), Events.DO_DELETE_OBJECT, this);
            getController().processControllerEvent(controllerEvent);
        } finally {
            doRefresh();
        }

        FormController fCtrl = (FormController) getController().getContainer();
        FormuleRisque formuleRisque = (FormuleRisque) fCtrl.getFormModel().getObject();

        try {
            formuleRisqueDAO.synchronize(fCtrl.getBusinessContext(), formuleRisque);
            Messenger messenger = getController().getApplicationSession().getMessenger();
            messenger.addMessage(new InternalMessage(Messages.getMessage("message.Messages.deleteActionOk"), MessageSeverity.INFO));; //$NON-NLS-1$
        } catch (Exception excep) {
            Messenger messenger = getController().getApplicationSession().getMessenger();
            messenger.addMessage(new InternalMessage(Messages.getMessage("form.formuleRisque.supression.err"), MessageSeverity.ERROR));; //$NON-NLS-1$
        }

        getController().getView().setModified(false);
        fCtrl.getView().setModified(false);
        
        return super.execute();
    }
}
