package fr.gouv.agriculture.dal.sial.arq.business.rule;


import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Rule sur les Ponderation de Zone
 * @author pbarreau
 *
 */
public class PonderationZoneRule extends AbstractARQRule{

	/** serial. */
	private static final long serialVersionUID = 8751191598906217462L;

	@Override
	public RuleReport validate(Object object) {
		RuleReport report = new RuleReport();
		
		if(object != null ){
			PonderationZone ponderation = (PonderationZone)object;

			controlPoids(report,ponderation.getPzonePoidsNb2());
		}
		
		
		return report;
	}

}
