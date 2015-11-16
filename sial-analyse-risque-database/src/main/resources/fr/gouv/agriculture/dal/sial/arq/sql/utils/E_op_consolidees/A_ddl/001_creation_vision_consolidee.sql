/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  06/06/2014 16:19:11                      */
/*==============================================================*/

SET search_path="operations_consolidees";

DROP VIEW IF EXISTS V_RE_APPROBATION_V1_0;

DROP VIEW IF EXISTS V_RE_EVENEMENT_V1_0;

DROP VIEW IF EXISTS V_RE_INSPECTION_V1_0;

DROP VIEW IF EXISTS V_RE_SUITE_V1_0;

DROP TABLE IF EXISTS REH_APPROBATION CASCADE;

DROP TABLE IF EXISTS REH_INSPECTION CASCADE;

DROP TABLE IF EXISTS REH_SUITE CASCADE;

DROP TABLE IF EXISTS RE_HISTO CASCADE;

/*==============================================================*/
/* Table : REH_APPROBATION                                      */
/*==============================================================*/
create table REH_APPROBATION (
   REHA_APP_RFA         VARCHAR(30)          not null,
   REHA_SI_SOURCE_LB    VARCHAR(10)          not null,
   REHA_CDN_NO          NUMERIC              not null,
   REHA_APP_NO          VARCHAR(60)          null,
   RAPPR_RFA            VARCHAR(20)          null,
   TAPPR_RFA            VARCHAR(20)          null,
   REHA_STATUT_DT       TIMESTAMP            null,
   REHA_STATUT_LB       VARCHAR(255)         null,
   REHA_CREA_DT         TIMESTAMP            not null,
   REHA_MODIF_DT        TIMESTAMP            not null,
   REHA_ETAT_LB         VARCHAR(3)           null,
   constraint PK_REH_APPROBATION primary key (REHA_APP_RFA, REHA_SI_SOURCE_LB)
);

/*==============================================================*/
/* Table : REH_INSPECTION                                       */
/*==============================================================*/
create table REH_INSPECTION (
   REHI_INSP_RFA        VARCHAR(30)          not null,
   REHI_SI_SOURCE_LB    VARCHAR(10)          not null,
   REHI_CDN_NO          NUMERIC              not null,
   REHI_INSP_NO         VARCHAR(30)          null,
   DT_RFA               VARCHAR(20)          null,
   REHI_GRI_RFA         VARCHAR(50)          null,
   REHI_REAL_DT         TIMESTAMP            null,
   NOMEN_RFA       VARCHAR(30)          null,
   EVAL_RFA        VARCHAR(20)          null,
   ETATI_RFA            VARCHAR(20)          null,
   CONT_RFA             VARCHAR(20)          null,
   REHI_RAP_INSP_ON     VARCHAR(1)           null,
   REHI_RES_LB          VARCHAR(60)          null,
   REHI_ORG_INSP_RFA    VARCHAR(120)         null,
   REHI_CREA_DT         TIMESTAMP            null,
   REHI_MODIF_DT        TIMESTAMP            null,
   REHI_ETAT_LB         VARCHAR(3)           null,
   constraint PK_REH_INSPECTION primary key (REHI_INSP_RFA, REHI_SI_SOURCE_LB)
);

/*==============================================================*/
/* Table : REH_SUITE                                            */
/*==============================================================*/
create table REH_SUITE (
   REHS_SUITE_RFA       VARCHAR(30)          not null,
   REHS_SI_SOURCE_LB    VARCHAR(10)          not null,
   REHS_CDN_NO          NUMERIC              not null,
   REHS_SUITE_NO        NUMERIC              null,
   REHS_SUITE_CAT_LB    VARCHAR(255)         null,
   REHS_DECISION_DT     TIMESTAMP            null,
   REHS_CREA_DT         TIMESTAMP            null,
   REHS_MODIF_DT        TIMESTAMP            null,
   REHS_ETAT_LB         VARCHAR(3)           null,
   constraint PK_REH_SUITE primary key (REHS_SUITE_RFA, REHS_SI_SOURCE_LB)
);

/*==============================================================*/
/* Table : RE_HISTO                                             */
/*==============================================================*/
create table RE_HISTO (
   REH_CDN              NUMERIC              not null,
   REH_USA_RFA          VARCHAR(50)          not null,
   REH_CREA_DT          DATE                 not null,
   REH_MODIF_DT         DATE                 not null,
   REH_ETAT_LB          VARCHAR(3)           null,
   constraint PK_RE_HISTO primary key (REH_CDN)
);

/*==============================================================*/
/* Vue : V_RE_APPROBATION_V1_0                                  */
/*==============================================================*/
create or replace view V_RE_APPROBATION_V1_0 as
select
   REH_APPROBATION.REHA_APP_RFA,
   REH_APPROBATION.REHA_SI_SOURCE_LB,
   REH_APPROBATION.REHA_APP_NO,
   REH_APPROBATION.RAPPR_RFA,
   REH_APPROBATION.TAPPR_RFA,
   REH_APPROBATION.REHA_STATUT_DT,
   REH_APPROBATION.REHA_STATUT_LB
from
   REH_APPROBATION;

/*==============================================================*/
/* Vue : V_RE_EVENEMENT_V1_0                                    */
/*==============================================================*/
create or replace view V_RE_EVENEMENT_V1_0 (DOSSIER_RFA, SI_SOURCE_LB, UA_RFA) as
select
   REH_INSPECTION.REHI_INSP_RFA as DOSSIER_RFA,
   REH_INSPECTION.REHI_SI_SOURCE_LB as SI_SOURCE_LB,
   REH_USA_RFA as UA_RFA
from
   RE_HISTO,REH_INSPECTION
where 
    RE_HISTO.REH_CDN=REH_INSPECTION.REHI_CDN_NO
union
select
   REH_SUITE.REHS_SUITE_RFA as DOSSIER_RFA,
   REH_SUITE.REHS_SI_SOURCE_LB as SI_SOURCE_LB,
   REH_USA_RFA as UA_RFA
from
   RE_HISTO,REH_SUITE
where 
    RE_HISTO.REH_CDN=REH_SUITE.REHS_CDN_NO
union
select
   REH_APPROBATION.REHA_APP_RFA as DOSSIER_RFA,
   REH_APPROBATION.REHA_SI_SOURCE_LB as SI_SOURCE_LB,
   REH_USA_RFA as UA_RFA
from
   RE_HISTO,REH_APPROBATION
where 
    RE_HISTO.REH_CDN=REH_APPROBATION.REHA_CDN_NO  ;

/*==============================================================*/
/* Vue : V_RE_INSPECTION_V1_0                                   */
/*==============================================================*/
create or replace view V_RE_INSPECTION_V1_0 as
select
   REH_INSPECTION.REHI_INSP_RFA,
   REH_INSPECTION.REHI_SI_SOURCE_LB,
   REH_INSPECTION.REHI_INSP_NO,
   REH_INSPECTION.DT_RFA,
   REH_INSPECTION.REHI_GRI_RFA,
   REH_INSPECTION.REHI_REAL_DT,
   REH_INSPECTION.NOMEN_RFA,
   REH_INSPECTION.EVAL_RFA,
   REH_INSPECTION.ETATI_RFA,
   REH_INSPECTION.CONT_RFA,
   REH_INSPECTION.REHI_RAP_INSP_ON,
   REH_INSPECTION.REHI_RES_LB,
   REH_INSPECTION.REHI_ORG_INSP_RFA
from
   REH_INSPECTION;

/*==============================================================*/
/* Vue : V_RE_SUITE_V1_0                                        */
/*==============================================================*/
create or replace view V_RE_SUITE_V1_0 as
select
   REH_SUITE.REHS_SUITE_RFA,
   REH_SUITE.REHS_SI_SOURCE_LB,
   REH_SUITE.REHS_SUITE_NO,
   REH_SUITE.REHS_SUITE_CAT_LB,
   REH_SUITE.REHS_DECISION_DT
from
   REH_SUITE;

alter table REH_APPROBATION
   add constraint FK_REH_APPR_ASSO_RE_H_RE_HISTO foreign key (REHA_CDN_NO)
      references RE_HISTO (REH_CDN)
      on delete restrict on update restrict;

alter table REH_INSPECTION
   add constraint FK_REH_INSP_ASSO_RE_H_RE_HISTO foreign key (REHI_CDN_NO)
      references RE_HISTO (REH_CDN)
      on delete restrict on update restrict;

alter table REH_SUITE
   add constraint FK_REH_SUIT_ASSO_RE_H_RE_HISTO foreign key (REHS_CDN_NO)
      references RE_HISTO (REH_CDN)
      on delete restrict on update restrict;

