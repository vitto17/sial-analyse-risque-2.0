package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;

/**
 * Comparateur de deux PonderationRisqueTheorique, sans avoir besoin de charger les objets transient
 * @author sopra
 *
 */
public class PonderationRisqueTheoriqueWithoutTransientComparator implements Comparator<PonderationRisqueTheorique>{

	/** Valeur de retour pour different */
	private int DIFFERENT = 1;
	/** Valeur de retour pour identique */
	private int EQUAL = 0;
	
	/**
     * Compare les objets.
     *
     * @param pond1 ponderation 1 à comparer
     * @param pond2 ponderation 2 à comparer
     * @return 1 si different, 0 si identique.
     */
    @Override
	public int compare(PonderationRisqueTheorique pond1,
			PonderationRisqueTheorique pond2) {
		int resultat = EQUAL;
    	
		if(pond1!=pond2){
			VTypeActivite tAct1 = pond1.getTypeActivite();
			VTypeActivite tAct2 = pond2.getTypeActivite();
			String stAct1 = "";
			String stAct2 = "";
			
			if(tAct1 != null) stAct1 = (String)tAct1.getIdentifier();
			if(tAct2 != null) stAct2 = (String)tAct2.getIdentifier();
			if(stAct1.equals(stAct2)
					&& compareApprobations(pond1,pond2) == EQUAL
					&& compareProcedes(pond1,pond2) == EQUAL
					&& compareProduits(pond1,pond2) == EQUAL ){
				
				resultat=EQUAL;
			} else {
				resultat=DIFFERENT;
			}
		}
    	

		
		
		return resultat;
	}

    
    /**
     * Compare les approbations
     * @param pond1 ponderation 1 à comparer
     * @param pond2 ponderation 2 à comparer
     * @return 0 si identique.
     */
    private int compareApprobations(PonderationRisqueTheorique pond1,
			PonderationRisqueTheorique pond2){
    	   	
    	return pond1.getPonderationRisqueTheoriqueApprobationsStr().compareTo(pond2.getPonderationRisqueTheoriqueApprobationsStr()); 	  	
    }
    
    /**
     * Compare les procedes
     * @param pond1 ponderation 1 à comparer
     * @param pond2 ponderation 2 à comparer
     * @return 0 si identique.
     */
    private int compareProcedes(PonderationRisqueTheorique pond1,
			PonderationRisqueTheorique pond2){
    	return pond1.getPonderationRisqueTheoriqueProcedesStr().compareTo(pond2.getPonderationRisqueTheoriqueProcedesStr());
    }
    
    /**
     * Compare les produits
     * @param pond1 ponderation 1 à comparer
     * @param pond2 ponderation 2 à comparer
     * @return 1 si different, 0 si identique.
     */
    private int compareProduits(PonderationRisqueTheorique pond1,
			PonderationRisqueTheorique pond2){
    	
    	return createTagProduit(pond1).compareTo(createTagProduit(pond2));
    	
    	
    }
    
    /**
     * Genere une empreinte qui servira à comparer deux listes de produits
     * @param ponderation dont on veut generer l'empreinte de la liste de produits
     * @return empreinte de comparaison des produits presents
     */
    private String createTagProduit(PonderationRisqueTheorique ponderation){
    	StringBuilder empreinte = new StringBuilder("");
    	List<String> listeCodeProduit = new ArrayList<String>();
    	
    	List<PonderationRisqueTheoriqueProduit> prtProduits = ponderation.getPonderationRisqueTheoriqueProduits();
    	for(PonderationRisqueTheoriqueProduit prtProduit:prtProduits){
    		
			listeCodeProduit.add(StringUtils.trimToEmpty(prtProduit
					.getProduit().getProdRfa())
					+ StringUtils.trimToEmpty(prtProduit.getProduit()
							.getTypeProduit().getTprodRfa()));
    	}
    	Collections.sort(listeCodeProduit);
    	empreinte.append(StringUtils.join(listeCodeProduit));
    	
    	
    	return empreinte.toString();
    }
}
