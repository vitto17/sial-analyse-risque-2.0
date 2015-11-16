package fr.gouv.agriculture.dal.sial.arq.query.translator;

/**
 * Regoupe un ExpressionTranslator et un ExpressionMatcher, permet de
 * remplacer/supprimer les Expressions qui correspondent à certains critères.
 * 
 * @author adrian.carretero
 * 
 */
public interface TranslatorStrategy extends ExpressionTranslator, ExpressionMatcher {
}
