/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.list;

import java.util.Collection;

import fr.gouv.agriculture.dal.sial.arq.dao.BatchDAO;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.helper.CommonHelper;

/**
 *
 * @author ygarcia
 */
public class TraitementsEnCoursListController extends ListController {

    @Inject
    private transient BatchDAO businessDao;
    
    /** Service de gestion de l'utilisateur*/
	@Inject
	private HabilitationsService habilitationsService;

	@SuppressWarnings("rawtypes")
	@Override
    protected Collection findBusinesses(
            BusinessSearchContext businessSearchContext) throws Exception {

        String loginLb = habilitationsService.getUtilisateurConnecte().getLogin();
        
        return businessDao.findByEstatus(loginLb);
    }

	@SuppressWarnings("javadoc")
	public void setBusinessDao(BatchDAO businessDao) {
        this.businessDao = businessDao;
    }
    
	/**
	 * Cette méthode détermine si la liste contrôlée par cette classe est vide.
	 * 
	 * @return Un booléen, 'true' si la liste du contrôleur est vide, 'false' si
	 *         elle contient au moins 1 élément.
	 */
	public Boolean getIsEmpty() {
		return CommonHelper.isEmpty(getListModel().getObjects());
	}

}
