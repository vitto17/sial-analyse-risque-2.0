/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VTypeActiviteQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;

/**
 * Permet de contextualiser le guide des type d'activité de la page ponderation
 * risque théorique par le secteur d'activité.
 *
 * @author pegaltier
 */
public class NomenclaturesRisqueTheoriqueTypeActiviteShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    @Inject(value = "vTypeActiviteQFC")
    private VTypeActiviteQueryFormController queryFormController;

    @Override
    public void setContext(Object object) {
        PonderationRisqueTheorique ponderationRisqueTheorique = (PonderationRisqueTheorique) object;
        queryFormController.setRememberQuery(false);
        queryFormController.setConsoSecteurActivite(null);
        FormuleRisque formuleRisque = ponderationRisqueTheorique.getFormuleRisque();
        if (formuleRisque != null) {
        	queryFormController.setNivSupDomaineTechnique(formuleRisque.getDomaineTechnique());
        }
    }
}
