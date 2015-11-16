package fr.gouv.agriculture.dal.sial.arq.service;

import java.util.List;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService;

/**
 * Service orchestrateur d'accès aux données étendues du Webmin SIAL. Ce service
 * utilise le service issu d'Habilitations
 *
 * @author sopra
 */
public interface HabilitationsService extends IHabilitationsSialService {

    /**
     * Permet de retourner les domaines techniques disponible pour l'utilisateur
     * si nécessaire pour le profil : ALA, ANMA et CA selon la RG :
     * Règles_Trans_002_FiltreValeurs
     *
     * @return
     * @throws Exception
     */
    List<VDomaineTechnique> getAttCompDomainesTechniquesRoleConnecteFiltre() throws Exception;

	/**
	 * Cette méthode récupère la liste des codes rfa des structures de
	 * l'utilisateur.
	 * 
	 * @return Une liste de codes rfa de structures.
	 */
	public List<String> getAttCompStructRfaRoleConnecte();
}
