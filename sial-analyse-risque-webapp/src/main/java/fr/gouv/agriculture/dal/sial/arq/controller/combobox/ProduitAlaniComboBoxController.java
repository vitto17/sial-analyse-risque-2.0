/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.controller.combobox;

import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VTypeActivite;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Join;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;
import fr.gouv.agriculture.orion.query.join.InnerJoin;
import java.util.Collection;

/**
 * Controller pour Combobox Alimentation animale utilisé dans pondération du
 * risque théorique.
 *
 * @author pegaltier
 */
public class ProduitAlaniComboBoxController extends ListController {

    private VTypeActivite typeActivite;

    /**
     * {@inheritDoc}
     *
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

        // Contextualisation des Alimenations animale avec le type d'activité passé en parametre
        Join joinCtx = new InnerJoin("ctxTaAas", "joinCtxTaAas");
        query.addJoin(joinCtx);
        Expression expression = new SimpleExpression(new Criterion("typeActivite", null, null, "joinCtxTaAas"),
                Operators.EQUALS, new Parameter(typeActivite));
        junctionGlobal.add(expression);

        // on mets la nouvelle junction dans la query
        query.setExpression(junctionGlobal);

        try {
            return super.findBusinesses(businessSearchContext);
        } finally {
            // on doit laisser la query propre
            query.setExpression(expressionInitiale);
            if (joinCtx != null) {
                query.getJoins().remove(joinCtx);
            }
        }
    }

    public VTypeActivite getTypeActivite() {
        return typeActivite;
    }

    public void setTypeActivite(VTypeActivite typeActivite) {
        this.typeActivite = typeActivite;
    }
}
