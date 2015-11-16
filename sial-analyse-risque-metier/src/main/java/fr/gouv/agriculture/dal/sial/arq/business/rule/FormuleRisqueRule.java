/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDiffusion;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VZone;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.business.rule.AbstractBusinessRule;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Contrôle les règles de gestions à l'enregistrement pour FormuleRisque.
 *
 * @author pegaltier
 */
public class FormuleRisqueRule extends AbstractBusinessRule {

	/**
     * serial.
     */
    private static final long serialVersionUID = 1L;
	
    /**
     * Nom du champ Campagne.
     */
    private static final String FIELD_NAME_CAMPAGNE = "Campagne";
    
    /**
     * Nom du champ Domaine technique.
     */
    private static final String FIELD_NAME_DT = "Domaine technique";
	
    /**
     * Injection du DAO utilisé pour récupérer les informations.
     */
    @Inject(value = "fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO")
    private FormuleRisqueDAO formuleRisqueDAO;
    
    /**
     * Injection du DAO générique.
     */
    @Inject(value="fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO")
    private BusinessDAO defaultBusinessDAO;
    
    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();
        boolean uniciteIsOk = false;
        if (object != null) {
            FormuleRisque formuleRisque = (FormuleRisque) object;

            // Controle des champs obligatoires
            VCampagne campagne = formuleRisque.getCampagne();
            VDomaineTechnique domaineTechnique = formuleRisque.getDomaineTechnique();
            if (campagne == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{FIELD_NAME_CAMPAGNE})));
            }
            if (domaineTechnique == null) {
                report.addMessage(new RuleMessage(Messages.getMessage("common.rule.champ.obligatoire",
                        new Object[]{FIELD_NAME_DT})));
            }else{
            	//Sert à charger le lazy pour eviter une lazyexception lors de l'appel d'un guide suite à une RuleException
            	formuleRisque.getDomaineTechnique().getCtxDtSas().size();
            }

            // ARQ_008_Règles_ControleUnicitéFormule
			if (campagne != null && domaineTechnique != null) {
				try {
					uniciteIsOk = formuleRisqueDAO
							.controleUniciteFormule(formuleRisque);
				} catch (Exception exep) {
					Logger.getLogger(FormuleRisqueRule.class.getName()).log(
							Level.SEVERE, null, exep);
				}
				if (!uniciteIsOk) {
					report.addMessage(new RuleMessage(
							Messages.getMessage("form.formuleRisque.controle.unicite.rule")));
				}
			}
            // ARQ_009_Règles_ControleAnnée
            if (campagne != null) {
            	
            	try {
					defaultBusinessDAO.refresh(campagne);
				} catch (Exception exep) {
					Logger.getLogger(FormuleRisqueRule.class.getName()).log(Level.SEVERE, null, exep);
				}
            	
                Date dateDuJour = new Date();
                if (campagne.getCampFinDt() != null && campagne.getCampFinDt().before(dateDuJour)) {
                    report.addMessage(new RuleMessage(Messages.getMessage("form.formuleRisque.controle.annee.rule")));
                }
            }

            // Vérifie ARQ_018_Règles_ContrôleUnicitéPondérationDestination
            controleUnicitePonderationDestination(report, formuleRisque.getPonderationDestinations());

            // Vérifie ARQ_016_Règles_ContrôleUnicitéPondérationVolume
            controleUnicitePonderationVolume(report, formuleRisque.getPonderationVolumes());

            // Vérifie ARQ_017_Règles_ContrôleUnicitéPondérationDiffusion
            controleUnicitePonderationDiffusion(report, formuleRisque.getPonderationDiffusions());

            // Vérifie ARQ_014_Règles_ContrôleUnicitéPondérationZone
            controleUnicitePonderationZone(report, formuleRisque.getPonderationZones());

        }
        return report;
    }

    /**
     * Vérifie ARQ_018_Règles_ContrôleUnicitéPondérationDestination
     *
     * @param report
     * @param ponderationDestinations
     */
    private void controleUnicitePonderationDestination(RuleReport report, List<PonderationDestination> ponderations) {
        VDestination destination1, destination2;
        VTypeActivite typeActivite1, typeActivite2;
        Map<String, PonderationARQ> map = new HashMap<String, PonderationARQ>();

        if (ponderations != null) {
            for (PonderationDestination iter : ponderations) {
                destination1 = iter.getDestRfa();
                typeActivite1 = iter.getTaRfa();
                for (PonderationDestination ponderation : ponderations) {
                    destination2 = ponderation.getDestRfa();
                    typeActivite2 = ponderation.getTaRfa();
                    if (iter != ponderation && destination1 != null && typeActivite1 != null && destination2 != null
                            && typeActivite2 != null) {
                        if (destination2.getIdentifier().equals(destination1.getIdentifier())
                                && typeActivite2.getIdentifier().equals(typeActivite1.getIdentifier())) {
                            if (!map.containsKey(typeActivite1.getIdentifier() + "-" + destination1.getIdentifier())) {
                                report.addMessage(new RuleMessage(Messages.getMessage(
                                        "PonderationDestination.rule.duplication.err",
                                        new Object[]{typeActivite1.getTaLb(), destination1.getDestLb()})));
                                map.put(typeActivite1.getIdentifier() + "-" + destination1.getIdentifier(), iter);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Vérifie ARQ_016_Règles_ContrôleUnicitéPondérationVolume
     *
     * @param report
     * @param ponderation
     */
    private void controleUnicitePonderationVolume(RuleReport report, List<PonderationVolume> ponderations) {
        VUniteProduction uniteProduction1, uniteProduction2;
        VTypeActivite typeActivite1, typeActivite2;
        Map<String, PonderationARQ> map = new HashMap<String, PonderationARQ>();

        if (ponderations != null) {
            for (PonderationVolume iter : ponderations) {
                uniteProduction1 = iter.getUprodRfa();
                typeActivite1 = iter.getTaRfa();
                for (PonderationVolume ponderation : ponderations) {
                    uniteProduction2 = ponderation.getUprodRfa();
                    typeActivite2 = ponderation.getTaRfa();
                    if (iter != ponderation && uniteProduction1 != null && typeActivite1 != null
                            && uniteProduction2 != null && typeActivite2 != null) {
                        if (uniteProduction2.getIdentifier().equals(uniteProduction1.getIdentifier())
                                && typeActivite2.getIdentifier().equals(typeActivite1.getIdentifier())) {
                            if (!map.containsKey(typeActivite1.getIdentifier() + "-" + uniteProduction1.getIdentifier())) {
                                report.addMessage(new RuleMessage(Messages.getMessage(
                                        "ponderationVolume.rule.duplication.err",
                                        new Object[]{typeActivite1.getTaLb(), uniteProduction1.getUprodLb()})));
                                map.put(typeActivite1.getIdentifier() + "-" + uniteProduction1.getIdentifier(), iter);

                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Vérifie ARQ_017_Règles_ContrôleUnicitéPondérationDiffusion
     *
     * @param report
     * @param ponderations
     */
    private void controleUnicitePonderationDiffusion(RuleReport report, List<PonderationDiffusion> ponderations) {
        VDiffusion diffusion1, diffusion2;
        Map<String, PonderationARQ> map = new HashMap<String, PonderationARQ>();

        if (ponderations != null) {
            for (PonderationDiffusion iter : ponderations) {
                diffusion1 = iter.getDifRfa();
                for (PonderationDiffusion ponderation : ponderations) {
                    diffusion2 = ponderation.getDifRfa();
                    if (iter != ponderation && diffusion2 != null && diffusion1 != null) {
                        if (diffusion2.getIdentifier().equals(diffusion1.getIdentifier())) {
                            if (!map.containsKey(diffusion1.getIdentifier().toString())) {
                                report.addMessage(new RuleMessage(Messages.getMessage(
                                        "PonderationDiffusion.rule.duplication.err",
                                        new Object[]{diffusion1.getDifLb()})));
                                map.put(diffusion1.getIdentifier().toString(), iter);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Vérifie ARQ_014_Règles_ContrôleUnicitéPondérationZone
     *
     * @param report
     * @param ponderation
     */
    private void controleUnicitePonderationZone(RuleReport report, List<PonderationZone> ponderations) {
        VZone zone1, zone2;
        Map<String, PonderationARQ> map = new HashMap<String, PonderationARQ>();

        if (ponderations != null) {
            for (PonderationZone iter : ponderations) {
                zone1 = iter.getZone();
                for (PonderationZone ponderation : ponderations) {
                    zone2 = ponderation.getZone();
                    if (iter != ponderation && zone1 != null && zone2 != null) {
                        if (zone2.getIdentifier().equals(zone1.getIdentifier())) {
                            if (!map.containsKey(zone1.getIdentifier().toString())) {
                                report.addMessage(new RuleMessage(Messages.getMessage(
                                        "PonderationZone.rule.duplication.err", new Object[]{zone1.getTypeZoneLb(),
                                            zone1.getZlb()})));
                                map.put(zone1.getIdentifier().toString(), iter);
                            }
                        }
                    }
                }
            }
        }
    }
}
