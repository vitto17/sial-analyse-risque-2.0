/*======================================================================================================*/
/* SGBD  :  PostgreSQL 9                                                                                */
/*                                                                                                      */
/* Description contenu  :  Création de la table APPROBATIONS_VALIDES                                    */
/*                                                                                                      */
/* Version : DATE       - USER - COMMENTAIRES                                                           */
/*   T0.1  : 09/09/2015 - FRDL - Initialisation                                                         */
/*======================================================================================================*/

SET search_path="analyse_risque";

/*==============================================================*/
/* Création TABLE : APPROBATIONS_VALIDES                        */
/*==============================================================*/ 

DROP TABLE IF EXISTS APPROBATIONS_VALIDES;
create table APPROBATIONS_VALIDES (
    VREA_STATUT_LB         VARCHAR(255)          not null,
    constraint PK_APPROBATIONS_VALIDES primary key (VREA_STATUT_LB)
);

comment on table BATCH is
'Table de paramétrage du périmètre des approbations "valides" pour le batch qui calcule les notes de risque.';
