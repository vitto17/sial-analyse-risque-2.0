package fr.gouv.agriculture.dal.sial.arq.controller.combobox;

import java.util.Collection;
import java.util.Date;

import fr.gouv.agriculture.dal.sial.arq.service.CampagneService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Disjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * Controller pour Combobox Campagne
 *
 * @author sopra
 */
public class CampagneComboBoxController extends ListController {

    /**
     * Critere: date de fin de validité
     */
    private static final String CRIT_DATE_FIN = "campFinDt";
    /**
     * Service de gestion de la nomenclature Campagne
     */
    @Inject
    private CampagneService campagneService;

    /**
	 * Ce champ permet de définir si l'on veut filtrer les campagnes en fonction
	 * de leur date d'échéance.
	 */
	private Boolean isFiltreFinDtActive = Boolean.TRUE;

	@SuppressWarnings("javadoc")
	public Boolean getIsFiltreFinDtActive() {
		return isFiltreFinDtActive;
	}

	@SuppressWarnings("javadoc")
	public void setIsFiltreFinDtActive(Boolean isFiltreFinDtActive) {
		this.isFiltreFinDtActive = isFiltreFinDtActive;
	}

	/**
	 * Retourne la valeur par defaut de la campagne
	 * 
	 * @return Valeur par defaut de la campagne
	 * @throws Exception
	 *             exception
	 */
    public VCampagne getDefaultValue() throws Exception {
        return campagneService.getCurrentCampagne();
    }

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.gouv.agriculture.orion.controller.list.ListController#findBusinesses
	 *      (fr.gouv.agriculture.orion.context.BusinessSearchContext)
	 */
	@Override
	protected Collection<?> findBusinesses(BusinessSearchContext businessSearchContext) throws Exception {
		if (isFiltreFinDtActive) {
			// on obtient la query
			Query query = businessSearchContext.getQuery();
			// on obtient l'expression initialie pour le sauvegarder
			Expression expressionInitiale = query.getExpression();
			// on crée une nouvelle junction
			Junction junctionGlobal = new Conjunction();
			// si la expression initial n'est pas vide, on l'ajoute
			if (expressionInitiale != null) {
				junctionGlobal.add(expressionInitiale);
			}

			Date currentDate = new Date();
			Expression expDateFinNull = new SimpleExpression(new Criterion(CRIT_DATE_FIN), Operators.IS_NULL, null);
			Expression expDateFinInf = new SimpleExpression(new Criterion(CRIT_DATE_FIN),
					Operators.GREATER_EQUALS_THAN, new Parameter(currentDate));

			// Creation de la requete
			Junction juncDateFin = new Disjunction();
			juncDateFin.add(expDateFinNull);
			juncDateFin.add(expDateFinInf);

			junctionGlobal.add(juncDateFin);

			// on mets la nouvelle junction dans la query
			query.setExpression(junctionGlobal);

			try {
				return super.findBusinesses(businessSearchContext);
			} finally {
				// on doit laisser la query propre
				query.setExpression(expressionInitiale);
			}
		} else {
			return super.findBusinesses(businessSearchContext);
		}
	}
}
