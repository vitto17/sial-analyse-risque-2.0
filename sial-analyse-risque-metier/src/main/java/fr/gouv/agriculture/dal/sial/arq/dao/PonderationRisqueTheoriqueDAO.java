package fr.gouv.agriculture.dal.sial.arq.dao;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 *
 * @author SiteGenerator
 * @version $Id: dao.vm 12071 2008-10-29 23:44:22Z sebastien.bouvet $
 */
public interface PonderationRisqueTheoriqueDAO extends BusinessDAO {

    /**
     * Permet de créer une PonderationRisqueTheorique et l'associer a une
     * formule risque passé en parametre. Cette methode est utilisé pour le
     * saveAndNew du formulaire de ponderation risque theorique
     *
     * @param businessContext
     * @param businessClass
     * @param formuleRisque
     * @return
     * @throws PersistenceException
     */
    public Object createWithFormuleRisque(BusinessContext businessContext, Class businessClass, FormuleRisque formuleRisque) throws PersistenceException;
}
