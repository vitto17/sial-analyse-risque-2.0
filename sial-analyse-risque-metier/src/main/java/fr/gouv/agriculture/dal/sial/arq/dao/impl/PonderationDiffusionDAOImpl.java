package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationDiffusionDAO;
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
 * Implémentation d'accès au données pour la table ponderation_diffusion.
 *
 * @author pegaltier
 */
public class PonderationDiffusionDAOImpl extends DefaultBusinessDAO implements PonderationDiffusionDAO {
    
	/** Libellé de la diffusion */
    private final static String DIFFUSION_PARAM_NAME = "difRfa.difLb";
    /** Poids de la diffusion */
    private final static String POID_PARAM_NAME = "pdiffPoidsNb2";
    
    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationDiffusion ponderationDiffusion = (PonderationDiffusion) super.create(businessContext, PonderationDiffusion.class);
        // le super.create à affecté la FormuleRisque trouvée dans la BusinessStack
        // au PonderationDiffusion qui vient d'être créé
        FormuleRisque formuleRisque = ponderationDiffusion.getFormuleRisque();
        if (formuleRisque != null) {
            formuleRisque.getPonderationDiffusions().add(ponderationDiffusion);
        }
        return ponderationDiffusion;
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
            context.getQuery().addOrder(new Order(DIFFUSION_PARAM_NAME, true, true));
            
            // Récupération des données de la recherche
            collection = this.findBy(context);
        } finally {
            context.setMaxObjects(previousMaxValue);
        }
        
        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
                PonderationDiffusion.class.getSimpleName(), collection);
        
        /* Modification de l'entete */
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationDiffusion.class.getSimpleName(), DIFFUSION_PARAM_NAME));
        propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationDiffusion.class.getSimpleName(), POID_PARAM_NAME));
        
        reportDataSource.setProperties(propertyDescriptors);
        
        return reportDataSource;
    }
}
