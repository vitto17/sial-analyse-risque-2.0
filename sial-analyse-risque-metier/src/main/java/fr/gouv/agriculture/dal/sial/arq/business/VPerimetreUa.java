package fr.gouv.agriculture.dal.sial.arq.business;

import java.io.Serializable;



// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class VPerimetreUa extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private String uaRfa;

    /**  identifier field. */
     private Integer localisationAdrRfa;

    /**  identifier field. */
     private Integer etbRfa;

    /**  identifier field. */
     private Integer geoRfa;

    /**  identifier field. */
     private String taRfa;

    /**  identifier field. */
     private String diffusionRfa;

    /**
     *  Empty constructor.
     */
    public VPerimetreUa() {
    	super();
    }

    /**
     * Gets the ua rfa.
     *
     * @return the ua rfa
     */
    public String getUaRfa() {
        return this.uaRfa;
    }

    /**
     * Sets the ua rfa.
     *
     * @param uaRfa the new ua rfa
     */
    public void setUaRfa(String uaRfa) {
        this.uaRfa = uaRfa;
    }

    /**
     * Gets the localisation adr rfa.
     *
     * @return the localisation adr rfa
     */
    public Integer getLocalisationAdrRfa() {
        return this.localisationAdrRfa;
    }

    /**
     * Sets the localisation adr rfa.
     *
     * @param localisationAdrRfa the new localisation adr rfa
     */
    public void setLocalisationAdrRfa(Integer localisationAdrRfa) {
        this.localisationAdrRfa = localisationAdrRfa;
    }

    /**
     * Gets the etb rfa.
     *
     * @return the etb rfa
     */
    public Integer getEtbRfa() {
        return this.etbRfa;
    }

    /**
     * Sets the etb rfa.
     *
     * @param etbRfa the new etb rfa
     */
    public void setEtbRfa(Integer etbRfa) {
        this.etbRfa = etbRfa;
    }

    /**
     * Gets the geo rfa.
     *
     * @return the geo rfa
     */
    public Integer getGeoRfa() {
        return this.geoRfa;
    }

    /**
     * Sets the geo rfa.
     *
     * @param geoRfa the new geo rfa
     */
    public void setGeoRfa(Integer geoRfa) {
        this.geoRfa = geoRfa;
    }

    /**
     * Gets the ta rfa.
     *
     * @return the ta rfa
     */
    public String getTaRfa() {
        return this.taRfa;
    }

    /**
     * Sets the ta rfa.
     *
     * @param taRfa the new ta rfa
     */
    public void setTaRfa(String taRfa) {
        this.taRfa = taRfa;
    }

    /**
     * Gets the diffusion rfa.
     *
     * @return the diffusion rfa
     */
    public String getDiffusionRfa() {
        return this.diffusionRfa;
    }

    /**
     * Sets the diffusion rfa.
     *
     * @param diffusionRfa the new diffusion rfa
     */
    public void setDiffusionRfa(String diffusionRfa) {
        this.diffusionRfa = diffusionRfa;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getDiffusionRfa());
		return toStringBuilder.toString();
}

	/**
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    @Override
    public Serializable getIdentifier() {
        return uaRfa;
    }
}
