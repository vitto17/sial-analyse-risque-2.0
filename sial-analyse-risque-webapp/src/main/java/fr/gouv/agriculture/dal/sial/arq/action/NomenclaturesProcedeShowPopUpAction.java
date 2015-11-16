/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VProcedeQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;

/**
 * Permet de contextualiser le guide des procede par le type d'activité.
 *
 * @author pegaltier
 */
public class NomenclaturesProcedeShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    @Inject(value = "vProcedeQFC")
    private VProcedeQueryFormController queryFormController;

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
