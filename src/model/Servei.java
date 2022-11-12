package model;

public class Servei {
	
	private String nom;
	private int codi, durada;
	private double cost;

	
	public Servei() {
		super();
	}


	public Servei(String nom, int codi, int durada, double cost) {
		super();
		this.nom = nom;
		this.codi = codi;
		this.durada = durada;
		this.cost = cost;
	}

	
	

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public int getDurada() {
		return durada;
	}

	public void setDurada(int durada) {
		this.durada = durada;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	} 
	
}
