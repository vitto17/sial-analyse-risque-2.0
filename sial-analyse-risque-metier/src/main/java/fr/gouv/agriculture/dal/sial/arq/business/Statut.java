package fr.gouv.agriculture.dal.sial.arq.business;

import java.util.List;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class Statut extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private Integer statutId;

    /**  persistent field. */
     private String statutLb;

    /**  persistent field. */
     private List batches;

    /**
     *  Empty constructor.
     */
    public Statut() {
    	super();
    }

    /**
     * Gets the statut id.
     *
     * @return the statut id
     */
    public Integer getStatutId() {
        return this.statutId;
    }

    /**
     * Sets the statut id.
     *
     * @param statutId the new statut id
     */
    public void setStatutId(Integer statutId) {
        this.statutId = statutId;
    }

    /**
     * Gets the statut lb.
     *
     * @return the statut lb
     */
    public String getStatutLb() {
        return this.statutLb;
    }

    /**
     * Sets the statut lb.
     *
     * @param statutLb the new statut lb
     */
    public void setStatutLb(String statutLb) {
        this.statutLb = statutLb;
    }

    /**
     * Gets the batches.
     *
     * @return the batches
     */
    public List getBatches() {
        return this.batches;
    }

    /**
     * Sets the batches.
     *
     * @param batches the new batches
     */
    public void setBatches(List batches) {
        this.batches = batches;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getStatutId());
		return toStringBuilder.toString();
}
	
	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	@Override
	public java.io.Serializable getIdentifier(){
		return statutId;
	}

	/**
	 * The Enum StatutEnum.
	 */
	@SuppressWarnings("javadoc")
	public enum StatutEnum {

		/** The ok. */
		OK(1), /** The en cours. */
 EN_COURS(2), /** The ko. */
 KO(3), /** The en attente. */
 EN_ATTENTE(4);

		/** The code. */
		private Integer code;

		/**
		 * Instantiates a new statut enum.
		 *
		 * @param code the code
		 */
		private StatutEnum(Integer code) {
			this.code = code;
		}

		/**
		 * Gets the code.
		 *
		 * @return the code
		 */
		public Integer getCode() {
			return code;
		}

	}
}
