package fr.gouv.agriculture.dal.sial.arq.controller.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.ModificationPonderation;
import fr.gouv.agriculture.dal.sial.arq.business.bean.DuplicationFormuleRisqueBean;
import fr.gouv.agriculture.dal.sial.arq.constante.TypePonderation.TypePonderationEnum;
import fr.gouv.agriculture.dal.sial.arq.dao.impl.FormuleRisqueDuplicationDAOImpl;
import fr.gouv.agriculture.dal.sial.arq.service.FormuleRisqueService;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.helper.CommonHelper;

/**
 * Ce contrôleur est utilisé dans la page de duplication de formules de risque.
 * Il permet de créer les copies et de les sauvegarder si la duplication est
 * validée.
 * 
 * @author jodurand
 * 
 */
public class FormuleRisqueDuplicationFC extends FormController {

	/** Service de gestion de l'utilisateur*/
	@Inject
	private HabilitationsService habilitationsService;

	@Inject
	private FormuleRisqueService formuleRisqueService;

	@Inject(value = "formuleRisqueLC")
	private ListController formuleRisqueLC;

	@SuppressWarnings("unchecked")
	@Override
	protected Object obtainBusiness() throws Exception {
		DuplicationFormuleRisqueBean res = (DuplicationFormuleRisqueBean) super.obtainBusiness();
		res.setFormuleRisqueList(new ArrayList<FormuleRisque>());

		List<FormuleRisque> selection = (List<FormuleRisque>) formuleRisqueLC.getSelection();
		if (!CommonHelper.isEmpty(selection)) {
			for (FormuleRisque formule : selection) {
				FormuleRisque formuleReloaded = (FormuleRisque) getBusinessDao().load(FormuleRisque.class,
						formule.getIdentifier());
				FormuleRisque newFormule = ((FormuleRisqueDuplicationDAOImpl) getBusinessDao()).createFormuleRisque(
						getBusinessContext(), formuleReloaded);
				res.getFormuleRisqueList().add(newFormule);
			}
		}

		return res;
	}

	@Override
	protected void save() throws Exception {
		DuplicationFormuleRisqueBean object = (DuplicationFormuleRisqueBean) getFormModel().getObject();

		List<FormuleRisque> formuleRisqueList = object.getFormuleRisqueList();
		VCampagne campagne = object.getCampagne();
		String userLb = getCurrentUserLogin();
		if (!CommonHelper.isEmpty(formuleRisqueList)) {
			formuleRisqueService.synchronizeFormuleServiceWithRollback(formuleRisqueList, campagne, userLb);
		}
	}

/*	private void fhjdsfhjksd(List<FormuleRisque> formuleRisqueList, VCampagne campagne) throws Exception {
		for (FormuleRisque formule : formuleRisqueList) {
			formule.setCampagne(campagne);
			formule.setFormCreationDt(new Date());
			String userLb = getCurrentUserLogin();
			formule.setFromAuteurCreationLb(userLb);

			if (formule.isFromCritDestinationOn()) {
				ModificationPonderation modPond = (ModificationPonderation) getBusinessDao().create(
						getBusinessContext(), ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				modPond.setModpodCreaTs(new Date());
				modPond.setModpodTypepondLb(TypePonderationEnum.DESTINATION.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritDiffusionOn()) {
				ModificationPonderation modPond = (ModificationPonderation) getBusinessDao().create(
						getBusinessContext(), ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				modPond.setModpodCreaTs(new Date());
				modPond.setModpodTypepondLb(TypePonderationEnum.DIFFUSION.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritRisquetheoriqueOn()) {
				ModificationPonderation modPond = (ModificationPonderation) getBusinessDao().create(
						getBusinessContext(), ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				modPond.setModpodCreaTs(new Date());
				modPond.setModpodTypepondLb(TypePonderationEnum.RISQUE_THEORIQUE.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritVolumeOn()) {
				ModificationPonderation modPond = (ModificationPonderation) getBusinessDao().create(
						getBusinessContext(), ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				modPond.setModpodCreaTs(new Date());
				modPond.setModpodTypepondLb(TypePonderationEnum.VOLUME.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			if (formule.isFromCritZoneOn()) {
				ModificationPonderation modPond = (ModificationPonderation) getBusinessDao().create(
						getBusinessContext(), ModificationPonderation.class);
				modPond.setFormuleRisque(formule);
				modPond.setModpodCreaTs(new Date());
				modPond.setModpodTypepondLb(TypePonderationEnum.ZONE.getValue());
				modPond.setModpodUtiCreaLb(userLb);
				formule.getModificationPonderations().add(modPond);
			}

			saveBusiness(formule);
		}
	}*/

	/**
	 * Recuperation de l'utilisateur courant.
	 * 
	 * @return libellé de l'utilisateur courant
	 */
	public String getCurrentUserLogin() {
		return habilitationsService.getUtilisateurConnecte().getLogin();
	}
}
