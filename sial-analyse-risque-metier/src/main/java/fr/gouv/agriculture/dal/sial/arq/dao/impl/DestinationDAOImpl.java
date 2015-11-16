/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.DestinationDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Join;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.builder.QueryBuilder;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.join.InnerJoin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DAO utilisé pour la page ponderationDestination afin de contextualiser la
 * destination de chaque ligne de la slavelist par rapport au type d'activité de
 * la meme ligne.
 *
 * @author pegaltier
 */
public class DestinationDAOImpl extends DefaultBusinessDAO implements DestinationDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.gouv.agriculture.dal.sial.arq.dao.DestinationDAO#findWithContextualisation
	 * (java.util.List,
	 * fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite)
	 */
    @Override
    public List<VDestination> findWithContextualisation(List<Order> orders, VTypeActivite typeActivite) throws Exception {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VDestination.class);
        
        // Contextualisation des destinations avec le type d'activité passé en parametre
        Join joinCtx = new InnerJoin("ctxTaDests", "joinCtxTaDests");
        queryBuilder.addJoin(joinCtx);
        Expression expression = new SimpleExpression(new Criterion("typeActivite", null, null, "joinCtxTaDests"),
                Operators.EQUALS, new Parameter(typeActivite));

        queryBuilder.where(expression);
        queryBuilder.orderBy(orders.toArray(new Order[0]));
        Query query = queryBuilder.getQuery();

        BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        businessSearchContext.setQuery(query);
        Collection<VDestination> collection = super.findBy(businessSearchContext);
        return new ArrayList<VDestination>(collection);
    }
}
