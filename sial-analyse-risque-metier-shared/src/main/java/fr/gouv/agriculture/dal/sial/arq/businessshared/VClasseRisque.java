package fr.gouv.agriculture.dal.sial.arq.businessshared;

import java.math.BigDecimal;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class VClasseRisque extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  persistent field. */
     private String classeRfa;

    /**  nullable persistent field. */
     private String classeLb;

    /**  persistent field. */
     private long classeValeurNb;

    /**  persistent field. */
     private BigDecimal classeMinNb2;

    /**  nullable persistent field. */
     private BigDecimal classeMaxNb2;

     /**  persistent field. */
     private VDomaineTechnique domaineTechnique;

    /**  persistent field. */
     private VCampagne campagne;
     
    /**
     *  Empty constructor.
     */
    public VClasseRisque() {
    	super();
    }

    /**
     * Gets the classe rfa.
     *
     * @return the classe rfa
     */
    public String getClasseRfa() {
        return this.classeRfa;
    }

    /**
     * Sets the classe rfa.
     *
     * @param classeRfa the new classe rfa
     */
    public void setClasseRfa(String classeRfa) {
        this.classeRfa = classeRfa;
    }

    /**
     * Gets the classe lb.
     *
     * @return the classe lb
     */
    public String getClasseLb() {
        return this.classeLb;
    }

    /**
     * Sets the classe lb.
     *
     * @param classeLb the new classe lb
     */
    public void setClasseLb(String classeLb) {
        this.classeLb = classeLb;
    }

    /**
     * Gets the classe valeur nb.
     *
     * @return the classe valeur nb
     */
    public long getClasseValeurNb() {
        return this.classeValeurNb;
    }

    /**
     * Sets the classe valeur nb.
     *
     * @param classeValeurNb the new classe valeur nb
     */
    public void setClasseValeurNb(long classeValeurNb) {
        this.classeValeurNb = classeValeurNb;
    }

    /**
     * Gets the classe min nb2.
     *
     * @return the classe min nb2
     */
    public BigDecimal getClasseMinNb2() {
        return this.classeMinNb2;
    }

    /**
     * Sets the classe min nb2.
     *
     * @param classeMinNb2 the new classe min nb2
     */
    public void setClasseMinNb2(BigDecimal classeMinNb2) {
        this.classeMinNb2 = classeMinNb2;
    }

    /**
     * Gets the classe max nb2.
     *
     * @return the classe max nb2
     */
    public BigDecimal getClasseMaxNb2() {
        return this.classeMaxNb2;
    }

    /**
     * Sets the classe max nb2.
     *
     * @param classeMaxNb2 the new classe max nb2
     */
    public void setClasseMaxNb2(BigDecimal classeMaxNb2) {
        this.classeMaxNb2 = classeMaxNb2;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getClasseRfa());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return classeRfa;
	}

	/**
	 * Get the domaineTechnique
	 * @return the domaineTechnique
	 */
	public VDomaineTechnique getDomaineTechnique() {
		return domaineTechnique;
	}

	/**
	 * Set the domaineTechnique
	 * @param domaineTechnique the domaineTechnique to set
	 */
	public void setDomaineTechnique(VDomaineTechnique domaineTechnique) {
		this.domaineTechnique = domaineTechnique;
	}

	/**
	 * Get the campagne
	 * @return the campagne
	 */
	public VCampagne getCampagne() {
		return campagne;
	}

	/**
	 * Set the campagne
	 * @param campagne the campagne to set
	 */
	public void setCampagne(VCampagne campagne) {
		this.campagne = campagne;
	}
}
