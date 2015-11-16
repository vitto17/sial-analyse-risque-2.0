package fr.gouv.agriculture.dal.sial.arq.business;

import java.util.Date;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class ModificationPonderation extends fr.gouv.agriculture.orion.business.BaseEntity {

	/**  serial. */
	private static final long serialVersionUID = 1L;
	
    /**  identifier field. */
     private Integer modpodId;

    /**  nullable persistent field. */
     private Date modpodCreaTs;

    /**  nullable persistent field. */
     private String modpodUtiCreaLb;

    /**  nullable persistent field. */
     private String modpodUtiModLb;

    /**  nullable persistent field. */
     private Date modpodModTs;

    /**  nullable persistent field. */
     private String modpodTypepondLb;

    /**  persistent field. */
     private fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque formuleRisque;

    /**
     *  Empty constructor.
     */
    public ModificationPonderation() {
    	super();
    }

    /**
     * Gets the modpod id.
     *
     * @return the modpod id
     */
    public Integer getModpodId() {
        return this.modpodId;
    }

    /**
     * Sets the modpod id.
     *
     * @param modpodId the new modpod id
     */
    public void setModpodId(Integer modpodId) {
        this.modpodId = modpodId;
    }

    /**
     * Gets the modpod crea ts.
     *
     * @return the modpod crea ts
     */
    public Date getModpodCreaTs() {
        return this.modpodCreaTs;
    }

    /**
     * Sets the modpod crea ts.
     *
     * @param modpodCreaTs the new modpod crea ts
     */
    public void setModpodCreaTs(Date modpodCreaTs) {
        this.modpodCreaTs = modpodCreaTs;
    }

    /**
     * Gets the modpod uti crea lb.
     *
     * @return the modpod uti crea lb
     */
    public String getModpodUtiCreaLb() {
        return this.modpodUtiCreaLb;
    }

    /**
     * Sets the modpod uti crea lb.
     *
     * @param modpodUtiCreaLb the new modpod uti crea lb
     */
    public void setModpodUtiCreaLb(String modpodUtiCreaLb) {
        this.modpodUtiCreaLb = modpodUtiCreaLb;
    }

    /**
     * Gets the modpod uti mod lb.
     *
     * @return the modpod uti mod lb
     */
    public String getModpodUtiModLb() {
        return this.modpodUtiModLb;
    }

    /**
     * Sets the modpod uti mod lb.
     *
     * @param modpodUtiModLb the new modpod uti mod lb
     */
    public void setModpodUtiModLb(String modpodUtiModLb) {
        this.modpodUtiModLb = modpodUtiModLb;
    }

    /**
     * Gets the modpod mod ts.
     *
     * @return the modpod mod ts
     */
    public Date getModpodModTs() {
        return this.modpodModTs;
    }

    /**
     * Sets the modpod mod ts.
     *
     * @param modpodModTs the new modpod mod ts
     */
    public void setModpodModTs(Date modpodModTs) {
        this.modpodModTs = modpodModTs;
    }

    /**
     * Gets the modpod typepond lb.
     *
     * @return the modpod typepond lb
     */
    public String getModpodTypepondLb() {
        return this.modpodTypepondLb;
    }

    /**
     * Sets the modpod typepond lb.
     *
     * @param modpodTypepondLb the new modpod typepond lb
     */
    public void setModpodTypepondLb(String modpodTypepondLb) {
        this.modpodTypepondLb = modpodTypepondLb;
    }

    /**
     * Gets the formule risque.
     *
     * @return the formule risque
     */
    public fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque getFormuleRisque() {
        return this.formuleRisque;
    }

    /**
     * Sets the formule risque.
     *
     * @param formuleRisque the new formule risque
     */
    public void setFormuleRisque(fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque formuleRisque) {
        this.formuleRisque = formuleRisque;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getModpodId());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return modpodId;
	}
}
