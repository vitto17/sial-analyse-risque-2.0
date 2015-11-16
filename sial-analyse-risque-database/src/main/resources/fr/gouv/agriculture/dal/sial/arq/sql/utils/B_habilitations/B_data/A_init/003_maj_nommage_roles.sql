SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;


SET search_path = "habilitations", pg_catalog;


create or replace function create_webmin() returns void as
$$
declare
	l_perm_cdn integer;
begin
--
-- Création de l'application
--
if not exists (select 1 from T_ORION_APPLICATION where NAME = 'Analyse de Risque')
then
	insert into T_ORION_APPLICATION (NAME) values ('Analyse de Risque');
end if;



--
-- Migration des noms de rôles
--
update habilitations.t_orion_role
set NAME = '[ARQ] Consultant'
where exists (select 1 from habilitations.T_ORION_APPLICATION a where a.APPLICATION_CDN = habilitations.t_orion_role.APPLICATION_CDN and a.NAME = 'Analyse de Risque')
  and habilitations.t_orion_role.NAME = 'Consultant ARQ (CA)';

update habilitations.t_orion_role
set NAME = '[ARQ] Gestionnaire Local'
where exists (select 1 from habilitations.T_ORION_APPLICATION a where a.APPLICATION_CDN = habilitations.t_orion_role.APPLICATION_CDN and a.NAME = 'Analyse de Risque')
  and habilitations.t_orion_role.NAME = 'Administrateur Local ARQ (ALA)';

update habilitations.t_orion_role
set NAME = '[ARQ] Gestionnaire National'
where exists (select 1 from habilitations.T_ORION_APPLICATION a where a.APPLICATION_CDN = habilitations.t_orion_role.APPLICATION_CDN and a.NAME = 'Analyse de Risque')
  and habilitations.t_orion_role.NAME = 'Administrateur National Métier ARQ (ANMA)';

update habilitations.t_orion_role
set NAME = '[ARQ] Gestionnaire SIAL'
where exists (select 1 from habilitations.T_ORION_APPLICATION a where a.APPLICATION_CDN = habilitations.t_orion_role.APPLICATION_CDN and a.NAME = 'Analyse de Risque')
  and habilitations.t_orion_role.NAME = 'Administrateur SIAL ARQ (ASA)';

--
--Mise à jour des catégories de rôles
--
--
--Nettoyage de la table T_ORION_GROUP_ROLE
--
delete from T_ORION_GROUP_ROLE where
ROLE_CDN = (select ROLE_CDN from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Consultant'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
ROLE_CDN = (select ROLE_CDN from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Gestionnaire Local'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
ROLE_CDN = (select ROLE_CDN from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Gestionnaire National'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
ROLE_CDN = (select ROLE_CDN from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Gestionnaire SIAL'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Calcul Resultat restreint'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Droit en lecture des entités Analyse de Risque'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Droit en écriture de l''entité Batch (ARQ)'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Droit en écriture des entités Analyse de Risque'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Groupe de droit changement role'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Menu ARQ : Accès Total'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Menu ARQ : Accès restreint pour Traitements'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Menu ARQ : Accès restreint pour formulaires C/U'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Restrictions sur les actions de formules de risque'
	and	a.NAME='Analyse de Risque'
);

delete from T_ORION_GROUP_ROLE where
GROUP_CDN = (select GROUP_CDN from T_ORION_GROUP g
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
	where
		g.NAME = 'Restrictions sur les actions des pondérations'
	and	a.NAME='Analyse de Risque'
);

--
--Mise à jour des rôles
--
if not exists (select 1 from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Consultant'
	and	a.NAME = 'Analyse de Risque')
then
	insert into T_ORION_ROLE(
		NAME,DESCRIPTION,APPLICATION_CDN,ROLECAT_CDN,ROLE_INHERITED_CDN
	) select
		'[ARQ] Consultant',
		'Le rôle CA est associé à un attribut complémentaire de type couple:
(Structure*Domaine Technique)',
		(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
		(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = 'Analyse de Risque');
else
	update T_ORION_ROLE set
		DESCRIPTION='Le rôle CA est associé à un attribut complémentaire de type couple:
(Structure*Domaine Technique)',
		ROLECAT_CDN=(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		ROLE_INHERITED_CDN=(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = null)
	where
		NAME='[ARQ] Consultant'
	and	APPLICATION_CDN = (select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque');
end if;

if not exists (select 1 from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Gestionnaire Local'
	and	a.NAME = 'Analyse de Risque')
then
	insert into T_ORION_ROLE(
		NAME,DESCRIPTION,APPLICATION_CDN,ROLECAT_CDN,ROLE_INHERITED_CDN
	) select
		'[ARQ] Gestionnaire Local',
		'Le rôle ALA est associé à un attribut complémentaire de type couple:
(Structure*Domaine Technique)',
		(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
		(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = 'Analyse de Risque');
else
	update T_ORION_ROLE set
		DESCRIPTION='Le rôle ALA est associé à un attribut complémentaire de type couple:
(Structure*Domaine Technique)',
		ROLECAT_CDN=(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		ROLE_INHERITED_CDN=(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = null)
	where
		NAME='[ARQ] Gestionnaire Local'
	and	APPLICATION_CDN = (select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque');
end if;

if not exists (select 1 from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Gestionnaire National'
	and	a.NAME = 'Analyse de Risque')
then
	insert into T_ORION_ROLE(
		NAME,DESCRIPTION,APPLICATION_CDN,ROLECAT_CDN,ROLE_INHERITED_CDN
	) select
		'[ARQ] Gestionnaire National',
		'Le rôle ANMA est associé à un attribut complémentaire : Domaine Technique.',
		(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
		(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = 'Analyse de Risque');
else
	update T_ORION_ROLE set
		DESCRIPTION='Le rôle ANMA est associé à un attribut complémentaire : Domaine Technique.',
		ROLECAT_CDN=(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		ROLE_INHERITED_CDN=(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = null)
	where
		NAME='[ARQ] Gestionnaire National'
	and	APPLICATION_CDN = (select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque');
end if;

if not exists (select 1 from T_ORION_ROLE r
	join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
	where
		r.NAME = '[ARQ] Gestionnaire SIAL'
	and	a.NAME = 'Analyse de Risque')
then
	insert into T_ORION_ROLE(
		NAME,DESCRIPTION,APPLICATION_CDN,ROLECAT_CDN,ROLE_INHERITED_CDN
	) select
		'[ARQ] Gestionnaire SIAL',
		'Son rôle est identique à celui de l''ANMA sans restriction de domaine
technique.',
		(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
		(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = 'Analyse de Risque');
else
	update T_ORION_ROLE set
		DESCRIPTION='Son rôle est identique à celui de l''ANMA sans restriction de domaine
technique.',
		ROLECAT_CDN=(select ROLECAT_CDN from T_ORION_ROLE_CATEGORY where NAME = null),
		ROLE_INHERITED_CDN=(select ROLE_CDN from T_ORION_ROLE r
			join T_ORION_APPLICATION a on a.APPLICATION_CDN = r.APPLICATION_CDN
			where
				r.NAME = null
			and	a.NAME = null)
	where
		NAME='[ARQ] Gestionnaire SIAL'
	and	APPLICATION_CDN = (select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque');
end if;

--
--Suppression des groupes
--
delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Calcul Resultat restreint'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Calcul Resultat restreint'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Calcul Resultat restreint'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Calcul Resultat restreint'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Calcul Resultat restreint'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en lecture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en lecture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en lecture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en lecture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en lecture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where

PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Droit en écriture des entités Analyse de Risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Groupe de droit changement role'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Groupe de droit changement role'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Groupe de droit changement role'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Groupe de droit changement role'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Groupe de droit changement role'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès Total'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès Total'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès Total'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès Total'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès Total'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions de formules de risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions de formules de risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions de formules de risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions de formules de risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions de formules de risque'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_METHOD_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions des pondérations'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PROPERTY_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions des pondérations'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_PERMISSION where
PERM_CDN in (select
	PERM_CDN
from T_ORION_PERMISSION p
join T_ORION_GUARDED_RESOURCE gr on gr.GR_CDN = p.GR_CDN
join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions des pondérations'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GUARDED_RESOURCE where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions des pondérations'
and	a.NAME = 'Analyse de Risque'
);

delete from T_ORION_GROUP where
GROUP_CDN in (select
	GROUP_CDN
from T_ORION_GROUP g
join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
where
	g.NAME='Restrictions sur les actions des pondérations'
and	a.NAME = 'Analyse de Risque'
);

--
--Création des groupes
--
insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Calcul Resultat restreint',
	'Restriction sur l''action de calcul de resultat',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.dal.sial.arq.action.CalculerNotesRisquesAction',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Calcul Resultat restreint'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'false',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.action.CalculerNotesRisquesAction'
		and	gr.DESCRIPTION=''
		and	g.NAME='Calcul Resultat restreint'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.view.Renderable',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Calcul Resultat restreint'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'rendered',
	'this.id!=''calculerActionButton''',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.view.Renderable'
		and	gr.DESCRIPTION=''
		and	g.NAME='Calcul Resultat restreint'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Droit en lecture des entités Analyse de Risque',
	'Droit en lecture des entités Analyse de Risque',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.dal.sial.arq.business.*',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Droit en lecture des entités Analyse de Risque'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'create',
	'false',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.*'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en lecture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'delete',
	'false',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.*'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en lecture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'read',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.*'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en lecture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'update',
	'false',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.*'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en lecture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Droit en écriture de l''entité Batch (ARQ)',
	'Analyse de Risque : Droit en écriture de l''entité Batch',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.dal.sial.arq.business.Batch',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Droit en écriture de l''entité Batch (ARQ)'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'create',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.Batch'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'delete',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.Batch'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'update',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.dal.sial.arq.business.Batch'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Droit en écriture des entités Analyse de Risque',
	'Droit en écriture des entités Analyse de Risque',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.business.Business',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Droit en écriture des entités Analyse de Risque'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'create',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.business.Business'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en écriture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'delete',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.business.Business'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en écriture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select

	'update',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.business.Business'
		and	gr.DESCRIPTION=''
		and	g.NAME='Droit en écriture des entités Analyse de Risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Groupe de droit changement role',
	'Autorisation de modifier son rôle courant',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.security.authentication.OrionCredentials',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Groupe de droit changement role'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'read',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authentication.OrionCredentials'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'update',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authentication.OrionCredentials'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.security.authentication.spi.RoleForPrincipal',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Groupe de droit changement role'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'create',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authentication.spi.RoleForPrincipal'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'delete',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authentication.spi.RoleForPrincipal'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'update',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authentication.spi.RoleForPrincipal'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.security.authorization.ChangeRoleAction',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Groupe de droit changement role'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authorization.ChangeRoleAction'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.security.authorization.SetDefaultRoleAction',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Groupe de droit changement role'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.security.authorization.SetDefaultRoleAction'
		and	gr.DESCRIPTION=''
		and	g.NAME='Groupe de droit changement role'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Menu ARQ : Accès Total',
	'Accès à tous les menus',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.Action',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès Total'),
	'this.outcome==''accueil_page'' || this.outcome==''formuleRisque_list'' || this.outcome==''nbrUa_list'' || this.outcome==''batch_list''',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.Action'
		and	gr.DESCRIPTION='this.outcome==''accueil_page'' || this.outcome==''formuleRisque_list'' || this.outcome==''nbrUa_list'' || this.outcome==''batch_list'''
		and	g.NAME='Menu ARQ : Accès Total'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.action.navigation.MenuItemAction',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès Total'),
	'this.outcome==''accueil_page'' || this.outcome==''formuleRisque_list'' || this.outcome==''nbrUa_list'' || this.outcome==''batch_list''',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.action.navigation.MenuItemAction'
		and	gr.DESCRIPTION='this.outcome==''accueil_page'' || this.outcome==''formuleRisque_list'' || this.outcome==''nbrUa_list'' || this.outcome==''batch_list'''
		and	g.NAME='Menu ARQ : Accès Total'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.view.Renderable',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès Total'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'rendered',
	'true',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.view.Renderable'
		and	gr.DESCRIPTION=''
		and	g.NAME='Menu ARQ : Accès Total'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Menu ARQ : Accès restreint pour Traitements',
	'Accès restreint pour l''onglet Traitements en cours',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.Action',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès restreint pour Traitements'),
	'this.outcome!=''batch_list''',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'this.outcome!=''batch_list''',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.Action'
		and	gr.DESCRIPTION='this.outcome!=''batch_list'''
		and	g.NAME='Menu ARQ : Accès restreint pour Traitements'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.action.navigation.MenuItemAction',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès restreint pour Traitements'),
	'this.outcome!=''batch_list''',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'this.outcome!=''batch_list''',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.action.navigation.MenuItemAction'
		and	gr.DESCRIPTION='this.outcome!=''batch_list'''
		and	g.NAME='Menu ARQ : Accès restreint pour Traitements'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.view.Renderable',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès restreint pour Traitements'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'rendered',
	'this.id!=''separatorMenuItemBatch''',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.view.Renderable'
		and	gr.DESCRIPTION=''
		and	g.NAME='Menu ARQ : Accès restreint pour Traitements'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Menu ARQ : Accès restreint pour formulaires C/U',
	'Menu ARQ : Accès restreint pour les formulaires de modification/créationUn nouveau groupe',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.Action',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Menu ARQ : Accès restreint pour formulaires C/U'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'execute',
	'!o:endsWith(this.outcome, ''_crea_form'')',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.Action'
		and	gr.DESCRIPTION=''
		and	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Restrictions sur les actions de formules de risque',
	'Restrictions sur les actions de formules de risque',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.view.faces.standard.component.action.ButtonComponent',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Restrictions sur les actions de formules de risque'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'rendered',
	'!o:startsWith(this.id,''formuleRisqueListNewButton'') && !o:startsWith(this.id,''formuleRisqueListDeleteButton'') && !o:startsWith(this.id,''formuleRisqueListDuplicationAction'')',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.view.faces.standard.component.action.ButtonComponent'
		and	gr.DESCRIPTION=''
		and	g.NAME='Restrictions sur les actions de formules de risque'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

insert into T_ORION_GROUP (
	NAME, DESCRIPTION, APPLICATION_CDN, GROUPCAT_CDN
) select
	'Restrictions sur les actions des pondérations',
	'Restrictions sur les actions des pondérations',
	(select APPLICATION_CDN from T_ORION_APPLICATION where NAME = 'Analyse de Risque'),
	(select GROUPCAT_CDN from T_ORION_GROUP_CATEGORY where NAME = null);

insert into T_ORION_GUARDED_RESOURCE (
	TYPE, NAME, GROUP_CDN, DESCRIPTION, GRCAT_CDN
) select
	'fr.gouv.agriculture.orion.security.authorization.GuardedResource',
	'fr.gouv.agriculture.orion.view.faces.standard.component.action.ButtonComponent',
	(select GROUP_CDN from T_ORION_GROUP g join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN where a.NAME='Analyse de Risque' and g.NAME = 'Restrictions sur les actions des pondérations'),
	'',
	(select GRCAT_CDN from T_ORION_GUARDED_RESOURCE_CATEG where NAME = null);

insert into T_ORION_PERMISSION (
	NAME, VALUE, GR_CDN, DESCRIPTION
) select
	'rendered',
	'!o:startsWith(this.id,''saveButtonPonderation'') && !o:startsWith(this.id,''saveAndCloseButtonPonderation'')',
	(select GR_CDN from T_ORION_GUARDED_RESOURCE gr
		join T_ORION_GROUP g on g.GROUP_CDN = gr.GROUP_CDN
		join T_ORION_APPLICATION a on a.APPLICATION_CDN = g.APPLICATION_CDN
		where
			gr.NAME='fr.gouv.agriculture.orion.view.faces.standard.component.action.ButtonComponent'
		and	gr.DESCRIPTION=''
		and	g.NAME='Restrictions sur les actions des pondérations'
		and	a.NAME='Analyse de Risque'),
	'' returning perm_cdn into l_perm_cdn;

insert into T_ORION_METHOD_PERMISSION (PERM_CDN)
select l_perm_cdn;

--
--Création des associations entre les roles et les groupes
--
insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Groupe de droit changement role'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Droit en lecture des entités Analyse de Risque'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Restrictions sur les actions de formules de risque'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Restrictions sur les actions des pondérations'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Calcul Resultat restreint'
and	r.NAME='[ARQ] Consultant';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Groupe de droit changement role'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Menu ARQ : Accès restreint pour Traitements'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Droit en lecture des entités Analyse de Risque'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Restrictions sur les actions de formules de risque'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Restrictions sur les actions des pondérations'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Menu ARQ : Accès restreint pour formulaires C/U'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Droit en écriture de l''entité Batch (ARQ)'
and	r.NAME='[ARQ] Gestionnaire Local';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Groupe de droit changement role'
and	r.NAME='[ARQ] Gestionnaire National';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Menu ARQ : Accès Total'
and	r.NAME='[ARQ] Gestionnaire National';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Droit en écriture des entités Analyse de Risque'
and	r.NAME='[ARQ] Gestionnaire National';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Groupe de droit changement role'
and	r.NAME='[ARQ] Gestionnaire SIAL';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Menu ARQ : Accès Total'
and	r.NAME='[ARQ] Gestionnaire SIAL';

insert into T_ORION_GROUP_ROLE (GROUP_CDN, ROLE_CDN)
select
	g.GROUP_CDN, r.ROLE_CDN
from T_ORION_APPLICATION a
join T_ORION_GROUP g on g.APPLICATION_CDN = a.APPLICATION_CDN
join T_ORION_ROLE r on r.APPLICATION_CDN = a.APPLICATION_CDN
where
	a.NAME='Analyse de Risque'
and	g.NAME='Droit en écriture des entités Analyse de Risque'
and	r.NAME='[ARQ] Gestionnaire SIAL';

--------------------------------------------------------------------------------------------------------------------
--------------------------------------- RESYTAL DEBUT --------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
delete
from role_sial
where role_cdn in
(
   select
   role_cdn
   from t_orion_role role join t_orion_application app on app.application_cdn=role.application_cdn
   where app.name='Analyse de Risque'
);

insert into role_sial(role_cdn, ac_id, role_ac_defaut_on)
values
(
   (select role_cdn
    from t_orion_role role
	join t_orion_application app on app.application_cdn = role.application_cdn
	where app.name='Analyse de Risque' and role.name='[ARQ] Consultant'
	),
   (select ac_id from attribut_complementaire where ac_code_rfa='STRUCT_DOMTECH!'),
   false
);

insert into role_sial(role_cdn, ac_id, role_ac_defaut_on)
values
(
   (select role_cdn
    from t_orion_role role
	join t_orion_application app on app.application_cdn = role.application_cdn
	where app.name='Analyse de Risque' and role.name='[ARQ] Gestionnaire Local'
	),
   (select ac_id from attribut_complementaire where ac_code_rfa='STRUCT_DOMTECH!'),
   false
);

insert into role_sial(role_cdn, ac_id, role_ac_defaut_on)
values
(
   (select role_cdn
    from t_orion_role role
	join t_orion_application app on app.application_cdn = role.application_cdn
	where app.name='Analyse de Risque' and role.name='[ARQ] Gestionnaire National'
	),
   (select ac_id from attribut_complementaire where ac_code_rfa='DOMTECH!'),
   false
);

insert into role_sial(role_cdn, ac_id, role_ac_defaut_on)
values
(
   (select role_cdn
    from t_orion_role role
	join t_orion_application app on app.application_cdn = role.application_cdn
	where app.name='Analyse de Risque' and role.name='[ARQ] Gestionnaire SIAL'
	),
   null,
   false
);
--------------------------------------------------------------------------------------------------------------------
--------------------------------------- RESYTAL FIN ----------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------


end
$$
LANGUAGE 'plpgsql';

select create_webmin();

drop function create_webmin();
