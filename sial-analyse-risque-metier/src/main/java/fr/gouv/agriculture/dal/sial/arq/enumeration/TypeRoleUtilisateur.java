package fr.gouv.agriculture.dal.sial.arq.enumeration;

/**
 * Cette énumération contient le nom des rôles que peut prendre l'utilisateur de
 * l'application. Les rôles ANMA et ASA ont des droits en modification, alors
 * que les droits CA et ALA sont plus limités.
 * 
 * @author jodurand
 * 
 */
public enum TypeRoleUtilisateur {

	/**
	 * Le rôle CA, qui est surtout consultant
	 */
	CA("[ARQ] Consultant"),
	/**
	 * Le rôle ALA, principalement consultant
	 */
	ALA("[ARQ] Gestionnaire Local"),
	/**
	 * Le rôle ANMA, qui a des droits de modification
	 */
	ANMA("[ARQ] Gestionnaire National"),
	/**
	 * Le rôle ASA, qui a des droits de modification
	 */
	ASA("[ARQ] Gestionnaire SIAL");

	private String nomRole;

	/**
	 * constructeur
	 * @param nomRole Nom du role
	 */
	private TypeRoleUtilisateur(String nomRole) {
		this.nomRole = nomRole;
	}

	/**
	 * retourne le Nom du role
	 * @return Nom du role
	 */
	public String getNomRole() {
		return this.nomRole;
	}
}
