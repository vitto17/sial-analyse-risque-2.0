/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.arq.business.Produit;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationRisqueTheoriqueProduitDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeProduit;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 * Implémentation d'accès au données pour la table
 * ponderation_risque_theorique_produit.
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueProduitDAOImpl extends DefaultBusinessDAO implements PonderationRisqueTheoriqueProduitDAO {

    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationRisqueTheoriqueProduit ponderationRisqueTheoProduit = (PonderationRisqueTheoriqueProduit) super.create(businessContext, PonderationRisqueTheoriqueProduit.class);
        ponderationRisqueTheoProduit.setProduit(new Produit());
//        ponderationRisqueTheoProduit.getProduit().setProdLb("FIXME");
        // le super.create à affecté la PonderationRisqueTheorique trouvée dans la BusinessStack
        // au ponderationRisqueTheoProduit qui vient d'être créé
        PonderationRisqueTheorique ponderationRisqueTheo = ponderationRisqueTheoProduit.getPonderationRisqueTheorique();
        if (ponderationRisqueTheo != null) {
            ponderationRisqueTheo.getPonderationRisqueTheoriqueProduits().add(ponderationRisqueTheoProduit);
            if (ponderationRisqueTheo.getTypeActivite() != null) {
                VTypeProduit typeProduit = ponderationRisqueTheo.getTypeActivite().getTypeProduit();
                ponderationRisqueTheoProduit.getProduit().setTypeProduit(typeProduit);
            }
        }
        return ponderationRisqueTheoProduit;
    }
}
