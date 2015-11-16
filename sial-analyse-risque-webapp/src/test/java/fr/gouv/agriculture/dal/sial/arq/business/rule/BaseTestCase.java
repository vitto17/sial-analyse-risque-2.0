package fr.gouv.agriculture.dal.sial.arq.business.rule;

import java.util.Locale;

import junit.framework.TestCase;
import fr.gouv.agriculture.o2.kernel.Kernel;
import fr.gouv.agriculture.orion.Context;
import fr.gouv.agriculture.orion.helper.OrionHelper;
import fr.gouv.agriculture.orion.message.Message;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * La classe
 * 
 * BaseTestCase.
 * 
 * @author fabien.le-baillif
 */
public abstract class BaseTestCase extends TestCase {

	/** Attribut kernel. */
	private Kernel kernel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {

		Context context = OrionHelper.createSimpleContext(getKernelConfFilename());
		Context.getCurrentContext().getApplicationSession().setLocale(Locale.FRANCE);
		this.kernel = context.getKernel();
	}

	/**
	 * Getter sur l'attribut kernel.
	 * 
	 * @return kernel
	 */
	public Kernel getKernel() {
		return kernel;
	}

	/**
	 * Setter sur l'attribut kernel.
	 * 
	 * @param kernel
	 *            le nouveau kernel
	 */
	public void setKernel(Kernel kernel) {
		this.kernel = kernel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		this.kernel.stop();
	}

	/**
	 * Getter sur l'attribut kernelConfFilename.
	 * 
	 * @return kernelConfFilename
	 */
	protected abstract String getKernelConfFilename();

	/**
	 * Vérifie que le report contient un message de type erreur.
	 * 
	 * @param report
	 *            the report
	 * @param erreur
	 *            the erreur
	 */
	protected void assertRuleReportContainsError(RuleReport report, String erreur) {
		assertTrue(report.containsError());
		assertRuleReportContainsMessage(report, erreur);
	}

	/**
	 * Vérifie que le report contient un message.
	 * 
	 * @param report
	 *            Le RuleReport dans lequel on vérifie la présence du message
	 *            <code>erreur</code>.
	 * @param erreur
	 *            Le message que l'on cherche dans <code>report</code>.
	 */
	public void assertRuleReportContainsMessage(RuleReport report, String erreur) {
		boolean isMsgTrouve = false;

		for (Object msgObject : report.getMessages()) {
			Message msgError = (Message) msgObject;

			if (msgError.toString().equals(erreur)) {
				isMsgTrouve = true;
				break;
			}
		}

		assertTrue(isMsgTrouve);
	}
}