/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueApprobation;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProcede;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.arq.business.Produit;
import fr.gouv.agriculture.dal.sial.arq.business.constants.TestsConstants;
import fr.gouv.agriculture.dal.sial.arq.business.utils.TestFactory;
import fr.gouv.agriculture.dal.sial.arq.constante.TypeProduit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VAlimentationAnimale;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VProcede;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeProduit;
import fr.gouv.agriculture.o2.rules.ConfigureKernelRule;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * TU de la rule PonderationRisqueTheoriqueRule.
 * 
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class PonderationRisqueTheoriqueRuleTestCase {

    /**
     * The configure kernel rule.
     */
    @ClassRule
    public static ConfigureKernelRule configureKernelRule = new ConfigureKernelRule(TestsConstants.KERNEL_CONFIG);
    /**
     * Rule testée
     */
    @TestedObject
    private PonderationRisqueTheoriqueRule rule;
    /**
     * Code rfa du type activité 1
     */
    private String T_RFA_1 = "trfa1";
    /**
     * Code rfa du type activité 2
     */
    private String T_RFA_2 = "trfa2";
    /**
     * Code rfa de l'approbation 1
     */
    private String A_RFA_1 = "arfa1";
    /**
     * Code rfa de l'approbation 2
     */
    private String A_RFA_2 = "arfa2";
    /**
     * Code rfa du procede 1
     */
    private String P_RFA_1 = "prfa1";
    /**
     * Code rfa du procede 2
     */
    private String P_RFA_2 = "prfa2";
    /**
     * Code rfa du produit 1
     */
    private String P2_RFA_1 = "p2rfa1";
    /**
     * Code rfa du produit 2
     */
    private String P2_RFA_2 = "p2rfa2";
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
     * Cas d'un objet null passé en parametre (robustesse)
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testValidateNull() throws Exception {
        RuleReport report = rule.validate(null);

        assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }

    /**
     * Cas nominal passant.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testValidateNominal() throws Exception {

        FormuleRisque formuleRisque = TestFactory.createFormuleRisque();

        // Le produit de la poonderation 2 est différent du produit de la
        // ponderation 1
        PonderationRisqueTheorique ponderation1 = createPonderationRisqueTheorique(T_RFA_1, POIDS_POS, formuleRisque);
        addPonderationRisqueTheoriqueApprobation(ponderation1, A_RFA_1);
        addPonderationRisqueTheoriqueProcede(ponderation1, P_RFA_1);
        addPonderationRisqueTheoriqueProduit(ponderation1, P2_RFA_1);

        PonderationRisqueTheorique ponderation2 = createPonderationRisqueTheorique(T_RFA_1, POIDS_POS, formuleRisque);
        addPonderationRisqueTheoriqueApprobation(ponderation2, A_RFA_1);
        addPonderationRisqueTheoriqueProcede(ponderation2, P_RFA_1);
        addPonderationRisqueTheoriqueProduit(ponderation2, P2_RFA_2);

        RuleReport report = rule.validate(ponderation1);

        assertTrue(TestsConstants.MSG_RAPPORT_VIDE, report.isEmpty());
    }

    /**
     * Cas ou une ponderation risque thérorique est en double.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testValidateUniciteFail() throws Exception {

        FormuleRisque formuleRisque = TestFactory.createFormuleRisque();

        PonderationRisqueTheorique ponderation = createPonderationRisqueTheorique(T_RFA_1, POIDS_POS, formuleRisque);

        // ponderation 1 et ponderation 2 sont les memes (memes rfa et libellés)
        PonderationRisqueTheorique ponderation1 = createPonderationRisqueTheorique(T_RFA_1, POIDS_POS, formuleRisque);
        addPonderationRisqueTheoriqueApprobation(ponderation1, A_RFA_1);
        addPonderationRisqueTheoriqueProcede(ponderation1, P_RFA_1);
        addPonderationRisqueTheoriqueProduit(ponderation1, P2_RFA_1);
        ponderation.getFormuleRisque().getPonderationRisqueTheoriques().add(ponderation1);

        PonderationRisqueTheorique ponderation2 = createPonderationRisqueTheorique(T_RFA_1, POIDS_POS, formuleRisque);
        addPonderationRisqueTheoriqueApprobation(ponderation2, A_RFA_1);
        addPonderationRisqueTheoriqueProcede(ponderation2, P_RFA_1);
        addPonderationRisqueTheoriqueProduit(ponderation2, P2_RFA_1);
        ponderation.getFormuleRisque().getPonderationRisqueTheoriques().add(ponderation2);

        RuleReport report = rule.validate(ponderation);
//TODO corriger
        //assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        //assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Controlle du poids: le poids est à 0
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testValidatePoidsZero() throws Exception {
        FormuleRisque formuleRisque = TestFactory.createFormuleRisque();

        PonderationRisqueTheorique ponderation1 = createPonderationRisqueTheorique(T_RFA_1, POIDS_0, formuleRisque);

        RuleReport report = rule.validate(ponderation1);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Controlle du poids: le poids est négatif
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testValidatePoidsNeg() throws Exception {
        FormuleRisque formuleRisque = TestFactory.createFormuleRisque();

        PonderationRisqueTheorique ponderation1 = createPonderationRisqueTheorique(T_RFA_1, POIDS_NEG, formuleRisque);

        RuleReport report = rule.validate(ponderation1);

        assertFalse(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, report.isEmpty());
        List messages = report.getMessages();
        assertEquals(TestsConstants.MSG_RAPPORT_CONTIENT_1_ERREUR, messages.size(), 1);
    }

    /**
     * Création d'un objet à tester valide.
     * 
     * @param tRfa
     *            code du type d'activité
     * @return objet à tester valide
     */
    private PonderationRisqueTheorique createPonderationRisqueTheorique(String tRfa, BigDecimal poids,
            FormuleRisque formuleRisque) {
        PonderationRisqueTheorique ponderation = new PonderationRisqueTheorique();
        ponderation.setTypeActivite(createTypeActivite(tRfa));
        ponderation.setPrisqtheoPoidsNb2(poids);
        ponderation.setFormuleRisque(formuleRisque);
        formuleRisque.getPonderationRisqueTheoriques().add(ponderation);
        ponderation.setPonderationRisqueTheoriqueApprobations(new ArrayList<PonderationRisqueTheoriqueApprobation>());
        ponderation.setPonderationRisqueTheoriqueProcedes(new ArrayList<PonderationRisqueTheoriqueProcede>());
        ponderation.setPonderationRisqueTheoriqueProduits(new ArrayList<PonderationRisqueTheoriqueProduit>());

        return ponderation;
    }

    /**
     * Création et ajout d'une PonderationRisqueTheoriqueApprobation.
     * 
     * @param ponderation
     */
    private void addPonderationRisqueTheoriqueApprobation(PonderationRisqueTheorique ponderation, String rfa) {
        PonderationRisqueTheoriqueApprobation pondApprobation = new PonderationRisqueTheoriqueApprobation();
        pondApprobation.setApprobation(createApprobation(rfa));
        pondApprobation.setPonderationRisqueTheorique(ponderation);
        ponderation.getPonderationRisqueTheoriqueApprobations().add(pondApprobation);
    }

    /**
     * Création et ajout d'une PonderationRisqueTheoriqueProcede.
     * 
     * @param ponderation
     */
    private void addPonderationRisqueTheoriqueProcede(PonderationRisqueTheorique ponderation, String rfa) {
        PonderationRisqueTheoriqueProcede pondProcede = new PonderationRisqueTheoriqueProcede();
        pondProcede.setProcede(createProcede(rfa));
        pondProcede.setPonderationRisqueTheorique(ponderation);
        ponderation.getPonderationRisqueTheoriqueProcedes().add(pondProcede);
    }

    /**
     * Création et ajout d'une PonderationRisqueTheoriqueProduit.
     * 
     * @param ponderation
     */
    private void addPonderationRisqueTheoriqueProduit(PonderationRisqueTheorique ponderation, String rfa) {
        PonderationRisqueTheoriqueProduit pondProduit = new PonderationRisqueTheoriqueProduit();
        pondProduit.setProduit(createProduit(rfa));
        pondProduit.setPonderationRisqueTheorique(ponderation);
        ponderation.getPonderationRisqueTheoriqueProduits().add(pondProduit);
    }

    /**
     * Création d'un type d'activité
     * 
     * @param rfa
     *            code du type d'activité
     * @return type d'activité
     */
    private VTypeActivite createTypeActivite(String rfa) {
        VTypeActivite typeActivite = new VTypeActivite();
        typeActivite.setTaRfa(rfa);

        return typeActivite;
    }

    /**
     * Création d'une approbation
     * 
     * @param rfa
     *            code de l'approbation
     * @return approbation
     */
    private VRefApprobation createApprobation(String rfa) {
        VRefApprobation approbation = new VRefApprobation();
        approbation.setRapprRfa(rfa);
        approbation.setRapprLb(rfa);
        return approbation;
    }

    /**
     * Création d'un procédé
     * 
     * @param rfa
     *            code du procede
     * @return procede
     */
    private VProcede createProcede(String rfa) {
        VProcede procede = new VProcede();
        procede.setProcRfa(rfa);
        procede.setProcLb(rfa);
        return procede;
    }

    /**
     * Création d'un produit
     * 
     * @param rfa
     *            code du produit
     * @return produit
     */
    private Produit createProduit(String rfa) {
        Produit produit = new Produit();
        produit.setProdRfa(rfa);
        // produit.setProdLb(rfa);

        VTypeProduit typeProduit = new VTypeProduit();
        typeProduit.setTprodRfa(TypeProduit.TYPE_PROD_ALANI);
        produit.setTypeProduit(typeProduit);

        VAlimentationAnimale alani = new VAlimentationAnimale();
        alani.setAaRfa(rfa);
        alani.setAaLb(rfa);
        produit.setAlani(alani);

        return produit;
    }
}
