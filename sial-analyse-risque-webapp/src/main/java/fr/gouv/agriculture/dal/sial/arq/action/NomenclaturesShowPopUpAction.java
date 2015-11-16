package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.Collection;

import fr.gouv.agriculture.dal.sial.nomenclatures.constants.TypeApplication;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.TypeSelection;
import fr.gouv.agriculture.dal.sial.nomenclatures.shared.controller.AbstractGuideListController;
import fr.gouv.agriculture.orion.action.navigation.ShowPopUpAction;

/**
 * Ouverture de guide d'une Nomenclature
 * @author sopra
 *
 */
public class NomenclaturesShowPopUpAction extends ShowPopUpAction{

	
	/** serial */
	private static final long serialVersionUID = 7653874378314265733L;
	
	 /** Le type de sélection avec laquelle ouvrir le guide */
    private TypeSelection typeSelection = TypeSelection.MONO_SELECTION;
    /** Le type de l'application qui ouvre le guide */
    private TypeApplication typeApplication = TypeApplication.APPLICATION_CONSOMMATRICE;
    /**
     * Indique si les critères du guide correspondants aux liens de
     * consommation, consommé par et contextualisation sont figés ou pas
     */
    private Boolean lienFige = Boolean.FALSE;
       
    
    /** ListController du guide de la Nomenclature */
    private AbstractGuideListController listController;
    
   
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.gouv.agriculture.orion.action.navigation.ShowPopUpAction#execute()
     */
    @Override
    public Object execute() throws Exception {

        getListController().setTypeApplication(this.typeApplication);
        getListController().setTypeSelection(this.typeSelection);
        getListController().setLiensFige(this.lienFige);

		Collection selection = getBaseController().getSelection();
		if (selection != null && !selection.isEmpty()) {
			setContext(selection.iterator().next());
		}
            
        return super.execute();
    }
    
    /**
     * Init du contexte
     * @param object Objet
     */
    public void setContext(Object object){
    	
    }
    
    /**
	 * @return the typeSelection
	 */
	public TypeSelection getTypeSelection() {
		return typeSelection;
	}
	/**
	 * @param typeSelection the typeSelection to set
	 */
	public void setTypeSelection(TypeSelection typeSelection) {
		this.typeSelection = typeSelection;
	}
	/**
	 * @return the typeApplication
	 */
	public TypeApplication getTypeApplication() {
		return typeApplication;
	}
	/**
	 * @param typeApplication the typeApplication to set
	 */
	public void setTypeApplication(TypeApplication typeApplication) {
		this.typeApplication = typeApplication;
	}
	/**
	 * @return the lienFige
	 */
	public Boolean getLienFige() {
		return lienFige;
	}
	/**
	 * @param lienFige the lienFige to set
	 */
	public void setLienFige(Boolean lienFige) {
		this.lienFige = lienFige;
	}

	/**
	 * @return the listController
	 */
	public AbstractGuideListController getListController() {
		return listController;
	}

	/**
	 * @param listController the listController to set
	 */
	public void setListController(AbstractGuideListController listController) {
		this.listController = listController;
	}
    
}

