set search_path="nomenclatures";
/*============================================================================*/
/* Début du script                                                            */
/*============================================================================*/
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;


/*==============================================================*/
/* Table : EVALUATION                                           */
/*==============================================================*/
create table EVALUATION (
   NOMEN_RFA             VARCHAR(5)           not null,
   EVAL_RFA             VARCHAR(5)           not null,
   EVAL_COURT_LB        VARCHAR(25)          not null,
   EVAL_LB              VARCHAR(40)          not null,
   EVAL_DEB_DT          DATE                 not null,
   EVAL_FIN_DT          DATE                 null,
   EVAL_TRI_NB          INT2                 not null
);

alter table EVALUATION
   add constraint PK_EVALUATION primary key (EVAL_RFA);

/*==============================================================*/
/* Vue : V_EVALUATION_V2_0                                      */
/*==============================================================*/
create or replace view V_EVALUATION_V2_0 as
select
   'N_044' as NOMEN_RFA,
   ON_RFA as EVAL_RFA,
   ON_LB as EVAL_COURT_LB,
   ON_LB as EVAL_LB,
   ON_DEB_DT as EVAL_DEB_DT,
   ON_FIN_DT as EVAL_FIN_DT,
   ON_TRI_NB as EVAL_TRI_NB
from
   BINAIRE
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
   NOTATION
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
   CONFORMITE
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
   NOTATION_PV;



/*============================================================================*/
/* Fin du script                                                              */
/*============================================================================*/