package com.greta.gsb_frais2014;

public class FraisHorsForfait {
	
	String mat;
	String date;
	String libelle;
	double montant;
	
	
	public FraisHorsForfait(String mat, String date, String libelle,
			double montant) {
		super();
		this.mat = mat;
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
	}
	public String getMat() {
		return mat;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	

}
