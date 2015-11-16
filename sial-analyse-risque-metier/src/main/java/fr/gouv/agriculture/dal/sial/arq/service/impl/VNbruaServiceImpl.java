package fr.gouv.agriculture.dal.sial.arq.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.gouv.agriculture.dal.sial.arq.business.ClasseRisque;
import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.business.bean.VNbruaAgg;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.VNbruaListComparator;
import fr.gouv.agriculture.dal.sial.arq.service.VNbruaService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.persistence.PersistenceService;

/**
 * Service pour les VNbrua
 * @author pbarreau
 *
 */
public class VNbruaServiceImpl implements VNbruaService {

	/** DAO par defaut*/
	@Inject
	private BusinessDAO businessDao;
	
	/** Nom du critere campagne*/
	private final static String CRIT_CAMPAGNE = "campagne";
	/** Nom du critere domaine technique*/
	private final static String CRIT_DT = "domaineTechnique";
	/** Nom du critere code insee commune*/
	private final static String CRIT_INSEE = "codeInseeCommune";
	/** requete nommée de recherche des VNbrua aggrégés*/
    public static final String REQ_FIND_AGG_ET_COM = "findByCampDtCommuneAgg";
    /** requete nommée de recherche des VNbrua aggrégés*/
    public static final String REQ_FIND_AGG = "findByCampDtAgg";
	

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.dal.sial.arq.service.VNbruaService#findCommuneInseeForStructures(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findCommuneInseeForStructures(List<String> listeStructsRfa) throws PersistenceException {

		Map<String, Object> parametres = new HashMap<String, Object>();
		parametres.put("codesStructure", listeStructsRfa);
		PersistenceService persServ = businessDao.getPersistenceService();
		Collection<String> results = persServ.findUsingNamedQuery("findCommuneInseeForStructuresByRegion", parametres,
				Boolean.FALSE);
		results.addAll(persServ.findUsingNamedQuery("findCommuneInseeForStructuresByDepartement", parametres,
				Boolean.FALSE));

		return (List<String>) results;
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.dal.sial.arq.service.VNbruaService#aggCountVNbrua(java.util.Collection)
	 */
	@Override
	public Collection<VNbrua> aggCountVNbrua(Collection<VNbrua> vNbruas) {

		Map<String,VNbrua>  vNbruaMap = new HashMap<String,VNbrua>();
		
		for(VNbrua vNbrua :vNbruas){
			
			
			// Pour être sûr de posseder la donnée la plus fraiche
			// (cas ou un batch vient de modifier la donnée)
			try {
				businessDao.refresh(vNbrua);
			} catch (Exception exep) {
				Logger.getLogger(VNbruaService.class.getName()).log(Level.SEVERE, null, exep);
			}
			
			VCampagne campagne = vNbrua.getCampagne();
			VDomaineTechnique domaine = vNbrua.getDomaineTechnique();
			ClasseRisque classe = vNbrua.getClasseRisque();
			Integer nbrUa = vNbrua.getNbrUa();
			
			StringBuilder builder = new StringBuilder(campagne.getCampRfa());
			builder.append(domaine.getDtRfa());
			builder.append(classe.getClasseRfa());
			
			//incrémentation
			if(vNbruaMap.containsKey(builder.toString())){
				
				Integer nbrUatemp = vNbruaMap.get(builder.toString()).getNbrUa();
				Integer sum = nbrUatemp.intValue() + nbrUa.intValue();
				vNbruaMap.get(builder.toString()).setNbrUa(sum);
				
				
			}else{ //ajout
				VNbrua vNbruaNew = new VNbrua();
				vNbruaNew.setCampagne(campagne);
				vNbruaNew.setDomaineTechnique(domaine);
				vNbruaNew.setClasseRisque(classe);
				vNbruaNew.setNbrUa(nbrUa);
				
				vNbruaMap.put(builder.toString(), vNbruaNew);
			}
		}
		List<VNbrua> returnList = new ArrayList<VNbrua>(vNbruaMap.values());
		Collections.sort(returnList, new VNbruaListComparator());
		
		return returnList;
	}

	@Override
	public Collection<VNbruaAgg> findByCampDtCommuneAgg(VCampagne campagne,VDomaineTechnique domaineTechnique,
			List<String> codeInseeCommune) throws PersistenceException {
		
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put(CRIT_CAMPAGNE, campagne);
		params.put(CRIT_DT,domaineTechnique);		
		
		Collection<Object[]> result  = null;
		
		Integer oldMaxRes = businessDao.getPersistenceService().getMaxResults();
		businessDao.getPersistenceService().setMaxResults(Integer.MAX_VALUE);
		
		if(CommonHelper.isEmpty(codeInseeCommune)){
			result = businessDao.getPersistenceService().findUsingNamedQuery(
					REQ_FIND_AGG, params, false);
		} else {
			params.put(CRIT_INSEE,codeInseeCommune);
			
			result = businessDao.getPersistenceService().findUsingNamedQuery(
					REQ_FIND_AGG_ET_COM, params, false);
		}

		 businessDao.getPersistenceService().setMaxResults(oldMaxRes);
		
		List<VNbruaAgg> vNbruaAggs = new ArrayList<VNbruaAgg>();
		for(Object[] item:result){
			VNbruaAgg vNbruaAgg = new VNbruaAgg();
			vNbruaAgg.setCampRfa((String)item[0]);
			vNbruaAgg.setDtRfa((String)item[1]);
			vNbruaAgg.setDtLb((String)item[2]);
			vNbruaAgg.setClasseRfa((String)item[3]);
			vNbruaAgg.setNbrUa(((Long)item[4]).intValue());
			
			vNbruaAggs.add(vNbruaAgg);
		}
		
		
		return vNbruaAggs;
	}

	@Override
	public Collection<VNbruaAgg> findByCampDtAgg(VCampagne campagne,VDomaineTechnique domaineTechnique)
			throws PersistenceException {
		return findByCampDtCommuneAgg(campagne,domaineTechnique,null);
	}

}
