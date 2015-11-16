/*======================================================================================================*/
/* SGBD  :  PostgreSQL 9                                                                                */
/*                                                                                                      */
/* Description contenu  :  MAJ de la BD pour ARQ V2_0                                                   */
/*                                                                                                      */
/* Version : DATE       - USER - COMMENTAIRES                                                           */
/*   T0.1  : 04/11/2015 - FRDL - Initialisation                                                         */
/*======================================================================================================*/

SET search_path="analyse_risque";

drop view if exists V_CLASSE_RISQUE_V1_0;
drop view if exists V_EVALUATION_V3_0;
drop view if exists V_EXPORT_NOTE_RISQUE;
drop view if exists V_NOTE_RISQUE_V1_0;
drop view if exists V_UA_CLASSE_NOTE_RISQUE_V1_0;

COMMENT ON TABLE approbations_valides IS 'Liste des approbations "valides" pour ARQ (table de parametrage du batch)';

DROP TABLE if exists asso_peri_rappr;
CREATE TABLE asso_peri_rappr (
    asso_peri_rappr_id serial NOT NULL,
    perimetre_id integer,
    rappr_rfa character varying(20) NOT NULL,
    statut_appro_id integer
);
alter table asso_peri_rappr add constraint PK_ASSO_PERI_RAPPR primary key (asso_peri_rappr_id);   
COMMENT ON TABLE asso_peri_rappr IS 'Table association pour les approbations du perimetre';

DROP TABLE if exists asso_peri_ta;
CREATE TABLE asso_peri_ta (
    asso_peri_ta_id serial NOT NULL,
    perimetre_id integer,
    ta_rfa character varying(20) NOT NULL
);
alter table asso_peri_ta add constraint PK_ASSO_PERI_TA primary key (asso_peri_ta_id);
COMMENT ON TABLE asso_peri_ta IS 'Table association pour les types d''activité du perimetre';

DROP TABLE if exists  asso_peri_vgrille;
CREATE TABLE asso_peri_vgrille (
    asso_peri_vgrille_id serial NOT NULL,
    perimetre_id integer,
    vgr_rfa character varying(20) NOT NULL
);
alter table asso_peri_vgrille add constraint PK_ASSO_PERI_VGRILLE primary key (asso_peri_vgrille_id);
COMMENT ON TABLE asso_peri_vgrille IS 'Table association pour les grilles du perimetre';

alter table batch  add column form_id bigint;

--Mise à jour du form_id de la table batch
UPDATE batch b set  form_id = 
(select form.form_id from FORMULE_RISQUE form where  b.dt_rfa=form.dt_rfa and b.camp_rfa=form.camp_rfa ); 

DELETE FROM batch where form_id is null;

ALTER TABLE batch ALTER COLUMN form_id SET NOT NULL;


COMMENT ON TABLE batch IS 'Table de batch pour les demandes de calcul de note de risque';


alter table CLASSE_RISQUE drop constraint PK_CLASSE_RISQUE;
alter table CLASSE_RISQUE drop constraint uni_rfa_classe_risque;

drop table if exists tmp_CLASSE_RISQUE;
alter table CLASSE_RISQUE rename to tmp_CLASSE_RISQUE;

CREATE TABLE classe_risque (
    classe_id serial NOT NULL,
    dt_rfa character varying(20) NOT NULL,
    camp_rfa character varying(20) NOT NULL,
    classe_simule_on boolean NOT NULL,
    classe_auteur_creation_lb character varying(255) NOT NULL,
    classe_creation_ts character varying(255) NOT NULL,
    classe_auteur_modif_lb character varying(255),
    classe_derniere_modif_ts character varying(255)
);
alter table CLASSE_RISQUE add constraint PK_CLASSE_RISQUE primary key (CLASSE_ID);
comment on table CLASSE_RISQUE is 'Table des classes de risque';

insert into CLASSE_RISQUE (CLASSE_ID, DT_RFA, CAMP_RFA, CLASSE_SIMULE_ON, CLASSE_AUTEUR_CREATION_LB, CLASSE_CREATION_TS)
select CLASSE_ID, DT_RFA, CAMP_RFA, FALSE, 'SYSTEME', now()
from tmp_CLASSE_RISQUE;

create  index I_T_CLASSE_RISQUE_DT_CAMP_SIM on CLASSE_RISQUE (
DT_RFA,
CAMP_RFA,
CLASSE_SIMULE_ON
);

drop table if exists ELEMENT_CONTROLE;

alter table FORMULE_RISQUE
   add column FORM_SIMULE_ON BOOL;

alter table FORMULE_RISQUE
   add column FORM_NUM_SIMU_NB INT4;

alter table FORMULE_RISQUE
   rename From_Auteur_Creation_LB to FORM_AUT_CRE_LB;

alter table FORMULE_RISQUE
   rename Form_Creation_DT to FORM_CRE_TS;

alter table FORMULE_RISQUE
   rename Form_Auteur_Modif_LB to FORM_AUT_MOD_LB;

alter table FORMULE_RISQUE
   rename Form_Derniere_Modif_TS to FORM_AUT_MOD_TS;

alter table FORMULE_RISQUE
   rename From_Crit_RisqueTheorique_ON to FORM_CRIT_RISQTHEO_ON;

alter table FORMULE_RISQUE
   rename From_Crit_Zone_ON to FORM_CRIT_ZONE_ON;

alter table FORMULE_RISQUE
   rename From_Crit_Volume_ON to FORM_CRIT_VOL_ON;

alter table FORMULE_RISQUE
   rename From_Crit_Diffusion_ON to FORM_CRIT_DIFF_ON;

alter table FORMULE_RISQUE
   rename From_Crit_Destination_ON to FORM_CRIT_DEST_ON;

alter table FORMULE_RISQUE
   rename From_Crit_Note_Inspection_ON to FORM_CRIT_NI_ON;

drop table if exists lieu_controle;

DROP TABLE if exists  intervalle_classe;
CREATE TABLE intervalle_classe (
    int_classe_id serial NOT NULL,
    int_classe_rfa character varying(50) NOT NULL,
    int_classe_lb character varying(255) NOT NULL,
    int_classe_min_nb bigint NOT NULL,
    int_classe_max_nb bigint,
    int_classe_valeur_nb bigint NOT NULL,
    classe_id integer
);
alter table intervalle_classe add constraint PK_intervalle_classe primary key (int_classe_id);
alter table intervalle_classe add constraint uni_rfa_int_classe_risque UNIQUE (int_classe_rfa);
COMMENT ON TABLE intervalle_classe IS 'Table des intervalles de classes de risque';
                                
DROP TABLE if exists intervalle_volume;
CREATE TABLE intervalle_volume (
    int_vol_id serial NOT NULL,
    pvol_id integer NOT NULL,
    int_vol_min_nb bigint NOT NULL,
    int_vol_max_nb bigint,
    int_vol_poids_nb bigint NOT NULL
);
alter table intervalle_volume add constraint PK_intervalle_volume primary key (int_vol_id);
COMMENT ON TABLE intervalle_volume IS 'Table des intervalles de volume de pondération';

alter table mapping_categorie_denree drop column map_id;
drop index if exists i_t_map_ta_catden_rfa;
alter table mapping_categorie_denree add constraint PK_mapping_categorie_denree primary key (ta_rfa, catden_rfa);
COMMENT ON TABLE mapping_categorie_denree IS 'Table des mapping des catégorie de denrée avec denrée  (table de parametrage pour le batch)';

drop table if exists mode_production;

alter table note_risque add column form_id INTEGER;
alter table note_risque rename note_prec_val_nb to note_valeur_precedante_n_1_nb2;
alter table note_risque add column note_valeur_precedante_n_2_nb2 bigint;
alter table note_risque add column note_valeur_precedante_n_3_nb2 bigint;

--Mise à jour du form_id de la table note_risque
UPDATE note_risque nr set  form_id = 
(select form.form_id from FORMULE_RISQUE form where  nr.dt_rfa=form.dt_rfa and nr.camp_rfa=form.camp_rfa ); 

DELETE FROM note_risque where form_id is null;

ALTER TABLE note_risque ALTER COLUMN form_id SET NOT NULL;

DROP TABLE if exists note_risque_simule;
CREATE TABLE note_risque_simule (
    note_id serial NOT NULL,
    note_rfa character varying(50) NOT NULL,
    ua_rfa character varying(40) NOT NULL,
    form_id bigint,
    dt_rfa character varying(20) NOT NULL,
    camp_rfa character varying(20) NOT NULL,
    note_val_nb bigint,
    note_date_calcul_ds timestamp without time zone DEFAULT now() NOT NULL,
    note_p_risqu_nb2 numeric,
    note_p_vol_nb2 numeric,
    note_p_zone_nb2 numeric,
    note_p_diff_nb2 numeric,
    note_p_dest_nb2 numeric,
    note_p_ni_nb2 numeric,
    note_valeur_precedante_n_1_nb2 bigint
);
alter table note_risque_simule add constraint PK_note_risque_simule primary key (note_id);
COMMENT ON TABLE note_risque_simule IS 'Table des notes de risque simulées
N = Ri * Z * V * D * S * M
';

DROP TABLE if exists perimetre;
CREATE TABLE perimetre (
    perimetre_id serial NOT NULL,
    perimetre_rfa character varying(50) NOT NULL,
    camp_rfa character varying(20) NOT NULL,
    dt_rfa character varying(20) NOT NULL,
    peri_simule_on boolean NOT NULL,
    peri_auteur_creation_lb_ character varying(255) NOT NULL,
    peri_creation_ts timestamp without time zone NOT NULL,
    peri_auteur_modif_lb_ character varying(255),
    peri_derniere_modif_ts timestamp without time zone
);
alter table PERIMETRE  add constraint PK_PERIMETRE primary key (PERIMETRE_ID);
COMMENT ON TABLE perimetre IS 'Table des perimetres ';

COMMENT ON TABLE ponderation_note_inpection IS 'Table de pondération de la derniere note d''inspection (M)';
   
alter table ponderation_risque_theorique_approbation add column statut_appro_id integer;

COMMENT ON TABLE ponderation_risque_theorique_approbation IS 'Table d''association entre la pondération du risque theorique et les approbations et leur statut';   

COMMENT ON TABLE ponderation_risque_theorique_procede IS 'Table d''association entre la pondération du risque theorique et les procedes';

COMMENT ON TABLE ponderation_risque_theorique_produit IS 'Table d''association entre la pondération du risque theorique et les "produits ARQ"';

DROP TABLE IF EXISTS tmp_PONDERATION_VOLUME; 
alter table PONDERATION_VOLUME rename to tmp_PONDERATION_VOLUME;
alter table tmp_PONDERATION_VOLUME DROP CONSTRAINT PK_PONDERATION_VOLUME;
 
CREATE TABLE ponderation_volume (
    pvol_id serial NOT NULL,
    ta_rfa character varying(20) NOT NULL,
    uprod_rfa character varying(20) NOT NULL,
    form_id integer NOT NULL
);
alter table PONDERATION_VOLUME add constraint PK_PONDERATION_VOLUME primary key (PVOL_ID);
comment on table PONDERATION_VOLUME is 'Table de pondération du Volume (V)';

insert into PONDERATION_VOLUME (PVOL_ID, TA_RFA, UPROD_RFA, FORM_ID)
select PVOL_ID, TA_RFA, UPROD_RFA, FORM_ID
from tmp_PONDERATION_VOLUME;

alter table produit add column prod_classe_rfa character varying(20);

COMMENT ON TABLE statut IS 'Table de statut du batch';

DROP TABLE if exists statut_approbations;
CREATE TABLE statut_approbations (
    statut_appro_id SERIAL NOT NULL,
    statut_appro_lb character varying(255) NOT NULL
);
alter table statut_approbations  add constraint PK_statut_approbations primary key (statut_appro_id);
COMMENT ON TABLE statut_approbations IS 'Listes des statut d''approbations';

CREATE VIEW v_classe_risque_v1_0 AS
    SELECT intc.int_classe_rfa, cl.dt_rfa, cl.camp_rfa, intc.int_classe_lb, intc.int_classe_min_nb, intc.int_classe_max_nb, intc.int_classe_valeur_nb 
    FROM (classe_risque cl JOIN intervalle_classe intc ON ((intc.classe_id = cl.classe_id))) 
    WHERE (cl.classe_simule_on = true);

CREATE VIEW v_export_note_risque AS
    SELECT row_number() OVER (ORDER BY noterisque.note_id DESC) AS row_id, noterisque.dt_rfa, noterisque.camp_rfa, noterisque.note_val_nb, noterisque.note_p_risqu_nb2, noterisque.note_p_vol_nb2, noterisque.note_p_zone_nb2, noterisque.note_p_diff_nb2, noterisque.note_p_dest_nb2, noterisque.note_p_ni_nb2, noterisque.note_date_calcul_ds, noterisque.note_valeur_precedante_n_1_nb2, etabbdnu.etb_rfa, etab.eta_enseigne_usuel_lb, intervalleclasse.int_classe_rfa, commune.insee, commune.nomofficiel, videnmetier2.iden_valeur, videnmetier2.iden_principal_bo, geoloc.geo_localisation, typeac.ta_lb, exploitation.exp_rfa, string_agg((((vtypeidenmetier.tim_lb)::text || '&'::text) || (videnmetier.iden_valeur)::text), '|'::text) AS identifiants_etab 
    FROM (((((((((((((((usagers.v_unite_activite_v_1_0 ua 
    LEFT JOIN note_risque noterisque ON (((noterisque.ua_rfa)::text = (ua.ua_rfa)::text))) 
    LEFT JOIN usagers.v_etablissement_bdnu_v_1_0 etabbdnu ON ((etabbdnu.etb_id = ua.etb_id))) 
    LEFT JOIN usagers.v_etablissement_v1_0 etab ON ((etab.etb_id = ua.etb_id))) 
    LEFT JOIN usagers.v_adresse_v1_0 adresse ON ((adresse.adr_id = ua.localisation_adr_id))) 
    LEFT JOIN nomenclatures.v_commune_v1_0 commune ON (((commune.insee)::text = (adresse.code_insee_commune_rfa)::text))) 
    LEFT JOIN usagers.v_geolocalisation_v_1_0 geoloc ON ((geoloc.geo_id = ua.geo_id))) 
    LEFT JOIN nomenclatures.v_type_activite_v1_0 typeac ON (((typeac.ta_rfa)::text = (ua.type_activite_rfa)::text))) JOIN classe_risque classerisque ON ((((classerisque.dt_rfa)::text = (noterisque.dt_rfa)::text) AND ((classerisque.camp_rfa)::text = (noterisque.camp_rfa)::text)))) 
    JOIN intervalle_classe intervalleclasse ON ((((intervalleclasse.classe_id = classerisque.classe_id) AND (noterisque.note_val_nb >= intervalleclasse.int_classe_min_nb)) AND ((noterisque.note_val_nb < intervalleclasse.int_classe_max_nb) OR (intervalleclasse.int_classe_max_nb IS NULL))))) LEFT JOIN usagers.v_asso_etabl_id_metier_v_1_0 vassoetab ON ((vassoetab.etb_id = ua.etb_id))) LEFT JOIN usagers.v_identifiant_metier_v_1_0 videnmetier ON ((videnmetier.iden_id = vassoetab.iden_id))) LEFT JOIN nomenclatures.v_type_ident_metier_v1_0 vtypeidenmetier ON (((vtypeidenmetier.tim_rfa)::text = (videnmetier.type_ident_metier_rfa)::text))) LEFT JOIN usagers.v_asso_ua_identifiant_v_1_0 vassouaiden ON ((vassouaiden.ua_id = ua.ua_id))) LEFT JOIN usagers.v_identifiant_metier_v_1_0 videnmetier2 ON ((videnmetier2.iden_id = vassouaiden.iden_id))) LEFT JOIN usagers.v_exploitation_v_1_0 exploitation ON ((exploitation.exp_id = ua.exp_id))) GROUP BY noterisque.note_id, vassoetab.etb_id, noterisque.dt_rfa, noterisque.camp_rfa, noterisque.note_val_nb, noterisque.note_p_risqu_nb2, noterisque.note_p_vol_nb2, noterisque.note_p_zone_nb2, noterisque.note_p_diff_nb2, noterisque.note_p_dest_nb2, noterisque.note_p_ni_nb2, noterisque.note_date_calcul_ds, noterisque.note_valeur_precedante_n_1_nb2, etabbdnu.etb_rfa, etab.eta_enseigne_usuel_lb, intervalleclasse.int_classe_rfa, commune.insee, commune.nomofficiel, videnmetier2.iden_valeur, videnmetier2.iden_principal_bo, geoloc.geo_localisation, typeac.ta_lb, exploitation.exp_rfa;

CREATE VIEW v_nbrua_sim_v1_0 AS
    SELECT cr.camp_rfa, cr.dt_rfa, intc.int_classe_rfa, vadr.code_insee_commune_rfa, count(vua.ua_rfa) AS nbr_ua FROM ((((classe_risque cr JOIN note_risque nr ON (((((nr.dt_rfa)::text = (cr.dt_rfa)::text) AND ((nr.camp_rfa)::text = (cr.camp_rfa)::text)) AND (cr.classe_simule_on = true)))) JOIN intervalle_classe intc ON (((((intc.classe_id = cr.classe_id) AND ((nr.camp_rfa)::text = (cr.camp_rfa)::text)) AND (nr.note_val_nb >= intc.int_classe_min_nb)) AND ((nr.note_val_nb < intc.int_classe_max_nb) OR (intc.int_classe_max_nb IS NULL))))) JOIN usagers.v_unite_activite_v_1_0 vua ON (((nr.ua_rfa)::text = (vua.ua_rfa)::text))) JOIN usagers.v_adresse_v1_0 vadr ON ((vadr.adr_id = vua.localisation_adr_id))) WHERE (cr.classe_simule_on = false) GROUP BY cr.camp_rfa, cr.dt_rfa, intc.int_classe_rfa, vadr.code_insee_commune_rfa ORDER BY cr.camp_rfa, cr.dt_rfa, intc.int_classe_rfa;

CREATE OR REPLACE VIEW v_nbrua_v1_0 AS
    SELECT cr.camp_rfa, cr.dt_rfa, substring(intc.int_classe_rfa,1,20)::varchar(20)
     as classe_rfa, vadr.code_insee_commune_rfa, count(vua.ua_rfa) AS nbr_ua 
    FROM ((((classe_risque cr JOIN note_risque nr ON (((((nr.dt_rfa)::text = (cr.dt_rfa)::text) AND ((nr.camp_rfa)::text = (cr.camp_rfa)::text)) AND (cr.classe_simule_on = false)))) JOIN intervalle_classe intc ON (((((intc.classe_id = cr.classe_id) AND ((nr.camp_rfa)::text = (cr.camp_rfa)::text)) AND (nr.note_val_nb >= intc.int_classe_min_nb)) AND ((nr.note_val_nb < intc.int_classe_max_nb) OR (intc.int_classe_max_nb IS NULL))))) JOIN usagers.v_unite_activite_v_1_0 vua ON (((nr.ua_rfa)::text = (vua.ua_rfa)::text))) JOIN usagers.v_adresse_v1_0 vadr ON ((vadr.adr_id = vua.localisation_adr_id))) WHERE (cr.classe_simule_on = false) 
    GROUP BY cr.camp_rfa, cr.dt_rfa, intc.int_classe_rfa, vadr.code_insee_commune_rfa ORDER BY cr.camp_rfa, cr.dt_rfa, classe_rfa;
        
CREATE VIEW v_nbrua_v2_0 AS
    SELECT cr.camp_rfa, cr.dt_rfa, intc.int_classe_rfa as classe_rfa, vadr.code_insee_commune_rfa, count(vua.ua_rfa) AS nbr_ua FROM ((((classe_risque cr JOIN note_risque nr ON (((((nr.dt_rfa)::text = (cr.dt_rfa)::text) AND ((nr.camp_rfa)::text = (cr.camp_rfa)::text)) AND (cr.classe_simule_on = false)))) JOIN intervalle_classe intc ON (((((intc.classe_id = cr.classe_id) AND ((nr.camp_rfa)::text = (cr.camp_rfa)::text)) AND (nr.note_val_nb >= intc.int_classe_min_nb)) AND ((nr.note_val_nb < intc.int_classe_max_nb) OR (intc.int_classe_max_nb IS NULL))))) JOIN usagers.v_unite_activite_v_1_0 vua ON (((nr.ua_rfa)::text = (vua.ua_rfa)::text))) JOIN usagers.v_adresse_v1_0 vadr ON ((vadr.adr_id = vua.localisation_adr_id))) WHERE (cr.classe_simule_on = false) GROUP BY cr.camp_rfa, cr.dt_rfa, intc.int_classe_rfa, vadr.code_insee_commune_rfa ORDER BY cr.camp_rfa, cr.dt_rfa, classe_rfa;

CREATE VIEW v_note_risque_v1_0 AS
    SELECT note_risque.note_rfa, note_risque.ua_rfa, note_risque.dt_rfa, note_risque.camp_rfa, note_risque.note_val_nb, note_risque.note_date_calcul_ds, note_risque.note_p_vol_nb2, note_risque.note_p_risqu_nb2, note_risque.note_p_zone_nb2, note_risque.note_p_diff_nb2, note_risque.note_p_dest_nb2, note_risque.note_p_ni_nb2, note_risque.note_valeur_precedante_n_1_nb2 FROM note_risque;

CREATE VIEW v_ua_classe_note_risque_v1_0 AS
    SELECT (((((nr.ua_rfa)::text || '-'::text) || (cr.camp_rfa)::text) || '-'::text) || (cr.dt_rfa)::text) AS ua_classe_note_risque_rfa, nr.ua_rfa, cr.camp_rfa, cr.dt_rfa, nr.note_rfa FROM classe_risque cr, note_risque nr WHERE ((((cr.dt_rfa)::text = (nr.dt_rfa)::text) AND ((cr.camp_rfa)::text = (nr.camp_rfa)::text)) AND (cr.classe_simule_on = true));

CREATE OR REPLACE VIEW v_arq_inspection_ua_v1_0 AS 
 SELECT vre.ua_rfa, vre.dt_rfa, vre.nomen_rfa, vre.eval_rfa, vre.vrei_real_dt AS date_fin_insp, vre.gri_rfa
   FROM op_consolidees.v_re_inspection_ua_v1_0 vre;
   
drop index if exists I_T_CLASSE_RFA_DT_CAMP;

CREATE INDEX i_t_note_sim_note_rfa ON note_risque_simule USING btree (note_rfa);
CREATE INDEX i_t_note_sim_ua_dt_camp_rfa ON note_risque_simule USING btree (ua_rfa, dt_rfa, camp_rfa);
CREATE INDEX i_t_perimtre_dt_camp_sim ON perimetre USING btree (camp_rfa, dt_rfa, peri_simule_on);

ALTER TABLE ONLY asso_peri_rappr ADD CONSTRAINT fk_asso_peri_rappr_ref_perimetre_id FOREIGN KEY (perimetre_id) REFERENCES perimetre(perimetre_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY asso_peri_ta ADD CONSTRAINT fk_asso_peri_ta_ref_perimetre_id FOREIGN KEY (perimetre_id) REFERENCES perimetre(perimetre_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY asso_peri_vgrille  ADD CONSTRAINT fk_asso_peri_vgrille_ref_perimetre_id FOREIGN KEY (perimetre_id) REFERENCES perimetre(perimetre_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY intervalle_classe ADD CONSTRAINT fk_intervalle_classe_ref_classe_id FOREIGN KEY (classe_id) REFERENCES classe_risque(classe_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY intervalle_volume ADD CONSTRAINT fk_intervalle_volume_ref_pvol_id FOREIGN KEY (pvol_id) REFERENCES ponderation_volume(pvol_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY note_risque ADD CONSTRAINT fk_note_risque_ref_form_id FOREIGN KEY (form_id) REFERENCES formule_risque(form_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY note_risque_simule ADD CONSTRAINT fk_note_risque_sim_ref_form_id_ FOREIGN KEY (form_id) REFERENCES formule_risque(form_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE ONLY ponderation_risque_theorique_approbation  ADD CONSTRAINT fk_p_ri_ref_statut_appro_id FOREIGN KEY (statut_appro_id) REFERENCES statut_approbations(statut_appro_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
