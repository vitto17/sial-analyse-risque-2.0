
----------------------
-- Compatiblilité MPD 1.3 
-- pour la 1.5, remplacer ANIMA par E_TRANS_ANX
----------------------

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = "usagers", pg_catalog;

create or replace function create_UA_en_masse(nbr_ua INT8, val_EDE INT8,val_SIREN INT8,val_SIRET INT8) returns void as
$$
DECLARE v_adresse_entreprise_id INT4;
DECLARE v_entreprise_id INT4;
DECLARE v_entreprise_niv_id INT4;
DECLARE v_entreprise_hist_id INT4;
DECLARE v_interlocuteur_id INT4;
DECLARE v_adresse_exploitation_id INT4;
DECLARE v_exploitation_niv_id INT4;
DECLARE v_adresse_etablissement_id INT4;
DECLARE v_adresse_etablissement_bdnu_id INT4;
DECLARE v_etablissement_id INT4;
DECLARE v_etablissement_niv_id INT4;
DECLARE v_etablissement_hist_id INT4;
DECLARE v_etablissement_identifiant_id INT4;
DECLARE v_adresse_ua_id INT4;
DECLARE v_ua_niv_id INT4;
DECLARE v_ua_hist_id INT4;
DECLARE v_ua_id INT4;
DECLARE v_ua_identifiant_id INT4;
DECLARE v_equip_id INT4;
DECLARE v_SIREN INT8;
DECLARE v_SIRET INT8;
DECLARE v_EDE INT8;
DECLARE V_UA_RFA TEXT;
DECLARE V_UA_INDEN_METIER INT8;
DECLARE V_NB_INSERT INT4;

BEGIN
	
	v_SIREN := val_SIREN;
	v_SIRET := val_SIRET;
	v_EDE := val_EDE;
	
	FOR i IN 1..nbr_ua LOOP
		v_EDE   := v_EDE + 1;
		v_SIREN := v_SIREN+1;
		v_SIRET := v_SIRET + 1;
		V_UA_INDEN_METIER := v_SIRET;
		V_UA_RFA := v_SIRET || '_00001';

		--entreprise
		insert into usagers.ADRESSE ( TYPA_RFA, ADR_LIGNE1_LB, ADR_LIGNE2_LB, ADR_LIGNE3_LB, ADR_CONCAT, ADR_COMPLEMENT_TXT, CODE_INSEE_COMMUNE_RFA, PAYS_RFA, ADR_CODE_POSTAL_NB, ADR_COMMUNE_ETR_LB) values ('BDNU','Route N 134','','','','','44001','XXXXX',null,'') RETURNING ADR_ID INTO v_adresse_entreprise_id;	
		INSERT INTO usagers.entreprise_bdnu(enb_rfa, enb_rais_soc_lb, enb_dt_crea, enb_dt_cess, naf_insee_rfa, typ_identifiant_rfa, cat_jurid_rfa, adr_id)     VALUES (v_SIREN,'ENTREPRISE avec SIREN ' || v_SIREN,'01/01/2014',null,'46.33Z','SIREN','7348',v_adresse_entreprise_id) returning enb_id into v_entreprise_id;	
		INSERT INTO usagers.HISTORIQUE(his_crea_aut_lb, his_crea_dt) VALUES ('Nom et Prenom de l''auteur de la derniere modif',now()) RETURNING HIS_ID INTO v_entreprise_hist_id;
		INSERT INTO usagers.niveau_usager(niv_usager_rfa) VALUES ('ENT') RETURNING NIV_ID INTO v_entreprise_niv_id; 
		INSERT INTO usagers.entreprise(enb_id, his_id, niv_id, statut_activite_rfa) VALUES (v_entreprise_id, v_entreprise_hist_id,v_entreprise_niv_id,'ACTIV');
		INSERT INTO usagers.INTERLOCUTEUR (role_rfa, civilite_rfa, int_nom_lb,int_prenom_lb ,int_courriel_lb, int_tel_lb, int_telecopie_lb)  VALUES ('GER','M',' Nom Contact entrep.'||v_SIREN,' Prenom Contact entrep.'||v_SIREN,'SIREN 111111111@gmail.com','','') RETURNING INT_ID INTO v_interlocuteur_id;
		INSERT INTO usagers.asso_entreprise_interlocuteur(int_id, enb_id)  VALUES (v_interlocuteur_id,v_entreprise_id);


		--etablissement
		insert into usagers.ADRESSE ( TYPA_RFA, ADR_LIGNE1_LB, ADR_LIGNE2_LB, ADR_LIGNE3_LB, ADR_CONCAT, ADR_COMPLEMENT_TXT, CODE_INSEE_COMMUNE_RFA, PAYS_RFA, ADR_CODE_POSTAL_NB, ADR_COMMUNE_ETR_LB) values ('BDNU','','','','','','44002','XXXXX',null,'') RETURNING ADR_ID INTO v_adresse_etablissement_id;	
		insert into usagers.ADRESSE ( TYPA_RFA, ADR_LIGNE1_LB, ADR_LIGNE2_LB, ADR_LIGNE3_LB, ADR_CONCAT, ADR_COMPLEMENT_TXT, CODE_INSEE_COMMUNE_RFA, PAYS_RFA, ADR_CODE_POSTAL_NB, ADR_COMMUNE_ETR_LB) values ('BDNU','','','','','','44002','XXXXX',null,'') RETURNING ADR_ID INTO v_adresse_etablissement_bdnu_id;	
		INSERT INTO usagers.etablissement_bdnu(etb_rfa,  etb_enseigne_lb, etb_dt_crea, etb_dt_cess, naf_insee_rfa, typ_identifiant_rfa, enb_id, adr_id, etb_siegesoc_bo) VALUES (v_SIRET,'ETABLISSEMENT avec SIRET '||v_SIRET,now(),null,'01.45Z','SIRET',(select ENB_ID FROM usagers.ENTREPRISE_BDNU WHERE ENB_RFA = V_SIREN::text),v_adresse_etablissement_id,'true')  returning etb_id into v_etablissement_id;	
		INSERT INTO usagers.HISTORIQUE(his_crea_aut_lb, his_crea_dt) VALUES ('Nom et Prenom de l''auteur de la derniere modif',now()) RETURNING HIS_ID INTO v_etablissement_hist_id;
		INSERT INTO usagers.niveau_usager(niv_usager_rfa) VALUES ('ETA') RETURNING NIV_ID INTO v_etablissement_niv_id; 
		INSERT INTO usagers.etablissement(etb_id,  eta_enseigne_usuel_lb, eta_effectif_nb, statut_activite_rfa,localisation_adr_id, enb_id, his_id, niv_id)  VALUES (v_etablissement_id,'ETABLISSEMENT avec SIRET '||v_SIRET,'2','ACTIV', v_adresse_etablissement_id, (select ENB_ID FROM usagers.ENTREPRISE_BDNU WHERE ENB_RFA = v_SIREN::text), v_etablissement_hist_id, v_etablissement_niv_id);	
		INSERT INTO usagers.INTERLOCUTEUR (role_rfa, civilite_rfa, int_nom_lb,int_prenom_lb,int_courriel_lb, int_tel_lb, int_telecopie_lb)  VALUES ('FERMI','MR','Nom Contact etabli.'||v_SIRET,'Prenom Contact etabl.'||v_SIRET,'SIRET '||v_SIRET||'@gmail.com','','') RETURNING INT_ID INTO v_interlocuteur_id;
		INSERT INTO usagers.asso_etablis_interloc(int_id, etb_id)  VALUES (v_interlocuteur_id,v_etablissement_id);	


		--exploitation
		insert into usagers.ADRESSE ( TYPA_RFA, ADR_LIGNE1_LB, ADR_LIGNE2_LB, ADR_LIGNE3_LB, ADR_CONCAT, ADR_COMPLEMENT_TXT, CODE_INSEE_COMMUNE_RFA, PAYS_RFA, ADR_CODE_POSTAL_NB, ADR_COMMUNE_ETR_LB) values ('BDNI','26, Rue Bayol','','','26, Rue Bayard','Attention A coté du lycée agicole','44004','XXXXX',null,'') RETURNING ADR_ID INTO v_adresse_exploitation_id;	
		INSERT INTO usagers.NIVEAU_USAGER(niv_usager_rfa) VALUES ('EDE') RETURNING NIV_ID INTO  v_exploitation_niv_id; 
		insert into usagers.EXPLOITATION (TYP_IDENTIFIANT_RFA,EXP_RFA,EXP_NOM_USUEL_LB, TYPE_EXPLOITATION_LB,EXP_CREA_DT, ADR_ID, niv_id, etb_id) values ('EDE',v_EDE,'Exploitation '||v_SIREN,'10','2009-06-11 17:45:54.013',v_adresse_exploitation_id,v_exploitation_niv_id,(select ETB_ID FROM usagers.ETABLISSEMENT_BDNU WHERE ETB_RFA = v_SIRET::text));

		--UA
		insert into usagers.ADRESSE ( TYPA_RFA, ADR_LIGNE1_LB, ADR_LIGNE2_LB, ADR_LIGNE3_LB, ADR_CONCAT, ADR_COMPLEMENT_TXT, CODE_INSEE_COMMUNE_RFA, PAYS_RFA, ADR_CODE_POSTAL_NB, ADR_COMMUNE_ETR_LB) values ('RESYT','0, Rue UA du SIRET '||v_SIRET,'Ligne 2. '||v_SIRET,'Ligne 3. '||v_SIRET,'Ligne 4. '||v_SIRET,'Attention A coté du lycée agicole Comment 107','44003','XXXXX',null,'') RETURNING ADR_ID INTO v_adresse_ua_id;	
		INSERT INTO usagers.HISTORIQUE(his_crea_aut_lb, his_crea_dt) VALUES ('Nom et Prenom de l''auteur de la derniere modif',now()) RETURNING HIS_ID INTO v_ua_hist_id;
		INSERT INTO usagers.NIVEAU_USAGER(niv_usager_rfa) VALUES ('UA') RETURNING NIV_ID INTO  v_ua_niv_id;  
		IF ((i % 5) = 0) THEN
			-- type_activite_rfa : ANIMA
			-- PRODUITS_ANIMAUX 
			INSERT INTO usagers.unite_activite(ua_rfa, niv_id, localisation_adr_id, etb_id, type_activite_rfa, statut_activite_rfa, his_id, ua_dt_debut_act,ua_adr_diff_on,diffusion_rfa) VALUES (v_UA_RFA, v_ua_niv_id, v_adresse_ua_id,(select ETB_ID FROM usagers.ETABLISSEMENT_BDNU WHERE ETB_RFA = v_SIRET::text),'ANIMA','ACTIV',v_ua_hist_id,now(),true,'NAT') RETURNING ua_id INTO v_ua_id;	
			INSERT INTO usagers.identifiant_metier(type_ident_metier_rfa, iden_valeur,iden_principal_bo) VALUES ('LIBRE',V_UA_INDEN_METIER,True) RETURNING iden_id INTO v_ua_identifiant_id;
			INSERT INTO usagers.asso_ua_identifiant(iden_id, ua_id) VALUES (v_ua_identifiant_id,v_ua_id); 
			INSERT INTO usagers.asso_ua_produits_animaux(ua_id, espece_dgal_rfa, type_animal_rfa) VALUES (v_ua_id, 'BOVIN','BOEUF');
			INSERT INTO usagers.asso_ua_procede(ua_id,procede_rfa) VALUES (v_ua_id, 'TR_ROUTIER');
			INSERT INTO usagers.asso_ua_zone_geo(ua_id,zone_rfa) VALUES (v_ua_id, '1021');
			INSERT INTO usagers.asso_ua_destination(ua_id,destination_rfa) VALUES (v_ua_id, 'BOUCH');
			INSERT INTO usagers.production(ua_id,prod_capacite_nb,prod_volume_nb,unite_rfa) VALUES (v_ua_id, 500,500,'BOVIN');
		ELSIF ((i % 4) = 0)  THEN
			-- type_activite_rfa : PRODV
			-- FILIERE_VEGETAL_RFA  chez CONCER
			INSERT INTO usagers.unite_activite(ua_rfa, niv_id, localisation_adr_id, etb_id, type_activite_rfa, statut_activite_rfa, his_id, ua_dt_debut_act,ua_adr_diff_on,diffusion_rfa) VALUES (v_UA_RFA, v_ua_niv_id, v_adresse_ua_id,(select ETB_ID FROM usagers.ETABLISSEMENT_BDNU WHERE ETB_RFA = v_SIRET::text),'GRAIN','ACTIV',v_ua_hist_id,now(),true,'NAT') RETURNING ua_id INTO v_ua_id;	
			INSERT INTO usagers.identifiant_metier(type_ident_metier_rfa, iden_valeur,iden_principal_bo) VALUES ('LIBRE',V_UA_INDEN_METIER,True) RETURNING iden_id INTO v_ua_identifiant_id;
			INSERT INTO usagers.asso_ua_identifiant(iden_id, ua_id) VALUES (v_ua_identifiant_id,v_ua_id); 
			INSERT INTO usagers.asso_ua_procede(ua_id,procede_rfa) VALUES (v_ua_id, 'RAFF_HUILE');
			INSERT INTO usagers.asso_ua_zone_geo(ua_id,zone_rfa) VALUES (v_ua_id, '11342');
			INSERT INTO usagers.asso_ua_destination(ua_id,destination_rfa) VALUES (v_ua_id, 'GP');
			INSERT INTO usagers.production(ua_id,prod_capacite_nb,prod_volume_nb,unite_rfa) VALUES (v_ua_id, 400,400,'PRODV');
		ELSIF ((i % 3) = 0)  THEN
			-- type_activite_rfa : INTR 
			-- TYPE_INTRANT
			INSERT INTO usagers.unite_activite(ua_rfa, niv_id, localisation_adr_id, etb_id, type_activite_rfa, statut_activite_rfa, his_id, ua_dt_debut_act,ua_adr_diff_on,diffusion_rfa) VALUES (v_UA_RFA, v_ua_niv_id, v_adresse_ua_id,(select ETB_ID FROM usagers.ETABLISSEMENT_BDNU WHERE ETB_RFA = v_SIRET::text),'PRODV','ACTIV',v_ua_hist_id,now(),true,'NAT') RETURNING ua_id INTO v_ua_id;	
			INSERT INTO usagers.identifiant_metier(type_ident_metier_rfa, iden_valeur,iden_principal_bo) VALUES ('LIBRE',V_UA_INDEN_METIER,True) RETURNING iden_id INTO v_ua_identifiant_id;
			INSERT INTO usagers.asso_ua_identifiant(iden_id, ua_id) VALUES (v_ua_identifiant_id,v_ua_id); 
			INSERT INTO usagers.ASSO_UA_TYPE_INTRANT(ua_id,TYPE_INTRANT_rfa) VALUES (v_ua_id, 'PPP');
			INSERT INTO usagers.asso_ua_procede(ua_id,procede_rfa) VALUES (v_ua_id, 'PROC_CHIR');
			INSERT INTO usagers.asso_ua_destination(ua_id,destination_rfa) VALUES (v_ua_id, 'UE');
			INSERT INTO usagers.production(ua_id,prod_capacite_nb,prod_volume_nb,unite_rfa) VALUES (v_ua_id, 300,300,'INT');
		ELSIF ((i % 2) = 0)  THEN
			-- type_activite_rfa : ANIM
			-- UA_PRODUIT_AA1   : ALIMENTATION_ANIMALE
			INSERT INTO usagers.unite_activite(ua_rfa, niv_id, localisation_adr_id, etb_id, type_activite_rfa, statut_activite_rfa, his_id, ua_dt_debut_act,ua_adr_diff_on,diffusion_rfa) VALUES (v_UA_RFA, v_ua_niv_id, v_adresse_ua_id,(select ETB_ID FROM usagers.ETABLISSEMENT_BDNU WHERE ETB_RFA = v_SIRET::text),'ANIM','ACTIV',v_ua_hist_id,now(),true,'UE') RETURNING ua_id INTO v_ua_id;	
			INSERT INTO usagers.identifiant_metier(type_ident_metier_rfa, iden_valeur,iden_principal_bo) VALUES ('LIBRE',V_UA_INDEN_METIER,True) RETURNING iden_id INTO v_ua_identifiant_id;
			INSERT INTO usagers.asso_ua_identifiant(iden_id, ua_id) VALUES (v_ua_identifiant_id,v_ua_id); 
			INSERT INTO usagers.ASSO_UA_PRODUIT_AA1(ua_id,UE_AA1_PRODUIT_RFA) VALUES (v_ua_id, 'ALAN5');
			INSERT INTO usagers.asso_ua_procede(ua_id,procede_rfa) VALUES (v_ua_id, 'SPAN_MT1');
			INSERT INTO usagers.asso_ua_destination(ua_id,destination_rfa) VALUES (v_ua_id, 'PROD');
			INSERT INTO usagers.production(ua_id,prod_capacite_nb,prod_volume_nb,unite_rfa) VALUES (v_ua_id, 200,200,'POUDR');
		ELSE 
			-- type_activite_rfa : FROMA
			-- denree
			INSERT INTO usagers.unite_activite(ua_rfa, niv_id, localisation_adr_id, etb_id, type_activite_rfa, statut_activite_rfa, his_id, ua_dt_debut_act,ua_adr_diff_on,diffusion_rfa) VALUES (v_UA_RFA, v_ua_niv_id, v_adresse_ua_id,(select ETB_ID FROM usagers.ETABLISSEMENT_BDNU WHERE ETB_RFA = v_SIRET::text),'FROMA','ACTIV',v_ua_hist_id,now(),true,'LOC') RETURNING ua_id INTO v_ua_id;	
			INSERT INTO usagers.identifiant_metier(type_ident_metier_rfa, iden_valeur,iden_principal_bo) VALUES ('LIBRE',V_UA_INDEN_METIER,True) RETURNING iden_id INTO v_ua_identifiant_id;
			INSERT INTO usagers.asso_ua_identifiant(iden_id, ua_id) VALUES (v_ua_identifiant_id,v_ua_id); 
			INSERT INTO usagers.asso_ua_denree(ua_id, cat_denree_rfa, denree_rfa) VALUES (v_ua_id, 'FROMA','FDECO');
			INSERT INTO usagers.asso_ua_denree(ua_id, cat_denree_rfa, denree_rfa) VALUES (v_ua_id, 'FROMA','');
			INSERT INTO usagers.asso_ua_denree(ua_id, cat_denree_rfa, denree_rfa) VALUES (v_ua_id, 'FROMA','FFOND');
			INSERT INTO usagers.asso_ua_procede(ua_id,procede_rfa) VALUES (v_ua_id, 'TR_ROUTIER');
			INSERT INTO usagers.asso_ua_zone_geo(ua_id,zone_rfa) VALUES (v_ua_id, 'AOC25');
			INSERT INTO usagers.asso_ua_destination(ua_id,destination_rfa) VALUES (v_ua_id, 'TRAIT');
			INSERT INTO usagers.production(ua_id,prod_capacite_nb,prod_volume_nb,unite_rfa) VALUES (v_ua_id, 1000,1000,'FROMA');
		END IF;
	END LOOP;
	
EXCEPTION
    WHEN OTHERS THEN 
    ROLLBACK;
END
$$
LANGUAGE 'plpgsql';

DO $$
DECLARE 
	v_SIREN INT8;
	v_SIRET INT8;
	v_EDE INT8;
	v_UA_INDEN_METIER INT8;
	nbr_ua_part_transaction INT8;
	nbr_transacation INT8;
BEGIN


		truncate asso_etablis_interloc  cascade ;
		truncate asso_ua_identifiant cascade ;
		truncate asso_etablissement_id_metier cascade ;
		truncate relation_historique cascade ;
		truncate asso_entreprise_interlocuteur cascade ;
		truncate EXPLOITATION cascade ;
		truncate etablissement cascade ;
		truncate etablissement_bdnu cascade ;
		truncate entreprise cascade ;
		truncate entreprise_bdnu cascade ;
		truncate adresse cascade ;
		truncate historique cascade ;
		truncate niveau_usager cascade ;
		truncate INTERLOCUTEUR cascade ;
		truncate UNITE_ACTIVITE cascade ;
		truncate identifiant_metier cascade ;


	v_EDE   := 10000001;
	v_SIREN := 100000001;
	v_SIRET := 11111100000001;
	--SELECT  create_UA_en_masse(10,10000001,100000001,11111100000001);
	nbr_ua_part_transaction := 15;
	nbr_transacation := 2;
	
	FOR i IN 1..nbr_transacation LOOP
		PERFORM  create_UA_en_masse(nbr_ua_part_transaction,v_EDE,v_SIREN,v_SIRET);
		v_EDE := v_EDE+nbr_ua_part_transaction;
		v_SIREN := v_SIREN+nbr_ua_part_transaction;
		v_SIRET := v_SIRET+nbr_ua_part_transaction;
		v_UA_INDEN_METIER := v_UA_INDEN_METIER+nbr_ua_part_transaction;
	END LOOP;	
END$$;


select * from UNITE_ACTIVITE order by 9;




