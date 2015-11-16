/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.action;


/**
 * Permet de contextualiser le guide des destinations par le type d'activité.
 * (Aucune contextualisation pour le moment car destination ne le permet pas)
 *
 * @author pegaltier
 */
public class NomenclaturesDestinationShowPopUpAction extends NomenclaturesShowPopUpAction {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * QueryFormController du guide de la Nomenclature
     */
    //@Inject(value = "vDestinationQFC")
    //private DefaultQueryFormController queryFormController;

    @Override
    public void setContext(Object object) {
//		PonderationDestination ponderation = (PonderationDestination) object;
//                VTypeActivite typeActivite = ponderation.getTaRfa();
//                queryFormController.setc
    }
}
