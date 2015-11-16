/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import fr.gouv.agriculture.dal.sial.arq.business.constants.TestsConstants;
import fr.gouv.agriculture.dal.sial.arq.business.utils.TestFactory;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import fr.gouv.agriculture.o2.rules.ConfigureKernelRule;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;
import java.math.BigDecimal;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

/**
 * TU de la rule PonderationVolumeRule.
 *
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class PonderationVolumeRuleTestCase {

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
    private PonderationVolumeRule rule;
    /**
     * Code rfa du type activité 1
     */
    private String T_RFA_1 = "trfa1";
    /**
     * Code rfa du type activité 2
     */
    private String T_RFA_2 = "trfa2";
    /**
     * Code rfa de l'unité production 1
     */
    private String U_RFA_1 = "urfa1";
    /**
     * Code rfa de l'unité production 2
     */
    private String U_RFA_2 = "urfa2";
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

        PonderationVolume ponderation1 = createPonderationVolume(T_RFA_1, U_RFA_1, POIDS_POS, formuleRisque);
        createPonderationVolume(T_RFA_2, U_RFA_2, POIDS_POS, formuleRisque);

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

        PonderationVolume ponderation1 = createPonderationVolume(T_RFA_1, U_RFA_1, POIDS_0, formuleRisque);

        RuleReport report = rule.validate(ponderation1);

        assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }

    /**
     * Controlle du poids: le poids est à 0
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidatePoidsNull() throws Exception {
        FormuleRisque formuleRisque = TestFactory.createFormuleRisque();

        PonderationVolume ponderation1 = createPonderationVolume(T_RFA_1, U_RFA_1, null, formuleRisque);

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

        PonderationVolume ponderation1 = createPonderationVolume(T_RFA_1, U_RFA_1, POIDS_NEG, formuleRisque);

        RuleReport report = rule.validate(ponderation1);

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
}
