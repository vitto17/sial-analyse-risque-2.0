package fr.gouv.agriculture.dal.sial.arq.controller.list;

import java.util.Collection;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.operator.In;

/**
 * Cette classe permet de contrôler la liste des formules de risque.
 *
 * @author jodurand
 *
 */
public class FormuleRisqueListController extends ListController {

    /**
     * Service de gestion de l'utilisateur
     */
    @Inject
    private HabilitationsService habilitationsService;
    
    /**
     * {@inheritDoc}
     *
     * @return
     * @see
     * fr.gouv.agriculture.orion.controller.list.ListController#findBusinesses
     * (fr.gouv.agriculture.orion.context.BusinessSearchContext)
     */
    @Override
    protected Collection<?> findBusinesses(BusinessSearchContext businessSearchContext) throws Exception {
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
        
        addFilterDomaineTechnique(junctionGlobal);

        // on mets la nouvelle junction dans la query
        query.setExpression(junctionGlobal);

        try {
            return super.findBusinesses(businessSearchContext);
        } finally {
            // on doit laisser la query propre
            query.setExpression(expressionInitiale);
        }
    }

    /**
     * Permet d'afficher uniquement les formule risques appartenant au domaine
     * technique habilité pour l'utilisateur
     *
     * @param expression
     * @throws Exception
     */
    private void addFilterDomaineTechnique(Junction junction) throws Exception {
        
        List<VDomaineTechnique> listDts = habilitationsService.getAttCompDomainesTechniquesRoleConnecteFiltre();
        if (!listDts.isEmpty()) {
            In inOperator =  (In) Operators.IN;
            inOperator.setReverse(true);
            Expression expDT = new SimpleExpression(new Criterion("domaineTechnique"),
                  inOperator , new Parameter(listDts));
            junction.add(expDT);
        }

    }
}
