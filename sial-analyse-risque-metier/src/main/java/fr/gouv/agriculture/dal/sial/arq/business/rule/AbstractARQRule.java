package fr.gouv.agriculture.dal.sial.arq.business.rule;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.orion.business.rule.AbstractBusinessRule;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import java.math.BigDecimal;

/**
 * Regles communes
 * @author sopra
 *
 */
public abstract class AbstractARQRule extends AbstractBusinessRule{

	/** serial */
	private static final long serialVersionUID = -3969261681820435005L;

	
	/**
	 * Test sur le poids d'une pondération
	 * @param report rapport d'erreur
	 * @param poids poids à tester
	 * @return rapport d'erreur
	 */
	public RuleReport controlPoids(RuleReport report, byte poids) {

		if(poids <= 0){
			report.addMessage(new RuleMessage(Messages.getMessage("ponderation.rule.poids.err")));
		}

		return report;
	}
        
	/**
	 * Test sur le poids d'une pondération
	 * @param report rapport d'erreur
	 * @param poids poids à tester
	 * @return rapport d'erreur
	 */
	public RuleReport controlPoids(RuleReport report, BigDecimal poids) {

                if (poids == null) {
                    report.addMessage(new RuleMessage(Messages.getMessage("ponderation.rule.poids.err")));
                } else {
                    int result = poids.compareTo(BigDecimal.ZERO);
                    if(result <= 0){
                            report.addMessage(new RuleMessage(Messages.getMessage("ponderation.rule.poids.err")));
                    }
                }
		return report;
	}
	
	/**
	 * Test sur le poids d'une pondération (decimal positif non nul)
	 * @param report rapport d'erreur
	 * @param poids poids à tester
	 * @return rapport d'erreur
	 */
	public RuleReport controlPoidsPositif(RuleReport report, BigDecimal poids) {

                if (poids == null) {
                    report.addMessage(new RuleMessage(Messages.getMessage("ponderation.rule.poids.positif.err")));
                } else {
                    int result = poids.compareTo(BigDecimal.ZERO);
                    if(result < 0){
                            report.addMessage(new RuleMessage(Messages.getMessage("ponderation.rule.poids.positif.err")));
                    }
                }
		return report;
	}
}
