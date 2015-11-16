/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueApprobation;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationRisqueTheoriqueApprobationDAO;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 * Implémentation d'accès au données pour la table ponderation_risque_theorique_approbation.
 * 
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueApprobationDAOImpl extends DefaultBusinessDAO implements PonderationRisqueTheoriqueApprobationDAO {

    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationRisqueTheoriqueApprobation ponderationRisqueTheoApprobation = (PonderationRisqueTheoriqueApprobation) super.create(businessContext, PonderationRisqueTheoriqueApprobation.class);
        // le super.create à affecté la PonderationRisqueTheorique trouvée dans la BusinessStack
        // au ponderationRisqueTheoApprobation qui vient d'être créé
        PonderationRisqueTheorique ponderationRisqueTheo = ponderationRisqueTheoApprobation.getPonderationRisqueTheorique();
        if (ponderationRisqueTheo != null) {
            ponderationRisqueTheo.getPonderationRisqueTheoriqueApprobations().add(ponderationRisqueTheoApprobation);
        }
        return ponderationRisqueTheoApprobation;
    }
}
