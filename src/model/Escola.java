package model;

import org.json.JSONObject;

public class Escola {
	
	private int codi;
	private String nom;
	private String adreca;
	private String telefon;
	
	
	public Escola() {}
	
	
	public Escola(int codi, String nom, String adreca, String telefon) {
		super();
		this.nom = nom;
		this.adreca = adreca;
		this.telefon = telefon;
	}


	
	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
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
	
	
	
	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI ESCOLA");
		json.put("codiSessio", codiSessio);		
		JSONObject dades = new JSONObject();
		dades.put("nomEscola", this.getNom());		
		dades.put("adreca", this.getAdreca());	
		dades.put("telefon", this.getTelefon());	
		json.put("dades", dades);
		return json.toString();
	}

	public static String consultaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "CONSULTA ESCOLA");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEscola", codi);				
		json.put("dades", dades);
		return json.toString();
	}

}
