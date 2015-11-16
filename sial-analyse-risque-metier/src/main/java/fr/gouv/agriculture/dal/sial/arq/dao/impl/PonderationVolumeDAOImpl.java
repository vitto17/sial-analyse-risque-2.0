package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolumeExport;
import fr.gouv.agriculture.dal.sial.arq.dao.PonderationVolumeDAO;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.query.Order;
import fr.gouv.agriculture.orion.report.PropertyDescriptor;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.datasource.DefaultPropertyDescriptor;
import fr.gouv.agriculture.orion.report.datasource.ReportCollectionBeanDataSource;

/**
 * Implémentation d'accès au données pour la table ponderation_volume.
 *
 * @author pegaltier
 */
public class PonderationVolumeDAOImpl extends DefaultBusinessDAO implements PonderationVolumeDAO {
    
	private static final String TYPE_ACTIVITE_PARAM_NAME = "taRfa.taLb";
	private static final String UNITE_MESURE_PRODUCTION_PARAM_NAME = "uprodRfa.uprodLb";
	private static final String POID_1_PARAM_NAME = "ponderationVolume.pvolS1Nb2";
	private static final String POID_2_PARAM_NAME = "ponderationVolume.pvolS2Nb2";
	private static final String POID_3_PARAM_NAME = "ponderationVolume.pvolS3Nb2";
	private static final String POID_4_PARAM_NAME = "ponderationVolume.pvolS4Nb2";
	private static final String POID_1_LB = "pvolS1Lb";
	private static final String POID_2_LB = "pvolS2Lb";
	private static final String POID_3_LB = "pvolS3Lb";
	private static final String POID_4_LB = "pvolS4Lb";
    
    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        PonderationVolume ponderationVolume = (PonderationVolume) super.create(businessContext, PonderationVolume.class);
        // le super.create à affecté la FormuleRisque trouvée dans la BusinessStack
        // au PonderationVolume qui vient d'être créé
        FormuleRisque formuleRisque = ponderationVolume.getFormuleRisque();
        if (formuleRisque != null) {
            formuleRisque.getPonderationVolumes().add(ponderationVolume);
        }
        return ponderationVolume;
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
	@SuppressWarnings("unchecked")
	@Override
    public ReportDataSource getReportDataSource(String reportName, BusinessSearchContext context)
            throws PersistenceException, Exception {
        
        // Colonnes exportées.
        List<PropertyDescriptor> propertyDescriptors = new LinkedList<PropertyDescriptor>();
        
		Collection<PonderationVolume> collection = null;
        
        Integer previousMaxValue = context.getMaxObjects();
        
        try {
            // Suppression de la limite
            context.setMaxObjects(Integer.MAX_VALUE);
            // Suppression de l'ordre pré établi
            context.getQuery().getOrders().clear();
            // Ajout des order by
            context.getQuery().addOrder(new Order(TYPE_ACTIVITE_PARAM_NAME, true, true));
            context.getQuery().addOrder(new Order(UNITE_MESURE_PRODUCTION_PARAM_NAME, true, true));
            
            // Récupération des données de la recherche
            collection = this.findBy(context);
        } finally {
            context.setMaxObjects(previousMaxValue);
        }
        
		// On transforme les données récupérées pour correspondre à ce qui est
		// dans le tableau
		Collection<PonderationVolumeExport> collectionExport = new ArrayList<PonderationVolumeExport>();
		for (PonderationVolume pondVol : collection) {
			PonderationVolumeExport pondVolExport = new PonderationVolumeExport(pondVol);
			collectionExport.add(pondVolExport);
		}

        ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource(
				PonderationVolumeExport.class.getSimpleName(), collectionExport);
        
        /* Modification de l'entete */
		propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(),
				"ponderationVolume." + TYPE_ACTIVITE_PARAM_NAME));
		propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(),
				"ponderationVolume." + UNITE_MESURE_PRODUCTION_PARAM_NAME));
		propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(),
				POID_1_PARAM_NAME));
		propertyDescriptors
				.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(), POID_1_LB));
		propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(),
				POID_2_PARAM_NAME));
		propertyDescriptors
				.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(), POID_2_LB));
		propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(),
				POID_3_PARAM_NAME));
		propertyDescriptors
				.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(), POID_3_LB));
		propertyDescriptors.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(),
				POID_4_PARAM_NAME));
		propertyDescriptors
				.add(new DefaultPropertyDescriptor(PonderationVolumeExport.class.getSimpleName(), POID_4_LB));
        
        reportDataSource.setProperties(propertyDescriptors);
        
        return reportDataSource;
    }
}
