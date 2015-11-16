package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.ElementBatchConstante;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Dao de la table Batch
 *
 */
public class BatchDAO extends BaseJBDCTemplateDAO {

    private PreparedStatement updateNbUATraiteesStatement;

    /**
     * Mise à jour de la table batch lors de son lancement
     *
     * @param batchId l'identifiant du batch
     * @param statut statut a affecté au batch
     *
     * @throws SQLException l'exception
     */
    public void updateStartBatch(int batchId, int statut) throws SQLException {
        String sql = ElementBatchConstante.UPDATE_START_BATCH;
        this.performUpdate(sql, statut, new Timestamp(new Date().getTime()), batchId);
        /* FIXME FDA 2014/10 Faire ce commit dans une autre transaction que celle des notes de risque. */
        getConnection().commit();
    }

    /**
     * Mise à jour de la table batch lors de sa fin de traitement
     *
     * @param batchId l'identifiant du batch
     * @param statut statut a affecté au batch
     *
     * @throws SQLException l'exception
     */
    public void updateStopBatch(int batchId, int statut) throws SQLException {
        String sql = ElementBatchConstante.UPDATE_STOP_BATCH;
        this.performUpdate(sql, statut, new Timestamp(new Date().getTime()), batchId);
        /* FIXME FDA 2014/10 Faire ce commit dans une autre transaction que celle des notes de risque. */
        getConnection().commit();
    }

    /**
     * Mise à jour du nombre d'UA du batch
     *
     * @param batchId l'identifiant du batch
     * @param nbUA le nombre d'UA a traité par le batch
     *
     * @throws SQLException l'exception
     */
    public void updateNbUaBatch(int batchId, int nbUA) throws SQLException {
        String sql = ElementBatchConstante.UPDATE_NB_UA_BATCH;
        // LOGGER.debug("Execution de la requete : {}", sql);
        this.performUpdate(sql, nbUA, batchId);
        /* FIXME FDA 2014/10 Faire ce commit dans une autre transaction que celle des notes de risque. */
        getConnection().commit();
    }

    /**
     * Mise à jour du nombre d'ua traitées du batch
     *
     * @param batchId l'identifiant du batch
     * @param nbUA le nombre d'UA traitées par le batch
     *
     * @throws SQLException l'exception
     */
    public void updateNbUaTraiteesBatch(int batchId, int nbUA) throws SQLException {
        updateNbUATraiteesStatement.setInt(1, nbUA);
        updateNbUATraiteesStatement.setLong(2, batchId);
        updateNbUATraiteesStatement.addBatch();
        updateNbUATraiteesStatement.executeBatch();
        /* FIXME FDA 2014/10 Faire ce commit dans une autre transaction que celle des notes de risque. */
        getConnection().commit();
    }

    public void initStatements() throws SQLException {
        updateNbUATraiteesStatement = this.getConnection().prepareStatement(
                ElementBatchConstante.UPDATE_NB_UA_TRAITE_BATCH);
    }
}
