/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueApprobation;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.inject.annotation.TestedObject;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 * Classe de test du comparateur utilisé pour effectuer un tri sur le libellé de
 * la ponderationRisqueTheoriqueApprobation, ponderationRisqueTheoriqueProcede,
 * ponderationRisqueTheoriqueProduit.
 *
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class PonderationRiThLibelleSLComparatorTestCase {

    /**
     * Generic SlaveList 1.
     */
    private PonderationRisqueTheoriqueApprobation comparedObject;
    /**
     * GenericSlaveList 2.
     */
    private PonderationRisqueTheoriqueApprobation comparedWith;
    /**
     * Comparateur testé.
     */
    @TestedObject
    private PonderationRiThLibelleSLComparator comparator;

    /**
     * Init à faire avant les tests.
     */
    @Before
    public void initTest() {
        comparedObject = new PonderationRisqueTheoriqueApprobation();
        comparedObject.setApprobation(new VRefApprobation());
        comparedWith = new PonderationRisqueTheoriqueApprobation();
        comparedWith.setApprobation(new VRefApprobation());
    }

    /**
     * A faire après les tests.
     */
    @After
    public void clean() {
        comparedObject = null;
        comparedWith = null;
        comparator = null;
    }

    /**
     * Test le cas ou l'object 1 est null.
     */
    @Test
    public void testObject1Null() {
        assertTrue(comparator.compare(null, comparedWith) < 0);
    }

    /**
     * Test le cas ou l'object 2 est null.
     */
    @Test
    public void testObject2Null() {
        assertTrue(comparator.compare(comparedObject, null) > 0);
    }

    /**
     * Test le cas ou le libellé de object 1 est null.
     */
    @Test
    public void testLbObject1Null() {
        comparedObject.getApprobation().setRapprLb(null);
        comparedWith.getApprobation().setRapprLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de object 2 est null.
     */
    @Test
    public void testLbObject2Null() {
        comparedObject.getApprobation().setRapprLb("BB");
        comparedWith.getApprobation().setRapprLb(null);
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou le libellé de object 1 est avant.
     */
    @Test
    public void testLbObject1Inf() {
        comparedObject.getApprobation().setRapprLb("AA");
        comparedWith.getApprobation().setRapprLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de object 2 est avant.
     */
    @Test
    public void testLbObject2Inf() {
        comparedObject.getApprobation().setRapprLb("BB");
        comparedWith.getApprobation().setRapprLb("AA");
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }
}
