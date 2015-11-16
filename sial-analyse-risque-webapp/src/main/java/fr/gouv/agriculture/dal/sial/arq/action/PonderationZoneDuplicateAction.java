package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;

/**
 * Action de duplication des Ponderations de zone
 * @author pbarreau
 *
 */
public class PonderationZoneDuplicateAction extends AbstractPonderationDuplicateAction<PonderationZone>{

	/**serial */
	private static final long serialVersionUID = -6187430062909988315L;
	
	@Override
	public List<PonderationZone> getPonderations(
			FormuleRisque formuleRisque) {
		return formuleRisque.getPonderationZones();
	}

}
