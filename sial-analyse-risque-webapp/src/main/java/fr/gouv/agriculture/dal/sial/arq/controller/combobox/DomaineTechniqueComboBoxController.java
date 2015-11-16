package fr.gouv.agriculture.dal.sial.arq.controller.combobox;

import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.habilitations.businessshared.VDomaineTechniqueEtendu;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.list.ListController;
import java.util.Collection;
import java.util.List;


/**
 * Controller pour Combobox Domaine Technique
 *
 * @author sopra
 */
public class DomaineTechniqueComboBoxController extends ListController {

    /**
     * Service de gestion de l'utilisateur
     */
    @Inject
    private HabilitationsService habilitationsService;
    
    @Override
    protected Collection findBusinesses(BusinessSearchContext businessSearchContext) throws Exception {
        
        // Filtre les Domaine techniques si nécessaire pour le profil : ALA ANMA et CA
        List<VDomaineTechnique> listDt = habilitationsService.getAttCompDomainesTechniquesRoleConnecteFiltre();
        
        return listDt;
    }
	
    
    
	/**
	 * Retourne la valeur par defaut du domaine technique
	 * @return Valeur par defaut du domaine technique
	 * @throws Exception exception
	 */
	public VDomaineTechnique getDefaultValue() throws Exception {
		return null;
	}
}
