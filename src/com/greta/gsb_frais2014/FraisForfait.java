package com.greta.gsb_frais2014;

public class FraisForfait {
	
	String numVis;
	String date;
	String idFraisForfait;
	int quantite;
	
	
	
	public FraisForfait(String numVis, String date, String idFraisForfait, int quantite) {	
		this.numVis = numVis;
		this.date = date;
		this.idFraisForfait = idFraisForfait;
		this.quantite = quantite;
	}
	/**
	 * @return the numVis
	 */
	public String getNumVis() {
		return numVis;
	}
	/**
	 * @param numVis the numVis to set
	 */
	public void setNumVis(String numVis) {
		this.numVis = numVis;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the idFraisForfait
	 */
	public String getIdFraisForfait() {
		return idFraisForfait;
	}
	/**
	 * @param idFraisForfait the idFraisForfait to set
	 */
	public void setIdFraisForfait(String idFraisForfait) {
		this.idFraisForfait = idFraisForfait;
	}
	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}
	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	
	
	
	}
	
	


