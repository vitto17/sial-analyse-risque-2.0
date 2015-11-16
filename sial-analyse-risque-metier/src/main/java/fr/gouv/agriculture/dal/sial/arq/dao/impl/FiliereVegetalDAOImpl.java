/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.gouv.agriculture.dal.sial.arq.dao.FiliereVegetalDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VFiliereVegetal;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.builder.QueryBuilder;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.operator.In;

/**
 *
 * @author pegaltier
 */
public class FiliereVegetalDAOImpl extends DefaultBusinessDAO implements FiliereVegetalDAO {
    
	/** Nombre max d'objets */
    private final static int MAX_ITEM_IN = 100;
    
    @Override
    public Map<String, VFiliereVegetal> findWithListRfa(List<String> listRfaAll) throws Exception {
        
        // Init
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VFiliereVegetal.class);

        double nbIteration1= (double) ((double) listRfaAll.size())/ ((double) MAX_ITEM_IN);
        int nbIteration = (int) Math.ceil(nbIteration1);
        
        Collection<VFiliereVegetal> collectionAll = new ArrayList<VFiliereVegetal>();
        Map<String, VFiliereVegetal> retour = new HashMap<String, VFiliereVegetal>();
        
        // Boucle qui permet de partager la requete en plusieurs fois
        for (int i = 0;i<=nbIteration-1;i++){
            
            int fromIndex = i*MAX_ITEM_IN;
            int toIndex = (i*MAX_ITEM_IN)+MAX_ITEM_IN;
            if (toIndex>listRfaAll.size()) {
                toIndex = listRfaAll.size();
            }
            List<String> listRfa = listRfaAll.subList(fromIndex, toIndex);
            In inOperator =  (In) Operators.IN;
            inOperator.setReverse(true);
            Expression expression = new SimpleExpression(new Criterion("filvegRfa"),
                    inOperator, new Parameter(listRfa));

            queryBuilder.where(expression);
            Query query = queryBuilder.getQuery();

            BusinessSearchContext businessSearchContext = new BusinessSearchContext();
            businessSearchContext.setQuery(query);
            Collection<VFiliereVegetal> collection = super.findBy(businessSearchContext);

            collectionAll.addAll(collection);
        }
        
        for (VFiliereVegetal filiereVegetal : collectionAll) {
            retour.put(filiereVegetal.getFilvegRfa(), filiereVegetal);
        }
        return retour;

    }
}
