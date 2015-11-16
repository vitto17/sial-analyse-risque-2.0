package fr.gouv.agriculture.dal.sial.arq.policy;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.enumeration.TypeRoleUtilisateur;
import fr.gouv.agriculture.dal.sial.arq.service.HabilitationsService;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.list.ListController;
import fr.gouv.agriculture.orion.view.ListView;
import fr.gouv.agriculture.orion.view.ViewNotAvailableException;
import fr.gouv.agriculture.orion.view.faces.model.FacesListModel;
import fr.gouv.agriculture.orion.view.policy.DefaultViewPolicy;

/**
 * Cette view policy permet de vérifier certaines conditions sur chaque élément
 * de la liste des formules de risque contenue dans l'écran de recherche des
 * formules de risque.
 * 
 * @author jodurand
 * 
 */
public class FormuleRisqueListViewPolicy extends DefaultViewPolicy {

	@Inject(value = "formuleRisqueLC")
	private ListController formuleRisqueLC;

	@Inject
	private HabilitationsService habilitationsService;

	/**
	 * Cette méthode détermine si la pondération de la formule de risque doit
	 * s'ouvrir en modification/création.
	 * 
	 * @return Un booléen.
	 */
	public boolean isAfficherLienPonderationCrea() {
		boolean res = false;
		if (isUtilisateurGestion() && !isCampagneEchue()) {
			res = true;
		}

		return res;
	}

	/**
	 * Cette méthode détermine si la pondération de la formule de risque doit
	 * s'ouvrir en consultation.
	 * 
	 * @return Un booléen.
	 */
	public boolean isAfficherLienPonderationDetail() {
		boolean res = false;
		if (isUtilisateurConsultant() || (isUtilisateurGestion() && isCampagneEchue())) {
			res = true;
		}

		return res;
	}

	/**
	 * Cette méthode permet de déterminer s'il faut afficher ou non la liste des
	 * actions pour une formule de risque. On ne l'affiche que s'il y a au moins
	 * une action dans la liste.
	 * 
	 * @return true si il faut afficher la liste des actions, false sinon.
	 */
	public boolean isAfficherListeActions() {
		boolean res = Boolean.FALSE;
		FormuleRisque formule = getFormuleCourante();
		if (formule != null) {
			if (formule.isFromCritRisquetheoriqueOn() || formule.isFromCritZoneOn() || formule.isFromCritVolumeOn()
					|| formule.isFromCritDiffusionOn() || formule.isFromCritDestinationOn()
					|| isAfficherActionModifier()) {
				res = Boolean.TRUE;
			}
		}
		return res;
	}

	/**
	 * Cette méthode permet de déterminer s'il faut afficher ou non l'action de
	 * modification pour une formule de risque. Seuls les utilisateurs avec des
	 * droits de modification peuvent l'utiliser.
	 * 
	 * @return true s'il faut afficher l'action de modification, false sinon
	 */
	public boolean isAfficherActionModifier() {
		boolean res = Boolean.FALSE;
		if (isUtilisateurGestion()) {
			res = Boolean.TRUE;
		}
		return res;
	}

	/**
	 * Permet de vérifier si la campagne de la formule de risque est échue ou
	 * pas.
	 * 
	 * @param formule
	 *            La formule dont on veut vérifier si la campagne est échue.
	 * 
	 * @return true si la campagne est échue, false sinon
	 */
	public boolean isCampagneEchue(FormuleRisque formule) {
		Boolean retour = Boolean.FALSE;

		if (formule != null) {
			VCampagne campagne = formule.getCampagne();
			if (campagne != null) {
				Date today = Calendar.getInstance().getTime();
				Date campFinDt = campagne.getCampFinDt();
				if (campFinDt != null) {
					if (DateUtils.truncatedCompareTo(today, campFinDt, Calendar.DAY_OF_MONTH) > 0) {
						retour = Boolean.TRUE;
					} else {
						retour = Boolean.FALSE;
					}
				} else {
					retour = Boolean.FALSE;
				}
			}
		}

		return retour;
	}

	/**
	 * Permet de vérifier si la campagne de la formule de risque est échue ou
	 * pas.
	 * 
	 * @return true si la campagne est échue, false sinon
	 */
	public boolean isCampagneEchue() {
		return isCampagneEchue(getFormuleCourante());
	}

	/**
	 * Permet de récupérer l'objet courant de la liste des formules de risque.
	 * 
	 * @return la formule de risque courante si trouvée, null sinon
	 */
	private FormuleRisque getFormuleCourante() {
		ListView listView = formuleRisqueLC.getListView();
		FormuleRisque formuleCourante = null;

		int currentIndex = 0;
		try {
			// on récupère l'index courant du parcours de la table
			currentIndex = listView.getEditedRow();
			if (currentIndex != -1) {
				FacesListModel facesListModel = (FacesListModel) formuleRisqueLC.getListModel();
				// ici l'objet courant de la liste.
				formuleCourante = (FormuleRisque) facesListModel.getObjectAt(currentIndex);
			}
		} catch (ViewNotAvailableException e) {
			// On ne fait rien car la vue peut ne pas être encore initialisée
			// lorsque la viewPolicy est appelée
			formuleCourante = null;
		}
		return formuleCourante;
	}

	private Boolean isUtilisateurConsultant() {
		String nomRole = getCurrentRoleName();

		return nomRole.equals(TypeRoleUtilisateur.CA.getNomRole())
				|| nomRole.equals(TypeRoleUtilisateur.ALA.getNomRole());
	}

	private Boolean isUtilisateurGestion() {
		String nomRole = getCurrentRoleName();

		return nomRole.equals(TypeRoleUtilisateur.ANMA.getNomRole())
				|| nomRole.equals(TypeRoleUtilisateur.ASA.getNomRole());
	}

	private String getCurrentRoleName() {
		return habilitationsService.getRoleSialUtilisateurConnecte().getName();
	}
}
