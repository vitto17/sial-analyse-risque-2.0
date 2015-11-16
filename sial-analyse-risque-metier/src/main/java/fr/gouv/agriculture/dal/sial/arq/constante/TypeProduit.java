/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.constante;

/**
 * Liste des types de produit de nomenclatures.
 * 
 * @author pegaltier
 */
public final class TypeProduit {
    
	/**
	 * Constructeur par defaut.
	 */
	private TypeProduit(){
		
	}
	
	/** Code du type de produit ALIMENTATION_ANIMALE */
	public static final String TYPE_PROD_ALANI = "ALANI";
	/** Code du type de produit ESPECE_DGAL */
	public static final String TYPE_PROD_ANIMA = "ANIMA";
	/** Code du type de produit DENREE */
	public static final String TYPE_PROD_DENRE = "DENRE";
	/** Code du type de produit TYPE_INTRANT */
	public static final String TYPE_PROD_INTRA = "INTRA";
	/** Code du type de produit FILIERE VEGETAL */
	public static final String TYPE_PROD_VEGET = "VEGET";

}
