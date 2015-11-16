package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.VExportNoteRisque;
import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.dao.CalculEtResultatsDAO;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.arq.service.VNbruaService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.habilitations.businessshared.VStructureDomaineTechnique;
import fr.gouv.agriculture.habilitations.businessshared.VStructureEtendu;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.operator.In;
import fr.gouv.agriculture.orion.report.PropertyDescriptor;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.datasource.DefaultPropertyDescriptor;
import fr.gouv.agriculture.orion.report.datasource.ReportCollectionBeanDataSource;

/**
 * DAO pour les calcul et Résultat
 *
 * @author sopra
 *
 */
public class CalculEtResultatsDAOImpl extends DefaultBusinessDAO implements CalculEtResultatsDAO {

    /**
     * Service pour les VNbrua
     */
    @Inject
    private VNbruaService nbrUaService;
    /**
     * Service pour les habilitations
     */
    @Inject
    private HabilitationsService habilitationsService;

    private static final String DOMAINE_TECHNIQUE_CRITERE_NAME = "domaineTechnique";
    private static final String CAMPAGNE_CRITERE_NAME = "campagne";

    private static final String DOMAINE_TECHNIQUE_PARAM_NAME = "dtRfaExport";
    private static final String CAMPAGNE_PARAM_NAME = "campRfaExport";
    private static final String ETABLISSEMENT_BDNU_PARAM_NAME = "etbRfaBdnuExport";
    private static final String ENSEIGNE_USUELLE_ETABLISSEMENT_PARAM_NAME = "etaEnseigneUsuelLbExport";
    private static final String IDENTIFIANTS_PARAM_NAME = "identifiantsEtabExport";
    private static final String IDENTIFIANTS_EDE = "identifiantEdeExport";
    private static final String IDENTIFIANT_PRINCIPAL_PARAM_NAME = "idenValeurExport";
    private static final String COMMUNE_UA_PARAM_NAME = "nomofficielExport";
    private static final String CODE_INSEE_COMMUNE_UA_PARAM_NAME = "inseeExport";
    private static final String LAT_UA_PARAM_NAME = "geoLocalisationExport.x";
    private static final String LONG_UA_PARAM_NAME = "geoLocalisationExport.y";
    private static final String TYPE_ACTIVITE_PARAM_NAME = "taLbExport";
    private static final String NOTE_RISQUE_PARAM_NAME = "noteValNbExport";
    private static final String CLASSE_RISQUE_PARAM_NAME = "classeRfaExport";
    private static final String POIDS_RISQUE_THEORIQUE_PARAM_NAME = "notePRisquNb2Export";
    private static final String POIDS_ZONE_PARAM_NAME = "notePZoneNb2Export";
    private static final String POIDS_VOLUME_PARAM_NAME = "notePVolNb2Export";
    private static final String POIDS_DIFFUSION_PARAM_NAME = "notePDiffNb2Export";
    private static final String POIDS_DESTINATION_PARAM_NAME = "notePDestNb2Export";
    private static final String POIDS_NOTE_INSPECTION_PARAM_NAME = "notePNiNb2Export";
    private static final String DATE_CALCUL_PARAM_NAME = "noteDateCalculDsExportFormated";
    private static final String NOTE_RISQUE_ANTERIEURE_PARAM_NAME = "notePrecValNbExport";

    /**
     * Récupère les données de l'export, le nom du fichier et des colonnes
     *
     * @param reportName le nom du rapport
     * @param context le contexte
     * @return le report Data Source contenant les données à exporter
     * @throws PersistenceException en cas d'erreur
     * @throws Exception en cas d'erreur
     */
    @Override
    public ReportDataSource getReportDataSource(String reportName, BusinessSearchContext context)
            throws PersistenceException, Exception {

        // Colonnes exportées.
        List<PropertyDescriptor> propertyDescriptors = new LinkedList<PropertyDescriptor>();

        Collection collection = null;

        Integer previousMaxValue = context.getMaxObjects();

        Query query = context.getQuery();
        Expression previousExpresion = query.getExpression();
        List<Order> previousOrders = query.getOrders();
        VCampagne previousCampagne = null;
        VDomaineTechnique previousDomaineTechnique = null;
        Junction newExpression = new Conjunction();

        try {
            // Suppression de la limite
            context.setMaxObjects(Integer.MAX_VALUE);

            query.setClazz(VExportNoteRisque.class);

            // translate pour l'archivage du groupe
            previousCampagne = translateCampagne(query);
            previousDomaineTechnique = translateDomaineTechnique(query);

            // Ajout de la condition sur les structures
            newExpression.add(previousExpresion);
            List<String> listeStructuresRfa = new ArrayList<String>();

            // Attribut complementaire : Cas Structure seule
            List<VStructureEtendu> listeStructures = habilitationsService.getAttCompStructuresRoleConnecte();
            for (VStructureEtendu struct : listeStructures) {
                listeStructuresRfa.add(struct.getStructCodeRfa());
            }

            // Attribut complementaire : Cas Structure + Dt
            List<VStructureDomaineTechnique> listeStructuresDt = habilitationsService
                    .getAttCompStructuresDomainesTechniquesRoleConnecte();
            for (VStructureDomaineTechnique structDt : listeStructuresDt) {
                if (structDt.getStructure() != null && structDt.getStructure().getStructCodeRfa() != null) {
                    listeStructuresRfa.add(structDt.getStructure().getStructCodeRfa());
                }
            }

            if (!listeStructuresRfa.isEmpty()) {
                List<String> communesInsee = nbrUaService.findCommuneInseeForStructures(listeStructuresRfa);
                if (!CommonHelper.isEmpty(communesInsee)) {
                    In inOperator = (In) Operators.IN;
                    inOperator.setReverse(true);
                    Expression communeInseeExpr = new SimpleExpression(new Criterion(CODE_INSEE_COMMUNE_UA_PARAM_NAME),
                            inOperator, new Parameter(communesInsee));
                    newExpression.add(communeInseeExpr);
                }
            }

            context.getQuery().setExpression(newExpression);

            // Suppression de l'ordre pré établi
            context.getQuery().getOrders().clear();
            // Ajout des order by
            context.getQuery().addOrder(new Order(CAMPAGNE_PARAM_NAME, true, true));
            context.getQuery().addOrder(new Order(DOMAINE_TECHNIQUE_PARAM_NAME, true, true));
            context.getQuery().addOrder(new Order(CLASSE_RISQUE_PARAM_NAME, true, true));

            // Récupération des données de la recherche
            Collection<VExportNoteRisque> vExportNoteRisqueList = this.findBy(context);

            // On ne garde que les données dont IDEN_PRINCIPAL_BO = true ou vide
            if (vExportNoteRisqueList != null) {
                collection = new ArrayList<VExportNoteRisque>();
                for (VExportNoteRisque data : vExportNoteRisqueList) {
                    if (data.getIdenPrincipalBoExport() == null || data.getIdenPrincipalBoExport()) {
                        collection.add(data);
                    }
                }
            }
        } finally {
            context.setMaxObjects(previousMaxValue);
            context.getQuery().setClazz(VNbrua.class);
            context.getQuery().setExpression(previousExpresion);
            restaureCampagne(context.getQuery(), previousCampagne);
            restaureDomaineTechnique(context.getQuery(), previousDomaineTechnique);
            context.getQuery().getOrders().clear();
            context.getQuery().setOrders(previousOrders);
        }

        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
                VExportNoteRisque.class.getSimpleName(), collection);

        /* Modification de l'entete */
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                DOMAINE_TECHNIQUE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                CAMPAGNE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                ETABLISSEMENT_BDNU_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                ENSEIGNE_USUELLE_ETABLISSEMENT_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                IDENTIFIANTS_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                IDENTIFIANTS_EDE));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                IDENTIFIANT_PRINCIPAL_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                COMMUNE_UA_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                CODE_INSEE_COMMUNE_UA_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                LAT_UA_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                LONG_UA_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                TYPE_ACTIVITE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                NOTE_RISQUE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                CLASSE_RISQUE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                POIDS_RISQUE_THEORIQUE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                POIDS_ZONE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                POIDS_VOLUME_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                POIDS_DIFFUSION_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                POIDS_DESTINATION_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                POIDS_NOTE_INSPECTION_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                DATE_CALCUL_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(VExportNoteRisque.class.getSimpleName(),
                NOTE_RISQUE_ANTERIEURE_PARAM_NAME));

        reportDataSource.setProperties(propertyDescriptors);

        return reportDataSource;
    }

    /**
     * Méthode pour traiter le critère 'campagne'
     *
     * @param query requete à modifier
     * @return Campagne d'origine
     */
    private VCampagne translateCampagne(Query query) {
        List<Expression> expressionCampagne = query.findExpressionByName(CAMPAGNE_CRITERE_NAME);

        VCampagne value = null;

        if (!CommonHelper.isEmpty(expressionCampagne)) {
            SimpleExpression simpleExpressionCampagne = (SimpleExpression) expressionCampagne.iterator().next();

            if (simpleExpressionCampagne.getValue() == null) {
                query.removeExpression(simpleExpressionCampagne);
            } else {
                Parameter parameter = (Parameter) simpleExpressionCampagne.getValue();
                value = (VCampagne) parameter.getValue();

                simpleExpressionCampagne.setOperator(Operators.EQUALS);
                simpleExpressionCampagne.setCriterion(new Criterion(CAMPAGNE_PARAM_NAME, null, null));
                simpleExpressionCampagne.setValue(new Parameter(value.getCampRfa()));
            }
        }
        return value;
    } // fin translateCampagne

    /**
     * Méthode pour traiter le critère 'domaineTechnique'
     *
     * @param query requete à modifier
     * @return VDomaineTechnique d'origine
     */
    private VDomaineTechnique translateDomaineTechnique(Query query) {
        List<Expression> expressionDT = query.findExpressionByName(DOMAINE_TECHNIQUE_CRITERE_NAME);

        VDomaineTechnique value = null;

        if (!CommonHelper.isEmpty(expressionDT)) {
            SimpleExpression simpleExpressionDT = (SimpleExpression) expressionDT.iterator().next();

            if (simpleExpressionDT.getValue() == null) {
                query.removeExpression(simpleExpressionDT);
            } else {
                Parameter parameter = (Parameter) simpleExpressionDT.getValue();
                value = (VDomaineTechnique) parameter.getValue();

                simpleExpressionDT.setOperator(Operators.EQUALS);
                simpleExpressionDT.setCriterion(new Criterion(DOMAINE_TECHNIQUE_PARAM_NAME, null, null));
                simpleExpressionDT.setValue(new Parameter(value.getDtRfa()));

            }
        }
        return value;
    } // fin translateDomaineTechnique

    /**
     * Méthode pour restaurer le critère 'campagne'
     *
     * @param query requete à modifier
     * @param campagne Campagne à restaurer
     */
    private void restaureCampagne(Query query, VCampagne campagne) {
        List<Expression> expressionCampagne = query.findExpressionByName(CAMPAGNE_PARAM_NAME);
        if (!CommonHelper.isEmpty(expressionCampagne)) {
            SimpleExpression simpleExpressionCampagne = (SimpleExpression) expressionCampagne.iterator().next();

            if (simpleExpressionCampagne.getValue() == null) {
                query.removeExpression(simpleExpressionCampagne);
            } else {
                simpleExpressionCampagne.setOperator(Operators.EQUALS);
                simpleExpressionCampagne.setCriterion(new Criterion(CAMPAGNE_CRITERE_NAME, null, null));
                simpleExpressionCampagne.setValue(new Parameter(campagne));
            }
        }

    }

    /**
     * Méthode pour restaurer le critère 'domaineTechnique'
     *
     * @param query requete à modifier
     * @param domaineTechnique Domaine Technique à restaurer
     */
    private void restaureDomaineTechnique(Query query, VDomaineTechnique domaineTechnique) {
        List<Expression> expressionDT = query.findExpressionByName(DOMAINE_TECHNIQUE_PARAM_NAME);
        if (!CommonHelper.isEmpty(expressionDT)) {
            SimpleExpression simpleExpressionDT = (SimpleExpression) expressionDT.iterator().next();

            if (simpleExpressionDT.getValue() == null) {
                query.removeExpression(simpleExpressionDT);
            } else {
                simpleExpressionDT.setOperator(Operators.EQUALS);
                simpleExpressionDT.setCriterion(new Criterion(DOMAINE_TECHNIQUE_CRITERE_NAME, null, null));
                simpleExpressionDT.setValue(new Parameter(domaineTechnique));

            }
        }

    }

}
