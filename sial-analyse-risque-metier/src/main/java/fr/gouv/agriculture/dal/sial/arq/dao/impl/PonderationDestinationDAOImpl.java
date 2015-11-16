package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationDestinationDAO;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.report.PropertyDescriptor;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.datasource.DefaultPropertyDescriptor;
import fr.gouv.agriculture.orion.report.datasource.ReportCollectionBeanDataSource;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implémentation d'accès au données pour la table ponderation_destination.
 *
 * @author pegaltier
 */
public class PonderationDestinationDAOImpl extends DefaultBusinessDAO implements PonderationDestinationDAO {
    
	/** libellé de la destination */
    private static final String DESTINATION_PARAM_NAME = "destRfa.destLb";
    /** libellé du type d'activité */
    private static final String TYPE_ACTIVITE_PARAM_NAME = "taRfa.taLb";
    /** Poids de la ponderation */
    private static final String POID_PARAM_NAME = "pdestPoidsNb2";
    
    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationDestination ponderationDestination = (PonderationDestination) super.create(businessContext, PonderationDestination.class);
        // le super.create à affecté la FormuleRisque trouvée dans la BusinessStack
        // au PonderationDestination qui vient d'être créé
        FormuleRisque formuleRisque = ponderationDestination.getFormuleRisque();
        if (formuleRisque != null) {
            formuleRisque.getPonderationDestinations().add(ponderationDestination);
        }
        return ponderationDestination;
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
        
        try {
            // Suppression de la limite
            context.setMaxObjects(Integer.MAX_VALUE);
            // Suppression de l'ordre pré établi
            context.getQuery().getOrders().clear();
            // Ajout des order by
            context.getQuery().addOrder(new Order(TYPE_ACTIVITE_PARAM_NAME, true, true));
            context.getQuery().addOrder(new Order(DESTINATION_PARAM_NAME, true, true));
            
            // Récupération des données de la recherche
            collection = this.findBy(context);
        } finally {
            context.setMaxObjects(previousMaxValue);
        }
        
        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
                PonderationDestination.class.getSimpleName(), collection);
        
        /* Modification de l'entete */
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationDestination.class.getSimpleName(), TYPE_ACTIVITE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationDestination.class.getSimpleName(), DESTINATION_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationDestination.class.getSimpleName(), POID_PARAM_NAME));
        
        reportDataSource.setProperties(propertyDescriptors);
        
        return reportDataSource;
    }
}