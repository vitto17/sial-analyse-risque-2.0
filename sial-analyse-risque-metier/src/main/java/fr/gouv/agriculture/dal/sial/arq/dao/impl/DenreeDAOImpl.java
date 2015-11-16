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

import fr.gouv.agriculture.dal.sial.arq.dao.DenreeDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDenree;
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
public class DenreeDAOImpl extends DefaultBusinessDAO implements DenreeDAO {

    private final static int MAX_ITEM_IN = 100;
    
    @Override
    public Map<String, VDenree> findWithListRfa(List<String> listRfaAll) throws Exception {
        
        // Init
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VDenree.class);

        double nbIteration1= (double) ((double) listRfaAll.size())/ ((double) MAX_ITEM_IN);
        int nbIteration = (int) Math.ceil(nbIteration1);
        
        Collection<VDenree> collectionAll = new ArrayList<VDenree>();
        Map<String, VDenree> retour = new HashMap<String, VDenree>();

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
            Expression expression = new SimpleExpression(new Criterion("denRfa"),
                    inOperator, new Parameter(listRfa));

            queryBuilder.where(expression);
            Query query = queryBuilder.getQuery();

            BusinessSearchContext businessSearchContext = new BusinessSearchContext();
            businessSearchContext.setQuery(query);
            Collection<VDenree> collection = super.findBy(businessSearchContext);
            
            collectionAll.addAll(collection);
        }
        
        for (VDenree denree : collectionAll) {
            retour.put(denree.getDenRfa(), denree);
        }
        
        return retour;

    }
}
