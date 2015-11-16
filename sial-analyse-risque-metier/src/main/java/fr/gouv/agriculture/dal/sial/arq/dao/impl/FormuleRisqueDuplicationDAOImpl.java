package fr.gouv.agriculture.dal.sial.arq.dao.impl;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.arq.business.ModificationPonderation;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDestination;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationDiffusion;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheorique;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueApprobation;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProcede;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationRisqueTheoriqueProduit;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationVolume;
import fr.gouv.agriculture.dal.sial.arq.business.PonderationZone;
import fr.gouv.agriculture.dal.sial.arq.business.Produit;
import fr.gouv.agriculture.dal.sial.arq.dao.FormuleRisqueDuplicationDAO;
import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.helper.CommonHelper;
import fr.gouv.agriculture.orion.persistence.PersistenceException;

/**
 * DAO pour la duplication des Formules de risque
 * 
 * @author sopra
 * 
 */
public class FormuleRisqueDuplicationDAOImpl extends DefaultBusinessDAO implements FormuleRisqueDuplicationDAO {

	/**
	 * Duplication d'une formule de risque
	 * 
	 * @param businessContext
	 *            contexte
	 * @param formule
	 *            formule de risque à dupliquer
	 * @return Duplicata
	 * @throws PersistenceException
	 *             exception bdd
	 */
	public FormuleRisque createFormuleRisque(BusinessContext businessContext, FormuleRisque formule)
			throws PersistenceException {

		FormuleRisque res = (FormuleRisque) create(businessContext, FormuleRisque.class);

		if (formule != null) {
			res.setCampagne(formule.getCampagne());
			res.setDomaineTechnique(formule.getDomaineTechnique());
			res.setFormAuteurModifLb(formule.getFormAuteurModifLb());
			res.setFormCreationDt(formule.getFormCreationDt());
			res.setFormDerniereModifTs(formule.getFormDerniereModifTs());
			res.setFormId(formule.getFormId());
			res.setFromAuteurCreationLb(formule.getFromAuteurCreationLb());
			res.setFromCritDestinationOn(formule.isFromCritDestinationOn());
			res.setFromCritDiffusionOn(formule.isFromCritDiffusionOn());
			res.setFromCritNoteInspectionOn(formule.isFromCritNoteInspectionOn());
			res.setFromCritRisquetheoriqueOn(formule.isFromCritRisquetheoriqueOn());
			res.setFromCritVolumeOn(formule.isFromCritVolumeOn());
			res.setFromCritZoneOn(formule.isFromCritZoneOn());
			res.setModificationPonderations(formule.getModificationPonderations());
			// Copie de la liste des pondérations de type destination
			if (!CommonHelper.isEmpty(formule.getPonderationDestinations())) {
				res.setPonderationDestinations(new ArrayList<PonderationDestination>());
				for (PonderationDestination destination : formule.getPonderationDestinations()) {
					PonderationDestination newDestination = createPonderationDestination(businessContext, destination);
					newDestination.setFormuleRisque(res);
					res.getPonderationDestinations().add(newDestination);
				}
			}
			// Copie de la liste des pondérations de type diffusion
			if (!CommonHelper.isEmpty(formule.getPonderationDiffusions())) {
				res.setPonderationDiffusions(new ArrayList<PonderationDiffusion>());
				for (PonderationDiffusion diffusion : formule.getPonderationDiffusions()) {
					PonderationDiffusion newDiffusion = createPonderationDiffusion(businessContext, diffusion);
					newDiffusion.setFormuleRisque(res);
					res.getPonderationDiffusions().add(newDiffusion);
				}
			}
			// Copie de la liste des pondérations de type risque théorique
			if (!CommonHelper.isEmpty(formule.getPonderationRisqueTheoriques())) {
				res.setPonderationRisqueTheoriques(new ArrayList<PonderationRisqueTheorique>());
				for (PonderationRisqueTheorique risqueTheorique : formule.getPonderationRisqueTheoriques()) {
					PonderationRisqueTheorique newRisqueTheorique = createPonderationRisqueTheorique(businessContext,
							risqueTheorique);
					newRisqueTheorique.setFormuleRisque(res);
					res.getPonderationRisqueTheoriques().add(newRisqueTheorique);
				}
			}
			// Copie de la liste des pondérations de type volume
			if (!CommonHelper.isEmpty(formule.getPonderationVolumes())) {
				res.setPonderationVolumes(new ArrayList<PonderationVolume>());
				for (PonderationVolume volume : formule.getPonderationVolumes()) {
					PonderationVolume newVolume = createPonderationVolume(businessContext, volume);
					newVolume.setFormuleRisque(res);
					res.getPonderationVolumes().add(newVolume);
				}
			}
			// Copie de la liste des pondérations de type zone
			if (!CommonHelper.isEmpty(formule.getPonderationZones())) {
				res.setPonderationZones(new ArrayList<PonderationZone>());
				for (PonderationZone zone : formule.getPonderationZones()) {
					PonderationZone newZone = createPonderationZone(businessContext, zone);
					newZone.setFormuleRisque(res);
					res.getPonderationZones().add(newZone);
				}
			}
		}

		res.setFormId(null);
		res.setFromAuteurCreationLb(null);
		res.setFormCreationDt(null);
		res.setFormAuteurModifLb(null);
		res.setFormDerniereModifTs(null);
		res.setModificationPonderations(new ArrayList<ModificationPonderation>());

		return res;
	}

	/**
	 * Duplication d'une pondération de zone.
	 * 
	 * @param businessContext
	 *            Le BusinessContext dans lequel on crée la nouvelle instance.
	 * @param oldZone
	 *            La pondération de zone à dupliquer.
	 * 
	 * @return Une nouvelle instance de PonderationZone avec les même
	 *         caractèristiques que celle dupliquée.
	 * 
	 * @throws PersistenceException
	 */
	private PonderationZone createPonderationZone(BusinessContext businessContext, PonderationZone oldZone)
			throws PersistenceException {

		PonderationZone res = (PonderationZone) create(businessContext, PonderationZone.class);
		if (oldZone != null) {
			res.setZone(oldZone.getZone());
			res.setPzonePoidsNb2(oldZone.getPzonePoidsNb2());
		}
		return res;
	}

	/**
	 * Duplication d'une pondération de volume.
	 * 
	 * @param businessContext
	 *            Le BusinessContext dans lequel on crée la nouvelle instance.
	 * @param oldPonderationVolume
	 *            La pondération de volume à dupliquer.
	 * 
	 * @return Une nouvelle instance de PonderationVolume avec les même
	 *         caractèristiques que celle dupliquée.
	 * 
	 * @throws PersistenceException
	 */
	private PonderationVolume createPonderationVolume(BusinessContext businessContext,
			PonderationVolume oldPonderationVolume) throws PersistenceException {

		PonderationVolume res = (PonderationVolume) create(businessContext, PonderationVolume.class);
		if (oldPonderationVolume != null) {
			res.setTaRfa(oldPonderationVolume.getTaRfa());
			res.setUprodRfa(oldPonderationVolume.getUprodRfa());
			res.setPvolS1Nb2(oldPonderationVolume.getPvolS1Nb2());
			res.setPvolS2Nb2(oldPonderationVolume.getPvolS2Nb2());
			res.setPvolS3Nb2(oldPonderationVolume.getPvolS3Nb2());
			res.setPvolS4Nb2(oldPonderationVolume.getPvolS4Nb2());
		}
		return res;
	}

	/**
	 * Duplication d'une pondération de risque théorique.
	 * 
	 * @param businessContext
	 *            Le BusinessContext dans lequel on crée la nouvelle instance.
	 * @param oldPonderationRisqueTheo
	 *            La pondération de risque théorique à dupliquer.
	 * 
	 * @return Une nouvelle instance de PonderationRisqueTheorique avec les même
	 *         caractèristiques que celle dupliquée.
	 * 
	 * @throws PersistenceException
	 */
	private PonderationRisqueTheorique createPonderationRisqueTheorique(BusinessContext businessContext,
			PonderationRisqueTheorique oldPonderationRisqueTheo) throws PersistenceException {

		PonderationRisqueTheorique res = (PonderationRisqueTheorique) create(businessContext,
				PonderationRisqueTheorique.class);
		if (oldPonderationRisqueTheo != null) {
			res.setTypeActivite(oldPonderationRisqueTheo.getTypeActivite());
			res.setPrisqtheoPoidsNb2(oldPonderationRisqueTheo.getPrisqtheoPoidsNb2());

			res.setPonderationRisqueTheoriqueApprobations(new ArrayList<PonderationRisqueTheoriqueApprobation>());
			List<PonderationRisqueTheoriqueApprobation> oldApprobations = oldPonderationRisqueTheo
					.getPonderationRisqueTheoriqueApprobations();
			if (!CommonHelper.isEmpty(oldApprobations)) {
				for (PonderationRisqueTheoriqueApprobation approbation : oldApprobations) {

					PonderationRisqueTheoriqueApprobation newApprobation = (PonderationRisqueTheoriqueApprobation) create(
							businessContext, PonderationRisqueTheoriqueApprobation.class);
					newApprobation.setApprobation(approbation.getApprobation());
					newApprobation.setPonderationRisqueTheorique(res);

					res.getPonderationRisqueTheoriqueApprobations().add(newApprobation);
				}
			}

			res.setPonderationRisqueTheoriqueProcedes(new ArrayList<PonderationRisqueTheoriqueProcede>());
			List<PonderationRisqueTheoriqueProcede> oldProcedes = oldPonderationRisqueTheo
					.getPonderationRisqueTheoriqueProcedes();
			if (!CommonHelper.isEmpty(oldProcedes)) {
				for (PonderationRisqueTheoriqueProcede procede : oldProcedes) {

					PonderationRisqueTheoriqueProcede newProcede = (PonderationRisqueTheoriqueProcede) create(
							businessContext, PonderationRisqueTheoriqueProcede.class);
					newProcede.setProcede(procede.getProcede());
					newProcede.setPonderationRisqueTheorique(res);

					res.getPonderationRisqueTheoriqueProcedes().add(newProcede);
				}
			}

			res.setPonderationRisqueTheoriqueProduits(new ArrayList<PonderationRisqueTheoriqueProduit>());
			List<PonderationRisqueTheoriqueProduit> oldProduits = oldPonderationRisqueTheo
					.getPonderationRisqueTheoriqueProduits();
			if (!CommonHelper.isEmpty(oldProduits)) {
				for (PonderationRisqueTheoriqueProduit risqueTheoProduit : oldProduits) {

					PonderationRisqueTheoriqueProduit newRisqueTheoProduit = (PonderationRisqueTheoriqueProduit) create(
							businessContext, PonderationRisqueTheoriqueProduit.class);

					// Définition du produit avec une nouvelle instance
					Produit oldProduit = risqueTheoProduit.getProduit();
					Produit newProduit = createProduit(businessContext, oldProduit);
					newRisqueTheoProduit.setProduit(newProduit);

					newRisqueTheoProduit.setPonderationRisqueTheorique(risqueTheoProduit
							.getPonderationRisqueTheorique());
					newRisqueTheoProduit.setPonderationRisqueTheorique(res);

					res.getPonderationRisqueTheoriqueProduits().add(newRisqueTheoProduit);
				}
			}
		}

		return res;
	}

	/**
	 * Duplication d'un produit.
	 * 
	 * @param businessContext
	 *            Le BusinessContext dans lequel est créée la nouvelle instance.
	 * @param oldProduit
	 *            Le produit à dupliquer.
	 * 
	 * @return Une nouvelle instance de Produit avec les mêmes caractèristiques
	 *         que celle dupliquée.
	 * 
	 * @throws PersistenceException
	 */
	private Produit createProduit(BusinessContext businessContext, Produit oldProduit) throws PersistenceException {
		Produit newProduit = (Produit) create(businessContext, Produit.class);
		if (oldProduit != null) {
			newProduit.setProdRfa(oldProduit.getProdRfa());
			newProduit.setTypeProduit(oldProduit.getTypeProduit());
			newProduit.setAnima(oldProduit.getAnima());
			newProduit.setAlani(oldProduit.getAlani());
			newProduit.setIntra(oldProduit.getIntra());
			newProduit.setDenre(oldProduit.getDenre());
			newProduit.setVeget(oldProduit.getVeget());
		}
		return newProduit;
	}

	/**
	 * Duplication d'une pondération de diffusion.
	 * 
	 * @param businessContext
	 *            Le BusinessContext dans lequel on crée la nouvelle instance.
	 * @param oldPonderationDiffusion
	 *            La pondération de diffusion à dupliquer.
	 * 
	 * @return Une nouvelle instance de PonderationDiffusion avec les même
	 *         caractèristiques que celle dupliquée.
	 * 
	 * @throws PersistenceException
	 */
	private PonderationDiffusion createPonderationDiffusion(BusinessContext businessContext,
			PonderationDiffusion oldPonderationDiffusion) throws PersistenceException {

		PonderationDiffusion res = (PonderationDiffusion) create(businessContext, PonderationDiffusion.class);
		if (oldPonderationDiffusion != null) {
			res.setDifRfa(oldPonderationDiffusion.getDifRfa());
			res.setPdiffPoidsNb2(oldPonderationDiffusion.getPdiffPoidsNb2());
		}
		return res;
	}

	/**
	 * Duplication d'une pondération de destination.
	 * 
	 * @param businessContext
	 *            Le BusinessContext dans lequel on crée la nouvelle instance.
	 * @param oldPonderationDestination
	 *            La pondération de destination à dupliquer.
	 * 
	 * @return Une nouvelle instance de PonderationDestination avec les même
	 *         caractèristiques que celle dupliquée.
	 * 
	 * @throws PersistenceException
	 */
	private PonderationDestination createPonderationDestination(BusinessContext businessContext,
			PonderationDestination oldPonderationDestination) throws PersistenceException {

		PonderationDestination res = (PonderationDestination) create(businessContext, PonderationDestination.class);
		if (oldPonderationDestination != null) {
			res.setDestRfa(oldPonderationDestination.getDestRfa());
			res.setTaRfa(oldPonderationDestination.getTaRfa());
			res.setPdestPoidsNb2(oldPonderationDestination.getPdestPoidsNb2());
		}
		return res;
	}
}
