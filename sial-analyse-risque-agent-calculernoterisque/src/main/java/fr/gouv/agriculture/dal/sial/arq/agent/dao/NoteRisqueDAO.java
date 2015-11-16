package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.dal.sial.arq.agent.constante.NoteRisqueConstante;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.ElementBatch;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.NoteRisque;
import fr.gouv.agriculture.dal.sial.arq.agent.domaine.UniteActivite;
import fr.gouv.agriculture.o2.kernel.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao de mise à jour de la note de risque
 * 
 */
public class NoteRisqueDAO extends BaseJBDCTemplateDAO {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoteRisqueDAO.class);

	private PreparedStatement updateNoteRisqueStatement;
	private PreparedStatement insertNoteRisqueStatement;

	/**
	 * Nombre de Note Mises à jour avant d'effectuer un commit
	 */
	@Inject
	private int lotCommitNotesMaj;

	public void initStatements() throws SQLException {
		updateNoteRisqueStatement = this.getConnection().prepareStatement(
				NoteRisqueConstante.REQUETE_UPDATE_NOTE_RISQUE);
		insertNoteRisqueStatement = this.getConnection().prepareStatement(
				NoteRisqueConstante.REQUETE_INSERT_NOTE_RISQUE);
	}

	/**
	 * Mets à jour la note de risque dans la table Note_risque.
	 * 
	 * @param noteRisque
	 *            la note de risque à mettre à jour
	 * @param element
	 *            les données contenant la campagne et le domaine technique
	 * @param ua
	 *            l'Ua en cours de traitement
	 * @param nbNoteUpdate
	 *            le nombre de Note traitées
	 * 
	 * @return le nombre de notes traitées.
	 * @throws SQLException
	 *             l'exception
	 */
	public int updateNoteRisque(NoteRisque noteRisque, ElementBatch element, UniteActivite ua, int nbNoteUpdate,
			long nbrUATraitees, long nbrUATotal) throws SQLException {

		int noteUpdate = nbNoteUpdate;

		// Si on avait une note avant :
		if (ua.getNoteActuelle() != null) {

			// Comparaison des 2 notes
			// LOGGER.debug("Note actuelle : " + ua.getNoteActuelle());
			if (noteRisque.getNoteValeur().equals(ua.getNoteActuelle().getNoteValeur())) {
				// La note de risque est identique, rien à mémoriser en BDD.
			} else {
				// On met à jour la ligne dans la table
				updateNoteRisqueStatement.setLong(1, noteRisque.getNoteValeur());
				updateNoteRisqueStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
				setParamSQL(updateNoteRisqueStatement, 3, noteRisque.getNoteRisquePond(), java.sql.Types.FLOAT);
				setParamSQL(updateNoteRisqueStatement, 4, noteRisque.getNoteVolume(), java.sql.Types.INTEGER);
				setParamSQL(updateNoteRisqueStatement, 5, noteRisque.getNoteZone(), java.sql.Types.FLOAT);
				setParamSQL(updateNoteRisqueStatement, 6, noteRisque.getNoteDiff(), java.sql.Types.FLOAT);
				setParamSQL(updateNoteRisqueStatement, 7, noteRisque.getNoteDest(), java.sql.Types.FLOAT);
				setParamSQL(updateNoteRisqueStatement, 8, noteRisque.getNoteInspection(), java.sql.Types.FLOAT);
				updateNoteRisqueStatement.setLong(9, ua.getNoteActuelle().getNoteValeur());
				updateNoteRisqueStatement.setLong(10, ua.getNoteActuelle().getId());
				updateNoteRisqueStatement.addBatch();

				noteUpdate++;
			}
		} else {
			// On n'a encore jamais calculé de note pour cette UA/campagne/DT,
			// on insere une nouvelle ligne :
			String rfa = new StringBuilder(ua.getUaRfa()).append("_").append(element.getDtRfa()).append("_")
					.append(element.getCampagneRfa()).toString();
			insertNoteRisqueStatement.setString(1, rfa);
			insertNoteRisqueStatement.setString(2, ua.getUaRfa());
			insertNoteRisqueStatement.setString(3, element.getDtRfa());
			insertNoteRisqueStatement.setString(4, element.getCampagneRfa());
			insertNoteRisqueStatement.setLong(5, noteRisque.getNoteValeur());
			insertNoteRisqueStatement.setTimestamp(6, new Timestamp(new Date().getTime()));
			setParamSQL(insertNoteRisqueStatement, 7, noteRisque.getNoteRisquePond(), java.sql.Types.DECIMAL);
			setParamSQL(insertNoteRisqueStatement, 8, noteRisque.getNoteVolume(), java.sql.Types.INTEGER);
			setParamSQL(insertNoteRisqueStatement, 9, noteRisque.getNoteZone(), java.sql.Types.DECIMAL);
			setParamSQL(insertNoteRisqueStatement, 10, noteRisque.getNoteDiff(), java.sql.Types.DECIMAL);
			setParamSQL(insertNoteRisqueStatement, 11, noteRisque.getNoteDest(), java.sql.Types.DECIMAL);
			setParamSQL(insertNoteRisqueStatement, 12, noteRisque.getNoteInspection(), java.sql.Types.DECIMAL);
			insertNoteRisqueStatement.setNull(13, java.sql.Types.BIGINT);
			insertNoteRisqueStatement.addBatch();

			noteUpdate++;
		}

        noteUpdate = sauvegardeNotesSiNecessaire(noteUpdate, nbrUATraitees, nbrUATotal);

		return noteUpdate;
	}

    public int sauvegardeNotesSiNecessaire(int nbrNotesCalculeesNonSauvegardees, long nbrUATraitees, long nbrUATotal) throws SQLException {
        // Si le nombre d'update/insert des notes atteint la taille du lot, ou
        // qu'on est en fin de traitment, on effectue un commit.
        if ((nbrNotesCalculeesNonSauvegardees == lotCommitNotesMaj) || (nbrUATraitees == nbrUATotal)) {
            // StopWatch watch = new StopWatch();
            // watch.start();
            
            updateNoteRisqueStatement.executeBatch();
            insertNoteRisqueStatement.executeBatch();
            
            this.getConnection().commit();
            
            // watch.stop();
            // LOGGER.debug("Temps de Insert/Update + commit de {} UA : {}",
            // noteUpdate, watch.toString());
            
            nbrNotesCalculeesNonSauvegardees = 0;
        }
        return nbrNotesCalculeesNonSauvegardees;
    }

	/**
	 * @param lotCommitNotesMaj
	 *            the lotCommitNotesMaj to set
	 */
	public void setLotCommitNotesMaj(int lotCommitNotesMaj) {
		this.lotCommitNotesMaj = lotCommitNotesMaj;
	}

	private void setParamSQL(PreparedStatement statement, int positionParam, Object value, int sqlTypeId)
			throws SQLException {
		if (value == null) {
			statement.setNull(positionParam, sqlTypeId);
		} else {
			statement.setObject(positionParam, value, sqlTypeId);
		}
	}
}
