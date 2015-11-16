/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDiffusion;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import static org.junit.Assert.*;


/**
 * Classe de test du comparateur utilisé pour effectuer un tri sur 1 colonne.
 *
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class Generic1ColumnListComparatorTestCase {

    /**
     * Generic SlaveList 1.
     */
    private PonderationDiffusion comparedObject;
    /**
     * GenericSlaveList 2.
     */
    private PonderationDiffusion comparedWith;
    /**
     * Comparateur testé.
     */
    @TestedObject
    private Generic1ColumnListComparator comparator;

    /**
     * Init à faire avant les tests.
     */
    @Before
    public void initTest() {
        comparedObject = new PonderationDiffusion();
        comparedObject.setDifRfa(new VDiffusion());
        comparedWith = new PonderationDiffusion();
        comparedWith.setDifRfa(new VDiffusion());
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
        comparedObject.getDifRfa().setDifLb(null);
        comparedWith.getDifRfa().setDifLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de object 2 est null.
     */
    @Test
    public void testLbObject2Null() {
        comparedObject.getDifRfa().setDifLb("BB");
        comparedWith.getDifRfa().setDifLb(null);
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou le libellé de object 1 est avant.
     */
    @Test
    public void testLbObject1Inf() {
        comparedObject.getDifRfa().setDifLb("AA");
        comparedWith.getDifRfa().setDifLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de object 2 est avant.
     */
    @Test
    public void testLbObject2Inf() {
        comparedObject.getDifRfa().setDifLb("BB");
        comparedWith.getDifRfa().setDifLb("AA");
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }
}
