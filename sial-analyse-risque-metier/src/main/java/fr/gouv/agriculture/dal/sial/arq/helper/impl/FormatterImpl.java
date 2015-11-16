package fr.gouv.agriculture.dal.sial.arq.helper.impl;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.dal.sial.arq.helper.Formatter;
import fr.gouv.agriculture.dal.sial.nomenclatures.constants.Constantes;
import java.util.Locale;

/**
 * Classe utilitataire de formatage des données
 * @author pbarreau
 *
 */
public class FormatterImpl implements Formatter {
    
    /** espace */
    private final static String SPACE = " ";
    
    /** Format de date pour le suivi de modification */
    private final static String DATE_FORMAT_LOG = "dd/MM/yyyy";
    
    /** Format des dates utilisés. */
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    
    /** Formateur de date. */
    public static final DateFormat DATETIME_FORMATER= new SimpleDateFormat(DATETIME_FORMAT,Locale.FRANCE);
    
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.arq.helper.Formatter#formatSuiviModification(java.lang.String, java.util.Date)
     */
    @Override
    public String formatSuiviModification(String login, Date date) {
        
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(login) && date != null) {
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    DATE_FORMAT_LOG);
            
            builder.append(Messages.getMessage("common.suivi.le.lb"));
            builder.append(SPACE);
            builder.append(simpleDateFormat.format(date));
            builder.append(SPACE);
            builder.append(Messages.getMessage("common.suivi.par.lb"));
            builder.append(SPACE);
            builder.append(login);
        }
        return builder.toString();
        
    }
    
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.nomenclatures.utils.Formatter#date2String(java.util.Date)
     */
    @Override
    public String dateTime2String(Date date) {
        String resultat = "";
        
        if (date != null) {
            resultat = DATETIME_FORMATER.format(date);
        }
        return resultat;
    }
    
    /* (non-Javadoc)
     * @see fr.gouv.agriculture.dal.sial.nomenclatures.utils.Formatter#date2String(java.util.Date)
     */
    @Override
    public String date2String(Date date) {
        String resultat = "";
        
        if (date != null) {
            resultat = Constantes.DATE_FORMATER.format(date);
        }
        return resultat;
    }
    
}
