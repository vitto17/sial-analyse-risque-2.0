package fr.gouv.agriculture.dal.sial.arq.business;



// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class PonderationNoteInpection extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private Integer pniId;

    /**  persistent field. */
     private String nomenRfa;

    /**  persistent field. */
     private String evalRfa;

    /**  persistent field. */
     private byte pniPoidsNb2;

    /**
     *  Empty constructor.
     */
    public PonderationNoteInpection() {
    	super();
    }

    /**
     * Gets the pni id.
     *
     * @return the pni id
     */
    public Integer getPniId() {
        return this.pniId;
    }

    /**
     * Sets the pni id.
     *
     * @param pniId the new pni id
     */
    public void setPniId(Integer pniId) {
        this.pniId = pniId;
    }

    /**
     * Gets the nomen rfa.
     *
     * @return the nomen rfa
     */
    public String getNomenRfa() {
        return this.nomenRfa;
    }

    /**
     * Sets the nomen rfa.
     *
     * @param nomenRfa the new nomen rfa
     */
    public void setNomenRfa(String nomenRfa) {
        this.nomenRfa = nomenRfa;
    }

    /**
     * Gets the eval rfa.
     *
     * @return the eval rfa
     */
    public String getEvalRfa() {
        return this.evalRfa;
    }

    /**
     * Sets the eval rfa.
     *
     * @param evalRfa the new eval rfa
     */
    public void setEvalRfa(String evalRfa) {
        this.evalRfa = evalRfa;
    }

    /**
     * Gets the pni poids nb2.
     *
     * @return the pni poids nb2
     */
    public byte getPniPoidsNb2() {
        return this.pniPoidsNb2;
    }

    /**
     * Sets the pni poids nb2.
     *
     * @param pniPoidsNb2 the new pni poids nb2
     */
    public void setPniPoidsNb2(byte pniPoidsNb2) {
        this.pniPoidsNb2 = pniPoidsNb2;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getPniId());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return pniId;
	}
}
