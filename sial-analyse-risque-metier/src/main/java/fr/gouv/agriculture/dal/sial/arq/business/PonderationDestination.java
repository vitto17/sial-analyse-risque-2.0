package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.arq.business.rule.PonderationDestinationRule;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDestination;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.business.BaseEntity;
import java.math.BigDecimal;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class PonderationDestination extends BaseEntity implements PonderationARQ<PonderationDestination>, IGenericList {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  identifier field. */
     private Integer pdestId;

    /**  persistent field. */
     private VDestination destRfa;

    /**  persistent field. */
     private VTypeActivite taRfa;

    /**  persistent field. */
     private BigDecimal pdestPoidsNb2;

    /**  persistent field. */
     private fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque formuleRisque;

    /**
     *  Empty constructor.
     */
    public PonderationDestination() {
    	super();
    }

    /**
     *  Constructeur pour dupliquer une PonderationDestination.
     *
     * @param ponderationDestination the ponderation destination
     */
    public PonderationDestination(PonderationDestination ponderationDestination) {
    	super();
    	
    	if(ponderationDestination != null){
    		destRfa = ponderationDestination.getDestRfa();
                taRfa = ponderationDestination.getTaRfa();
                pdestPoidsNb2 = ponderationDestination.getPdestPoidsNb2();
                formuleRisque = ponderationDestination.getFormuleRisque();
    	}
    }
    
    /**
     * Gets the pdest id.
     *
     * @return the pdest id
     */
    public Integer getPdestId() {
        return this.pdestId;
    }

    /**
     * Sets the pdest id.
     *
     * @param pdestId the new pdest id
     */
    public void setPdestId(Integer pdestId) {
        this.pdestId = pdestId;
    }

    /**
     * Gets the dest rfa.
     *
     * @return the dest rfa
     */
    public VDestination getDestRfa() {
        return destRfa;
    }

    /**
     * Sets the dest rfa.
     *
     * @param destRfa the new dest rfa
     */
    public void setDestRfa(VDestination destRfa) {
        this.destRfa = destRfa;
    }

    /**
     * Gets the ta rfa.
     *
     * @return the ta rfa
     */
    public VTypeActivite getTaRfa() {
        return taRfa;
    }

    /**
     * Sets the ta rfa.
     *
     * @param taRfa the new ta rfa
     */
    public void setTaRfa(VTypeActivite taRfa) {
        this.taRfa = taRfa;
    }

    /**
     * Gets the pdest poids nb2.
     *
     * @return the pdest poids nb2
     */
    public BigDecimal getPdestPoidsNb2() {
        return this.pdestPoidsNb2;
    }

    /**
     * Sets the pdest poids nb2.
     *
     * @param pdestPoidsNb2 the new pdest poids nb2
     */
    public void setPdestPoidsNb2(BigDecimal pdestPoidsNb2) {
        this.pdestPoidsNb2 = pdestPoidsNb2;
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
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getPdestId());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return pdestId;
	}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.BaseEntity#breakAssociations()
	 */
	@Override
	public void breakAssociations() {
		
		super.breakAssociations();
		formuleRisque.getPonderationDestinations().remove(this);
	}
        
    /**
     * @see fr.gouv.agriculture.orion.business.BaseEntity#buildRules()
     */
    @Override
    public void buildRules() {
        super.buildRules();
        addRule(new PonderationDestinationRule());
    }

    /**
     * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ#duplicate()
     */
    @Override
    public PonderationDestination duplicate() {
        return new PonderationDestination(this);
    }

    /**
     * Comparaison des ponderation, retourne 0 si egal 1 sinon.
     *
     * @param object the object
     * @return 0 si egal 1 sinon
     */
    @Override
    public int compareTo(PonderationDestination object) {
        int result = COMPARE_OK;

        if (object != null) {
            if (destRfa != null && object.getDestRfa() != null) {
                if (!destRfa.getIdentifier().equals(
                        object.getDestRfa().getIdentifier())) {
                    result = COMPARE_KO;
                }
            }
            if (taRfa != null && object.getTaRfa() != null) {
                if (!taRfa.getIdentifier().equals(
                        object.getTaRfa().getIdentifier())) {
                    result = COMPARE_KO;
                }
            }
        } else {
            result = COMPARE_KO;
        }

        return result;
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
        if (taRfa!=null) {
            retour = taRfa.getTaLb();
        }
        return retour;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getSecondStringTriSur2Colonne()
     */
    @Override
    public String getSecondStringTriSur2Colonne() {
        String retour=null;
        if (destRfa!=null){
            retour = destRfa.getDestLb();
        }
        return retour;
    }
}
