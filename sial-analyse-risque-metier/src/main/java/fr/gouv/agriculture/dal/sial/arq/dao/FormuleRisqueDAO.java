package fr.gouv.agriculture.dal.sial.arq.dao;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.orion.dao.BusinessDAO;

/**
 * DAO pour les formules de risques
 *
 * @author SiteGenerator
 * @version $Id: dao.vm 12071 2008-10-29 23:44:22Z sebastien.bouvet $
 */
public interface FormuleRisqueDAO extends BusinessDAO {

    /**
     * Permet de vérifier la RG : ARQ_008_Règles_ControleUnicitéFormule
     *
     *
     * @param formuleRisque
     * @return true si la formule est unique, false sinon
     * @throws Exception
     */
    boolean controleUniciteFormule(FormuleRisque formuleRisque) throws Exception;

    /**
     * Permet de vérifier qu'une formule existe pour une campagne et un domaine
     * Technique donnés
     *
     * @param campagneRfa
     * @param domaineTechRfa
     * @return rue si une formule existe
     * @throws Exception
     */
    boolean formuleExistForCampagneAndDt(String campagneRfa, String domaineTechRfa) throws Exception;
}
