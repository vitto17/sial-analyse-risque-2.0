package fr.gouv.agriculture.dal.sial.arq.agent.jobs;

import fr.gouv.agriculture.o2.agent.jmx.impl.AgentManagementBean;
import fr.gouv.agriculture.o2.kernel.BeanNameAware;
import fr.gouv.agriculture.o2.kernel.Inject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Permet l'arret du traitement du Bean
 * 
 */
public class StopJob implements BeanNameAware, Job {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(StopJob.class);

	/** Nom du Bean */
	private String beanName;

	/** Agent Management Bean */
	@Inject(value = "agentManagementBean")
	private AgentManagementBean agentManagementBean;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//LOGGER.debug("execute");
		// Arret du scheduler
		try {
			agentManagementBean.stop();
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBeanName() {
		return beanName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBeanName(String string) {
		this.beanName = string;
	}

}
