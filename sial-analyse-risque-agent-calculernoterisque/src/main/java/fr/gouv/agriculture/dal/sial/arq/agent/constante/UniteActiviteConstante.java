package fr.gouv.agriculture.dal.sial.arq.agent.constante;

/**
 * Classe des constantes pour les Unites d'activités.
 *
 */
public final class UniteActiviteConstante {

    /**
     * Constructeur privée
     */
    private UniteActiviteConstante() {
    }

    /**
     * Colonne ua_id
     */
    public final static String UA_ID = "ua_id";

    /**
     * Colonne ua_rfa
     */
    public final static String UA_RFA = "ua_rfa";

    /**
     * Colonne ta_rfa.
     */
    public final static String TA_RFA = "ta_rfa";

    /**
     * Colonne diffusion_rfa.
     */
    public final static String DIFFUSION_RFA = "diffusion_rfa";

    /**
     * Colonne destination_rfa array.
     */
    public final static String DESTINATION_RFA_ARRAY = "tableau_destination_rfa";

    /**
     * Colonne tableau_approbation_rfa array.
     */
    public final static String APPROBATION_RFA_ARRAY = "tableau_approbation_rfa";

    /**
     * Colonne tableau_procede_rfa array.
     */
    public final static String PROCEDE_RFA_ARRAY = "tableau_procede_rfa";

    /**
     * Colonne tableau_produit_rfa array.
     */
    public final static String PRODUIT_RFA_ARRAY = "tableau_produit_rfa";

    /**
     * Colonne note_actuelle_id
     */
    public final static String NOTE_ACTUELLE_ID = "note_actuelle_id";

    /**
     * Colonne note_actuelle_val
     */
    public final static String NOTE_ACTUELLE_VALEUR = "note_actuelle_val";

    /**
     * Colonne clef_dern_note_insp
     */
    public final static String DERN_NOTE_INSP_CLEF = "clef_dern_note_insp";

    /**
     * Colonne tableau_volume_production
     */
    public final static String VOL_PROD_ARRAY = "tableau_volume_production";

    /**
     * Colonne tableau_zone_rfa
     */
    public final static String ZONE_RFA_ARRAY = "tableau_zone_rfa";

    /**
     * Requête pour le select des UA
     */
    public final static String REQUETE_SELECT_UA = //
            "select" + "	per.ua_id as "
            + UA_ID
            + ","
            + "	per.ua_rfa as "
            + UA_RFA
            + ","
            + "	per.type_activite_rfa as "
            + TA_RFA
            + ","
            + "	per.diffusion_rfa as "
            + DIFFUSION_RFA
            + ","
            + "	array(select dest_ua.destination_rfa "
            + "       from usagers.v_asso_ua_destination_v_1_0 as dest_ua "
            + "       where dest_ua.ua_id = per.ua_id) as "
            + DESTINATION_RFA_ARRAY
            + ","
            + "	array( "
            + "	  SELECT concat (vra.rappr_rfa , ';' , vra.VREA_STATUT_LB , ';' , vra.DT_RFA )"
            + "   FROM op_consolidees.v_re_approbation_ua_v1_0 vra"
            + "   WHERE vra.ua_rfa = per.ua_rfa "
            + "   and vra.dt_rfa=ctx.dt_rfa " 
            + "  order by rappr_rfa"
            + "	)"
            + "	as "
            + APPROBATION_RFA_ARRAY
            + ","
            + "	array(select proc.procede_rfa "
            + "       from usagers.v_asso_ua_procede_v_1_0 as proc "
            + "       where proc.ua_id = per.ua_id order by procede_rfa) as "
            + PROCEDE_RFA_ARRAY
            + ","
            + "	array(("
            + "	          select	   denrees.denree_rfa as produit_rfa	   from usagers.v_asso_ua_denree_v_1_0 as denrees	   where denrees.ua_id = per.ua_id	and denrees.denree_rfa is not null"
            + "	        union"
            + "	          (select	   mcd.den_rfa as produit_rfa	   from usagers.v_asso_ua_denree_v_1_0 as denrees join analyse_risque.mapping_categorie_denree as mcd on mcd.ta_rfa=per.type_activite_rfa and mcd.catden_rfa=denrees.cat_denree_rfa	   where denrees.ua_id = per.ua_id	 and denrees.denree_rfa is null "
            + "			  order by den_rfa) "
            + "			  order by produit_rfa"
            + "	        )"
            + "	    union all"
            + "	      (select prod_aa1.ue_aa1_produit_rfa from usagers.v_asso_ua_produit_aa1_v_1_0 as prod_aa1 where prod_aa1.ua_id = per.ua_id"
            + "	    union all"
            + "	      (select type_intrant.type_intrant_rfa from usagers.v_asso_ua_type_intrant_v_1_0 as type_intrant   where type_intrant.ua_id = per.ua_id"
            + "	    union all"
            + "	      (select produits_animaux.espece_dgal_rfa   from usagers.v_asso_ua_produits_animaux_v1_0 as produits_animaux   where produits_animaux.ua_id = per.ua_id"
            + "	    union all"
            + "	      (select produits_veg.filiere_vegetal_rfa   from op_consolidees.v_asso_ua_produits_veg_v1_0 as produits_veg   where produits_veg.ua_rfa = per.ua_rfa order by filiere_vegetal_rfa)"
            + " 	  order by espece_dgal_rfa)"
            + " 	  order by type_intrant_rfa)"
            + " 	  order by ue_aa1_produit_rfa)"
            + "	  )"
            + "	as "
            + PRODUIT_RFA_ARRAY
            + ","
            + "	array(select concat (vol_product.unite_rfa , ';' , vol_product.prod_volume_nb) "
            + "       from usagers.v_production_v_1_0 as vol_product"
            + "       where vol_product.ua_id = per.ua_id) as "
            + VOL_PROD_ARRAY
            + ","
            + "	array(select zone.zone_rfa from usagers.v_asso_ua_zone_geo_v1_0 as zone where zone.ua_id = per.ua_id) as "
            + ZONE_RFA_ARRAY
            + ","
            + "	COALESCE((select concat (insp.nomen_rfa , ';' , insp.eval_rfa )"
            + "		from analyse_risque.v_arq_inspection_ua_v1_0 as insp"
            + "		join analyse_risque.ponderation_note_inpection as pni on pni.dt_rfa=ctx.dt_rfa and pni.nomen_rfa=insp.nomen_rfa and pni.eval_rfa=insp.eval_rfa"
            + "		where insp.ua_rfa = per.ua_rfa"
            + " 	and insp.dt_rfa = ctx.dt_rfa"
            + "		and insp.DATE_FIN_INSP =  (select insp_max.DATE_FIN_INSP"
            + "						from analyse_risque.v_arq_inspection_ua_v1_0 insp_max"
            + "						where insp.ua_rfa=insp_max.ua_rfa"
            + "						and insp_max.dt_rfa = ctx.dt_rfa"
            + "						order by insp_max.DATE_FIN_INSP desc"
            + "						limit 1"
            + "						)"
            + "	    order by pni.pni_poids_nb2 "
            + "		desc limit 1),'N_XX;DEF') "
            + "	   as "
            + DERN_NOTE_INSP_CLEF
            + ","
            + "	nr.note_id as "
            + NOTE_ACTUELLE_ID
            + ","
            + "	nr.note_val_nb as "
            + NOTE_ACTUELLE_VALEUR
            + "	from usagers.v_unite_activite_v_1_0 per"
            + "	join nomenclatures.v_ctx_dt_ta_v1_0 ctx on ctx.ta_rfa = per.type_activite_rfa"
            + "	left join analyse_risque.note_risque as nr on nr.ua_rfa = per.ua_rfa and nr.dt_rfa = ctx.dt_rfa and nr.camp_rfa = ?"
            + "	where ctx.dt_rfa = ?" /*+ "     and per.ua_rfa in ( select reha.ua_rfa"
             + "                         from op_consolidees.v_re_approbation_ua_v1_0 reha"
             + "                         where reha.dt_rfa=ctx.dt_rfa"
             + "                         and reha.rappr_rfa IN (SELECT fp.rappr_rfa"
             + "                                                 FROM filtre_perimetre fp"
             + "                                                 where fp.dt_rfa=ctx.dt_rfa ))"*/;

    /**
     * Requête pour le select des UA
     */
    public final static String REQUETE_SELECT_VIA_STRUCT = "select" + "	per.ua_id as "
            + UA_ID
            + " ,"
            + "	ctx.dt_rfa AS DT ,"
            + "	per.ua_rfa AS "
            + UA_RFA
            + ","
            + "	per.type_activite_rfa as "
            + TA_RFA
            + ","
            + "	per.diffusion_rfa as "
            + DIFFUSION_RFA
            + " ,"
            + "	array(select dest_ua.destination_rfa "
            + "       from usagers.v_asso_ua_destination_v_1_0 as dest_ua "
            + "       where dest_ua.ua_id = per.ua_id) as "
            + DESTINATION_RFA_ARRAY
            + ","
            + "	array( "
            + "	  SELECT concat (vra.rappr_rfa , ';' , vra.VREA_STATUT_LB , ';' , vra.DT_RFA) "
            + "   FROM op_consolidees.v_re_approbation_ua_v1_0 vra"
            + "   WHERE vra.ua_rfa = per.ua_rfa"
            + "   and vra.dt_rfa=ctx.dt_rfa "
            + "  order by rappr_rfa"
            + "	)"
            + "	as "
            + APPROBATION_RFA_ARRAY
            + ","
            + "	array(select proc.procede_rfa"
            + "       from usagers.v_asso_ua_procede_v_1_0 as proc"
            + "       where proc.ua_id = per.ua_id order by procede_rfa) as "
            + PROCEDE_RFA_ARRAY
            + " ,"
            + "	CASE"
            + "		WHEN ta.TPROD_RFA = 'DENRE' THEN"
            + "			array( 	select denrees.denree_rfa as produit_rfa from usagers.v_asso_ua_denree_v_1_0 as denrees where denrees.ua_id = per.ua_id and denrees.denree_rfa is not null"
            + "				union"
            + "				(select mcd.den_rfa as produit_rfa from usagers.v_asso_ua_denree_v_1_0 as denrees join analyse_risque.mapping_categorie_denree as mcd on mcd.ta_rfa=per.type_activite_rfa and mcd.catden_rfa=denrees.cat_denree_rfa	   where denrees.ua_id = per.ua_id	 and denrees.denree_rfa is null"
            + "			  order by den_rfa) "
            + "			  order by produit_rfa"
            + "			)"
            + "		WHEN ta.TPROD_RFA = 'ALANI' THEN"
            + "			array(	select prod_aa1.ue_aa1_produit_rfa from usagers.v_asso_ua_produit_aa1_v_1_0 as prod_aa1 where prod_aa1.ua_id = per.ua_id order by ue_aa1_produit_rfa)"
            + "		WHEN ta.TPROD_RFA = 'INTRA' THEN"
            + "			array(	select type_intrant.type_intrant_rfa from usagers.v_asso_ua_type_intrant_v_1_0 as type_intrant   where type_intrant.ua_id = per.ua_id order by type_intrant_rfa)"
            + "		WHEN ta.TPROD_RFA = 'ANIMA' THEN"
            + "			array(	select produits_animaux.espece_dgal_rfa   from usagers.v_asso_ua_produits_animaux_v1_0 as produits_animaux   where produits_animaux.ua_id = per.ua_id order by espece_dgal_rfa)"
            + "		ELSE "
            + "			array(	select produits_veg.filiere_vegetal_rfa from op_consolidees.v_asso_ua_produits_veg_v1_0 as produits_veg   where produits_veg.ua_rfa = per.ua_rfa order by filiere_vegetal_rfa)"
            + "	END as "
            + PRODUIT_RFA_ARRAY
            + ","
            + "	array(select concat (vol_product.unite_rfa , ';' , vol_product.prod_volume_nb)"
            + "       from usagers.v_production_v_1_0 as vol_product "
            + "       where vol_product.ua_id = per.ua_id) as "
            + VOL_PROD_ARRAY
            + ","
            + "	array(select zone.zone_rfa "
            + "       from usagers.v_asso_ua_zone_geo_v1_0 as zone"
            + "       where zone.ua_id = per.ua_id) as "
            + ZONE_RFA_ARRAY
            + ","
            + "	COALESCE((select concat (insp.nomen_rfa , ';' , insp.eval_rfa)"
            + "		from analyse_risque.v_arq_inspection_ua_v1_0 as insp"
            + "		join analyse_risque.ponderation_note_inpection as pni on pni.dt_rfa=ctx.dt_rfa and pni.nomen_rfa=insp.nomen_rfa and pni.eval_rfa=insp.eval_rfa"
            + "		where insp.ua_rfa = per.ua_rfa"
            + " 	and insp.dt_rfa = ctx.dt_rfa"
            + "		and insp.DATE_FIN_INSP =  (select insp_max.DATE_FIN_INSP"
            + "						from analyse_risque.v_arq_inspection_ua_v1_0 insp_max"
            + "						where insp.ua_rfa=insp_max.ua_rfa"
            + "						and insp_max.dt_rfa = ctx.dt_rfa"
            + "						order by insp_max.DATE_FIN_INSP desc"
            + "						limit 1"
            + "						)"
            + "	    order by pni.pni_poids_nb2 "
            + "		desc limit 1),'N_XX;DEF')"
            + "	   as "
            + DERN_NOTE_INSP_CLEF
            + ","
            + "	nr.note_id as "
            + NOTE_ACTUELLE_ID
            + " ,"
            + "	nr.note_val_nb as "
            + NOTE_ACTUELLE_VALEUR
            + "	from usagers.v_unite_activite_v_1_0 per"
            + "	join nomenclatures.v_ctx_dt_ta_v1_0 ctx on ctx.ta_rfa = per.type_activite_rfa"
            + "	left join analyse_risque.note_risque as nr on nr.ua_rfa = per.ua_rfa and nr.dt_rfa = ctx.dt_rfa and nr.camp_rfa = ?"
            + "	join usagers.v_adresse_v1_0 adr on  per.localisation_adr_id = adr.adr_id"
            + "	join nomenclatures.v_commune_v1_0 com on com.insee = adr.code_insee_commune_rfa"
            + "	join nomenclatures.v_arrondissement_v1_0 arron on arron.insee = com.parent_insee"
            + "	join nomenclatures.v_departement_v1_0 dep on dep.insee = arron.parent_insee"
            + "	join structures.v_structure_v_1_0 struct on"
            + "		(( struct.code_insee_dep_rfa = dep.insee and struct.tstruc_rfa = 'DEP')"
            + "		or"
            + "		( struct.code_insee_reg_rfa = (select reg.insee from nomenclatures.v_region_v1_0 reg where reg.insee = dep.parent_insee)"
            + "		    and struct.tstruc_rfa = 'REG'  ))" + "	  	and struct.struct_rfa IN (?REPLACE?)"
            + "	where ctx.dt_rfa = ?" /*+ "     and per.ua_rfa in ( select reha.ua_rfa"
             + "                         from op_consolidees.v_re_approbation_ua_v1_0 reha"
             + "                         where reha.dt_rfa=ctx.dt_rfa"
             + "                         and reha.rappr_rfa IN (SELECT fp.rappr_rfa"
             + "                                                 FROM filtre_perimetre fp"
             + "                                                 where fp.dt_rfa=ctx.dt_rfa ))"*/;
}
