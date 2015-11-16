package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.Collection;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.controller.slavelist.PonderationZoneSlaveListController;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.TypeApplication;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.TypeSelection;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.AbstractGuideListController;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.queryform.VZoneQueryFormController;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.navigation.ShowPopUpAction;

/**
 * Ouverture du guide des Zones
 * @author pbarreau
 *
 */
public class NomenclaturesZoneModShowPopUpAction extends ShowPopUpAction{

	/**serial*/
	private static final long serialVersionUID = -6546558601833277243L;
	
	
	/** QueryFormController du guide de la Nomenclature */
    @Inject(value = "vZoneQFC")
	private VZoneQueryFormController queryFormController;
	
    /** slaveList controleur de la ponderation des zones*/
    @Inject(value = "ponderationZoneSLC")
   	private PonderationZoneSlaveListController slaveListController;
    
    /** ListController du guide de la Nomenclature */
    @Inject(value = "vZoneLC")
    private AbstractGuideListController listController;
    
    @Override
    public Object execute() throws Exception {
        
        queryFormController.setConsoTypeZone(null);
        queryFormController.setRememberQuery(false);
        Collection selection = getBaseController().getSelection();
		if (selection != null && !selection.isEmpty()) {
			PonderationZone ponderation = (PonderationZone) selection.iterator().next();
			queryFormController.setConsoTypeZone(ponderation.getZone()
					.getTypeZone());
		}
                
        // Configuration
    	listController.setTypeApplication(TypeApplication.APPLICATION_CONSOMMATRICE);
    	listController.setTypeSelection(TypeSelection.MONO_SELECTION);
    	listController.setLiensFige(true);
        
    	//Modification
    	slaveListController.setNewPonderation(false);


        
    	return super.execute();
    }

}
