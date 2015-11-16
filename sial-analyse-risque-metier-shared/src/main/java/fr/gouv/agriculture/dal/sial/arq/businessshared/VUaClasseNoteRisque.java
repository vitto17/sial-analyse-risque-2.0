package fr.gouv.agriculture.dal.sial.arq.businessshared;


import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.dal.sial.usagers.businessshared.VUniteActivite;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class VUaClasseNoteRisque extends fr.gouv.agriculture.orion.business.BaseEntity {


    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**  persistent field. */
     private String uaClasseNoteRisqueRfa;
     
     /**  persistent field. */
     private VUniteActivite uniteActivite;

    /**  persistent field. */
     private VDomaineTechnique domaineTechnique;

    /**  persistent field. */
     private VCampagne campagne;
     
     /**  persistent field. */
     private VNoteRisque noteRisque;
     
     /**  persistent field. */
     private VClasseRisque classeRisque;
     
    /**
     *  Empty constructor.
     */
    public VUaClasseNoteRisque() {
    	super();
    }

    public String getUaClasseNoteRisqueRfa() {
        return uaClasseNoteRisqueRfa;
    }

    public void setUaClasseNoteRisqueRfa(String uaClasseNoteRisqueRfa) {
        this.uaClasseNoteRisqueRfa = uaClasseNoteRisqueRfa;
    }

    public VUniteActivite getUniteActivite() {
        return uniteActivite;
    }

    public void setUniteActivite(VUniteActivite uniteActivite) {
        this.uniteActivite = uniteActivite;
    }

    public VDomaineTechnique getDomaineTechnique() {
        return domaineTechnique;
    }

    public void setDomaineTechnique(VDomaineTechnique domaineTechnique) {
        this.domaineTechnique = domaineTechnique;
    }

    public VCampagne getCampagne() {
        return campagne;
    }

    public void setCampagne(VCampagne campagne) {
        this.campagne = campagne;
    }

    public VNoteRisque getNoteRisque() {
        return noteRisque;
    }

    public void setNoteRisque(VNoteRisque noteRisque) {
        this.noteRisque = noteRisque;
    }

    public VClasseRisque getClasseRisque() {
        return classeRisque;
    }

    public void setClasseRisque(VClasseRisque classeRisque) {
        this.classeRisque = classeRisque;
    }
    
    

   
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getUaClasseNoteRisqueRfa());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return uaClasseNoteRisqueRfa;
	}

}
