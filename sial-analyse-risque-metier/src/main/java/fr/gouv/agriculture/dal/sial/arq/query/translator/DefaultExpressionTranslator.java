package fr.gouv.agriculture.dal.sial.arq.query.translator;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Disjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.Negation;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * L'arborescence d'une Expression fournie en entrée, est parcourue et pour
 * chaque Expression de l'arborescence, vérifie si cette dernière correspond à
 * des critères donnés et si c'est le cas, la supprime ou la remplace par une
 * nouvelle Expression. <br/>
 * L'Expression initiale n'est pas modifiée, mais on renvoie une nouvelle
 * Expression qui contient les Expressions remplacées/supprimées. <br/>
 * Le DefaultExpressionTranslator est configuré soit
 * <ul>
 * <li>en lui ajoutant des TranslatorStrategy</li>
 * <li>en lui ajoutant des ExpressionTranslator qui se déclencheront pour les
 * Expression avec un propertyName donné</li>
 * <li>en lui ajoutant des ExpressionTranslator qui se déclencheront pour les
 * Expression avec un alias donné</li>
 * </ul>
 * <br/>
 * Exemple d'utilisation: <code>
 * 
 * public Collection<T> findBy(BusinessSearchContext businessSearchContext)
 * 			throws Exception {
 * 
 * 		Collection<T> retour = null;
 * 		Query query = businessSearchContext.getQuery();
 * 		Expression expressionInitiale = query.getExpression();
 * 
 * 		DefaultExpressionTranslator expressionTranslator = new DefaultExpressionTranslator();
 * 
 * 		expressionTranslator.addTranslatorForPropertyName(
 * 				"aPassagesEnCommission", new BooleanToCollectionIsEmptyTranslator("passagesEnCommissions"));
 * 
 * 		expressionTranslator.addTranslatorForPropertyName(
 * 				"soumettreDpma", new BooleanToEqualsTranslator("O","N"));
 * 
 * 		try {
 * 			query.setExpression(expressionTranslator.translate(expressionInitiale));
 * 			retour = super.findBy(businessSearchContext);
 * 		} finally {
 * 			query.setExpression(expressionInitiale);
 * 		}
 * 
 * 		return retour;
 * 	}
 * 
 * </code>
 * 
 * @author adrian.carretero
 * 
 */
public class DefaultExpressionTranslator implements ExpressionTranslator {

	private List<TranslatorStrategy> translatorStrategies = new ArrayList<TranslatorStrategy>();

	/**
	 * L'arborescence d'une Expression fournie en entrée, est parcourue et pour
	 * chaque Expression de l'arborescence, vérifie si cette dernière correspond
	 * à des critères donnés et si c'est le cas, la supprime ou la remplace par
	 * une nouvelle Expression.
	 * 
	 * @param expression
	 *            Expression à traduire
	 * @return Une nouvelle expression qui contient les Expressions
	 *         remplacées/supprimées.
	 */
	@Override
	public Expression translate(Expression expression) {
		Expression result = null;

		boolean isExpressionTranslated = false;

		for (TranslatorStrategy translator : getTranslatorStrategies()) {
			if (translator.isExpressionMatching(expression)) {
				result = translator.translate(expression);
				isExpressionTranslated = true;
				break;
			}
		}

		if (!isExpressionTranslated) {
			if (expression instanceof Junction) {
				if (expression instanceof Conjunction) {
					result = new Conjunction();
				} else {
					result = new Disjunction();
				}

				Junction resultJunction = (Junction) result;
				Junction expressionJunction = (Junction) expression;

				for (Expression subExpression : expressionJunction.getExpressions()) {
					resultJunction.add(translate(subExpression));
				}
			} else if (expression instanceof Negation) {
				result = new Negation(translate(((Negation) expression).getExpression()));
			} else {
				result = expression;
			}
		}

		if (result == null) {
			result = new SimpleExpression(new Criterion("dummy"), Operators.INACTIVE, null);
		}

		return result;
	}

	/**
	 * Définit la liste des TranslatorStrategy.
	 * 
	 * @param translatorStrategies
	 *            liste des TranslatorStrategy utilisées
	 */
	public void setTranslatorStrategies(List<TranslatorStrategy> translatorStrategies) {
		this.translatorStrategies = translatorStrategies;
	}

	/**
	 * Récupère la liste des TranslatorStrategy.
	 * 
	 * @return la liste des TranslatorStrategy.
	 */
	public List<TranslatorStrategy> getTranslatorStrategies() {
		return translatorStrategies;
	}

	/**
	 * Ajout un TranslatorStrategy.
	 * 
	 * @param translatorStragey
	 *            TranslatorStrategy ajouté.
	 */
	public void addTranslatorStrategy(TranslatorStrategy translatorStragey) {
		getTranslatorStrategies().add(translatorStragey);
	}

	/**
	 * Ajoute un DefaultTranslatorStrategy créé à partir des ExpressionMatcher
	 * et ExpressionTranslator fournis.
	 * 
	 * @param expressionMatcher
	 *            ExpressionMatcher pour rechercher les Expressions que l'on
	 *            veut remplacer
	 * @param expressionTranslator
	 *            ExpressionTranslator définit comment les Expressions qui
	 *            correspondent sont remplacées
	 */
	public void addTranslatorStrategy(ExpressionMatcher expressionMatcher, ExpressionTranslator expressionTranslator) {
		addTranslatorStrategy(new DefaultTranslatorStrategy(expressionMatcher, expressionTranslator));
	}

	/**
	 * Ajoute un ExpressionTranslator qui sera déclenché pour les Expression qui
	 * ont un propertyName donné.
	 * 
	 * @param propertyName
	 *            propertyName des Expressions à traduire
	 * @param expressionTranslator
	 *            ExpressionTranslator pour traduire les Expressions qui
	 *            correspondent
	 */
	public void addTranslatorForPropertyName(String propertyName, ExpressionTranslator expressionTranslator) {
		addTranslatorStrategy(new MatcherByPropertyName(propertyName), expressionTranslator);
	}

	/**
	 * Ajoute un ExpressionTranslator qui sera déclenché pour les Expression qui
	 * ont un aliasName donné.
	 * 
	 * @param aliasName
	 *            aliasName des Expressions à traduire
	 * @param expressionTranslator
	 *            ExpressionTranslator pour traduire les Expressions qui
	 *            correspondent
	 */
	public void addTranslatorForAlias(String aliasName, ExpressionTranslator expressionTranslator) {
		addTranslatorStrategy(new MatcherByAlias(aliasName), expressionTranslator);
	}
}
