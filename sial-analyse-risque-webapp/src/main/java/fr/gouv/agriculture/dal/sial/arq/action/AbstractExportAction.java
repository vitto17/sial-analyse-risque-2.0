package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.action.report.ProcessReportFromButtonAction;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.report.Engine;
import fr.gouv.agriculture.orion.report.Report;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.ReportDataSourceProvider;
import fr.gouv.agriculture.orion.report.ReportException;
import fr.gouv.agriculture.orion.report.processor.ReportProcessor;
import fr.gouv.agriculture.orion.report.processor.ReportProcessorResponseHandler;

/**
 * Action pour l'export de données. Permet d'afficher le bon message quand la
 * liste à exporter est vide : "La liste à exporter ne contient aucun élément"
 * et non le message standard d'orion "Erreur technique dans la génération du
 * rapport"
 *
 * @author bdeat
 */
public abstract class AbstractExportAction extends ProcessReportFromButtonAction {
    
    /**
     * Retourne un rapport vide ReportProcessor
     */
    public static final ReportProcessor PROCESSOR_EMPTY = new ReportProcessor() {
        @Override
        public void process() throws ReportException {
            // Na fait rien
        }
        @Override
        public void addResponseHandler(ReportProcessorResponseHandler handler) {
            // Ne fait rien
        }
        @Override
        public Engine getEngine() {
            return null;
        }
        @Override
        public Report getReport() {
            return null;
        }
    };
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ReportProcessor createReportProcessor() throws ReportException {
        
        // Si la liste de données est vide
        if (listeIsEmpty(getExportBusinessSearchContext(), getExportController())) {
            // On retourne un rapport vide, pour pouvoir afficher le message d'erreur souhaité
            return AbstractExportAction.PROCESSOR_EMPTY;
        } else {
            return super.createReportProcessor();
        }
        
    }
    
    @Override
    protected ReportDataSource createDataSource(String providerClassName,
    String reportName) throws ReportException {
        try {
            ReportDataSourceProvider dataSourceProvider = ((ReportDataSourceProvider) Class
                    .forName(providerClassName).newInstance());
            BusinessSearchContext context = getExportBusinessSearchContext();
            ReportDataSource dataSource = dataSourceProvider
                    .getReportDataSource(reportName, context);
            dataSource.initialize();
            return dataSource;
        } catch (Exception e) {
            throw new ReportException(
                    Messages.getMessage("action.report.AbstractProcessReportAction.sqlerror"), e); //$NON-NLS-1$
        }
    }
    
    /**
     * Indique si la liste à exporter est vide ou non
     *
     * @param businessSearchContext context contenant les critères de recherche
     * choisit par l'utilisateur
     * @param controller
     * @return
     */
    public abstract Boolean listeIsEmpty(BusinessSearchContext businessSearchContext, Controller controller);
    
    /**
     * Obtenir le bon controller pour l'export
     * @return
     */
    public abstract Controller getExportController();
    
    /**
     * Obtenir le bon BussinessSearchContext pour l'export
     * @return
     */
    public abstract BusinessSearchContext getExportBusinessSearchContext();
}
