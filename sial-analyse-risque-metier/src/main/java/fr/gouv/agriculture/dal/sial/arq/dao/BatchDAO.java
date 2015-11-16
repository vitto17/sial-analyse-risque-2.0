
package fr.gouv.agriculture.dal.sial.arq.dao;


import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.Batch;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
* DAO pour les Batch
* @author SiteGenerator
* @version $Id: dao.vm 12071 2008-10-29 23:44:22Z sebastien.bouvet $ 
*/
public interface BatchDAO extends BusinessDAO {
	
	/**
	 * Recherche une liste Batch par le statut 
	 * @param batchUtiLb status du batch
	 * @return liste Batch
	 * @throws Exception erreur BDD
	 */
    public List<Batch> findByEstatus(String batchUtiLb) throws Exception;

	/**
	 * Cette méthode permet de récupérer les traitements posées par un
	 * utilisateur pour une campagne et un domaine technique donnés et qui ne
	 * sont pas terminés.
	 * 
	 * @param batchUtiLb
	 *            Le login de l'utilisateur qui a posé la demande de traitement.
	 * @param campRfa
	 *            L'identifiant de la campagne pour laquelle on a posé la
	 *            demande de traitement.
	 * @param dtRfa
	 *            L'identifiant du domaine technique pour lequel on a posé la
	 *            demande de traitement.
	 * 
	 * @return Une liste de demandes de traitement.
	 * @throws Exception
	 *             Une exception lancée au moindre problème
	 */
	public List<Batch> findNonTerminePourUtilisateurCampagneDomaineTechnique(String batchUtiLb, String campRfa,
			String dtRfa) throws Exception;

	/**
	 * Cette méthode permet de récupérer les demandes de traitement posées par
	 * un utilisateur pour une campagne et un domaine technique donné, et dont
	 * on précise également le status.
	 * 
	 * @param batchUtiLb
	 *            Le login de l'utilisateur qui a posé la demande de traitement.
	 * @param campRfa
	 *            L'identifiant de la campagne pour laquelle on a posé la
	 *            demande de traitement.
	 * @param dtRfa
	 *            L'identifiant du domaine technique pour lequel on a posé la
	 *            demande de traitement.
	 * @param statutId
	 *            Le statut dans lequel les demandes de traitements que l'on
	 *            recherche doivent se trouver.
	 * @return Une liste de demandes de traitement.
	 * @throws Exception
	 *             Une exception lancée au moindre problème
	 */
	public List<Batch> findByUtilisateurCampagneDomaineTechniqueStatut(String batchUtiLb, String campRfa, String dtRfa,
			Integer statutId) throws Exception;
}
