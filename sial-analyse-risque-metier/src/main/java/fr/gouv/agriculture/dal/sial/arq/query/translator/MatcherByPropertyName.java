package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * Matcher qui recherche les Expression qui ont un propertyName donné.
 * 
 * @author adrian.carretero
 * 
 */
public class MatcherByPropertyName implements ExpressionMatcher {

	/** Nom de la propriete */
	private String propertyName;

	/**
	 * Setter pour le Nom de la propriete
	 * @param propertyName Nom de la propriete
	 */
	public MatcherByPropertyName(String propertyName) {
		this.propertyName = propertyName;
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
			String simpleExprPropertyName = ((SimpleExpression) expression).getCriterion().getPropertyName();

			if (simpleExprPropertyName != null) {
				result = simpleExprPropertyName.equals(propertyName);
			}
		}

		return result;
	}
}
