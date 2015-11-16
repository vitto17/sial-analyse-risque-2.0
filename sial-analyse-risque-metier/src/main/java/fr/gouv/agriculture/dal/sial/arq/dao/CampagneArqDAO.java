package fr.gouv.agriculture.dal.sial.arq.dao;

import java.util.List;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * DAO pour la nomenclature Campagne
 * @author sopra
 *
 */
public interface CampagneArqDAO extends BusinessDAO{

	/**
	 * Retourne les campagnes actives à la date du jour.
	 * @return les campagnes actives à la date du jour.
	 * @throws Exception exception
	 */
	public List<VCampagne> findCurrents() throws Exception;
}
