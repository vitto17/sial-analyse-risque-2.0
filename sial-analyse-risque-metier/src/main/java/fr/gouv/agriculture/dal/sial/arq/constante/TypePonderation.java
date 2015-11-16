package fr.gouv.agriculture.dal.sial.arq.constante;

/**
 * Liste des types de ponderation
 * 
 * @author sopra
 * 
 */
public final class TypePonderation {

	/** Code du type de zone de ponderation de destination */
	public static final String TYPE_POND_DESTINATION = "pdest";
	/** Code du type de zone de ponderation de diffusion */
	public static final String TYPE_POND_DIFFUSION = "pdiff";
	/** Code du type de zone de ponderation de risque theorique */
	public static final String TYPE_POND_RISQUE_THEO = "prisqtheo";
	/** Code du type de zone de ponderation de volume */
	public static final String TYPE_POND_VOLUME = "pvol";
	/** Code du type de zone de ponderation de zone */
	public static final String TYPE_POND_ZONE = "pzone";

	/** Constructeur par defaut */
	private TypePonderation() {
		
	}
	
	
	/**
	 * Enumeration sur les types de ponderation
	 * @author sopra
	 *
	 */
	public enum TypePonderationEnum {

		DESTINATION(TypePonderation.TYPE_POND_DESTINATION), DIFFUSION(TypePonderation.TYPE_POND_DIFFUSION), RISQUE_THEORIQUE(
				TypePonderation.TYPE_POND_RISQUE_THEO), VOLUME(TypePonderation.TYPE_POND_VOLUME), ZONE(
				TypePonderation.TYPE_POND_ZONE);

		private String value;

		private TypePonderationEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
}
