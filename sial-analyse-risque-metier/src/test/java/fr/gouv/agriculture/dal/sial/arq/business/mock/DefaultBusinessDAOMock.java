package fr.gouv.agriculture.dal.sial.arq.business.mock;

import java.io.Serializable;
import java.util.Collection;

import fr.gouv.agriculture.orion.business.Business;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.dao.listener.CopyListener;
import fr.gouv.agriculture.orion.dao.listener.DeleteListener;
import fr.gouv.agriculture.orion.dao.listener.PasteListener;
import fr.gouv.agriculture.orion.dao.listener.SynchronizeFormListener;
import fr.gouv.agriculture.orion.dao.listener.SynchronizeListener;
import fr.gouv.agriculture.orion.datatransfer.BusinessTransfer;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.persistence.PersistenceService;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.ReportDataSourceProvider;
import fr.gouv.agriculture.orion.rule.Rule;

public class DefaultBusinessDAOMock implements BusinessDAO, CopyListener,
DeleteListener, SynchronizeFormListener, SynchronizeListener,
PasteListener, ReportDataSourceProvider {

	@Override
	public Object create(BusinessContext businessContext, Class businessClass)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection copy(BusinessContext businessContext,
			Collection transferables) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(BusinessContext businessContext, Business business)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BusinessContext businessContext, Collection businesses)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void synchronize(BusinessContext businessContext,
			Collection businesses) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void synchronize(BusinessContext businessContext, Business business)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void synchronizeForm(BusinessContext businessContext,
			Business business) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Business business) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attach(Business business) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(Business business) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection findBy(BusinessSearchContext businessSearchContext)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Business load(Class clazz, Serializable identifier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistenceService getPersistenceService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pasteAndSynchronize(BusinessContext businessContext,
			Collection transferables) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pasteAndCreate(BusinessContext businessContext,
			Collection transferables) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RuleReport validateRule(Business business, Rule rule)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTransient(Business business) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReportDataSource getReportDataSource(String paramString,
			BusinessSearchContext paramBusinessSearchContext)
			throws PersistenceException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void preparePaste(BusinessTransfer paramBusinessTransfer,
			BusinessContext paramBusinessContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminatePaste(BusinessTransfer paramBusinessTransfer,
			BusinessContext paramBusinessContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareSynchronization(Business paramBusiness) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateSynchronization(Business paramBusiness)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareDelete(Business paramBusiness) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateDelete(Business paramBusiness) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareCopy(BusinessTransfer paramBusinessTransfer,
			BusinessContext paramBusinessContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateCopy(BusinessTransfer paramBusinessTransfer,
			BusinessContext paramBusinessContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
