package fr.gouv.agriculture.dal.sial.arq.business.bean;

import java.io.Serializable;

import fr.gouv.agriculture.orion.business.BaseEntity;

public class VNbruaAgg extends BaseEntity{

	/**serial*/
	private static final long serialVersionUID = -2581099679207125625L;

	/** The camp rfa. */
	private String campRfa;
	
	/** The dt rfa. */
	private String dtRfa;
	
	/** The dt label. */
	private String dtLb;
	
	/** The classe rfa. */
	private String classeRfa;

	/**  identifier field. */
    private Integer nbrUa;
	
	
	/**
	 * @return the campRfa
	 */
	public String getCampRfa() {
		return campRfa;
	}


	/**
	 * @param campRfa the campRfa to set
	 */
	public void setCampRfa(String campRfa) {
		this.campRfa = campRfa;
	}


	/**
	 * @return the dtRfa
	 */
	public String getDtRfa() {
		return dtRfa;
	}


	/**
	 * @param dtRfa the dtRfa to set
	 */
	public void setDtRfa(String dtRfa) {
		this.dtRfa = dtRfa;
	}


	/**
	 * @return the dtLb
	 */
	public String getDtLb() {
		return dtLb;
	}


	/**
	 * @param dtLb the dtLb to set
	 */
	public void setDtLb(String dtLb) {
		this.dtLb = dtLb;
	}


	/**
	 * @return the classeRfa
	 */
	public String getClasseRfa() {
		return classeRfa;
	}


	/**
	 * @param classeRfa the classeRfa to set
	 */
	public void setClasseRfa(String classeRfa) {
		this.classeRfa = classeRfa;
	}


	/**
	 * @return the nbrUa
	 */
	public Integer getNbrUa() {
		return nbrUa;
	}


	/**
	 * @param nbrUa the nbrUa to set
	 */
	public void setNbrUa(Integer nbrUa) {
		this.nbrUa = nbrUa;
	}


	public String getDtToString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append("(");
		toStringBuilder.append(dtRfa);
		toStringBuilder.append(") ");
		toStringBuilder.append(dtLb);
		return toStringBuilder.toString();
	}
	
	
	@Override
	public Serializable getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
