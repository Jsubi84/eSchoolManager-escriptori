package model;

public class Persona {
		
	private String dni, nom, cognoms, dataNaixament, adreca, telefon, email;

	
	public Persona() {}
	
	public Persona(String dni, String nom, String cognoms, String dataNaixament, String adreca, String telefon,
			String email) {
		super();
		this.dni = dni;
		this.nom = nom;
		this.cognoms = cognoms;
		this.dataNaixament = dataNaixament;
		this.adreca = adreca;
		this.telefon = telefon;
		this.email = email;
	}

	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public String getDataNaixament() {
		return dataNaixament;
	}

	public void setDataNaixament(String dataNaixament) {
		this.dataNaixament = dataNaixament;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
