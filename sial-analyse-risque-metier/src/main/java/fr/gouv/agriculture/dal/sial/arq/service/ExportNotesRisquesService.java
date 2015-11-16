package fr.gouv.agriculture.dal.sial.arq.service;

import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.business.BusinessService;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;

/**
 * Service pour gérer si la liste des notes de risque à exporter contient ou
 * non des données
 *
 * @author fjperez
 */
public interface ExportNotesRisquesService extends BusinessService {
    
    /**
     * Retourne true si la liste à exporter est vide, false sinon
     *
     * @param businessSearchContext
     * @param controller
     * @return
     */
    public boolean validateExport(BusinessSearchContext businessSearchContext, Controller controller);
}
