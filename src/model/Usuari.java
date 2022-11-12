package model;

public class Usuari {
	
	private int codi;
	private String usuari, contrasenya;
	
	public Usuari() {
		super();
	}

	public Usuari(int codi, String usuari, String contrasenya) {
		super();
		this.codi = codi;
		this.usuari = usuari;
		this.contrasenya = contrasenya;
	}
	
	
	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
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
