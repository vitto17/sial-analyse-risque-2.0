package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.controller.list.TraitementsEnCoursListController;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.BusinessAction;
import fr.gouv.agriculture.orion.event.ControllerEvent;
import fr.gouv.agriculture.orion.event.Events;

/**
 *Rafraichissement de page des traitements en cours
 * @author ygarcia
 */
public class TraitementsEnCoursRafraichirAction extends BusinessAction {
    
	/** Controlleur de la page des traitements en cours*/
	@Inject(value="traitementsEnCoursLC")
    private TraitementsEnCoursListController traitEnCoursListController;
            
    @Override
    public Object execute() throws Exception{
        traitEnCoursListController.processControllerEvent(new ControllerEvent(traitEnCoursListController, Events.DO_REFRESH, null));
        return super.execute();
    }

    /**
     * Controlleur de la page des traitements en cours
     * @return  Controlleur de la page des traitements en cours
     */
    public TraitementsEnCoursListController getTraitEnCoursListController() {
        return traitEnCoursListController;
    }

    /**
     * Controlleur de la page des traitements en cours
     * @param traitEnCoursListController Controlleur de la page des traitements en cours
     */
    public void setTraitEnCoursListController(TraitementsEnCoursListController traitEnCoursListController) {
        this.traitEnCoursListController = traitEnCoursListController;
    }
    
}