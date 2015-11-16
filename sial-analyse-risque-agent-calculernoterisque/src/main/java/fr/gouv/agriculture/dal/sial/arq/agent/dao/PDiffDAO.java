package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.PDiffConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao pour le calcul de la pondération de la diffusion.
 * 
 */
public class PDiffDAO extends BaseJBDCTemplateDAO {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PDiffDAO.class);

	private static final int CACHE_INITIAL_CAPACITY = 1000;

	private Map<String, Float> CACHE_P_DIFF;

	/**
	 * Chargement du cache.
	 * 
	 * @param formule
	 *            la formule de risque
	 * @throws SQLException
	 *             si un problème survient
	 */
	public void chargeCache(FormuleRisque formule) throws SQLException {

		CACHE_P_DIFF = new HashMap<String, Float>(CACHE_INITIAL_CAPACITY);

		// StopWatch watch = new StopWatch();
		// watch.start();
		String sql = PDiffConstante.REQUETE_SELECT_DIFF;
		// LOGGER.debug("Execution de la requete : {}", sql);
		ResultSet resultSet = this.performQuery(sql, formule.getFormId());

		try {
			while (resultSet.next()) {
				String diffRfa = resultSet.getString(1);
				Float poids = resultSet.getFloat(2);
				CACHE_P_DIFF.put(diffRfa, poids);
				LOGGER.info("Cache ++ : {} |-> {}", new String[] { diffRfa, poids.toString() });
			}
		} finally {
			resultSet.close();
		}
		// watch.stop();
		// LOGGER.debug("Temps de chargement du cache PDiffusion : {}",
		// watch.toString());
	}

	/**
	 * Recupération du poids max de la diffusion.
	 * 
	 * @param ua
	 *            L'unité d'activité
	 * 
	 * @return Le poids de la pondération
	 * @throws SQLException
	 *             l'exception
	 */
	public Float getPDiff(UniteActivite ua) throws SQLException {
		Float diff;
		diff = CACHE_P_DIFF.get(ua.getDiffusionRfa());
		if (diff == null) {
			return 1F;
		}
		return diff;
	}
}
