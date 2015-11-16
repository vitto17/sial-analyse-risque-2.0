package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.arq.constante.TypeProduit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VAlimentationAnimale;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDenree;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VEspeceDgal;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VFiliereVegetal;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeIntrant;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeProduit;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class Produit extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private Integer prodId;

    /**  nullable persistent field. */
     private VTypeProduit typeProduit;

    /**  persistent field. */
     private String prodRfa;

    /**  persistent field. */
//     private String prodLb="FIXME2";

    /** persistent field */
//     private List ponderationRisqueTheoriqueProduits;

    /** Not persistent field */
     private transient VEspeceDgal anima;
     
    /**  Not persistent field. */
     private transient VAlimentationAnimale alani;
     
    /**  Not persistent field. */
     private transient VTypeIntrant intra;
     
    /**  Not persistent field. */
     private transient VDenree denre;
     
    /**  Not persistent field. */
     private transient VFiliereVegetal veget;
     
    /**
     *  Empty constructor.
     */
    public Produit() {
    	super();
    }

    /**
     *  Constructeur permettant de dupliquer un objet.
     *
     * @param produit the produit
     */
    public Produit(Produit produit) {
    	super();
        this.prodId=produit.getProdId();
        this.typeProduit=produit.getTypeProduit();
        this.prodRfa=produit.getProdRfa();
//        this.prodLb=produit.getProdLb();
        this.anima=produit.getAnima();
        this.alani=produit.getAlani();
        this.intra=produit.getIntra();
        this.denre=produit.getDenre();
        this.veget=produit.getVeget();
    }
    
    /**
     * Gets the prod id.
     *
     * @return the prod id
     */
    public Integer getProdId() {
        return this.prodId;
    }

    /**
     * Sets the prod id.
     *
     * @param prodId the new prod id
     */
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    /**
     * Gets the type produit.
     *
     * @return the type produit
     */
    public VTypeProduit getTypeProduit() {
        return typeProduit;
    }

    /**
     * Sets the type produit.
     *
     * @param typeProduit the new type produit
     */
    public void setTypeProduit(VTypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    /**
     * Gets the prod rfa.
     *
     * @return the prod rfa
     */
    public String getProdRfa() {
        return this.prodRfa;
    }

    /**
     * Sets the prod rfa.
     *
     * @param prodRfa the new prod rfa
     */
    public void setProdRfa(String prodRfa) {
        this.prodRfa = prodRfa;
    }


//    public List getPonderationRisqueTheoriqueProduits() {
//        return this.ponderationRisqueTheoriqueProduits;
//    }
//
//    public void setPonderationRisqueTheoriqueProduits(List ponderationRisqueTheoriqueProduits) {
//        this.ponderationRisqueTheoriqueProduits = ponderationRisqueTheoriqueProduits;
//    }
    
    /**
 * Gets the prod lb by type produit.
 *
 * @return the prod lb by type produit
 */
public String getProdLbByTypeProduit() {
        String libelle = "unknow2";
        if (typeProduit != null) {
            
            if (TypeProduit.TYPE_PROD_ANIMA.equals(typeProduit.getTprodRfa()) && anima != null) {
                libelle = anima.getEspdLb();
            }
            if (TypeProduit.TYPE_PROD_VEGET.equals(typeProduit.getTprodRfa()) && veget != null) {
                libelle = veget.getFilvegLb();
            }
            if (TypeProduit.TYPE_PROD_DENRE.equals(typeProduit.getTprodRfa()) && denre != null) {
                libelle = denre.getDenLb();
            }
            if (TypeProduit.TYPE_PROD_INTRA.equals(typeProduit.getTprodRfa()) && intra != null) {
                libelle = intra.getTintLb();
            }
            if (TypeProduit.TYPE_PROD_ALANI.equals(typeProduit.getTprodRfa()) && alani != null) {
                libelle = alani.getAaLb();
            }
            
        }
        
        return libelle;
    }

    /**
     * Gets the anima.
     *
     * @return the anima
     */
    public VEspeceDgal getAnima() {
        return anima;
    }

    /**
     * Sets the anima.
     *
     * @param anima the new anima
     */
    public void setAnima(VEspeceDgal anima) {
        this.anima = anima;
    }

    /**
     * Gets the alani.
     *
     * @return the alani
     */
    public VAlimentationAnimale getAlani() {
        return alani;
    }

    /**
     * Sets the alani.
     *
     * @param alani the new alani
     */
    public void setAlani(VAlimentationAnimale alani) {
        this.alani = alani;
    }

    /**
     * Gets the intra.
     *
     * @return the intra
     */
    public VTypeIntrant getIntra() {
        return intra;
    }

    /**
     * Sets the intra.
     *
     * @param intra the new intra
     */
    public void setIntra(VTypeIntrant intra) {
        this.intra = intra;
    }

    /**
     * Gets the denre.
     *
     * @return the denre
     */
    public VDenree getDenre() {
        return denre;
    }

    /**
     * Sets the denre.
     *
     * @param denre the new denre
     */
    public void setDenre(VDenree denre) {
        this.denre = denre;
    }

    /**
     * Gets the veget.
     *
     * @return the veget
     */
    public VFiliereVegetal getVeget() {
        return veget;
    }

    /**
     * Sets the veget.
     *
     * @param veget the new veget
     */
    public void setVeget(VFiliereVegetal veget) {
        this.veget = veget;
    }
    
    /**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getProdId());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return prodId;
	}
}
