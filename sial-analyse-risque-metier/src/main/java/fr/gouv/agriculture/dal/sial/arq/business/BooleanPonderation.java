package fr.gouv.agriculture.dal.sial.arq.business;

import java.io.Serializable;

import fr.gouv.agriculture.orion.business.BaseEntity;
import fr.gouv.agriculture.orion.i18n.Messages;

// TODO: Auto-generated Javadoc
/**
 * Cette classe définit un nouveau toString pour un booléen, dont la valeur est
 * définie dans le fichier common.properties :
 * 
 * common.combobox.oui.lb pour "true"
 * 
 * common.combobox.non.lb pour "false"
 * 
 * Cette classe est utilisée dans la liste des formules de risque pour afficher
 * les bons libellés dans les critères de présence/absence d'une pondération.
 * 
 * @author jodurand
 * 
 */
public class BooleanPonderation extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6727298887461991770L;

	/** The value. */
	private Boolean value;

	/**
	 * Instantiates a new boolean ponderation.
	 *
	 * @param value the value
	 */
	@SuppressWarnings("javadoc")
	public BooleanPonderation(Boolean value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@SuppressWarnings("javadoc")
	public Boolean getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	@SuppressWarnings("javadoc")
	public void setValue(Boolean value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "null";
		if (value != null) {
			if (value) {
				res = Messages.getMessage("common.combobox.oui.lb");
			} else {
				res = Messages.getMessage("common.combobox.non.lb");
			}
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.gouv.agriculture.orion.business.Persistent#getIdentifier()
	 */
	@Override
	public Serializable getIdentifier() {
		return value;
	}

}
