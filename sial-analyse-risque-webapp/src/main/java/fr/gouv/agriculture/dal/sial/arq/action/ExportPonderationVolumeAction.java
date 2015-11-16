package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationVolumeService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;

/**
 * Classe pour les exports des ponderations Volume
 * 
 * @author fjperez
 */
public class ExportPonderationVolumeAction extends AbstractExportPonderationAction{
    
    @Inject
            ExportPonderationVolumeService exportPonderationVolumeService;
    
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
        return exportPonderationVolumeService.validateExport(businessSearchContext, controller);
    }
    
    @Override
    public Class getBussinesClass() {
        return PonderationVolume.class;
    }
}
