/*======================================================================================================*/
/* SGBD  :  PostgreSQL 9                                                                                */
/*                                                                                                      */
/* Description contenu  :  MAJ valeur de référence de la table PONDERATION_NOTE_INPECTION               */
/*                         MAJ de la vue V_ARQ_INSPECTION_UA_V1_0                                       */
/*                                                                                                      */
/* Version : DATE       - USER - COMMENTAIRES                                                           */
/*   T0.1  : 17/02/2015 - FRDL - Initialisation                                                         */
/*======================================================================================================*/

SET search_path="analyse_risque";

/*==============================================================*/
/* MAJ TABLE : PONDERATION_NOTE_INPECTION                       */
/*==============================================================*/ 
TRUNCATE PONDERATION_NOTE_INPECTION;
 
INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT 'N_XX','DEF',4,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE
WHERE DT_RFA LIKE 'SS%';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT 'N_XX','DEF',2.5,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE
WHERE DT_RFA NOT LIKE 'SS%';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,1,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_042' and eval_rfa='A';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,2,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_042' and eval_rfa='B';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,3,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_042' and eval_rfa='C';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,4,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_042' and eval_rfa='D';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,1,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_043' and eval_rfa='CONF';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,2,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_043' and eval_rfa='NCONF';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,1,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_092' and eval_rfa='CONF';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,2,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_092' and eval_rfa='NCMIN';

INSERT INTO PONDERATION_NOTE_INPECTION (NOMEN_RFA, EVAL_RFA, PNI_POIDS_NB2, DT_RFA) 
SELECT nomen_rfa,EVAL_RFA,3,DT_RFA
FROM NOMENCLATURES.DOMAINE_TECHNIQUE 
CROSS JOIN v_evaluation_v3_0 where nomen_rfa='N_092' and eval_rfa='NCMAJ';

/*==============================================================*/
/* VUE : V_ARQ_INSPECTION_UA_V1_0                           */
/*==============================================================*/  
DROP VIEW V_ARQ_INSPECTION_UA_V1_0;

CREATE OR REPLACE VIEW V_ARQ_INSPECTION_UA_V1_0 AS
SELECT UA_RFA, DT_RFA, NOMEN_RFA, EVAL_RFA, VREI_REAL_DT AS DATE_FIN_INSP
FROM OP_CONSOLIDEES.V_RE_INSPECTION_UA_V1_0
WHERE ETATI_RFA = 'VAL';
