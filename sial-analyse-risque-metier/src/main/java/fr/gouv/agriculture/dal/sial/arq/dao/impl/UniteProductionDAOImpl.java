/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.UniteProductionDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
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
 * DAO utilisé pour la page ponderationVolume afin de contextualiser la unite
 * production de chaque ligne de la slavelist par rapport au type d'activité de
 * la meme ligne.
 *
 * @author pegaltier
 */
public class UniteProductionDAOImpl extends DefaultBusinessDAO implements UniteProductionDAO {

    @Override
    public List<VUniteProduction> findWithContextualisation(List<Order> orders, VTypeActivite typeActivite) throws Exception {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VUniteProduction.class);

        // Contextualisation des unite production avec le type d'activité passé en parametre
        Join joinCtx = new InnerJoin("ctxTaUprods", "joinCtxTaUprods");
        queryBuilder.addJoin(joinCtx);
        Expression expression = new SimpleExpression(new Criterion("typeActivite", null, null, "joinCtxTaUprods"),
                Operators.EQUALS, new Parameter(typeActivite));

        queryBuilder.where(expression);
        queryBuilder.orderBy(orders.toArray(new Order[0]));
        Query query = queryBuilder.getQuery();

        BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        businessSearchContext.setQuery(query);
        Collection<VUniteProduction> collection = super.findBy(businessSearchContext);
        return new ArrayList<VUniteProduction>(collection);
    }
}
