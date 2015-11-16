package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.Batch;
import fr.gouv.agriculture.dal.sial.arq.dao.BatchDAO;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * DAO pour la nomenclature BatchDAO
 *
 * @author sopra
 *
 */
public class BatchDAOImpl extends DefaultBusinessDAO implements BatchDAO {
    
    /**
     * Critere: date de debut de validité
     */
    private static final String BATCH_UTI_LB = "batchUtiLb";
    /**
     * Critere: date de fin de validité
     */
    private static final String STATUS_ID = "statut.statutId";
	/** Critère : identifiant de la campagne des traitements */
	private static final String CAMP_RFA = "campRfa";
	/** Critère : identifiant du domaine technique des traitements */
	private static final String DT_RFA = "domaineTechnique.dtRfa";
    /** Critere: batch id */
    private static final String BATCH_ID = "batchId";
    
    @Override
	public List<Batch> findByEstatus(String batchUtiLb) throws Exception {
        
        
        // Enlever la recherche anterieur
        this.getPersistenceService().clearSyncStatements();
        
        
        //liste des expression utilisée
        Expression batchUti = new SimpleExpression(new Criterion(BATCH_UTI_LB), Operators.EQUALS, new Parameter(batchUtiLb));
        Expression statusId = new SimpleExpression(new Criterion(STATUS_ID), Operators.NOT_EQUALS, new Parameter(1));
        
        
        //Creation de la requete
        Junction expression = new Conjunction();
        expression.add(batchUti, statusId);
        
        //Ordre de tri
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(BATCH_ID, true));
        
        return getResults(expression, orders);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Batch> findByUtilisateurCampagneDomaineTechniqueStatut(String batchUtiLb, String campRfa, String dtRfa,
			Integer statutId) throws Exception {

		// Enlever la recherche antérieure
		this.getPersistenceService().clearSyncStatements();

		// liste des expressions utilisées
		Expression batchUti = new SimpleExpression(new Criterion(BATCH_UTI_LB), Operators.EQUALS, new Parameter(
				batchUtiLb));
		Expression statusId = new SimpleExpression(new Criterion(STATUS_ID), Operators.EQUALS, new Parameter(
				statutId));
		Expression campRfaExpr = new SimpleExpression(new Criterion(CAMP_RFA), Operators.EQUALS, new Parameter(campRfa));
		Expression dtRfaExpr = new SimpleExpression(new Criterion(DT_RFA), Operators.EQUALS, new Parameter(dtRfa));

		// Creation de la requete
		Junction expression = new Conjunction();
		expression.add(batchUti, statusId, campRfaExpr, dtRfaExpr);

		// Ordre de tri
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(BATCH_ID, true));

		return getResults(expression, orders);
	}

	@Override
	public List<Batch> findNonTerminePourUtilisateurCampagneDomaineTechnique(String batchUtiLb, String campRfa,
			String dtRfa) throws Exception {
		// Enlever la recherche antérieure
		this.getPersistenceService().clearSyncStatements();

		// liste des expressions utilisées
		Expression batchUti = new SimpleExpression(new Criterion(BATCH_UTI_LB), Operators.EQUALS, new Parameter(
				batchUtiLb));
		Expression statusId = new SimpleExpression(new Criterion(STATUS_ID), Operators.NOT_EQUALS, new Parameter(1));
		Expression campRfaExpr = new SimpleExpression(new Criterion(CAMP_RFA), Operators.EQUALS, new Parameter(campRfa));
		Expression dtRfaExpr = new SimpleExpression(new Criterion(DT_RFA), Operators.EQUALS, new Parameter(dtRfa));

		// Creation de la requete
		Junction expression = new Conjunction();
		expression.add(batchUti, statusId, campRfaExpr, dtRfaExpr);

		// Ordre de tri
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(BATCH_ID, true));

		return getResults(expression, orders);
	}

	/**
	 * Cette méthode fabrique une requête sur la table "batch" à partir d'une
	 * expression et d'un ensemble d'ordres de tri.
	 * 
	 * @param expression
	 *            L'expression de la requête.
	 * @param orders
	 *            La liste des ordres de tri à appliquer à la requête. Si null,
	 *            aucun ordre de tri n'est appliqué.
	 * @return Une liste de traitements.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<Batch> getResults(Junction expression, List<Order> orders) throws Exception {
		BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        Query query = new Query(Batch.class);
        query.setExpression(expression);
        if (!CommonHelper.isEmpty(orders)) {
        query.setOrders(orders);
        }
        businessSearchContext.setQuery(query);
        
        List<Batch> resultat = (List<Batch>) findBy(businessSearchContext);

        return resultat;
	}

}
