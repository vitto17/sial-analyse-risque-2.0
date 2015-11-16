package fr.gouv.agriculture.dal.sial.arq.query.translator;

import fr.gouv.agriculture.orion.query.Expression;

/**
 * Prend une Expression en entrée, et en envoie une nouvelle.
 * 
 * @author adrian.carretero
 * 
 */
public interface ExpressionTranslator {

	/**
	 * Prend une Expression en entrée, et en envoie une nouvelle ou
	 * <code>null</code> si l'expression en entrée doit être retirée des
	 * critères de recherche.
	 * 
	 * @param expression
	 *            expression qui doit être remplacée ou suprimée.
	 * @return <code>null</code> si l'expression en entrée doit être retirée des
	 *         critères de recherche, ou bien une Expression qui remplacera
	 *         l'expression fournie en entrée.
	 */
	public Expression translate(Expression expression);
}
