package fr.gouv.agriculture.dal.sial.arq.business;

// TODO: Auto-generated Javadoc
/**
 * The Class PonderationRisqueTheoriqueApprobation.
 *
 * @author Hibernate CodeGenerator Renderer:
 *         fr.gouv.agriculture.orion.automation
 *         .persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class PonderationRisqueTheoriqueApprobation extends fr.gouv.agriculture.orion.business.BaseEntity implements PonderationRiTh {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  identifier field. */
	private Integer prisqtheoRapprId;

	/**  persistent field. */
	private fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation approbation;

	/**  persistent field. */
	private fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique ponderationRisqueTheorique;

	/**
	 *  Empty constructor.
	 */
	public PonderationRisqueTheoriqueApprobation() {
		super();
	}

	/**
	 * Instantiates a new ponderation risque theorique approbation.
	 *
	 * @param approbation the approbation
	 */
	public PonderationRisqueTheoriqueApprobation(PonderationRisqueTheoriqueApprobation approbation) {
		super();
		this.prisqtheoRapprId = approbation.getPrisqtheoRapprId();
		this.approbation = approbation.getApprobation();
		this.ponderationRisqueTheorique = approbation.getPonderationRisqueTheorique();
	}

	/**
	 * Gets the prisqtheo rappr id.
	 *
	 * @return the prisqtheo rappr id
	 */
	public Integer getPrisqtheoRapprId() {
		return this.prisqtheoRapprId;
	}

	/**
	 * Sets the prisqtheo rappr id.
	 *
	 * @param prisqtheoRapprId the new prisqtheo rappr id
	 */
	public void setPrisqtheoRapprId(Integer prisqtheoRapprId) {
		this.prisqtheoRapprId = prisqtheoRapprId;
	}

	/**
	 * Gets the approbation.
	 *
	 * @return the approbation
	 */
	public fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation getApprobation() {
		return this.approbation;
	}

	/**
	 * Sets the approbation.
	 *
	 * @param approbation the new approbation
	 */
	public void setApprobation(fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation approbation) {
		this.approbation = approbation;
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
            if (ponderationRisqueTheorique != null && ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations()!= null) {
                ponderationRisqueTheorique.getPonderationRisqueTheoriqueApprobations().remove(this);
            }
        }
        
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getPrisqtheoRapprId());
		return toStringBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	@Override
	public java.io.Serializable getIdentifier() {
		return prisqtheoRapprId;
	}
        
        /* (non-Javadoc)
         * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationRiTh#getStringLibelleColumnToOrder()
         */
        @Override
        public String getStringLibelleColumnToOrder() {
            return approbation.getRapprLb();
        }
        
        /* (non-Javadoc)
         * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationRiTh#getStringRfaColumnToOrder()
         */
        @Override
        public String getStringRfaColumnToOrder() {
            return approbation.getRapprRfa();
        }
}
