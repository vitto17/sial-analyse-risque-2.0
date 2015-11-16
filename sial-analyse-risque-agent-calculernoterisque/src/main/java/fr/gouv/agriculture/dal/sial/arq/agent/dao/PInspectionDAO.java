package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.PInspectionConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.NoteInspection;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao pour le calcul du poids de la note de dernière inspection.
 * 
 */
public class PInspectionDAO extends BaseJBDCTemplateDAO {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PInspectionDAO.class);

	private static final int CACHE_INITIAL_CAPACITY = 50;

	private Map<String, Map<String, Float>> CACHE_P_INSP;

	/**
	 * Chargement du cache.
	 * 
	 * @param dtRfa
	 *            le RFA du domaine technique
	 * @throws SQLException
	 *             si un problème survient
	 */
	public void chargeCache(String dtRfa) throws SQLException {

		CACHE_P_INSP = new HashMap<String, Map<String, Float>>(CACHE_INITIAL_CAPACITY);

		// StopWatch watch = new StopWatch();
		// watch.start();
		String sql = PInspectionConstante.REQUETE_SELECT_INSP;
		// LOGGER.debug("Execution de la requete : {}", sql);
		ResultSet resultSet = this.performQuery(sql, dtRfa);

		try {
			while (resultSet.next()) {
				String nomen_rfa = resultSet.getString(1);
				String eval_rfa = resultSet.getString(2);
				Float poids = resultSet.getFloat(3);

				if (!CACHE_P_INSP.containsKey(nomen_rfa)) {
					CACHE_P_INSP.put(nomen_rfa, new HashMap<String, Float>(10));
				}
				Map<String, Float> cacheNomen = CACHE_P_INSP.get(nomen_rfa);
				cacheNomen.put(eval_rfa, poids);

				LOGGER.info("Cache ++ : ( {}, {} ) |-> {}", new String[] { nomen_rfa, eval_rfa, poids + "" });
			}
		} finally {
			resultSet.close();
		}
		// watch.stop();
		// LOGGER.debug("Temps de chargement du cache PInsp : {}",
		// watch.toString());
	}

	/**
	 * Recupération de la note d'inspection.
	 * 
	 * @param ua
	 *            l'Uunité d'activité
	 * 
	 * @return Le poids d'inspection
	 * @throws SQLException
	 *             l'exception
	 */
	public Float getPInsp(UniteActivite ua) throws SQLException {

		NoteInspection ni = ua.getNoteDerniereInspection();
		if (ni == null) {
			return 1F;
		}

		String nomenRfa = ni.getNomenRfa();
		Map<String, Float> cacheNomen = CACHE_P_INSP.get(nomenRfa);
		if (cacheNomen == null) {
			return 1F;
		}

		String eval_rfa = ni.getEvalRfa();
		if (cacheNomen.get(eval_rfa) == null) {
			return 1F;
		}

		Float poids = cacheNomen.get(eval_rfa);

		return poids;
	}
}
