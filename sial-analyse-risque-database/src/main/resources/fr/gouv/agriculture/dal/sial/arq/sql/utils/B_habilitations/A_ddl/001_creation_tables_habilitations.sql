/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de creation :  06/05/2014 11:41:57                      */
/*==============================================================*/

SET search_path = "habilitations";

SET default_with_oids = false;

--
-- TOC entry 167 (class 1259 OID 140291)
-- Dependencies: 9
-- Name: agricoll_credentials_structure; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE agricoll_credentials_structure (
    pr_cdn integer NOT NULL,
    struct_code_rfa character varying(8)
);


--
-- TOC entry 168 (class 1259 OID 140294)
-- Dependencies: 9
-- Name: attribut_complementaire; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE attribut_complementaire (
    ac_id integer NOT NULL,
    ac_code_rfa character varying(15) NOT NULL,
    ac_lb character varying(50) NOT NULL
);


--
-- TOC entry 169 (class 1259 OID 140297)
-- Dependencies: 9 168
-- Name: attribut_complementaire_ac_id_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE attribut_complementaire_ac_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3193 (class 0 OID 0)
-- Dependencies: 169
-- Name: attribut_complementaire_ac_id_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE attribut_complementaire_ac_id_seq OWNED BY attribut_complementaire.ac_id;


--
-- TOC entry 170 (class 1259 OID 140299)
-- Dependencies: 9
-- Name: bdnu_credentials_structure; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE bdnu_credentials_structure (
    pr_cdn integer NOT NULL,
    struct_code_rfa character varying(8)
);


--
-- TOC entry 228 (class 1259 OID 192962)
-- Dependencies: 9
-- Name: domaine_technique; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE domaine_technique (
    dt_rfa character varying(10) NOT NULL,
    dt_lb character varying(100) NOT NULL,
    dt_court_lb character varying(50) NOT NULL,
    dt_deb_dt date NOT NULL,
    dt_fin_dt date,
    dt_com_lb character varying(255),
    dt_crea_dt date NOT NULL,
    dt_uti_crea_lb character varying(255) NOT NULL,
    dt_modif_dt date,
    dt_uti_modif_lb character varying(255)
);


--
-- TOC entry 3194 (class 0 OID 0)
-- Dependencies: 228
-- Name: TABLE domaine_technique; Type: COMMENT; Schema: habilitations; Owner: -
--

COMMENT ON TABLE domaine_technique IS 'Référentiel SIAL des domaines techniques';


--
-- TOC entry 231 (class 1259 OID 274064)
-- Dependencies: 9
-- Name: fonction_structure; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE fonction_structure (
    fs_id integer NOT NULL,
    fs_code_rfa character varying(5) NOT NULL,
    fs_lb character varying(50) NOT NULL
);


--
-- TOC entry 230 (class 1259 OID 274062)
-- Dependencies: 231 9
-- Name: fonction_structure_fs_id_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE fonction_structure_fs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3195 (class 0 OID 0)
-- Dependencies: 230
-- Name: fonction_structure_fs_id_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE fonction_structure_fs_id_seq OWNED BY fonction_structure.fs_id;


--
-- TOC entry 171 (class 1259 OID 140302)
-- Dependencies: 9
-- Name: rfp_liste_applications; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_applications (
    rfp_cdn integer NOT NULL
);


--
-- TOC entry 172 (class 1259 OID 140305)
-- Dependencies: 9
-- Name: rfp_liste_applications_appli; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_applications_appli (
    rfp_appli_cdn integer NOT NULL,
    rfp_cdn integer NOT NULL,
    application_cdn integer NOT NULL
);


--
-- TOC entry 173 (class 1259 OID 140308)
-- Dependencies: 172 9
-- Name: rfp_liste_applications_appli_rfp_appli_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE rfp_liste_applications_appli_rfp_appli_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3196 (class 0 OID 0)
-- Dependencies: 173
-- Name: rfp_liste_applications_appli_rfp_appli_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE rfp_liste_applications_appli_rfp_appli_cdn_seq OWNED BY rfp_liste_applications_appli.rfp_appli_cdn;


--
-- TOC entry 174 (class 1259 OID 140310)
-- Dependencies: 9
-- Name: rfp_liste_delegations; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_delegations (
    rfp_cdn integer NOT NULL
);


--
-- TOC entry 175 (class 1259 OID 140313)
-- Dependencies: 9
-- Name: rfp_liste_delegations_deleg; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_delegations_deleg (
    rfp_deleg_cdn integer NOT NULL,
    rfp_cdn integer NOT NULL,
    delegation_id character varying(20) NOT NULL
);


--
-- TOC entry 176 (class 1259 OID 140316)
-- Dependencies: 9 175
-- Name: rfp_liste_delegations_deleg_rfp_deleg_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE rfp_liste_delegations_deleg_rfp_deleg_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3197 (class 0 OID 0)
-- Dependencies: 176
-- Name: rfp_liste_delegations_deleg_rfp_deleg_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE rfp_liste_delegations_deleg_rfp_deleg_cdn_seq OWNED BY rfp_liste_delegations_deleg.rfp_deleg_cdn;


--
-- TOC entry 177 (class 1259 OID 140318)
-- Dependencies: 9
-- Name: rfp_liste_dt; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_dt (
    rfp_cdn integer NOT NULL
);


--
-- TOC entry 178 (class 1259 OID 140321)
-- Dependencies: 9
-- Name: rfp_liste_dt_dt; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_dt_dt (
    rfp_dt_id integer NOT NULL,
    rfp_cdn integer NOT NULL,
    dt_rfa character varying(5) NOT NULL
);


--
-- TOC entry 179 (class 1259 OID 140324)
-- Dependencies: 9 178
-- Name: rfp_liste_dt_dt_rfp_dt_id_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE rfp_liste_dt_dt_rfp_dt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3198 (class 0 OID 0)
-- Dependencies: 179
-- Name: rfp_liste_dt_dt_rfp_dt_id_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE rfp_liste_dt_dt_rfp_dt_id_seq OWNED BY rfp_liste_dt_dt.rfp_dt_id;


--
-- TOC entry 180 (class 1259 OID 140326)
-- Dependencies: 9
-- Name: rfp_liste_struct_dt; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_struct_dt (
    rfp_cdn integer NOT NULL
);


--
-- TOC entry 181 (class 1259 OID 140329)
-- Dependencies: 9
-- Name: rfp_liste_struct_dt_sdt; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_struct_dt_sdt (
    rfp_sdt_id integer NOT NULL,
    rfp_cdn integer NOT NULL,
    sdt_id character varying(14) NOT NULL
);


--
-- TOC entry 182 (class 1259 OID 140332)
-- Dependencies: 181 9
-- Name: rfp_liste_struct_dt_sdt_rfp_sdt_id_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE rfp_liste_struct_dt_sdt_rfp_sdt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3199 (class 0 OID 0)
-- Dependencies: 182
-- Name: rfp_liste_struct_dt_sdt_rfp_sdt_id_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE rfp_liste_struct_dt_sdt_rfp_sdt_id_seq OWNED BY rfp_liste_struct_dt_sdt.rfp_sdt_id;


--
-- TOC entry 183 (class 1259 OID 140334)
-- Dependencies: 9
-- Name: rfp_liste_structures; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_structures (
    rfp_cdn integer NOT NULL
);


--
-- TOC entry 184 (class 1259 OID 140337)
-- Dependencies: 3010 9
-- Name: rfp_liste_structures_struct; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE rfp_liste_structures_struct (
    rfp_struct_cdn integer NOT NULL,
    rfp_cdn integer NOT NULL,
    struct_code_rfa character varying(8) NOT NULL,
    struct_defaut_on boolean DEFAULT false NOT NULL
);


--
-- TOC entry 185 (class 1259 OID 140341)
-- Dependencies: 184 9
-- Name: rfp_liste_structures_struct_rfp_struct_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE rfp_liste_structures_struct_rfp_struct_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3200 (class 0 OID 0)
-- Dependencies: 185
-- Name: rfp_liste_structures_struct_rfp_struct_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE rfp_liste_structures_struct_rfp_struct_cdn_seq OWNED BY rfp_liste_structures_struct.rfp_struct_cdn;


--
-- TOC entry 186 (class 1259 OID 140343)
-- Dependencies: 3011 9
-- Name: role_sial; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE role_sial (
    role_cdn integer NOT NULL,
    ac_id integer,
    role_ac_defaut_on boolean DEFAULT false NOT NULL
);


--
-- TOC entry 235 (class 1259 OID 274084)
-- Dependencies: 3029 9
-- Name: structure; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE structure (
    struct_id integer NOT NULL,
    ts_id integer NOT NULL,
    struct_code_rfa character varying(15) NOT NULL,
    struct_lb character varying(100) NOT NULL,
    fs_id integer DEFAULT 1 NOT NULL
);


--
-- TOC entry 234 (class 1259 OID 274082)
-- Dependencies: 9 235
-- Name: structure_struct_id_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE structure_struct_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3201 (class 0 OID 0)
-- Dependencies: 234
-- Name: structure_struct_id_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE structure_struct_id_seq OWNED BY structure.struct_id;


--
-- TOC entry 187 (class 1259 OID 140347)
-- Dependencies: 9
-- Name: t_orion_agricoll_credentials; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_agricoll_credentials (
    pr_cdn integer NOT NULL,
    agri_identifiant character varying(255),
    ou character varying(255)
);


--
-- TOC entry 188 (class 1259 OID 140353)
-- Dependencies: 9
-- Name: t_orion_application; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_application (
    application_cdn integer NOT NULL,
    name character varying(50) NOT NULL
);


--
-- TOC entry 189 (class 1259 OID 140356)
-- Dependencies: 9 188
-- Name: t_orion_application_application_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_application_application_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3202 (class 0 OID 0)
-- Dependencies: 189
-- Name: t_orion_application_application_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_application_application_cdn_seq OWNED BY t_orion_application.application_cdn;


--
-- TOC entry 190 (class 1259 OID 140358)
-- Dependencies: 9
-- Name: t_orion_attribute_infos; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_attribute_infos (
    attinfos_cdn integer NOT NULL,
    override smallint NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    pref_cdn integer,
    value text,
    xml_value text
);


--
-- TOC entry 191 (class 1259 OID 140364)
-- Dependencies: 9 190
-- Name: t_orion_attribute_infos_attinfos_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_attribute_infos_attinfos_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3203 (class 0 OID 0)
-- Dependencies: 191
-- Name: t_orion_attribute_infos_attinfos_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_attribute_infos_attinfos_cdn_seq OWNED BY t_orion_attribute_infos.attinfos_cdn;


--
-- TOC entry 192 (class 1259 OID 140366)
-- Dependencies: 9
-- Name: t_orion_bdnu_credentials; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_bdnu_credentials (
    pr_cdn integer NOT NULL,
    t_o_pr_cdn integer,
    ref_org character varying(32),
    type_iden_rfa character(8),
    iden_entite_rfa character(32)
);


--
-- TOC entry 193 (class 1259 OID 140369)
-- Dependencies: 9
-- Name: t_orion_cerbere_credentials; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_cerbere_credentials (
    pr_cdn integer NOT NULL,
    t_o_pr_cdn integer
);


--
-- TOC entry 194 (class 1259 OID 140372)
-- Dependencies: 9
-- Name: t_orion_civility; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_civility (
    civ_cda character(4) NOT NULL,
    short_label character varying(10),
    long_label character varying(50)
);


--
-- TOC entry 195 (class 1259 OID 140375)
-- Dependencies: 9
-- Name: t_orion_community; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_community (
    owner_cdn integer NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 196 (class 1259 OID 140378)
-- Dependencies: 9
-- Name: t_orion_community_admin; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_community_admin (
    admin_cdn integer NOT NULL,
    community_cdn integer NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 140381)
-- Dependencies: 9
-- Name: t_orion_community_member; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_community_member (
    member_cdn integer NOT NULL,
    community_cdn integer NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 140384)
-- Dependencies: 9
-- Name: t_orion_credentials; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_credentials (
    pr_cdn integer NOT NULL,
    civ_cda character(4),
    beginning_date timestamp without time zone NOT NULL,
    expiry_date timestamp without time zone,
    last_name character varying(100) NOT NULL,
    first_name character varying(100),
    mail character varying(255),
    phone_number character varying(20),
    fax_number character varying(20),
    street character varying(255),
    postal_code character varying(20),
    town character varying(100),
    country character varying(100),
    description character varying(255),
    login_lb character varying(255) NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 140390)
-- Dependencies: 9 198
-- Name: t_orion_credentials_pr_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_credentials_pr_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3204 (class 0 OID 0)
-- Dependencies: 199
-- Name: t_orion_credentials_pr_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_credentials_pr_cdn_seq OWNED BY t_orion_credentials.pr_cdn;


--
-- TOC entry 200 (class 1259 OID 140392)
-- Dependencies: 9
-- Name: t_orion_file_descriptor; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_file_descriptor (
    fd_cdn integer NOT NULL,
    file_name character varying(100),
    file_path character varying(150),
    last_updated timestamp without time zone,
    file_size integer
);


--
-- TOC entry 201 (class 1259 OID 140395)
-- Dependencies: 9 200
-- Name: t_orion_file_descriptor_fd_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_file_descriptor_fd_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3205 (class 0 OID 0)
-- Dependencies: 201
-- Name: t_orion_file_descriptor_fd_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_file_descriptor_fd_cdn_seq OWNED BY t_orion_file_descriptor.fd_cdn;


--
-- TOC entry 202 (class 1259 OID 140397)
-- Dependencies: 9
-- Name: t_orion_group; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_group (
    group_cdn integer NOT NULL,
    groupcat_cdn integer,
    application_cdn integer,
    name character varying(50) NOT NULL,
    description character varying(255)
);


--
-- TOC entry 203 (class 1259 OID 140400)
-- Dependencies: 9
-- Name: t_orion_group_category; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_group_category (
    groupcat_cdn integer NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(255)
);


--
-- TOC entry 204 (class 1259 OID 140403)
-- Dependencies: 9 203
-- Name: t_orion_group_category_groupcat_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_group_category_groupcat_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3206 (class 0 OID 0)
-- Dependencies: 204
-- Name: t_orion_group_category_groupcat_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_group_category_groupcat_cdn_seq OWNED BY t_orion_group_category.groupcat_cdn;


--
-- TOC entry 205 (class 1259 OID 140405)
-- Dependencies: 202 9
-- Name: t_orion_group_group_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_group_group_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3207 (class 0 OID 0)
-- Dependencies: 205
-- Name: t_orion_group_group_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_group_group_cdn_seq OWNED BY t_orion_group.group_cdn;


--
-- TOC entry 206 (class 1259 OID 140407)
-- Dependencies: 9
-- Name: t_orion_group_role; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_group_role (
    group_cdn integer NOT NULL,
    role_cdn integer NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 140410)
-- Dependencies: 9
-- Name: t_orion_guarded_resource; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_guarded_resource (
    gr_cdn integer NOT NULL,
    type character varying(150) NOT NULL,
    name character varying(150) NOT NULL,
    group_cdn integer,
    description character varying(255),
    grcat_cdn integer
);


--
-- TOC entry 208 (class 1259 OID 140416)
-- Dependencies: 9
-- Name: t_orion_guarded_resource_categ; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_guarded_resource_categ (
    grcat_cdn integer NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(255)
);


--
-- TOC entry 209 (class 1259 OID 140419)
-- Dependencies: 9 208
-- Name: t_orion_guarded_resource_categ_grcat_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_guarded_resource_categ_grcat_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3208 (class 0 OID 0)
-- Dependencies: 209
-- Name: t_orion_guarded_resource_categ_grcat_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_guarded_resource_categ_grcat_cdn_seq OWNED BY t_orion_guarded_resource_categ.grcat_cdn;


--
-- TOC entry 210 (class 1259 OID 140421)
-- Dependencies: 9 207
-- Name: t_orion_guarded_resource_gr_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_guarded_resource_gr_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3209 (class 0 OID 0)
-- Dependencies: 210
-- Name: t_orion_guarded_resource_gr_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_guarded_resource_gr_cdn_seq OWNED BY t_orion_guarded_resource.gr_cdn;


--
-- TOC entry 211 (class 1259 OID 140423)
-- Dependencies: 9
-- Name: t_orion_member; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_member (
    owner_cdn integer NOT NULL,
    pr_cdn integer
);


--
-- TOC entry 212 (class 1259 OID 140426)
-- Dependencies: 9
-- Name: t_orion_method_permission; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_method_permission (
    perm_cdn integer NOT NULL
);


--
-- TOC entry 213 (class 1259 OID 140429)
-- Dependencies: 9
-- Name: t_orion_owner; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_owner (
    owner_cdn integer NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 140432)
-- Dependencies: 213 9
-- Name: t_orion_owner_owner_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_owner_owner_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 214
-- Name: t_orion_owner_owner_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_owner_owner_cdn_seq OWNED BY t_orion_owner.owner_cdn;


--
-- TOC entry 215 (class 1259 OID 140434)
-- Dependencies: 9
-- Name: t_orion_permission; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_permission (
    perm_cdn integer NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255) NOT NULL,
    gr_cdn integer,
    description character varying(255)
);


--
-- TOC entry 216 (class 1259 OID 140440)
-- Dependencies: 9 215
-- Name: t_orion_permission_perm_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_permission_perm_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3211 (class 0 OID 0)
-- Dependencies: 216
-- Name: t_orion_permission_perm_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_permission_perm_cdn_seq OWNED BY t_orion_permission.perm_cdn;


--
-- TOC entry 217 (class 1259 OID 140442)
-- Dependencies: 9
-- Name: t_orion_preference; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_preference (
    pref_cdn integer NOT NULL,
    type character varying(255) NOT NULL,
    object_id character varying(100) NOT NULL,
    override integer NOT NULL,
    last_updated timestamp without time zone,
    owner_cdn integer,
    orig_pref_cdn integer
);


--
-- TOC entry 218 (class 1259 OID 140445)
-- Dependencies: 9 217
-- Name: t_orion_preference_pref_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_preference_pref_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3212 (class 0 OID 0)
-- Dependencies: 218
-- Name: t_orion_preference_pref_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_preference_pref_cdn_seq OWNED BY t_orion_preference.pref_cdn;


--
-- TOC entry 219 (class 1259 OID 140447)
-- Dependencies: 9
-- Name: t_orion_property_permission; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_property_permission (
    perm_cdn integer NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 140450)
-- Dependencies: 9
-- Name: t_orion_role; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_role (
    role_cdn integer NOT NULL,
    description character varying(255),
    name character varying(50) NOT NULL,
    application_cdn integer,
    rolecat_cdn integer,
    role_inherited_cdn integer
);


--
-- TOC entry 221 (class 1259 OID 140453)
-- Dependencies: 9
-- Name: t_orion_role_category; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_role_category (
    rolecat_cdn integer NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(255)
);


--
-- TOC entry 222 (class 1259 OID 140456)
-- Dependencies: 221 9
-- Name: t_orion_role_category_rolecat_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_role_category_rolecat_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3213 (class 0 OID 0)
-- Dependencies: 222
-- Name: t_orion_role_category_rolecat_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_role_category_rolecat_cdn_seq OWNED BY t_orion_role_category.rolecat_cdn;


--
-- TOC entry 223 (class 1259 OID 140459)
-- Dependencies: 9
-- Name: t_orion_role_for_principal; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_role_for_principal (
    rfp_cdn integer NOT NULL,
    role_cdn integer,
    pr_cdn integer,
    beginning_date timestamp without time zone,
    expiry_date timestamp without time zone,
    default_role boolean
);


--
-- TOC entry 224 (class 1259 OID 140462)
-- Dependencies: 223 9
-- Name: t_orion_role_for_principal_rfp_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_role_for_principal_rfp_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 224
-- Name: t_orion_role_for_principal_rfp_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_role_for_principal_rfp_cdn_seq OWNED BY t_orion_role_for_principal.rfp_cdn;


--
-- TOC entry 225 (class 1259 OID 140464)
-- Dependencies: 220 9
-- Name: t_orion_role_role_cdn_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE t_orion_role_role_cdn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 225
-- Name: t_orion_role_role_cdn_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE t_orion_role_role_cdn_seq OWNED BY t_orion_role.role_cdn;


--
-- TOC entry 226 (class 1259 OID 140466)
-- Dependencies: 9
-- Name: t_orion_simple_credentials; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE t_orion_simple_credentials (
    pr_cdn integer NOT NULL,
    t_o_pr_cdn integer,
    password character varying(255) NOT NULL
);


--
-- TOC entry 233 (class 1259 OID 274074)
-- Dependencies: 9
-- Name: type_structure; Type: TABLE; Schema: habilitations; Owner: -
--

CREATE TABLE type_structure (
    ts_id integer NOT NULL,
    ts_code_rfa character varying(15) NOT NULL,
    ts_lb character varying(50) NOT NULL
);


--
-- TOC entry 232 (class 1259 OID 274072)
-- Dependencies: 233 9
-- Name: type_structure_ts_id_seq; Type: SEQUENCE; Schema: habilitations; Owner: -
--

CREATE SEQUENCE type_structure_ts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3216 (class 0 OID 0)
-- Dependencies: 232
-- Name: type_structure_ts_id_seq; Type: SEQUENCE OWNED BY; Schema: habilitations; Owner: -
--

ALTER SEQUENCE type_structure_ts_id_seq OWNED BY type_structure.ts_id;


--
-- TOC entry 227 (class 1259 OID 140469)
-- Dependencies: 2996 9
-- Name: v_application; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_application AS
    SELECT appli.application_cdn, appli.name AS appli_lb FROM t_orion_application appli;


--
-- TOC entry 241 (class 1259 OID 274124)
-- Dependencies: 3003 9
-- Name: v_delegation; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_delegation AS
    SELECT (((appli.application_cdn)::text || '-'::text) || (struct.struct_code_rfa)::text) AS delegation_id, appli.application_cdn, struct.struct_code_rfa FROM t_orion_application appli, structure struct;


--
-- TOC entry 166 (class 1259 OID 89727)
-- Dependencies: 2995 9
-- Name: v_domaine_technique; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_domaine_technique AS
    SELECT dt.dt_rfa AS dt_code_rfa, dt.dt_lb, dt.dt_court_lb FROM nomenclatures.domaine_technique dt;


--
-- TOC entry 236 (class 1259 OID 274104)
-- Dependencies: 2998 9
-- Name: v_fonction_structure; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_fonction_structure AS
    SELECT fs.fs_code_rfa, fs.fs_lb FROM fonction_structure fs;


--
-- TOC entry 229 (class 1259 OID 192970)
-- Dependencies: 2997 9
-- Name: v_nomen_domaine_technique_v1_0; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_nomen_domaine_technique_v1_0 AS
    SELECT domaine_technique.dt_rfa, domaine_technique.dt_lb, domaine_technique.dt_court_lb, domaine_technique.dt_deb_dt, domaine_technique.dt_fin_dt, domaine_technique.dt_com_lb, domaine_technique.dt_crea_dt, domaine_technique.dt_uti_crea_lb, domaine_technique.dt_modif_dt, domaine_technique.dt_uti_modif_lb FROM domaine_technique;


--
-- TOC entry 3217 (class 0 OID 0)
-- Dependencies: 229
-- Name: VIEW v_nomen_domaine_technique_v1_0; Type: COMMENT; Schema: habilitations; Owner: -
--

COMMENT ON VIEW v_nomen_domaine_technique_v1_0 IS 'Vue sur la nomenclature DOMAINE TECHNIQUE';


--
-- TOC entry 238 (class 1259 OID 274112)
-- Dependencies: 3000 9
-- Name: v_structure; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_structure AS
    SELECT struct.struct_code_rfa, ts.ts_code_rfa, struct.struct_lb, fs.fs_code_rfa FROM structure struct, type_structure ts, fonction_structure fs WHERE ((struct.ts_id = ts.ts_id) AND (struct.fs_id = fs.fs_id));


--
-- TOC entry 239 (class 1259 OID 274116)
-- Dependencies: 3001 9
-- Name: v_struct_dt; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_struct_dt AS
    SELECT (((struct.struct_code_rfa)::text || '-'::text) || (dt.dt_rfa)::text) AS sdt_id, struct.struct_code_rfa, dt.dt_rfa FROM v_nomen_domaine_technique_v1_0 dt, v_structure struct;


--
-- TOC entry 240 (class 1259 OID 274120)
-- Dependencies: 3002 9
-- Name: v_structure_v1_0; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_structure_v1_0 AS
    SELECT struct.struct_code_rfa, ts.ts_code_rfa, struct.struct_lb, fs.fs_code_rfa FROM structure struct, type_structure ts, fonction_structure fs WHERE ((struct.ts_id = ts.ts_id) AND (struct.fs_id = fs.fs_id));


--
-- TOC entry 237 (class 1259 OID 274108)
-- Dependencies: 2999 9
-- Name: v_type_structure; Type: VIEW; Schema: habilitations; Owner: -
--

CREATE VIEW v_type_structure AS
    SELECT ts.ts_code_rfa, ts.ts_lb FROM type_structure ts;


--
-- TOC entry 3004 (class 2604 OID 140477)
-- Dependencies: 169 168
-- Name: ac_id; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY attribut_complementaire ALTER COLUMN ac_id SET DEFAULT nextval('attribut_complementaire_ac_id_seq'::regclass);


--
-- TOC entry 3026 (class 2604 OID 274067)
-- Dependencies: 230 231 231
-- Name: fs_id; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY fonction_structure ALTER COLUMN fs_id SET DEFAULT nextval('fonction_structure_fs_id_seq'::regclass);


--
-- TOC entry 3005 (class 2604 OID 140478)
-- Dependencies: 173 172
-- Name: rfp_appli_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_applications_appli ALTER COLUMN rfp_appli_cdn SET DEFAULT nextval('rfp_liste_applications_appli_rfp_appli_cdn_seq'::regclass);


--
-- TOC entry 3006 (class 2604 OID 140479)
-- Dependencies: 176 175
-- Name: rfp_deleg_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_delegations_deleg ALTER COLUMN rfp_deleg_cdn SET DEFAULT nextval('rfp_liste_delegations_deleg_rfp_deleg_cdn_seq'::regclass);


--
-- TOC entry 3007 (class 2604 OID 140480)
-- Dependencies: 179 178
-- Name: rfp_dt_id; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_dt_dt ALTER COLUMN rfp_dt_id SET DEFAULT nextval('rfp_liste_dt_dt_rfp_dt_id_seq'::regclass);


--
-- TOC entry 3008 (class 2604 OID 140481)
-- Dependencies: 182 181
-- Name: rfp_sdt_id; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_struct_dt_sdt ALTER COLUMN rfp_sdt_id SET DEFAULT nextval('rfp_liste_struct_dt_sdt_rfp_sdt_id_seq'::regclass);


--
-- TOC entry 3009 (class 2604 OID 140482)
-- Dependencies: 185 184
-- Name: rfp_struct_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_structures_struct ALTER COLUMN rfp_struct_cdn SET DEFAULT nextval('rfp_liste_structures_struct_rfp_struct_cdn_seq'::regclass);


--
-- TOC entry 3028 (class 2604 OID 274087)
-- Dependencies: 234 235 235
-- Name: struct_id; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY structure ALTER COLUMN struct_id SET DEFAULT nextval('structure_struct_id_seq'::regclass);


--
-- TOC entry 3012 (class 2604 OID 140483)
-- Dependencies: 189 188
-- Name: application_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_application ALTER COLUMN application_cdn SET DEFAULT nextval('t_orion_application_application_cdn_seq'::regclass);


--
-- TOC entry 3013 (class 2604 OID 140484)
-- Dependencies: 191 190
-- Name: attinfos_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_attribute_infos ALTER COLUMN attinfos_cdn SET DEFAULT nextval('t_orion_attribute_infos_attinfos_cdn_seq'::regclass);


--
-- TOC entry 3014 (class 2604 OID 140485)
-- Dependencies: 199 198
-- Name: pr_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_credentials ALTER COLUMN pr_cdn SET DEFAULT nextval('t_orion_credentials_pr_cdn_seq'::regclass);


--
-- TOC entry 3015 (class 2604 OID 140486)
-- Dependencies: 201 200
-- Name: fd_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_file_descriptor ALTER COLUMN fd_cdn SET DEFAULT nextval('t_orion_file_descriptor_fd_cdn_seq'::regclass);


--
-- TOC entry 3016 (class 2604 OID 140487)
-- Dependencies: 205 202
-- Name: group_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group ALTER COLUMN group_cdn SET DEFAULT nextval('t_orion_group_group_cdn_seq'::regclass);


--
-- TOC entry 3017 (class 2604 OID 140488)
-- Dependencies: 204 203
-- Name: groupcat_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group_category ALTER COLUMN groupcat_cdn SET DEFAULT nextval('t_orion_group_category_groupcat_cdn_seq'::regclass);


--
-- TOC entry 3018 (class 2604 OID 140489)
-- Dependencies: 210 207
-- Name: gr_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_guarded_resource ALTER COLUMN gr_cdn SET DEFAULT nextval('t_orion_guarded_resource_gr_cdn_seq'::regclass);


--
-- TOC entry 3019 (class 2604 OID 140490)
-- Dependencies: 209 208
-- Name: grcat_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_guarded_resource_categ ALTER COLUMN grcat_cdn SET DEFAULT nextval('t_orion_guarded_resource_categ_grcat_cdn_seq'::regclass);


--
-- TOC entry 3020 (class 2604 OID 140491)
-- Dependencies: 214 213
-- Name: owner_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_owner ALTER COLUMN owner_cdn SET DEFAULT nextval('t_orion_owner_owner_cdn_seq'::regclass);


--
-- TOC entry 3021 (class 2604 OID 140492)
-- Dependencies: 216 215
-- Name: perm_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_permission ALTER COLUMN perm_cdn SET DEFAULT nextval('t_orion_permission_perm_cdn_seq'::regclass);


--
-- TOC entry 3022 (class 2604 OID 140493)
-- Dependencies: 218 217
-- Name: pref_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_preference ALTER COLUMN pref_cdn SET DEFAULT nextval('t_orion_preference_pref_cdn_seq'::regclass);


--
-- TOC entry 3023 (class 2604 OID 140494)
-- Dependencies: 225 220
-- Name: role_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role ALTER COLUMN role_cdn SET DEFAULT nextval('t_orion_role_role_cdn_seq'::regclass);


--
-- TOC entry 3024 (class 2604 OID 140495)
-- Dependencies: 222 221
-- Name: rolecat_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role_category ALTER COLUMN rolecat_cdn SET DEFAULT nextval('t_orion_role_category_rolecat_cdn_seq'::regclass);


--
-- TOC entry 3025 (class 2604 OID 140496)
-- Dependencies: 224 223
-- Name: rfp_cdn; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role_for_principal ALTER COLUMN rfp_cdn SET DEFAULT nextval('t_orion_role_for_principal_rfp_cdn_seq'::regclass);


--
-- TOC entry 3027 (class 2604 OID 274077)
-- Dependencies: 232 233 233
-- Name: ts_id; Type: DEFAULT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY type_structure ALTER COLUMN ts_id SET DEFAULT nextval('type_structure_ts_id_seq'::regclass);


--
-- TOC entry 3133 (class 2606 OID 274071)
-- Dependencies: 231 231
-- Name: ak_fs; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY fonction_structure
    ADD CONSTRAINT ak_fs UNIQUE (fs_code_rfa);


--
-- TOC entry 3141 (class 2606 OID 274092)
-- Dependencies: 235 235
-- Name: ak_struct; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT ak_struct UNIQUE (struct_code_rfa);


--
-- TOC entry 3137 (class 2606 OID 274081)
-- Dependencies: 233 233
-- Name: ak_ts; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY type_structure
    ADD CONSTRAINT ak_ts UNIQUE (ts_code_rfa);


--
-- TOC entry 3031 (class 2606 OID 140498)
-- Dependencies: 167 167
-- Name: pk_agricoll_credentials_struct; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY agricoll_credentials_structure
    ADD CONSTRAINT pk_agricoll_credentials_struct PRIMARY KEY (pr_cdn);


--
-- TOC entry 3033 (class 2606 OID 140500)
-- Dependencies: 168 168
-- Name: pk_attribut_complementaire; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY attribut_complementaire
    ADD CONSTRAINT pk_attribut_complementaire PRIMARY KEY (ac_id);


--
-- TOC entry 3035 (class 2606 OID 140502)
-- Dependencies: 170 170
-- Name: pk_bdnu_credentials_structure; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY bdnu_credentials_structure
    ADD CONSTRAINT pk_bdnu_credentials_structure PRIMARY KEY (pr_cdn);


--
-- TOC entry 3131 (class 2606 OID 192969)
-- Dependencies: 228 228
-- Name: pk_domaine_technique; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY domaine_technique
    ADD CONSTRAINT pk_domaine_technique PRIMARY KEY (dt_rfa);


--
-- TOC entry 3135 (class 2606 OID 274069)
-- Dependencies: 231 231
-- Name: pk_fs; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY fonction_structure
    ADD CONSTRAINT pk_fs PRIMARY KEY (fs_id);


--
-- TOC entry 3037 (class 2606 OID 140504)
-- Dependencies: 171 171
-- Name: pk_rfp_liste_applications; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_applications
    ADD CONSTRAINT pk_rfp_liste_applications PRIMARY KEY (rfp_cdn);


--
-- TOC entry 3039 (class 2606 OID 140506)
-- Dependencies: 172 172
-- Name: pk_rfp_liste_applications_appl; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_applications_appli
    ADD CONSTRAINT pk_rfp_liste_applications_appl PRIMARY KEY (rfp_appli_cdn);


--
-- TOC entry 3041 (class 2606 OID 140508)
-- Dependencies: 174 174
-- Name: pk_rfp_liste_delegations; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_delegations
    ADD CONSTRAINT pk_rfp_liste_delegations PRIMARY KEY (rfp_cdn);


--
-- TOC entry 3043 (class 2606 OID 140510)
-- Dependencies: 175 175
-- Name: pk_rfp_liste_delegations_deleg; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_delegations_deleg
    ADD CONSTRAINT pk_rfp_liste_delegations_deleg PRIMARY KEY (rfp_deleg_cdn);


--
-- TOC entry 3046 (class 2606 OID 140512)
-- Dependencies: 177 177
-- Name: pk_rfp_liste_dt; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_dt
    ADD CONSTRAINT pk_rfp_liste_dt PRIMARY KEY (rfp_cdn);


--
-- TOC entry 3048 (class 2606 OID 140514)
-- Dependencies: 178 178
-- Name: pk_rfp_liste_dt_dt; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_dt_dt
    ADD CONSTRAINT pk_rfp_liste_dt_dt PRIMARY KEY (rfp_dt_id);


--
-- TOC entry 3050 (class 2606 OID 140516)
-- Dependencies: 180 180
-- Name: pk_rfp_liste_struct_dt; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_struct_dt
    ADD CONSTRAINT pk_rfp_liste_struct_dt PRIMARY KEY (rfp_cdn);


--
-- TOC entry 3052 (class 2606 OID 140518)
-- Dependencies: 181 181
-- Name: pk_rfp_liste_struct_dt_sdt; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_struct_dt_sdt
    ADD CONSTRAINT pk_rfp_liste_struct_dt_sdt PRIMARY KEY (rfp_sdt_id);


--
-- TOC entry 3054 (class 2606 OID 140520)
-- Dependencies: 183 183
-- Name: pk_rfp_liste_structures; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_structures
    ADD CONSTRAINT pk_rfp_liste_structures PRIMARY KEY (rfp_cdn);


--
-- TOC entry 3056 (class 2606 OID 140522)
-- Dependencies: 184 184
-- Name: pk_rfp_liste_structures_struct; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_structures_struct
    ADD CONSTRAINT pk_rfp_liste_structures_struct PRIMARY KEY (rfp_struct_cdn);


--
-- TOC entry 3059 (class 2606 OID 140524)
-- Dependencies: 186 186
-- Name: pk_role_sial; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY role_sial
    ADD CONSTRAINT pk_role_sial PRIMARY KEY (role_cdn);


--
-- TOC entry 3144 (class 2606 OID 274090)
-- Dependencies: 235 235
-- Name: pk_struct; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT pk_struct PRIMARY KEY (struct_id);


--
-- TOC entry 3061 (class 2606 OID 140526)
-- Dependencies: 187 187
-- Name: pk_t_orion_agricoll_credential; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_agricoll_credentials
    ADD CONSTRAINT pk_t_orion_agricoll_credential PRIMARY KEY (pr_cdn);


--
-- TOC entry 3063 (class 2606 OID 140528)
-- Dependencies: 188 188
-- Name: pk_t_orion_application; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_application
    ADD CONSTRAINT pk_t_orion_application PRIMARY KEY (application_cdn);


--
-- TOC entry 3066 (class 2606 OID 140530)
-- Dependencies: 190 190
-- Name: pk_t_orion_attribute_infos; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_attribute_infos
    ADD CONSTRAINT pk_t_orion_attribute_infos PRIMARY KEY (attinfos_cdn);


--
-- TOC entry 3068 (class 2606 OID 140532)
-- Dependencies: 192 192
-- Name: pk_t_orion_bdnu_credentials; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_bdnu_credentials
    ADD CONSTRAINT pk_t_orion_bdnu_credentials PRIMARY KEY (pr_cdn);


--
-- TOC entry 3070 (class 2606 OID 140534)
-- Dependencies: 193 193
-- Name: pk_t_orion_cerbere_credentials; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_cerbere_credentials
    ADD CONSTRAINT pk_t_orion_cerbere_credentials PRIMARY KEY (pr_cdn);


--
-- TOC entry 3072 (class 2606 OID 140536)
-- Dependencies: 194 194
-- Name: pk_t_orion_civility; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_civility
    ADD CONSTRAINT pk_t_orion_civility PRIMARY KEY (civ_cda);


--
-- TOC entry 3074 (class 2606 OID 140538)
-- Dependencies: 195 195
-- Name: pk_t_orion_community; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community
    ADD CONSTRAINT pk_t_orion_community PRIMARY KEY (owner_cdn);


--
-- TOC entry 3077 (class 2606 OID 140540)
-- Dependencies: 196 196 196
-- Name: pk_t_orion_community_admin; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community_admin
    ADD CONSTRAINT pk_t_orion_community_admin PRIMARY KEY (admin_cdn, community_cdn);


--
-- TOC entry 3080 (class 2606 OID 140542)
-- Dependencies: 197 197 197
-- Name: pk_t_orion_community_member; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community_member
    ADD CONSTRAINT pk_t_orion_community_member PRIMARY KEY (member_cdn, community_cdn);


--
-- TOC entry 3083 (class 2606 OID 140544)
-- Dependencies: 198 198
-- Name: pk_t_orion_credentials; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_credentials
    ADD CONSTRAINT pk_t_orion_credentials PRIMARY KEY (pr_cdn);


--
-- TOC entry 3085 (class 2606 OID 140546)
-- Dependencies: 200 200
-- Name: pk_t_orion_file_descriptor; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_file_descriptor
    ADD CONSTRAINT pk_t_orion_file_descriptor PRIMARY KEY (fd_cdn);


--
-- TOC entry 3089 (class 2606 OID 140548)
-- Dependencies: 202 202
-- Name: pk_t_orion_group; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group
    ADD CONSTRAINT pk_t_orion_group PRIMARY KEY (group_cdn);


--
-- TOC entry 3091 (class 2606 OID 140550)
-- Dependencies: 203 203
-- Name: pk_t_orion_group_category; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group_category
    ADD CONSTRAINT pk_t_orion_group_category PRIMARY KEY (groupcat_cdn);


--
-- TOC entry 3094 (class 2606 OID 140552)
-- Dependencies: 206 206 206
-- Name: pk_t_orion_group_role; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group_role
    ADD CONSTRAINT pk_t_orion_group_role PRIMARY KEY (role_cdn, group_cdn);


--
-- TOC entry 3098 (class 2606 OID 140554)
-- Dependencies: 207 207
-- Name: pk_t_orion_guarded_resource; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_guarded_resource
    ADD CONSTRAINT pk_t_orion_guarded_resource PRIMARY KEY (gr_cdn);


--
-- TOC entry 3100 (class 2606 OID 140556)
-- Dependencies: 208 208
-- Name: pk_t_orion_guarded_resource_ca; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_guarded_resource_categ
    ADD CONSTRAINT pk_t_orion_guarded_resource_ca PRIMARY KEY (grcat_cdn);


--
-- TOC entry 3103 (class 2606 OID 140558)
-- Dependencies: 211 211
-- Name: pk_t_orion_member; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_member
    ADD CONSTRAINT pk_t_orion_member PRIMARY KEY (owner_cdn);


--
-- TOC entry 3105 (class 2606 OID 140560)
-- Dependencies: 212 212
-- Name: pk_t_orion_method_permission; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_method_permission
    ADD CONSTRAINT pk_t_orion_method_permission PRIMARY KEY (perm_cdn);


--
-- TOC entry 3107 (class 2606 OID 140562)
-- Dependencies: 213 213
-- Name: pk_t_orion_owner; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_owner
    ADD CONSTRAINT pk_t_orion_owner PRIMARY KEY (owner_cdn);


--
-- TOC entry 3110 (class 2606 OID 140564)
-- Dependencies: 215 215
-- Name: pk_t_orion_permission; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_permission
    ADD CONSTRAINT pk_t_orion_permission PRIMARY KEY (perm_cdn);


--
-- TOC entry 3114 (class 2606 OID 140566)
-- Dependencies: 217 217
-- Name: pk_t_orion_preference; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_preference
    ADD CONSTRAINT pk_t_orion_preference PRIMARY KEY (pref_cdn);


--
-- TOC entry 3116 (class 2606 OID 140568)
-- Dependencies: 219 219
-- Name: pk_t_orion_property_permission; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_property_permission
    ADD CONSTRAINT pk_t_orion_property_permission PRIMARY KEY (perm_cdn);


--
-- TOC entry 3121 (class 2606 OID 140570)
-- Dependencies: 220 220
-- Name: pk_t_orion_role; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role
    ADD CONSTRAINT pk_t_orion_role PRIMARY KEY (role_cdn);


--
-- TOC entry 3123 (class 2606 OID 140572)
-- Dependencies: 221 221
-- Name: pk_t_orion_role_category; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role_category
    ADD CONSTRAINT pk_t_orion_role_category PRIMARY KEY (rolecat_cdn);


--
-- TOC entry 3127 (class 2606 OID 140574)
-- Dependencies: 223 223
-- Name: pk_t_orion_role_for_principal; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role_for_principal
    ADD CONSTRAINT pk_t_orion_role_for_principal PRIMARY KEY (rfp_cdn);


--
-- TOC entry 3129 (class 2606 OID 140576)
-- Dependencies: 226 226
-- Name: pk_t_orion_simple_credentials; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_simple_credentials
    ADD CONSTRAINT pk_t_orion_simple_credentials PRIMARY KEY (pr_cdn);


--
-- TOC entry 3139 (class 2606 OID 274079)
-- Dependencies: 233 233
-- Name: pk_ts; Type: CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY type_structure
    ADD CONSTRAINT pk_ts PRIMARY KEY (ts_id);


--
-- TOC entry 3044 (class 1259 OID 140577)
-- Dependencies: 177
-- Name: fki_test; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX fki_test ON rfp_liste_dt USING btree (rfp_cdn);


--
-- TOC entry 3064 (class 1259 OID 140578)
-- Dependencies: 190
-- Name: i_attinfos_pref_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_attinfos_pref_fk ON t_orion_attribute_infos USING btree (pref_cdn);


--
-- TOC entry 3078 (class 1259 OID 140579)
-- Dependencies: 197
-- Name: i_com_member_com_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_com_member_com_fk ON t_orion_community_member USING btree (community_cdn);


--
-- TOC entry 3075 (class 1259 OID 140580)
-- Dependencies: 196
-- Name: i_community_admin_community_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_community_admin_community_fk ON t_orion_community_admin USING btree (community_cdn);


--
-- TOC entry 3095 (class 1259 OID 140581)
-- Dependencies: 207
-- Name: i_gr_grcateg_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_gr_grcateg_fk ON t_orion_guarded_resource USING btree (grcat_cdn);


--
-- TOC entry 3096 (class 1259 OID 140582)
-- Dependencies: 207
-- Name: i_gr_group_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_gr_group_fk ON t_orion_guarded_resource USING btree (group_cdn);


--
-- TOC entry 3086 (class 1259 OID 140583)
-- Dependencies: 202
-- Name: i_group_application_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_group_application_fk ON t_orion_group USING btree (application_cdn);


--
-- TOC entry 3087 (class 1259 OID 140584)
-- Dependencies: 202
-- Name: i_group_groupcateg_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_group_groupcateg_fk ON t_orion_group USING btree (groupcat_cdn);


--
-- TOC entry 3092 (class 1259 OID 140585)
-- Dependencies: 206
-- Name: i_groupe_role_role_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_groupe_role_role_fk ON t_orion_group_role USING btree (group_cdn);


--
-- TOC entry 3101 (class 1259 OID 140586)
-- Dependencies: 211
-- Name: i_member_pr_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_member_pr_fk ON t_orion_member USING btree (pr_cdn);


--
-- TOC entry 3081 (class 1259 OID 140587)
-- Dependencies: 198
-- Name: i_oc_civility_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_oc_civility_fk ON t_orion_credentials USING btree (civ_cda);


--
-- TOC entry 3108 (class 1259 OID 140588)
-- Dependencies: 215
-- Name: i_perm_gr_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_perm_gr_fk ON t_orion_permission USING btree (gr_cdn);


--
-- TOC entry 3111 (class 1259 OID 140589)
-- Dependencies: 217
-- Name: i_pref_owner_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_pref_owner_fk ON t_orion_preference USING btree (owner_cdn);


--
-- TOC entry 3112 (class 1259 OID 140590)
-- Dependencies: 217
-- Name: i_pref_pref_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_pref_pref_fk ON t_orion_preference USING btree (orig_pref_cdn);


--
-- TOC entry 3124 (class 1259 OID 140591)
-- Dependencies: 223
-- Name: i_rfp_oc_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_rfp_oc_fk ON t_orion_role_for_principal USING btree (pr_cdn);


--
-- TOC entry 3125 (class 1259 OID 140592)
-- Dependencies: 223
-- Name: i_rfp_role_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_rfp_role_fk ON t_orion_role_for_principal USING btree (role_cdn);


--
-- TOC entry 3057 (class 1259 OID 140593)
-- Dependencies: 186
-- Name: i_role_ac_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_role_ac_fk ON role_sial USING btree (ac_id);


--
-- TOC entry 3117 (class 1259 OID 140594)
-- Dependencies: 220
-- Name: i_role_application_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_role_application_fk ON t_orion_role USING btree (application_cdn);


--
-- TOC entry 3118 (class 1259 OID 140595)
-- Dependencies: 220
-- Name: i_role_role_inherited_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_role_role_inherited_fk ON t_orion_role USING btree (role_inherited_cdn);


--
-- TOC entry 3119 (class 1259 OID 140596)
-- Dependencies: 220
-- Name: i_role_rolecateg_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_role_rolecateg_fk ON t_orion_role USING btree (rolecat_cdn);


--
-- TOC entry 3142 (class 1259 OID 274103)
-- Dependencies: 235
-- Name: i_struct_ts_fk; Type: INDEX; Schema: habilitations; Owner: -
--

CREATE INDEX i_struct_ts_fk ON structure USING btree (ts_id);


--
-- TOC entry 3160 (class 2606 OID 140597)
-- Dependencies: 187 3082 198
-- Name: fk_agricoll_pr_pr; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_agricoll_credentials
    ADD CONSTRAINT fk_agricoll_pr_pr FOREIGN KEY (pr_cdn) REFERENCES t_orion_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3145 (class 2606 OID 140602)
-- Dependencies: 187 3060 167
-- Name: fk_agricred_agricredstruct; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY agricoll_credentials_structure
    ADD CONSTRAINT fk_agricred_agricredstruct FOREIGN KEY (pr_cdn) REFERENCES t_orion_agricoll_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3161 (class 2606 OID 140607)
-- Dependencies: 217 3113 190
-- Name: fk_attinfos_pref; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_attribute_infos
    ADD CONSTRAINT fk_attinfos_pref FOREIGN KEY (pref_cdn) REFERENCES t_orion_preference(pref_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3162 (class 2606 OID 140612)
-- Dependencies: 198 3082 192
-- Name: fk_bdnu_pr_pr; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_bdnu_credentials
    ADD CONSTRAINT fk_bdnu_pr_pr FOREIGN KEY (t_o_pr_cdn) REFERENCES t_orion_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3146 (class 2606 OID 140617)
-- Dependencies: 3067 192 170
-- Name: fk_bdnucred_bdnucredsstruct; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY bdnu_credentials_structure
    ADD CONSTRAINT fk_bdnucred_bdnucredsstruct FOREIGN KEY (pr_cdn) REFERENCES t_orion_bdnu_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3163 (class 2606 OID 140622)
-- Dependencies: 193 3082 198
-- Name: fk_cerbere_pr_pr; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_cerbere_credentials
    ADD CONSTRAINT fk_cerbere_pr_pr FOREIGN KEY (t_o_pr_cdn) REFERENCES t_orion_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3165 (class 2606 OID 140627)
-- Dependencies: 196 195 3073
-- Name: fk_community_admin_community; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community_admin
    ADD CONSTRAINT fk_community_admin_community FOREIGN KEY (community_cdn) REFERENCES t_orion_community(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3166 (class 2606 OID 140632)
-- Dependencies: 196 211 3102
-- Name: fk_community_admin_member; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community_admin
    ADD CONSTRAINT fk_community_admin_member FOREIGN KEY (admin_cdn) REFERENCES t_orion_member(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3167 (class 2606 OID 140637)
-- Dependencies: 197 195 3073
-- Name: fk_community_member_community; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community_member
    ADD CONSTRAINT fk_community_member_community FOREIGN KEY (community_cdn) REFERENCES t_orion_community(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3168 (class 2606 OID 140642)
-- Dependencies: 197 211 3102
-- Name: fk_community_member_member; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community_member
    ADD CONSTRAINT fk_community_member_member FOREIGN KEY (member_cdn) REFERENCES t_orion_member(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3164 (class 2606 OID 140647)
-- Dependencies: 195 213 3106
-- Name: fk_community_owner; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_community
    ADD CONSTRAINT fk_community_owner FOREIGN KEY (owner_cdn) REFERENCES t_orion_owner(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3174 (class 2606 OID 140652)
-- Dependencies: 207 208 3099
-- Name: fk_gr_grcateg; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_guarded_resource
    ADD CONSTRAINT fk_gr_grcateg FOREIGN KEY (grcat_cdn) REFERENCES t_orion_guarded_resource_categ(grcat_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3175 (class 2606 OID 140657)
-- Dependencies: 207 202 3088
-- Name: fk_gr_group; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_guarded_resource
    ADD CONSTRAINT fk_gr_group FOREIGN KEY (group_cdn) REFERENCES t_orion_group(group_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3170 (class 2606 OID 140662)
-- Dependencies: 202 188 3062
-- Name: fk_group_application; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group
    ADD CONSTRAINT fk_group_application FOREIGN KEY (application_cdn) REFERENCES t_orion_application(application_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3171 (class 2606 OID 140667)
-- Dependencies: 202 203 3090
-- Name: fk_group_groupcateg; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group
    ADD CONSTRAINT fk_group_groupcateg FOREIGN KEY (groupcat_cdn) REFERENCES t_orion_group_category(groupcat_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3172 (class 2606 OID 140672)
-- Dependencies: 206 202 3088
-- Name: fk_groupe_role_group; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group_role
    ADD CONSTRAINT fk_groupe_role_group FOREIGN KEY (group_cdn) REFERENCES t_orion_group(group_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3173 (class 2606 OID 140677)
-- Dependencies: 3120 220 206
-- Name: fk_groupe_role_role; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_group_role
    ADD CONSTRAINT fk_groupe_role_role FOREIGN KEY (role_cdn) REFERENCES t_orion_role(role_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3176 (class 2606 OID 140682)
-- Dependencies: 211 213 3106
-- Name: fk_member_owner; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_member
    ADD CONSTRAINT fk_member_owner FOREIGN KEY (owner_cdn) REFERENCES t_orion_owner(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3177 (class 2606 OID 140687)
-- Dependencies: 3082 211 198
-- Name: fk_member_pr; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_member
    ADD CONSTRAINT fk_member_pr FOREIGN KEY (pr_cdn) REFERENCES t_orion_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3169 (class 2606 OID 140692)
-- Dependencies: 194 198 3071
-- Name: fk_pr_civility; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_credentials
    ADD CONSTRAINT fk_pr_civility FOREIGN KEY (civ_cda) REFERENCES t_orion_civility(civ_cda) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3180 (class 2606 OID 140697)
-- Dependencies: 213 217 3106
-- Name: fk_pref_owner; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_preference
    ADD CONSTRAINT fk_pref_owner FOREIGN KEY (owner_cdn) REFERENCES t_orion_owner(owner_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3181 (class 2606 OID 140702)
-- Dependencies: 217 217 3113
-- Name: fk_pref_pref; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_preference
    ADD CONSTRAINT fk_pref_pref FOREIGN KEY (orig_pref_cdn) REFERENCES t_orion_preference(pref_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3151 (class 2606 OID 140707)
-- Dependencies: 223 177 3126
-- Name: fk_rfp_dt; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_dt
    ADD CONSTRAINT fk_rfp_dt FOREIGN KEY (rfp_cdn) REFERENCES t_orion_role_for_principal(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3153 (class 2606 OID 194021)
-- Dependencies: 178 228 3130
-- Name: fk_rfp_list_rfp_domaine_tech; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_dt_dt
    ADD CONSTRAINT fk_rfp_list_rfp_domaine_tech FOREIGN KEY (dt_rfa) REFERENCES domaine_technique(dt_rfa) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3152 (class 2606 OID 140712)
-- Dependencies: 3045 178 177
-- Name: fk_rfp_list_rfpdtdt_d_rfp_list; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_dt_dt
    ADD CONSTRAINT fk_rfp_list_rfpdtdt_d_rfp_list FOREIGN KEY (rfp_cdn) REFERENCES rfp_liste_dt(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3155 (class 2606 OID 140717)
-- Dependencies: 181 180 3049
-- Name: fk_rfp_list_rfpsdtsdt_rfp_list; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_struct_dt_sdt
    ADD CONSTRAINT fk_rfp_list_rfpsdtsdt_rfp_list FOREIGN KEY (rfp_cdn) REFERENCES rfp_liste_struct_dt(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3147 (class 2606 OID 140722)
-- Dependencies: 3126 171 223
-- Name: fk_rfp_rfpappli; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_applications
    ADD CONSTRAINT fk_rfp_rfpappli FOREIGN KEY (rfp_cdn) REFERENCES t_orion_role_for_principal(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3149 (class 2606 OID 140727)
-- Dependencies: 223 174 3126
-- Name: fk_rfp_rfpdeleg; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_delegations
    ADD CONSTRAINT fk_rfp_rfpdeleg FOREIGN KEY (rfp_cdn) REFERENCES t_orion_role_for_principal(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3156 (class 2606 OID 140732)
-- Dependencies: 223 183 3126
-- Name: fk_rfp_rfpstruct; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_structures
    ADD CONSTRAINT fk_rfp_rfpstruct FOREIGN KEY (rfp_cdn) REFERENCES t_orion_role_for_principal(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3186 (class 2606 OID 140737)
-- Dependencies: 220 223 3120
-- Name: fk_rfp_role; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role_for_principal
    ADD CONSTRAINT fk_rfp_role FOREIGN KEY (role_cdn) REFERENCES t_orion_role(role_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3154 (class 2606 OID 140742)
-- Dependencies: 223 180 3126
-- Name: fk_rfp_stdt; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_struct_dt
    ADD CONSTRAINT fk_rfp_stdt FOREIGN KEY (rfp_cdn) REFERENCES t_orion_role_for_principal(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3148 (class 2606 OID 140747)
-- Dependencies: 171 172 3036
-- Name: fk_rfpappli_appli; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_applications_appli
    ADD CONSTRAINT fk_rfpappli_appli FOREIGN KEY (rfp_cdn) REFERENCES rfp_liste_applications(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3150 (class 2606 OID 140752)
-- Dependencies: 175 174 3040
-- Name: fk_rfpdeleg_deleg; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_delegations_deleg
    ADD CONSTRAINT fk_rfpdeleg_deleg FOREIGN KEY (rfp_cdn) REFERENCES rfp_liste_delegations(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3157 (class 2606 OID 140757)
-- Dependencies: 3053 183 184
-- Name: fk_rfpstruct_struct; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY rfp_liste_structures_struct
    ADD CONSTRAINT fk_rfpstruct_struct FOREIGN KEY (rfp_cdn) REFERENCES rfp_liste_structures(rfp_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3158 (class 2606 OID 140762)
-- Dependencies: 3032 186 168
-- Name: fk_role_ac; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY role_sial
    ADD CONSTRAINT fk_role_ac FOREIGN KEY (ac_id) REFERENCES attribut_complementaire(ac_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3183 (class 2606 OID 140767)
-- Dependencies: 188 220 3062
-- Name: fk_role_application; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role
    ADD CONSTRAINT fk_role_application FOREIGN KEY (application_cdn) REFERENCES t_orion_application(application_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3184 (class 2606 OID 140772)
-- Dependencies: 220 220 3120
-- Name: fk_role_role; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role
    ADD CONSTRAINT fk_role_role FOREIGN KEY (role_inherited_cdn) REFERENCES t_orion_role(role_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3185 (class 2606 OID 140777)
-- Dependencies: 221 220 3122
-- Name: fk_role_rolecateg; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role
    ADD CONSTRAINT fk_role_rolecateg FOREIGN KEY (rolecat_cdn) REFERENCES t_orion_role_category(rolecat_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3159 (class 2606 OID 140782)
-- Dependencies: 186 220 3120
-- Name: fk_role_rolesial; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY role_sial
    ADD CONSTRAINT fk_role_rolesial FOREIGN KEY (role_cdn) REFERENCES t_orion_role(role_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3188 (class 2606 OID 140787)
-- Dependencies: 226 198 3082
-- Name: fk_simple_pr_pr; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_simple_credentials
    ADD CONSTRAINT fk_simple_pr_pr FOREIGN KEY (t_o_pr_cdn) REFERENCES t_orion_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3189 (class 2606 OID 274093)
-- Dependencies: 235 231 3134
-- Name: fk_struct_fs; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT fk_struct_fs FOREIGN KEY (fs_id) REFERENCES fonction_structure(fs_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3190 (class 2606 OID 274098)
-- Dependencies: 3138 235 233
-- Name: fk_struct_ts; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY structure
    ADD CONSTRAINT fk_struct_ts FOREIGN KEY (ts_id) REFERENCES type_structure(ts_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3178 (class 2606 OID 140792)
-- Dependencies: 3109 212 215
-- Name: fk_t_orion__methodper_t_orion_; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_method_permission
    ADD CONSTRAINT fk_t_orion__methodper_t_orion_ FOREIGN KEY (perm_cdn) REFERENCES t_orion_permission(perm_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3179 (class 2606 OID 140797)
-- Dependencies: 207 3097 215
-- Name: fk_t_orion__perm_gr_t_orion_; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_permission
    ADD CONSTRAINT fk_t_orion__perm_gr_t_orion_ FOREIGN KEY (gr_cdn) REFERENCES t_orion_guarded_resource(gr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3182 (class 2606 OID 140802)
-- Dependencies: 219 215 3109
-- Name: fk_t_orion__propertyp_t_orion_; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_property_permission
    ADD CONSTRAINT fk_t_orion__propertyp_t_orion_ FOREIGN KEY (perm_cdn) REFERENCES t_orion_permission(perm_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3187 (class 2606 OID 140807)
-- Dependencies: 223 198 3082
-- Name: fk_t_orion__rfp_pr_t_orion_; Type: FK CONSTRAINT; Schema: habilitations; Owner: -
--

ALTER TABLE ONLY t_orion_role_for_principal
    ADD CONSTRAINT fk_t_orion__rfp_pr_t_orion_ FOREIGN KEY (pr_cdn) REFERENCES t_orion_credentials(pr_cdn) ON UPDATE RESTRICT ON DELETE RESTRICT;
