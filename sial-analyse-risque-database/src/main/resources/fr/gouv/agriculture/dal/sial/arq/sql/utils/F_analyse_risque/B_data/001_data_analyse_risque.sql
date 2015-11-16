set search_path = "analyse_risque";

DO $$

DECLARE 
	val_formule1 INT4;
	val_formule2 INT4;
	val_prisqTheoId1 INT4;
	val_prisqTheoId2 INT4;
	val_prisqTheoId3 INT4;
	val_prodId1 INT4;
	val_prodId2 INT4;
	val_prodId3 INT4;

BEGIN
DELETE FROM mapping_categorie_denree;
delete from CLASSE_RISQUE;
DELETE from NOTE_RISQUE;
DELETE FROM PONDERATION_RISQUE_THEORIQUE_PRODUIT ;
DELETE FROM PRODUIT;
DELETE FROM PONDERATION_RISQUE_THEORIQUE_PROCEDE;
DELETE FROM PONDERATION_RISQUE_THEORIQUE;
DELETE FROM PONDERATION_DESTINATION;
DELETE from ponderation_volume;
DELETE from formule_risque;

--formule_risque
insert into formule_risque (dt_rfa,camp_rfa,from_auteur_creation_lb,form_creation_dt,from_crit_risquetheorique_on,from_crit_zone_on, from_crit_volume_on,from_crit_diffusion_on,from_crit_destination_on,from_crit_note_inspection_on) VALUES ('SPA1','2013','création jdd usagers',now(),'true','false','true','false','true','true') RETURNING FORM_ID INTO val_formule1;
insert into formule_risque (dt_rfa,camp_rfa,from_auteur_creation_lb,form_creation_dt,from_crit_risquetheorique_on,from_crit_zone_on, from_crit_volume_on,from_crit_diffusion_on,from_crit_destination_on,from_crit_note_inspection_on) VALUES ('SPA7','2013','création jdd usagers',now(),'true','false','true','false','true','true') RETURNING FORM_ID INTO val_formule2;

--ponderation_volume
insert into ponderation_volume (ta_rfa,uprod_rfa,form_id,pvol_s1_nb2,pvol_s2_nb2,pvol_s3_nb2,pvol_s4_nb2) values ('FROMA','FROMA',val_formule1,'0','1250','6250','25000');
insert into ponderation_volume (ta_rfa,uprod_rfa,form_id,pvol_s1_nb2,pvol_s2_nb2,pvol_s3_nb2,pvol_s4_nb2) values ('ANIMA','BOVIN',val_formule1,'0','1250','6250','25000');
insert into ponderation_volume (ta_rfa,uprod_rfa,form_id,pvol_s1_nb2,pvol_s2_nb2,pvol_s3_nb2,pvol_s4_nb2) values ('ANIMA','BOVIN',val_formule2,'0','1250','6250','25000');

--PONDERATION_DESTINATION
insert into PONDERATION_DESTINATION (form_id,dest_rfa,ta_rfa,PDEST_POIDS_NB2) values (val_formule1,'BOUCH','ANIMA','2');
insert into PONDERATION_DESTINATION (form_id,dest_rfa,ta_rfa,PDEST_POIDS_NB2) values (val_formule1,'TRAIT','FROMA','2');
insert into PONDERATION_DESTINATION (form_id,dest_rfa,ta_rfa,PDEST_POIDS_NB2) values (val_formule2,'TRAIT','FROMA','2');

--PONDERATION_RISQUE_THEORIQUE
insert into analyse_risque.PONDERATION_RISQUE_THEORIQUE (FORM_ID,PRISQTHEO_POIDS_NB2,TA_RFA) values (val_formule1,'30','ANIMA') RETURNING PRISQTHEO_ID INTO val_prisqTheoId1;
insert into analyse_risque.PONDERATION_RISQUE_THEORIQUE (FORM_ID,PRISQTHEO_POIDS_NB2,TA_RFA) values (val_formule1,'20','ANIMA') RETURNING PRISQTHEO_ID INTO val_prisqTheoId2;
insert into analyse_risque.PONDERATION_RISQUE_THEORIQUE (FORM_ID,PRISQTHEO_POIDS_NB2,TA_RFA) values (val_formule2,'40','FROMA') RETURNING PRISQTHEO_ID INTO val_prisqTheoId3;

--PONDERATION_RISQUE_THEORIQUE_PROCEDE
insert into PONDERATION_RISQUE_THEORIQUE_PROCEDE (PRISQTHEO_ID,proc_rfa) values (val_prisqTheoId3,'TR_ROUTIER');
insert into PONDERATION_RISQUE_THEORIQUE_PROCEDE (PRISQTHEO_ID,proc_rfa) values (val_prisqTheoId3,'TRT_AERIEN');
insert into PONDERATION_RISQUE_THEORIQUE_PROCEDE (PRISQTHEO_ID,proc_rfa) values (val_prisqTheoId2,'TR_FLUVIAL');
insert into PONDERATION_RISQUE_THEORIQUE_PROCEDE (PRISQTHEO_ID,proc_rfa) values (val_prisqTheoId2,'TR_ROUTIER');
insert into PONDERATION_RISQUE_THEORIQUE_PROCEDE (PRISQTHEO_ID,proc_rfa) values (val_prisqTheoId1,'TR_ROUTIER');

--PRODUIT 
insert into PRODUIT (TPROD_RFA,PROD_RFA) values ('DENRE','FDECO') RETURNING PROD_ID INTO val_prodId1;
insert into PRODUIT (TPROD_RFA,PROD_RFA) values ('DENRE','FFOND') RETURNING PROD_ID INTO val_prodId2;
insert into PRODUIT (TPROD_RFA,PROD_RFA) values ('ANIMA','BOEUF') RETURNING PROD_ID INTO val_prodId3;

--PONDERATION_RISQUE_THEORIQUE_PRODUIT 
insert into PONDERATION_RISQUE_THEORIQUE_PRODUIT (PRISQTHEO_ID,PROD_ID) values (val_prisqTheoId3,val_prodId3);
insert into PONDERATION_RISQUE_THEORIQUE_PRODUIT (PRISQTHEO_ID,PROD_ID) values (val_prisqTheoId2,val_prodId2);
insert into PONDERATION_RISQUE_THEORIQUE_PRODUIT (PRISQTHEO_ID,PROD_ID) values (val_prisqTheoId1,val_prodId1);


--CLASSE_RISQUE
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2,classe_max_nb2) VALUES ('1','SPA7','2013',1,0,10);
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2,classe_max_nb2) VALUES ('2','SPA7','2013',1,10,20);
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2,classe_max_nb2) VALUES ('3','SPA7','2013',1,20,50);
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2) VALUES ('4','SPA7','2013',1,50);

insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2,classe_max_nb2) VALUES ('1','SSA1','2013',1,0,10);
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2,classe_max_nb2) VALUES ('2','SSA1','2013',1,10,20);
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2,classe_max_nb2) VALUES ('3','SSA1','2013',1,20,50);
insert into CLASSE_RISQUE (classe_rfa,dt_rfa,camp_rfa,classe_valeur_nb,classe_min_nb2) VALUES ('4','SSA1','2013',1,50);

--NOTE_RISQUE
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('32635569000024_00001_SSA1_2013','32635569000024_00001','SSA1','2013',15,3,3,3,3,3,3,20);
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('32077440900017_00001_SSA1_2013','32077440900017_00001','SSA1','2013',30,1,1,10,3,1,1,20);
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('32635569000024_00001_SPA7_2013','32635569000024_00001','SPA7','2013',15,3,3,3,3,3,3,20);
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('32077440900017_00001_SPA7_2013','32077440900017_00001','SPA7','2013',30,1,1,10,3,1,1,20);

insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('11111100000002_00001_SSA1_2013','11111100000002_00001','SSA1','2013',15,3,3,3,3,3,3,20);
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('11111100000003_00001_SSA1_2013','11111100000003_00001','SSA1','2013',30,1,1,10,3,1,1,20);
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('11111100000002_00001_SPA7_2013','11111100000002_00001','SPA7','2013',15,3,3,3,3,3,3,20);
insert into NOTE_RISQUE (note_rfa,ua_rfa,dt_rfa,camp_rfa,note_val_nb,note_p_risqu_nb2,note_p_vol_nb2,note_p_zone_nb2,note_p_diff_nb2,note_p_dest_nb2,note_p_ni_nb2,note_prec_val_nb) VALUES ('11111100000003_00001_SPA7_2013','11111100000003_00001','SPA7','2013',30,1,1,10,3,1,1,20);

--mapping_categorie_denree
insert into mapping_categorie_denree (ta_rfa,catden_rfa,den_rfa) VALUES ('FROMA','FROMA','FDECO');
insert into mapping_categorie_denree (ta_rfa,catden_rfa,den_rfa) VALUES ('TRANX','PECHE','APPEF');

END$$


