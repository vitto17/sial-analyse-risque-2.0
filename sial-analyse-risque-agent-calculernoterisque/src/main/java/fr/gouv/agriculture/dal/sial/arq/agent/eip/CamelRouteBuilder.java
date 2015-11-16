package fr.gouv.agriculture.dal.sial.arq.agent.eip;

import fr.gouv.agriculture.o2.kernel.Inject;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;

/**
 * Classe définissant les routes à prendre selon le scénario chois.
 *
 */
public class CamelRouteBuilder extends RouteBuilder {

    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(CamelRouteBuilder.class);
    /**
     * Timer du scénario à la demande
     */
    @Inject
    private String timer;
    /**
     * Timer du scénario Toutes
     */
    @Inject
    private String timerOneShot;
    /**
     * Requete du scénario à la demande
     */
    @Inject
    private String sqlBatch;
    /**
     * Requete du scénario Toutes
     */
    @Inject
    private String sqlBatchToutes;

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure() throws Exception {
        // Scénario Toutes
        //if (ElementBatchConstante.TOUTES.equals(CalculerNoteRisqueAgentStart.modeLancement)) {
            from(timerOneShot)
                    .log("lancement traitement hebdo - TOUTES")
                    .setBody(simple(sqlBatchToutes))
                    .to("jdbc:arqJdbcDataSource")
                    .beanRef("fr.gouv.agriculture.dal.sial.arq.agent.services.CalculerNoteRisqueAgentService",
                            "traitementToutes");
        //} else {
            // Scénario a la demande
            from(timer)
                    .setBody(simple(sqlBatch))
                    .to("jdbc:arqJdbcDataSource")
                    .split(body(List.class))
                    .parallelProcessing()
                    .beanRef("fr.gouv.agriculture.dal.sial.arq.agent.services.CalculerNoteRisqueAgentService",
                            "traitement").end();
        //}
    }

    /**
     *
     * @param timer timer to set
     */
    public void setTimer(String timer) {
        this.timer = timer;
    }

    /**
     *
     * @param timerOneShot timer to set
     */
    public void setTimerOneShot(String timerOneShot) {
        this.timerOneShot = timerOneShot;
    }

    /**
     *
     * @param sqlBatch sqlBatch to set
     */
    public void setSqlBatch(String sqlBatch) {
        this.sqlBatch = sqlBatch;
    }

    /**
     *
     * @param sqlBatchToutes sqlBatch to set
     */
    public void setSqlBatchToutes(String sqlBatchToutes) {
        this.sqlBatchToutes = sqlBatchToutes;
    }

}
