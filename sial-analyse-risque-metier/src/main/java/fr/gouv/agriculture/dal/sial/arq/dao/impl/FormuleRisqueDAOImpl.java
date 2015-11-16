package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;

import java.util.Collection;

import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO;
import fr.gouv.agriculture.dal.sial.arq.query.translator.DefaultExpressionTranslator;
import fr.gouv.agriculture.dal.sial.arq.query.translator.ExpressionTranslator;
import fr.gouv.agriculture.dal.sial.arq.query.translator.ObjectToObjectIdentifierTranslator;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.builder.QueryBuilder;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.HQLExpression;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.operator.In;
import fr.gouv.agriculture.orion.report.PropertyDescriptor;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.datasource.DefaultPropertyDescriptor;
import fr.gouv.agriculture.orion.report.datasource.ReportCollectionBeanDataSource;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * DAO pour les formules de risques
 *
 * @author sopra
 *
 */
public class FormuleRisqueDAOImpl extends DefaultBusinessDAO implements FormuleRisqueDAO {

    /**
     * Service de gestion de l'utilisateur
     */
    @Inject
    private HabilitationsService habilitationsService;

    /**
     * Alias risque Theorique
     */
    public static final String ALIAS_FOR_RISQUE_THEORIQUE = "risquetheorique";
    /**
     * Alias Zone
     */
    public static final String ALIAS_FOR_ZONE = "zone";
    /**
     * Alias Volume
     */
    public static final String ALIAS_FOR_VOLUME = "volume";
    /**
     * Alias Diffusion
     */
    public static final String ALIAS_FOR_DIFFUSION = "diffusion";
    /**
     * Alias Destination
     */
    public static final String ALIAS_FOR_DESTINATION = "destination";
    /**
     * Alias Note Inspection
     */
    public static final String ALIAS_FOR_NOTE_INSPECTION = "noteInspection";

    /**
     * Nom Colonne Domaine Technique
     */
    public static final String COLONNE_DOMAINE_TECHNIQUE = "domaineTechnique";
    /**
     * Nom Colonne Campagne
     */
    public static final String COLONNE_CAMPAGNE = "campagne";
    /**
     * Nom Colonne FORM_ID
     */
    public static final String COLONNE_ID_FORMULE = "formId";

    /**
     * Alias RFA de Campagne
     */
    public static final String CAMPAGNE_PARAM_NAME = "campagne.campRfa";
    /**
     * Alias Domaine Technique
     */
    public static final String DOMAINE_TECHNIQUE_PARAM_NAME = "domaineTechnique";
    /**
     * Alias Libellé du µRisque Theorique
     */
    public static final String RISQUE_THEORIQUE_LB_PARAM_NAME = "fromCritRisquetheoriqueOnLb";
    /**
     * Alias Libellé Zone
     */
    public static final String ZONE_LB_PARAM_NAME = "fromCritZoneOnLb";
    /**
     * Alias Libellé Volume
     */
    public static final String VOLUME_LB_PARAM_NAME = "fromCritVolumeOnLb";
    /**
     * Alias Libellé Diffusion
     */
    public static final String DIFFUSION_LB_PARAM_NAME = "fromCritDiffusionOnLb";
    /**
     * Alias Libellé Destination
     */
    public static final String DESTINATION_LB_PARAM_NAME = "fromCritDestinationOnLb";
    /**
     * Alias Libellé Note Inspection
     */
    public static final String NOTE_INSPECTION_LB_PARAM_NAME = "fromCritNoteInspectionOnLb";

    /**
     * requete stockée d'existance d'une formule de risque (cas de création)
     */
    public static final String REQ_EXISTS_FORMULE = "existsFormuleRisqueCampDt";
    /**
     * requete stockée d'existance d'une formule de risque (cas de modification)
     */
    public static final String REQ_EXISTS_FORMULE_NOT_ID = "existsFormuleRisqueCampDtNotId";

    @Override
    public Collection findBy(BusinessSearchContext businessSearchContext) throws Exception {

        Query query = businessSearchContext.getQuery();
        Expression oldExpression = query.getExpression();
        try {
            Expression newExpression = buildExpressionTranslator().translate(oldExpression);
            query.setExpression(newExpression);
            return super.findBy(businessSearchContext);
        } finally {
            query.setExpression(oldExpression);
        }
    }

    private ExpressionTranslator buildExpressionTranslator() {

        DefaultExpressionTranslator res = new DefaultExpressionTranslator();
        res.addTranslatorForAlias(ALIAS_FOR_RISQUE_THEORIQUE, new ObjectToObjectIdentifierTranslator(null));
        res.addTranslatorForAlias(ALIAS_FOR_ZONE, new ObjectToObjectIdentifierTranslator(null));
        res.addTranslatorForAlias(ALIAS_FOR_VOLUME, new ObjectToObjectIdentifierTranslator(null));
        res.addTranslatorForAlias(ALIAS_FOR_DIFFUSION, new ObjectToObjectIdentifierTranslator(null));
        res.addTranslatorForAlias(ALIAS_FOR_DESTINATION, new ObjectToObjectIdentifierTranslator(null));
        res.addTranslatorForAlias(ALIAS_FOR_NOTE_INSPECTION, new ObjectToObjectIdentifierTranslator(null));
        return res;
    }

    /*
     * {@inheritDoc}
     * 
     * @see fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO#controleUniciteFormule(FormuleRisque)
     *
     //@Override
     public boolean controleUniciteFormule2(FormuleRisque formuleRisque) throws Exception {
     boolean res=true;
     BusinessSearchContext bsc = new BusinessSearchContext();
     QueryBuilder queryBuilder = new QueryBuilder();
     queryBuilder.from(FormuleRisque.class);
     Collection<FormuleRisque> formules;
     Junction junction = new Conjunction();
     Expression expression1 = queryBuilder.equals(COLONNE_DOMAINE_TECHNIQUE, formuleRisque.getDomaineTechnique());
     Expression expression2 = queryBuilder.equals(COLONNE_CAMPAGNE, formuleRisque.getCampagne());
     //Expression expression3 = queryBuilder.notEquals(COLONNE_ID_FORMULE, formuleRisque.getFormId());
        
     junction.add(expression1);
     junction.add(expression2);
     //        junction.add(expression3);
        
     Query queryParam = queryBuilder.getQuery();
     queryParam.setExpression(junction);
     bsc.setQuery(queryParam);
        
     formules = super.findBy(bsc);
        
     if (formules != null && !formules.isEmpty()) {
     for (FormuleRisque formule : formules) {
     if (formule.getFormId() != formuleRisque.getFormId()) {
     res=false;
     }
     }
     }
        
     return res;
     }*/
    /**
     * {@inheritDoc}
     *
     * @see
     * fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDAO#controleUniciteFormule(FormuleRisque)
     */
    @Override
    public boolean controleUniciteFormule(FormuleRisque formuleRisque) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(COLONNE_CAMPAGNE, formuleRisque.getCampagne().getCampRfa());
        params.put(COLONNE_DOMAINE_TECHNIQUE, formuleRisque.getDomaineTechnique().getDtRfa());

        Integer formId = formuleRisque.getFormId();
        Collection resultSet = null;

        // cas de la création
        if (formId == null) {
            resultSet = getPersistenceService().findUsingNamedQuery(
                    REQ_EXISTS_FORMULE, params, false);
        } else {
            params.put(COLONNE_ID_FORMULE, formId);
            resultSet = getPersistenceService().findUsingNamedQuery(
                    REQ_EXISTS_FORMULE_NOT_ID, params, false);
        }

        return !resultSet.iterator().hasNext();

    }

    @Override
    public boolean formuleExistForCampagneAndDt(String campagneRfa, String domaineTechRfa)  throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(COLONNE_CAMPAGNE, campagneRfa);
        params.put(COLONNE_DOMAINE_TECHNIQUE, domaineTechRfa);

       Collection resultSet = getPersistenceService().findUsingNamedQuery(
                REQ_EXISTS_FORMULE, params, false);

        return resultSet.iterator().hasNext();
    }

    /**
     * Récupère les données de l'export, le nom du fichier et des colonnes
     *
     * @param reportName
     * @param context
     * @return
     * @throws PersistenceException
     * @throws Exception
     */
    @Override
    public ReportDataSource getReportDataSource(String reportName, BusinessSearchContext context)
            throws PersistenceException, Exception {

        // Colonnes exportées.
        List<PropertyDescriptor> propertyDescriptors = new LinkedList<PropertyDescriptor>();

        Collection collection = null;

        Integer previousMaxValue = context.getMaxObjects();

        // Suppression de la limite
        context.setMaxObjects(Integer.MAX_VALUE);
        // Suppression de l'ordre pré établi
        context.getQuery().getOrders().clear();
        // Ajout des order by
        context.getQuery().addOrder(new Order(CAMPAGNE_PARAM_NAME, true, true));

            // Filtrer l'export suivant les habilitations de l'utilisateur
        // on obtient l'expression initialie pour le sauvegarder
        Expression expressionInitiale = context.getQuery().getExpression();
        // on crée une nouvelle junction
        Junction junctionGlobal = new Conjunction();
        // si la expression initial n'est pas vide, on l'ajoute
        if (expressionInitiale != null) {
            junctionGlobal.add(expressionInitiale);
        }
        addFilterDomaineTechnique(junctionGlobal);
        // on mets la nouvelle junction dans la query
        context.getQuery().setExpression(junctionGlobal);
            // FIN Filtrer l'export suivant les habilitations de l'utilisateur

        try {
            // Récupération des données de la recherche
            collection = this.findBy(context);
        } finally {
            context.setMaxObjects(previousMaxValue);
            // on doit laisser la query propre
            context.getQuery().setExpression(expressionInitiale);
        }

        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
                FormuleRisque.class.getSimpleName(), collection);

        /* Modification de l'entete */
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), CAMPAGNE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), DOMAINE_TECHNIQUE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), RISQUE_THEORIQUE_LB_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), ZONE_LB_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), VOLUME_LB_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), DIFFUSION_LB_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), DESTINATION_LB_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(FormuleRisque.class.getSimpleName(), NOTE_INSPECTION_LB_PARAM_NAME));

        reportDataSource.setProperties(propertyDescriptors);

        return reportDataSource;
    }

    /**
     * Permet d'afficher uniquement les formule risques appartenant au domaine
     * technique habilité pour l'utilisateur
     *
     * @param expression
     * @throws Exception
     */
    private void addFilterDomaineTechnique(Junction junction) throws Exception {

        List<VDomaineTechnique> listDts = habilitationsService.getAttCompDomainesTechniquesRoleConnecteFiltre();
        if (!listDts.isEmpty()) {
            In inOperator =  (In) Operators.IN;
            inOperator.setReverse(true);
            Expression expDT = new SimpleExpression(new Criterion("domaineTechnique"),
                    Operators.IN, new Parameter(listDts));
            junction.add(expDT);
        }

    }
}
