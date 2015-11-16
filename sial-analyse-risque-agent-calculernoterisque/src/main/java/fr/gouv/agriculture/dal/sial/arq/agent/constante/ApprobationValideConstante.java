package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constantes pour les approbations valides.
 */
public final class ApprobationValideConstante {

    /**
     * Constructeur privée
     */
    private ApprobationValideConstante() {
    }

    /**
     * Constante pour la requete de chargement du cache.
     */
    public final static String REQUETE_CHARGE_CACHE = //
            "select VREA_STATUT_LB" +//
            " from analyse_risque.APPROBATIONS_VALIDES";

}
