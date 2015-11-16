package fr.gouv.agriculture.dal.sial.arq.service.impl;

import java.util.Date;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.ModificationPonderation;
import fr.gouv.agriculture.dal.sial.arq.constante.TypePonderation.TypePonderationEnum;
import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDuplicationDAO;
import fr.gouv.agriculture.dal.sial.arq.service.FormuleRisqueService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.o2.transaction.TransactionPolicyType;
import fr.gouv.agriculture.o2.transaction.Transactional;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.helper.CommonHelper;

/**
 * Implémentation de l'interface FormuleRisqueService
 * 
 * @author jodurand
 * 
 */
public class FormuleRisqueServiceImpl implements FormuleRisqueService {

	/** serial */
	private static final long serialVersionUID = 1L;

	/** DAO sur les Formules de risque*/
	@Inject
	private FormuleRisqueDuplicationDAO businessDao;

	@Transactional(policy = TransactionPolicyType.REQUIRED)
	@Override
	public void synchronizeFormuleServiceWithRollback(List<FormuleRisque> formulesRisque, VCampagne campagne,
			String userLb) throws Exception {

		BusinessContext businessContext = new BusinessContext();

		for (FormuleRisque formule : formulesRisque) {
			formule.setCampagne(campagne);
			formule.setFormCreationDt(new Date());
			formule.setFromAuteurCreationLb(userLb);

			if (formule.isFromCritDestinationOn()) {
				ModificationPonderation modPond = (ModificationPonderation) businessDao.create(businessContext,
						ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				if (!CommonHelper.isEmpty(formule.getPonderationDestinations())) {
					modPond.setModpodCreaTs(new Date());
				}
				modPond.setModpodTypepondLb(TypePonderationEnum.DESTINATION.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritDiffusionOn()) {
				ModificationPonderation modPond = (ModificationPonderation) businessDao.create(businessContext,
						ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				if (!CommonHelper.isEmpty(formule.getPonderationDiffusions())) {
					modPond.setModpodCreaTs(new Date());
				}
				modPond.setModpodTypepondLb(TypePonderationEnum.DIFFUSION.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritRisquetheoriqueOn()) {
				ModificationPonderation modPond = (ModificationPonderation) businessDao.create(businessContext,
						ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				if (!CommonHelper.isEmpty(formule.getPonderationRisqueTheoriques())) {
					modPond.setModpodCreaTs(new Date());
				}
				modPond.setModpodTypepondLb(TypePonderationEnum.RISQUE_THEORIQUE.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritVolumeOn()) {
				ModificationPonderation modPond = (ModificationPonderation) businessDao.create(businessContext,
						ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				if (!CommonHelper.isEmpty(formule.getPonderationVolumes())) {
					modPond.setModpodCreaTs(new Date());
				}
				modPond.setModpodTypepondLb(TypePonderationEnum.VOLUME.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritZoneOn()) {
				ModificationPonderation modPond = (ModificationPonderation) businessDao.create(businessContext,
						ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				if (!CommonHelper.isEmpty(formule.getPonderationZones())) {
					modPond.setModpodCreaTs(new Date());
				}
				modPond.setModpodTypepondLb(TypePonderationEnum.ZONE.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			businessDao.synchronizeForm(businessContext, formule);
		}

	}

}
