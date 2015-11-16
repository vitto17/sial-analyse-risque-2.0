package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Constante pour la classe Risque.
 */
public final class PRisqueConstante {

    /**
     * Constructeur privé
     */
    private PRisqueConstante() {
    }

    /**
     * Requête pour récupérer la pondération du risque théorique.
     */
    public final static String REQUETE_SELECT_P_RISQUE = //
            "select "
            + //
            // "	prt.prisqtheo_id, " +//
            "	ta_rfa,"
            + //
            "	array(select rappr_rfa from analyse_risque.ponderation_risque_theorique_approbation as prt_approbation where prt.prisqtheo_id=prt_approbation.prisqtheo_id order by rappr_rfa) as array_approbations,"
            + // NB Il faut trier les RFA.
            "	array(select proc_rfa from analyse_risque.ponderation_risque_theorique_procede as prt_procede where prt.prisqtheo_id=prt_procede.prisqtheo_id order by proc_rfa) as array_procedes,"
            + // NB Il faut trier les RFA.
            "	array(" + //
            "		select prod_rfa " + //
            "		from analyse_risque.ponderation_risque_theorique_produit as prt_produit " + //
            "			left join analyse_risque.produit as produit on prt_produit.prod_id=produit.prod_id " + //
            "		where prt.prisqtheo_id=prt_produit.prisqtheo_id" + //
            "       order by prod_rfa" + // NB Il faut trier les RFA.
            "	) as array_produits," + //
            "	prt.prisqtheo_poids_nb2 " + //
            "from analyse_risque.ponderation_risque_theorique as prt " + //
            "where form_id = ?";

}
