/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.dal.sial.arq.agent.dao;

import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.o2.sql.jdbc.CoreJDBCTemplate;
import java.sql.Connection;

/**
 * Un DAO "de base" qui n'est pas @Transaction'el.
 *
 * @author frederic.danna
 */
public abstract class BaseJBDCTemplateDAO extends CoreJDBCTemplate {

    class ConnectionHolder {

        @Inject
        protected Connection connection;

        public Connection getConnection() {
            return connection;
        }
    }

    /**
     * Connexion courante du Thread
     *
     * @return
     */
    protected Connection getConnection() {
        return new ConnectionHolder().getConnection();
    }
}
