/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDiffusion;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import java.util.List;

/**
 * Contrôle les règles de gestions à l'enregistrement pour PonderationDiffusion.
 *
 * @author pegaltier
 */
public class PonderationDiffusionRule extends AbstractARQRule {

    /**
     * serial.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();

        if (object != null) {
            PonderationDiffusion ponderation = (PonderationDiffusion) object;
            VDiffusion diffusion = ponderation.getDifRfa();

            // Vérifie que diffusion est renseigné
            if (diffusion == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("PonderationDiffusion.difRfa.difLb.shortLabel")})));
            }
            
            // Règles_Trans_008_ValeurPoids
            controlPoids(report, ponderation.getPdiffPoidsNb2());
        }

        return report;
    }
}
