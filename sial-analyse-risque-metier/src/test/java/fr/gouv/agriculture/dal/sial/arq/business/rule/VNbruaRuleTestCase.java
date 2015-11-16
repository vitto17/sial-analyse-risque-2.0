package fr.gouv.agriculture.dal.sial.arq.business.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.business.constants.TestsConstants;
import fr.gouv.agriculture.dal.sial.arq.business.utils.TestFactory;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * TU de la rule VNbruaRule
 *
 * @author sopra
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class VNbruaRuleTestCase {

	/** Rule testée */
    @TestedObject
    private VNbruaRule rule;
	
	/**
     * Cas nominal passant.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateNominal() throws Exception {
    	VNbrua vnbrua = new VNbrua();
    	
    	vnbrua.setCampagne(new VCampagne());
    	vnbrua.setDomaineTechnique(new VDomaineTechnique());
    	
    	RuleReport report = rule.validate(vnbrua);
     	
     	assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }
    
    /**
     * Cas objet vide (ne doit pas planter)
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateNominalObjetNull() throws Exception {
        RuleReport report = rule.validate(null);
    	
    	assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty()); 
    }
    
    /**
     * Controlle du poids: le poids est à 0
     * 
     * @throws Exception the exception
     */
    @Test
    public void testValidateCampagneNull() throws Exception {
        
    	VNbrua vnbrua = new VNbrua();    	
    	vnbrua.setDomaineTechnique(new VDomaineTechnique());
    	
    	RuleReport report = rule.validate(vnbrua);
    	
    	assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
    	List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    /**
     * Controlle du poids: le poids est à 0
     * 
     * @throws Exception the exception
     */
    @Test
    public void testValidateDTNull() throws Exception {
        
    	VNbrua vnbrua = new VNbrua();    	
    	vnbrua.setDomaineTechnique(new VDomaineTechnique());
    	
    	RuleReport report = rule.validate(vnbrua);
    	
    	assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
    	List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
}
