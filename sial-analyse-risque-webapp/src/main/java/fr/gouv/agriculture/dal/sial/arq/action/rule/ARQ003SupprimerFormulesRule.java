package fr.gouv.agriculture.dal.sial.arq.action.rule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.orion.business.rule.AbstractBusinessRule;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Rule de suppression des formules de risques
 * @author pbarreau
 *
 */
public class ARQ003SupprimerFormulesRule extends AbstractBusinessRule {

	/**serial*/
	private static final long serialVersionUID = -2117200995139674493L;

	/** Nom de la Rule */
	private static final String RULE_NAME = "ARQ003SupprimerFormulesRule"; 
	
    /**
     * Constructeur
     */
    public ARQ003SupprimerFormulesRule() {
        super();
        setName(RULE_NAME);
    }

    @SuppressWarnings("unchecked")
    @Override
    public RuleReport validate(Object arg0) {
        List<FormuleRisque> selection = (List<FormuleRisque>) arg0;
        RuleReport ruleReport = new RuleReport();
        if (selection.isEmpty()) {
            RuleMessage ruleMessage = new RuleMessage(Messages.getMessage("message.Messages.emptySelectionError"));
            ruleReport.addMessage(ruleMessage);
        } else {
            for (FormuleRisque formule : selection) {
                if (isCampagneEchue(formule)) {
                    RuleMessage ruleMessage = new RuleMessage(Messages.getMessage(
                            "FormuleRisque.rule.suppr.campagne.echue.err", new String[] {
                                    formule.getCampagne().getCampRfa(), formule.getDomaineTechnique().getDtLb() }));
                    ruleReport.addMessage(ruleMessage);
                }
            }
        }
        return ruleReport;
    }

    /**
     * Permet de vérifier si la campagne de la formule de risque est échue ou
     * pas.
     * 
     * @param formule
     *            La formule dont on veut vérifier si la campagne est échue.
     * 
     * @return true si la campagne est échue, false sinon
     */
    public boolean isCampagneEchue(FormuleRisque formule) {
        Boolean retour = Boolean.FALSE;

        VCampagne campagne = formule.getCampagne();
        if (campagne != null) {
            Date today = Calendar.getInstance().getTime();
            Date campFinDt = campagne.getCampFinDt();
            if (campFinDt != null) {
                if (DateUtils.truncatedCompareTo(today, campFinDt, Calendar.DAY_OF_MONTH) > 0) {
                    retour = Boolean.TRUE;
                } else {
                    retour = Boolean.FALSE;
                }
            } else {
                retour = Boolean.FALSE;
            }
        }

        return retour;
    }

}
