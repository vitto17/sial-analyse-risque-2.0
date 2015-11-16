/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import fr.gouv.agriculture.dal.sial.arq.business.IGenericList;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparateur utilisé pour effectuer un tri générique sur une colonne de la
 * liste.
 *
 * @author pegaltier
 */
public class Generic1ColumnListComparator implements Comparator<IGenericList>, Serializable {

    /**
     * Compare les objets génériques
     *
     * @param object1
     * @param object2
     * @return -1 si l'object 1 doit se situer avant l'object 2.
     */
    @Override
    public int compare(IGenericList object1, IGenericList object2) {
        int value;
        if (object1 == null) {
            value = -1;
        } else if (object2 == null) {
            value = 1;
        } else {
            value = compareColumn(object1.getStringTriSur1Colonne(), object2.getStringTriSur1Colonne());
        }
        return value;
    }

    /**
     * Compare les strings passés en paramètres.
     *
     * @param lb1 libelle 1
     * @param lb2 libelle 2
     * @return -1 si le libellé 1 est situé avant dans l'ordre alphabétique, 1
     * sinon.
     */
    private int compareColumn(String lb1, String lb2) {
        int value;

        if (lb1 == null) {
            value = -1;
        } else if (lb2 == null) {
            value = 1;
        } else {
            value = lb1.compareToIgnoreCase(lb2);
        }
        return value;
    }
}
