/**
 * 
 */
package com.greta.gsb_frais2014;

/**
 * @author greta
 *
 */
public class Visiteur {
	
	private String mat;
	private String nom;
	private String mdp;
	
	
	public Visiteur(String mat, String nom, String mdp) {
		super();
		this.mat = mat;
		this.nom = nom;
		this.mdp = mdp;
	}


	/**
	 * @return the mat
	 */
	public String getMat() {
		return mat;
	}


	/**
	 * @param mat the mat to set
	 */
	public void setMat(String mat) {
		this.mat = mat;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}


	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	
}
