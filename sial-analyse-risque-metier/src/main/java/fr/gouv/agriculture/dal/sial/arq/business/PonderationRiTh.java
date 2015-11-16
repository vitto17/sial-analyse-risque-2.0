/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business;

// TODO: Auto-generated Javadoc
/**
 * Interface permettant de faire un tri sur les 3 différents objets de
 * ponderation risque théorique.
 *
 * @author pegaltier
 */
public interface PonderationRiTh {

    /**
     * Gets the string libelle of column to order.
     *
     * @return the string libelle column to order
     */
    String getStringLibelleColumnToOrder();

    /**
     * Gets the string rfa of column to order.
     *
     * @return the string rfa column to order
     */
    String getStringRfaColumnToOrder();
}
