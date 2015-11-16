package fr.gouv.agriculture.dal.sial.arq.controller.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.business.bean.VNbruaAgg;
import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.arq.service.VNbruaService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.habilitations.businessshared.VStructureDomaineTechnique;
import fr.gouv.agriculture.habilitations.businessshared.VStructureEtendu;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.form.QueryFormController;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import java.util.Stack;

/**
 *
 * @author fjperez
 */
public class CalculEtResultatsListController extends AbstractInitVideListController {

    @Inject
    private VNbruaService nbrUaService;
    @Inject
    private HabilitationsService habilitationsService;

    @Inject(value = "calculEtResultatsQFC")
    private QueryFormController queryFormController;

    @Inject
    private FormuleRisqueDAO formuleRisqueDAO;

    /**
     * permet de savoir si une formule existe pour la recherche courante sans refaire a chaque appel un appel DAO
     */
    private Boolean formuleRisqueExistForCurrentSearch = false;
    /**
     * Permet de gérer le cas ou il y a des resultats mais pas pour les structure de l'utilisateur courant
     */
    private Boolean resultExistNotInUserStructure=true;

    @Override
    protected Collection loadEntities(Stack stack) throws Exception {
        //on ne fait pas de recherche lors de l'initialisation du controller donc il faut s'assurer que le tag formuleRisqueExist 
        // est bien reinitialisé a false.
        Collection emptyCollection = super.loadEntities(stack); //To change body of generated methods, choose Tools | Templates.
        formuleRisqueExistForCurrentSearch=false;
        return emptyCollection;
    }

    
    @Override
    protected Collection findBusinesses(BusinessSearchContext businessSearchContext) throws Exception {

        List<String> communesInsee = new ArrayList<String>();

        //executer la requete
        boolean doFind = true;

        List<String> listeStructuresRfa = new ArrayList<String>();
        // Attribut complementaire : Cas Structure seule
        List<VStructureEtendu> listeStructures = habilitationsService.getAttCompStructuresRoleConnecte();
        for (VStructureEtendu struct : listeStructures) {
            listeStructuresRfa.add(struct.getStructCodeRfa());
        }

        // Attribut complementaire : Cas Structure + Dt
        List<VStructureDomaineTechnique> listeStructuresDt = habilitationsService
                .getAttCompStructuresDomainesTechniquesRoleConnecte();
        for (VStructureDomaineTechnique structDt : listeStructuresDt) {
            if (structDt.getStructure() != null && structDt.getStructure().getStructCodeRfa() != null) {
                listeStructuresRfa.add(structDt.getStructure().getStructCodeRfa());
            }
        }

        if (!CommonHelper.isEmpty(listeStructuresRfa)) {
            communesInsee = nbrUaService.findCommuneInseeForStructures(listeStructuresRfa);
            if (CommonHelper.isEmpty(communesInsee)) {
                doFind = false;
            }
        }

        VNbrua criteria = (VNbrua) queryFormController.getFormModel().getObject();

        VCampagne campagne = criteria.getCampagne();
        VDomaineTechnique domaineTechnique = criteria.getDomaineTechnique();

        // Recherche
        Collection<VNbruaAgg> vNbruaList = new ArrayList<VNbruaAgg>();
        if (doFind) {
            // doit être initialisé a true pour le cas ou l'on cherche sur toutes les communes et non sur un nombre de communes limité
            resultExistNotInUserStructure=true;
            if (CommonHelper.isEmpty(communesInsee)) {
                vNbruaList = nbrUaService.findByCampDtAgg(campagne, domaineTechnique);
            } else {
                vNbruaList = nbrUaService.findByCampDtCommuneAgg(campagne, domaineTechnique, communesInsee);
                if(vNbruaList.isEmpty()){
                   Collection nbrUaAll= nbrUaService.findByCampDtAgg(campagne, domaineTechnique);
                   resultExistNotInUserStructure = !nbrUaAll.isEmpty();
                }
            }
        }
        formuleRisqueExistForCurrentSearch = calculateFormuleExistFormCampagneAndDomaineTech();

        return vNbruaList;

    }

    /**
     * Vérification de l'existance d'une formule Risque pour les élément de
     * recherche
     *
     * @return true si une formule existe
     * @throws Exception
     */
    public boolean calculateFormuleExistFormCampagneAndDomaineTech() throws Exception {
        VNbrua criteria = (VNbrua) queryFormController.getFormModel().getObject();

        VCampagne campagne = criteria.getCampagne();
        VDomaineTechnique domaineTechnique = criteria.getDomaineTechnique();
        if (campagne != null && domaineTechnique != null) {
            return formuleRisqueDAO.formuleExistForCampagneAndDt(campagne.getCampRfa(), domaineTechnique.getDtRfa());
        } else {
            return false;
        }
    }

    public Boolean getFormuleRisqueExistForCurrentSearch() {
        return formuleRisqueExistForCurrentSearch;
    }

    public void setFormuleRisqueExistForCurrentSearch(Boolean formuleRisqueExistForCurrentSearch) {
        this.formuleRisqueExistForCurrentSearch = formuleRisqueExistForCurrentSearch;
    }

    public Boolean getResultExistNotInUserStructure() {
        return resultExistNotInUserStructure;
    }

    public void setResultExistNotInUserStructure(Boolean resultExistNotInUserStructure) {
        this.resultExistNotInUserStructure = resultExistNotInUserStructure;
    }
}
