package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.PonderationZoneDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationZoneService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des Ponderations Zone (Z)
 *
 * @author fjperez
 */
public class ExportPonderationZoneServiceImpl extends AbstractExportService implements ExportPonderationZoneService {
    
    @Inject
    private PonderationZoneDAO ponderationZoneDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return ponderationZoneDAO;
    }
}
