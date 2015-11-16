package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * Matcher qui recherche les Expression qui ont un alias donné.
 * 
 * @author adrian.carretero
 * 
 */
public class MatcherByAlias implements ExpressionMatcher {

	/** Nom de l'alias */
	private String aliasName;

	/**
	 * Constructeur
	 * @param aliasName Nom de l'alias
	 */
	public MatcherByAlias(String aliasName) {
		this.aliasName = aliasName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gouv.agriculture.dal.sial.arq.query.translator.ExpressionMatcher#
	 * isExpressionMatching(fr.gouv.agriculture.orion.query.Expression)
	 */
	@Override
	public boolean isExpressionMatching(Expression expression) {
		boolean result = false;

		if (expression instanceof SimpleExpression) {
			String simpleExprAliasName = ((SimpleExpression) expression).getCriterion().getAlias();

			if (simpleExprAliasName != null) {
				result = simpleExprAliasName.equals(aliasName);
			}
		}

		return result;
	}
}
