package fr.gouv.agriculture.dal.sial.arq.business;

// TODO: Auto-generated Javadoc
/**
 * The Class PonderationRisqueTheoriqueProduit.
 *
 * @author Hibernate CodeGenerator Renderer:
 *         fr.gouv.agriculture.orion.automation
 *         .persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class PonderationRisqueTheoriqueProduit extends fr.gouv.agriculture.orion.business.BaseEntity implements PonderationRiTh {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  identifier field. */
	private Integer prisqtheoProdId;

	/**  persistent field. */
	private fr.gouv.agriculture.dal.sial.arq.business.Produit produit;

	/**  persistent field. */
	private fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique ponderationRisqueTheorique;

	/**
	 *  Empty constructor.
	 */
	public PonderationRisqueTheoriqueProduit() {
		super();
	}

	/**
	 *  Constructeur permettant de dupliquer un objet.
	 *
	 * @param produit the produit
	 */
	public PonderationRisqueTheoriqueProduit(PonderationRisqueTheoriqueProduit produit) {
		super();
		this.prisqtheoProdId = produit.getPrisqtheoProdId();
		this.produit = new Produit(produit.getProduit());
                this.produit.setProdId(null);
		this.ponderationRisqueTheorique = produit.getPonderationRisqueTheorique();
	}

	/**
	 * Gets the prisqtheo prod id.
	 *
	 * @return the prisqtheo prod id
	 */
	public Integer getPrisqtheoProdId() {
		return this.prisqtheoProdId;
	}

	/**
	 * Sets the prisqtheo prod id.
	 *
	 * @param prisqtheoProdId the new prisqtheo prod id
	 */
	public void setPrisqtheoProdId(Integer prisqtheoProdId) {
		this.prisqtheoProdId = prisqtheoProdId;
	}

	/**
	 * Gets the produit.
	 *
	 * @return the produit
	 */
	public fr.gouv.agriculture.dal.sial.arq.business.Produit getProduit() {
		return this.produit;
	}

	/**
	 * Sets the produit.
	 *
	 * @param produit the new produit
	 */
	public void setProduit(fr.gouv.agriculture.dal.sial.arq.business.Produit produit) {
		this.produit = produit;
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
            if (ponderationRisqueTheorique != null && ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits()!= null) {
                ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits().remove(this);
            }
            ponderationRisqueTheorique=null;
        }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getPrisqtheoProdId());
		return toStringBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	@Override
	public java.io.Serializable getIdentifier() {
		return prisqtheoProdId;
	}

        /* (non-Javadoc)
         * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationRiTh#getStringLibelleColumnToOrder()
         */
        @Override
        public String getStringLibelleColumnToOrder() {
            return produit.getProdLbByTypeProduit();
        }
        
        /* (non-Javadoc)
         * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationRiTh#getStringRfaColumnToOrder()
         */
        @Override
        public String getStringRfaColumnToOrder() {
            return produit.getProdRfa();
        }
}
