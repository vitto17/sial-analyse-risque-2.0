package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.PZoneConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao pour le calcul de la pondération de la zone.
 * 
 */
public class PZoneDAO extends BaseJBDCTemplateDAO {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PZoneDAO.class);

	private static final int CACHE_INITIAL_CAPACITY = 50;

	private Map<String, Float> CACHE_P_ZONE;

	/**
	 * Chargement du cache.
	 * 
	 * @param formule
	 *            la formule de risque
	 * @throws SQLException
	 *             si un problème survient
	 */
	public void chargeCache(FormuleRisque formule) throws SQLException {

		CACHE_P_ZONE = new HashMap<String, Float>(CACHE_INITIAL_CAPACITY);

		// StopWatch watch = new StopWatch();
		// watch.start();
		String sql = PZoneConstante.REQUETE_SELECT_ZONE;
        //LOGGER.debug("Execution de la requete : {}", sql);
		ResultSet resultSet = this.performQuery(sql, formule.getFormId());

		try {
			while (resultSet.next()) {
				String zoneRfa = resultSet.getString(1);
				Float poids = resultSet.getFloat(2);

				CACHE_P_ZONE.put(zoneRfa, poids);

				LOGGER.info("Cache ++ : {} |-> {}", new String[] { zoneRfa, poids + "" });
			}
		} finally {
			resultSet.close();
		}
		// watch.stop();
		// LOGGER.debug("Temps de chargement du cache PZone : {}",
		// watch.toString());
	}

	/**
	 * Recupération du poids max de la zone géographique.
	 * 
	 * @param ua
	 *            L'unité d'activité
	 * 
	 * @return Liste des valeurs
	 * @throws SQLException
	 *             l'exception
	 */
	public Float getPZone(UniteActivite ua) throws SQLException {
		Float poidsMax = 1F;

		for (String zoneRfa : ua.getZonesRfa()) {
			Float poidsZone = CACHE_P_ZONE.get(zoneRfa);
			if (poidsZone == null) {
				// Cf. règle de gestion NOTE_P_ZONE_NB2
				return 1F;
			}
			if (poidsZone > poidsMax) {
				poidsMax = poidsZone;
			}
		}

		return poidsMax;
	}

}
