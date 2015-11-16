/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.form;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.ModificationPonderation;
import fr.gouv.agriculture.dal.sial.arq.constante.TypePonderation;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.arq.util.ValidatorUtils;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.RuleException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author pegaltier
 */
public class FormuleRisqueFC extends FormController {

    /**
     * Service de gestion de l'utilisateur
     */
    @Inject
    private HabilitationsService habilitationsService;
    
    /**
     * Etat des ponderations.
     */
    private boolean fromCritDestinationOn;
    private boolean fromCritDiffusionOn;
    private boolean fromCritRisquetheoriqueOn;
    private boolean fromCritVolumeOn;
    private boolean fromCritZoneOn;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void initialize() throws Exception {

        super.initialize();

        FormuleRisque formuleRisque = (FormuleRisque) getFormModel()
                .getObject();

        // Sauvegarde l'état (true/false des ponderations).
        savePonderationState(formuleRisque);
        
    }

    /**
     * Surcharge de la methode save
     */
    @Override
    protected void save() throws Exception {
        FormuleRisque formuleRisque = (FormuleRisque) getFormModel()
                .getObject();

        // On valide les slaveList pour éviter des problèmes hibernate et pour
        // avoir l'ensemble des messages d'erreurs.
        // les messages d'erreurs déclénchés sur les fils de la formuleRisque posent probleme car l'id
        // de la formule risque a était généré avant.
        RuleReport ruleReport = ValidatorUtils.validateObject(formuleRisque);
        //RuleReport ruleReport = formuleRisque.validate();

        if (!ruleReport.isEmpty()) {
            throw new RuleException(ruleReport);
        } else if (getFormView().isModified()) {
            customSave(formuleRisque);
            super.save();
        }
    }

    /**
     * Gestion du suivi de modification
     */
    protected void customSave(FormuleRisque formuleRisque) {

        //Cas de création
        if (StringUtils.isBlank(formuleRisque.getFromAuteurCreationLb())) {
            formuleRisque.setFormCreationDt(new Date());
            formuleRisque.setFromAuteurCreationLb(getCurrentUserLb());

        } else { //Cas de modification
            formuleRisque.setFormDerniereModifTs(new Date());
            formuleRisque.setFormAuteurModifLb(getCurrentUserLb());
        }

        // Dans tous les cas (création et modification)
        // Controler l'etat des ponderations afin d'initialiser leurs date
        checkPonderationState(formuleRisque);
    }

    /**
     * Recuperation de l'utilisateur courant.
     *
     * @return libellé de l'utilisateur courant
     */
    public String getCurrentUserLb() {
        return habilitationsService.getUtilisateurConnecte().getLogin();
    }

    /**
     * Sauvegarde l'état (true/false des ponderations). pour effectuer la rule
     * ARQ_006_Règles_AlimentationDateCréation et
     * ARQ_007_Règles_AlimentationDateModification
     *
     * @param formuleRisque
     */
    private void savePonderationState(FormuleRisque formuleRisque) {
        fromCritDestinationOn = formuleRisque.isFromCritDestinationOn();
        fromCritDiffusionOn = formuleRisque.isFromCritDiffusionOn();
        fromCritRisquetheoriqueOn = formuleRisque.isFromCritRisquetheoriqueOn();
        fromCritVolumeOn = formuleRisque.isFromCritVolumeOn();
        fromCritZoneOn = formuleRisque.isFromCritZoneOn();
    }

    /**
     * Controler l'etat des ponderations afin d'initialiser leurs dates de
     * création et de modification.
     *
     * @param formuleRisque
     */
    private void checkPonderationState(FormuleRisque formuleRisque) {

        // Si une pondération était a false lors du chargement et que l'utilisateur
        // a mis l'état a true alors on reinit la date de création 
        // si celle ci etait deja présente
        if (!fromCritDestinationOn && formuleRisque.isFromCritDestinationOn()) {
            reinitDatePonderation(formuleRisque, TypePonderation.TYPE_POND_DESTINATION);
        }
        if (!fromCritDiffusionOn && formuleRisque.isFromCritDiffusionOn()) {
            reinitDatePonderation(formuleRisque, TypePonderation.TYPE_POND_DIFFUSION);
        }
        if (!fromCritRisquetheoriqueOn && formuleRisque.isFromCritRisquetheoriqueOn()) {
            reinitDatePonderation(formuleRisque, TypePonderation.TYPE_POND_RISQUE_THEO);
        }
        if (!fromCritVolumeOn && formuleRisque.isFromCritVolumeOn()) {
            reinitDatePonderation(formuleRisque, TypePonderation.TYPE_POND_VOLUME);
        }
        if (!fromCritZoneOn && formuleRisque.isFromCritZoneOn()) {
            reinitDatePonderation(formuleRisque, TypePonderation.TYPE_POND_ZONE);
        }

    }

    /**
     * Reinititialiser la date de création et de modification si nécéssaire.
     *
     * @param formuleRisque
     */
    private void reinitDatePonderation(FormuleRisque formuleRisque, String typePonderation) {

        ModificationPonderation currentMod;
        List<ModificationPonderation> modifications = formuleRisque
                .getModificationPonderationsByType(typePonderation);
        if (!modifications.isEmpty()) {
            currentMod = modifications.iterator().next();

            // Si date de création deja présente alors on la reinitialise
            if (!StringUtils.isBlank(currentMod.getModpodUtiCreaLb())) {
                currentMod.setModpodCreaTs(new Date());
                currentMod.setModpodUtiCreaLb(getCurrentUserLb());
            }
            // Dans tout les cas on efface la date de modif
            currentMod.setModpodModTs(null);
            currentMod.setModpodUtiModLb(null);
        }
    }
}