package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationDiffusionService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;

/**
 * Classe pour les exports des Ponderations Diffusion.
 * 
 * @author fjperez
 */
public class ExportPonderationDiffusionAction extends AbstractExportPonderationAction{
    
    @Inject
            ExportPonderationDiffusionService exportPonderationDiffusionService;
    
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
        return exportPonderationDiffusionService.validateExport(businessSearchContext, controller);
    }
    
    @Override
    public Class getBussinesClass() {
        return PonderationDiffusion.class;
    }
    
    
}
