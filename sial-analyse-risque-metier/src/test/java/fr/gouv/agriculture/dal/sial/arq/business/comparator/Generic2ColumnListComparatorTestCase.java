/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeZone;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VZone;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import static org.junit.Assert.*;

/**
 * Classe de test du comparateur utilisé pour effectuer un tri sur 2 colonnes.
 *
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class Generic2ColumnListComparatorTestCase {

    /**
     * Generic SlaveList 1.
     */
    private PonderationZone comparedObject;
    /**
     * GenericSlaveList 2.
     */
    private PonderationZone comparedWith;
    /**
     * Comparateur testé.
     */
    @TestedObject
    private Generic2ColumnListComparator comparator;

    /**
     * Init à faire avant les tests.
     */
    @Before
    public void initTest() {
        comparedObject = new PonderationZone();
        comparedObject.setZone(new VZone());
        comparedObject.getZone().setTypeZone(new VTypeZone());
        comparedWith = new PonderationZone();
        comparedWith.setZone(new VZone());
        comparedWith.getZone().setTypeZone(new VTypeZone());
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
     * Test le cas ou le libellé de la colonne 1 de object 1 est null.
     */
    @Test
    public void testLbObject1Null() {
        comparedObject.getZone().getTypeZone().setTzLb(null);
        comparedWith.getZone().getTypeZone().setTzLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de la colonne 1 de object 2 est null.
     */
    @Test
    public void testLbObject2Null() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().getTypeZone().setTzLb(null);
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou le libellé de la colonne 1 de object 1 est avant.
     */
    @Test
    public void testLbObject1Inf() {
        comparedObject.getZone().getTypeZone().setTzLb("AA");
        comparedWith.getZone().getTypeZone().setTzLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de la colonne 1 de object 2 est avant.
     */
    @Test
    public void testLbObject2Inf() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().getTypeZone().setTzLb("AA");
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou les libellés de la colonne 1 sont égaux
     */
    @Test
    public void testLbObjectEgal() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().getTypeZone().setTzLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     *
     * Test sur la 2eme colonne
     *
     */
    
    /**
     * Test le cas ou les libellés de la colonne 1 sont égaux et le libellé de
     * la colonne 2 de object 1 est null.
     */
    @Test
    public void testColum1EgalLbObject1Null() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedObject.getZone().setZlb(null);

        comparedWith.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().setZlb("BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);

    }

    /**
     * Test le cas ou les libellés de la colonne 1 sont égaux et le libellé de
     * la colonne 2 de object 2 est null.
     */
    @Test
    public void testColum1EgalLbObject2Null() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedObject.getZone().setZlb("BB");

        comparedWith.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().setZlb(null);

        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);

    }
    
    /**
     * Test le cas ou les libellés de la colonne 1 sont égaux et le libellé de
     * la colonne 2 de object 1 est inférieur.
     */
    @Test
    public void testColum1EgalLbObject1Inf() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedObject.getZone().setZlb("AA");

        comparedWith.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().setZlb("BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);

    }
    
    /**
     * Test le cas ou les libellés de la colonne 1 sont égaux et le libellé de
     * la colonne 2 de object 2 est inférieur.
     */
    @Test
    public void testColum1EgalLbObject2Inf() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedObject.getZone().setZlb("GG");

        comparedWith.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().setZlb("AA");

        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);

    }
    
    
    /**
     * Test le cas ou les libellés de la colonne 1 sont égaux et le libellé de
     * la colonne 2 sont aussi égaux.
     */
    @Test
    public void testColum1EgalLbAndColumn2Egal() {
        comparedObject.getZone().getTypeZone().setTzLb("BB");
        comparedObject.getZone().setZlb("DDD");

        comparedWith.getZone().getTypeZone().setTzLb("BB");
        comparedWith.getZone().setZlb("DDD");

        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);

    }
}
