package fr.gouv.agriculture.dal.sial.arq.dao;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.query.Order;

import java.util.List;

/**
 * DAO pour la nomenclature Campagne
 *
 * @author sopra
 *
 */
public interface DomaineTechniqueArqDAO extends BusinessDAO {

    /**
     * Retourne une liste de tout les domaines techniques présent dans la table
     * domaine_technique.
     * @param orders ordre de recuperation des données
     *
     * @return liste de VDomaineTechnique
     * @throws Exception
     */
    List<VDomaineTechnique> findAll(List<Order> orders) throws Exception;

    /**
     * Retourne une liste des domaines techniques correspondant a la liste
     * de leur identifiant passé en paramètre.
     * 
     * @param identifiants liste des identifiants des domaines techniques
     * @param orders ordre de recuperation des données
     *
     * @return liste de VDomaineTechnique
     * @throws Exception
     */
    List<VDomaineTechnique> findIn(List<String> identifiants,List<Order> orders) throws Exception;
}
