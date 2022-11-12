package model;

public class Escola {
	
	private String nom;
	private String adreca;
	private String telefon;
	
	
	public Escola() {}
	
	
	public Escola(String nom, String adreca, String telefon) {
		super();
		this.nom = nom;
		this.adreca = adreca;
		this.telefon = telefon;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	
	

}
