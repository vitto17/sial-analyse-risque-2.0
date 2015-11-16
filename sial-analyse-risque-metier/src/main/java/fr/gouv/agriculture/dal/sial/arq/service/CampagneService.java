package fr.gouv.agriculture.dal.sial.arq.service;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;

/**
 * Service pour Campagne
 * @author sopra
 *
 */
public interface CampagneService {

	/**
	 * Retourne la Campagne en cours.
	 * @return Campagne courante
	 * @throws Exception exception
	 */
	public VCampagne getCurrentCampagne() throws Exception;
	
}
