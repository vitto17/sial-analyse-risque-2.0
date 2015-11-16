package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportNotesRisquesService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des notes de risque
 *
 * @author fjperez
 */
public class ExportFormulesRisqueServiceImpl extends AbstractExportService implements ExportNotesRisquesService {
    
    @Inject
    private FormuleRisqueDAO formuleRisqueDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return formuleRisqueDAO;
    }
}
