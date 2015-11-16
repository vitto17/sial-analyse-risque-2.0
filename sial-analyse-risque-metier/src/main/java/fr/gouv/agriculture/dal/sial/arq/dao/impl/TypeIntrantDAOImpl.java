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

import fr.gouv.agriculture.dal.sial.arq.dao.TypeIntrantDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeIntrant;
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
public class TypeIntrantDAOImpl extends DefaultBusinessDAO implements TypeIntrantDAO {
    
    private final static int MAX_ITEM_IN = 100;
    
    @Override
    public Map<String, VTypeIntrant> findWithListRfa(List<String> listRfaAll) throws Exception {
        
        // Init
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VTypeIntrant.class);

        double nbIteration1= (double) ((double) listRfaAll.size())/ ((double) MAX_ITEM_IN);
        int nbIteration = (int) Math.ceil(nbIteration1);
        
        Collection<VTypeIntrant> collectionAll = new ArrayList<VTypeIntrant>();
        Map<String, VTypeIntrant> retour = new HashMap<String, VTypeIntrant>();
        
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
            Expression expression = new SimpleExpression(new Criterion("tintRfa"),
                    inOperator, new Parameter(listRfa));

            queryBuilder.where(expression);
            Query query = queryBuilder.getQuery();

            BusinessSearchContext businessSearchContext = new BusinessSearchContext();
            businessSearchContext.setQuery(query);
            Collection<VTypeIntrant> collection = super.findBy(businessSearchContext);

            collectionAll.addAll(collection);
        }
        
        for (VTypeIntrant typeIntrant : collectionAll) {
            retour.put(typeIntrant.getTintRfa(), typeIntrant);
        }
        return retour;

    }
}
