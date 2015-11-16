package fr.gouv.agriculture.dal.sial.arq.service;

import fr.gouv.agriculture.orion.Controller;
import fr.gouv.agriculture.orion.context.ApplicationSession;
import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.BusinessDAO;
import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.message.InternalMessage;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.Messenger;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service permettant de vérifier si la liste à exporter est vide ou non. Permet
 * d'afficher le message d'erreur voulu "La liste à exporter ne contient aucun
 * élément" et non le message standard d'orion "Erreur technique dans la
 * génération du rapport"
 *
 * @author bdeat
 */
public abstract class AbstractExportService {

    /**
     * Retourne true si la liste à exporter est vide, false sinon
     *
     * @param businessSearchContext
     * @param controller
     * @return
     */
    public boolean validateExport(BusinessSearchContext businessSearchContext, Controller controller) {

        Collection collection = null;
        boolean isEmpty = false;
        try {
            // Récupération de la liste en fonction des critères définit
            collection = getBusinessDAO().findBy(businessSearchContext);
        } catch (Exception ex) {
            Logger.getLogger(AbstractExportService.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Si la liste est vide
        if (collection.isEmpty()) {
            // Ajout du message d'erreur
            final ApplicationSession applicationSession = controller.getApplicationSession();
            final Messenger messenger = applicationSession.getMessenger();
            messenger.addMessage(new InternalMessage(Messages.getMessage("commons.export.isEmpty"),
                    MessageSeverity.ERROR));
            isEmpty = true;
        }
        return isEmpty;

    }

    /**
     * Getter sur le DAO
     *
     * @return propertyName
     */
    public abstract BusinessDAO getBusinessDAO();
}
