package fr.gouv.agriculture.dal.sial.arq.business;

import java.math.BigDecimal;
import java.util.Date;

import fr.gouv.agriculture.dal.sial.arq.helper.Formatter;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.geo.business.ReferenceSystem;
import fr.gouv.agriculture.orion.geo.geometry.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class VExportNoteRisque.
 * 
 * @author fjperez
 */
public class VExportNoteRisque extends fr.gouv.agriculture.orion.business.BaseEntity {

    /** The formatter. */
    @Inject
    private Formatter formatter;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**  Identifier. */
    private Integer rowId;
    
    /** The dt rfa export. */
    private String dtRfaExport;
    
    /** The camp rfa export. */
    private String campRfaExport;
    
    /** The note val nb export. */
    private Long noteValNbExport;
    
    /** The note p risqu nb2 export. */
    private BigDecimal notePRisquNb2Export;
    
    /** The note p vol nb2 export. */
    private BigDecimal notePVolNb2Export;
    
    /** The note p zone nb2 export. */
    private BigDecimal notePZoneNb2Export;
    
    /** The note p diff nb2 export. */
    private BigDecimal notePDiffNb2Export;
    
    /** The note p dest nb2 export. */
    private BigDecimal notePDestNb2Export;
    
    /** The note p ni nb2 export. */
    private BigDecimal notePNiNb2Export;
    
    /** The note date calcul ds export. */
    private Date noteDateCalculDsExport;
    
    /** The note prec val nb export. */
    private Long notePrecValNbExport;
    
    /** The etb rfa bdnu export. */
    private String etbRfaBdnuExport;
    
    /** The eta enseigne usuel lb export. */
    private String etaEnseigneUsuelLbExport;
    
    /** The classe rfa export. */
    private String classeRfaExport;
    
    /** The insee export. */
    private String inseeExport;
    
    /** The nomofficiel export. */
    private String nomofficielExport;
    
    /** The iden valeur export. */
    private String idenValeurExport;
    
    /** Identifiant EDE de l'UA. */
    private String identifiantEdeExport;
    
    /** The iden principal bo export. */
    private Boolean idenPrincipalBoExport;
    
    /** The geo localisation export. */
    @ReferenceSystem(srid = "EPSG:4326")
    private Point geoLocalisationExport;
    
    /** The ta lb export. */
    private String taLbExport;
    
    /** The identifiants etab export. */
    private String identifiantsEtabExport;

    /**
     * Retourne rowId.
     *
     * @return la valeur de rowId
     */
    public Integer getRowId() {
        return rowId;
    }

    /**
     * Initialise rowId.
     *
     * @param rowId            la valeur rowId à initialiser
     */
    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    /**
     * Retourne dtRfaExport.
     *
     * @return la valeur de dtRfaExport
     */
    public String getDtRfaExport() {
        return dtRfaExport;
    }

    /**
     * Initialise dtRfaExport.
     *
     * @param dtRfaExport            la valeur dtRfaExport à initialiser
     */
    public void setDtRfaExport(String dtRfaExport) {
        this.dtRfaExport = dtRfaExport;
    }

    /**
     * Retourne campRrfaExport.
     *
     * @return la valeur de campRrfaExport
     */
    public String getCampRfaExport() {
        return campRfaExport;
    }

    /**
     * Initialise campRrfaExport.
     *
     * @param campRfaExport            la valeur campRrfaExport à initialiser
     */
    public void setCampRfaExport(String campRfaExport) {
        this.campRfaExport = campRfaExport;
    }

    /**
     * Retourne noteValNbExport.
     *
     * @return la valeur de noteValNbExport
     */
    public Long getNoteValNbExport() {
        return noteValNbExport;
    }

    /**
     * Initialise noteValNbExport.
     *
     * @param noteValNbExport            la valeur noteValNbExport à initialiser
     */
    public void setNoteValNbExport(Long noteValNbExport) {
        this.noteValNbExport = noteValNbExport;
    }

    /**
     * Retourne notePRisquNb2Export.
     *
     * @return la valeur de notePRisquNb2Export
     */
    public BigDecimal getNotePRisquNb2Export() {
        return notePRisquNb2Export;
    }

    /**
     * Initialise notePRisquNb2Export.
     *
     * @param notePRisquNb2Export            la valeur notePRisquNb2Export à initialiser
     */
    public void setNotePRisquNb2Export(BigDecimal notePRisquNb2Export) {
        this.notePRisquNb2Export = notePRisquNb2Export;
    }

    /**
     * Retourne notePVolNb2Export.
     *
     * @return la valeur de notePVolNb2Export
     */
    public BigDecimal getNotePVolNb2Export() {
        return notePVolNb2Export;
    }

    /**
     * Initialise notePVolNb2Export.
     *
     * @param notePVolNb2Export            la valeur notePVolNb2Export à initialiser
     */
    public void setNotePVolNb2Export(BigDecimal notePVolNb2Export) {
        this.notePVolNb2Export = notePVolNb2Export;
    }

    /**
     * Retourne notePZoneNb2Export.
     *
     * @return la valeur de notePZoneNb2Export
     */
    public BigDecimal getNotePZoneNb2Export() {
        return notePZoneNb2Export;
    }

    /**
     * Initialise notePZoneNb2Export.
     *
     * @param notePZoneNb2Export            la valeur notePZoneNb2Export à initialiser
     */
    public void setNotePZoneNb2Export(BigDecimal notePZoneNb2Export) {
        this.notePZoneNb2Export = notePZoneNb2Export;
    }

    /**
     * Retourne notePDiffNb2Export.
     *
     * @return la valeur de notePDiffNb2Export
     */
    public BigDecimal getNotePDiffNb2Export() {
        return notePDiffNb2Export;
    }

    /**
     * Initialise notePDiffNb2Export.
     *
     * @param notePDiffNb2Export            la valeur notePDiffNb2Export à initialiser
     */
    public void setNotePDiffNb2Export(BigDecimal notePDiffNb2Export) {
        this.notePDiffNb2Export = notePDiffNb2Export;
    }

    /**
     * Retourne notePDestNb2Export.
     *
     * @return la valeur de notePDestNb2Export
     */
    public BigDecimal getNotePDestNb2Export() {
        return notePDestNb2Export;
    }

    /**
     * Initialise notePDestNb2Export.
     *
     * @param notePDestNb2Export            la valeur notePDestNb2Export à initialiser
     */
    public void setNotePDestNb2Export(BigDecimal notePDestNb2Export) {
        this.notePDestNb2Export = notePDestNb2Export;
    }

    /**
     * Retourne notePNiNb2Export.
     *
     * @return la valeur de notePNiNb2Export
     */
    public BigDecimal getNotePNiNb2Export() {
        return notePNiNb2Export;
    }

    /**
     * Initialise notePNiNb2Export.
     *
     * @param notePNiNb2Export            la valeur notePNiNb2Export à initialiser
     */
    public void setNotePNiNb2Export(BigDecimal notePNiNb2Export) {
        this.notePNiNb2Export = notePNiNb2Export;
    }

    /**
     * Retourne noteDateCalculDsExport.
     *
     * @return la valeur de noteDateCalculDsExport
     */
    public Date getNoteDateCalculDsExport() {
        return noteDateCalculDsExport;
    }

    /**
     * Retourne la noteDateCalculDsExport formatter.
     *
     * @return la valeur de noteDateCalculDsExport au format JJ/MM/AAAA HH:MM:SS
     */
    public String getNoteDateCalculDsExportFormated() {
        return formatter.dateTime2String(getNoteDateCalculDsExport());
    }

    /**
     * Initialise noteDateCalculDsExport.
     *
     * @param noteDateCalculDsExport            la valeur noteDateCalculDsExport à initialiser
     */
    public void setNoteDateCalculDsExport(Date noteDateCalculDsExport) {
        this.noteDateCalculDsExport = noteDateCalculDsExport;
    }

    /**
     * Retourne notePrecValNbExport.
     *
     * @return la valeur de notePrecValNbExport
     */
    public Long getNotePrecValNbExport() {
        return notePrecValNbExport;
    }

    /**
     * Initialise notePrecValNbExport.
     *
     * @param notePrecValNbExport            la valeur notePrecValNbExport à initialiser
     */
    public void setNotePrecValNbExport(Long notePrecValNbExport) {
        this.notePrecValNbExport = notePrecValNbExport;
    }

    /**
     * Retourne etbRfaBdnuExport.
     *
     * @return la valeur de etbRfaBdnuExport
     */
    public String getEtbRfaBdnuExport() {
        return etbRfaBdnuExport;
    }

    /**
     * Initialise etbRfaBdnuExport.
     *
     * @param etbRfaBdnuExport            la valeur etbRfaBdnuExport à initialiser
     */
    public void setEtbRfaBdnuExport(String etbRfaBdnuExport) {
        this.etbRfaBdnuExport = etbRfaBdnuExport;
    }

    /**
     * Retourne etaEnseigneUsuelLbExport.
     *
     * @return la valeur de etaEnseigneUsuelLbExport
     */
    public String getEtaEnseigneUsuelLbExport() {
        return etaEnseigneUsuelLbExport;
    }

    /**
     * Initialise etaEnseigneUsuelLbExport.
     *
     * @param etaEnseigneUsuelLbExport            la valeur etaEnseigneUsuelLbExport à initialiser
     */
    public void setEtaEnseigneUsuelLbExport(String etaEnseigneUsuelLbExport) {
        this.etaEnseigneUsuelLbExport = etaEnseigneUsuelLbExport;
    }

    /**
     * Retourne classeRfaExport.
     *
     * @return la valeur de classeRfaExport
     */
    public String getClasseRfaExport() {
        return classeRfaExport;
    }

    /**
     * Initialise classeRfaExport.
     *
     * @param classeRfaExport            la valeur classeRfaExport à initialiser
     */
    public void setClasseRfaExport(String classeRfaExport) {
        this.classeRfaExport = classeRfaExport;
    }

    /**
     * Retourne inseeExport.
     *
     * @return la valeur de inseeExport
     */
    public String getInseeExport() {
        return inseeExport;
    }

    /**
     * Initialise inseeExport.
     *
     * @param inseeExport            la valeur inseeExport à initialiser
     */
    public void setInseeExport(String inseeExport) {
        this.inseeExport = inseeExport;
    }

    /**
     * Retourne nomofficielExport.
     *
     * @return la valeur de nomofficielExport
     */
    public String getNomofficielExport() {
        return nomofficielExport;
    }

    /**
     * Initialise nomofficielExport.
     *
     * @param nomofficielExport            la valeur nomofficielExport à initialiser
     */
    public void setNomofficielExport(String nomofficielExport) {
        this.nomofficielExport = nomofficielExport;
    }

    /**
     * Retourne idenValeurExport.
     *
     * @return la valeur de idenValeurExport
     */
    public String getIdenValeurExport() {
        return idenValeurExport;
    }

    /**
     * Initialise idenValeurExport.
     *
     * @param idenValeurExport            la valeur idenValeurExport à initialiser
     */
    public void setIdenValeurExport(String idenValeurExport) {
        this.idenValeurExport = idenValeurExport;
    }

    /**
     * Retourne idenPrincipalBoExport.
     *
     * @return la valeur de idenPrincipalBoExport
     */
    public Boolean getIdenPrincipalBoExport() {
        return idenPrincipalBoExport;
    }

    /**
     * Initialise idenPrincipalBoExport.
     *
     * @param idenPrincipalBoExport            la valeur idenPrincipalBoExport à initialiser
     */
    public void setIdenPrincipalBoExport(Boolean idenPrincipalBoExport) {
        this.idenPrincipalBoExport = idenPrincipalBoExport;
    }

    /**
     * Retourne geoLocalisationExport.
     *
     * @return la valeur de geoLocalisationExport
     */
    public Point getGeoLocalisationExport() {
        return geoLocalisationExport;
    }

    /**
     * Initialise geoLocalisationExport.
     *
     * @param geoLocalisationExport            la valeur geoLocalisationExport à initialiser
     */
    public void setGeoLocalisationExport(Point geoLocalisationExport) {
        this.geoLocalisationExport = geoLocalisationExport;
    }

    /**
     * Retourne taLbExport.
     *
     * @return la valeur de taLbExport
     */
    public String getTaLbExport() {
        return taLbExport;
    }

    /**
     * Initialise taLbExport.
     *
     * @param taLbExport            la valeur taLbExport à initialiser
     */
    public void setTaLbExport(String taLbExport) {
        this.taLbExport = taLbExport;
    }

    /**
     * Retourne identifiantsEtabExport.
     *
     * @return la valeur de identifiantsEtabExport
     */
    public String getIdentifiantsEtabExport() {
        return identifiantsEtabExport;
    }

    /**
     * Initialise identifiantsEtabExport.
     *
     * @param identifiantsEtabExport            la valeur identifiantsEtabExport à initialiser
     */
    public void setIdentifiantsEtabExport(String identifiantsEtabExport) {
        this.identifiantsEtabExport = identifiantsEtabExport;
    }

    /**
	 * @return the identifiantEdeExport
	 */
	public String getIdentifiantEdeExport() {
		return identifiantEdeExport;
	}

	/**
	 * @param identifiantEdeExport the identifiantEdeExport to set
	 */
	public void setIdentifiantEdeExport(String identifiantEdeExport) {
		this.identifiantEdeExport = identifiantEdeExport;
	}

	/*
     * (non-Javadoc)
     * 
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    @Override
    public java.io.Serializable getIdentifier() {
        return getRowId();
    }

}
