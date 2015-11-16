package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.query.Expression;

/**
 * Cette classe sert à filtrer les Expression qui correspondent à certains
 * critères.
 * 
 * @author adrian.carretero
 * 
 */
public interface ExpressionMatcher {

	/**
	 * Retourne <code>true</code> si l'expression en entrée doit être remplacée.
	 * 
	 * @param expression
	 *            Expression testée.
	 * @return <code>true</code> si l'expression en entrée doit être remplacée.
	 */
	public boolean isExpressionMatching(Expression expression);
}
