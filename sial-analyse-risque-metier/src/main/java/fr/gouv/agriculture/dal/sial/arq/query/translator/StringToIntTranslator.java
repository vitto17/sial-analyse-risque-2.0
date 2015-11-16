package fr.gouv.agriculture.dal.sial.arq.query.translator;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * ExpressionTranslator qui sert à traduire une expression comprenant une chaîne
 * de caractères contenant un entier (traduit avec un like en sql : criteria
 * like '%value%') en une expression avec un entier contenant un Equals
 * (criteria = value) en sql.
 * 
 * @author mlhote
 */
public class StringToIntTranslator extends AbstractSimpleExpressionTranslator {

	/**
	 * @inheritDoc
	 */
	@Override
	public Expression translateSimpleExpression(SimpleExpression expression) {
		Expression result = null;
		final Object criterionValue = expression.getValue();
		if (criterionValue != null) {
			final Parameter parameter = (Parameter) criterionValue;
			final Object parameterValueObject = parameter.getValue();
			if (parameterValueObject instanceof String) {
				final String paramValue = (String) parameterValueObject;
				if (StringUtils.isNumeric(paramValue)) {
					final Integer paramIntValue = Integer.parseInt(paramValue);
					result = new SimpleExpression(expression.getCriterion(), Operators.EQUALS, new Parameter(
							paramIntValue));
				}
			}
		}
		return result;
	}
}
