package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.SQLException;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author frederic.danna
 */
public class PRisqueDAOTestCase {

    // @Inject // TODO FDA 2015/04 Comprendre pourquoi le @Inject ne fonctionne pas.
    // @TestedObject // TODO FDA 2015/04 Quelle utilité cette annotation ?
    private static PRisqueDAO pRisqueDAO = new PRisqueDAO();

    private static final String TA1 = new String("TA1");
    private static final String TA2 = new String("TA2");
    private static final String TA_ALIM_ANI = new String("ALIM_ANI");
    private static final String APPRO1 = new String("APPRO1");
    private static final String APPRO2 = new String("APPRO2");
    private static final String PROC1 = new String("PROC1");
    private static final String PROC2 = new String("PROC2");
    private static final String PROC_LAIT_CRU = new String("LAIT_CRU");
    private static final String PROC_LAIT_PAST = new String("LAIT_PAST");
    private static final String PROD1 = new String("PROD1");
    private static final String PROD2 = new String("PROD2");
    private static final String PROD3 = new String("PROD3");
    private static final String PROD_YAOURT = new String("YAOURT");
    private static final String PROD_FROMAGE = new String("FROMAGE");
    private static final String PROD_DESSERT_LACTE = new String("DESSERT_LACTE");

    private int tailleCacheAvantTest;
    private UniteActivite uaExemples123;

    public PRisqueDAOTestCase() {
        // Rien, pour l'instant.
    }

    @BeforeClass
    public static void setUpClass() {
        // Rien, pour l'instant.
    }

    @AfterClass
    public static void tearDownClass() {
        // Rien, pour l'instant.
    }

    @Before
    public void setUp() throws SQLException {
        uaExemples123 = new UniteActivite();
        uaExemples123.setTaRfa(TA_ALIM_ANI);
        uaExemples123.setApprobations(new String[] { APPRO1 });
        uaExemples123.setProcedes(new String[] { PROC_LAIT_CRU, PROC_LAIT_PAST });
        uaExemples123.setProduits(new String[] { PROD_YAOURT });
    }

    @After
    public void tearDown() {
        // Rien, pour l'instant.
    }

    //@Test
    public void test_getPRisque_TypeActiviteSansPonderation_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa("TA pas dans le cache");
        ua.setApprobations(new String[] {});
        ua.setProcedes(new String[] {});
        ua.setProduits(new String[] {});

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1F, pRisque);
    }

    //@Test
    public void test_getPRisque_StricteUnique_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { PROD1 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1001001001F, pRisque);
    }

    //@Test
    public void test_getPRisque_StricteUniqueApproPasDsCache_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { "APPRO pas dans cache" });
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { PROD1 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1000001001F, pRisque);
    }

    //@Test
    public void test_getPRisque_StricteUniqueProduitPasDsCache_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { "PRODUIT pas dans cache" });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1F, pRisque);
    }

    //@Test
    public void test_getPRisque_StricteMultiple_Procedes_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1, PROC2 });
        ua.setProduits(new String[] { PROD1 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1001012001F, pRisque);
    }

    //@Test
    public void test_getPRisque_Approx_MultipleProduits_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1, PROC2 });
        ua.setProduits(new String[] { PROD1, PROD2, PROD3 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1001012002F, pRisque);
    }

    //@Test
    public void test_getPRisque_Approx_UniqueProduit_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] {});
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { PROD1, PROD3 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1000001003F, pRisque);
    }

    //@Test
    public void test_getPRisque_Approx_clefProduitsUAStricteTrouveePlusieursProduitsUA_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO2 });
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { PROD1, PROD2 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1000001012F, pRisque);
    }

    //@Test
    public void test_getPRisque_StricteMultiple_Produits_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { PROD1, PROD2 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1001001012F, pRisque);
    }

    //@Test
    public void test_getPRisque_StricteMultiple_ProcedesEtProduits_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1, PROC2 });
        ua.setProduits(new String[] { PROD1, PROD2 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1001012012F, pRisque);
    }

    //@Test
    public void test_getPRisque_ApproxMultiple_Produits_N() throws Exception {

        UniteActivite ua = new UniteActivite();
        ua.setTaRfa(TA1);
        ua.setApprobations(new String[] { APPRO1 });
        ua.setProcedes(new String[] { PROC1 });
        ua.setProduits(new String[] { PROD1, PROD3 });

        PRisqueDAO.setCachePoidsRisque(cacheFictif());
        Float pRisque = pRisqueDAO.getPRisque(ua);
        Assert.assertEquals((Float) 1001001001F, pRisque);
    }

    // =========== Exemples fournis dans les SFD :

    //@Test
    public void test_getPRisque_Exemple1_N() throws Exception {
        PRisqueDAO.setCachePoidsRisque(cacheExemple1());
        Float pRisque = pRisqueDAO.getPRisque(uaExemples123);
        Assert.assertEquals((Float) 1F, pRisque);
    }

    //@Test
    public void test_getPRisque_Exemple2_N() throws Exception {
        PRisqueDAO.setCachePoidsRisque(cacheExemple2());
        Float pRisque = pRisqueDAO.getPRisque(uaExemples123);
        Assert.assertEquals((Float) 6F, pRisque);
    }

    //@Test
    public void test_getPRisque_Exemple3_N() throws Exception {
        PRisqueDAO.setCachePoidsRisque(cacheExemple3());
        Float pRisque = pRisqueDAO.getPRisque(uaExemples123);
        Assert.assertEquals((Float) 8F, pRisque);
    }

    //@Test
    public void test_getPRisque_Exemple4_N() throws Exception {

        UniteActivite uaExemple4 = new UniteActivite();
        uaExemple4.setTaRfa(TA_ALIM_ANI);
        uaExemple4.setApprobations(new String[] { APPRO1 });
        uaExemple4.setProcedes(new String[] { PROC_LAIT_CRU, PROC_LAIT_PAST });
        uaExemple4.setProduits(new String[] { PROD_DESSERT_LACTE, PROD_FROMAGE, PROD_YAOURT });

        PRisqueDAO.setCachePoidsRisque(cacheExemple4());
        Float pRisque = pRisqueDAO.getPRisque(uaExemple4);
        Assert.assertEquals((Float) 6F, pRisque);
    }

    // =========== Caches (bouchons) :

    private static Map<String, Map<String, Map<String, Map<String, Float>>>> cacheFictif() {
        Map<String, Map<String, Map<String, Map<String, Float>>>> cache = new java.util.HashMap<String, Map<String, Map<String, Map<String, Float>>>>(
                10);
        cache.put(PRisqueDAO.clef(TA1), new java.util.HashMap<String, Map<String, Map<String, Float>>>(10));
        cache.put(PRisqueDAO.clef(TA2), new java.util.HashMap<String, Map<String, Map<String, Float>>>(10));
        {
            Map<String, Map<String, Map<String, Float>>> cacheTA1 = cache.get(PRisqueDAO.clef(TA1));
            cacheTA1.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Map<String, Float>>(10));
            cacheTA1.put(PRisqueDAO.clef(APPRO1), new java.util.HashMap<String, Map<String, Float>>(10));
            {
                Map<String, Map<String, Float>> cacheTA1TouteAppro = cacheTA1.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                cacheTA1TouteAppro.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                cacheTA1TouteAppro.put(PRisqueDAO.clef(PROC1), new java.util.HashMap<String, Float>(10));
                cacheTA1TouteAppro.put(PRisqueDAO.clef(PROC2), new java.util.HashMap<String, Float>(10));
                cacheTA1TouteAppro.put(PRisqueDAO.clef(new String[] { PROC1, PROC2 }), new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTA1TouteToutProcede = cacheTA1TouteAppro.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTA1TouteToutProcede.put(PRisqueDAO.clef(PROD1), 1000000001F);
                    cacheTA1TouteToutProcede.put(PRisqueDAO.clef(PROD2), 1000000002F);
                    cacheTA1TouteToutProcede.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1000000012F);
                }
                {
                    Map<String, Float> cacheTA1TouteApproProced1 = cacheTA1TouteAppro.get(PRisqueDAO.clef(PROC1));
                    cacheTA1TouteApproProced1.put(PRisqueDAO.clef(PROD1), 1000001001F);
                    cacheTA1TouteApproProced1.put(PRisqueDAO.clef(PROD2), 1000001002F);
                    cacheTA1TouteApproProced1.put(PRisqueDAO.clef(PROD3), 1000001003F);
                    cacheTA1TouteApproProced1.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1000001012F);
                }
                {
                    Map<String, Float> cacheTA1TouteApproProced2 = cacheTA1TouteAppro.get(PRisqueDAO.clef(PROC2));
                    cacheTA1TouteApproProced2.put(PRisqueDAO.clef(PROD1), 1000002001F);
                    cacheTA1TouteApproProced2.put(PRisqueDAO.clef(PROD2), 1000002002F);
                    cacheTA1TouteApproProced2.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1000002012F);
                }
                {
                    Map<String, Float> cacheTA1TouteApproProced1Et2 = cacheTA1TouteAppro.get(PRisqueDAO.clef(new String[] { PROC1, PROC2 }));
                    cacheTA1TouteApproProced1Et2.put(PRisqueDAO.clef(PROD1), 1000012001F);
                    cacheTA1TouteApproProced1Et2.put(PRisqueDAO.clef(PROD2), 1000012002F);
                    cacheTA1TouteApproProced1Et2.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1000012012F);
                }
            }
            {
                Map<String, Map<String, Float>> cacheTA1Appro1 = cacheTA1.get(PRisqueDAO.clef(APPRO1));
                cacheTA1Appro1.put(PRisqueDAO.clef(PROC1), new java.util.HashMap<String, Float>(10));
                cacheTA1Appro1.put(PRisqueDAO.clef(PROC2), new java.util.HashMap<String, Float>(10));
                cacheTA1Appro1.put(PRisqueDAO.clef(new String[] { PROC1, PROC2 }), new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTA1Appro1Proced1 = cacheTA1Appro1.get(PRisqueDAO.clef(PROC1));
                    cacheTA1Appro1Proced1.put(PRisqueDAO.clef(PROD1), 1001001001F);
                    cacheTA1Appro1Proced1.put(PRisqueDAO.clef(PROD2), 1001001002F);
                    cacheTA1Appro1Proced1.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1001001012F);
                }
                {
                    Map<String, Float> cacheTA1Appro1Proced2 = cacheTA1Appro1.get(PRisqueDAO.clef(PROC2));
                    cacheTA1Appro1Proced2.put(PRisqueDAO.clef(PROD1), 1001002001F);
                    cacheTA1Appro1Proced2.put(PRisqueDAO.clef(PROD2), 1001002002F);
                    cacheTA1Appro1Proced2.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1001002012F);
                }
                {
                    Map<String, Float> cacheTAATouteApproProced1Et2 = cacheTA1Appro1.get(PRisqueDAO.clef(new String[] { PROC1, PROC2 }));
                    cacheTAATouteApproProced1Et2.put(PRisqueDAO.clef(PROD1), 1001012001F);
                    cacheTAATouteApproProced1Et2.put(PRisqueDAO.clef(PROD2), 1001012002F);
                    cacheTAATouteApproProced1Et2.put(PRisqueDAO.clef(new String[] { PROD1, PROD2 }), 1001012012F);
                }
            }
        }
        {
            Map<String, Map<String, Map<String, Float>>> cacheTA2 = cache.get(PRisqueDAO.clef(TA2));
            cacheTA2.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Map<String, Float>>(10));
            {
                Map<String, Map<String, Float>> cacheTA2TouteAppro = cacheTA2.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                cacheTA2TouteAppro.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTA2TouteApproToutProced = cacheTA2TouteAppro.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTA2TouteApproToutProced.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 2000000000F);
                }
            }
        }
        return cache;
    }

    private static Map<String, Map<String, Map<String, Map<String, Float>>>> cacheExemple1() {
        Map<String, Map<String, Map<String, Map<String, Float>>>> cache = new java.util.HashMap<String, Map<String, Map<String, Map<String, Float>>>>(
                10);
        cache.put(PRisqueDAO.clef(TA_ALIM_ANI), new java.util.HashMap<String, Map<String, Map<String, Float>>>(10));
        {
            Map<String, Map<String, Map<String, Float>>> cacheTAAlimAni = cache.get(pRisqueDAO.clef(TA_ALIM_ANI));
            cacheTAAlimAni.put(PRisqueDAO.clef(APPRO1), new java.util.HashMap<String, Map<String, Float>>(10));
            cacheTAAlimAni.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Map<String, Float>>(10));
            {
                Map<String, Map<String, Float>> cacheTA1Appro1 = cacheTAAlimAni.get(PRisqueDAO.clef(APPRO1));
                cacheTA1Appro1.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU, PROC_LAIT_PAST }), new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAATouteApproProcedLaitCruEtLaitPast = cacheTA1Appro1.get(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU,
                            PROC_LAIT_PAST }));
                    cacheTAATouteApproProcedLaitCruEtLaitPast.put(PRisqueDAO.clef(PROD_YAOURT), 1F);
                    cacheTAATouteApproProcedLaitCruEtLaitPast.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 2F);
                }
            }
            {
                Map<String, Map<String, Float>> cacheTAAlimAniTouteAppro = cacheTAAlimAni.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU, PROC_LAIT_PAST }),
                        new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniTouteApproProcedLaitCruEtLaitPast = cacheTAAlimAniTouteAppro.get(PRisqueDAO.clef(new String[] {
                            PROC_LAIT_CRU, PROC_LAIT_PAST }));
                    cacheTAAlimAniTouteApproProcedLaitCruEtLaitPast.put(PRisqueDAO.clef(PROD_YAOURT), 3F);
                    cacheTAAlimAniTouteApproProcedLaitCruEtLaitPast.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 4F);
                }
            }
        }
        return cache;
    }

    private static Map<String, Map<String, Map<String, Map<String, Float>>>> cacheExemple2() {
        Map<String, Map<String, Map<String, Map<String, Float>>>> cache = new java.util.HashMap<String, Map<String, Map<String, Map<String, Float>>>>(
                10);
        cache.put(PRisqueDAO.clef(TA_ALIM_ANI), new java.util.HashMap<String, Map<String, Map<String, Float>>>(10));
        {
            Map<String, Map<String, Map<String, Float>>> cacheTAAlimAni = cache.get(pRisqueDAO.clef(TA_ALIM_ANI));
            cacheTAAlimAni.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Map<String, Float>>(10));
            cacheTAAlimAni.put(PRisqueDAO.clef(APPRO1), new java.util.HashMap<String, Map<String, Float>>(10));
            {
                Map<String, Map<String, Float>> cacheTAAlimAniTouteAppro = cacheTAAlimAni.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.clef(new String[] { PROC_LAIT_PAST }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniTouteApproProcedLaitPast = cacheTAAlimAniTouteAppro.get(PRisqueDAO
                            .clef(new String[] { PROC_LAIT_PAST }));
                    cacheTAAlimAniTouteApproProcedLaitPast.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 1F);
                }
                {
                    Map<String, Float> cacheTAAlimAniTouteApproToutProced = cacheTAAlimAniTouteAppro.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTAAlimAniTouteApproToutProced.put(PRisqueDAO.clef(PROD_FROMAGE), 2F);
                    cacheTAAlimAniTouteApproToutProced.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 3F);
                }
            }
            {
                Map<String, Map<String, Float>> cacheTAAlimAniAppro1 = cacheTAAlimAni.get(PRisqueDAO.clef(APPRO1));
                cacheTAAlimAniAppro1.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniAppro1.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniAppro1ProcedLaitCru = cacheTAAlimAniAppro1.get(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU }));
                    cacheTAAlimAniAppro1ProcedLaitCru.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 4F);
                }
                {
                    Map<String, Float> cacheTAAlimAniAppro1ToutProced = cacheTAAlimAniAppro1.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTAAlimAniAppro1ToutProced.put(PRisqueDAO.clef(PROD_FROMAGE), 5F);
                    cacheTAAlimAniAppro1ToutProced.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 6F);
                }
            }
        }
        return cache;
    }

    private static Map<String, Map<String, Map<String, Map<String, Float>>>> cacheExemple3() {
        Map<String, Map<String, Map<String, Map<String, Float>>>> cache = new java.util.HashMap<String, Map<String, Map<String, Map<String, Float>>>>(
                10);
        cache.put(PRisqueDAO.clef(TA_ALIM_ANI), new java.util.HashMap<String, Map<String, Map<String, Float>>>(10));
        {
            Map<String, Map<String, Map<String, Float>>> cacheTAAlimAni = cache.get(pRisqueDAO.clef(TA_ALIM_ANI));
            cacheTAAlimAni.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Map<String, Float>>(10));
            cacheTAAlimAni.put(PRisqueDAO.clef(APPRO1), new java.util.HashMap<String, Map<String, Float>>(10));
            {
                Map<String, Map<String, Float>> cacheTAAlimAniTouteAppro = cacheTAAlimAni.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU, PROC_LAIT_PAST }),
                        new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniTouteApproProcedLaitCru = cacheTAAlimAniTouteAppro.get(PRisqueDAO
                            .clef(new String[] { PROC_LAIT_CRU }));
                    cacheTAAlimAniTouteApproProcedLaitCru.put(PRisqueDAO.clef(PROD_YAOURT), 1F);
                }
                {
                    Map<String, Float> cacheTAAlimAniTouteApproProcedLaitCruEtLaitPast = cacheTAAlimAniTouteAppro.get(PRisqueDAO.clef(new String[] {
                            PROC_LAIT_CRU, PROC_LAIT_PAST }));
                    cacheTAAlimAniTouteApproProcedLaitCruEtLaitPast.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 2F);
                }
                {
                    Map<String, Float> cacheTAAlimAniTouteApproToutProced = cacheTAAlimAniTouteAppro.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTAAlimAniTouteApproToutProced.put(PRisqueDAO.clef(PROD_YAOURT), 3F);
                    cacheTAAlimAniTouteApproToutProced.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 4F);
                }
            }
            {
                Map<String, Map<String, Float>> cacheTAAlimAniAppro1 = cacheTAAlimAni.get(PRisqueDAO.clef(APPRO1));
                cacheTAAlimAniAppro1.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniAppro1.put(PRisqueDAO.clef(new String[] { PROC_LAIT_PAST }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniAppro1.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniAppro1ProcedLaitCru = cacheTAAlimAniAppro1.get(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU }));
                    cacheTAAlimAniAppro1ProcedLaitCru.put(PRisqueDAO.clef(PROD_YAOURT), 5F);
                }
                {
                    Map<String, Float> cacheTAAlimAniAppro1ProcedLaitPast = cacheTAAlimAniAppro1
                            .get(PRisqueDAO.clef(new String[] { PROC_LAIT_PAST }));
                    cacheTAAlimAniAppro1ProcedLaitPast.put(PRisqueDAO.clef(PROD_YAOURT), 6F);
                    cacheTAAlimAniAppro1ProcedLaitPast.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 7F);
                }
                {
                    Map<String, Float> cacheTAAlimAniAppro1ToutProced = cacheTAAlimAniAppro1.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTAAlimAniAppro1ToutProced.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 8F);
                }
            }
        }
        return cache;
    }

    private static Map<String, Map<String, Map<String, Map<String, Float>>>> cacheExemple4() {
        Map<String, Map<String, Map<String, Map<String, Float>>>> cache = new java.util.HashMap<String, Map<String, Map<String, Map<String, Float>>>>(
                10);
        cache.put(PRisqueDAO.clef(TA_ALIM_ANI), new java.util.HashMap<String, Map<String, Map<String, Float>>>(10));
        {
            Map<String, Map<String, Map<String, Float>>> cacheTAAlimAni = cache.get(pRisqueDAO.clef(TA_ALIM_ANI));
            cacheTAAlimAni.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Map<String, Float>>(10));
            cacheTAAlimAni.put(PRisqueDAO.clef(APPRO1), new java.util.HashMap<String, Map<String, Float>>(10));
            {
                Map<String, Map<String, Float>> cacheTAAlimAniTouteAppro = cacheTAAlimAni.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.clef(new String[] { PROC_LAIT_CRU }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.clef(new String[] { PROC_LAIT_PAST }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniTouteAppro.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniTouteApproProcedLaitCru = cacheTAAlimAniTouteAppro.get(PRisqueDAO
                            .clef(new String[] { PROC_LAIT_CRU }));
                    cacheTAAlimAniTouteApproProcedLaitCru.put(PRisqueDAO.clef(PROD_YAOURT), 1F);
                }
                {
                    Map<String, Float> cacheTAAlimAniTouteApproProcedLaitPast = cacheTAAlimAniTouteAppro.get(PRisqueDAO
                            .clef(new String[] { PROC_LAIT_PAST }));
                    cacheTAAlimAniTouteApproProcedLaitPast.put(PRisqueDAO.clef(PROD_FROMAGE), 2F);
                }
                {
                    Map<String, Float> cacheTAAlimAniTouteApproToutProced = cacheTAAlimAniTouteAppro.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTAAlimAniTouteApproToutProced.put(PRisqueDAO.clef(new String[] { PROD_FROMAGE, PROD_YAOURT }), 3F);
                }
            }
            {
                Map<String, Map<String, Float>> cacheTAAlimAniAppro1 = cacheTAAlimAni.get(PRisqueDAO.clef(APPRO1));
                cacheTAAlimAniAppro1.put(PRisqueDAO.clef(new String[] { PROC_LAIT_PAST }), new java.util.HashMap<String, Float>(10));
                cacheTAAlimAniAppro1.put(PRisqueDAO.CLEF_TOUTES_VALEURS, new java.util.HashMap<String, Float>(10));
                {
                    Map<String, Float> cacheTAAlimAniAppro1ProcedLaitPast = cacheTAAlimAniAppro1
                            .get(PRisqueDAO.clef(new String[] { PROC_LAIT_PAST }));
                    cacheTAAlimAniAppro1ProcedLaitPast.put(PRisqueDAO.clef(PROD_YAOURT), 4F);
                    cacheTAAlimAniAppro1ProcedLaitPast.put(PRisqueDAO.clef(PROD_FROMAGE), 5F);
                }
                {
                    Map<String, Float> cacheTAAlimAniAppro1ToutProced = cacheTAAlimAniAppro1.get(PRisqueDAO.CLEF_TOUTES_VALEURS);
                    cacheTAAlimAniAppro1ToutProced.put(PRisqueDAO.CLEF_TOUTES_VALEURS, 6F);
                }
            }
        }
        return cache;
    }

    // =========== Test des fonctions utilitaires :

    @Test
    public void test_valeurs_N() {
        String[] valeurs = pRisqueDAO.valeurs("<VAL1><VAL2><VAL3>");
        Assert.assertNotNull(valeurs);
        Assert.assertEquals(3, valeurs.length);
        Assert.assertEquals("VAL1", valeurs[0]);
        Assert.assertEquals("VAL2", valeurs[1]);
        Assert.assertEquals("VAL3", valeurs[2]);
    }

    @Test
    public void test_poidsMax_N() {
        Float poidsMax = pRisqueDAO.poidsMax(cacheFictif());
        Assert.assertNotNull(poidsMax);
        Assert.assertEquals((Float) 2000000000F, poidsMax);
    }

    @Test
    public void test_cloneCache_N() {

        Map<String, Map<String, Map<String, Map<String, Float>>>> cacheOrigine = cacheFictif();
        Map<String, Map<String, Map<String, Map<String, Float>>>> cacheClone = pRisqueDAO.cloneCache(cacheOrigine);

        Assert.assertNotSame(cacheClone, cacheOrigine);

        Assert.assertEquals(cacheClone.keySet().size(), cacheOrigine.keySet().size());
        for (String key1 : cacheOrigine.keySet()) {
            Assert.assertEquals(cacheClone.get(key1).keySet().size(), cacheOrigine.get(key1).keySet().size());
            for (String key2 : cacheOrigine.get(key1).keySet()) {
                Assert.assertEquals(cacheClone.get(key1).get(key2).keySet().size(), cacheOrigine.get(key1).get(key2).keySet().size());
                for (String key3 : cacheOrigine.get(key1).get(key2).keySet()) {
                    Assert.assertEquals(cacheClone.get(key1).get(key2).get(key3).keySet().size(), cacheOrigine.get(key1).get(key2).get(key3).keySet()
                            .size());
                    for (String key4 : cacheOrigine.get(key1).get(key2).get(key3).keySet()) {
                        Float fOrigine = cacheOrigine.get(key1).get(key2).get(key3).get(key4);
                        Float fClone = cacheClone.get(key1).get(key2).get(key3).get(key4);
                        Assert.assertEquals(fClone, fOrigine);
                    }
                }
            }
        }
    }
}
