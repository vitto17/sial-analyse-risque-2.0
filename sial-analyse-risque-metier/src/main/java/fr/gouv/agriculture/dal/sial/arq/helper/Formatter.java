package fr.gouv.agriculture.dal.sial.arq.helper;

import java.util.Date;

/**
 * Classe Utilitaire pour formater les données.
 *
 * @author sopra
 */
public interface Formatter {
    
    /**
     * Formate la donnée à afficher pour le suivi de modification
     * @param login nom de l'utilisateur
     * @param date date modification
     * @return Suivi de modification formatté
     */
    public String formatSuiviModification(String login,Date date);
    
    /**
     * Formate une dateTime.
     *
     * @param date
     *            à formater
     * @return dateTime formatée ("dd/mm/yyyy hh:mm:ss")
     */
    public String dateTime2String(Date date);
    
    /**
     * Formate une dateTime.
     *
     * @param date
     *            à formater
     * @return dateTime formatée ("dd/mm/yyyy")
     */
     public String date2String(Date date);
}
