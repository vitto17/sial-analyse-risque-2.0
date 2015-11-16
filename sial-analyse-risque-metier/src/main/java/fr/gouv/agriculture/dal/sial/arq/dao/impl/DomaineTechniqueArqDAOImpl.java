package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.dao.DomaineTechniqueArqDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.CriterionName;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.builder.QueryBuilder;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.operator.In;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DAO pour la nomenclature Domaine Technique
 *
 * @author sopra
 *
 */
public class DomaineTechniqueArqDAOImpl extends DefaultBusinessDAO implements DomaineTechniqueArqDAO {
	
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.dao.DomaineTechniqueArqDAO#findAll()
     */
    @Override
    public List<VDomaineTechnique> findAll(List<Order> orders) throws Exception {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VDomaineTechnique.class);
        Query query = queryBuilder.getQuery();
        
        query.setOrders(orders);
        
        BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        businessSearchContext.setCacheable(true);
        
        businessSearchContext.setQuery(query);
        Collection<VDomaineTechnique> collection = super.findBy(businessSearchContext);
        return new ArrayList<VDomaineTechnique>(collection);
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.dao.DomaineTechniqueArqDAO#findAll()
     */
    @Override
    public List<VDomaineTechnique> findIn(List<String> identifiants,List<Order> orders) throws Exception {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(VDomaineTechnique.class);
        Query query = queryBuilder.getQuery();

        // on obtient l'expression initialie pour le sauvegarder
        Expression expressionInitiale = query.getExpression();
        // on crée une nouvelle junction
        Junction junctionGlobal = new Conjunction();
        // si la expression initial n'est pas vide, on l'ajoute
        if (expressionInitiale != null) {
            junctionGlobal.add(expressionInitiale);
        };
        
         In inOperator =  (In) Operators.IN;
            inOperator.setReverse(true);
        Expression expDT = new SimpleExpression(new Criterion(CriterionName.DOMAINE_TECHNIQUE_CODE),
                inOperator, new Parameter(identifiants));
        junctionGlobal.add(expDT);

        // on mets la nouvelle junction dans la query
        query.setExpression(junctionGlobal);
        query.setOrders(orders);
        BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        businessSearchContext.setCacheable(true);
        
        businessSearchContext.setQuery(query);
        Collection<VDomaineTechnique> collection = super.findBy(businessSearchContext);
        return new ArrayList<VDomaineTechnique>(collection);
    }
}
