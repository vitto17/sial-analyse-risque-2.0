/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VDenreeQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.form.FormController;

/**
 * Permet de contextualiser le guide des denres de la page risque théorique par
 * le type d'activité.
 *
 * @author pegaltier
 */
public class NomenclaturesRisqueTheoriqueDenreeShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    @Inject(value = "vDenreeQFC")
    private VDenreeQueryFormController queryFormController;
    
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
