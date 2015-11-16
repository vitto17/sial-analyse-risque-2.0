package fr.gouv.agriculture.dal.sial.arq.business;

import java.util.Date;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class ModeProduction extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private String mprodRfa;

    /**  nullable persistent field. */
     private String mprodCourtLb;

    /**  nullable persistent field. */
     private String mprodLb;

    /**  nullable persistent field. */
     private Date mprodDebDt;

    /**  nullable persistent field. */
     private Date mprodFinDt;

    /**  nullable persistent field. */
     private Short mprodTriNb;

    /**
     *  Empty constructor.
     */
    public ModeProduction() {
    	super();
    }

    /**
     * Gets the mprod rfa.
     *
     * @return the mprod rfa
     */
    public String getMprodRfa() {
        return this.mprodRfa;
    }

    /**
     * Sets the mprod rfa.
     *
     * @param mprodRfa the new mprod rfa
     */
    public void setMprodRfa(String mprodRfa) {
        this.mprodRfa = mprodRfa;
    }

    /**
     * Gets the mprod court lb.
     *
     * @return the mprod court lb
     */
    public String getMprodCourtLb() {
        return this.mprodCourtLb;
    }

    /**
     * Sets the mprod court lb.
     *
     * @param mprodCourtLb the new mprod court lb
     */
    public void setMprodCourtLb(String mprodCourtLb) {
        this.mprodCourtLb = mprodCourtLb;
    }

    /**
     * Gets the mprod lb.
     *
     * @return the mprod lb
     */
    public String getMprodLb() {
        return this.mprodLb;
    }

    /**
     * Sets the mprod lb.
     *
     * @param mprodLb the new mprod lb
     */
    public void setMprodLb(String mprodLb) {
        this.mprodLb = mprodLb;
    }

    /**
     * Gets the mprod deb dt.
     *
     * @return the mprod deb dt
     */
    public Date getMprodDebDt() {
        return this.mprodDebDt;
    }

    /**
     * Sets the mprod deb dt.
     *
     * @param mprodDebDt the new mprod deb dt
     */
    public void setMprodDebDt(Date mprodDebDt) {
        this.mprodDebDt = mprodDebDt;
    }

    /**
     * Gets the mprod fin dt.
     *
     * @return the mprod fin dt
     */
    public Date getMprodFinDt() {
        return this.mprodFinDt;
    }

    /**
     * Sets the mprod fin dt.
     *
     * @param mprodFinDt the new mprod fin dt
     */
    public void setMprodFinDt(Date mprodFinDt) {
        this.mprodFinDt = mprodFinDt;
    }

    /**
     * Gets the mprod tri nb.
     *
     * @return the mprod tri nb
     */
    public Short getMprodTriNb() {
        return this.mprodTriNb;
    }

    /**
     * Sets the mprod tri nb.
     *
     * @param mprodTriNb the new mprod tri nb
     */
    public void setMprodTriNb(Short mprodTriNb) {
        this.mprodTriNb = mprodTriNb;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getMprodRfa());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return mprodRfa;
	}
}
