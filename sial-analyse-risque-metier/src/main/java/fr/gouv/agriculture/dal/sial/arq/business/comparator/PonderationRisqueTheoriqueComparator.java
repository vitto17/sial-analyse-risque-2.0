/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.business.comparator;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Permet de trier les ponderation ri th sur la page "Liste et formulaire de
 * pondération du Risque théorique de l’activité".
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueComparator implements Comparator<PonderationRisqueTheorique>, Serializable {

    /**
     * Compare les objets.
     *
     * @param object1
     * @param object2
     * @return -1 si l'object 1 doit se situer avant l'object 2.
     */
    @Override
    public int compare(PonderationRisqueTheorique object1, PonderationRisqueTheorique object2) {
        int value;
        if (object1 == null) {
            value = -1;
        } else if (object2 == null) {
            value = 1;
        } else if (object1.getTypeActivite() == null) {
            value = -1;
        } else if (object2.getTypeActivite() == null) {
            value = 1;
        } else {
            value = compareColumn(object1.getTypeActivite().getTaLb(), object2.getTypeActivite().getTaLb());
            
            if (value==0) {
                value = compareColumn(object1.getPonderationRisqueTheoriqueApprobationsStr(), object2.getPonderationRisqueTheoriqueApprobationsStr());
            }
            if (value==0) {
                value = compareColumn(object1.getPonderationRisqueTheoriqueProcedesStr(), object2.getPonderationRisqueTheoriqueProcedesStr());
            }
            if (value==0) {
                value = compareColumn(object1.getPonderationRisqueTheoriqueProduitsStr(), object2.getPonderationRisqueTheoriqueProduitsStr());
            }
            
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
