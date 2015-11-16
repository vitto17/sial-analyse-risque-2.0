package fr.gouv.agriculture.dal.sial.arq.service;

import java.util.List;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.orion.business.BusinessService;

/**
 * Cette classe contient des méthodes utilitaires pour la classe Batch.
 * 
 * @author jodurand
 * 
 */
public interface BatchService extends BusinessService {

	/**
	 * Cette méthode procède à l'enregistrement des demandes de traitement dans
	 * la base à partir des données envoyées par l'utilisateur.
	 * 
	 * @param campagne
	 *            Campagne qui sert de base à la création des
	 *            demandes de traitement.
	 * @param domaineTechnique
	 *            DomaineTechnique qui sert de base à la création des
	 *            demandes de traitement.            
	 * @param structRfaList
	 *            La liste des structures à passer au batch.
	 * 
	 * @throws Exception
	 *             Une exception qui est lancée au moindre problème.
	 */
	public void insertBatchFromVNbUa(VCampagne campagne, VDomaineTechnique domaineTechnique, List<String> structRfaList) throws Exception;
}
