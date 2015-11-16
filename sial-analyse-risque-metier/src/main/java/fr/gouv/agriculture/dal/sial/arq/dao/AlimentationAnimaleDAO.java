/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao;

import java.util.List;
import java.util.Map;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VAlimentationAnimale;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 *
 * @author pegaltier
 */
public interface AlimentationAnimaleDAO extends BusinessDAO {

    /**
     * Permet de charger tous les objets alimentation animale correspondant a la
     * liste de rfa passé en paramètre.
     *
     * @param listRfa
     * @return
     * @throws Exception
     */
    public Map<String, VAlimentationAnimale> findWithListRfa(List<String> listRfa) throws Exception;
}
