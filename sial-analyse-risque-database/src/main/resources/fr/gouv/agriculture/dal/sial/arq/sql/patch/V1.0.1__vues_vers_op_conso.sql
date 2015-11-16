/*======================================================================================================*/
/* SGBD  :  PostgreSQL 9                                                                                */
/*                                                                                                      */
/* Description contenu  :  Création des VUES vers OP_CONSO                                              */
/*                                                                                                      */
/* Version : DATE       - USER - COMMENTAIRES                                                           */
/*   T0.1  : 26/01/2015 - FRDL - Initialisation                                                         */
/*======================================================================================================*/

SET search_path="analyse_risque";

/*==============================================================*/
/* VUE : V_ARQ_INSPECTION_UA_V1_0                           */
/*==============================================================*/  
CREATE OR REPLACE VIEW ANALYSE_RISQUE.V_ARQ_INSPECTION_UA_V1_0 AS(
  SELECT REH.USA_RFA AS UA_RFA, REHI.DT_RFA, REHI.NOMEN_RFA, REHI.EVAL_RFA, REHI.VREI_REAL_DT AS DATE_FIN_INSP
  FROM OP_CONSOLIDEES.V_RE_INSPECTION_V1_0 REHI
    JOIN OP_CONSOLIDEES.V_RE_HISTO_V1_0 REH ON REHI.EVENEMENT_RFA = REH.EVENEMENT_RFA AND REH.NIV_USAGER_RFA::TEXT = 'UA'::TEXT
  WHERE REHI.ETATI_RFA::TEXT = 'VAL'::TEXT
UNION ALL 
  SELECT REH.USA_RFA AS UA_RFA, REHI.DT_RFA, REHI.NOMEN_RFA, REHI.EVAL_RFA,REHI.VREI_REAL_DT AS DATE_FIN_INSP
  FROM OP_CONSOLIDEES.V_RE_INSPECTION_V1_0 REHI
    JOIN OP_CONSOLIDEES.V_RE_HISTO_V1_0 REH ON REHI.EVENEMENT_RFA = REH.EVENEMENT_RFA AND REH.NIV_USAGER_RFA::TEXT = 'ETA'::TEXT
    JOIN USAGERS.V_UNITE_ACTIVITE_V_1_0 VUA ON REH.USA_RFA::TEXT = VUA.UA_RFA::TEXT
    JOIN USAGERS.V_ETABLISSEMENT_BDNU_V_1_0 VEB ON VUA.ETB_ID = VEB.ETB_ID
  WHERE REHI.ETATI_RFA::TEXT = 'VAL'::TEXT);
