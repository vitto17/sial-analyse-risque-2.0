/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.combobox;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.dao.UniteProductionDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import fr.gouv.agriculture.orion.model.ListModel;
import fr.gouv.agriculture.orion.query.Order;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet de contextualiser l'unité de production par rapport au type d'activité
 * selectionné par l'utilisateur.
 *
 * @author pegaltier
 */
public class UniteProductionComboBoxController extends ListController {
    
    /**
     * Injection du DAO utilisé pour récupérer la liste.
     */
    @Inject(value = "fr.gouv.agriculture.dal.sial.arq.dao.UniteProductionDAO")
    private UniteProductionDAO uniteProductionDao;

    /**
     * {@inheritDoc}
     *
     * @see fr.gouv.agriculture.orion.controller.list.ListController
     * #getListModel()
     */
    @Override
    public ListModel getListModel() {
        SlaveListController container = (SlaveListController) getContainer();
        Collection selection = container.getSelection();
        // Cas ou cette méthode est appelée au moment le parcours de la liste
        // n'a pas encore commencé
        if (selection == null || selection.isEmpty()) {
            return super.getListModel();
        }
        PonderationVolume object = (PonderationVolume) selection.iterator().next();

        List<VUniteProduction> uniteProduction = null;
        if (object.getTaRfa() != null) {
        // On récupère la liste des orderBy pour garder un minimum de généricité
        List<Order> orderBy = getQuery().getOrders();
        try {
            uniteProduction = uniteProductionDao.findWithContextualisation(orderBy, object.getTaRfa());
        } catch (Exception ex) {
            Logger.getLogger(UniteProductionComboBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if (uniteProduction != null) {
            listModel.setObjects(uniteProduction);
        } else {
            listModel.setObjects(new ArrayList<VUniteProduction>());
        }

        return listModel;
    }
}
