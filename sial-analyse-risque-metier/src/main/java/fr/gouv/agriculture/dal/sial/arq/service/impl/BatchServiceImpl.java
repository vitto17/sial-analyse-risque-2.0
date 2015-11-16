package fr.gouv.agriculture.dal.sial.arq.service.impl;

import java.util.List;
import java.util.Calendar;

import fr.gouv.agriculture.dal.sial.arq.business.Batch;
import fr.gouv.agriculture.dal.sial.arq.business.Statut;
import fr.gouv.agriculture.dal.sial.arq.business.Statut.StatutEnum;
import fr.gouv.agriculture.dal.sial.arq.service.BatchService;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.o2.transaction.TransactionPolicyType;
import fr.gouv.agriculture.o2.transaction.Transactional;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;

/**
 * Implémentation de l'interface BatchService.
 * 
 * @author jodurand
 * 
 */
public class BatchServiceImpl implements BatchService {

	private static final long serialVersionUID = 1L;

	@Inject
	private BusinessDAO businessDao;
	/** Service de gestion de l'utilisateur */
	@Inject
	private HabilitationsService habilitationsService;

	@Override
	@Transactional(policy = TransactionPolicyType.REQUIRED)
	public void insertBatchFromVNbUa(VCampagne campagne, VDomaineTechnique domaineTechnique, List<String> structRfaList) throws Exception {

			Batch batch = (Batch) businessDao.create(new BusinessContext(), Batch.class);
			// On définit les données du contexte
			batch.setBatchDemandeTs(Calendar.getInstance().getTime());
			batch.setBatchUtiLb(habilitationsService.getUtilisateurConnecte().getLogin());
			// On définit le statut du traitement
			batch.setStatut((Statut) businessDao.load(Statut.class, StatutEnum.EN_ATTENTE.getCode()));
			// On définit la campagne et le domaine technique
			batch.setCampRfa(campagne.getCampRfa());
			batch.setDomaineTechnique(domaineTechnique);
			// On définit la liste de codes rfa de structures
			String structRfaListStr = formatStructRfaList(structRfaList);
			batch.setBatchListStructRfa(structRfaListStr);

			businessDao.synchronize(new BusinessContext(), batch);

	}

	/**
	 * Cette méthode formate une liste de codes rfa de structure en une chaîne
	 * de caractère dans laquelle les codes rfa sont séparés par ', '.
	 * 
	 * @param structRfaList
	 *            La liste de codes rfa de structures à formater
	 * 
	 * @return Une chaîne de caractère contenant les codes rfa de structRfaList,
	 *         séparés par ', '
	 */
	private String formatStructRfaList(List<String> structRfaList) {
		String structRfaListStr = null;

		if (!CommonHelper.isEmpty(structRfaList)) {
			StringBuilder strBuild = new StringBuilder("");
			for (String structRfa : structRfaList) {
				strBuild.append(", ");
				strBuild.append(structRfa);
			}
			structRfaListStr = strBuild.substring(2);
		}

		return structRfaListStr;
	}

}
