/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.query.Order;
import java.util.List;

/**
 * DAO utilisé pour la page ponderationDestination afin de contextualiser la
 * destination de chaque ligne de la slavelist par rapport au type d'activité de
 * la meme ligne.
 * 
 * @author pegaltier
 */
public interface DestinationDAO extends BusinessDAO {

	/**
	 * Recherche une liste de VDestination par Type d'activité
	 * @param orders ordre des données
	 * @param typeActivite Type d'activité
	 * @return liste de VDestination triée
	 * @throws Exception
	 */
	public List<VDestination> findWithContextualisation(List<Order> orders,
			VTypeActivite typeActivite) throws Exception;
}
