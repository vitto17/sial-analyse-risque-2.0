package fr.gouv.agriculture.dal.sial.arq.action;

import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.VNbrua;
import fr.gouv.agriculture.dal.sial.arq.business.rule.ARQ019LancementCalculRule;
import fr.gouv.agriculture.dal.sial.arq.controller.list.CalculEtResultatsListController;
import fr.gouv.agriculture.dal.sial.arq.service.BatchService;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.BusinessAction;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.rule.RuleException;

/**
 *
 * @author fjperez
 */
public class CalculerNotesRisquesAction extends BusinessAction {

	/** Clé du message d'erreur 1 */
	private static final String MESSAGE_ERREUR_CONTROLE_1 = "CalculEtResultats.ruleARQ_019_LancementCalcul.controle1";
	/** Nom du critère Campagne dans le formulaire de recherche des NbrUa */
	private static final String CAMPAGNE_CRITERE_NAME = "campagne";
	/**
	 * Nom du critère Domaine Technique dans le formulaire de recherche des
	 * NbrUa
	 */
	private static final String DOMAINE_TECHNIQUE_CRITERE_NAME = "domaineTechnique";

	@Inject
	private BatchService batchService;
	@Inject
	private HabilitationsService habilitationsService;
    
	@SuppressWarnings("unchecked")
	@Override
    public final Object execute() throws Exception {

		VCampagne paramCampagne = null;

		VDomaineTechnique paramDt = null;
		
		VNbrua nbrUa = null;
		
        // on récupére le controleur
		CalculEtResultatsListController listController = (CalculEtResultatsListController) getController();

		// On définit le RuleReport
		RuleReport ruleReport = new RuleReport();

			Query query = listController.getQuery();
			if (query != null) {
				nbrUa = (VNbrua) listController.getBusinessDao().create(new BusinessContext(), VNbrua.class);
				nbrUa.setNbrUa(Integer.valueOf("0"));

				// On récupère la valeur de la campagne utilisée lors de la
				// dernière recherche
				List<Expression> expressionCampagne = query.findExpressionByName(CAMPAGNE_CRITERE_NAME);
				if (!CommonHelper.isEmpty(expressionCampagne)) {
					SimpleExpression simpleExpressionCampagne = (SimpleExpression) expressionCampagne.iterator().next();

					if (simpleExpressionCampagne.getValue() != null) {
						Parameter parameter = (Parameter) simpleExpressionCampagne.getValue();
						paramCampagne = (VCampagne) parameter.getValue();
					}
				}

				// On récupère la valeur du domaine technique utilisé lors de la
				// dernière recherche
				List<Expression> expressionDT = query.findExpressionByName(DOMAINE_TECHNIQUE_CRITERE_NAME);
				if (!CommonHelper.isEmpty(expressionDT)) {
					SimpleExpression simpleExpressionDT = (SimpleExpression) expressionDT.iterator().next();

					if (simpleExpressionDT.getValue() != null) {
						Parameter parameter = (Parameter) simpleExpressionDT.getValue();
						paramDt = (VDomaineTechnique) parameter.getValue();
					}
				}

				// On vérifie que les deux paramètres, campagne et domaine
				// technique, ont été renseignés
				if (paramCampagne == null || paramDt == null) {
					ruleReport.addMessage(new RuleMessage(Messages.getMessage(MESSAGE_ERREUR_CONTROLE_1),
							MessageSeverity.ERROR));
					throw new RuleException(ruleReport);
				}

				nbrUa.setCampagne(paramCampagne);
				nbrUa.setDomaineTechnique(paramDt);

			} else {
				ruleReport.addMessage(new RuleMessage(Messages.getMessage(MESSAGE_ERREUR_CONTROLE_1),
						MessageSeverity.ERROR));
				throw new RuleException(ruleReport);
			}


        // règle de gestion pour l'archivage
        ARQ019LancementCalculRule rule = new ARQ019LancementCalculRule();

        // on execute la règle pour les contrôles
		ruleReport.addReport(rule.validate(nbrUa));
		// s'il y'a eu des erreurs
        if (ruleReport.containsError()) {
            throw new RuleException(ruleReport);
        }

		batchService.insertBatchFromVNbUa(paramCampagne,paramDt, habilitationsService.getAttCompStructRfaRoleConnecte());

        return super.execute();
    }    
}