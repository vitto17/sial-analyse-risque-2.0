package fr.gouv.agriculture.dal.sial.arq.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.dao.DomaineTechniqueArqDAO;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.CriterionName;
import fr.gouv.agriculture.habilitations.businessshared.AttributComplementaire;
import fr.gouv.agriculture.habilitations.businessshared.VDomaineTechniqueEtendu;
import fr.gouv.agriculture.habilitations.businessshared.VStructureDomaineTechnique;
import fr.gouv.agriculture.habilitations.businessshared.VStructureEtendu;
import fr.gouv.agriculture.habilitations.security.authentication.UserSial;
import fr.gouv.agriculture.habilitations.security.authorization.RoleSial;
import fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.security.authentication.OrionCredentials;

/**
 * Service orchestrateur d'accès aux données étendues du Webmin SIAL.
 * Ce service utilise le service issu du projet Habilitations.
 * 
 * @author sopra
 */
public class HabilitationsServiceImpl implements HabilitationsService {

	/** Service issu d'Habilitations*/
	@Inject
	private IHabilitationsSialService habilitationsSialService;
    
	/** DAO des domaines techniques */
    @Inject (value="fr.gouv.agriculture.dal.sial.arq.dao.DomaineTechniqueArqDAO")
    private DomaineTechniqueArqDAO domaineTechniqueArqDAO;
        

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getUtilisateurConnecte()
	 */
	@Override
	public OrionCredentials getUtilisateurConnecte() {
		return habilitationsSialService.getUtilisateurConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getUserSialConnecte()
	 */
	@Override
	public UserSial getUserSialConnecte() {
		return habilitationsSialService.getUserSialConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getRoleSialUtilisateurConnecte()
	 */
	@Override
	public RoleSial getRoleSialUtilisateurConnecte() {
		return habilitationsSialService.getRoleSialUtilisateurConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getStructureDefaultUtilisateurConnecte()
	 */
	@Override
	public VStructureEtendu getStructureDefaultUtilisateurConnecte() {
		return habilitationsSialService.getStructureDefaultUtilisateurConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getStructureDefautRoleUtilisateurConnecte()
	 */
	@Override
	public VStructureEtendu getStructureDefautRoleUtilisateurConnecte() {
		return habilitationsSialService.getStructureDefautRoleUtilisateurConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getAttCompStructuresRoleConnecte()
	 */
	@Override
	public List<VStructureEtendu> getAttCompStructuresRoleConnecte() {
		return habilitationsSialService.getAttCompStructuresRoleConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getAttCompStructuresRoleConnecte(fr.gouv.agriculture.habilitations.businessshared.VDomaineTechniqueEtendu)
	 */
	@Override
	public List<VStructureEtendu> getAttCompStructuresRoleConnecte(
			VDomaineTechniqueEtendu filter) {
		return habilitationsSialService.getAttCompStructuresRoleConnecte(filter);
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getAttCompDomainesTechniquesRoleConnecte()
	 */
	@Override
	public List<VDomaineTechniqueEtendu> getAttCompDomainesTechniquesRoleConnecte() {
		return habilitationsSialService. getAttCompDomainesTechniquesRoleConnecte();
	}
        
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService#getAttCompDomainesTechniquesRoleConnecteFiltre()
     */
    @Override
    public List<VDomaineTechnique> getAttCompDomainesTechniquesRoleConnecteFiltre() throws Exception {
        
        // Domaine technique pour le profil : ALA et CA
        List<VStructureDomaineTechnique> listDtAlaCa = habilitationsSialService.getAttCompStructuresDomainesTechniquesRoleConnecte();
        
        // Domaine technique pour le profil : ANMA
        List<VDomaineTechniqueEtendu> listDtAnma = habilitationsSialService.getAttCompDomainesTechniquesRoleConnecte();
        
        // On crée une liste des identifiants RFA concatenant listDtAlaCa et listDtANMA en suprimmant les doublons
        List<String> listIdentifiantsDt = new ArrayList<String>();
        
        for (VStructureDomaineTechnique structDt : listDtAlaCa) {
            listIdentifiantsDt.add(structDt.getDomaineTechnique().getIdentifier().toString());
        }
        for (VDomaineTechniqueEtendu domaineTech : listDtAnma) {
            String dtRfa = domaineTech.getIdentifier().toString();
            if (!listIdentifiantsDt.contains(dtRfa)) {
                listIdentifiantsDt.add(dtRfa);
            }
        }

        //Ordre d'affichage des domaines techniques
        List<Order> dtOrders = new ArrayList<Order>();
        Order orderDtRfa = new Order(CriterionName.DOMAINE_TECHNIQUE_CODE,true);
        dtOrders.add(orderDtRfa);
        
        // Renvoi soit la liste filtrée soit tout
        if (!listIdentifiantsDt.isEmpty()) {
            List<VDomaineTechnique> domaineTechniques = domaineTechniqueArqDAO.findIn(listIdentifiantsDt,dtOrders);
            return domaineTechniques;
        } else {
            return domaineTechniqueArqDAO.findAll(dtOrders);
        }

    }

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getAttCompDomainesTechniquesRoleConnecte(fr.gouv.agriculture.habilitations.businessshared.VStructureEtendu)
	 */
	@Override
	public List<VDomaineTechniqueEtendu> getAttCompDomainesTechniquesRoleConnecte(
			VStructureEtendu filter) {
		return habilitationsSialService.getAttCompDomainesTechniquesRoleConnecte(filter);
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getAttCompStructuresDomainesTechniquesRoleConnecte()
	 */
	@Override
	public List<VStructureDomaineTechnique> getAttCompStructuresDomainesTechniquesRoleConnecte() {
		return habilitationsSialService.getAttCompStructuresDomainesTechniquesRoleConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getRolesSialUtilisateurConnecte()
	 */
	@Override
	public List<RoleSial> getRolesSialUtilisateurConnecte() {
		return habilitationsSialService.getRolesSialUtilisateurConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getAttributComplementaireUtilisateurConnecte()
	 */
	@Override
	public AttributComplementaire getAttributComplementaireUtilisateurConnecte() {
		return habilitationsSialService.getAttributComplementaireUtilisateurConnecte();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService#getStructureDefaultByRoleSial(fr.gouv.agriculture.habilitations.security.authorization.RoleSial)
	 */
	@Override
	public VStructureEtendu getStructureDefaultByRoleSial(RoleSial roleSial) {
		return habilitationsSialService.getStructureDefaultByRoleSial(roleSial);
	}

	@Override
	public List<String> getAttCompStructRfaRoleConnecte() {
		List<String> listeStructuresRfa = new ArrayList<String>();

		// Attribut complementaire : Cas Structure seule
		List<VStructureEtendu> listeStructures = getAttCompStructuresRoleConnecte();
		for (VStructureEtendu struct : listeStructures) {
			String structRfa = struct.getStructCodeRfa();
			if (!listeStructuresRfa.contains(structRfa)) {
				listeStructuresRfa.add(structRfa);
			}
		}

		// Attribut complementaire : Cas Structure seule
		List<VStructureDomaineTechnique> listeStructuresDt = getAttCompStructuresDomainesTechniquesRoleConnecte();
		for (VStructureDomaineTechnique structDt : listeStructuresDt) {
			if (structDt.getStructure() != null && structDt.getStructure().getStructCodeRfa() != null
					&& !listeStructuresRfa.contains(structDt.getStructure().getStructCodeRfa())) {
				listeStructuresRfa.add(structDt.getStructure().getStructCodeRfa());
			}
		}
		return listeStructuresRfa;
	}
	
}
