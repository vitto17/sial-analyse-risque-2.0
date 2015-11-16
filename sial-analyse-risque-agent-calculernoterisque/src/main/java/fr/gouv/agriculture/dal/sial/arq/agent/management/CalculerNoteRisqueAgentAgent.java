package fr.gouv.agriculture.dal.sial.arq.agent.management;

import fr.gouv.agriculture.dal.sial.arq.agent.jmx.impl.CalculerNoteRisqueAgentManagementBean;
import fr.gouv.agriculture.o2.agent.Agent;
import fr.gouv.agriculture.o2.agent.impl.AbstractAgent;
import fr.gouv.agriculture.o2.agent.jmx.impl.AgentManagementBean;
import fr.gouv.agriculture.o2.kernel.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents Agent instance
 *
 * @author olivier.pillaud
 *
 */
public class CalculerNoteRisqueAgentAgent extends AbstractAgent implements Agent {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculerNoteRisqueAgentAgent.class);

    /**
     * Scheduler
     */
//    private Scheduler scheduler;
    /**
     * Agent Management
     */
    @Inject(value = "agentManagementBean")
    private CalculerNoteRisqueAgentManagementBean agentManagementBean;
    /**
     * Context
     */
    @Inject
    private CamelContext context;
    /**
     * Route Builder
     */
    @Inject(value = "camelRouteBuilder")
    private RouteBuilder routeBuilder;

    /**
     * Constructor
     *
     */
    public CalculerNoteRisqueAgentAgent() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopServicesAgent() {
        try {
            context.stop();
//            scheduler.shutdown();
            LOGGER.info("Arret de l'agent -> {}.", agentManagementBean.getAgentName());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'arrêt du scheduler.", e);
            System.exit(0);
        }
    }

    /**
     * Start agent services
     *
     * @throws Exception
     */
    @Override
    protected void startServicesAgent() throws Exception {
        // creation du scheduler Quartz
//        scheduler = StdSchedulerFactory.getDefaultScheduler();

        // Define job instance
        // JobDetail job1 = new JobDetail("stopJob", "group1", StopJob.class);
        // Define a Trigger that will fire 5 minutes later
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.MINUTE, 5);
        // Trigger trigger1 = new SimpleTrigger("trigger1", "group1",
        // cal.getTime());
        // scheduler.scheduleJob(job1, trigger1);
        // Demarrage du scheduler
        // scheduler.start();
        // Demarrage de CAMEL
        context.addRoutes(routeBuilder);
        context.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AgentManagementBean getAgentManagementBean() {
        return agentManagementBean;
    }

}
