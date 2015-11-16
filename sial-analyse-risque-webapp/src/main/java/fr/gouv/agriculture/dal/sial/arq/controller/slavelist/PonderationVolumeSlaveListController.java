/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.slavelist;

import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.business.comparator.Generic2ColumnListComparator;
import fr.gouv.agriculture.orion.controller.list.SlaveListController;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author pegaltier
 */
public class PonderationVolumeSlaveListController extends SlaveListController {

    @Override
    protected Collection loadEntities(Stack stack) throws Exception {
        final List<PonderationVolume> list = (List<PonderationVolume>) super.loadEntities(stack);
        Collections.sort(list, new Generic2ColumnListComparator());

        return list;
    }
}
