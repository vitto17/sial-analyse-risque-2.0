package fr.gouv.agriculture.dal.sial.arq.business.rule;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import fr.gouv.agriculture.dal.sial.arq.business.Batch;
import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.dao.BatchDAO;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.security.authentication.AgricollCredentials;

/**
 * Class de test des regles de contrôle pour le lancement du calcul des notes de risques
 *
 * @author fjperez
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class ARQ019LancementCalculRuleTestCase {
    
    /**
     * rule à tester
     */
    @TestedObject
    private ARQ019LancementCalculRule rule;
    
    /**
     * Bouchon pour le service des habilitations
     */
    @Mock
	@InjectIntoByType
	private HabilitationsService habilitationsService;
   
    /**
     * Bouchon pour le DAO du batch
     */
    @Mock
	@InjectIntoByType
    private BatchDAO batchDao;
    
    
    
    /** Jeux de test: login du credentials principal*/
    private static final String MOCK_CREDENTIAL_LOGIN = "login";
    
    /** Jeux de test: Campagne RFA */
    private static final String MOCK_CAMP_RFA = "2013";
    
    /** Jeux de test: Domaine Technique RFA  */
    private static final String MOCK_DT_RFA = "dtRfa";
    
    private final boolean UP = true;
    private final boolean DOWN = false;
    
    @Before
    public void doBefore() {
    }
    
    // TODO : controle 2 - habilitations Structure
    
    /**
     *
     * Contrôle 3 Test 1
     * 
     * Si l’utilisateur a sélectionné une campagne dont la date de fin de validité (V_CAMPAGNE_V2_0.CAMP_FIN_DT)
     * est inférieure à la date courante du système, alors le traitement n’est pas lancé.
     * 
     * Un message est alorsaffiché avec le message suivant :
     *
     * «Merci de bien vouloir sélectionner une campagne dont la date de fin de validé n’est pas échue»
     * 
     * <h3>Cas de test:</h3><br> <br> La liste n'est pas vide, la date de fin de validité de la campagne est passée;
     *
     * <b>Resultat attendue:</b><br> Message d'erreur affichée
     *
     * @throws Exception
     */
    @Test
    public void testControle3Test1() throws Exception {
        
    	AgricollCredentials agricollCredentials = new AgricollCredentials();
    	agricollCredentials.setLogin(MOCK_CREDENTIAL_LOGIN);    	
    	

        VNbrua elem1 = new VNbrua();
        VCampagne campagne = new VCampagne();
        campagne.setCampRfa(MOCK_CAMP_RFA);
        Date currentDate = new Date();
        campagne.setCampFinDt(this.getCurrentFromDatePlusOneYear(currentDate, this.DOWN));
        elem1.setCampagne(campagne);
        
        VDomaineTechnique domaineTechnique = new VDomaineTechnique();
        domaineTechnique.setDtRfa(MOCK_DT_RFA);
        elem1.setDomaineTechnique(domaineTechnique);
        
    	
    	
    	EasyMock.expect(habilitationsService.getUtilisateurConnecte()).andReturn(agricollCredentials);
    	
    	EasyMock.expect(batchDao.findNonTerminePourUtilisateurCampagneDomaineTechnique(
    			MOCK_CREDENTIAL_LOGIN, MOCK_CAMP_RFA, MOCK_DT_RFA)).andReturn(new ArrayList<Batch>());
    	
    	
		EasyMockUnitils.replay();

        
        
        
        // demarrer l'objet de test
        RuleReport ruleReport = rule.validate(elem1);
        
        // vérifications des resultats
        assertEquals(1, ruleReport.getMessages().size());
        
    }
    /**
     * Contrôle OK
     *
     * <h3>Cas de test:</h3><br> <br> 
     * La liste n'est pas vide, 
     * la date de fin de validité de la campagne est future;
     * 
     * <b>Resultat attendue:</b><br> Aucun message d'erreur est affichée
     *
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        
    	AgricollCredentials agricollCredentials = new AgricollCredentials();
    	agricollCredentials.setLogin(MOCK_CREDENTIAL_LOGIN);  
    	
        VNbrua elem1 = new VNbrua();
        VCampagne campagne = new VCampagne();
        campagne.setCampRfa(MOCK_CAMP_RFA);
        Date currentDate = new Date();
        campagne.setCampFinDt(this.getCurrentFromDatePlusOneYear(currentDate,this.UP));
        elem1.setCampagne(campagne);
        
        VDomaineTechnique domaineTechnique = new VDomaineTechnique();
        domaineTechnique.setDtRfa(MOCK_DT_RFA);
        elem1.setDomaineTechnique(domaineTechnique);
        
    	
        EasyMock.expect(habilitationsService.getUtilisateurConnecte()).andReturn(agricollCredentials);
    	EasyMock.expect(batchDao.findNonTerminePourUtilisateurCampagneDomaineTechnique(
    			MOCK_CREDENTIAL_LOGIN, MOCK_CAMP_RFA, MOCK_DT_RFA)).andReturn(new ArrayList<Batch>());
		EasyMockUnitils.replay();
    	

        
        
        
        // demarrer l'objet de test
        RuleReport ruleReport = rule.validate(elem1);
        
        // vérifications des resultats
        assertEquals(0, ruleReport.getMessages().size());
        
    }
    /**
     * Contrôle OK
     *
     * <h3>Cas de test:</h3><br> <br> 
     * La liste n'est pas vide, 
     * la date de fin de validité de la campagne est future;
     * 
     * <b>Resultat attendue:</b><br> Aucun message d'erreur est affichée
     *
     * @throws Exception
     */
    @Test
    public void testOK2() throws Exception {
        
    	AgricollCredentials agricollCredentials = new AgricollCredentials();
    	agricollCredentials.setLogin(MOCK_CREDENTIAL_LOGIN);  
        
        VNbrua elem1 = new VNbrua();
        VCampagne campagne = new VCampagne();
        campagne.setCampRfa("2013");
        Date currentDate = new Date();
        campagne.setCampFinDt(currentDate);
        elem1.setCampagne(campagne);
        VDomaineTechnique domaineTechnique = new VDomaineTechnique();
        domaineTechnique.setDtRfa(MOCK_DT_RFA);
        elem1.setDomaineTechnique(domaineTechnique);
        
        
        EasyMock.expect(habilitationsService.getUtilisateurConnecte()).andReturn(agricollCredentials);
    	EasyMock.expect(batchDao.findNonTerminePourUtilisateurCampagneDomaineTechnique(
    			MOCK_CREDENTIAL_LOGIN, MOCK_CAMP_RFA, MOCK_DT_RFA)).andReturn(new ArrayList<Batch>());
		EasyMockUnitils.replay();
        
        // demarrer l'objet de test
		RuleReport ruleReport = rule.validate(elem1);
        
        // vérifications des resultats
        assertEquals(0, ruleReport.getMessages().size());
        
    }

    
    /**
     * Methode pour avoir une date aprés/avant de la date renseignée
     * 
     * @param date
     * @param direction
     * @return 
     */
   private Date getCurrentFromDatePlusOneYear(Date date, boolean direction) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(Calendar.YEAR, direction);

        return calendar.getTime();
    }    
}
