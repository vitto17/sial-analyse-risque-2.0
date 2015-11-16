package fr.gouv.agriculture.dal.sial.arq.business.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import fr.gouv.agriculture.dal.sial.arq.action.rule.ARQ003SupprimerFormulesRule;
import fr.gouv.agriculture.dal.sial.arq.business.FormuleRisque;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VCampagne;
import fr.gouv.agriculture.dal.sial.nomenclatures.businessshared.VDomaineTechnique;
import fr.gouv.agriculture.o2.tests.runner.UnitilsJunit4WithRulesRunner;
import fr.gouv.agriculture.orion.message.RuleReport;

/**
 * Class de test des regles de contrôle pour le lancement du calcul des notes de
 * risques
 * 
 * @author fjperez
 */
@RunWith(UnitilsJunit4WithRulesRunner.class)
public class ARQ003SupprimerFormulesRuleTestCase {

	@TestedObject
	private ARQ003SupprimerFormulesRule rule;

	private final boolean UP = true;
	private final boolean DOWN = false;

	@Before
	public void doBefore() {
	}

	/**
	 * Si l'utilisateur a sélectionné une formule de risque dont la campagne est
	 * échue, alors le traitement n'est pas lancé.
	 * 
	 * Le message suivant est affiché :
	 * 
	 * "La formule ayant comme campagne {CAMP_RFA} et le domaine technique
	 * {LIBELLE DOMAINE TECHNIQUE} ne peut être supprimée car la campagne est
	 * échue."
	 * 
	 * <h3>Cas de test:</h3><br>
	 * <br>
	 * La liste contient une formule de risque dont la campagne est échue.
	 * 
	 * <b>Resultat attendue:</b><br>
	 * Message d'erreur affiché.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCampagneEchueUnElement() throws Exception {
		RuleReport report = new RuleReport();
		List<FormuleRisque> liste = createListFormuleRisqueOK();
		FormuleRisque formule = liste.get(0);
		formule.getCampagne().setCampFinDt(getCurrentFromDatePlusOneYear(new Date(), DOWN));
		report = rule.validate(liste);
		assertFalse(report.isEmpty());
		assertEquals(1, report.getMessages().size());
	}

	/**
	 * Si l'utilisateur a sélectionné plusieurs formules de risque dont la
	 * campagne est échue, alors le traitement n'est pas lancé.
	 * 
	 * Le message suivant est affiché, plusieurs fois :
	 * 
	 * "La formule ayant comme campagne {CAMP_RFA} et le domaine technique
	 * {LIBELLE DOMAINE TECHNIQUE} ne peut être supprimée car la campagne est
	 * échue."
	 * 
	 * <h3>Cas de test:</h3><br>
	 * <br>
	 * La liste contient plus d'une formule de risque dont la campagne est
	 * échue.
	 * 
	 * <b>Resultat attendue:</b><br>
	 * Plusieurs message d'erreur affichés.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCampagneEchuePlxElements() throws Exception {
		RuleReport report = new RuleReport();
		List<FormuleRisque> liste = createListFormuleRisqueOK();
		FormuleRisque formule = liste.get(0);
		formule.getCampagne().setCampFinDt(getCurrentFromDatePlusOneYear(new Date(), DOWN));
		formule = liste.get(2);
		formule.getCampagne().setCampFinDt(getCurrentFromDatePlusOneYear(new Date(), DOWN));
		formule = liste.get(4);
		formule.getCampagne().setCampFinDt(getCurrentFromDatePlusOneYear(new Date(), DOWN));
		report = rule.validate(liste);
		assertFalse(report.isEmpty());
		assertEquals(3, report.getMessages().size());
	}

	/**
	 * Si l'utilisateur n'a sélectionné aucune formule de risque avant d'appuyer
	 * sur le bouton "Supprimer", alors le traitement n'est pas lancé.
	 * 
	 * Un message d'erreur est affiché.
	 * 
	 * <h3>Cas de test:</h3><br>
	 * <br>
	 * La liste est vide.
	 * 
	 * <b>Resultat attendue:</b><br>
	 * Message d'erreur affiché.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListeVide() throws Exception {
		RuleReport report = new RuleReport();
		List<FormuleRisque> liste = new ArrayList<FormuleRisque>();
		report = rule.validate(liste);
		assertFalse(report.isEmpty());
		assertEquals(1, report.getMessages().size());
	}

	/**
	 * Contrôle OK
	 * 
	 * <h3>Cas de test:</h3><br>
	 * <br>
	 * La liste n'est pas vide, la date de fin de validité de la campagne est
	 * future;
	 * 
	 * <b>Resultat attendue:</b><br>
	 * Aucun message d'erreur est affichée
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOK() throws Exception {

		// création du ruleReport
		RuleReport ruleReport;
		List<FormuleRisque> liste = createListFormuleRisqueOK();
		// demarrer l'objet de test
		ruleReport = rule.validate(liste);
		// vérifications des resultats
		assertEquals(0, ruleReport.getMessages().size());

	}

	private List<FormuleRisque> createListFormuleRisqueOK() {
		List<FormuleRisque> listFormule = new ArrayList<FormuleRisque>();
		for (int i = 1; i <= 5; i++) {
			StringBuffer campagne = new StringBuffer("2015-");
			campagne.append(i);
			StringBuffer domTech = new StringBuffer("Domainte Technique n°");
			domTech.append(i);
			listFormule.add(createFormuleRisqueOK(campagne.toString(), domTech.toString()));
		}
		return listFormule;
	}

	private FormuleRisque createFormuleRisqueOK(String campRfa, String dtLb) {
		FormuleRisque formule = new FormuleRisque();

		VCampagne campagne = new VCampagne();
		campagne.setCampRfa(campRfa);
		campagne.setCampFinDt(getCurrentFromDatePlusOneYear(new Date(), UP));
		formule.setCampagne(campagne);

		VDomaineTechnique domaine = new VDomaineTechnique();
		domaine.setDtLb(dtLb);
		formule.setDomaineTechnique(domaine);

		return formule;
	}

	/**
	 * Methode pour avoir une date aprés/avant de la date renseignée
	 * 
	 * @param date
	 * @param direction
	 * @return
	 */
	private Date getCurrentFromDatePlusOneYear(Date date, boolean direction) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.roll(Calendar.YEAR, direction);

		return calendar.getTime();
	}
}
