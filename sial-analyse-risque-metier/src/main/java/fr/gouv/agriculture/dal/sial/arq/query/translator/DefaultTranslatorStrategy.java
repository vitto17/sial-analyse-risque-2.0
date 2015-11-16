package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.query.Expression;

/**
 * TranslatorStrategy par défaut qui est basé sur un ExpressionMatcher donné et
 * un ExpressionTranslator donné.
 * 
 * @author adrian.carretero
 * 
 */
public class DefaultTranslatorStrategy implements TranslatorStrategy {

	private ExpressionMatcher expressionMatcher;
	private ExpressionTranslator expressionTranslator;

	/**
	 * Constructeur qui définit les ExpressionMatcher et ExpressionTranslator
	 * utilisés.
	 * 
	 * @param expressionMatcher
	 *            ExpressionMatcher utlisé.
	 * @param expressionTranslator
	 *            ExpressionTranslator utilisé.
	 */
	public DefaultTranslatorStrategy(ExpressionMatcher expressionMatcher, ExpressionTranslator expressionTranslator) {
		if (expressionMatcher == null) {
			throw new IllegalArgumentException(
					"The expressionMatcher argument of DefaultTranslatorStrategy constructor must not be null");
		}

		if (expressionTranslator == null) {
			throw new IllegalArgumentException(
					"The expressionTranslator argument of DefaultTranslatorStrategy constructor must not be null");
		}

		this.expressionMatcher = expressionMatcher;
		this.expressionTranslator = expressionTranslator;
	}

	/**
	 * Utilise l'ExpressionTranslator fournit dans le constructeur pour traduire
	 * une Expression.
	 * 
	 * @param expression
	 *            Expression à traduire
	 * @return Expression traduite
	 */
	@Override
	public Expression translate(Expression expression) {
		return expressionTranslator.translate(expression);
	}

	/**
	 * Utilise l'ExpressionMatcher fournit dans le constructeur pour déterminer
	 * si une Expression doit être traduite.
	 * 
	 * @param expression
	 *            Expression à tester
	 * @return <code>true</code> si l'expression doit être traduite
	 */
	@Override
	public boolean isExpressionMatching(Expression expression) {
		return expressionMatcher.isExpressionMatching(expression);
	}
}
