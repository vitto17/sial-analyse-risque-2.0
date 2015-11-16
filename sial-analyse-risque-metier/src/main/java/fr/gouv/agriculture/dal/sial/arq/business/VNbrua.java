package fr.gouv.agriculture.dal.sial.arq.business;

import java.io.Serializable;

import fr.gouv.agriculture.dal.sial.arq.business.rule.VNbruaRule;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class VNbrua extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The camp rfa. */
	private String campRfa;
	
	/** The dt rfa. */
	private String dtRfa;
	
	/** The classe rfa. */
	private String classeRfa;
	
	/** The code insee commune. */
	private String codeInseeCommune;

    /**  persistent field. */
    private VCampagne campagne;

    /**  persistent field. */
     private VDomaineTechnique domaineTechnique;

     /**  persistent field. */
     private ClasseRisque classeRisque;
        
    /**  identifier field. */
     private Integer nbrUa;

    /**
     *  Empty constructor.
     */
    public VNbrua() {
    	super();
    }


    /**
     * Gets the campagne.
     *
     * @return the campagne
     */
    public VCampagne getCampagne() {
        return campagne;
    }

    /**
     * Sets the campagne.
     *
     * @param campagne the new campagne
     */
    public void setCampagne(VCampagne campagne) {
        this.campagne = campagne;
    }


    /**
     * Gets the domaine technique.
     *
     * @return the domaine technique
     */
    public VDomaineTechnique getDomaineTechnique() {
        return domaineTechnique;
    }


    /**
     * Sets the domaine technique.
     *
     * @param domaineTechnique the new domaine technique
     */
    public void setDomaineTechnique(VDomaineTechnique domaineTechnique) {
        this.domaineTechnique = domaineTechnique;
    }


    /**
     * Gets the classe risque.
     *
     * @return the classe risque
     */
    public ClasseRisque getClasseRisque() {
        return classeRisque;
    }


    /**
     * Sets the classe risque.
     *
     * @param classeRisque the new classe risque
     */
    public void setClasseRisque(ClasseRisque classeRisque) {
        this.classeRisque = classeRisque;
    }


    /**
     * Gets the nbr ua.
     *
     * @return the nbr ua
     */
    public Integer getNbrUa() {
        return this.nbrUa;
    }


    /**
     * Sets the nbr ua.
     *
     * @param nbrUa the new nbr ua
     */
    public void setNbrUa(Integer nbrUa) {
        this.nbrUa = nbrUa;
    }


	/**
	 * Gets the camp rfa.
	 *
	 * @return the camp rfa
	 */
	public String getCampRfa() {
		return campRfa;
	}


	/**
	 * Sets the camp rfa.
	 *
	 * @param campRfa the new camp rfa
	 */
	public void setCampRfa(String campRfa) {
		this.campRfa = campRfa;
	}


	/**
	 * Gets the dt rfa.
	 *
	 * @return the dt rfa
	 */
	public String getDtRfa() {
		return dtRfa;
	}

	/**
	 * Sets the dt rfa.
	 *
	 * @param dtRfa the new dt rfa
	 */
	public void setDtRfa(String dtRfa) {
		this.dtRfa = dtRfa;
	}

	/**
	 * Gets the classe rfa.
	 *
	 * @return the classe rfa
	 */
	public String getClasseRfa() {
		return classeRfa;
	}

	/**
	 * Sets the classe rfa.
	 *
	 * @param classeRfa the new classe rfa
	 */
	public void setClasseRfa(String classeRfa) {
		this.classeRfa = classeRfa;
	}

	/**
	 * Gets the code insee commune.
	 *
	 * @return the code insee commune
	 */
	public String getCodeInseeCommune() {
		return codeInseeCommune;
	}

	/**
	 * Sets the code insee commune.
	 *
	 * @param codeInseeCommune the new code insee commune
	 */
	public void setCodeInseeCommune(String codeInseeCommune) {
		this.codeInseeCommune = codeInseeCommune;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getNbrUa());
		return toStringBuilder.toString();
}

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    @Override
    public Serializable getIdentifier() {
        return this.campagne + " - " + this.domaineTechnique + " - " + this.classeRisque;
    }
    
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.orion.business.BaseEntity#buildRules()
     */
    @Override
    public void buildRules() {
        super.buildRules();
        addRule(new VNbruaRule());
    }
    
}
