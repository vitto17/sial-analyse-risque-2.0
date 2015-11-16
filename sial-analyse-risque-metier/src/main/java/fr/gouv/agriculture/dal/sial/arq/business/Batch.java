package fr.gouv.agriculture.dal.sial.arq.business;

import java.util.Date;

import fr.gouv.agriculture.dal.sial.arq.helper.Formatter;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;


// TODO: Auto-generated Javadoc
/**
 * The Class Batch.
 *
 * @author Hibernate CodeGenerator Renderer: fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class Batch extends fr.gouv.agriculture.orion.business.BaseEntity {
    
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The formatter. */
    @Inject
    private Formatter formatter;
    
    /**  identifier field. */
    private Integer batchId;
    
    /**  persistent field. */
    private String batchUtiLb;
    
    /**  persistent field. */
    private Date batchDemandeTs;
    
    /**  nullable persistent field. */
    private Date batchDebutTs;
    
    /**  nullable persistent field. */
    private Date batchFinTs;
    
    /**  nullable persistent field. */
    private Long batchNbrUaTotalNb;
    
    /**  nullable persistent field. */
    private Long batchNbrUaTraiteNb;
    
    /**  persistent field. */
    private String campRfa;
    
	/**
	 * Liste des codes RFA de structures. Champ persistant.
	 */
	private String batchListStructRfa;

    /**  persistent field. */
    private VDomaineTechnique domaineTechnique;
    
    /**  persistent field. */
    private fr.gouv.agriculture.dal.sial.arq.business.Statut statut;
    
    /**
     *  Empty constructor.
     */
    public Batch() {
        super();
    }
    
    /**
     * Gets the batch id.
     *
     * @return the batch id
     */
    public Integer getBatchId() {
        return this.batchId;
    }
    
    /**
     * Sets the batch id.
     *
     * @param batchId the new batch id
     */
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    
    /**
     * Gets the batch uti lb.
     *
     * @return the batch uti lb
     */
    public String getBatchUtiLb() {
        return this.batchUtiLb;
    }
    
    /**
     * Sets the batch uti lb.
     *
     * @param batchUtiLb the new batch uti lb
     */
    public void setBatchUtiLb(String batchUtiLb) {
        this.batchUtiLb = batchUtiLb;
    }
    
    /**
     * Gets the batch demande ts.
     *
     * @return the batch demande ts
     */
    public Date getBatchDemandeTs() {
        return this.batchDemandeTs;
    }
    
    /**
     * Sets the batch demande ts.
     *
     * @param batchDemandeTs the new batch demande ts
     */
    public void setBatchDemandeTs(Date batchDemandeTs) {
        this.batchDemandeTs = batchDemandeTs;
    }
    
    /**
     * Gets the batch debut ts.
     *
     * @return the batch debut ts
     */
    public Date getBatchDebutTs() {
        return this.batchDebutTs;
    }
    
    /**
     * Sets the batch debut ts.
     *
     * @param batchDebutTs the new batch debut ts
     */
    public void setBatchDebutTs(Date batchDebutTs) {
        this.batchDebutTs = batchDebutTs;
    }
    
    /**
     * Gets the batch debut ts str.
     *
     * @return the batch debut ts str
     */
    public String  getBatchDebutTsStr() {
        return formatter.dateTime2String(getBatchDebutTs());
    }
    
    /**
     * Gets the batch fin ts.
     *
     * @return the batch fin ts
     */
    public Date getBatchFinTs() {
        return this.batchFinTs;
    }
    
    /**
     * Sets the batch fin ts.
     *
     * @param batchFinTs the new batch fin ts
     */
    public void setBatchFinTs(Date batchFinTs) {
        this.batchFinTs = batchFinTs;
    }
    
    /**
     * Gets the batch nbr ua total nb.
     *
     * @return the batch nbr ua total nb
     */
    public Long getBatchNbrUaTotalNb() {
        return this.batchNbrUaTotalNb;
    }
    
    /**
     * Sets the batch nbr ua total nb.
     *
     * @param batchNbrUaTotalNb the new batch nbr ua total nb
     */
    public void setBatchNbrUaTotalNb(Long batchNbrUaTotalNb) {
        this.batchNbrUaTotalNb = batchNbrUaTotalNb;
    }
    
    /**
     * Gets the batch nbr ua traite nb.
     *
     * @return the batch nbr ua traite nb
     */
    public Long getBatchNbrUaTraiteNb() {
        return this.batchNbrUaTraiteNb;
    }
    
    /**
     * Sets the batch nbr ua traite nb.
     *
     * @param batchNbrUaTraiteNb the new batch nbr ua traite nb
     */
    public void setBatchNbrUaTraiteNb(Long batchNbrUaTraiteNb) {
        this.batchNbrUaTraiteNb = batchNbrUaTraiteNb;
    }
    
    /**
     * Gets the camp rfa.
     *
     * @return the camp rfa
     */
    public String getCampRfa() {
        return this.campRfa;
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
	 * Get la liste des codes rfa de structures.
	 * 
	 * @return La liste des codes rfa de structures
	 */
	public String getBatchListStructRfa() {
		return batchListStructRfa;
	}

	/**
	 * Set la liste des codes rfa de structures.
	 * 
	 * @param batchListStructRfa
	 *            La liste des codes rfa de structures.
	 */
	public void setBatchListStructRfa(String batchListStructRfa) {
		this.batchListStructRfa = batchListStructRfa;
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
     * Gets the statut.
     *
     * @return the statut
     */
    public fr.gouv.agriculture.dal.sial.arq.business.Statut getStatut() {
        return this.statut;
    }
    
    /**
     * Sets the statut.
     *
     * @param statut the new statut
     */
    public void setStatut(fr.gouv.agriculture.dal.sial.arq.business.Statut statut) {
        this.statut = statut;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append(getBatchId());
        return toStringBuilder.toString();
    }
    
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    @Override
	public java.io.Serializable getIdentifier(){
        return batchId;
    }
}
