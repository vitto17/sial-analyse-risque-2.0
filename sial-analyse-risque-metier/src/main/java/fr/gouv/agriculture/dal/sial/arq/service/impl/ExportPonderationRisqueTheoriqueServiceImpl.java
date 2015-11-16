package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.PonderationRisqueTheoriqueDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportPonderationRisqueTheoriqueService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des Ponderations Risque Theorique (Ri)
 *
 * @author fjperez
 */
public class ExportPonderationRisqueTheoriqueServiceImpl extends AbstractExportService implements ExportPonderationRisqueTheoriqueService {
    
    @Inject
    private PonderationRisqueTheoriqueDAO ponderationRisqueTheoriqueDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return ponderationRisqueTheoriqueDAO;
    }
}
