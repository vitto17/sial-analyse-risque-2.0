package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.BooleanPonderation;
import fr.gouv.agriculture.dal.sial.arq.dao.BooleanPonderationDAO;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;

/**
 * DAO pour generer des Boolean
 * @author pbarreau
 *
 */
public class BooleanPonderationDAOImpl extends DefaultBusinessDAO implements BooleanPonderationDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public Collection findBy(BusinessSearchContext businessSearchContext) throws Exception {

		List<BooleanPonderation> res = new ArrayList<BooleanPonderation>();
		res.add(new BooleanPonderation(Boolean.TRUE));
		res.add(new BooleanPonderation(Boolean.FALSE));
		return res;
	}
}
