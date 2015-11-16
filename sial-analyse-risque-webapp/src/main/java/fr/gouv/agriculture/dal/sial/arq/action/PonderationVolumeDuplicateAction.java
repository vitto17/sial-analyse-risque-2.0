package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;

/**
 * Action de duplication des Ponderations de Volume
 * @author pbarreau
 *
 */
public class PonderationVolumeDuplicateAction extends AbstractPonderationDuplicateAction<PonderationVolume>{

	/**serial */
	private static final long serialVersionUID = 5897957969213329609L;

	@Override
	public List<PonderationVolume> getPonderations(FormuleRisque formuleRisque) {
		return formuleRisque.getPonderationVolumes();
	}

}
