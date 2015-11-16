package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VUniteProduction;
import java.math.BigDecimal;

import fr.gouv.agriculture.orion.business.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class PonderationVolume.
 *
 * @author Hibernate CodeGenerator Renderer:
 * fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class PonderationVolume extends BaseEntity implements PonderationARQ<PonderationVolume>, IGenericList {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** identifier field. */
    private Integer pvolId;
    
    /** persistent field. */
    private VTypeActivite taRfa;
    
    /** persistent field. */
    private VUniteProduction uprodRfa;
    
    /** persistent field. */
    private BigDecimal pvolS1Nb2;
    
    /** nullable persistent field. */
    private BigDecimal pvolS2Nb2;
    
    /** nullable persistent field. */
    private BigDecimal pvolS3Nb2;
    
    /** nullable persistent field. */
    private BigDecimal pvolS4Nb2;
    
    /** persistent field. */
    private FormuleRisque formuleRisque;

    /**
     * Empty constructor.
     */
    public PonderationVolume() {
        super();
    }

    /**
     * Constructeur pour dupliquer une PonderationVolume.
     *
     * @param ponderationVolume the ponderation volume
     */
    public PonderationVolume(PonderationVolume ponderationVolume) {
        super();

        if (ponderationVolume != null) {
            taRfa = ponderationVolume.getTaRfa();
            uprodRfa = ponderationVolume.getUprodRfa();
            pvolS1Nb2 = ponderationVolume.getPvolS1Nb2();
            pvolS2Nb2 = ponderationVolume.getPvolS2Nb2();
            pvolS3Nb2 = ponderationVolume.getPvolS3Nb2();
            pvolS4Nb2 = ponderationVolume.getPvolS4Nb2();
            formuleRisque = ponderationVolume.getFormuleRisque();
        }
    }

    /**
     * Gets the pvol id.
     *
     * @return the pvol id
     */
    public Integer getPvolId() {
        return this.pvolId;
    }

    /**
     * Sets the pvol id.
     *
     * @param pvolId the new pvol id
     */
    public void setPvolId(Integer pvolId) {
        this.pvolId = pvolId;
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
     * Gets the uprod rfa.
     *
     * @return the uprod rfa
     */
    public VUniteProduction getUprodRfa() {
        return uprodRfa;
    }

    /**
     * Sets the uprod rfa.
     *
     * @param uprodRfa the new uprod rfa
     */
    public void setUprodRfa(VUniteProduction uprodRfa) {
        this.uprodRfa = uprodRfa;
    }

    /**
     * Gets the pvol s1 nb2.
     *
     * @return the pvol s1 nb2
     */
    public BigDecimal getPvolS1Nb2() {
        return this.pvolS1Nb2;
    }

    /**
     * Sets the pvol s1 nb2.
     *
     * @param pvolS1Nb2 the new pvol s1 nb2
     */
    public void setPvolS1Nb2(BigDecimal pvolS1Nb2) {
        this.pvolS1Nb2 = pvolS1Nb2;
    }

    /**
     * Gets the pvol s2 nb2.
     *
     * @return the pvol s2 nb2
     */
    public BigDecimal getPvolS2Nb2() {
        return this.pvolS2Nb2;
    }

    /**
     * Sets the pvol s2 nb2.
     *
     * @param pvolS2Nb2 the new pvol s2 nb2
     */
    public void setPvolS2Nb2(BigDecimal pvolS2Nb2) {
        this.pvolS2Nb2 = pvolS2Nb2;
    }

    /**
     * Gets the pvol s3 nb2.
     *
     * @return the pvol s3 nb2
     */
    public BigDecimal getPvolS3Nb2() {
        return this.pvolS3Nb2;
    }

    /**
     * Sets the pvol s3 nb2.
     *
     * @param pvolS3Nb2 the new pvol s3 nb2
     */
    public void setPvolS3Nb2(BigDecimal pvolS3Nb2) {
        this.pvolS3Nb2 = pvolS3Nb2;
    }

    /**
     * Gets the pvol s4 nb2.
     *
     * @return the pvol s4 nb2
     */
    public BigDecimal getPvolS4Nb2() {
        return this.pvolS4Nb2;
    }

    /**
     * Sets the pvol s4 nb2.
     *
     * @param pvolS4Nb2 the new pvol s4 nb2
     */
    public void setPvolS4Nb2(BigDecimal pvolS4Nb2) {
        this.pvolS4Nb2 = pvolS4Nb2;
    }


    /**
     * Retourne la Formule de Risque
     * @return Formule de Risque
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
        toStringBuilder.append(getPvolId());
        return toStringBuilder.toString();
    }

    /***
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    public java.io.Serializable getIdentifier() {
        return pvolId;
    }

    /**
     * @see fr.gouv.agriculture.orion.business.BaseEntity#breakAssociations()
     */
    @Override
    public void breakAssociations() {

        super.breakAssociations();
        formuleRisque.getPonderationVolumes().remove(this);
    }
        
    /**
     * Comparaison des ponderation, retourne 0 si egal 1 sinon.
     *
     * @param object the object
     * @return 0 si egal 1 sinon
     */
    @Override
    public int compareTo(PonderationVolume object) {
        int result = COMPARE_OK;

        if (object != null) {
            if (uprodRfa != null && object.getUprodRfa() != null) {
                if (!uprodRfa.getIdentifier().equals(
                        object.getUprodRfa().getIdentifier())) {
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
     * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ#duplicate()
     */
    @Override
    public PonderationVolume duplicate() {
        return new PonderationVolume(this);
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getStringTriSur1Colonne()
     */
    @Override
    public String getStringTriSur1Colonne() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        if (uprodRfa!=null) {
            retour = uprodRfa.getUprodLb();
        }
        return retour;
    }
}
