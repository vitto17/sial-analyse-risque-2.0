/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VFiliereVegetalQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.form.FormController;

/**
 * Permet de contextualiser le guide des filière végétales de la page risque
 * théorique par le type d'activité.
 *
 * @author pegaltier
 */
public class NomenclaturesRisqueTheoriqueFiliereVegetaleShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    @Inject(value = "vFiliereVegetalQFC")
    private VFiliereVegetalQueryFormController queryFormController;

    @Override
    public void setContext(Object object) {
        PonderationRisqueTheorique ponderation = (PonderationRisqueTheorique) object;
        VTypeActivite typeActivite = ponderation.getTypeActivite();
        queryFormController.setRememberQuery(false);
        if (typeActivite != null) {
            queryFormController.setContextTaComplete(typeActivite.getTaComplete());
        }
    }
}
