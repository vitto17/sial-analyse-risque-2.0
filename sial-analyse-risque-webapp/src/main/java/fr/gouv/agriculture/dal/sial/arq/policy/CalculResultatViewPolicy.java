package fr.gouv.agriculture.dal.sial.arq.policy;

import fr.gouv.agriculture.dal.sial.arq.enumeration.TypeRoleUtilisateur;
import fr.gouv.agriculture.habilitations.security.authorization.RoleSial;
import fr.gouv.agriculture.habilitations.serviceshared.IHabilitationsSialService;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.view.policy.DefaultViewPolicy;

/**
 * Cette view policy permet de vérifier certaines conditions sur l'écran de
 * calcul et résultats des notes de risque.
 * 
 * @author bdeat
 * 
 */
public class CalculResultatViewPolicy extends DefaultViewPolicy {

    @Inject
    private IHabilitationsSialService habilitationsService;

    /**
     * Cette méthode détermine si le bouton "Calculer" doit être afficher ou non
     * 
     * @return false si role CA, true sinon.
     */
    public boolean isAfficherCalculer() {
        boolean res = false;

        RoleSial role = habilitationsService.getRoleSialUtilisateurConnecte();

        if (!TypeRoleUtilisateur.CA.getNomRole().equals(role.getName())) {
            res = true;
        }

        return res;
    }

}
