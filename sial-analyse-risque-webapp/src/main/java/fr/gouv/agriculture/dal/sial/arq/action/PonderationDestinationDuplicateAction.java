package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
/**
 * Action de duplication des Ponderation de destination
 * @author pbarreau
 *
 */
public class PonderationDestinationDuplicateAction extends AbstractPonderationDuplicateAction<PonderationDestination> {

	/** serial */
	private static final long serialVersionUID = 8762873407260490137L;

	@Override
	public List<PonderationDestination> getPonderations(
			FormuleRisque formuleRisque) {
		return formuleRisque.getPonderationDestinations();
	}

}
