/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.ModificationPonderation;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.PonderationRiThRfaSLComparator;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.PonderationRisqueTheoriqueComparator;
import fr.gouv.agriculture.dal.sial.arq.constante.TypePonderation;
import fr.gouv.agriculture.dal.sial.arq.constante.TypeProduit;
import fr.gouv.agriculture.dal.sial.arq.controller.combobox.ProduitAlaniComboBoxController;
import fr.gouv.agriculture.dal.sial.arq.controller.combobox.ProduitAnimaComboBoxController;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.arq.service.PonderationRisqueTheoriqueService;
import fr.gouv.agriculture.dal.sial.arq.util.ValidatorUtils;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.AssignmentDatas;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.event.ControllerEvent;
import fr.gouv.agriculture.orion.event.Events;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.RuleException;

/**
 * Form Controlleur de Ponderation Risque Theorique.
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueFormController extends FormController {

    /**
     * Injections
     */
    @Inject(value = "produitAlaniCBC")
    ProduitAlaniComboBoxController produitAlaniCBC;
    @Inject(value = "produitAnimaCBC")
    ProduitAnimaComboBoxController produitAnimaCBC;
    @Inject
    private HabilitationsService habilitationsService;
    @Inject
    private BusinessDAO dao;
    /**
     * Service de gestion des ponderations theorique
     */
    @Inject
    private PonderationRisqueTheoriqueService ponderationRisqueTheoriqueService;
    /**
     * Type de ponderation
     */
    private String typePonderation = TypePonderation.TYPE_POND_RISQUE_THEO;
    FormuleRisque formuleRisque;

    @Override
    protected void initialize() throws Exception {
        super.initialize();

        // Charge l'objet du formulaire
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();

        // Charge a nouveau la formule risque (car changement de session lors de la transition de la page)
        // Provoque un bug pour contextualiser le type d'activité  dans : NomenclaturesRisqueTheoriqueTypeActiviteShowPopUpAction
        // failed to lazily initialize a collection of role: 
        // fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique.ctxDtSas, no session or session was closed
        try {
            formuleRisque = (FormuleRisque) dao.load(FormuleRisque.class, ponderationRisqueTheorique.getFormuleRisque().getIdentifier());
            ponderationRisqueTheorique.setFormuleRisque(formuleRisque);
        } catch (Exception ex) {
            Logger.getLogger(PonderationRisqueTheoriqueFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // On rajoute ici la ponderation risque théorique car apres le dao.load elle a disparu 
        // étant donné qu'en mode (Création et Duplication) elle n'etait pas encore persisté
        if (!formuleRisque.getPonderationRisqueTheoriques().contains(ponderationRisqueTheorique)) {
            formuleRisque.getPonderationRisqueTheoriques().add(ponderationRisqueTheorique);
        }

        // Initialise la contextualisation sur les combobox de la slavelist produit
        produitAlaniCBC.setTypeActivite(ponderationRisqueTheorique.getTypeActivite());
        produitAnimaCBC.setTypeActivite(ponderationRisqueTheorique.getTypeActivite());

        // Initialise les objets transiant dans la slavelist produit
        List<String> listProduitsRfa = ponderationRisqueTheorique.initListProduitRfa();

        if (!listProduitsRfa.isEmpty()) {
            initPonderationRisqueTheoriqueProduit(ponderationRisqueTheorique, listProduitsRfa);
        }

        // Tri des slavelists
        if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations() != null) {
            Collections.sort(ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations(), new PonderationRiThRfaSLComparator());
        }
        if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes() != null) {
            Collections.sort(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes(), new PonderationRiThRfaSLComparator());
        }
        if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits() != null) {
            Collections.sort(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), new PonderationRiThRfaSLComparator());
        }

    }

    /**
     * Surcharge de la methode save
     */
    @Override
    protected void save() throws Exception {

        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        // On valide les slaveList pour éviter le probleme en mode DUPLICATION
        // Lors qu'on declenche une rule lors de l'enregistrement et quon corrige et qu'on reenregistre
        // ERREUR : failed to lazily initialize a collection of role: 
        //fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque.modificationPonderations, no session or session was closed
        RuleReport ruleReport = ValidatorUtils.validateObject(ponderationRisqueTheorique);
        if (!ruleReport.isEmpty()) {
            throw new RuleException(ruleReport);

        } else {

            if (getFormView().isModified()) {
                                        
                savePonderationRisqueTheoriqueProduit(ponderationRisqueTheorique);

                ponderationRisqueTheorique.checkPonderationRisqueTheoriqueProduitsContainsDoublon();
                                
                // Tri des slavelist de la slavelist en premier
                Collections.sort(ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations(), new PonderationRiThRfaSLComparator());
                Collections.sort(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes(), new PonderationRiThRfaSLComparator());
                Collections.sort(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), new PonderationRiThRfaSLComparator());
                // Tri de la slavelist parente ensuite
                Collections.sort(formuleRisque.getPonderationRisqueTheoriques(), new PonderationRisqueTheoriqueComparator());

                // Suivi des modifications (historique date et user)
                customSave();
            }

            // Save
            super.save();
        }
    }

    /**
     * Gestion du suivi de modification
     */
    protected void customSave() {

        List<ModificationPonderation> modificationsToute = formuleRisque.getModificationPonderations();
        if (modificationsToute == null) {
            formuleRisque.setModificationPonderations(new ArrayList<ModificationPonderation>());
        }

        List<ModificationPonderation> modifications = formuleRisque
                .getModificationPonderationsByType(typePonderation);

        ModificationPonderation currentMod;

        //Cas de la creation
        if (modifications.isEmpty()) {
            modifications = new ArrayList<ModificationPonderation>();

            currentMod = new ModificationPonderation();
            currentMod.setFormuleRisque(formuleRisque);
            currentMod.setModpodCreaTs(new Date());
            currentMod.setModpodTypepondLb(typePonderation);
            currentMod.setModpodUtiCreaLb(getCurrentUserLb());
            formuleRisque.getModificationPonderations().add(currentMod);
        } else {

            currentMod = modifications.iterator().next();
            //Cas de re-création
            if (StringUtils.isBlank(currentMod.getModpodUtiCreaLb())) {
                currentMod.setModpodCreaTs(new Date());
                currentMod.setModpodUtiCreaLb(getCurrentUserLb());

            } else { //Cas de modification
                currentMod.setModpodModTs(new Date());
                currentMod.setModpodUtiModLb(getCurrentUserLb());
            }
        }

    }

    /**
     * Recuperation de l'utilisateur courant.
     *
     * @return libellé de l'utilisateur courant
     */
    public String getCurrentUserLb() {
        return habilitationsService.getUtilisateurConnecte().getLogin();
    }

    @Override
    protected void assign(AssignmentDatas assignmentDatas) throws Exception {

        if (assignmentDatas.getTargetProperty().equals("typeActivite")) {
            VTypeActivite typeActivite = (VTypeActivite) assignmentDatas.getSelection().iterator().next();

            // Initialise la contextualisation sur les combobox de la slavelist produit
            produitAlaniCBC.setTypeActivite(typeActivite);
            produitAnimaCBC.setTypeActivite(typeActivite);

            produitAlaniCBC.processControllerEvent(new ControllerEvent(this, Events.DO_REFRESH, null));
            produitAnimaCBC.processControllerEvent(new ControllerEvent(this, Events.DO_REFRESH, null));

        }

        super.assign(assignmentDatas);
    }

    /**
     * Retourne l'objet metier du Form Controller.
     *
     * @return
     */
    public PonderationRisqueTheorique getPonderationRisqueTheorique() {
        PonderationRisqueTheorique ponderationRisqueTheorique = (PonderationRisqueTheorique) getFormModel()
                .getObject();

        return ponderationRisqueTheorique;
    }

    /**
     * Permet de desactiver la saisie du type d'activité si un des tableau «
     * Approbation » ou « Procédé » ou « Produits » est renseigné.
     *
     * @return true si le type d'activite est saisissable, false sinon
     */
    public boolean isTypeActiviteSaisissable() {
        boolean retour = true;
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations().size() > 0
                || ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes().size() > 0
                || ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits().size() > 0) {
            retour = false;
        }

        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est ANIMA.
     *
     * @return true si le type produit est ANIM, false sinon
     */
    public boolean isProduitAnima() {
        boolean retour = false;
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        if (ponderationRisqueTheorique != null) {
            VTypeActivite typeActivite = ponderationRisqueTheorique.getTypeActivite();
            if (typeActivite != null && TypeProduit.TYPE_PROD_ANIMA.equals(typeActivite.getTypeProduit().getTprodRfa())) {
                retour = true;
            }
        }

        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est VEGET.
     *
     * @return true si le type produit est VEGET, false sinon
     */
    public boolean isProduitVeget() {
        boolean retour = false;
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        if (ponderationRisqueTheorique != null) {
            VTypeActivite typeActivite = ponderationRisqueTheorique.getTypeActivite();
            if (typeActivite != null && TypeProduit.TYPE_PROD_VEGET.equals(typeActivite.getTypeProduit().getTprodRfa())) {
                retour = true;
            }
        }

        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est DENRE.
     *
     * @return true si le type produit est DENRE, false sinon
     */
    public boolean isProduitDenre() {
        boolean retour = false;
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        if (ponderationRisqueTheorique != null) {
            VTypeActivite typeActivite = ponderationRisqueTheorique.getTypeActivite();
            if (typeActivite != null && TypeProduit.TYPE_PROD_DENRE.equals(typeActivite.getTypeProduit().getTprodRfa())) {
                retour = true;
            }
        }

        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est INTRA.
     *
     * @return true si le type produit est INTRA, false sinon
     */
    public boolean isProduitIntra() {
        boolean retour = false;
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        if (ponderationRisqueTheorique != null) {
            VTypeActivite typeActivite = ponderationRisqueTheorique.getTypeActivite();
            if (typeActivite != null && TypeProduit.TYPE_PROD_INTRA.equals(typeActivite.getTypeProduit().getTprodRfa())) {
                retour = true;
            }
        }

        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est ALANI.
     *
     * @return true si le type produit est ALANI, false sinon
     */
    public boolean isProduitAlani() {
        boolean retour = false;
        PonderationRisqueTheorique ponderationRisqueTheorique = getPonderationRisqueTheorique();
        if (ponderationRisqueTheorique != null) {
            VTypeActivite typeActivite = ponderationRisqueTheorique.getTypeActivite();
            if (typeActivite != null && TypeProduit.TYPE_PROD_ALANI.equals(typeActivite.getTypeProduit().getTprodRfa())) {
                retour = true;
            }
        }

        return retour;
    }

    /**
     * Fait appel au service pour initialiser les objets transiants.
     *
     * @param ponderationRisqueTheorique
     * @param listProduitsRfa
     * @throws Exception
     */
    private void initPonderationRisqueTheoriqueProduit(PonderationRisqueTheorique ponderationRisqueTheorique, List<String> listProduitsRfa) throws Exception {
        if (isProduitAnima()) {
            ponderationRisqueTheoriqueService.initPonderationRisqueTheoriqueProduitAnima(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), listProduitsRfa);
        } else if (isProduitVeget()) {
            ponderationRisqueTheoriqueService.initPonderationRisqueTheoriqueProduitVeget(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), listProduitsRfa);
        } else if (isProduitDenre()) {
            ponderationRisqueTheoriqueService.initPonderationRisqueTheoriqueProduitDenre(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), listProduitsRfa);
        } else if (isProduitIntra()) {
            ponderationRisqueTheoriqueService.initPonderationRisqueTheoriqueProduitIntra(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), listProduitsRfa);
        } else if (isProduitAlani()) {
            ponderationRisqueTheoriqueService.initPonderationRisqueTheoriqueProduitAlani(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits(), listProduitsRfa);
        }
    }

    /**
     * Fait appel au service pour sauvegarder le rfa a partir des objets
     * transiants.
     *
     * @param ponderationRisqueTheorique
     */
    private void savePonderationRisqueTheoriqueProduit(PonderationRisqueTheorique ponderationRisqueTheorique) {
        if (isProduitAnima()) {
            ponderationRisqueTheoriqueService.savePonderationRisqueTheoriqueProduitAnima(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits());
        } else if (isProduitVeget()) {
            ponderationRisqueTheoriqueService.savePonderationRisqueTheoriqueProduitVeget(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits());
        } else if (isProduitDenre()) {
            ponderationRisqueTheoriqueService.savePonderationRisqueTheoriqueProduitDenre(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits());
        } else if (isProduitIntra()) {
            ponderationRisqueTheoriqueService.savePonderationRisqueTheoriqueProduitIntra(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits());
        } else if (isProduitAlani()) {
            ponderationRisqueTheoriqueService.savePonderationRisqueTheoriqueProduitAlani(ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits());
        }
    }
}
