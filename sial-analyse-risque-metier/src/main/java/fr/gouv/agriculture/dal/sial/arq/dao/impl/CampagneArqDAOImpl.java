package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.dao.CampagneArqDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Disjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * DAO pour la nomenclature Campagne
 * @author sopra
 *
 */
public class CampagneArqDAOImpl extends DefaultBusinessDAO implements CampagneArqDAO{

	/** Critere: date de debut de validité */
	private static final String CRIT_DATE_DEB = "campDebDt";
	/** Critere: date de fin de validité */
	private static final String CRIT_DATE_FIN = "campFinDt";
	/** Critere: code rfa */
	private static final String CRIT_RFA = "campRfa";
	
	@Override
	public List<VCampagne> findCurrents() throws Exception {
		

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(GregorianCalendar.YEAR, 1);
		
		Date currentDate = calendar.getTime();
		
        BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        businessSearchContext.setCacheable(true);
        
        
        //liste des expression utilisée
        Expression expDateDebSup = new SimpleExpression(new Criterion(CRIT_DATE_DEB), Operators.LESS_EQUALS_THAN,new Parameter(currentDate));
        Expression expDateFinNull = new SimpleExpression(new Criterion(CRIT_DATE_FIN), Operators.IS_NULL, null);
        Expression expDateFinInf = new SimpleExpression(new Criterion(CRIT_DATE_FIN), Operators.GREATER_EQUALS_THAN,  new Parameter(currentDate));
        
        //Creation de la requete
        Junction juncDateFin = new Disjunction();
        juncDateFin.add(expDateFinNull);
        juncDateFin.add(expDateFinInf);
        
        Junction expression = new Conjunction();
        expression.add(expDateDebSup);
        expression.add(juncDateFin);
        
        //Ordre de tri
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(CRIT_RFA, false));
        
        Query query = new Query(VCampagne.class);
        query.setExpression(expression);
        query.setOrders(orders);
        businessSearchContext.setQuery(query);

        List<VCampagne> resultat = (List<VCampagne>) findBy(businessSearchContext);

        

        return resultat;
	}

	
}
