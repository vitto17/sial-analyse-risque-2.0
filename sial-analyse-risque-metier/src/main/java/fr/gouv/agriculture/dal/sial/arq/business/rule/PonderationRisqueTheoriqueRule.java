/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.PonderationRisqueTheoriqueWithoutTransientComparator;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Contrôle les règles de gestions à l'enregistrement pour
 * PonderationRisqueTheorique
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueRule extends AbstractARQRule {
   
    
    /**serial*/
	private static final long serialVersionUID = 5482929649491299015L;


	@Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();

        if (object != null) {
            PonderationRisqueTheorique ponderation = (PonderationRisqueTheorique) object;
            VTypeActivite typeActivite = ponderation.getTypeActivite();

            // Vérifie que typeActivite est renseigné
            if (typeActivite == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{Messages.getMessage("PonderationDestination.taRfa.taLb.shortLabel")})));
            }
            
            // Vérifie Règles_Trans_008_ValeurPoids 
            controlPoids(report, ponderation.getPrisqtheoPoidsNb2());
            
            // Vérifie ARQ_012_Règles_ContrôleUnicitéPondérationRi
            try {
                controleUnicitePonderationRisqueTheorique(report, ponderation);
            } catch (Exception ex) {
                Logger.getLogger(PonderationRisqueTheoriqueRule.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        return report;
    }
    

    /**
     * Vérifie ARQ_012_Règles_ContrôleUnicitéPondérationRi
     *
     * @param report
     * @param ponderationRisqueTheoriques
     */
    private void controleUnicitePonderationRisqueTheorique(RuleReport report,  PonderationRisqueTheorique ponderationInit) {
        //VUniteProduction uniteProduction1, uniteProduction2;
    	List<PonderationRisqueTheorique> ponderations = ponderationInit.getFormuleRisque().getPonderationRisqueTheoriques();
    	VTypeActivite typeActivite1, typeActivite2;
        Map<String, PonderationARQ> map = new HashMap<String, PonderationARQ>();
        PonderationRisqueTheoriqueWithoutTransientComparator comparator = new PonderationRisqueTheoriqueWithoutTransientComparator();
        
        if (ponderations != null) {
            	typeActivite1 = ponderationInit.getTypeActivite();
                for (PonderationRisqueTheorique ponderation : ponderations) {
                    typeActivite2 = ponderation.getTypeActivite();
                    if (ponderationInit != ponderation && typeActivite1 != null && typeActivite2 != null) {
                    	if(comparator.compare(ponderationInit,ponderation)==0){
                            if (!map.containsKey(typeActivite1.getIdentifier() + "-" + ponderationInit.getPonderationRisqueTheoriqueApprobationsStr() + "-" + ponderationInit.getPonderationRisqueTheoriqueProcedesStr() + "-" + ponderationInit.getPonderationRisqueTheoriqueProduitsStr())) {
                                report.addMessage(new RuleMessage(Messages.getMessage(
                                        "PonderationRisqueTheorique.rule.duplication.err",
                                        new Object[]{typeActivite1.getTaLb(), ponderationInit.getPonderationRisqueTheoriqueApprobationsStr(), ponderationInit.getPonderationRisqueTheoriqueProcedesStr(), ponderationInit.getPonderationRisqueTheoriqueProduitsStr()})));
                                map.put(typeActivite1.getIdentifier() + "-" + ponderationInit.getPonderationRisqueTheoriqueApprobationsStr() + "-" + ponderationInit.getPonderationRisqueTheoriqueProcedesStr() + "-" + ponderationInit.getPonderationRisqueTheoriqueProduitsStr(), ponderationInit);

                            }
                        }
                    }
                }
        }
    }
}
