/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import java.math.BigDecimal;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Contrôle les règles de gestions à l'enregistrement pour PonderationVolume.
 * 
 * @author pegaltier
 */
public class PonderationVolumeRule extends AbstractARQRule {

    /**
     * serial.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();

        if (object != null) {
            PonderationVolume ponderation = (PonderationVolume) object;
            VUniteProduction uniteProduction = ponderation.getUprodRfa();
            VTypeActivite typeActivite = ponderation.getTaRfa();
                        
            // Vérifie que uniteProduction est renseigné
            if (uniteProduction == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("PonderationVolume.uprodRfa.uprodLb.shortLabel")})));
            }
            // Vérifie que typeActivite est renseigné
            if (typeActivite == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("PonderationVolume.taRfa.taLb.shortLabel")})));
            }
             
            // Vérifie ARQ_015_Règles_ContrôleCohérenceSeuil
            BigDecimal seuil1 = ponderation.getPvolS1Nb2();
            BigDecimal seuil2 = ponderation.getPvolS2Nb2();
            BigDecimal seuil3 = ponderation.getPvolS3Nb2();
            BigDecimal seuil4 = ponderation.getPvolS4Nb2();
            
            // Controler seuil 1 non null et décimal positif
            controlPoidsPositif(report, seuil1);
            
            // Controler si renseigné seuil 2, seuil 3 et seuil 4 décimal positif 
            if (seuil2 != null) {
            	controlPoidsPositif(report, seuil2);
            }
            if (seuil3 != null) {
            	controlPoidsPositif(report, seuil3);
            }
            if (seuil4 != null) {
            	controlPoidsPositif(report, seuil4);
            }
            
            boolean erreurSeuil=false;
            // Le seuil n°1 est renseigné et est inférieur ou égal au seuil n°2
            if (seuil1 != null && seuil2 != null) {
                int resultTest1 = seuil1.compareTo(seuil2);
				if (!(resultTest1 <= 0)) {
                    erreurSeuil=true;
                }
            }
            // Le seuil n°2 est strictement inférieur au seuil n°3
            if (seuil2 != null && seuil3 != null) {
                int resultTest2 = seuil2.compareTo(seuil3);
				if (!(resultTest2 < 0)) {
                    erreurSeuil=true;
                }
            }
            // Le seuil n°3 est strictement inférieur au seuil n°4
            if (seuil3 != null && seuil4 != null) {
                int resultTest3 = seuil3.compareTo(seuil4);
				if (!(resultTest3 < 0)) {
                    erreurSeuil=true;
                }
            }
            
            if (erreurSeuil) {
                String typeActiviteLb = "-";
                String uniteproductionLb = "-";
                if (typeActivite != null) {
                    typeActiviteLb = typeActivite.getTaLb();
                }
                if (uniteProduction != null) {
                    uniteproductionLb = uniteProduction.getUprodLb();
                }
                report.addMessage(new RuleMessage(Messages.getMessage("ponderationVolume.rule.seuil.err",
                            new Object[]{typeActiviteLb, uniteproductionLb})));
            }
        }

        return report;
    }
}