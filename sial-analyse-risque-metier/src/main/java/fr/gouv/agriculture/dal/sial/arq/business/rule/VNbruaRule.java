package fr.gouv.agriculture.dal.sial.arq.business.rule;

import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Contrôle les règles de gestions des VNbrua
 *
 * @author fjperez
 */
public class VNbruaRule extends AbstractARQRule {

    /**
     * serial.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();

        if (object != null) {
            VNbrua vnbrua = (VNbrua) object;
            VCampagne campagne = vnbrua.getCampagne();
            VDomaineTechnique domaineTechnique = vnbrua.getDomaineTechnique();
            
            // Vérifie que destination est renseigné
            if (campagne == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("CalculEtResultats.campagne.shortLabel")})));
            }
            // Vérifie que typeActivite est renseigné
            if (domaineTechnique == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("CalculEtResultats.domaineTechnique.shortLabel")})));
            }

        }

        return report;
    }
}
