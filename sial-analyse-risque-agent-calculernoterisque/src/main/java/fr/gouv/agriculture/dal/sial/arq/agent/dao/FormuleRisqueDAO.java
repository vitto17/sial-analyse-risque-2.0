package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.FormuleRisqueConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.FormuleRisque;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Le DAO d'acces aux formules de risques.
 * 
 */
public class FormuleRisqueDAO extends BaseJBDCTemplateDAO {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(FormuleRisqueDAO.class);

	/**
	 * Recupération de la formule de risque selon la campagne et le Domaine
	 * Technique.
	 * 
	 * @param dtRfa
	 *            le code Rfa du domaine technique
	 * @param campRfa
	 *            le code Rfa de la campagne
	 * 
	 * @return Liste des valeurs retournées
	 * @throws SQLException
	 *             l'exception
	 */
	public FormuleRisque recupFormuleRisque(String dtRfa, String campRfa) throws SQLException {
		StopWatch watch = new StopWatch();
		watch.start();
		String sql = FormuleRisqueConstante.REQUETE_SELECT_FORM;
        //LOGGER.debug("Execution de la requete : {}", sql);

		ResultSet resultSet = this.performQuery(sql, dtRfa, campRfa);
		FormuleRisque form = new FormuleRisque();
		try {
			if (resultSet.next()) {
                form.setFormId(resultSet.getLong(FormuleRisqueConstante.FORM_ID));
				form.setPDestActif(resultSet.getBoolean(FormuleRisqueConstante.DESTINATION));
				form.setPDiffActif(resultSet.getBoolean(FormuleRisqueConstante.DIFFUSION));
				form.setPNiActif(resultSet.getBoolean(FormuleRisqueConstante.INSPECTION));
				form.setPRisqueActif(resultSet.getBoolean(FormuleRisqueConstante.RISQUE_THEORIQUE));
				form.setPVolActif(resultSet.getBoolean(FormuleRisqueConstante.VOLUME));
				form.setPZoneActif(resultSet.getBoolean(FormuleRisqueConstante.ZONE));
                form.setCampRfa(resultSet.getString(FormuleRisqueConstante.CAMP_RFA));
            }
		} finally {
			resultSet.close();
		}
		watch.stop();
		LOGGER.info("Temps de Traitement Formule Risque : {}", watch.toString());
		return form;
	}

}
