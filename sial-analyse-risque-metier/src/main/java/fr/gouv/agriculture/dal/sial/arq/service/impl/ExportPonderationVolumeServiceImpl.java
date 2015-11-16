package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.PonderationVolumeDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationVolumeService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des ponderations Volume (V)
 *
 * @author fjperez
 */
public class ExportPonderationVolumeServiceImpl extends AbstractExportService implements ExportPonderationVolumeService {
    
    @Inject
    private PonderationVolumeDAO ponderationVolumeDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return ponderationVolumeDAO;
    }
}
