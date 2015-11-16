package fr.gouv.agriculture.dal.sial.arq.business.rule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.gouv.agriculture.dal.sial.arq.business.Batch;
import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.dao.BatchDAO;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.security.authentication.OrionCredentials;

/**
 * Contrôle la règle de gestion ARQ_019_LancementCalcul
 *
 * @author fjperez
 */
public class ARQ019LancementCalculRule extends AbstractARQRule {

	@Inject
	private BatchDAO batchDao;
	/** Service de gestion de l'utilisateur */
	@Inject
	private HabilitationsService habilitationsService;

	/** Clé du message d'erreur 2 */
	private static final String MESSAGE_ERREUR_CONTROLE_2 = "CalculEtResultats.ruleARQ_019_LancementCalcul.controle2";
	/** Clé du message d'erreur 3 */
	private static final String MESSAGE_ERREUR_CONTROLE_3 = "CalculEtResultats.ruleARQ_019_LancementCalcul.controle3";
    
    /**
     * serial.
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public RuleReport validate(Object object) {
        RuleReport ruleReport = new RuleReport();

		VNbrua vnbrua = (VNbrua)object;

		// Contrôle 2
		// Si l’utilisateur a déjà un traitement de calcul de note de risque non
		// terminé pour le même couple
		// campagne/domaine technique, afficher le message suivant :
		// «Merci de bien vouloir attente la fin de votre calcul les notes de
		// risque pour la campagne et le domaine technique sélectionnés».
		try {

			OrionCredentials orionCredentials = habilitationsService.getUtilisateurConnecte();
			String batchUtiLb = orionCredentials.getLogin();
			String campRfa = vnbrua.getCampagne().getCampRfa();
			String dtRfa = vnbrua.getDomaineTechnique().getDtRfa();
			List<Batch> lstBatchCalculEnCours = batchDao.findNonTerminePourUtilisateurCampagneDomaineTechnique(
					batchUtiLb, campRfa, dtRfa);
			if (!CommonHelper.isEmpty(lstBatchCalculEnCours)) {
				RuleMessage message = new RuleMessage(Messages.getMessage(MESSAGE_ERREUR_CONTROLE_2));
				ruleReport.addMessage(message);
			}
		} catch (Exception e) {
			RuleMessage message = new RuleMessage(e.getMessage());
			ruleReport.addMessage(message);
		}

		// Contrôle 3
		// Si l’utilisateur a sélectionné une campagne dont la date de fin de
		// validité (V_CAMPAGNE_V2_0.CAMP_FIN_DT)
		// est inférieure à la date courante du système, alors le traitement
		// n’est pas lancé.
		// Un message est alorsaffiché avec le message suivant :
		// «Merci de bien vouloir sélectionner une campagne dont la date de fin
		// de validé n’est pas échue»
		if (vnbrua != null && vnbrua.getCampagne() != null && vnbrua.getCampagne().getCampFinDt() != null
				&& !isDateLessThan(getTodayAtZeroHour(), vnbrua.getCampagne().getCampFinDt())) {
			ruleReport
					.addMessage(new RuleMessage(Messages.getMessage(MESSAGE_ERREUR_CONTROLE_3), MessageSeverity.ERROR));

		}

		return ruleReport;
	}

	/**
	 * Vérifie si une date de référence est inférieure à une autre date. Si
	 * l'une des 2 dates est nulle, false est retourné.
	 * 
	 * @param dateRef
	 *            la date de référence
	 * @param dateToCompare
	 *            la date à comparer
	 * @return true, si la date de référence est inférieure, sinon false
	 */
	public static boolean isDateLessThan(final Date dateRef, final Date dateToCompare) {
		if (dateRef == null || dateToCompare == null) {
			return false;
		}
		if (dateRef.before(dateToCompare)) {
			return true;
		}
		return false;
	}

	/**
	 * Renvoie la date du jour à 0:00:00.<!-- -->0.
	 * 
	 * @return la date du jour à 0:00:00.0
	 */
	public static Date getTodayAtZeroHour() {
		final Calendar cal = Calendar.getInstance(Locale.FRENCH);
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
}
