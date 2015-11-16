set search_path = "operations_consolidees";

/*re_histo
delete from re_histo;
*/
insert into re_histo (  reh_cdn ,reh_usa_rfa,reh_crea_dt,reh_modif_dt,reh_etat_lb) VALUES (1,'32635569000024_00001',now(),now(),'ON');
insert into re_histo (  reh_cdn ,reh_usa_rfa,reh_crea_dt,reh_modif_dt,reh_etat_lb) VALUES (2,'32077440900017_00001',now(),now(),'ON');

/* reh_approbation 
delete from reh_approbation;
*/
insert into reh_approbation ( reha_app_rfa,reha_si_source_lb,reha_cdn_no,rappr_rfa,reha_statut_lb,reha_crea_dt,reha_modif_dt) VALUES ('1','jdd',1,'AGSANTRANSPP','VAL',now(),now());
insert into reh_approbation ( reha_app_rfa,reha_si_source_lb,reha_cdn_no,rappr_rfa,reha_statut_lb,reha_crea_dt,reha_modif_dt) VALUES ('2','jdd',2,'AGSANTRANSPP','VAL',now(),now());




