/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.query.Order;
import java.util.List;

/**
 * DAO utilisé pour la page ponderationVolume afin de contextualiser la
 * unite production de chaque ligne de la slavelist par rapport au type d'activité de
 * la meme ligne.
 *
 * @author pegaltier
 */
public interface UniteProductionDAO extends BusinessDAO {
    
	/**
	 * Recherche des VUniteProduction
	 * @param orders ordre des données
	 * @param typeActivite Type d'activité
	 * @return Liste de VUniteProduction triés
	 * @throws Exception erreur dans la recherche
	 */
	public List<VUniteProduction> findWithContextualisation(List<Order> orders,
			VTypeActivite typeActivite) throws Exception;
}
