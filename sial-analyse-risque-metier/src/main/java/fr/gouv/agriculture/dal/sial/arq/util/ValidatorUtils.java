/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.util;

import fr.gouv.agriculture.orion.business.Checkable;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.message.RuleReport;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Classe utilitaire pour lancer les rules sur un objet métier et lancer
 * également les rules sur ses éventuels attributs de type Checkable.
 *
 * @author alerat
 */
public final class ValidatorUtils {

	/** prefixe des getter */
	private final static String PREFIX_GET = "get";
	
    /**
     * Classe Utilitaire.
     */
    private ValidatorUtils() {
    }

    /**
     * Permet de valider un objet métier ainsi que tous ses attributs si ceux-ci
     * sont eux-mêmes des objets "Checkable".
     *
     * Application de la méthode "validate" à chaque objets.
     *
     * @param checkable l'objet à controller
     * @return le report Orion
     * @throws Exception si un problème survient lors de l'introspection de
     * l'objet
     */
    public static RuleReport validateObject(Checkable checkable) throws Exception {
        RuleReport report = new RuleReport();
        if (checkable != null) {
            // On valide l'objet lui même
            report.addReport(checkable.validate());

            Field[] champs = checkable.getClass().getDeclaredFields();

            // On boucle sur l'ensemble des attributs de l'objet
            for (int i = 0; i < champs.length; i++) {
                Field field = champs[i];

                // On ne check pas les champs static et les objets de type
                // primitif
                if (!Modifier.isStatic(field.getModifiers()) && !field.getType().isPrimitive()) {
                    Method method = checkable.getClass().getMethod(PREFIX_GET + StringUtils.capitalize(field.getName()));
                    Object attributValue = method.invoke(checkable);
                    if (attributValue != null) {

                        if (List.class.isAssignableFrom(field.getType())) {
                            List<?> liste = (List<?>) attributValue;
                            if (!CommonHelper.isEmpty(liste)) {
                                for (Object o : liste) {
                                    if (o != null && Checkable.class.isAssignableFrom(o.getClass())) {
                                        report.addReport(((Checkable) o).validate());
                                    }
                                }
                            }
                        } else if (Checkable.class.isAssignableFrom(field.getType())) {
                            report.addReport(((Checkable) attributValue).validate());
                        }
                    }
                }
            }
        }
        return report;
    }
}

