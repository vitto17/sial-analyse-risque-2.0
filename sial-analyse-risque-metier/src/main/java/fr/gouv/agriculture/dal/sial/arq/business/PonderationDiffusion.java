package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDiffusion;
import java.math.BigDecimal;

import fr.gouv.agriculture.orion.business.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class PonderationDiffusion.
 *
 * @author Hibernate CodeGenerator Renderer:
 * fr.gouv.agriculture.orion.automation.persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class PonderationDiffusion extends BaseEntity implements PonderationARQ<PonderationDiffusion>, IGenericList {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** identifier field. */
    private Integer pdiffId;
    
    /** persistent field. */
    private VDiffusion difRfa;
    
    /** persistent field. */
    private BigDecimal pdiffPoidsNb2;
    
    /** persistent field. */
    private fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque formuleRisque;

    /**
     * Empty constructor.
     */
    public PonderationDiffusion() {
        super();
    }

    /**
     * Constructeur pour dupliquer une PonderationDiffusion.
     *
     * @param ponderationDiffusion the ponderation diffusion
     */
    public PonderationDiffusion(PonderationDiffusion ponderationDiffusion) {
        super();

        if (ponderationDiffusion != null) {
            difRfa = ponderationDiffusion.getDifRfa();
            pdiffPoidsNb2 = ponderationDiffusion.getPdiffPoidsNb2();
            formuleRisque = ponderationDiffusion.getFormuleRisque();
        }

    }

    /**
     * Gets the pdiff id.
     *
     * @return the pdiff id
     */
    public Integer getPdiffId() {
        return this.pdiffId;
    }

    /**
     * Sets the pdiff id.
     *
     * @param pdiffId the new pdiff id
     */
    public void setPdiffId(Integer pdiffId) {
        this.pdiffId = pdiffId;
    }

    /**
     * Gets the dif rfa.
     *
     * @return the dif rfa
     */
    public VDiffusion getDifRfa() {
        return difRfa;
    }

    /**
     * Sets the dif rfa.
     *
     * @param difRfa the new dif rfa
     */
    public void setDifRfa(VDiffusion difRfa) {
        this.difRfa = difRfa;
    }

    /**
     * Gets the pdiff poids nb2.
     *
     * @return the pdiff poids nb2
     */
    public BigDecimal getPdiffPoidsNb2() {
        return this.pdiffPoidsNb2;
    }

    /**
     * Sets the pdiff poids nb2.
     *
     * @param pdiffPoidsNb2 the new pdiff poids nb2
     */
    public void setPdiffPoidsNb2(BigDecimal pdiffPoidsNb2) {
        this.pdiffPoidsNb2 = pdiffPoidsNb2;
    }

    /**
     * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ#getFormuleRisque()
     */
    public fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque getFormuleRisque() {
        return this.formuleRisque;
    }

    /**
     * Sets the formule risque.
     *
     * @param formuleRisque the new formule risque
     */
    public void setFormuleRisque(fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque formuleRisque) {
        this.formuleRisque = formuleRisque;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append(getPdiffId());
        return toStringBuilder.toString();
    }

    /**
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    public java.io.Serializable getIdentifier() {
        return pdiffId;
    }

    /**
     * @see fr.gouv.agriculture.orion.business.BaseEntity#breakAssociations()
     */
    @Override
    public void breakAssociations() {

        super.breakAssociations();
        formuleRisque.getPonderationDiffusions().remove(this);
    }
    
    /**
     * Comparaison des ponderation, retourne 0 si egal 1 sinon.
     *
     * @param object the object
     * @return 0 si egal 1 sinon
     */
    @Override
    public int compareTo(PonderationDiffusion object) {
        int result = COMPARE_OK;

        if (object != null) {
            if (difRfa != null && object.getDifRfa() != null) {
                if (!difRfa.getIdentifier().equals(
                        object.getDifRfa().getIdentifier())) {
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
    public PonderationDiffusion duplicate() {
        return new PonderationDiffusion(this);
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getStringTriSur1Colonne()
     */
    @Override
    public String getStringTriSur1Colonne() {
        String retour = null;
        if (difRfa!=null) {
            retour = difRfa.getDifLb();
        }
        return retour;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getFirstStringTriSur2Colonne()
     */
    @Override
    public String getFirstStringTriSur2Colonne() {
        return null;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.IGenericList#getSecondStringTriSur2Colonne()
     */
    @Override
    public String getSecondStringTriSur2Colonne() {
        return null;
    }
}
