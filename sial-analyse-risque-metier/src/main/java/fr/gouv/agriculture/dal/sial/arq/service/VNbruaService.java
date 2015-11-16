package fr.gouv.agriculture.dal.sial.arq.service;

import java.util.Collection;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.business.bean.VNbruaAgg;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 * Service pour VNbrua
 * 
 * @author sopra
 * 
 */
public interface VNbruaService {

	/**
	 * Cette méthode permet de récupérer les codes INSEE des communes liées à
	 * une structure dont on fournit le code rfa.
	 * 
	 * @param listeStructsRfa
	 *            La liste des codes RFA des structures dont on veut récupérer
	 *            les communes.
	 * @return La liste des codes INSEE des communes liées aux structures.
	 * @throws PersistenceException
	 *             Une exception est lancée si l'on rencontre des problèmes lors
	 *             du dialogue avec la base de données.
	 */
	public List<String> findCommuneInseeForStructures(List<String> listeStructsRfa) throws PersistenceException;
	
	/**
	 * Fonction d'agrégation (count) des VNbrua
	 * @param vNbruas à agréger
	 * @return liste de VNbrua agrégés
	 */
	public Collection<VNbrua> aggCountVNbrua(Collection<VNbrua> vNbruas);
	
	
	/**
	 * retourne une liste de VNbrua aggrégés par campagne,domaine technique 
	 * @param campagne code campagne
	 * @param domaineTechnique code domaine technique
	 * @param codeInseeCommune liste de communes
	 * @return liste de VNbrua agrégés
	 */
	public Collection<VNbruaAgg> findByCampDtCommuneAgg(VCampagne campagne,VDomaineTechnique domaineTechnique,List<String> codeInseeCommune) throws PersistenceException;
	
	/**
	 * retourne une liste de VNbrua aggrégés par campagne,domaine technique 
	 * @param campagne code campagne
	 * @param domaineTechnique code domaine technique
	 * @return liste de VNbrua agrégés
	 */
	public Collection<VNbruaAgg> findByCampDtAgg(VCampagne campagne,VDomaineTechnique domaineTechnique) throws PersistenceException;
}
