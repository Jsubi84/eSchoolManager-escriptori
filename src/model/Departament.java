package model;

import org.json.JSONObject;


public class Departament {
	

	private int codi;
	private String nomDepartament;
	private Boolean empleat, departament, escola, estudiant, servei, beca, sessio, informe;
	
	
	public Departament() {}
	
	public Departament(int codi, String nomDepartament, Boolean empleat, Boolean estudiant, Boolean servei, Boolean beca,
			Boolean sessio, Boolean informe) {
		super();
		this.codi = codi;		
		this.nomDepartament = nomDepartament;
		this.empleat = empleat;
		this.estudiant = estudiant;
		this.servei = servei;
		this.beca = beca;
		this.sessio = sessio;
		this.informe = informe;
	}

	
	
	public Boolean getEscola() {
		return escola;
	}

	public void setEscola(Boolean escola) {
		this.escola = escola;
	}

	public Boolean getDepartament() {
		return departament;
	}

	public void setDepartament(Boolean departament) {
		this.departament = departament;
	}

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getNomDepartament() {
		return nomDepartament;
	}

	public void setNomDepartament(String nomDepartament) {
		this.nomDepartament = nomDepartament;
	}

	public Boolean getEmpleat() {
		return empleat;
	}

	public void setEmpleat(Boolean empleat) {
		this.empleat = empleat;
	}

	public Boolean getEstudiant() {
		return estudiant;
	}

	public void setEstudiant(Boolean estudiant) {
		this.estudiant = estudiant;
	}

	public Boolean getServei() {
		return servei;
	}

	public void setServei(Boolean servei) {
		this.servei = servei;
	}

	public Boolean getBeca() {
		return beca;
	}

	public void setBeca(Boolean beca) {
		this.beca = beca;
	}

	public Boolean getSessio() {
		return sessio;
	}

	public void setSessio(Boolean sessio) {
		this.sessio = sessio;
	}

	public Boolean getInforme() {
		return informe;
	}

	public void setInforme(Boolean informe) {
		this.informe = informe;
	}
	

	
	
	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA DEPARTAMENT");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("nomDepartament",this.getNomDepartament());
		JSONObject permisos = new JSONObject();
		permisos.put("escola", this.getEscola());	
		permisos.put("departament", this.getDepartament());	
		permisos.put("empleat", this.getEmpleat());	
		permisos.put("estudiant", this.getEstudiant());	
		permisos.put("servei", this.getServei());	
		permisos.put("beca", this.getBeca());	
		permisos.put("sessio", this.getSessio());	
		permisos.put("informe", this.getInforme());	
		dades.put("permisos", permisos);
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String baixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA DEPARTAMENT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiDepartament", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI DEPARTAMENT");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("codiDepartament", this.getCodi());
		dades.put("nomDepartament",this.getNomDepartament());
		JSONObject permisos = new JSONObject();
		permisos.put("empleat", this.getEmpleat());	
		permisos.put("estudiant", this.getEstudiant());	
		permisos.put("servei", this.getServei());	
		permisos.put("beca", this.getBeca());	
		permisos.put("sessio", this.getSessio());	
		permisos.put("informe", this.getInforme());	
		dades.put("permisos", permisos);
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA DEPARTAMENTS");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("camp", camp);
		dades.put("ordre", ordre);	
		dades.put("valor", ordre);	
		json.put("dades", dades);
		return json.toString();
	}	

	
	public static String consultaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "CONSULTA DEPARTAMENT");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiDepartament", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	// { "Id","Nom Departament", "Escola", "Departament", "Empleat","Estudiant", "Servei", "Beca","Sessio", "Informe" }
	public Object[] getRow(){
		return new Object[] {this.codi, this.nomDepartament};
	}
    
}
