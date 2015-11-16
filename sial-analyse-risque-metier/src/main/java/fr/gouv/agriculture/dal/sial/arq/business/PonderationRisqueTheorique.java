package fr.gouv.agriculture.dal.sial.arq.business;

import fr.gouv.agriculture.dal.sial.arq.business.comparator.PonderationRiThLibelleSLComparator;
import fr.gouv.agriculture.dal.sial.arq.business.rule.PonderationRisqueTheoriqueRule;
import fr.gouv.agriculture.dal.sial.arq.constante.TypeProduit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDenree;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VFiliereVegetal;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VProcede;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VRefApprobation;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PonderationRisqueTheorique.
 *
 * @author Hibernate CodeGenerator Renderer:
 * fr.gouv.agriculture.orion.automation
 * .persistence.hibernate.hbm2java.OrionRenderer
 * @version $Id: OrionRenderer.java 20604 2011-12-12 16:40:25Z olivier.schmitt $
 */
public class PonderationRisqueTheorique extends fr.gouv.agriculture.orion.business.BaseEntity implements
        PonderationARQ<PonderationRisqueTheorique> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The Constant STRING_PLUS. */
    private static final String STRING_PLUS = " + ";
    
    /** identifier field. */
    private Integer prisqtheoId;
    
    /** persistent field. */
    private BigDecimal prisqtheoPoidsNb2;
    
    /** persistent field. */
    private VTypeActivite typeActivite;
    
    /** persistent field. */
    private fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque formuleRisque;
    
    /** persistent field. */
    private List<PonderationRisqueTheoriqueApprobation> ponderationRisqueTheoriqueApprobations;
    
    /** persistent field. */
    private List<PonderationRisqueTheoriqueProcede> ponderationRisqueTheoriqueProcedes;
    
    /** persistent field. */
    private List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits;
   
    /**
     * Constructeur pour dupliquer une PonderationRisqueTheorique.
     *
     * @param ponderationRisqueTheorique the ponderation risque theorique
     */
    public PonderationRisqueTheorique(PonderationRisqueTheorique ponderationRisqueTheorique) {
        super();

        if (ponderationRisqueTheorique != null) {
            this.prisqtheoId = ponderationRisqueTheorique.getPrisqtheoId();
            this.typeActivite = ponderationRisqueTheorique.getTypeActivite();
            this.prisqtheoPoidsNb2 = ponderationRisqueTheorique.getPrisqtheoPoidsNb2();

            ponderationRisqueTheoriqueApprobations = new ArrayList<PonderationRisqueTheoriqueApprobation>();
            List<PonderationRisqueTheoriqueApprobation> oldApprobations = ponderationRisqueTheorique
                    .getPonderationRisqueTheoriqueApprobations();
            if (!CommonHelper.isEmpty(oldApprobations)) {
                for (PonderationRisqueTheoriqueApprobation approbation : oldApprobations) {
                    PonderationRisqueTheoriqueApprobation newApprobation = new PonderationRisqueTheoriqueApprobation(
                            approbation);
                    newApprobation.setPrisqtheoRapprId(null);
                    newApprobation.setPonderationRisqueTheorique(this);
                    ponderationRisqueTheoriqueApprobations.add(newApprobation);
                }
            }

            ponderationRisqueTheoriqueProcedes = new ArrayList<PonderationRisqueTheoriqueProcede>();
            List<PonderationRisqueTheoriqueProcede> oldProcedes = ponderationRisqueTheorique
                    .getPonderationRisqueTheoriqueProcedes();
            if (!CommonHelper.isEmpty(oldProcedes)) {
                for (PonderationRisqueTheoriqueProcede procede : oldProcedes) {
                    PonderationRisqueTheoriqueProcede newProcede = new PonderationRisqueTheoriqueProcede(procede);
                    newProcede.setPrisqtheoProcId(null);
                    newProcede.setPonderationRisqueTheorique(this);
                    ponderationRisqueTheoriqueProcedes.add(newProcede);
                }
            }

            ponderationRisqueTheoriqueProduits = new ArrayList<PonderationRisqueTheoriqueProduit>();
            List<PonderationRisqueTheoriqueProduit> oldProduits = ponderationRisqueTheorique
                    .getPonderationRisqueTheoriqueProduits();
            if (!CommonHelper.isEmpty(oldProduits)) {
                for (PonderationRisqueTheoriqueProduit produit : oldProduits) {
                    PonderationRisqueTheoriqueProduit newProduit = new PonderationRisqueTheoriqueProduit(produit);
                    newProduit.setPrisqtheoProdId(null);
                    newProduit.setPonderationRisqueTheorique(this);
                    ponderationRisqueTheoriqueProduits.add(newProduit);
                }
            }
        }
    }

    /**
     * Empty constructor.
     */
    public PonderationRisqueTheorique() {
        super();
    }

    /**
     * Gets the prisqtheo id.
     *
     * @return the prisqtheo id
     */
    public Integer getPrisqtheoId() {
        return this.prisqtheoId;
    }

    /**
     * Sets the prisqtheo id.
     *
     * @param prisqtheoId the new prisqtheo id
     */
    public void setPrisqtheoId(Integer prisqtheoId) {
        this.prisqtheoId = prisqtheoId;
    }

    /**
     * Gets the prisqtheo poids nb2.
     *
     * @return the prisqtheo poids nb2
     */
    public BigDecimal getPrisqtheoPoidsNb2() {
        return this.prisqtheoPoidsNb2;
    }

    /**
     * Sets the prisqtheo poids nb2.
     *
     * @param prisqtheoPoidsNb2 the new prisqtheo poids nb2
     */
    public void setPrisqtheoPoidsNb2(BigDecimal prisqtheoPoidsNb2) {
        this.prisqtheoPoidsNb2 = prisqtheoPoidsNb2;
    }

    /**
     * Gets the type activite.
     *
     * @return the type activite
     */
    public VTypeActivite getTypeActivite() {
        return this.typeActivite;
    }

    /**
     * Sets the type activite.
     *
     * @param typeActivite the new type activite
     */
    public void setTypeActivite(VTypeActivite typeActivite) {
        this.typeActivite = typeActivite;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.business.PonderationARQ#getFormuleRisque()
     */
    @Override
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
     * Gets the ponderation risque theorique approbations.
     *
     * @return the ponderation risque theorique approbations
     */
    public List<PonderationRisqueTheoriqueApprobation> getPonderationRisqueTheoriqueApprobations() {
        return this.ponderationRisqueTheoriqueApprobations;
    }

    /**
     * Sets the ponderation risque theorique approbations.
     *
     * @param ponderationRisqueTheoriqueApprobations the new ponderation risque theorique approbations
     */
    public void setPonderationRisqueTheoriqueApprobations(
            List<PonderationRisqueTheoriqueApprobation> ponderationRisqueTheoriqueApprobations) {
        this.ponderationRisqueTheoriqueApprobations = ponderationRisqueTheoriqueApprobations;
    }

    /**
     * Gets the ponderation risque theorique procedes.
     *
     * @return the ponderation risque theorique procedes
     */
    public List<PonderationRisqueTheoriqueProcede> getPonderationRisqueTheoriqueProcedes() {
        return this.ponderationRisqueTheoriqueProcedes;
    }

    /**
     * Sets the ponderation risque theorique procedes.
     *
     * @param ponderationRisqueTheoriqueProcedes the new ponderation risque theorique procedes
     */
    public void setPonderationRisqueTheoriqueProcedes(
            List<PonderationRisqueTheoriqueProcede> ponderationRisqueTheoriqueProcedes) {
        this.ponderationRisqueTheoriqueProcedes = ponderationRisqueTheoriqueProcedes;
    }

    /**
     * Gets the ponderation risque theorique produits.
     *
     * @return the ponderation risque theorique produits
     */
    public List<PonderationRisqueTheoriqueProduit> getPonderationRisqueTheoriqueProduits() {
        return this.ponderationRisqueTheoriqueProduits;
    }

    /**
     * Sets the ponderation risque theorique produits.
     *
     * @param ponderationRisqueTheoriqueProduits the new ponderation risque theorique produits
     */
    public void setPonderationRisqueTheoriqueProduits(
            List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits) {
        this.ponderationRisqueTheoriqueProduits = ponderationRisqueTheoriqueProduits;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append(getPrisqtheoId());
        return toStringBuilder.toString();
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
     */
    @Override
    public java.io.Serializable getIdentifier() {
        return prisqtheoId;
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.orion.business.BaseEntity#breakAssociations()
     */
    @Override
    public void breakAssociations() {

        super.breakAssociations();
        formuleRisque.getPonderationRisqueTheoriques().remove(this);
    }

    /* (non-Javadoc)
     * @see fr.gouv.agriculture.orion.business.BaseEntity#buildRules()
     */
    @Override
    public void buildRules() {
        super.buildRules();
        addRule(new PonderationRisqueTheoriqueRule());
    }

    /**
     * Comparaison des ponderation, retourne 0 si egal 1 sinon.
     *
     * @param object the object
     * @return 0 si egal 1 sinon
     */
    @Override
    public int compareTo(PonderationRisqueTheorique object) {
        int result = COMPARE_OK;

        if (object != null) {
            if (typeActivite != null && object.getTypeActivite() != null) {
                if (!typeActivite.getIdentifier().equals(
                        object.getTypeActivite().getIdentifier())) {
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
    public PonderationRisqueTheorique duplicate() {
        PonderationRisqueTheorique risque = new PonderationRisqueTheorique(this);
        risque.setPrisqtheoId(null);
        risque.setFormuleRisque(getFormuleRisque());
        return risque;
    }

    /**
     * Méthode pour avoir la liste des approbations, avec " + " entre chaque
     * libellé.
     *
     * @return String
     */
    public String getPonderationRisqueTheoriqueApprobationsStr() {
        StringBuilder toReturn = new StringBuilder();
        
        if (ponderationRisqueTheoriqueApprobations != null) {
            Collections.sort(ponderationRisqueTheoriqueApprobations, new PonderationRiThLibelleSLComparator());
            int size = ponderationRisqueTheoriqueApprobations.size();
            int inc=0;
            for (Iterator<PonderationRisqueTheoriqueApprobation> it = ponderationRisqueTheoriqueApprobations.iterator(); it.hasNext();) {
                PonderationRisqueTheoriqueApprobation ponderationRisqueTheoriqueApprobation = it.next();
                String libelle = "unknow";
                if (ponderationRisqueTheoriqueApprobation.getApprobation() != null) {
                    libelle = ponderationRisqueTheoriqueApprobation.getApprobation().getRapprLb();
                }
                toReturn.append(libelle);
                if (inc<size-1 && size>1) {
                    toReturn.append(STRING_PLUS);
                }
                inc++;
            }
        }

        return toReturn.toString();
    }

    /**
     * Méthode pour avoir la liste des procédés, avec " + " entre chaque libellé.
     *
     * @return String
     */
    public String getPonderationRisqueTheoriqueProcedesStr() {
        StringBuilder toReturn = new StringBuilder();
        
        if (ponderationRisqueTheoriqueProcedes != null) {
            Collections.sort(ponderationRisqueTheoriqueProcedes, new PonderationRiThLibelleSLComparator());
            int size = ponderationRisqueTheoriqueProcedes.size();
            int inc=0;
            for (Iterator<PonderationRisqueTheoriqueProcede> it = ponderationRisqueTheoriqueProcedes.iterator(); it.hasNext();) {
                PonderationRisqueTheoriqueProcede ponderationRisqueTheoriqueProcede = it.next();
                String libelle = "unknow";
                if (ponderationRisqueTheoriqueProcede.getProcede() != null) {
                    libelle = ponderationRisqueTheoriqueProcede.getProcede().getProcLb();
                }
                toReturn.append(libelle);
                if (inc<size-1 && size>1) {
                    toReturn.append(STRING_PLUS);
                }
                inc++;
            }
        }

        return toReturn.toString();
    }

    /**
     * Méthode pour avoir la liste des procédés, avec " + " entre chaque libellé.
     *
     * @return String
     */
    public String getPonderationRisqueTheoriqueProduitsStr() {
        StringBuilder toReturn = new StringBuilder();
        
        if (ponderationRisqueTheoriqueProduits != null) {
            Collections.sort(ponderationRisqueTheoriqueProduits, new PonderationRiThLibelleSLComparator());
            int size = ponderationRisqueTheoriqueProduits.size();
            int inc=0;
            for (Iterator<PonderationRisqueTheoriqueProduit> it = ponderationRisqueTheoriqueProduits.iterator(); it.hasNext();) {
                PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit = it.next();
                String libelle = "unknow1";
                if (ponderationRisqueTheoriqueProduit.getProduit() != null) {
                    libelle = ponderationRisqueTheoriqueProduit.getProduit().getProdLbByTypeProduit();
//                    libelle = ponderationRisqueTheoriqueProduit.getProduit().getProdLb();
                }
                toReturn.append(libelle);
                if (inc<size-1 && size>1) {
                    toReturn.append(STRING_PLUS);
                }
                inc++;
            }
        }

        return toReturn.toString();
    }

    /**
     * Permet de savoir si la liste contient déjà un objet approbation.
     * Règles_Trans_006_ComportementRetourGuideMultiSelection 
     *
     * @param approbation the approbation
     * @return true si la ponderation est déjà dans la liste
     */
    public boolean isPonderationRisqueTheoriqueApprobationsContainsApprobation(VRefApprobation approbation) {
        boolean retour = false;
        for (Iterator<PonderationRisqueTheoriqueApprobation> it = ponderationRisqueTheoriqueApprobations.iterator(); it.hasNext();) {
            PonderationRisqueTheoriqueApprobation ponderationRisqueTheoriqueApprobation = it.next();
            if (ponderationRisqueTheoriqueApprobation.getApprobation().equals(approbation)) {
                retour=true;
                break;
            }
        }
        return retour;
    }

    /**
     * Permet de savoir si la liste contient déjà un objet ponderation.
     * Règles_Trans_006_ComportementRetourGuideMultiSelection 
     *
     * @param approbation the approbation
     * @return true si le procede est déjà dans la liste
     */
    public boolean isPonderationRisqueTheoriqueProcedesContainsProcede(VProcede approbation) {
        boolean retour = false;
        for (Iterator<PonderationRisqueTheoriqueProcede> it = ponderationRisqueTheoriqueProcedes.iterator(); it.hasNext();) {
                PonderationRisqueTheoriqueProcede ponderationRisqueTheoriqueProcede = it.next();
                if (ponderationRisqueTheoriqueProcede.getProcede().equals(approbation)) {
                    retour=true;
                    break;
                }
        }
        return retour;
    }
    
    /**
     * Permet de savoir si la liste contient déjà un objet produit denree.
     * Règles_Trans_006_ComportementRetourGuideMultiSelection 
     *
     * @param denree produit
     * @return true si le produit est déjà dans la liste
     */
    public boolean isPonderationRisqueTheoriqueProduitsContainsDenree(VDenree denree) {
        boolean retour = false;
        for (Iterator<PonderationRisqueTheoriqueProduit> it = ponderationRisqueTheoriqueProduits.iterator(); it.hasNext();) {
            PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit = it.next();
            if (denree.equals(ponderationRisqueTheoriqueProduit.getProduit().getDenre())) {
                retour=true;
                break;
            }
        }
        return retour;
    }

    /**
     * Permet de savoir si la liste contient déjà un objet produit filVeg.
     * Règles_Trans_006_ComportementRetourGuideMultiSelection 
     *
     * @param filVeg produit
     * @return true si le produit est déjà dans la liste
     */
    public boolean isPonderationRisqueTheoriqueProduitsContainsFilVeg(VFiliereVegetal filVeg) {
        boolean retour = false;
        for (Iterator<PonderationRisqueTheoriqueProduit> it = ponderationRisqueTheoriqueProduits.iterator(); it.hasNext();) {
            PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit = it.next();
            if (filVeg.equals(ponderationRisqueTheoriqueProduit.getProduit().getVeget())) {
                retour=true;
                break;
            }
        }
        return retour;
    }
    
    /**
     * Permet de vérifier si il y a des doublons lors de l'enregistrement pour
     * les produits qui sont défini par une combobox.
     */
    public void checkPonderationRisqueTheoriqueProduitsContainsDoublon() {
        if (ponderationRisqueTheoriqueProduits != null && (isProduitAlani() || isProduitIntra() || isProduitAnima())) {
            List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits1 = new ArrayList<PonderationRisqueTheoriqueProduit>(ponderationRisqueTheoriqueProduits);
            List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsChecked = new ArrayList<PonderationRisqueTheoriqueProduit>();
            List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsForDelete = new ArrayList<PonderationRisqueTheoriqueProduit>();
            
            // Recherche les doublons
            for (PonderationRisqueTheoriqueProduit iter : ponderationRisqueTheoriqueProduits1) {
                List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits2 = new ArrayList<PonderationRisqueTheoriqueProduit>(ponderationRisqueTheoriqueProduits1);
                ponderationRisqueTheoriqueProduitsChecked.add(iter);
                ponderationRisqueTheoriqueProduits2.removeAll(ponderationRisqueTheoriqueProduitsChecked);
                deleteDoublonPonderationRisqueTheoriqueProduit(iter, ponderationRisqueTheoriqueProduits2, ponderationRisqueTheoriqueProduitsForDelete);
                // Supprime les doublons trouvés
                for (PonderationRisqueTheoriqueProduit ponderation : ponderationRisqueTheoriqueProduitsForDelete) {
                    ponderation.breakAssociations();
                }
            }
        }
    }
    
    /**
     * Permet de suprimmer les doublons.
     *
     * @param iter the iter
     * @param ponderationRisqueTheoriqueProduits2 the ponderation risque theorique produits2
     * @param ponderationRisqueTheoriqueProduitsForDelete the ponderation risque theorique produits for delete
     */
    private void deleteDoublonPonderationRisqueTheoriqueProduit(PonderationRisqueTheoriqueProduit iter, List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits2, List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsForDelete) {
        
        for (PonderationRisqueTheoriqueProduit ponderation : ponderationRisqueTheoriqueProduits2) {
            if (isProduitAlani() && iter.getProduit().getAlani().equals(ponderation.getProduit().getAlani())) {
                ponderationRisqueTheoriqueProduitsForDelete.add(ponderation);
            }
            if (isProduitIntra() && iter.getProduit().getIntra().equals(ponderation.getProduit().getIntra())) {
                ponderationRisqueTheoriqueProduitsForDelete.add(ponderation);
            }
            if (isProduitAnima() && iter.getProduit().getAnima().equals(ponderation.getProduit().getAnima())) {
                ponderationRisqueTheoriqueProduitsForDelete.add(ponderation);
            }
        }
    }
    
    /**
     * Retourne la liste des rfa des produits de ponderationRisqueTheoriqueProduits.
     * 
     * @return liste des rfa des produits 
     */
    public List<String> initListProduitRfa() {
        List<String> produitsRfa = new ArrayList<String>();
        if (ponderationRisqueTheoriqueProduits != null) {
            for (Iterator<PonderationRisqueTheoriqueProduit> it = ponderationRisqueTheoriqueProduits.iterator(); it.hasNext();) {
                PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit = it.next();
                Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
                if (produit != null && checkProtectionSameTypeProduit(produit) && !produitsRfa.contains(produit.getProdRfa())) {
                    produitsRfa.add(produit.getProdRfa());
                }
            }
        }
        
        return produitsRfa;
    }
    
    /**
     * Permet de vérifier si le produit a le meme type produit que le type d'activité.
     *
     * @param produit le produit
     * @return true si le produit a le meme type produit que le type d'activité.
     */
    public boolean checkProtectionSameTypeProduit(Produit produit) {
        boolean retour = false;
        if (typeActivite != null 
                && produit.getTypeProduit() != null 
                && typeActivite.getTypeProduit().getTprodRfa().equals(produit.getTypeProduit().getTprodRfa())) {
            retour = true;
        }
        return retour;
    }
    
    /**
     * Permet de savoir si le type produit du type d'activité est ANIMA.
     *
     * @return true si le type produit est ANIM, false sinon
     */
    public boolean isProduitAnima() {
        boolean retour = false;
        if (typeActivite != null && TypeProduit.TYPE_PROD_ANIMA.equals(typeActivite.getTypeProduit().getTprodRfa())) {
            retour = true;
        }
        return retour;
    }
    
    /**
     * Permet de savoir si le type produit du type d'activité est VEGET.
     *
     * @return true si le type produit est VEGET, false sinon
     */
    public boolean isProduitVeget() {
        boolean retour = false;
        if (typeActivite != null && TypeProduit.TYPE_PROD_VEGET.equals(typeActivite.getTypeProduit().getTprodRfa())) {
            retour = true;
        }
        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est DENRE.
     *
     * @return true si le type produit est DENRE, false sinon
     */
    public boolean isProduitDenre() {
        boolean retour = false;
        if (typeActivite != null && TypeProduit.TYPE_PROD_DENRE.equals(typeActivite.getTypeProduit().getTprodRfa())) {
            retour = true;
        }
        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est INTRA.
     *
     * @return true si le type produit est INTRA, false sinon
     */
    public boolean isProduitIntra() {
        boolean retour = false;
        if (typeActivite != null && TypeProduit.TYPE_PROD_INTRA.equals(typeActivite.getTypeProduit().getTprodRfa())) {
            retour = true;
        }
        return retour;
    }

    /**
     * Permet de savoir si le type produit du type d'activité est ALANI.
     *
     * @return true si le type produit est ALANI, false sinon
     */
    public boolean isProduitAlani() {
        boolean retour = false;
        if (typeActivite != null && TypeProduit.TYPE_PROD_ALANI.equals(typeActivite.getTypeProduit().getTprodRfa())) {
            retour = true;
        }
        return retour;
    }

}
