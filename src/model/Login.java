package model;


/**
 * @author Jorsu
 *
 */

public class Login {

	private String usuari, contrasenya, codiSessio, nom, codiDepartament;
	
	
	/**
	 * Constructor per defecte.
	 */
	public Login() {
	}

	/**
	 * Constructor inicial
	 * @param usuari
	 * @param contrasenya
	 */
	public Login (String usuari, String contrasenya){
		this.setUsuari(usuari);
		this.setContrasenya(contrasenya);
	}
	
	
	public String getCodiSessio() {
		return codiSessio;
	}


	public void setCodiSessio(String codiSessio) {
		this.codiSessio = codiSessio;
	}


	public String getCodiDepartament() {
		return codiDepartament;
	}


	public void setCodiDepartament(String codiDepartament) {
		this.codiDepartament = codiDepartament;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}


}



