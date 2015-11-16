package fr.gouv.agriculture.dal.sial.arq.controller.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import fr.gouv.agriculture.orion.controller.list.ListController;

/**
 * Cette classe implémente la règle de gestion
 * "Règles_Trans_010_OuvertureEcranRecherche" et initialise la liste à vide lors
 * de la première ouverture de la page.
 * 
 * @author jodurand
 * 
 */
public abstract class AbstractInitVideListController extends ListController {

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection loadEntities(Stack stack) throws Exception {
		return new ArrayList();
	}
}
