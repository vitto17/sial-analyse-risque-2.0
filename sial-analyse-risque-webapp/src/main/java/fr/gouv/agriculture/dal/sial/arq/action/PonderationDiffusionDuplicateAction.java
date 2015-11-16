package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;

/**
 * Action de duplication des Ponderations de Diffusion
 * @author pbarreau
 *
 */
public class PonderationDiffusionDuplicateAction extends AbstractPonderationDuplicateAction<PonderationDiffusion>{

	/** serial */
	private static final long serialVersionUID = -8275520645029631683L;

	@Override
	public List<PonderationDiffusion> getPonderations(
			FormuleRisque formuleRisque) {
		return formuleRisque.getPonderationDiffusions();
	}

}
