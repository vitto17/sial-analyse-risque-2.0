package fr.gouv.agriculture.dal.sial.arq.business;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Interface PonderationARQ.
 *
 * @param <T> the generic type
 */
public interface PonderationARQ<T> extends Comparable<T> {

	/**  Resultat de la comparaison OK. */
	public static final int COMPARE_OK = 0;
	
	/**  Resultat de la comparaison KO. */
	public static final int COMPARE_KO = 1;
	
	
	/**
	 * Formule de risque associée.
	 *
	 * @return la formule de risque associée
	 */
	public FormuleRisque getFormuleRisque();
	
	/**
	 * Duplique l'entité.
	 *
	 * @return entité dupliquée
	 */
	public T duplicate();
	
	/**
	 * Retourne l'identifiant de l'entité.
	 * @return identifiant de l'entité
	 */
	public Serializable getIdentifier();
	
	
}
