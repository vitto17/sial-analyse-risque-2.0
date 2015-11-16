package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import java.util.Comparator;

import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;

/**
 * Comparateur pour trier les VNbrua lors de l'affichage en liste
 * @author pbarreau
 *
 */
public class VNbruaListComparator implements Comparator<VNbrua>{
	
	@Override
	public int compare(VNbrua vNbrua1, VNbrua vNbrua2) {
		
		int resultat = 0;
		
		String campagne1 = vNbrua1.getCampagne().getCampRfa();
		String domaine1 = vNbrua1.getDomaineTechnique().getDtComplete();
		String classe1 = vNbrua1.getClasseRisque().getClasseRfa();
		
		String campagne2 = vNbrua2.getCampagne().getCampRfa();
		String domaine2 = vNbrua2.getDomaineTechnique().getDtComplete();
		String classe2 = vNbrua2.getClasseRisque().getClasseRfa();
		
		resultat = campagne1.compareTo(campagne2);
		if( resultat == 0 ){
			
			resultat = domaine1.compareTo(domaine2);
			
			if( resultat == 0 ){
				resultat = classe1.compareTo(classe2);
			}
		}
		
		return resultat;
	}

}
