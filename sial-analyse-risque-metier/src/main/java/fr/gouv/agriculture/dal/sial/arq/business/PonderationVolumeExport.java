package fr.gouv.agriculture.dal.sial.arq.business;

// TODO: Auto-generated Javadoc
/**
 * Cette classe est utilisée à l'export des pondérations de volume afin de
 * garder les colonnes avec les signes logiques qui sont présentes dans la page.
 * 
 * @author jodurand
 */
public class PonderationVolumeExport {

	/**  serial. */
	private static final long serialVersionUID = 8314263103969695789L;

	/**  Ponderation Volume. */
	private PonderationVolume ponderationVolume;
	
	/**  Libellé ponderation 1. */
	private static final String P_VOL_S1_LB = "<= V <=";
	
	/**  Libellé ponderation 2. */
	private static final String P_VOL_S2_LB = "< V <=";
	
	/**  Libellé ponderation 3. */
	private static final String P_VOL_S3_LB = "< V <=";
	
	/**  Libellé ponderation 4. */
	private static final String P_VOL_S4_LB = "< V";

	/**
	 * constructeur.
	 *
	 * @param ponderationVolume the ponderation volume
	 */
	public PonderationVolumeExport(PonderationVolume ponderationVolume) {
		this.ponderationVolume = ponderationVolume;
	}

	/**
	 * Retourne la PonderationVolume.
	 *
	 * @return PonderationVolume
	 */
	public PonderationVolume getPonderationVolume() {
		return ponderationVolume;
	}

	/**
	 * Setter de la PonderationVolume.
	 *
	 * @param ponderationVolume ponderation à setter
	 */
	public void setPonderationVolume(PonderationVolume ponderationVolume) {
		this.ponderationVolume = ponderationVolume;
	}

	/**
	 * Retourne le libellé de la ponderation 1.
	 *
	 * @return Libellé de la ponderation 1
	 */
	public String getPvolS1Lb() {
		return P_VOL_S1_LB;
	}

	/**
	 * Retourne le libellé de la ponderation 2.
	 *
	 * @return Libellé de la ponderation 2
	 */
	public String getPvolS2Lb() {
		return P_VOL_S2_LB;
	}

	/**
	 * Retourne le libellé de la ponderation 3.
	 *
	 * @return Libellé de la ponderation 3
	 */
	public String getPvolS3Lb() {
		return P_VOL_S3_LB;
	}

	/**
	 * Retourne le libellé de la ponderation 4.
	 *
	 * @return Libellé de la ponderation 4
	 */
	public String getPvolS4Lb() {
		return P_VOL_S4_LB;
	}

}
