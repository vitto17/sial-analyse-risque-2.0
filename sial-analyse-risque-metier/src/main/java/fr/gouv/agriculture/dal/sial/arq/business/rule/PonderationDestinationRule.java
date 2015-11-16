package fr.gouv.agriculture.dal.sial.arq.business.rule;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import java.util.List;

/**
 * Contrôle les règles de gestions à l'enregistrement pour
 * PonderationDestination.
 *
 * @author pegaltier
 */
public class PonderationDestinationRule extends AbstractARQRule {

    /**
     * serial.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();

        if (object != null) {
            PonderationDestination ponderation = (PonderationDestination) object;
            VDestination destination = ponderation.getDestRfa();
            VTypeActivite typeActivite = ponderation.getTaRfa();
            
            // Vérifie que destination est renseigné
            if (destination == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("PonderationDestination.destRfa.destLb.shortLabel")})));
            }
            // Vérifie que typeActivite est renseigné
            if (typeActivite == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("PonderationDestination.taRfa.taLb.shortLabel")})));
            }

            // Vérifie Règles_Trans_008_ValeurPoids 
            controlPoids(report, ponderation.getPdestPoidsNb2());
        }

        return report;
    }
}
