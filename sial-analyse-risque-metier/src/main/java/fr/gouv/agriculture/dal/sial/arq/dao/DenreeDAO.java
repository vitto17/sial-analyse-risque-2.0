/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao;

import java.util.List;
import java.util.Map;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDenree;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 *
 * @author pegaltier
 */
public interface DenreeDAO extends BusinessDAO {

    /**
     * Permet de charger tous les objets denree correspondant a la liste de rfa
     * passé en paramètre.
     *
     * @param listRfa
     * @return
     * @throws Exception
     */
    public Map<String, VDenree> findWithListRfa(List<String> listRfa) throws Exception;
}
