/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business;

// TODO: Auto-generated Javadoc
/**
 * Interface utilisée pour les listes afin de rendre générique le tri sur une
 * colonne de la liste ou bien sur deux colonne de la liste.
 *
 * @author pegaltier
 */
public interface IGenericList {

    /**
     * Correspond au string du tri sur 1 colonne.
     *
     * @return string a trier
     */
    String getStringTriSur1Colonne();

    /**
     * Correspond au 1er string du tri sur 2 colonne.
     *
     * @return string a trier
     */
    String getFirstStringTriSur2Colonne();

    /**
     * Correspond au 2eme string du tri sur 2 colonne.
     *
     * @return string a trier
     */
    String getSecondStringTriSur2Colonne();
}
