package fr.gouv.agriculture.dal.sial.arq.agent;

import fr.gouv.agriculture.o2.agent.exec.AgentLauncherHelper;
import fr.gouv.agriculture.o2.agent.jmx.AgentManagementBeanMXBean;
import fr.gouv.agriculture.o2.agent.jmx.JmxHelper;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe permettant d'afficher les informations du batch en cours
 * d'utilisation.
 * 
 */
public final class CalculerNoteRisqueAgentStatus {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculerNoteRisqueAgentStatus.class);
	
	/** Constructeur par defaut */
	private CalculerNoteRisqueAgentStatus() {
		
	}
	
	
	/**
	 * Méthode principale de la classe.
	 * 
	 * @param args
	 *            liste des arguments
	 */
	public static void main(String[] args) {
		AgentLauncherHelper.printEnvParams(args);

		int jmxPort = 0;
		if (args.length >= 1 && args[0].length() > 1) {
		    jmxPort = Integer.parseInt(args[0]);
		} else {
		    throw new IllegalArgumentException("jmxPort must be specified for quering agent status");
		}
		
		int rmiRegistryPort = 0;
		if (args.length >= 2 && args[1].length() > 1) {
		    rmiRegistryPort = Integer.parseInt(args[1]);
		} else {
		    throw new IllegalArgumentException("rmiRegitryPort must be specified for quering agent status");
		}

		String jmxRole = "";
		if (args.length >= 3 && args[2].length() > 1) {
		    jmxRole = args[2];
		} else {
		    throw new RuntimeException("jmxRole must be specified for quering agent status");
		}

		String jmxPasswd = "";
		if (args.length >= 4 && args[3].length() > 1) {
		    jmxPasswd = args[3];
		} else {
		    throw new RuntimeException("jmxPasswd must be specified for quering agent status");
		}
		

		try {
			MBeanServerConnection mbsc = JmxHelper.getMBeanServerConnection(jmxPort, rmiRegistryPort, jmxRole,
					jmxPasswd);

			AgentManagementBeanMXBean agentManagementBean = JMX.newMBeanProxy(mbsc, new ObjectName(
					AgentManagementBeanMXBean.JMX_PATH + ":type=AgentManagementBean"), AgentManagementBeanMXBean.class,
					true);

			// Affichage des données dans la sortie standart
			LOGGER.info("Nom de l'agent : " + agentManagementBean.getAgentName());
            LOGGER.info("Version : " + agentManagementBean.getAgentVersion());
            LOGGER.info("Evironnement d'execution : " + agentManagementBean.getEnvironnement());
            LOGGER.info("Date et heure de lancement : " + agentManagementBean.getStartTime());
            LOGGER.info("Etat : " + agentManagementBean.getStatus());

		} catch (Exception e) {
            LOGGER.error("Erreur a la connexion au serveur JMX. L'agent ne semble pas lance. ", e);
        }
	}

}
