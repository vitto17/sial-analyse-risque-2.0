package fr.gouv.agriculture.dal.sial.arq.business;

import java.util.Date;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class LieuControle extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private String lconRfa;

    /**  nullable persistent field. */
     private String lconCourtLb;

    /**  nullable persistent field. */
     private String lconLb;

    /**  nullable persistent field. */
     private Date lconDebDt;

    /**  nullable persistent field. */
     private Date lconFinDt;

    /**  nullable persistent field. */
     private Short lconTriNb;

    /**
     *  Empty constructor.
     */
    public LieuControle() {
    	super();
    }

    /**
     * Gets the lcon rfa.
     *
     * @return the lcon rfa
     */
    public String getLconRfa() {
        return this.lconRfa;
    }

    /**
     * Sets the lcon rfa.
     *
     * @param lconRfa the new lcon rfa
     */
    public void setLconRfa(String lconRfa) {
        this.lconRfa = lconRfa;
    }

    /**
     * Gets the lcon court lb.
     *
     * @return the lcon court lb
     */
    public String getLconCourtLb() {
        return this.lconCourtLb;
    }

    /**
     * Sets the lcon court lb.
     *
     * @param lconCourtLb the new lcon court lb
     */
    public void setLconCourtLb(String lconCourtLb) {
        this.lconCourtLb = lconCourtLb;
    }

    /**
     * Gets the lcon lb.
     *
     * @return the lcon lb
     */
    public String getLconLb() {
        return this.lconLb;
    }

    /**
     * Sets the lcon lb.
     *
     * @param lconLb the new lcon lb
     */
    public void setLconLb(String lconLb) {
        this.lconLb = lconLb;
    }

    /**
     * Gets the lcon deb dt.
     *
     * @return the lcon deb dt
     */
    public Date getLconDebDt() {
        return this.lconDebDt;
    }

    /**
     * Sets the lcon deb dt.
     *
     * @param lconDebDt the new lcon deb dt
     */
    public void setLconDebDt(Date lconDebDt) {
        this.lconDebDt = lconDebDt;
    }

    /**
     * Gets the lcon fin dt.
     *
     * @return the lcon fin dt
     */
    public Date getLconFinDt() {
        return this.lconFinDt;
    }

    /**
     * Sets the lcon fin dt.
     *
     * @param lconFinDt the new lcon fin dt
     */
    public void setLconFinDt(Date lconFinDt) {
        this.lconFinDt = lconFinDt;
    }

    /**
     * Gets the lcon tri nb.
     *
     * @return the lcon tri nb
     */
    public Short getLconTriNb() {
        return this.lconTriNb;
    }

    /**
     * Sets the lcon tri nb.
     *
     * @param lconTriNb the new lcon tri nb
     */
    public void setLconTriNb(Short lconTriNb) {
        this.lconTriNb = lconTriNb;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getLconRfa());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return lconRfa;
	}
}
