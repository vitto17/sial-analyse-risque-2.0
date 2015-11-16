/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.slavelist;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.PonderationRisqueTheoriqueComparator;
import fr.gouv.agriculture.dal.sial.arq.service.PonderationRisqueTheoriqueService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author pegaltier
 */
public class PonderationRisqueTheoriqueSLC extends SlaveListController {

    /**
     * Service de gestion des ponderations theorique
     */
    @Inject
    private PonderationRisqueTheoriqueService ponderationRisqueTheoriqueService;
    
    @Override
    protected Collection loadEntities(Stack stack) throws Exception {
        final List<PonderationRisqueTheorique> list = (List<PonderationRisqueTheorique>) super.loadEntities(stack);
        
        ponderationRisqueTheoriqueService.initProduitOfListPonderationRisqueTheorique(list);
        
        Collections.sort(list, new PonderationRisqueTheoriqueComparator());

        return list;
    }

}
