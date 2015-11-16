/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.service.impl;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.arq.business.Produit;
import fr.gouv.agriculture.dal.sial.arq.dao.AlimentationAnimaleDAO;
import fr.gouv.agriculture.dal.sial.arq.dao.DenreeDAO;
import fr.gouv.agriculture.dal.sial.arq.dao.FiliereVegetalDAO;
import fr.gouv.agriculture.dal.sial.arq.dao.TypeIntrantDAO;
import fr.gouv.agriculture.dal.sial.arq.dao.VEspeceDgalDAO;
import fr.gouv.agriculture.dal.sial.arq.service.PonderationRisqueTheoriqueService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VAlimentationAnimale;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDenree;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VEspeceDgal;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VFiliereVegetal;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeIntrant;
import fr.gouv.agriculture.o2.kernel.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueServiceImpl implements PonderationRisqueTheoriqueService {

    @Inject (value="fr.gouv.agriculture.dal.sial.arq.dao.VEspeceDgalDAO")
    VEspeceDgalDAO especeDgalDAO;
    
    @Inject (value="fr.gouv.agriculture.dal.sial.arq.dao.AlimentationAnimaleDAO")
    AlimentationAnimaleDAO alimentationAnimaleDAO;
    
    @Inject (value="fr.gouv.agriculture.dal.sial.arq.dao.TypeIntrantDAO")
    TypeIntrantDAO typeIntrantDAO;
    
    @Inject (value="fr.gouv.agriculture.dal.sial.arq.dao.DenreeDAO")
    DenreeDAO denreeDAO;
    
    @Inject (value="fr.gouv.agriculture.dal.sial.arq.dao.FiliereVegetalDAO")
    FiliereVegetalDAO filiereVegetalDAO;

    @Override
    public void initPonderationRisqueTheoriqueProduitAnima(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception {
        
        Map<String,VEspeceDgal> retour = especeDgalDAO.findWithListRfa(listRfa);
        VEspeceDgal especeDgal;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            if (!StringUtils.isEmpty(produit.getProdRfa())) {
                especeDgal = retour.get(produit.getProdRfa());
                produit.setAnima(especeDgal);
            }
        }
    }
    
    @Override
    public void savePonderationRisqueTheoriqueProduitAnima(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits) {
        
        VEspeceDgal especeDgal;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            especeDgal = produit.getAnima();
            if (especeDgal != null) {
                produit.setProdRfa(especeDgal.getEspdRfa());
//                produit.setProdLb("FIXME");
//                produit.setProdLb(especeDgal.getEspdLb());
            }
        }
    }
    
    @Override
    public void initPonderationRisqueTheoriqueProduitAlani(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception {
        
        Map<String,VAlimentationAnimale> retour = alimentationAnimaleDAO.findWithListRfa(listRfa);
        VAlimentationAnimale alimentationAnimale;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            if (!StringUtils.isEmpty(produit.getProdRfa())) {
                alimentationAnimale = retour.get(produit.getProdRfa());
                produit.setAlani(alimentationAnimale);
            }
        }
    }
    
    @Override
    public void savePonderationRisqueTheoriqueProduitAlani(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits) {
        
        VAlimentationAnimale alimentationAnimale;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            alimentationAnimale = produit.getAlani();
            if (alimentationAnimale != null) {
                produit.setProdRfa(alimentationAnimale.getAaRfa());
//                produit.setProdLb("FIXME");
//                produit.setProdLb(alimentationAnimale.getAaLb());
            }
        }
    }
    
    @Override
    public void initPonderationRisqueTheoriqueProduitIntra(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception {
        
        Map<String,VTypeIntrant> retour = typeIntrantDAO.findWithListRfa(listRfa);
        VTypeIntrant typeIntrant;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            if (!StringUtils.isEmpty(produit.getProdRfa())) {
                typeIntrant = retour.get(produit.getProdRfa());
                produit.setIntra(typeIntrant);
            }
        }
    }
    
    @Override
    public void savePonderationRisqueTheoriqueProduitIntra(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits) {
        
        VTypeIntrant typeIntrant;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            typeIntrant = produit.getIntra();
            if (typeIntrant != null) {
                produit.setProdRfa(typeIntrant.getTintRfa());
//                produit.setProdLb("FIXME");
//                produit.setProdLb(typeIntrant.getTintLb());
            }
        }
    }
    
    @Override
    public void initPonderationRisqueTheoriqueProduitDenre(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception {
        
        Map<String,VDenree> retour = denreeDAO.findWithListRfa(listRfa);
        VDenree denree;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            if (!StringUtils.isEmpty(produit.getProdRfa())) {
                denree = retour.get(produit.getProdRfa());
                produit.setDenre(denree);
            }
        }
    }
    
    @Override
    public void savePonderationRisqueTheoriqueProduitDenre(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits) {
        
        VDenree denree;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            denree = produit.getDenre();
            if (denree != null) {
                produit.setProdRfa(denree.getDenRfa());
//                produit.setProdLb("FIXME");
//                produit.setProdLb(denree.getDenLb());
            }
        }
    }
    
    @Override
    public void initPonderationRisqueTheoriqueProduitVeget(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception {
        
        Map<String,VFiliereVegetal> retour = filiereVegetalDAO.findWithListRfa(listRfa);
        VFiliereVegetal filiereVegetal;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            if (!StringUtils.isEmpty(produit.getProdRfa())) {
                filiereVegetal = retour.get(produit.getProdRfa());
                produit.setVeget(filiereVegetal);
            }
        }
    }
    
    @Override
    public void savePonderationRisqueTheoriqueProduitVeget(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits) {
        
        VFiliereVegetal filiereVegetal;
        for (PonderationRisqueTheoriqueProduit ponderationRisqueTheoriqueProduit : ponderationRisqueTheoriqueProduits) {
            Produit produit = ponderationRisqueTheoriqueProduit.getProduit();
            filiereVegetal = produit.getVeget();
            if (filiereVegetal != null) {
                produit.setProdRfa(filiereVegetal.getFilvegRfa());
//                produit.setProdLb("FIXME");
//                produit.setProdLb(filiereVegetal.getFilvegLb());
            }
        }
    }
    
    @Override
    public void initProduitOfListPonderationRisqueTheorique(List<PonderationRisqueTheorique> list) throws Exception {
        List<String> listProduitsRfa; // Produits de tout type
        List<PonderationRisqueTheoriqueProduit> listProduitsObjet; // Produits de tout type
        List<String> listProduitsAnimaRfa = new ArrayList<String>();
        List<String> listProduitsAlaniRfa = new ArrayList<String>();
        List<String> listProduitsIntraRfa = new ArrayList<String>();
        List<String> listProduitsDenreRfa = new ArrayList<String>();
        List<String> listProduitsVegetRfa = new ArrayList<String>();
        
        List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsAnima = new ArrayList<PonderationRisqueTheoriqueProduit>();
        List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsAlani = new ArrayList<PonderationRisqueTheoriqueProduit>();
        List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsIntra = new ArrayList<PonderationRisqueTheoriqueProduit>();
        List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsDenre = new ArrayList<PonderationRisqueTheoriqueProduit>();
        List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduitsVeget = new ArrayList<PonderationRisqueTheoriqueProduit>();
        
        // Initialise la liste de rfa des objets a récupérer
        for (PonderationRisqueTheorique ponderationRisqueTheorique : list) {
            
            // Récupere le rfa de tous les produits de la pondération risque théorique
            listProduitsRfa=ponderationRisqueTheorique.initListProduitRfa();
            // récupere l'objet  de tous les produits de la pondération risque théorique
            listProduitsObjet=ponderationRisqueTheorique.getPonderationRisqueTheoriqueProduits();
                    
            // Si la ponderation est de type anima
            if (ponderationRisqueTheorique.isProduitAnima()) {
                addStringWithUnicity(listProduitsRfa,listProduitsAnimaRfa);
                addProduitWithUnicity(listProduitsObjet, ponderationRisqueTheoriqueProduitsAnima);
            }
            
            // Si la ponderation est de type alani
            if (ponderationRisqueTheorique.isProduitAlani()) {
                addStringWithUnicity(listProduitsRfa,listProduitsAlaniRfa);
                addProduitWithUnicity(listProduitsObjet, ponderationRisqueTheoriqueProduitsAlani);
            }
            
            // Si la ponderation est de type intra
            if (ponderationRisqueTheorique.isProduitIntra()) {
                addStringWithUnicity(listProduitsRfa,listProduitsIntraRfa);
                addProduitWithUnicity(listProduitsObjet, ponderationRisqueTheoriqueProduitsIntra);
            }
            
            // Si la ponderation est de type denre
            if (ponderationRisqueTheorique.isProduitDenre()) {
                addStringWithUnicity(listProduitsRfa,listProduitsDenreRfa);
                addProduitWithUnicity(listProduitsObjet, ponderationRisqueTheoriqueProduitsDenre);
            }
            
            // Si la ponderation est de type veget
            if (ponderationRisqueTheorique.isProduitVeget()) {
                addStringWithUnicity(listProduitsRfa,listProduitsVegetRfa);
                addProduitWithUnicity(listProduitsObjet, ponderationRisqueTheoriqueProduitsVeget);
            }
        }
        
        // Charge les objets transiants 
        if (!listProduitsAnimaRfa.isEmpty()) {
            initPonderationRisqueTheoriqueProduitAnima(ponderationRisqueTheoriqueProduitsAnima,listProduitsAnimaRfa);
        }
        if (!listProduitsAlaniRfa.isEmpty()) {
            initPonderationRisqueTheoriqueProduitAlani(ponderationRisqueTheoriqueProduitsAlani,listProduitsAlaniRfa);
        }
        if (!listProduitsIntraRfa.isEmpty()) {
            initPonderationRisqueTheoriqueProduitIntra(ponderationRisqueTheoriqueProduitsIntra,listProduitsIntraRfa);
        }
        if (!listProduitsDenreRfa.isEmpty()) {
            initPonderationRisqueTheoriqueProduitDenre(ponderationRisqueTheoriqueProduitsDenre,listProduitsDenreRfa);
        }
        if (!listProduitsVegetRfa.isEmpty()) {
            initPonderationRisqueTheoriqueProduitVeget(ponderationRisqueTheoriqueProduitsVeget,listProduitsVegetRfa);
        }
    }

    private void addStringWithUnicity(List<String> listProduitsRfa, List<String> listProduitsByTypeRfa) {
        for (String rfa : listProduitsRfa) {
            if (!listProduitsByTypeRfa.contains(rfa)) {
                listProduitsByTypeRfa.add(rfa);
            }
        }
    }
    
    private void addProduitWithUnicity(List<PonderationRisqueTheoriqueProduit> listProduitsObjet, List<PonderationRisqueTheoriqueProduit> listProduitsByTypeObjet) {
        for (PonderationRisqueTheoriqueProduit pondProduit : listProduitsObjet) {
            if (!listProduitsByTypeObjet.contains(pondProduit)) {
                listProduitsByTypeObjet.add(pondProduit);
            }
        }
    }
}
