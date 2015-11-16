package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.business.PersistentEntity;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * Classe utilitaire pour les Query Orion
 * @author pbarreau
 *
 */
public class ObjectToObjectIdentifierTranslator extends AbstractSimpleExpressionTranslator {
	/** Nom de la propriete */
	String propertyName;

	/**
	 * Constructeur
	 * @param protertyName Nom de la propriete
	 */
	public ObjectToObjectIdentifierTranslator(String protertyName) {
		this.propertyName = protertyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gouv.agriculture.dal.sial.arq.query.translator.
	 * AbstractSimpleExpressionTranslator
	 * #translateSimpleExpression(fr.gouv.agriculture
	 * .orion.query.expression.SimpleExpression)
	 */
	@Override
	public Expression translateSimpleExpression(SimpleExpression simpleExpression) {
		Expression result;

		Parameter criterionValue = (Parameter) simpleExpression.getValue();
		if (criterionValue == null) {
			result = null;
		} else {

			Criterion criterion = simpleExpression.getCriterion();
			if (!CommonHelper.isEmpty(propertyName)) {
				criterion = new Criterion(this.propertyName, criterion.getAlias(), criterion.getLabel(),
						criterion.getFrom());
			}
			Parameter value = new Parameter(((PersistentEntity) criterionValue.getValue()).getIdentifier());

			result = new SimpleExpression(criterion, simpleExpression.getOperator(), value);
		}

		return result;
	}
}