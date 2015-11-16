package fr.gouv.agriculture.dal.sial.arq.service.impl;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.dao.CampagneArqDAO;
import fr.gouv.agriculture.dal.sial.arq.service.CampagneService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.o2.kernel.Inject;

/**
 * Service pour l'entité Campagne
 * @author pbarreau
 *
 */
public class CampagneServiceImpl implements CampagneService {

	/** DAO personalisée pour la nomenclature Campagne*/
	@Inject( value="fr.gouv.agriculture.dal.sial.arq.dao.CampagneArqDAO" )
	private CampagneArqDAO campagneArqDAO;
	
	@Override
	public VCampagne getCurrentCampagne() throws Exception {
		
		VCampagne resultat = null;
		
		List<VCampagne> list = campagneArqDAO.findCurrents();
		
		if(list!= null && !list.isEmpty()){
			resultat = list.get(0);
		}
		
		return resultat;
	}

}
