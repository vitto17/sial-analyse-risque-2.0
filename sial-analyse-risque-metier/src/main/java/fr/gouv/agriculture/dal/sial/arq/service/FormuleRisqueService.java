package fr.gouv.agriculture.dal.sial.arq.service;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.orion.business.BusinessService;

/**
 * Cette interface permet de manipuler des objets de type FormuleRisque.
 * 
 * @author jodurand
 * 
 */
public interface FormuleRisqueService extends BusinessService {

	/**
	 * Cette méthode permet de lancer la sauvegarde de plusieurs formules de
	 * risque en une seule fois, en leur attribuant à toutes la même campagne.
	 * Mais si jamais l'une des sauvegardes échoue, alors un rollback est
	 * effectué et aucune des modifications n'est persistée.
	 * 
	 * @param formulesRisque
	 *            La liste de formules de risque que l'on cherche à persister.
	 * 
	 * @param campagne
	 *            La campagne que l'on veut assigner aux formules de risque.
	 * 
	 * @param userLb
	 *            Le nom de l'utilisateur qui a créé ces formules de risque.
	 * 
	 * @throws Exception
	 * 
	 */
	public void synchronizeFormuleServiceWithRollback(List<FormuleRisque> formulesRisque, VCampagne campagne,
			String userLb) throws Exception;
}
