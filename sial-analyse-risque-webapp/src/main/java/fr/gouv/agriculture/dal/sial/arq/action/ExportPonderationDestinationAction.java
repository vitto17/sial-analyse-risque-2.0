package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationDestinationService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;

/**
 * Classe pour les exports des ponderations Destination
 * @author fjperez
 */
public class ExportPonderationDestinationAction extends AbstractExportPonderationAction{
    
    @Inject
            ExportPonderationDestinationService exportPonderationDestinationService;
    
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
        return exportPonderationDestinationService.validateExport(businessSearchContext, controller);
    }
    
    @Override
    public Class getBussinesClass() {
        return PonderationDestination.class;
    }
}
