package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.PonderationDiffusionDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationDiffusionService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des Ponderations Diffusion (D)
 *
 * @author fjperez
 */
public class ExportPonderationDiffusionServiceImpl extends AbstractExportService implements ExportPonderationDiffusionService {
    
    @Inject
    private PonderationDiffusionDAO ponderationDiffusionDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return ponderationDiffusionDAO;
    }
}
