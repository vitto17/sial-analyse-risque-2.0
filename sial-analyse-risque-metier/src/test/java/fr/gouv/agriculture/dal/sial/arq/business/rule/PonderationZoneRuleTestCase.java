package fr.gouv.agriculture.dal.sial.arq.business.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.business.constants.TestsConstants;
import fr.gouv.agriculture.dal.sial.arq.business.utils.TestFactory;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VZone;
import fr.gouv.agriculture.o2.rules.ConfigureKernelRule;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;
import java.math.BigDecimal;

/**
 * TU de la rule PonderationZoneRule
 *
 * @author sopra
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class PonderationZoneRuleTestCase {

	
	/** The configure kernel rule. */
    @ClassRule
    public static ConfigureKernelRule configureKernelRule = new ConfigureKernelRule(
            TestsConstants.KERNEL_CONFIG);
    
    /** Rule testée */
    @TestedObject
    private PonderationZoneRule rule;
    
    /** Code rfa de la zone 1*/
    private String Z_RFA_1 = "rfa1";
    /** Code rfa de la zone 2*/
    private String Z_RFA_2 = "rfa2";
    /**
     * Poids conforme
     */
    private BigDecimal POIDS_POS = new BigDecimal("1");
    /**
     * Poids non conforme
     */
    private BigDecimal POIDS_0 = new BigDecimal("0");
    /**
     * Poids non conforme
     */
    private BigDecimal POIDS_NEG = new BigDecimal("-2");
    
    
    /**
     * Cas nominal passant.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateNominal() throws Exception {
    	
    	FormuleRisque formuleRisque = TestFactory.createFormuleRisque();
    	
    	PonderationZone ponderation1  = createPonderationZone(Z_RFA_1,POIDS_POS,formuleRisque);
    	createPonderationZone(Z_RFA_2,POIDS_POS,formuleRisque);
    	
    	RuleReport report = rule.validate(ponderation1);
    	
    	assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }
    
    /**
     * Controlle du poids: le poids est à 0
     * 
     * @throws Exception the exception
     */
    @Test
    public void testValidatePoidsZero() throws Exception {
       FormuleRisque formuleRisque = TestFactory.createFormuleRisque();
    	
    	PonderationZone ponderation1  = createPonderationZone(Z_RFA_1,POIDS_NEG,formuleRisque);
    	    	
    	RuleReport report = rule.validate(ponderation1);
    	
    	assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
    	List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    /**
     * Controlle du poids: le poids est à negatif
     * 
     * @throws Exception the exception
     */
    @Test
    public void testValidatePoidsNeg() throws Exception {
       FormuleRisque formuleRisque = TestFactory.createFormuleRisque();
    	
    	PonderationZone ponderation1  = createPonderationZone(Z_RFA_1,POIDS_0,formuleRisque);
    	    	
    	RuleReport report = rule.validate(ponderation1);
    	
    	assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
    	List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    
    /**
     * Cas d'un objet null passé en parametre
     * (robustesse)
     * @throws Exception the exception
     */
    @Test
    public void testValidateNull() throws Exception {
        RuleReport report = rule.validate(null);
    	
    	assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }
    
    
    /**
     * Création d'un objet à tester valide
     * @param zRfa code de la zone
     * @return objet à tester valide
     */
    private PonderationZone createPonderationZone(String zRfa,BigDecimal poids,FormuleRisque formuleRisque){
    	PonderationZone ponderation = new PonderationZone();
    	ponderation.setZone(createZone(zRfa));
    	ponderation.setPzonePoidsNb2(poids);
    	
    	ponderation.setFormuleRisque(formuleRisque);
    	formuleRisque.getPonderationZones().add(ponderation);
    	
    	return ponderation;
    }
    
    /**
     * Création d'une zone
     * @param rfa code de la zone
     * @return zone
     */
    private VZone createZone(String rfa){
    	VZone zone = new VZone();
    	zone.setZrfa(rfa);
    	
    	return zone;
    }
	
}
