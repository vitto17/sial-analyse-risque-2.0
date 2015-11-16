package fr.gouv.agriculture.dal.sial.arq.businessshared;

import java.math.BigDecimal;
import java.util.Date;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.dal.sial.usagers.businessshared.VUniteActivite;


// TODO: Auto-generated Javadoc
/** 
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer 
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $ 
*/
public class VNoteRisque extends fr.gouv.agriculture.orion.business.BaseEntity {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**  persistent field. */
     private String noteRfa;

    /**  nullable persistent field. */
     private Long noteValNb;

    /**  persistent field. */
     private Date noteDateCalculDs;

    /**  nullable persistent field. */
     private BigDecimal notePRisquNb2;

    /**  nullable persistent field. */
     private BigDecimal notePVolNb2;

    /**  nullable persistent field. */
     private BigDecimal notePZoneNb2;

    /**  nullable persistent field. */
     private BigDecimal notePDiffNb2;

    /**  nullable persistent field. */
     private BigDecimal notePDestNb2;

    /**  nullable persistent field. */
     private BigDecimal notePNiNb2;

    /**  nullable persistent field. */
     private Long notePrecValNb;

     
     
     /**  persistent field. */
     private VUniteActivite uniteActivite;

    /**  persistent field. */
     private VDomaineTechnique domaineTechnique;

    /**  persistent field. */
     private VCampagne campagne;
     
    /**
     *  Empty constructor.
     */
    public VNoteRisque() {
    	super();
    }

    /**
     * Gets the note rfa.
     *
     * @return the note rfa
     */
    public String getNoteRfa() {
        return this.noteRfa;
    }

    /**
     * Sets the note rfa.
     *
     * @param noteRfa the new note rfa
     */
    public void setNoteRfa(String noteRfa) {
        this.noteRfa = noteRfa;
    }


    /**
     * Gets the note val nb.
     *
     * @return the note val nb
     */
    public Long getNoteValNb() {
        return this.noteValNb;
    }

    /**
     * Sets the note val nb.
     *
     * @param noteValNb the new note val nb
     */
    public void setNoteValNb(Long noteValNb) {
        this.noteValNb = noteValNb;
    }

    /**
     * Gets the note date calcul ds.
     *
     * @return the note date calcul ds
     */
    public Date getNoteDateCalculDs() {
        return this.noteDateCalculDs;
    }

    /**
     * Sets the note date calcul ds.
     *
     * @param noteDateCalculDs the new note date calcul ds
     */
    public void setNoteDateCalculDs(Date noteDateCalculDs) {
        this.noteDateCalculDs = noteDateCalculDs;
    }

    /**
     * Gets the note p risqu nb2.
     *
     * @return the note p risqu nb2
     */
    public BigDecimal getNotePRisquNb2() {
        return this.notePRisquNb2;
    }

    /**
     * Sets the note p risqu nb2.
     *
     * @param notePRisquNb2 the new note p risqu nb2
     */
    public void setNotePRisquNb2(BigDecimal notePRisquNb2) {
        this.notePRisquNb2 = notePRisquNb2;
    }

    /**
     * Gets the note p vol nb2.
     *
     * @return the note p vol nb2
     */
    public BigDecimal getNotePVolNb2() {
        return this.notePVolNb2;
    }

    /**
     * Sets the note p vol nb2.
     *
     * @param notePVolNb2 the new note p vol nb2
     */
    public void setNotePVolNb2(BigDecimal notePVolNb2) {
        this.notePVolNb2 = notePVolNb2;
    }

    /**
     * Gets the note p zone nb2.
     *
     * @return the note p zone nb2
     */
    public BigDecimal getNotePZoneNb2() {
        return this.notePZoneNb2;
    }

    /**
     * Sets the note p zone nb2.
     *
     * @param notePZoneNb2 the new note p zone nb2
     */
    public void setNotePZoneNb2(BigDecimal notePZoneNb2) {
        this.notePZoneNb2 = notePZoneNb2;
    }

    /**
     * Gets the note p diff nb2.
     *
     * @return the note p diff nb2
     */
    public BigDecimal getNotePDiffNb2() {
        return this.notePDiffNb2;
    }

    /**
     * Sets the note p diff nb2.
     *
     * @param notePDiffNb2 the new note p diff nb2
     */
    public void setNotePDiffNb2(BigDecimal notePDiffNb2) {
        this.notePDiffNb2 = notePDiffNb2;
    }

    /**
     * Gets the note p dest nb2.
     *
     * @return the note p dest nb2
     */
    public BigDecimal getNotePDestNb2() {
        return this.notePDestNb2;
    }

    /**
     * Sets the note p dest nb2.
     *
     * @param notePDestNb2 the new note p dest nb2
     */
    public void setNotePDestNb2(BigDecimal notePDestNb2) {
        this.notePDestNb2 = notePDestNb2;
    }

    /**
     * Gets the note p ni nb2.
     *
     * @return the note p ni nb2
     */
    public BigDecimal getNotePNiNb2() {
        return this.notePNiNb2;
    }

    /**
     * Sets the note p ni nb2.
     *
     * @param notePNiNb2 the new note p ni nb2
     */
    public void setNotePNiNb2(BigDecimal notePNiNb2) {
        this.notePNiNb2 = notePNiNb2;
    }

    /**
     * Gets the note prec val nb.
     *
     * @return the note prec val nb
     */
    public Long getNotePrecValNb() {
        return this.notePrecValNb;
    }

    /**
     * Sets the note prec val nb.
     *
     * @param notePrecValNb the new note prec val nb
     */
    public void setNotePrecValNb(Long notePrecValNb) {
        this.notePrecValNb = notePrecValNb;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(getNoteRfa());
		return toStringBuilder.toString();
}
	
	/**
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	public java.io.Serializable getIdentifier(){
		return noteRfa;
	}

	/**
	 * Get the uniteActivite
	 * @return the uniteActivite
	 */
	public VUniteActivite getUniteActivite() {
		return uniteActivite;
	}

	/**
	 * Set the uniteActivite to set
	 * @param uniteActivite the uniteActivite to set
	 */
	public void setUniteActivite(VUniteActivite uniteActivite) {
		this.uniteActivite = uniteActivite;
	}

	/**
	 * Get the domaineTechnique
	 * @return the domaineTechnique
	 */
	public VDomaineTechnique getDomaineTechnique() {
		return domaineTechnique;
	}

	/**
	 * Set the domaineTechnique to set
	 * @param domaineTechnique the domaineTechnique to set
	 */
	public void setDomaineTechnique(VDomaineTechnique domaineTechnique) {
		this.domaineTechnique = domaineTechnique;
	}

	/**
	 * Get the campagne
	 * @return the campagne
	 */
	public VCampagne getCampagne() {
		return campagne;
	}

	/**
	 * Set the campagne to set
	 * @param campagne the campagne to set
	 */
	public void setCampagne(VCampagne campagne) {
		this.campagne = campagne;
	}
}
