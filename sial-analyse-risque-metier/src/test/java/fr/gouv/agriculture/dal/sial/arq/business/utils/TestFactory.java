package fr.gouv.agriculture.dal.sial.arq.business.utils;

import java.util.ArrayList;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;

public class TestFactory {

	
	/**
     * Création d'une formule de risque
     * @return objet à tester valide
     */
    public static FormuleRisque createFormuleRisque(){
    	FormuleRisque formuleRisque = new FormuleRisque();
    	formuleRisque.setPonderationDestinations(new ArrayList<PonderationDestination>());
    	formuleRisque.setPonderationDiffusions(new ArrayList<PonderationDiffusion>());
    	formuleRisque.setPonderationRisqueTheoriques(new ArrayList<PonderationRisqueTheorique>());
    	formuleRisque.setPonderationVolumes(new ArrayList<PonderationVolume>());
    	formuleRisque.setPonderationZones(new ArrayList<PonderationZone>());
    	
    	return formuleRisque;
    }
	
}
