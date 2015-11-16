/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueApprobation;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProcede;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.arq.business.Produit;
import fr.gouv.agriculture.dal.sial.arq.constante.TypeProduit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VAlimentationAnimale;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VProcede;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeProduit;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import static org.junit.Assert.*;

/**
 * Classe de test du comparateur utilisé pour effectuer un tri de la ponderation
 * risque théorique sur plusieurs colonnes, le type d'activité, la liste des
 * approbations, la liste des procédés, la liste des produits.
 *
 * @author pegaltier
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class PonderationRisqueTheoriqueComparatorTestCase {

    /**
     * Generic SlaveList 1.
     */
    private PonderationRisqueTheorique comparedObject;
    /**
     * GenericSlaveList 2.
     */
    private PonderationRisqueTheorique comparedWith;
    /**
     * Comparateur testé.
     */
    @TestedObject
    private PonderationRisqueTheoriqueComparator comparator;

    /**
     * Init à faire avant les tests.
     */
    @Before
    public void initTest() {
        comparedObject = new PonderationRisqueTheorique();
        comparedObject.setTypeActivite(new VTypeActivite());
        comparedObject.setPonderationRisqueTheoriqueApprobations(new ArrayList<PonderationRisqueTheoriqueApprobation>());
        comparedObject.setPonderationRisqueTheoriqueProcedes(new ArrayList<PonderationRisqueTheoriqueProcede>());
        comparedObject.setPonderationRisqueTheoriqueProduits(new ArrayList<PonderationRisqueTheoriqueProduit>());

        comparedWith = new PonderationRisqueTheorique();
        comparedWith.setTypeActivite(new VTypeActivite());
        comparedWith.setPonderationRisqueTheoriqueApprobations(new ArrayList<PonderationRisqueTheoriqueApprobation>());
        comparedWith.setPonderationRisqueTheoriqueProcedes(new ArrayList<PonderationRisqueTheoriqueProcede>());
        comparedWith.setPonderationRisqueTheoriqueProduits(new ArrayList<PonderationRisqueTheoriqueProduit>());

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
     * Test le cas ou le libellé colonne 1 de object 1 est null.
     */
    @Test
    public void testLbCol1Object1Null() {
        comparedObject.getTypeActivite().setTaLb(null);
        comparedWith.getTypeActivite().setTaLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé colonne 1 de object 2 est null.
     */
    @Test
    public void testLbCol1Object2Null() {
        comparedObject.getTypeActivite().setTaLb("BB");
        comparedWith.getTypeActivite().setTaLb(null);
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou le libellé colonne 1 de object 1 est avant.
     */
    @Test
    public void testLbCol1Object1Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        comparedWith.getTypeActivite().setTaLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé colonne 1 de object 2 est avant.
     */
    @Test
    public void testLbCol1Object2Inf() {
        comparedObject.getTypeActivite().setTaLb("BB");
        comparedWith.getTypeActivite().setTaLb("AA");
        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou le libellé colonne 1 de object 1 est egal.
     */
    @Test
    public void testLbCol1Object1Egal() {
        comparedObject.getTypeActivite().setTaLb("BB");
        comparedWith.getTypeActivite().setTaLb("BB");
        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);
    }

    /**
     * Test le cas ou la colonne 1 est egal et le libellé colonne 2 de object 1
     * est null.
     */
    @Test
    public void testLbCol2Object1Null() {
        comparedObject.getTypeActivite().setTaLb("BB");
        comparedObject.setPonderationRisqueTheoriqueApprobations(null);
        comparedWith.getTypeActivite().setTaLb("BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);
    }

    /**
     * Test le cas ou le libellé de la colonne 1 est egal et le libellé colonne
     * 1 de object 2 est null.
     */
    @Test
    public void testLbCol2Object2Null() {
        comparedObject.getTypeActivite().setTaLb("BB");
        comparedWith.getTypeActivite().setTaLb("BB");
        comparedWith.setPonderationRisqueTheoriqueApprobations(null);
        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);
    }

    /**
     * Les tests ci-dessous permettent de tester
     * PonderationRisqueTheoriqueApprobation dans le cas ou le TypeActivite est
     * égal.
     */
    /**
     * Test le cas ou le libellé de la colonne 1 est egal et le libellé colonne
     * 2 de object 1 est avant.
     */
    @Test
    public void testLbCol2Object1Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "AA");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou le libellé de la colonne 1 est egal et le libellé colonne
     * 2 de object 2 est avant.
     */
    @Test
    public void testLbCol2Object2Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "BB");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "AA");

        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou les libellés de la colonne 1 est egal et le libellé
     * colonne 2 est aussi égal.
     */
    @Test
    public void testLbCol2Object1Egal() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "BB");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);
    }

    /**
     * Les tests ci-dessous permettent de tester
     * PonderationRisqueTheoriqueProcede dans le cas ou le TypeActivite est égal
     * et PonderationRisqueTheoriqueApprobation est aussi égal.
     */
    /**
     * Test le cas ou les libellés de la colonne 1 et la colonne 2 sont egal et
     * le libellé colonne 3 de object 1 est avant.
     */
    @Test
    public void testLbCol3Object1Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "AA");
        addPonderationRisqueTheoriqueProcede(comparedObject, "AA");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "AA");
        addPonderationRisqueTheoriqueProcede(comparedWith, "BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou les libellés de la colonne 1 et la colonne 2 sont egal et
     * le libellé colonne 3 de object 2 est avant.
     */
    @Test
    public void testLbCol3Object2Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "AA");
        addPonderationRisqueTheoriqueProcede(comparedObject, "BB");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "AA");
        addPonderationRisqueTheoriqueProcede(comparedWith, "AA");

        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou les libellés de la colonne 1 et la colonne 2 sont egal et
     * le libellé colonne 3 est aussi égal.
     */
    @Test
    public void testLbCol3Object1Egal() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "BB");
        addPonderationRisqueTheoriqueProcede(comparedObject, "BB");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "BB");
        addPonderationRisqueTheoriqueProcede(comparedWith, "BB");

        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);
    }

    /**
     * Les tests ci-dessous permettent de tester
     * PonderationRisqueTheoriqueProduit dans le cas ou le TypeActivite est égal
     * et PonderationRisqueTheoriqueApprobation est aussi égal et
     * PonderationRisqueTheoriqueProcede est aussi égal.
     */
    /**
     * Test le cas ou les libellés de la colonne 1 et la colonne 2 et la colonne
     * 3 sont egal et le libellé colonne 4 de object 1 est avant.
     */
    @Test
    public void testLbCol4Object1Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "AA");
        addPonderationRisqueTheoriqueProcede(comparedObject, "AA");
        addPonderationRisqueTheoriqueProduit(comparedObject, "GAZGH");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "AA");
        addPonderationRisqueTheoriqueProcede(comparedWith, "BB");
        addPonderationRisqueTheoriqueProduit(comparedWith, "ZZZ");

        assertTrue(comparator.compare(comparedObject, comparedWith) < 0);
    }

    /**
     * Test le cas ou les libellés de la colonne 1 et la colonne 2 et la colonne
     * 3 sont egal et le libellé colonne 4 de object 2 est avant.
     */
    @Test
    public void testLbCol4Object2Inf() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "AA");
        addPonderationRisqueTheoriqueProcede(comparedObject, "AA");
        addPonderationRisqueTheoriqueProduit(comparedObject, "GG");

        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "AA");
        addPonderationRisqueTheoriqueProcede(comparedWith, "AA");
        addPonderationRisqueTheoriqueProduit(comparedWith, "AA");

        assertTrue(comparator.compare(comparedObject, comparedWith) > 0);
    }

    /**
     * Test le cas ou les libellés de la colonne 1 et la colonne 2 et de la
     * colonne 3 sont egal et le libellé colonne 4 est aussi égal.
     */
    @Test
    public void testLbCol4Object1Egal() {
        comparedObject.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedObject, "BB");
        addPonderationRisqueTheoriqueProcede(comparedObject, "BB");
        addPonderationRisqueTheoriqueProduit(comparedObject, "GG");
        
        comparedWith.getTypeActivite().setTaLb("AA");
        addPonderationRisqueTheoriqueApprobation(comparedWith, "BB");
        addPonderationRisqueTheoriqueProcede(comparedWith, "BB");
        addPonderationRisqueTheoriqueProduit(comparedWith, "GG");

        assertTrue(comparator.compare(comparedObject, comparedWith) == 0);
    }

    private void addPonderationRisqueTheoriqueApprobation(PonderationRisqueTheorique object, String lbl) {
        PonderationRisqueTheoriqueApprobation appro = new PonderationRisqueTheoriqueApprobation();
        appro.setApprobation(new VRefApprobation());
        appro.getApprobation().setRapprLb(lbl);
        object.getPonderationRisqueTheoriqueApprobations().add(appro);
    }

    private void addPonderationRisqueTheoriqueProcede(PonderationRisqueTheorique object, String lbl) {
        PonderationRisqueTheoriqueProcede proc = new PonderationRisqueTheoriqueProcede();
        proc.setProcede(new VProcede());
        proc.getProcede().setProcLb(lbl);
        object.getPonderationRisqueTheoriqueProcedes().add(proc);
    }

    private void addPonderationRisqueTheoriqueProduit(PonderationRisqueTheorique object, String lbl) {
        PonderationRisqueTheoriqueProduit proc = new PonderationRisqueTheoriqueProduit();
        proc.setProduit(createProduit(lbl));
        object.getPonderationRisqueTheoriqueProduits().add(proc);
    }

    /**
     * Création d'un produit
     *
     * @param rfa code du produit
     * @return produit
     */
    private Produit createProduit(String rfa) {
        Produit produit = new Produit();
        produit.setProdRfa(rfa);
//        produit.setProdLb(rfa);
        
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
