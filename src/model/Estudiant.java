package model;

import org.json.JSONObject;

public class Estudiant extends Persona {
	
	private int codi;
	private Boolean matriculat;

	
	public Estudiant() {
		super();
	}	
	
	public Estudiant(String dni, String nom, String cognoms, String dataNaixament, String adreca, String telefon,
			String email, Boolean matriculat) {
		super(dni, nom, cognoms, dataNaixament, adreca,telefon, email);
		this.matriculat = matriculat;
	}
	

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public Boolean getMatriculat() {
		return matriculat;
	}

	public void setMatriculat(Boolean matriculat) {
		this.matriculat = matriculat;
	}
	
	
	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA ESTUDIANT");
		json.put("codiSessio", codiSessio);		
		JSONObject dades = new JSONObject();	
		dades.put("dni",this.getDni());			
		dades.put("nomEstudiant", this.getNom());	
		dades.put("cognoms", this.getCognoms());			
		dades.put("dataNaixement", this.getDataNaixament());	
		dades.put("adreca", this.getAdreca());			
		dades.put("telefon", this.getTelefon());	
		dades.put("email",this.getEmail());								
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public static String baixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA ESTUDIANT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEstudiant", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI ESTUDIANT");
		json.put("codiSessio", codiSessio);		
		JSONObject dades = new JSONObject();
		dades.put("codiEstudiant", this.getCodi());
		dades.put("dni",this.getDni());			
		dades.put("nomEstudiant", this.getNom());	
		dades.put("cognoms", this.getCognoms());			
		dades.put("dataNaixement", this.getDataNaixament());	
		dades.put("adreca", this.getAdreca());			
		dades.put("telefon", this.getTelefon());	
		dades.put("email",this.getEmail());			
		dades.put("registrat", this.matriculat);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA ESTUDIANTS");
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
		json.put("crida", "CONSULTA ESTUDIANT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiEstudiant", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	// { "ID","Nom","Cognoms"}
	public Object[] getRow(){
		return new Object[] {this.codi, super.getNom(), super.getCognoms()};
	}
	
}
