package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationZoneService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;

/**
 * Classe pour les exports des ponderations Zone.
 * 
 * @author fjperez
 */
public class ExportPonderationZoneAction extends AbstractExportPonderationAction{
    
    @Inject
            ExportPonderationZoneService exportPonderationZoneService;
    
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
        return exportPonderationZoneService.validateExport(businessSearchContext, controller);
    }
    
    @Override
    public Class getBussinesClass() {
        return PonderationZone.class;
    }
}
