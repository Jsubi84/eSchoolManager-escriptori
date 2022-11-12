package model;

import org.json.JSONObject;

public class Empleat extends Persona {
	
	private int codi, codiDepartament;	
	private Boolean actiu;

	
	public Empleat() {
		super();
	}
	
	
	public Empleat(int codi, int codiDepartament, Boolean actiu) {
		super();
		this.codi = codi;
		this.codiDepartament = codiDepartament;
		this.actiu = actiu;
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
	
	
	public String AltaJSon (String codiSessio, Usuari usuari) {
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
		dades.put("usuari", usuari);			
		dades.put("contrasenya", this.getCodiDepartament());		
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public static String BaixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA EMPLEAT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEmpleat", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public String ModiJSon (String codiSessio, Usuari usuari) {
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
		dades.put("contrasenya", usuari.getContrasenya());		
		dades.put("actiu", this.getActiu());	
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public static String LlistatJSon (String codiSessio, String camp, String valor, String ordre) {
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

	
	public static String ConsultaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "CONSULTA EMPLEAT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEmpleat", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
}
