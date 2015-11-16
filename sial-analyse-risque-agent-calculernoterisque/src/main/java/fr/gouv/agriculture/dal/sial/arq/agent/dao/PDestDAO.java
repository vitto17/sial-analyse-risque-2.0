package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.PDestConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao pour le calcul de la pondération de la destination.
 * 
 */
public class PDestDAO extends BaseJBDCTemplateDAO {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PDestDAO.class);

	private static final int CACHE_INITIAL_CAPACITY = 1000;

	private Map<String, Float> CACHE_P_DEST;

	/**
	 * Chargement du cache.
	 * 
	 * @param formule
	 *            la formule de risque
	 * @throws SQLException
	 *             si un problème survient
	 */
	public void chargeCache(FormuleRisque formule) throws SQLException {

		CACHE_P_DEST = new HashMap<String, Float>(CACHE_INITIAL_CAPACITY);

		// StopWatch watch = new StopWatch();
		// watch.start();
		String sql = PDestConstante.REQUETE_SELECT_DEST;
		// LOGGER.debug("Execution de la requete : {}", sql);
		ResultSet resultSet = this.performQuery(sql, formule.getFormId());

		try {
			while (resultSet.next()) {
				String destRfa = resultSet.getString(1);
				String typeActRfa = resultSet.getString(2);
				Float poids = resultSet.getFloat(3);
                String clef = clef(typeActRfa, destRfa);
				CACHE_P_DEST.put(clef, poids);
				LOGGER.info("Cache ++ : {} |-> {}", new String[] { clef, poids.toString() });
			}
		} finally {
			resultSet.close();
		}
		// watch.stop();
		// LOGGER.debug("Temps de chargement du cache PDestination : {}",
		// watch.toString());
	}

    private String clef(String typeActRfa, String destRfa) {
        return "<" + typeActRfa + "><" + destRfa + ">";
	}

	/**
	 * Recupération du poids max de la destination.
	 * 
	 * @param ua
	 *            Unite d'activité
	 * 
	 * @return Le poids de la pondération
	 * @throws SQLException
	 *             l'exception
	 */
	/*- TODO FDA 2014/10 Améliorer les perfs en gérant un cache à 2 niveaux : Type d'activité |-> Destination |-> Poids.
	 Exemple de cache :
	 {
	 "A_COM_DAOA" |-> {
	   "BOUCH" |-> 5,
	 "ANREN" |-> 10,
	 },
	   "A_COM_DAOA" |-> {
	 "BOVIN" |-> 7
	 }
	 }
	 */
	public Float getPDest(UniteActivite ua) throws SQLException {
		Float poidsMax = 1F;
		for (String destRfa : ua.getDestinations()) {
            Float poidsDest = CACHE_P_DEST.get(clef(ua.getTaRfa(), destRfa));
			if (poidsDest == null) {
				return 1F;
			}
			if (poidsDest > poidsMax) {
				poidsMax = poidsDest;
			}
		}
		return poidsMax;
	}

}
