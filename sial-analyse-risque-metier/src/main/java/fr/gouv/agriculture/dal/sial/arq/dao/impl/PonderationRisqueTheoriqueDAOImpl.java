/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.PonderationRisqueTheoriqueComparator;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationRisqueTheoriqueDAO;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.report.PropertyDescriptor;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.datasource.DefaultPropertyDescriptor;
import fr.gouv.agriculture.orion.report.datasource.ReportCollectionBeanDataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implémentation d'accès au données pour la table ponderation_risque_theorique.
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueDAOImpl extends DefaultBusinessDAO implements PonderationRisqueTheoriqueDAO {

    /**
     * Libellé du type d'activité
     */
    private static final String TYPE_ACTIVITE_PARAM_NAME = "typeActivite.taLb";
    /**
     * Poids de la ponderation
     */
    private static final String POID_PARAM_NAME = "prisqtheoPoidsNb2";
    /**
     * Libellé Approbation
     */
    private static final String APPROBATIONS_PARAM_NAME = "ponderationRisqueTheoriqueApprobationsStr";
    /**
     * Libellé Procédé
     */
    private static final String PROCEDE_PARAM_NAME = "ponderationRisqueTheoriqueProcedesStr";
    /**
     * Libellé Produit
     */
    private static final String PRODUIT_PARAM_NAME = "ponderationRisqueTheoriqueProduitsStr";

    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationRisqueTheorique ponderationRisqueTheo = (PonderationRisqueTheorique) super.create(businessContext, PonderationRisqueTheorique.class);
        // le super.create à affecté la FormuleRisque trouvée dans la BusinessStack
        // au PonderationRisqueTheorique qui vient d'être créé
        FormuleRisque formuleRisque = ponderationRisqueTheo.getFormuleRisque();
        if (formuleRisque != null) {
            formuleRisque.getPonderationRisqueTheoriques().add(ponderationRisqueTheo);
        }
        return ponderationRisqueTheo;
    }

    /**
     * Permet de créer une PonderationRisqueTheorique et l'associer a une
     * formule risque passé en parametre. Cette methode est utilisé pour le
     * saveAndNew du formulaire de ponderation risque theorique
     *
     * @param businessContext
     * @param businessClass
     * @param formuleRisque
     * @return
     * @throws PersistenceException
     */
    public Object createWithFormuleRisque(BusinessContext businessContext, Class businessClass, FormuleRisque formuleRisque) throws PersistenceException {
        PonderationRisqueTheorique ponderationRisqueTheo = (PonderationRisqueTheorique) super.create(businessContext, PonderationRisqueTheorique.class);
        ponderationRisqueTheo.setFormuleRisque(formuleRisque);
        if (formuleRisque != null) {
            formuleRisque.getPonderationRisqueTheoriques().add(ponderationRisqueTheo);
        }
        return ponderationRisqueTheo;
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

        Collection<PonderationRisqueTheorique> collection = null;
        List<PonderationRisqueTheorique> list = null; 
        
        Integer previousMaxValue = context.getMaxObjects();

        try {
            // Suppression de la limite
            context.setMaxObjects(Integer.MAX_VALUE);
            // Suppression de l'ordre pré établi
            context.getQuery().getOrders().clear();
            // Ajout des order by
            context.getQuery().addOrder(new Order(TYPE_ACTIVITE_PARAM_NAME, true, true));

            // Récupération des données de la recherche
            collection = this.findBy(context);
            
            //Tri
            list = new ArrayList<PonderationRisqueTheorique>(collection);
            Collections.sort(list, new PonderationRisqueTheoriqueComparator());

        } finally {
            context.setMaxObjects(previousMaxValue);
        }

        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
                PonderationRisqueTheorique.class.getSimpleName(), list);

        /* Modification de l'entete */
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationRisqueTheorique.class.getSimpleName(), TYPE_ACTIVITE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationRisqueTheorique.class.getSimpleName(), APPROBATIONS_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationRisqueTheorique.class.getSimpleName(), PROCEDE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationRisqueTheorique.class.getSimpleName(), PRODUIT_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationRisqueTheorique.class.getSimpleName(), POID_PARAM_NAME));

        reportDataSource.setProperties(propertyDescriptors);

        return reportDataSource;
    }
}
