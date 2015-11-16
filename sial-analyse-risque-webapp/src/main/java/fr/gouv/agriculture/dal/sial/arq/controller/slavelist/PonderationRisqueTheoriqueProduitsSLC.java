/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.slavelist;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.arq.business.Produit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDenree;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VFiliereVegetal;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeProduit;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.controller.AssignmentDatas;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.rule.RuleException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Controller de la slavelist des PonderationRisqueTheoriqueProduits permet de
 * faire des assign custom poit les denrees et les filieres vegetales.
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueProduitsSLC extends SlaveListController {

    /**
     * The default business dao.
     */
    @Inject
    private BusinessDAO businessDao;

    @SuppressWarnings("unchecked")
    @Override
    protected void assign(AssignmentDatas assignmentDatas) throws Exception {

        final FormController formController = (FormController) getContainer();
        Collection<?> selection = assignmentDatas.getSelection();

        if (!CommonHelper.isEmpty(selection)) {

            PonderationRisqueTheorique ponderationRisqueTheorique = (PonderationRisqueTheorique) getCurrentParentObject();
            VTypeProduit typeProduit = ponderationRisqueTheorique.getTypeActivite().getTypeProduit();
            BusinessContext businessContext = createBusinessContext(BusinessContext.class);

            // Retour du guide denree
            if (assignmentDatas.getTargetProperty().equals("produit.denre.denRfa")) {
                Collection<VDenree> selectionDenree = (Collection<VDenree>) selection;
                if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits() == null) {
                    ponderationRisqueTheorique.setPonderationRisqueTheoriqueProduits(new ArrayList<PonderationRisqueTheoriqueProduit>());
                }
                for (VDenree denree : selectionDenree) {

                    // Règles_Trans_006_ComportementRetourGuideMultiSelection
                    if (!ponderationRisqueTheorique.isPonderationRisqueTheoriqueProduitsContainsDenree(denree)) {
                        PonderationRisqueTheoriqueProduit ponderation = (PonderationRisqueTheoriqueProduit) businessDao
                                .create(businessContext, PonderationRisqueTheoriqueProduit.class);
                        Produit produit = (Produit) businessDao.create(businessContext, Produit.class);
                        produit.setDenre(denree);
                        produit.setProdRfa(denree.getDenRfa());
                        produit.setTypeProduit(typeProduit);
    //                    produit.setProdLb("FIXME");
                        ponderation.setProduit(produit);
                        ponderation.setPonderationRisqueTheorique(ponderationRisqueTheorique);

                        ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits().add(ponderation);
                    }
                }
            }

            // Retour du guide filiere vegetale
            if (assignmentDatas.getTargetProperty().equals("produit.veget.filvegRfa")) {
                Collection<VFiliereVegetal> selectionFiliereVegetale = (Collection<VFiliereVegetal>) selection;
                if (ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits() == null) {
                    ponderationRisqueTheorique.setPonderationRisqueTheoriqueProduits(new ArrayList<PonderationRisqueTheoriqueProduit>());
                }
                for (VFiliereVegetal filiereVegetale : selectionFiliereVegetale) {

                    // Règles_Trans_006_ComportementRetourGuideMultiSelection
                    if (!ponderationRisqueTheorique.isPonderationRisqueTheoriqueProduitsContainsFilVeg(filiereVegetale)) {
                        PonderationRisqueTheoriqueProduit ponderation = (PonderationRisqueTheoriqueProduit) businessDao
                                .create(businessContext, PonderationRisqueTheoriqueProduit.class);
                        Produit produit = (Produit) businessDao.create(businessContext, Produit.class);
                        produit.setVeget(filiereVegetale);
                        produit.setProdRfa(filiereVegetale.getFilvegRfa());
                        produit.setTypeProduit(typeProduit);
    //                    produit.setProdLb("FIXME");
                        ponderation.setProduit(produit);
                        ponderation.setPonderationRisqueTheorique(ponderationRisqueTheorique);

                        ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits().add(ponderation);
                    }
                }
            }

            getView().setModified(true);
            formController.getView().setModified(true);
            
        } else {
            RuleReport ruleReport = new RuleReport();
            RuleMessage ruleMessage = new RuleMessage(Messages.getMessage("guide.selection.empty.err"));
            ruleReport.addMessage(ruleMessage);
            throw new RuleException(ruleReport);
        }
    }
}
