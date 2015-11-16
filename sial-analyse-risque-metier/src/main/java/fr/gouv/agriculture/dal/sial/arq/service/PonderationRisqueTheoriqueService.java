/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.service;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VFiliereVegetal;
import java.util.List;

/**
 *
 * @author pegaltier
 */
public interface PonderationRisqueTheoriqueService {

    /**
     * Permet d'initialiser l'objet transiant espece dgal de chaque produit de
     * la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     * @param listRfa
     * @throws Exception
     */
    void initPonderationRisqueTheoriqueProduitAnima(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception;

    /**
     * Permet de sauvegarder le rfa de l'objet transiant espece dgal de chaque
     * produit de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     */
    void savePonderationRisqueTheoriqueProduitAnima(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits);

    /**
     * Permet d'initialiser l'objet transiant alimentation animale de chaque
     * produit de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     * @param listRfa
     * @throws Exception
     */
    void initPonderationRisqueTheoriqueProduitAlani(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception;

    /**
     * Permet de sauvegarder le rfa de l'objet transiant alimentation animale de
     * chaque produit de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     */
    void savePonderationRisqueTheoriqueProduitAlani(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits);

    /**
     * Permet d'initialiser l'objet transiant type intrant de chaque produit de
     * la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     * @param listRfa
     * @throws Exception
     */
    void initPonderationRisqueTheoriqueProduitIntra(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception;

    /**
     * Permet de sauvegarder le rfa de l'objet transiant type intrant de chaque
     * produit de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     */
    void savePonderationRisqueTheoriqueProduitIntra(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits);

    /**
     * Permet d'initialiser l'objet transiant denree de chaque produit de la
     * liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     * @param listRfa
     * @throws Exception
     */
    void initPonderationRisqueTheoriqueProduitDenre(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception;

    /**
     * Permet de sauvegarder le rfa de l'objet transiant denree de chaque
     * produit de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     */
    void savePonderationRisqueTheoriqueProduitDenre(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits);

    /**
     * Permet d'initialiser l'objet transiant Filiere Vegetal de chaque produit
     * de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     * @param listRfa
     * @throws Exception
     */
    void initPonderationRisqueTheoriqueProduitVeget(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits, List<String> listRfa) throws Exception;

    /**
     * Permet de sauvegarder le rfa de l'objet transiant Filiere Vegetal de
     * chaque produit de la liste ponderationRisqueTheoriqueProduits.
     *
     * @param ponderationRisqueTheoriqueProduits
     */
    void savePonderationRisqueTheoriqueProduitVeget(List<PonderationRisqueTheoriqueProduit> ponderationRisqueTheoriqueProduits);

    /**
     * Permet d'initialiser l'objet transiant de toute la liste de
     * PonderationRisqueTheorique passée en paramètre.
     *
     * @param ponderationRisqueTheorique
     * @throws Exception
     */
    public void initProduitOfListPonderationRisqueTheorique(List<PonderationRisqueTheorique> list) throws Exception;
}
