package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.arq.business.rule.PonderationZoneRule;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VZone;
import fr.gouv.agriculture.orion.business.BaseEntity;
import java.math.BigDecimal;



// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class PonderationZone extends BaseEntity implements PonderationARQ<PonderationZone>, IGenericList {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private Integer pzoneId;

    /**  persistent field. */
     private VZone zone;

    /**  persistent field. */
     private BigDecimal pzonePoidsNb2;

    /**  persistent field. */
     private FormuleRisque formuleRisque;

    /**
     *  Empty constructor.
     */
    public PonderationZone() {
    	super();
    }
    
    /**
     *  Constructeur pour dupliquer une PonderationZone.
     *
     * @param ponderationZone the ponderation zone
     */
    public PonderationZone(PonderationZone ponderationZone) {
    	super();
    	
    	if(ponderationZone != null){
    		zone = ponderationZone.getZone();
    		pzonePoidsNb2 = ponderationZone.getPzonePoidsNb2();
    		formuleRisque = ponderationZone.getFormuleRisque();
    	}
    	
    }
    

    /**
     * Gets the pzone id.
     *
     * @return the pzone id
     */
    public Integer getPzoneId() {
        return this.pzoneId;
    }

    /**
     * Sets the pzone id.
     *
     * @param pzoneId the new pzone id
     */
    public void setPzoneId(Integer pzoneId) {
        this.pzoneId = pzoneId;
    }


    /**
     * Gets the pzone poids nb2.
     *
     * @return the pzone poids nb2
     */
    public BigDecimal getPzonePoidsNb2() {
        return this.pzonePoidsNb2;
    }

    /**
     * Sets the pzone poids nb2.
     *
     * @param pzonePoidsNb2 the new pzone poids nb2
     */
    public void setPzonePoidsNb2(BigDecimal pzonePoidsNb2) {
        this.pzonePoidsNb2 = pzonePoidsNb2;
    }

    /**
     * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ#getFormuleRisque()
     */
    public FormuleRisque getFormuleRisque() {
        return this.formuleRisque;
    }

    /**
     * Sets the formule risque.
     *
     * @param formuleRisque the new formule risque
     */
    public void setFormuleRisque(FormuleRisque formuleRisque) {
        this.formuleRisque = formuleRisque;
    }

	/**
	 * Gets the zone.
	 *
	 * @return the zone
	 */
	public VZone getZone() {
		return zone;
	}

	/**
	 * Sets the zone.
	 *
	 * @param zone the zone to set
	 */
	public void setZone(VZone zone) {
		this.zone = zone;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getPzoneId());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return pzoneId;
	}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.BaseEntity#breakAssociations()
	 */
	@Override
	public void breakAssociations() {
		
		super.breakAssociations();
		formuleRisque.getPonderationZones().remove(this);
	}

        /**
         * @see fr.gouv.agriculture.orion.business.BaseEntity#buildRules()
         */
        @Override
        public void buildRules() {
            super.buildRules();
            addRule(new PonderationZoneRule());
        }
    
	/**
	 * Comparaison des ponderation, retourne 0 si egual 1 sinon.
	 *
	 * @param ponderation objet à comparer
	 * @return 0 si egual 1 sinon
	 */
	@Override
	public int compareTo(PonderationZone ponderation) {
		int result = COMPARE_OK;
		
		if (ponderation != null) {
			
			if (zone != null && ponderation.getZone() != null) {
				if (!zone.getIdentifier().equals(
						ponderation.getZone().getIdentifier())) {
					result = COMPARE_KO;
				}
			}

		} else {
			result = COMPARE_KO;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ#duplicate()
	 */
	@Override
	public PonderationZone duplicate() {
		return new PonderationZone(this);
	}

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getStringTriSur1Colonne()
     */
    @Override
    public String getStringTriSur1Colonne() {
        return null;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getFirstStringTriSur2Colonne()
     */
    @Override
    public String getFirstStringTriSur2Colonne() {
        String retour=null;
        if (zone!=null && zone.getTypeZone()!=null) {
            retour = zone.getTypeZone().getTzLb();
        }
        return retour;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getSecondStringTriSur2Colonne()
     */
    @Override
    public String getSecondStringTriSur2Colonne() {
        String retour=null;
        if (zone!=null) {
            retour = zone.getZlb();
        }
        return retour;
    }
}
	
