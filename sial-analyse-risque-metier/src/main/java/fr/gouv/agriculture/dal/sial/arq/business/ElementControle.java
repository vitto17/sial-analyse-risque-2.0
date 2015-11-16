package fr.gouv.agriculture.dal.sial.arq.business;

import java.util.Date;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class ElementControle extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private String econRfa;

    /**  nullable persistent field. */
     private String econCourtLb;

    /**  nullable persistent field. */
     private String econLb;

    /**  nullable persistent field. */
     private Date econDebDt;

    /**  nullable persistent field. */
     private Date econFinDt;

    /**  nullable persistent field. */
     private Short econTriNb;

    /**
     *  Empty constructor.
     */
    public ElementControle() {
    	super();
    }

    /**
     * Gets the econ rfa.
     *
     * @return the econ rfa
     */
    public String getEconRfa() {
        return this.econRfa;
    }

    /**
     * Sets the econ rfa.
     *
     * @param econRfa the new econ rfa
     */
    public void setEconRfa(String econRfa) {
        this.econRfa = econRfa;
    }

    /**
     * Gets the econ court lb.
     *
     * @return the econ court lb
     */
    public String getEconCourtLb() {
        return this.econCourtLb;
    }

    /**
     * Sets the econ court lb.
     *
     * @param econCourtLb the new econ court lb
     */
    public void setEconCourtLb(String econCourtLb) {
        this.econCourtLb = econCourtLb;
    }

    /**
     * Gets the econ lb.
     *
     * @return the econ lb
     */
    public String getEconLb() {
        return this.econLb;
    }

    /**
     * Sets the econ lb.
     *
     * @param econLb the new econ lb
     */
    public void setEconLb(String econLb) {
        this.econLb = econLb;
    }

    /**
     * Gets the econ deb dt.
     *
     * @return the econ deb dt
     */
    public Date getEconDebDt() {
        return this.econDebDt;
    }

    /**
     * Sets the econ deb dt.
     *
     * @param econDebDt the new econ deb dt
     */
    public void setEconDebDt(Date econDebDt) {
        this.econDebDt = econDebDt;
    }

    /**
     * Gets the econ fin dt.
     *
     * @return the econ fin dt
     */
    public Date getEconFinDt() {
        return this.econFinDt;
    }

    /**
     * Sets the econ fin dt.
     *
     * @param econFinDt the new econ fin dt
     */
    public void setEconFinDt(Date econFinDt) {
        this.econFinDt = econFinDt;
    }

    /**
     * Gets the econ tri nb.
     *
     * @return the econ tri nb
     */
    public Short getEconTriNb() {
        return this.econTriNb;
    }

    /**
     * Sets the econ tri nb.
     *
     * @param econTriNb the new econ tri nb
     */
    public void setEconTriNb(Short econTriNb) {
        this.econTriNb = econTriNb;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getEconRfa());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return econRfa;
	}
}
