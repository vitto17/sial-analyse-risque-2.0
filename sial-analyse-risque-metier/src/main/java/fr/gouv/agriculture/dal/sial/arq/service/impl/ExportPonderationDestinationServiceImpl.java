package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.PonderationDestinationDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationDestinationService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des ponderations Destination (S)
 *
 * @author fjperez
 */
public class ExportPonderationDestinationServiceImpl extends AbstractExportService implements ExportPonderationDestinationService {
    
    @Inject
    private PonderationDestinationDAO ponderationDestinationDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return ponderationDestinationDAO;
    }
}
