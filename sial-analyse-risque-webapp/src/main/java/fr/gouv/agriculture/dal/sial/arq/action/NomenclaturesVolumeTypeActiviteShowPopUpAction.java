/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VTypeActiviteQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.form.FormController;

/**
 * Permet de contextualiser le guide des type d'activité de la page ponderation
 * volume par le secteur d'activité.
 *
 * @author pegaltier
 */
public class NomenclaturesVolumeTypeActiviteShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    @Inject(value = "vTypeActiviteQFC")
    private VTypeActiviteQueryFormController queryFormController;
    /**
     * FormController parent
     */
    @Inject(value = "ponderationVolumeFC")
    private FormController ponderationVolumeFC;

    @Override
    public void setContext(Object object) {
        //PonderationDestination ponderation = (PonderationDestination) object;
        //PonderationVolume ponderation = (PonderationVolume) object;
        queryFormController.setRememberQuery(false);
        FormuleRisque formuleRisque = (FormuleRisque) ponderationVolumeFC.getFormModel().getObject();
        queryFormController.setConsoSecteurActivite(null);
        queryFormController.setNivSupDomaineTechnique(formuleRisque.getDomaineTechnique());
    }
}
