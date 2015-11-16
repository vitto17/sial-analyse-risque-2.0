package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.CalculEtResultatsDAO;
import fr.gouv.agriculture.dal.sial.arq.service.AbstractExportService;
import fr.gouv.agriculture.dal.sial.arq.service.ExportNotesRisquesService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * Impl. du service d'export des notes de risque
 *
 * @author fjperez
 */
public class ExportNotesRisquesServiceImpl extends AbstractExportService implements ExportNotesRisquesService {
    
    @Inject
    private CalculEtResultatsDAO calculEtResultatsDAO;
    
    /**
     * @see
     * fr.gouv.agriculture.dal.sial.methodes.services.AbstractExportService#getBusinessDAO()
     */
    @Override
    public BusinessDAO getBusinessDAO() {
        return calculEtResultatsDAO;
    }
}
