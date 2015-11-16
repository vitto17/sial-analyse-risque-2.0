package fr.gouv.agriculture.dal.sial.arq.business;



// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class MappingGuide extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private Integer mapId;

    /**  persistent field. */
     private String tprodRfa;

    /**  persistent field. */
     private String mapNomGuideLb;

    /**  persistent field. */
     private String mapProdTypeLb;

    /**
     *  Empty constructor.
     */
    public MappingGuide() {
    	super();
    }

    /**
     * Gets the map id.
     *
     * @return the map id
     */
    public Integer getMapId() {
        return this.mapId;
    }

    /**
     * Sets the map id.
     *
     * @param mapId the new map id
     */
    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * Gets the tprod rfa.
     *
     * @return the tprod rfa
     */
    public String getTprodRfa() {
        return this.tprodRfa;
    }

    /**
     * Sets the tprod rfa.
     *
     * @param tprodRfa the new tprod rfa
     */
    public void setTprodRfa(String tprodRfa) {
        this.tprodRfa = tprodRfa;
    }

    /**
     * Gets the map nom guide lb.
     *
     * @return the map nom guide lb
     */
    public String getMapNomGuideLb() {
        return this.mapNomGuideLb;
    }

    /**
     * Sets the map nom guide lb.
     *
     * @param mapNomGuideLb the new map nom guide lb
     */
    public void setMapNomGuideLb(String mapNomGuideLb) {
        this.mapNomGuideLb = mapNomGuideLb;
    }

    /**
     * Gets the map prod type lb.
     *
     * @return the map prod type lb
     */
    public String getMapProdTypeLb() {
        return this.mapProdTypeLb;
    }

    /**
     * Sets the map prod type lb.
     *
     * @param mapProdTypeLb the new map prod type lb
     */
    public void setMapProdTypeLb(String mapProdTypeLb) {
        this.mapProdTypeLb = mapProdTypeLb;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getMapId());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return mapId;
	}
}
