package fr.gouv.agriculture.dal.sial.arq.action;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.controller.form.FormController;
import fr.gouv.agriculture.orion.query.Criterion;
import fr.gouv.agriculture.orion.query.Expression;
import fr.gouv.agriculture.orion.query.Operators;
import fr.gouv.agriculture.orion.query.Parameter;
import fr.gouv.agriculture.orion.query.Query;
import fr.gouv.agriculture.orion.query.expression.Conjunction;
import fr.gouv.agriculture.orion.query.expression.Junction;
import fr.gouv.agriculture.orion.query.expression.SimpleExpression;

/**
 * Action générique pour les export des Ponderations d'une Formule de risque.
 * 
 * @author fjperez
 */
public abstract class AbstractExportPonderationAction extends AbstractExportAction{
    
    static final String FORMULE_RISQUE_PARAM_NAME = "formuleRisque";
    
    @Override
    public Controller getExportController() {
        return (Controller) getController().getControllers().values().iterator().next();
    }
    
    @Override
    public BusinessSearchContext getExportBusinessSearchContext() {
        BusinessSearchContext businessSearchContext = new BusinessSearchContext();
        FormController formController = (FormController) getController();
        FormuleRisque formuleRisque = (FormuleRisque) formController.getFormModel().getObject();
        
        Query query = new Query(getBussinesClass());
        Expression expFormuleRisque = new SimpleExpression(new Criterion(FORMULE_RISQUE_PARAM_NAME), Operators.EQUALS,new Parameter(formuleRisque));
        //Creation de la requete
        Junction expression = new Conjunction();
        expression.add(expFormuleRisque);
        
        query.setExpression(expression);
        
        businessSearchContext.setQuery(query);
        return businessSearchContext;
    }
    
    /**
     * Obtenir l'objet metier correspondant à l'export
     * @return
     */
    public abstract Class getBussinesClass();
}
