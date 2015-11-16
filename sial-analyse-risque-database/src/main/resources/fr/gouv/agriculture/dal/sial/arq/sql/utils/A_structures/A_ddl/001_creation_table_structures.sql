set search_path = "structures";

drop table if exists STRUCTURE CASCADE;

/*==============================================================*/
/* Table : STRUCTURE                                            */
/*==============================================================*/
CREATE TABLE structure
(
  struct_id serial NOT NULL,
  struct_rfa character varying(22) NOT NULL, -- = STRUCT_SIGLE_LB + "_V" + STRUCT_VERSION
  struct_sigle_lb character varying(15) NOT NULL,
  struct_version_nb character varying(5) NOT NULL,
  struct_lb character varying(100) NOT NULL,
  struct_court_lb character varying(50) NOT NULL,
  struct_commentaire_lb character varying(255),
  tstruc_rfa character varying(15) NOT NULL,
  ngs_rfa character varying(5),
  ncofrac_rfa character varying(10) NOT NULL,
  struct_administrative_on boolean DEFAULT false,
  struct_delegataire_on boolean NOT NULL DEFAULT false,
  struct_partenaire_on boolean NOT NULL DEFAULT false,
  etats_rfa character varying(5) NOT NULL,
  struct_etat_dt date NOT NULL,
  struct_ad_mere_rfa character varying(22),
  struct_op_mere_rfa character varying(22),
  struct_mere_rfa character varying(22),
  aba_rfa character varying(64),
  code_insee_dep_rfa character varying(3),
  code_insee_reg_rfa character varying(3),
  adr_id integer,
  struct_num_telephone_lb character varying(14),
  struct_num_portale_lb character varying(14),
  struct_num_fax_lb character varying(14),
  struct_courriel_principal_lb character varying(100),
  struct_courriel_alerte_lb character varying(100),
  struct_courriel_optionnel_lb character varying(100),
  struct_entete_officielle_lb character varying(255),
  struct_cre_dt date,
  struct_cre_auteur_login_lb character varying(255),
  struct_cre_auteur_nom_lb character varying(100),
  struct_cre_auteur_prenom_lb character varying(100),
  struct_cre_commentaire_lb character varying(255),
  struct_mod_dt date,
  struct_mod_auteur_login_lb character varying(255),
  struct_mod_auteur_nom_lb character varying(100),
  struct_mod_auteur_prenom_lb character varying(100),
  struct_mod_commentaire_lb character varying(255),
  struct_derniere_version_on boolean NOT NULL DEFAULT true,
  ss_rfa character varying(8) NOT NULL,
  CONSTRAINT pk_t_structure PRIMARY KEY (struct_id )
);

comment on column STRUCTURE.STRUCT_RFA is
'= STRUCT_SIGLE_LB + "_V" + STRUCT_VERSION';

/*==============================================================*/
/* Index : I_STRUCT_PK                                          */
/*==============================================================*/
create unique index I_STRUCT_PK on STRUCTURE (
STRUCT_ID
);

CREATE OR REPLACE VIEW v_structure_v_1_0 AS 
 SELECT struct_id, struct_rfa, struct_sigle_lb, struct_version_nb, 
 struct_lb, struct_court_lb, struct_commentaire_lb, tstruc_rfa, 
 ngs_rfa, ncofrac_rfa, struct_administrative_on, struct_delegataire_on, 
 struct_partenaire_on, etats_rfa, struct_etat_dt, struct_ad_mere_rfa, 
 struct_op_mere_rfa, struct_mere_rfa, aba_rfa, code_insee_dep_rfa, 
 code_insee_reg_rfa, adr_id, struct_num_telephone_lb, struct_num_portale_lb, 
 struct_num_fax_lb, struct_courriel_principal_lb, struct_courriel_alerte_lb, struct_courriel_optionnel_lb, 
 struct_entete_officielle_lb, struct_cre_dt, struct_cre_auteur_login_lb, struct_cre_auteur_nom_lb, 
 struct_cre_auteur_prenom_lb, struct_cre_commentaire_lb, struct_mod_dt, struct_mod_auteur_login_lb, 
 struct_mod_auteur_nom_lb, struct_mod_auteur_prenom_lb, struct_mod_commentaire_lb AS struct_cre_auteur_commentaire_2, 
 struct_derniere_version_on
   FROM structure;
