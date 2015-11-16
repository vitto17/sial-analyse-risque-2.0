/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VRefApprobationQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;

/**
 * Permet de contextualiser le guide des Approbation de la page risque théorique
 * par le secteur d'activité (évolution de la SFD).
 *
 * @author pegaltier
 */
public class NomenclaturesRisqueTheoriqueApprobationShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    @Inject(value = "vRefApprobationQFC")
    private VRefApprobationQueryFormController queryFormController;

    @Override
    public void setContext(Object object) {
        PonderationRisqueTheorique ponderationRisqueTheorique = (PonderationRisqueTheorique) object;
        queryFormController.setRememberQuery(false);
        queryFormController.setConsoDomaineTechnique(null);
        getListController().setLiensFige(true);
        FormuleRisque formuleRisque = ponderationRisqueTheorique.getFormuleRisque();
        if (formuleRisque != null) {
        	queryFormController.setConsoDomaineTechnique(formuleRisque.getDomaineTechnique());
        }
    }
}
