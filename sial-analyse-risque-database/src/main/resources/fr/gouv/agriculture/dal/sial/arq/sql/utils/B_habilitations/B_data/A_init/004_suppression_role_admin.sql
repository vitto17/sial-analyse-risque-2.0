SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;


SET search_path = "habilitations", pg_catalog;

--
-- Suppression du rôle '[ARQ] Admin' plus utilisé :
--
delete from T_ORION_ROLE  where
ROLE_CDN = (select ROLE_CDN from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Admin'
	and	a.NAME='Analyse de Risque'
);
