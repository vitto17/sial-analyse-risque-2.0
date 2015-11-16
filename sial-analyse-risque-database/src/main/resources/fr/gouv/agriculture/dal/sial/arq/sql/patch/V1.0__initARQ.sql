/*======================================================================================================*/
/* SGBD  :  PostgreSQL 9                                                                                */
/*                                                                                                      */
/* Description contenu  :  Création des TABLES et VUES et INDEX                                         */
/*                                                                                                      */
/* Version : DATE       - USER - COMMENTAIRES                                                           */
/*   T0.1  : 05/05/2014 - FRDL - Initialisation                                                         */
/*   T0.2  : 21/05/2014 - FRDL - Mise à jour de Evaluation basculé en V3 et premier retour Pre-commande */
/*   T0.3  : 30/10/2014 - FRDL - Murge de tous les scripts                                              */
/*   T0.4  : 26/01/2015 - FRDL - Mise à jour pour passage en production                                 */ 
/*======================================================================================================*/

SET search_path="analyse_risque";

DROP VIEW IF EXISTS V_CLASSE_RISQUE_V1_0;

DROP VIEW IF EXISTS V_EVALUATION_V3_0;

DROP VIEW IF EXISTS V_NBRUA_V1_0;

DROP VIEW IF EXISTS V_NOTE_RISQUE_V1_0;

DROP VIEW IF EXISTS V_UA_CLASSE_NOTE_RISQUE_V1_0;

DROP VIEW IF EXISTS V_EXPORT_NOTE_RISQUE;

DROP TABLE IF EXISTS BATCH;

DROP TABLE IF EXISTS CLASSE_RISQUE;

DROP TABLE IF EXISTS ELEMENT_CONTROLE;

DROP TABLE IF EXISTS FORMULE_RISQUE;

DROP TABLE IF EXISTS LIEU_CONTROLE;

DROP TABLE IF EXISTS MODE_PRODUCTION;

DROP TABLE IF EXISTS MODIFICATION_PONDERATION;

DROP TABLE IF EXISTS NOTE_RISQUE;

DROP TABLE IF EXISTS PONDERATION_DESTINATION;

DROP TABLE IF EXISTS PONDERATION_DIFFUSION;

DROP TABLE IF EXISTS PONDERATION_NOTE_INPECTION;

DROP TABLE IF EXISTS PONDERATION_RISQUE_THEORIQUE;

DROP TABLE IF EXISTS PONDERATION_RISQUE_THEORIQUE_APPROBATION;

DROP TABLE IF EXISTS PONDERATION_RISQUE_THEORIQUE_PROCEDE;

DROP TABLE IF EXISTS PONDERATION_RISQUE_THEORIQUE_PRODUIT;

DROP TABLE IF EXISTS PONDERATION_VOLUME;

DROP TABLE IF EXISTS PONDERATION_ZONE;

DROP TABLE IF EXISTS PRODUIT;

DROP TABLE IF EXISTS STATUT;
 
DROP TABLE IF EXISTS MAPPING_CATEGORIE_DENREE;

/*==============================================================*/
/* Table : BATCH                                                */
/*==============================================================*/
create table BATCH (
   BATCH_ID             SERIAL               not null,
   STATUT_ID            INT4                 not null,
   BATCH_UTI_LB         VARCHAR(30)          not null,
   BATCH_DEMANDE_TS     TIMESTAMP                 not null,
   BATCH_DEBUT_TS       TIMESTAMP                 null,
   BATCH_FIN_TS         TIMESTAMP                 null,
   BATCH_NBR_UA_TOTAL_NB INT8                 null,
   BATCH_NBR_UA_TRAITE_NB INT8                 null,
   CAMP_RFA             VARCHAR(20)          not null,
   DT_RFA               VARCHAR(20)          not null,
   BATCH_LIST_STRUCT_RFA VARCHAR(300)       null      
);

comment on table BATCH is
'Table de batch pour les demandes de calcul de note de risque';

/*==============================================================*/
/* Table : CLASSE_RISQUE                                        */
/*==============================================================*/
create table CLASSE_RISQUE (
   CLASSE_ID            SERIAL               not null,
   CLASSE_RFA           VARCHAR(20)          not null,
   DT_RFA               VARCHAR(20)          not null,
   CAMP_RFA             VARCHAR(20)          not null,
   CLASSE_LB            VARCHAR(255)         null,
   CLASSE_VALEUR_NB     INT8                 not null,
   CLASSE_MIN_NB2       DECIMAL              not null,
   CLASSE_MAX_NB2       DECIMAL              null
);

comment on table CLASSE_RISQUE is
'Table des classes de risque';

ALTER TABLE CLASSE_RISQUE ADD CONSTRAINT UNI_RFA_CLASSE_RISQUE UNIQUE (CLASSE_RFA);

/*==============================================================*/
/* Table : ELEMENT_CONTROLE                                     */
/*==============================================================*/
create table ELEMENT_CONTROLE (
   ECON_RFA             VARCHAR(5)           not null,
   ECON_COURT_LB        VARCHAR(25)          null,
   ECON_LB              VARCHAR(100)         null,
   ECON_DEB_DT          DATE                 null,
   ECON_FIN_DT          DATE                 null,
   ECON_TRI_NB          INT2                 null
);

/*==============================================================*/
/* Table : FORMULE_RISQUE                                       */
/*==============================================================*/
create table FORMULE_RISQUE (
   FORM_ID              SERIAL               not null,
   DT_RFA               VARCHAR(20)          not null,
   CAMP_RFA             VARCHAR(20)           not null,
   From_Auteur_Creation_LB CHAR(32)             not null,
   Form_Creation_DT     TIMESTAMP                 not null,
   Form_Auteur_Modif_LB CHAR(32)             null,
   Form_Derniere_Modif_TS TIMESTAMP                 null,
   From_Crit_RisqueTheorique_ON BOOL                 not null,
   From_Crit_Zone_ON    BOOL                 not null,
   From_Crit_Volume_ON  BOOL                 not null,
   From_Crit_Diffusion_ON BOOL                 not null,
   From_Crit_Destination_ON BOOL                 not null,
   From_Crit_Note_Inspection_ON BOOL                 not null
);

comment on table FORMULE_RISQUE is
'Table des formules de risques';

/*==============================================================*/
/* Table : LIEU_CONTROLE                                        */
/*==============================================================*/
create table LIEU_CONTROLE (
   LCON_RFA             VARCHAR(5)           not null,
   LCON_COURT_LB        VARCHAR(25)          null,
   LCON_LB              VARCHAR(100)         null,
   LCON_DEB_DT          DATE                 null,
   LCON_FIN_DT          DATE                 null,
   LCON_TRI_NB          INT2                 null
);

comment on table LIEU_CONTROLE is
'

';

/*==============================================================*/
/* Table : MODE_PRODUCTION                                      */
/*==============================================================*/
create table MODE_PRODUCTION (
   MPROD_RFA            VARCHAR(5)           not null,
   MPROD_COURT_LB       VARCHAR(25)          null,
   MPROD_LB             VARCHAR(100)         null,
   MPROD_DEB_DT         DATE                 null,
   MPROD_FIN_DT         DATE                 null,
   MPROD_TRI_NB         INT2                 null
);

/*==============================================================*/
/* Table : MODIFICATION_PONDERATION                             */
/*==============================================================*/
create table MODIFICATION_PONDERATION (
   MODPOD_ID            SERIAL               not null,
   FORM_ID              INT4                 not null,
   MODPOD_CREA_TS       TIMESTAMP                 null,
   MODPOD_UTI_CREA_LB   VARCHAR(255)         null,
   MODPOD_UTI_MOD_LB    VARCHAR(255)         null,
   MODPOD_MOD_TS        TIMESTAMP                 null,
   MODPOD_TYPEPOND_LB   VARCHAR(255)         null
);

comment on table MODIFICATION_PONDERATION is
'Table des modifications de pondération';

/*==============================================================*/
/* Table : NOTE_RISQUE                                          */
/*==============================================================*/
create table NOTE_RISQUE (
   NOTE_ID              SERIAL               not null,
   NOTE_RFA             VARCHAR(50)          not null,
   UA_RFA               VARCHAR(40)          not null,
   DT_RFA               VARCHAR(20)          not null,
   CAMP_RFA             VARCHAR(20)          not null,
   NOTE_VAL_NB          INT8                 null,
   NOTE_DATE_CALCUL_DS  TIMESTAMP            not null default CURRENT_TIMESTAMP,
   NOTE_P_RISQU_NB2     DECIMAL              null,
   NOTE_P_VOL_NB2       DECIMAL              null,
   NOTE_P_ZONE_NB2      DECIMAL              null,
   NOTE_P_DIFF_NB2      DECIMAL              null,
   NOTE_P_DEST_NB2      DECIMAL              null,
   NOTE_P_NI_NB2        DECIMAL              null,
   NOTE_PREC_VAL_NB     INT8                 null
);

comment on table NOTE_RISQUE is
'Table des notes de risque
N = Ri * Z * V * D * S * M
';

/*==============================================================*/
/* Table : PONDERATION_DESTINATION                              */
/*==============================================================*/
create table PONDERATION_DESTINATION (
   PDEST_ID             SERIAL               not null,
   FORM_ID              INT4                 not null,
   DEST_RFA             VARCHAR(20)          not null,
   TA_RFA               VARCHAR(20)          not null,
   PDEST_POIDS_NB2      DECIMAL              not null
);

comment on table PONDERATION_DESTINATION is
'Table de pondération de Destination (S)';

/*==============================================================*/
/* Table : PONDERATION_DIFFUSION                                */
/*==============================================================*/
create table PONDERATION_DIFFUSION (
   PDIFF_ID             SERIAL               not null,
   FORM_ID              INT4                 not null,
   DIF_RFA              VARCHAR(20)          not null,
   PDIFF_POIDS_NB2      DECIMAL              not null
);

comment on table PONDERATION_DIFFUSION is
'Table de pondération de diffusion (D)';

/*==============================================================*/
/* Table : PONDERATION_NOTE_INPECTION                           */
/*==============================================================*/
create table PONDERATION_NOTE_INPECTION (
   PNI_ID               SERIAL               not null,
   NOMEN_RFA            VARCHAR(20)          not null,
   EVAL_RFA             VARCHAR(20)          not null,
   DT_RFA               VARCHAR(20)          not null,
   PNI_POIDS_NB2        DECIMAL            not null
);

comment on table PONDERATION_NOTE_INPECTION is
'Table de pondération de la derniere note d''inspection';

/*==============================================================*/
/* Table : PONDERATION_RISQUE_THEORIQUE                         */
/*==============================================================*/
create table PONDERATION_RISQUE_THEORIQUE (
   PRISQTHEO_ID         SERIAL               not null,
   FORM_ID              INT4                 not null,
   PRISQTHEO_POIDS_NB2  DECIMAL              not null,
   TA_RFA               VARCHAR(20)          not null
);

comment on table PONDERATION_RISQUE_THEORIQUE is
'Table de pondération du risque theorique (Ri)';

/*==============================================================*/
/* Table : PONDERATION_RISQUE_THEORIQUE_APPROBATION             */
/*==============================================================*/
create table PONDERATION_RISQUE_THEORIQUE_APPROBATION (
   PRISQTHEO_RAPPR_ID   SERIAL               not null,
   PRISQTHEO_ID         INT4                 not null,
   RAPPR_RFA            VARCHAR(20)          not null
);

comment on table PONDERATION_RISQUE_THEORIQUE_APPROBATION is
'Table d''association entre la pondération du risque theorique et l''Approbation (CONCER)';

/*==============================================================*/
/* Table : PONDERATION_RISQUE_THEORIQUE_PROCEDE                 */
/*==============================================================*/
create table PONDERATION_RISQUE_THEORIQUE_PROCEDE (
   PRISQTHEO_PROC_ID    SERIAL               not null,
   PRISQTHEO_ID         INT4                 not null,
   PROC_RFA             VARCHAR(20)          not null
);

comment on table PONDERATION_RISQUE_THEORIQUE_PROCEDE is
'Table d''association entre la pondération du risque theorique et le procede';

/*==============================================================*/
/* Table : PONDERATION_RISQUE_THEORIQUE_PRODUIT                 */
/*==============================================================*/
create table PONDERATION_RISQUE_THEORIQUE_PRODUIT (
   PRISQTHEO_PROD_ID    SERIAL               not null,
   PRISQTHEO_ID         INT4                 not null,
   PROD_ID              INT4                 not null
);

comment on table PONDERATION_RISQUE_THEORIQUE_PRODUIT is
'Table d''association entre la pondération du risque theorique et le produit ARQ';

/*==============================================================*/
/* Table : PONDERATION_VOLUME                                   */
/*==============================================================*/
create table PONDERATION_VOLUME (
   PVOL_ID              SERIAL               not null,
   TA_RFA               VARCHAR(20)          not null,
   UPROD_RFA            VARCHAR(20)          not null,
   FORM_ID              INT4                 not null,
   PVOL_S1_NB2          DECIMAL              not null,
   PVOL_S2_NB2          DECIMAL              null,
   PVOL_S3_NB2          DECIMAL              null,
   PVOL_S4_NB2          DECIMAL              null
);

comment on table PONDERATION_VOLUME is
'Table de pondération du Volume (V)';

/*==============================================================*/
/* Table : PONDERATION_ZONE                                     */
/*==============================================================*/
create table PONDERATION_ZONE (
   PZONE_ID             SERIAL               not null,
   FORM_ID              INT4                 not null,
   Z_RFA                VARCHAR(20)          not null,
   PZONE_POIDS_NB2      DECIMAL           not null
);

comment on table PONDERATION_ZONE is
'Table de pondération de la zone géographique (Z)';

/*==============================================================*/
/* Table : PRODUIT                                              */
/*==============================================================*/
create table PRODUIT (
   PROD_ID              SERIAL               not null,
   TPROD_RFA            VARCHAR(20)          null,
   PROD_RFA             VARCHAR(20)          not null
);

comment on table PRODUIT is
'Tables des produits ARQ (généralisation des "produits" NOMEN : Denrée, Alimentation Animale, Intrans, Végetaux, Animaux)';

/*==============================================================*/
/* Table : STATUT                                               */
/*==============================================================*/
create table STATUT (
   STATUT_ID            SERIAL               not null,
   STATUT_LB            VARCHAR(20)          not null
);

comment on table STATUT is
'Table de statut du bathc';

INSERT INTO analyse_risque.statut(statut_id, statut_lb) VALUES (1, 'OK');
INSERT INTO analyse_risque.statut(statut_id, statut_lb) VALUES (2, 'En cours');
INSERT INTO analyse_risque.statut(statut_id, statut_lb) VALUES (3, 'KO');
INSERT INTO analyse_risque.statut(statut_id, statut_lb) VALUES (4, 'En attente');


/*==============================================================*/
/* Table : MAPPING_CATEGORIE_DENREE                             */
/*==============================================================*/
create table MAPPING_CATEGORIE_DENREE (
  MAP_ID SERIAL NOT NULL,
  TA_RFA CHARACTER VARYING(20) NOT NULL,
  CATDEN_RFA CHARACTER VARYING(20),
  DEN_RFA CHARACTER VARYING(20)
);

comment on table MAPPING_CATEGORIE_DENREE is
'Table des mapping des catégorie de denrée avec denrée';


/*==============================================================*/
/* Vue : V_CLASSE_RISQUE_V1_0                                   */
/*==============================================================*/
create or replace view V_CLASSE_RISQUE_V1_0 as
select
   CLASSE_RISQUE.CLASSE_RFA,
   CLASSE_RISQUE.DT_RFA,
   CLASSE_RISQUE.CAMP_RFA,
   CLASSE_RISQUE.CLASSE_LB,
   CLASSE_RISQUE.CLASSE_VALEUR_NB,
   CLASSE_RISQUE.CLASSE_MIN_NB2,
   CLASSE_RISQUE.CLASSE_MAX_NB2
from
   CLASSE_RISQUE;

/*==============================================================*/
/* Vue : V_EVALUATION_V3_0                                      */
/*==============================================================*/
create or replace view V_EVALUATION_V3_0 as
select
   'N_044' as NOMEN_RFA,
   ON_RFA as EVAL_RFA,
   ON_LB as EVAL_COURT_LB,
   ON_LB as EVAL_LB,
   ON_DEB_DT as EVAL_DEB_DT,
   ON_FIN_DT as EVAL_FIN_DT,
   ON_TRI_NB as EVAL_TRI_NB
from
   NOMENCLATURES.BINAIRE
UNION
select
   'N_042' as NOMEN_RFA,
   NOT_RFA as EVAL_RFA,
   NOT_COURT_LB as EVAL_COURT_LB,
   NOT_LB as EVAL_LB,
   NOT_DEB_DT as EVAL_DEB_DT,
   NOT_FIN_DT as EVAL_FIN_DT,
   NOT_TRI_NB as EVAL_TRI_NB
from
   NOMENCLATURES.NOTATION
UNION
select
   'N_043' as NOMEN_RFA,
   CONF_RFA as EVAL_RFA,
   CONF_COURT_LB as EVAL_COURT_LB,
   CONF_LB as EVAL_LB,
   CONF_DEB_DT as EVAL_DEB_DT,
   CONF_FIN_DT as EVAL_FIN_DT,
   CONF_TRI_NB as EVAL_TRI_NB
from
   NOMENCLATURES.CONFORMITE
UNION
select
   'N_092' as NOMEN_RFA,
   NOTPV_RFA as EVAL_RFA,
   NOTPV_COURT_LB as EVAL_COURT_LB,
   NOTPV_LB as EVAL_LB,
   NOTPV_DEB_DT as EVAL_DEB_DT,
   NOTPV_FIN_DT as EVAL_FIN_DT,
   NOTPV_TRI_NB as EVAL_TRI_NB
from
   NOMENCLATURES.NOTATION_PV
UNION
select
   'N_047' as NOMEN_RFA,
   ECON_RFA as EVAL_RFA,
   ECON_COURT_LB as EVAL_COURT_LB,
   ECON_LB as EVAL_LB,
   ECON_DEB_DT as EVAL_DEB_DT,
   ECON_FIN_DT as EVAL_FIN_DT,
   ECON_TRI_NB as EVAL_TRI_NB
from
   ELEMENT_CONTROLE
UNION
select
   'N_048' as NOMEN_RFA,
   LCON_RFA as EVAL_RFA,
   LCON_COURT_LB as EVAL_COURT_LB,
   LCON_LB as EVAL_LB,
   LCON_DEB_DT as EVAL_DEB_DT,
   LCON_FIN_DT as EVAL_FIN_DT,
   LCON_TRI_NB as EVAL_TRI_NB
from
   LIEU_CONTROLE
UNION
select
   'N_051' as NOMEN_RFA,
   MPROD_RFA as EVAL_RFA,
   MPROD_COURT_LB as EVAL_COURT_LB,
   MPROD_LB as EVAL_LB,
   MPROD_DEB_DT as EVAL_DEB_DT,
   MPROD_FIN_DT as EVAL_FIN_DT,
   MPROD_TRI_NB as EVAL_TRI_NB
from
   MODE_PRODUCTION;

/*==============================================================*/
/* Vue : V_NBRUA_V1_0                                           */
/*==============================================================*/
create or replace view V_NBRUA_V1_0 as
 SELECT cr.camp_rfa, cr.dt_rfa, cr.classe_rfa, vadr.code_insee_commune_rfa, count(vua.ua_rfa) AS nbr_ua
   FROM classe_risque cr
   JOIN  note_risque nr ON nr.dt_rfa = cr.dt_rfa 
				AND nr.camp_rfa =cr.camp_rfa 
				AND nr.note_val_nb::numeric >= cr.classe_min_nb2
				AND (nr.note_val_nb::numeric < cr.classe_max_nb2 or cr.classe_max_nb2 is null)
   JOIN usagers.v_unite_activite_v_1_0 vua on nr.ua_rfa = vua.ua_rfa 
   JOIN usagers.v_adresse_v1_0 vadr on vadr.adr_id = vua.localisation_adr_id 
  GROUP BY cr.camp_rfa, cr.dt_rfa, cr.classe_rfa, vadr.code_insee_commune_rfa
  ORDER BY cr.camp_rfa, cr.dt_rfa, cr.classe_rfa;

/*==============================================================*/
/* Vue : V_NOTE_RISQUE_V1_0                                     */
/*==============================================================*/
create or replace view V_NOTE_RISQUE_V1_0 as
select
   NOTE_RISQUE.NOTE_RFA,
   NOTE_RISQUE.UA_RFA,
   NOTE_RISQUE.DT_RFA,
   NOTE_RISQUE.CAMP_RFA,
   NOTE_RISQUE.NOTE_VAL_NB,
   NOTE_RISQUE.NOTE_DATE_CALCUL_DS,
   NOTE_RISQUE.NOTE_P_VOL_NB2,
   NOTE_RISQUE.NOTE_P_RISQU_NB2,
   NOTE_RISQUE.NOTE_P_ZONE_NB2,
   NOTE_RISQUE.NOTE_P_DIFF_NB2,
   NOTE_RISQUE.NOTE_P_DEST_NB2,
   NOTE_RISQUE.NOTE_P_NI_NB2,
   NOTE_RISQUE.NOTE_PREC_VAL_NB
from
   NOTE_RISQUE;


/*==============================================================*/
/* VUE : V_UA_CLASSE_NOTE_RISQUE_V1_0                           */
/*==============================================================*/
CREATE OR REPLACE VIEW V_UA_CLASSE_NOTE_RISQUE_V1_0 AS 
  SELECT (NR.UA_RFA||'-'||CR.CAMP_RFA||'-'||CR.DT_RFA) AS UA_CLASSE_NOTE_RISQUE_RFA, NR.UA_RFA, CR.CAMP_RFA, CR.DT_RFA, NR.NOTE_RFA,CR.CLASSE_RFA
  FROM CLASSE_RISQUE CR, NOTE_RISQUE NR
  WHERE CR.DT_RFA = NR.DT_RFA 
  AND CR.CAMP_RFA = NR.CAMP_RFA
  AND NR.NOTE_VAL_NB >= CR.CLASSE_MIN_NB2
  AND (NR.NOTE_VAL_NB <= CR.CLASSE_MAX_NB2 OR CLASSE_MAX_NB2 IS NULL);

/*==============================================================*/
/* VUE : V_EXPORT_NOTE_RISQUE                                           */
/*==============================================================*/
CREATE OR REPLACE VIEW V_EXPORT_NOTE_RISQUE AS 
 SELECT ROW_NUMBER() OVER (ORDER BY NOTERISQUE.NOTE_ID DESC) AS ROW_ID, NOTERISQUE.DT_RFA, NOTERISQUE.CAMP_RFA, NOTERISQUE.NOTE_VAL_NB, NOTERISQUE.NOTE_P_RISQU_NB2, NOTERISQUE.NOTE_P_VOL_NB2, NOTERISQUE.NOTE_P_ZONE_NB2, NOTERISQUE.NOTE_P_DIFF_NB2, NOTERISQUE.NOTE_P_DEST_NB2, NOTERISQUE.NOTE_P_NI_NB2, NOTERISQUE.NOTE_DATE_CALCUL_DS, NOTERISQUE.NOTE_PREC_VAL_NB, ETABBDNU.ETB_RFA, ETAB.ETA_ENSEIGNE_USUEL_LB, CLASSERISQUE.CLASSE_RFA, COMMUNE.INSEE, COMMUNE.NOMOFFICIEL, VIDENMETIER2.IDEN_VALEUR, VIDENMETIER2.IDEN_PRINCIPAL_BO, GEOLOC.GEO_LOCALISATION, TYPEAC.TA_LB, EXPLOITATION.EXP_RFA, STRING_AGG((VTYPEIDENMETIER.TIM_LB::TEXT || '&'::TEXT) || VIDENMETIER.IDEN_VALEUR::TEXT, '|'::TEXT) AS IDENTIFIANTS_ETAB
   FROM USAGERS.V_UNITE_ACTIVITE_V_1_0 UA
   LEFT JOIN NOTE_RISQUE NOTERISQUE ON NOTERISQUE.UA_RFA::TEXT = UA.UA_RFA::TEXT
   LEFT JOIN USAGERS.V_ETABLISSEMENT_BDNU_V_1_0 ETABBDNU ON ETABBDNU.ETB_ID = UA.ETB_ID
   LEFT JOIN USAGERS.V_ETABLISSEMENT_V1_0 ETAB ON ETAB.ETB_ID = UA.ETB_ID
   LEFT JOIN USAGERS.V_ADRESSE_V1_0 ADRESSE ON ADRESSE.ADR_ID = UA.LOCALISATION_ADR_ID
   LEFT JOIN NOMENCLATURES.V_COMMUNE_V1_0 COMMUNE ON COMMUNE.INSEE::TEXT = ADRESSE.CODE_INSEE_COMMUNE_RFA::TEXT
   LEFT JOIN USAGERS.V_GEOLOCALISATION_V_1_0 GEOLOC ON GEOLOC.GEO_ID = UA.GEO_ID
   LEFT JOIN NOMENCLATURES.V_TYPE_ACTIVITE_V1_0 TYPEAC ON TYPEAC.TA_RFA::TEXT = UA.TYPE_ACTIVITE_RFA::TEXT
   JOIN CLASSE_RISQUE CLASSERISQUE ON CLASSERISQUE.DT_RFA::TEXT = NOTERISQUE.DT_RFA::TEXT AND CLASSERISQUE.CAMP_RFA::TEXT = NOTERISQUE.CAMP_RFA::TEXT AND NOTERISQUE.NOTE_VAL_NB::NUMERIC >= CLASSERISQUE.CLASSE_MIN_NB2 AND (NOTERISQUE.NOTE_VAL_NB::NUMERIC <= CLASSERISQUE.CLASSE_MAX_NB2 OR CLASSERISQUE.CLASSE_MAX_NB2 IS NULL)
   LEFT JOIN USAGERS.V_ASSO_ETABL_ID_METIER_V_1_0 VASSOETAB ON VASSOETAB.ETB_ID = UA.ETB_ID
   LEFT JOIN USAGERS.V_IDENTIFIANT_METIER_V_1_0 VIDENMETIER ON VIDENMETIER.IDEN_ID = VASSOETAB.IDEN_ID
   LEFT JOIN NOMENCLATURES.V_TYPE_IDENT_METIER_V1_0 VTYPEIDENMETIER ON VTYPEIDENMETIER.TIM_RFA::TEXT = VIDENMETIER.TYPE_IDENT_METIER_RFA::TEXT
   LEFT JOIN USAGERS.V_ASSO_UA_IDENTIFIANT_V_1_0 VASSOUAIDEN ON VASSOUAIDEN.UA_ID = UA.UA_ID
   LEFT JOIN USAGERS.V_IDENTIFIANT_METIER_V_1_0 VIDENMETIER2 ON VIDENMETIER2.IDEN_ID = VASSOUAIDEN.IDEN_ID
   LEFT JOIN USAGERS.V_EXPLOITATION_V_1_0 EXPLOITATION ON ((EXPLOITATION.EXP_ID = UA.EXP_ID))
  GROUP BY NOTERISQUE.NOTE_ID, VASSOETAB.ETB_ID, NOTERISQUE.DT_RFA, NOTERISQUE.CAMP_RFA, NOTERISQUE.NOTE_VAL_NB, NOTERISQUE.NOTE_P_RISQU_NB2, NOTERISQUE.NOTE_P_VOL_NB2, NOTERISQUE.NOTE_P_ZONE_NB2, NOTERISQUE.NOTE_P_DIFF_NB2, NOTERISQUE.NOTE_P_DEST_NB2, NOTERISQUE.NOTE_P_NI_NB2, NOTERISQUE.NOTE_DATE_CALCUL_DS, NOTERISQUE.NOTE_PREC_VAL_NB, ETABBDNU.ETB_RFA, ETAB.ETA_ENSEIGNE_USUEL_LB, CLASSERISQUE.CLASSE_RFA, COMMUNE.INSEE, COMMUNE.NOMOFFICIEL, VIDENMETIER2.IDEN_VALEUR, VIDENMETIER2.IDEN_PRINCIPAL_BO, GEOLOC.GEO_LOCALISATION, TYPEAC.TA_LB, EXPLOITATION.EXP_RFA;

---------------------------------

DROP INDEX IF EXISTS I_T_BATCH;

DROP INDEX IF EXISTS I_T_CLASSE_RFA_DT_CAMP;

DROP INDEX IF EXISTS I_T_FORM;

DROP INDEX IF EXISTS I_T_MAP_TA_RFA;

DROP INDEX IF EXISTS I_T_MODPOD_FORM_FK;

DROP INDEX IF EXISTS I_T_NOTE_RFA;

DROP INDEX IF EXISTS I_T_NOTE_UA_DT_CAMP;

DROP INDEX IF EXISTS I_T_PDEST_FORM_FK;

DROP INDEX IF EXISTS I_T_PDIFF_FORM_FK;

DROP INDEX IF EXISTS I_T_PRISQTHEO_FORM_FK;

DROP INDEX IF EXISTS I_T_PRISQTHEO_TAPPR_FK;

DROP INDEX IF EXISTS I_T_PRISQTHEO_PROC_FK;

DROP INDEX IF EXISTS I_T_PRISQTHEO_PROD_FK;

DROP INDEX IF EXISTS I_T_PVOL_FORM_FK;

DROP INDEX IF EXISTS I_T_PZONE_FORM_FK;

alter table BATCH
   add constraint PK_BATCH primary key (BATCH_ID);

/*==============================================================*/
/* Index : I_T_BATCH                                            */
/*==============================================================*/
create  index I_T_BATCH on BATCH (
BATCH_UTI_LB
);

alter table CLASSE_RISQUE
   add constraint PK_CLASSE_RISQUE primary key (CLASSE_ID);

/*==============================================================*/
/* Index : I_T_CLASSE_RFA_DT_CAMP                               */
/*==============================================================*/
create  index I_T_CLASSE_RFA_DT_CAMP on CLASSE_RISQUE (
CLASSE_RFA,
DT_RFA,
CAMP_RFA
);

alter table ELEMENT_CONTROLE
   add constraint PK_ELEMENT_CONTROLE primary key (ECON_RFA);

alter table FORMULE_RISQUE
   add constraint PK_FORMULE_RISQUE primary key (FORM_ID);

/*==============================================================*/
/* Index : I_T_FORM                                             */
/*==============================================================*/
create  index I_T_FORM on FORMULE_RISQUE (
DT_RFA,
CAMP_RFA
);

alter table LIEU_CONTROLE
   add constraint PK_LIEU_CONTROLE primary key (LCON_RFA);

alter table MODE_PRODUCTION
   add constraint PK_MODE_PRODUCTION primary key (MPROD_RFA);

alter table MODIFICATION_PONDERATION
   add constraint PK_MODIFICATION_PONDERATION primary key (MODPOD_ID);

/*==============================================================*/
/* Index : I_T_MODPOD_FORM_FK                                   */
/*==============================================================*/
create  index I_T_MODPOD_FORM_FK on MODIFICATION_PONDERATION (
FORM_ID
);

alter table NOTE_RISQUE
   add constraint PK_NOTE_RISQUE primary key (NOTE_ID);

/*==============================================================*/
/* Index : I_T_NOTE_UA_DT_CAMP                                  */
/*==============================================================*/
create  index I_T_NOTE_UA_DT_CAMP on NOTE_RISQUE (
UA_RFA,
DT_RFA,
CAMP_RFA
);

/*==============================================================*/
/* Index : I_T_NOTE_RFA                                         */
/*==============================================================*/
create  index I_T_NOTE_RFA on NOTE_RISQUE (
NOTE_RFA
);

alter table PONDERATION_DESTINATION
   add constraint PK_PONDERATION_DESTINATION primary key (PDEST_ID);

/*==============================================================*/
/* Index : I_T_PDEST_FORM_FK                                    */
/*==============================================================*/
create  index I_T_PDEST_FORM_FK on PONDERATION_DESTINATION (
FORM_ID
);

alter table PONDERATION_DIFFUSION
   add constraint PK_PONDERATION_DIFFUSION primary key (PDIFF_ID);

/*==============================================================*/
/* Index : I_T_PDIFF_FORM_FK                                    */
/*==============================================================*/
create  index I_T_PDIFF_FORM_FK on PONDERATION_DIFFUSION (
FORM_ID
);

alter table PONDERATION_NOTE_INPECTION
   add constraint PK_PONDERATION_NOTE_INPECTION primary key (PNI_ID);

alter table PONDERATION_RISQUE_THEORIQUE
   add constraint PK_PRISQTHEO_ID primary key (PRISQTHEO_ID);

/*==============================================================*/
/* Index : I_T_PRISQTHEO_FORM_FK                                */
/*==============================================================*/
create  index I_T_PRISQTHEO_FORM_FK on PONDERATION_RISQUE_THEORIQUE (
FORM_ID
);

alter table PONDERATION_RISQUE_THEORIQUE_APPROBATION
   add constraint PK_PRISQTHEO_RAPPR_ID primary key (PRISQTHEO_RAPPR_ID);

/*==============================================================*/
/* Index : I_T_PRISQTHEO_TAPPR_FK                               */
/*==============================================================*/
create  index I_T_PRISQTHEO_TAPPR_FK on PONDERATION_RISQUE_THEORIQUE_APPROBATION (
PRISQTHEO_ID
);

alter table PONDERATION_RISQUE_THEORIQUE_PROCEDE
   add constraint PK_PRISQTHEO_PROC_ID primary key (PRISQTHEO_PROC_ID);

/*==============================================================*/
/* Index : I_T_PRISQTHEO_PROC_FK                                */
/*==============================================================*/
create  index I_T_PRISQTHEO_PROC_FK on PONDERATION_RISQUE_THEORIQUE_PROCEDE (
PRISQTHEO_ID
);

alter table PONDERATION_RISQUE_THEORIQUE_PRODUIT
   add constraint PK_PRISQTHEO_PROD_ID primary key (PRISQTHEO_PROD_ID);

/*==============================================================*/
/* Index : I_T_PRISQTHEO_PROD_FK                                */
/*==============================================================*/
create  index I_T_PRISQTHEO_PROD_FK on PONDERATION_RISQUE_THEORIQUE_PRODUIT (
PROD_ID
);

alter table PONDERATION_VOLUME
   add constraint PK_PONDERATION_VOLUME primary key (PVOL_ID);

/*==============================================================*/
/* Index : I_T_PVOL_FORM_FK                                     */
/*==============================================================*/
create  index I_T_PVOL_FORM_FK on PONDERATION_VOLUME (
FORM_ID
);

alter table PONDERATION_ZONE
   add constraint PK_PONDERATION_ZONE primary key (PZONE_ID);

/*==============================================================*/
/* Index : I_T_PZONE_FORM_FK                                    */
/*==============================================================*/
create  index I_T_PZONE_FORM_FK on PONDERATION_ZONE (
FORM_ID
);

/*==============================================================*/
/* Index : I_T_PNI_DT_RFA_FK                                    */
/*==============================================================*/
create index I_T_PNI_DT_RFA_FK on PONDERATION_NOTE_INPECTION (
DT_RFA
);


alter table PRODUIT
   add constraint PK_PRODUIT primary key (PROD_ID);

alter table STATUT
   add constraint PK_STATUT primary key (STATUT_ID);

alter table BATCH
   add constraint FK_BATCH_ARQ_ASSO__STATUT foreign key (STATUT_ID)
      references STATUT (STATUT_ID)
      on delete restrict on update restrict;

alter table MODIFICATION_PONDERATION
   add constraint FK_MODIFICA_ARQ_ASSO__FORMULE_ foreign key (FORM_ID)
      references FORMULE_RISQUE (FORM_ID)
      on delete restrict on update restrict;

alter table PONDERATION_DESTINATION
   add constraint FK_PONDERAT_ARQ_ASSO__FORMULE_ foreign key (FORM_ID)
      references FORMULE_RISQUE (FORM_ID)
      on delete restrict on update restrict;

alter table PONDERATION_DIFFUSION
   add constraint FK_PONDERAT_ARQ_ASSO__FORMULE_ foreign key (FORM_ID)
      references FORMULE_RISQUE (FORM_ID)
      on delete restrict on update restrict;

alter table PONDERATION_RISQUE_THEORIQUE
   add constraint FK_PONDERAT_ARQ_ASSO__FORMULE_ foreign key (FORM_ID)
      references FORMULE_RISQUE (FORM_ID)
      on delete restrict on update restrict;

alter table PONDERATION_RISQUE_THEORIQUE_APPROBATION
   add constraint FK_PONDERAT_ARQ_ASSO__PONDERAT foreign key (PRISQTHEO_ID)
      references PONDERATION_RISQUE_THEORIQUE (PRISQTHEO_ID)
      on delete restrict on update restrict;

alter table PONDERATION_RISQUE_THEORIQUE_PROCEDE
   add constraint FK_PONDERAT_ARQ_ASSO__PONDERAT foreign key (PRISQTHEO_ID)
      references PONDERATION_RISQUE_THEORIQUE (PRISQTHEO_ID)
      on delete restrict on update restrict;

alter table PONDERATION_RISQUE_THEORIQUE_PRODUIT
   add constraint FK_PONDERAT_ARQ_ASSO__PONDERAT foreign key (PRISQTHEO_ID)
      references PONDERATION_RISQUE_THEORIQUE (PRISQTHEO_ID)
      on delete restrict on update restrict;

alter table PONDERATION_RISQUE_THEORIQUE_PRODUIT
   add constraint FK_PONDERAT_ARQ_ASSO__PRODUIT foreign key (PROD_ID)
      references PRODUIT (PROD_ID)
      on delete restrict on update restrict;

alter table PONDERATION_VOLUME
   add constraint FK_PONDERAT_ARQ_ASSO__FORMULE_ foreign key (FORM_ID)
      references FORMULE_RISQUE (FORM_ID)
      on delete restrict on update restrict;

alter table PONDERATION_ZONE
   add constraint FK_PONDERAT_ARQ_ASSO__FORMULE_ foreign key (FORM_ID)
      references FORMULE_RISQUE (FORM_ID)
      on delete restrict on update restrict;
      
DROP INDEX IF EXISTS i_t_map_ta_catden_rfa;

CREATE INDEX i_t_map_ta_catden_rfa ON analyse_risque.mapping_categorie_denree USING btree (ta_rfa COLLATE pg_catalog."default" , catden_rfa COLLATE pg_catalog."default");

/*
Donnee de reéférence
*/

TRUNCATE ANALYSE_RISQUE.CLASSE_RISQUE;

INSERT INTO ANALYSE_RISQUE.CLASSE_RISQUE (CLASSE_RFA, DT_RFA, CAMP_RFA, CLASSE_LB, CLASSE_VALEUR_NB, CLASSE_MIN_NB2, CLASSE_MAX_NB2) 
SELECT '1 ('||DT_RFA||'-'||CAMP_RFA ||')', DT_RFA,CAMP_RFA,NULL,'1',0, 99
FROM NOMENCLATURES.DOMAINE_TECHNIQUE
CROSS JOIN  NOMENCLATURES.CAMPAGNE;

INSERT INTO ANALYSE_RISQUE.CLASSE_RISQUE (CLASSE_RFA, DT_RFA, CAMP_RFA, CLASSE_LB, CLASSE_VALEUR_NB, CLASSE_MIN_NB2, CLASSE_MAX_NB2) 
SELECT '2 ('||DT_RFA||'-'||CAMP_RFA ||')', DT_RFA,CAMP_RFA,NULL,2,100, 199
FROM NOMENCLATURES.DOMAINE_TECHNIQUE
CROSS JOIN  NOMENCLATURES.CAMPAGNE;

INSERT INTO ANALYSE_RISQUE.CLASSE_RISQUE (CLASSE_RFA, DT_RFA, CAMP_RFA, CLASSE_LB, CLASSE_VALEUR_NB, CLASSE_MIN_NB2, CLASSE_MAX_NB2) 
SELECT '3 ('||DT_RFA||'-'||CAMP_RFA ||')', DT_RFA,CAMP_RFA,NULL,3,200, 479
FROM NOMENCLATURES.DOMAINE_TECHNIQUE
CROSS JOIN  NOMENCLATURES.CAMPAGNE;

INSERT INTO ANALYSE_RISQUE.CLASSE_RISQUE (CLASSE_RFA, DT_RFA, CAMP_RFA, CLASSE_LB, CLASSE_VALEUR_NB, CLASSE_MIN_NB2, CLASSE_MAX_NB2) 
SELECT '4 ('||DT_RFA||'-'||CAMP_RFA ||')', DT_RFA,CAMP_RFA,NULL,4,480, NULL
FROM NOMENCLATURES.DOMAINE_TECHNIQUE
CROSS JOIN  NOMENCLATURES.CAMPAGNE;
