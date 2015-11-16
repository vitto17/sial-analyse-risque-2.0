package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.controller.list.FormuleRisqueListController;
import fr.gouv.agriculture.dal.sial.arq.service.ExportNotesRisquesService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.report.ReportException;
import fr.gouv.agriculture.orion.report.processor.ReportProcessor;
import fr.gouv.agriculture.orion.rule.RuleException;

/**
 * Classe pour les export des Formules de Risques.
 *
 * @author fjperez
 */
public class ExportFormulesRisqueAction extends AbstractExportAction{
    
	/** Service de recherche des Notes de risque. 
	 * */
    @Inject
    private ExportNotesRisquesService exportNotesRisquesService;
    
    /**
     * Liste des formules risques
     *
     */
    @Inject(value = "formuleRisqueLC")
    FormuleRisqueListController formuleRisqueLC;

    /**
     * Surcharge pour afficher un message d'erreur si la recherche n'a pas été
     * lancée.
     *
     * @return
     * @throws ReportException
     */
    @Override
    protected ReportProcessor createReportProcessor() throws ReportException {
        
        int size = formuleRisqueLC.getListModel().getSize();
        if (size==0) {
            RuleReport ruleReport = new RuleReport();
            RuleMessage ruleMessage = new RuleMessage(Messages.getMessage(
                    "list.formuleRisque.isEmpty"));
            ruleReport.addMessage(ruleMessage);
            throw new RuleException(ruleReport);
        } else {
            return super.createReportProcessor();
        }
    }
    
    

    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.action.AbstractExportAction#listeIsEmpty(BusinessSearchContext,
     * Controller)
     * @param businessSearchContext
     * @param controller
     * @return
     */
    @Override
    public Boolean listeIsEmpty(BusinessSearchContext businessSearchContext, Controller controller) {
        return exportNotesRisquesService.validateExport(businessSearchContext, controller);
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
