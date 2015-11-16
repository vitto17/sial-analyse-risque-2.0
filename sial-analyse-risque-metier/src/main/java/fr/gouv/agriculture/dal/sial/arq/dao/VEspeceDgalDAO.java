/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao;

import java.util.List;
import java.util.Map;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VEspeceDgal;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 *
 * @author pegaltier
 */
public interface VEspeceDgalDAO extends BusinessDAO {

    /**
     * Permet de charger tous les objets espece dgal correspondant a la liste de
     * rfa passé en paramètre.
     *
     * @param listRfa
     * @return
     * @throws Exception
     */
    public Map<String, VEspeceDgal> findWithListRfa(List<String> listRfa) throws Exception;
}
