/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProcede;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationRisqueTheoriqueProcedeDAO;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 * Implémentation d'accès au données pour la table ponderation_risque_theorique_procede.
 * 
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueProcedeDAOImpl extends DefaultBusinessDAO implements PonderationRisqueTheoriqueProcedeDAO {
    
    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationRisqueTheoriqueProcede ponderationRisqueTheoProcede = (PonderationRisqueTheoriqueProcede) super.create(businessContext, PonderationRisqueTheoriqueProcede.class);
        // le super.create à affecté la PonderationRisqueTheorique trouvée dans la BusinessStack
        // au ponderationRisqueTheoProcede qui vient d'être créé
        PonderationRisqueTheorique ponderationRisqueTheo = ponderationRisqueTheoProcede.getPonderationRisqueTheorique();
        if (ponderationRisqueTheo != null) {
            ponderationRisqueTheo.getPonderationRisqueTheoriqueProcedes().add(ponderationRisqueTheoProcede);
        }
        return ponderationRisqueTheoProcede;
    }
}
