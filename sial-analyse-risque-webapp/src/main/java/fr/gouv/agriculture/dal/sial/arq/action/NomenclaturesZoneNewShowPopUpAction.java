package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.controller.slavelist.PonderationZoneSlaveListController;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.TypeApplication;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.TypeSelection;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.AbstractGuideListController;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VZoneQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.navigation.ShowPopUpAction;

/**
 * Ouverture du guide des zones
 * @author pbarreau
 *
 */
public class NomenclaturesZoneNewShowPopUpAction extends ShowPopUpAction{

	/**serial*/
	private static final long serialVersionUID = 4698984530336343833L;

    /** slaveList controleur de la ponderation des zones*/
    @Inject(value = "ponderationZoneSLC")
   	private PonderationZoneSlaveListController slaveListController;
    
	/** QueryFormController du guide de la Nomenclature */
    @Inject(value = "vZoneQFC")
	private VZoneQueryFormController queryFormController;
    
    /** ListController du guide de la Nomenclature */
    @Inject(value = "vZoneLC")
    private AbstractGuideListController listController;
    
    @Override
    public Object execute() throws Exception {
        
        listController.setLiensFige(false);
        queryFormController.setConsoTypeZone(null);
        queryFormController.setRememberQuery(false);
    	listController.setTypeApplication(TypeApplication.APPLICATION_CONSOMMATRICE);
    	listController.setTypeSelection(TypeSelection.MULTI_SELECTION);
        
    	//Modification
    	slaveListController.setNewPonderation(true);
    	
        
    	return super.execute();
    }
	
}
