/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.business.constants.TestsConstants;
import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCtxDtSa;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDiffusion;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VZone;
import fr.gouv.agriculture.o2.rules.ConfigureKernelRule;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * TU de la rule FormuleRisqueRule.
 *
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class FormuleRisqueRuleTestCase {

    /**
     * The configure kernel rule.
     */
    @ClassRule
    public static ConfigureKernelRule configureKernelRule = new ConfigureKernelRule(
            TestsConstants.KERNEL_CONFIG);
    /**
     * Rule testée
     */
    @TestedObject
    private FormuleRisqueRule rule;
    /** Mock FormuleRisqueDAO*/
    @Mock
    @InjectIntoByType
    private FormuleRisqueDAO dao;
    
    /**
     * Poids conforme
     */
    private BigDecimal POIDS_POS = new BigDecimal("1");
    
    /** Poids conforme*/
    private byte POIDS_POS_B = 2;
    
    /**
     * Code rfa de la campagne.
     */
    private String C_RFA_1 = "crfa1";
    /**
     * Code rfa du domaine technique.
     */
    private String DT_RFA_1 = "dtrfa1";
    /**
     * Code rfa du type activité 1
     */
    private String T_RFA_1 = "trfa1";
    /**
     * Code rfa du type activité 2
     */
    private String T_RFA_2 = "trfa2";
    /**
     * Code rfa de la destination 1
     */
    private String DE_RFA_1 = "derfa1";
    /**
     * Code rfa de la destination 2
     */
    private String DE_RFA_2 = "derfa2";
    /**
     * Code rfa de la diffusion 1
     */
    private String DI_RFA_1 = "dirfa1";
    /**
     * Code rfa de la diffusion 2
     */
    private String DI_RFA_2 = "dirfa2";
    /**
     * Code rfa de l'unité production 1
     */
    private String U_RFA_1 = "urfa1";
    /**
     * Code rfa de l'unité production 2
     */
    private String U_RFA_2 = "urfa2";
    /**
     * Code rfa de la zone 1
     */
    private String Z_RFA_1 = "zrfa1";
    /**
     * Code rfa de la zone 2
     */
    private String Z_RFA_2 = "zrfa2";

    /**
     * Cas nominal passant.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateNominal() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);
        
        createPonderationDestination(T_RFA_1, DE_RFA_1, POIDS_POS, formuleRisque);
        createPonderationDestination(T_RFA_2, DE_RFA_2, POIDS_POS, formuleRisque);        
                
        createPonderationDiffusion(DI_RFA_1, POIDS_POS, formuleRisque);
        createPonderationDiffusion(DI_RFA_2, POIDS_POS, formuleRisque);
        
        createPonderationVolume(T_RFA_1, U_RFA_1, POIDS_POS, formuleRisque);
        createPonderationVolume(T_RFA_2, U_RFA_2, POIDS_POS, formuleRisque);
        
        createPonderationZone(Z_RFA_1, POIDS_POS, formuleRisque);
        createPonderationZone(Z_RFA_2, POIDS_POS, formuleRisque);
                
        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
        replay(dao);
       

        RuleReport report = rule.validate(formuleRisque);

        assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());


    }

    /**
     * Test dans le cas ou la ponderation de la destination est non unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidatePonderationDestinationUniciteFail() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);

        createPonderationDestination(T_RFA_1, DE_RFA_1, POIDS_POS, formuleRisque);
        createPonderationDestination(T_RFA_1, DE_RFA_1, POIDS_POS, formuleRisque);  
        
        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    /**
     * Test dans le cas ou la ponderation du volume est non unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidatePonderationVolumeUniciteFail() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);

        createPonderationVolume(T_RFA_1, U_RFA_1, POIDS_POS, formuleRisque);
        createPonderationVolume(T_RFA_1, U_RFA_1, POIDS_POS, formuleRisque);
        
        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    /**
     * Test dans le cas ou la ponderation de la diffusion est non unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidatePonderationDiffusionUniciteFail() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);

        createPonderationDiffusion(DI_RFA_1, POIDS_POS, formuleRisque);
        createPonderationDiffusion(DI_RFA_1, POIDS_POS, formuleRisque);
        
        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    /**
     * Test dans le cas ou la ponderation de la zone est non unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidatePonderationZoneUniciteFail() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);

        createPonderationZone(Z_RFA_1, POIDS_POS, formuleRisque);
        createPonderationZone(Z_RFA_1, POIDS_POS, formuleRisque);
        
        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }
    
    /**
     * Test dans le cas ou la campagne est non renseignée.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateCampagneNull() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);
        formuleRisque.setCampagne(null);

//        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
//        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Test dans le cas ou le domaine technique est non renseignée.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateDomaineTechniqueNull() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);
        formuleRisque.setDomaineTechnique(null);

//        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
//        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Test dans le cas ou le contrôle d'unicité echoue.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateControleUniciteFail() throws Exception {

        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, null, DT_RFA_1);

        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(false);
        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Test dans le cas ou la campagne a une date incorrecte.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateCampagneDateFail() throws Exception {

        Calendar calendar = new GregorianCalendar(2010, 05, 20);
        FormuleRisque formuleRisque = createFormuleRisque(C_RFA_1, calendar.getTime(), DT_RFA_1);

        expect(dao.controleUniciteFormule(formuleRisque)).andReturn(true);
        replay(dao);

        RuleReport report = rule.validate(formuleRisque);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Cas d'un objet null passé en parametre (robustesse)
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateNull() throws Exception {
        RuleReport report = rule.validate(null);

        assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }

    /**
     * Création d'un objet à tester valide
     *
     */
    private FormuleRisque createFormuleRisque(String campRfa, Date dateFin, String dtRfa) {
        FormuleRisque formuleRisque = new FormuleRisque();
        formuleRisque.setCampagne(createCampagne(campRfa, dateFin));
        formuleRisque.setDomaineTechnique(createDomaineTechnique(dtRfa));
        formuleRisque.setPonderationDestinations(new ArrayList<PonderationDestination>());
        formuleRisque.setPonderationDiffusions(new ArrayList<PonderationDiffusion>());
        formuleRisque.setPonderationRisqueTheoriques(new ArrayList<PonderationRisqueTheorique>());
        formuleRisque.setPonderationVolumes(new ArrayList<PonderationVolume>());
        formuleRisque.setPonderationZones(new ArrayList<PonderationZone>());

        VDomaineTechnique domaineTechnique = new VDomaineTechnique();
        List<VCtxDtSa> ctxDtSas = new ArrayList<VCtxDtSa>();
        domaineTechnique.setCtxDtSas(ctxDtSas);
        formuleRisque.setDomaineTechnique(domaineTechnique);
        
        
        return formuleRisque;
    }

    /**
     * Création d'un objet à tester valide
     *
     * @param zRfa code du type d'activité
     * @return objet à tester valide
     */
    private PonderationDestination createPonderationDestination(String tRfa, String dRfa, BigDecimal poids, FormuleRisque formuleRisque) {
        PonderationDestination ponderation = new PonderationDestination();
        ponderation.setTaRfa(createTypeActivite(tRfa));
        ponderation.setDestRfa(createDestination(dRfa));
        ponderation.setPdestPoidsNb2(poids);

        ponderation.setFormuleRisque(formuleRisque);
        formuleRisque.getPonderationDestinations().add(ponderation);

        return ponderation;
    }

    /**
     * Création d'un objet à tester valide
     *
     * @param zRfa code de la zone
     * @return objet à tester valide
     */
    private PonderationDiffusion createPonderationDiffusion(String zRfa, BigDecimal poids, FormuleRisque formuleRisque) {
        PonderationDiffusion ponderation = new PonderationDiffusion();
        ponderation.setDifRfa(createDiffusion(zRfa));
        ponderation.setPdiffPoidsNb2(poids);

        ponderation.setFormuleRisque(formuleRisque);
        formuleRisque.getPonderationDiffusions().add(ponderation);

        return ponderation;
    }

    /**
     * Création d'un objet à tester valide
     *
     * @param zRfa code du type d'activité
     * @return objet à tester valide
     */
    private PonderationVolume createPonderationVolume(String tRfa, String uRfa, BigDecimal poids, FormuleRisque formuleRisque) {
        PonderationVolume ponderation = new PonderationVolume();
        ponderation.setTaRfa(createTypeActivite(tRfa));
        ponderation.setUprodRfa(createUniteProduction(uRfa));
        ponderation.setPvolS1Nb2(poids);

        ponderation.setFormuleRisque(formuleRisque);
        formuleRisque.getPonderationVolumes().add(ponderation);

        return ponderation;
    }

    /**
     * Création d'un objet à tester valide
     *
     * @param zRfa code de la zone
     * @return objet à tester valide
     */
    private PonderationZone createPonderationZone(String zRfa, BigDecimal poids, FormuleRisque formuleRisque) {
        PonderationZone ponderation = new PonderationZone();
        ponderation.setZone(createZone(zRfa));
        ponderation.setPzonePoidsNb2(poids);

        ponderation.setFormuleRisque(formuleRisque);
        formuleRisque.getPonderationZones().add(ponderation);

        return ponderation;
    }

    /**
     * Création d'une zone
     *
     * @param rfa code de la zone
     * @return zone
     */
    private VZone createZone(String rfa) {
        VZone zone = new VZone();
        zone.setZrfa(rfa);

        return zone;
    }

    /**
     * Création d'une unite de Production
     *
     * @param rfa code d'une unite de Production
     * @return unite de Production
     */
    private VUniteProduction createUniteProduction(String rfa) {
        VUniteProduction uniteProduction = new VUniteProduction();
        uniteProduction.setUprodRfa(rfa);

        return uniteProduction;
    }

    /**
     * Création d'une diffusion
     *
     * @param rfa code de la diffusion
     * @return diffusion
     */
    private VDiffusion createDiffusion(String rfa) {
        VDiffusion diffusion = new VDiffusion();
        diffusion.setDifRfa(rfa);

        return diffusion;
    }

    /**
     * Création d'un type d'activité
     *
     * @param rfa code du type d'activité
     * @return type d'activité
     */
    private VTypeActivite createTypeActivite(String rfa) {
        VTypeActivite typeActivite = new VTypeActivite();
        typeActivite.setTaRfa(rfa);

        return typeActivite;
    }

    /**
     * Création d'une destination
     *
     * @param rfa code d'une destination
     * @return destination
     */
    private VDestination createDestination(String rfa) {
        VDestination destination = new VDestination();
        destination.setDestRfa(rfa);

        return destination;
    }

    /**
     * Création d'une campagne
     *
     * @param rfa code de la campagne
     * @return campagne
     */
    private VCampagne createCampagne(String rfa, Date datefin) {
        VCampagne campagne = new VCampagne();
        campagne.setCampRfa(rfa);
        campagne.setCampFinDt(datefin);

        return campagne;
    }

    /**
     * Création d'un domaine technique
     *
     * @param rfa code du domaine technique
     * @return domaine technique
     */
    private VDomaineTechnique createDomaineTechnique(String rfa) {
        VDomaineTechnique domaineTechnique = new VDomaineTechnique();
        domaineTechnique.setDtRfa(rfa);

        return domaineTechnique;
    }
}
