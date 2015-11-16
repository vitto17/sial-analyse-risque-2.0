package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operator;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * ExpressionTranslator qui sert à traduire les SimpleExpression, vérifie si
 * l'Operator de la SimpleExprssion est INACTIVE, et dans ce cas ne fais pas la
 * traduction
 * 
 * @author adrian.carretero
 * 
 */
public abstract class AbstractSimpleExpressionTranslator implements ExpressionTranslator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.gouv.agriculture.adage.salsa.query.ExpressionTranslator#translate(
	 * fr.gouv.agriculture.orion.query.Expression)
	 */
	@Override
	public Expression translate(Expression expression) {
		Expression result = null;

		if (expression instanceof SimpleExpression) {
			SimpleExpression simpleExpression = (SimpleExpression) expression;

			Operator operator = simpleExpression.getOperator();

			if (operator.equals(Operators.INACTIVE)) {
				result = expression;
			} else {
				result = translateSimpleExpression(simpleExpression);
			}
		} else {
			throw new IllegalArgumentException(this.getClass().getName() + " can only be used on a SimpleExpression");
		}

		return result;
	}

	/**
	 * Renvoie une nouvelle Expression.
	 * 
	 * @param simpleExpression
	 *            SimpleExpression à traduire.
	 * @return Expression traduite.
	 */
	public abstract Expression translateSimpleExpression(SimpleExpression simpleExpression);
}
