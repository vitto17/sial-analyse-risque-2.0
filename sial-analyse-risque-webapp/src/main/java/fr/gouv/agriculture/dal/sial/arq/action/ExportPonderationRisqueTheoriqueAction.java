/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationRisqueTheoriqueService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;

/**
 *
 * @author pegaltier
 */
public class ExportPonderationRisqueTheoriqueAction extends AbstractExportPonderationAction{
    
    @Inject
            ExportPonderationRisqueTheoriqueService exportPonderationRisqueTheoriqueService;
    
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
        return exportPonderationRisqueTheoriqueService.validateExport(businessSearchContext, controller);
    }
    
    @Override
    public Class getBussinesClass() {
        return PonderationRisqueTheorique.class;
    }
}
