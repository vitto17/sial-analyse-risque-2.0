package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VProcede;

// TODO: Auto-generated Javadoc
/**
 * The Class PonderationRisqueTheoriqueProcede.
 *
 * @author Hibernate CodeGenerator Renderer:
 *         fr.gouv.agriculture.orion.automation
 *         .persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class PonderationRisqueTheoriqueProcede extends fr.gouv.agriculture.orion.business.BaseEntity implements PonderationRiTh {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  identifier field. */
	private Integer prisqtheoProcId;

	/**  persistent field. */
	private VProcede procede;

	/**  persistent field. */
	private fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique ponderationRisqueTheorique;

	/**
	 *  Empty constructor.
	 */
	public PonderationRisqueTheoriqueProcede() {
		super();
	}

	/**
	 *  Constructeur permettant de dupliquer l'objet.
	 *
	 * @param procede the procede
	 */
	public PonderationRisqueTheoriqueProcede(PonderationRisqueTheoriqueProcede procede) {
		super();
		this.prisqtheoProcId = procede.getPrisqtheoProcId();
		this.procede = procede.getProcede();
		this.ponderationRisqueTheorique = procede.getPonderationRisqueTheorique();
	}

	/**
	 * Gets the prisqtheo proc id.
	 *
	 * @return the prisqtheo proc id
	 */
	public Integer getPrisqtheoProcId() {
		return this.prisqtheoProcId;
	}

	/**
	 * Sets the prisqtheo proc id.
	 *
	 * @param prisqtheoProcId the new prisqtheo proc id
	 */
	public void setPrisqtheoProcId(Integer prisqtheoProcId) {
		this.prisqtheoProcId = prisqtheoProcId;
	}

	/**
	 * Gets the procede.
	 *
	 * @return the procede
	 */
	public VProcede getProcede() {
		return this.procede;
	}

	/**
	 * Sets the procede.
	 *
	 * @param procede the new procede
	 */
	public void setProcede(VProcede procede) {
		this.procede = procede;
	}

	/**
	 * Gets the ponderation risque theorique.
	 *
	 * @return the ponderation risque theorique
	 */
	public fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique getPonderationRisqueTheorique() {
		return this.ponderationRisqueTheorique;
	}

	/**
	 * Sets the ponderation risque theorique.
	 *
	 * @param ponderationRisqueTheorique the new ponderation risque theorique
	 */
	public void setPonderationRisqueTheorique(
			fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique ponderationRisqueTheorique) {
		this.ponderationRisqueTheorique = ponderationRisqueTheorique;
	}

        /* (non-Javadoc)
         * @see fr.gouv.agriculture.orion.business.BaseEntity#breakAssociations()
         */
        @Override
        public void breakAssociations() {
            if (ponderationRisqueTheorique != null && ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes()!= null) {
                ponderationRisqueTheorique.getPonderationRisqueTheoriqueProcedes().remove(this);
            }
        }
        
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getPrisqtheoProcId());
		return toStringBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	@Override
	public java.io.Serializable getIdentifier() {
		return prisqtheoProcId;
	}
        
        /* (non-Javadoc)
         * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationRiTh#getStringLibelleColumnToOrder()
         */
        @Override
        public String getStringLibelleColumnToOrder() {
            return procede.getProcLb();
        }
        
        /* (non-Javadoc)
         * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationRiTh#getStringRfaColumnToOrder()
         */
        @Override
        public String getStringRfaColumnToOrder() {
            return procede.getProcRfa();
        }
}
