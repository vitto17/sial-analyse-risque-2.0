package fr.gouv.agriculture.dal.sial.arq.agent;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.ElementBatchConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.management.CalculerNoteRisqueAgentAgent;
import fr.gouv.agriculture.o2.agent.exec.AgentLauncherHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe de lancement de l'agent. Permet de lancer le batch.
 *
 */
public final class CalculerNoteRisqueAgentStart {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculerNoteRisqueAgentStart.class);

    /**
     * Fichier de configuration
     */
    private static final String DEFAULT_CONFIGURATION_FILE = "kernel-config.xml";

    /**
     * Paramètre Mode Lancement
     */
    public static String modeLancement = "";

    /**
     * Constructeur par defaut
     */
    private CalculerNoteRisqueAgentStart() {

    }

    /**
     * Lancement du batch
     *
     * @param args liste des arguments
     */
    public static void main(String[] args) {
        AgentLauncherHelper.printEnvParams(args);

        int rmiRegistryPort = 0;
        if (args.length >= 1 && args[0].length() > 1) {
            rmiRegistryPort = Integer.parseInt(args[0]);
        } else {
            throw new IllegalArgumentException("rmiRegitryPort must be spécified for starting Agent!");
        }

        if (args.length >= 2
                && (ElementBatchConstante.A_LA_DEMANDE.equals(args[1]) || ElementBatchConstante.TOUTES.equals(args[1]))) {
            modeLancement = args[1];
        } else {
            modeLancement = ElementBatchConstante.A_LA_DEMANDE;
        }
        LOGGER.info("Mode Lancement : " + modeLancement);

        String configurationFile = "";
        if (args.length >= 3 && args[2].length() > 1) {
            configurationFile = args[2];
        } else {
            configurationFile = DEFAULT_CONFIGURATION_FILE;
        }

        try {

            // Demarrage de l'agent
            AgentLauncherHelper.launchAgent(configurationFile, rmiRegistryPort,
                    CalculerNoteRisqueAgentAgent.class.getName());
        } catch (Exception e) {
            LOGGER.error("Erreur au lancement de l'agent", e);
        }
    }
}
