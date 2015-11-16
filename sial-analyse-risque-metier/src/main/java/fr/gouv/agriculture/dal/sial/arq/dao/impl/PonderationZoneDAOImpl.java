package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationZoneDAO;
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
 * Implémentation d'accès au données pour la table ponderation_zone.
 *
 * @author pegaltier
 */
public class PonderationZoneDAOImpl extends DefaultBusinessDAO implements PonderationZoneDAO {
    
	/** Libellé du type zone */
	private static final String TYPE_ZONE_PARAM_NAME = "zone.typeZone.tzLb";
	/** Libellé de la zone */
	private static final String ZONE_PARAM_NAME = "zone.zlb";
	/** Poids de la ponderation */
	private static final String POID_PARAM_NAME = "pzonePoidsNb2";
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
            context.getQuery().addOrder(new Order(TYPE_ZONE_PARAM_NAME, true, true));
            context.getQuery().addOrder(new Order(ZONE_PARAM_NAME, true, true));
            
            // Récupération des données de la recherche
            collection = this.findBy(context);
        } finally {
            context.setMaxObjects(previousMaxValue);
        }
        
        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
                PonderationZone.class.getSimpleName(), collection);
        
        /* Modification de l'entete */
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationZone.class.getSimpleName(), TYPE_ZONE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationZone.class.getSimpleName(), ZONE_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationZone.class.getSimpleName(), POID_PARAM_NAME));
        
        reportDataSource.setProperties(propertyDescriptors);
        
        return reportDataSource;
    }
}
