package model;

import java.time.LocalDate;

import org.json.JSONObject;

public class Sessio {
	
	private int codi, codiEmpleat, codiEstudiant, codiServei;
	private LocalDate dataIHora;
	
	public Sessio() {
		super();
	}

	public Sessio(int codi, int codiEmpleat, int codiEstudiant, int codiServei, LocalDate dataIHora) {
		super();
		this.codi = codi;
		this.codiEmpleat = codiEmpleat;
		this.codiEstudiant = codiEstudiant;
		this.codiServei = codiServei;
		this.dataIHora = dataIHora;
	}
	

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public int getCodiEmpleat() {
		return codiEmpleat;
	}

	public void setCodiEmpleat(int codiEmpleat) {
		this.codiEmpleat = codiEmpleat;
	}

	public int getCodiEstudiant() {
		return codiEstudiant;
	}

	public void setCodiEstudiant(int codiEstudiant) {
		this.codiEstudiant = codiEstudiant;
	}

	public int getCodiServei() {
		return codiServei;
	}

	public void setCodiServei(int codiServei) {
		this.codiServei = codiServei;
	}

	public LocalDate getDataIHora() {
		return dataIHora;
	}

	public void setDataIHora(LocalDate dataIHora) {
		this.dataIHora = dataIHora;
	}
	
	
	
	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA SESSIO");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("codiProfessor",this.getCodiEmpleat());	
		dades.put("codiEstudiant",this.getCodiEstudiant());
		dades.put("codiServei",this.getCodiServei());
		dades.put("dataIHora",this.dataIHora);
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String baixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA SESSIO");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiSessio", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	

	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI SESSIO");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("codiSessio",this.getCodi());
		dades.put("codiProfessor",this.getCodiEmpleat());	
		dades.put("codiEstudiant",this.getCodiEstudiant());
		dades.put("codiServei",this.getCodiServei());
		dades.put("dataIHora",this.dataIHora);
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA SESSIO");
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
		json.put("crida", "CONSULTA SESSIO");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiSessio", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	// { "Id","idEmpleat","idEstudiant","codiServei", "dataIHora" }
	public Object[] getRow(){
		return new Object[] {this.codi, this.codiEmpleat, this.codiEstudiant, this.codiServei, this.dataIHora.toString()};
	}
	
}
