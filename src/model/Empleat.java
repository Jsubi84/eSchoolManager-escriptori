package model;

import org.json.JSONObject;

public class Empleat extends Persona {
	
	private int codi, codiDepartament;	
	private Boolean actiu;
	private String nomDep, contrasenya, usuari;

	
	public Empleat() {
		super();
	}
	
	
	public Empleat(int codi, int codiDepartament, Boolean actiu, String nomDep, String contrasenya, String usuari) {
		super();
		this.codi = codi;
		this.codiDepartament = codiDepartament;
		this.actiu = actiu;
		this.nomDep = nomDep;
		this.contrasenya= contrasenya;
		this.usuari = usuari;
	}


	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public Boolean getActiu() {
		return actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public int getCodiDepartament() {
		return codiDepartament;
	}

	public void setCodiDepartament(int codiDepartament) {
		this.codiDepartament = codiDepartament;
	}
	
	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public String getUsuari() {
		return usuari;
	}
	
	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}


	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA EMPLEAT");
		json.put("codiSessio", codiSessio);		
		JSONObject dades = new JSONObject();	
		dades.put("dni",this.getDni());			
		dades.put("nom", this.getNom());	
		dades.put("cognoms", this.getCognoms());			
		dades.put("dataNaixement", this.getDataNaixament());	
		dades.put("adreca", this.getAdreca());			
		dades.put("telefon", this.getTelefon());	
		dades.put("email",this.getEmail());			
		dades.put("codiDepartament", this.getCodiDepartament());
		dades.put("usuari", this.getUsuari());			
		dades.put("contrasenya", this.getContrasenya());		
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public static String baixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA EMPLEAT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEmpleat", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI EMPLEAT");
		json.put("codiSessio", codiSessio);		
		JSONObject dades = new JSONObject();
		dades.put("codiEmpleat", this.getCodi());
		dades.put("dni",this.getDni());			
		dades.put("nom", this.getNom());	
		dades.put("cognoms", this.getCognoms());			
		dades.put("dataNaixement", this.getDataNaixament());	
		dades.put("adreca", this.getAdreca());			
		dades.put("telefon", this.getTelefon());	
		dades.put("email",this.getEmail());			
		dades.put("codiDepartament", this.getCodiDepartament());			
		dades.put("contrasenya", this.getContrasenya());
		dades.put("usuari", this.getUsuari());
		dades.put("actiu", this.getActiu());	
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA EMPLEATS");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("camp", camp);
		dades.put("valor", valor);
		dades.put("ordre", ordre);				
		json.put("dades", dades);
		return json.toString();
	}	

	
	public static String consultaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "CONSULTA EMPLEAT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEmpleat", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	// { "ID","Nom","Cognoms", "Codi Departament","Nom Departament"}
	public Object[] getRow(){
		return new Object[] {this.codi, super.getNom(), super.getCognoms(), this.codiDepartament, this.nomDep};
	}
}
