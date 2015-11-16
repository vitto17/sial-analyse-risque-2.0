package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.ApprobationValideConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao pour les approbations valides.
 *
 */
public class ApprobationValideDAO extends BaseJBDCTemplateDAO {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApprobationValideDAO.class);

    private static final int CACHE_INITIAL_CAPACITY = 20;

    private Set<String> CACHE_APPRO_VALIDES;

    /**
     * Chargement du cache.
     *
     * @throws SQLException si un problème survient
     */
    public void chargeCache() throws SQLException {

        CACHE_APPRO_VALIDES = new HashSet<String>(CACHE_INITIAL_CAPACITY);

        // StopWatch watch = new StopWatch();
        // watch.start();
        String sql = ApprobationValideConstante.REQUETE_CHARGE_CACHE;
        // LOGGER.debug("Execution de la requete : {}", sql);
        ResultSet resultSet = this.performQuery(sql);

        try {
            while (resultSet.next()) {
                String vREAStatutLb = resultSet.getString(1);
                CACHE_APPRO_VALIDES.add(vREAStatutLb);
                LOGGER.info("Cache ++ : {}", new String[]{vREAStatutLb});
            }
        } finally {
            resultSet.close();
        }
        // watch.stop();
        // LOGGER.debug("Temps de chargement du cache ApprobationsValides : {}",
        // watch.toString());
    }

    public boolean contains(String libStatutAppro) {
        return CACHE_APPRO_VALIDES.contains(libStatutAppro);
    }
}
