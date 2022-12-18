package model;

import org.json.JSONObject;

public class Beca {
	
	private int codi, codiEstudiant, codiServei;
	private String adjudicant;
	private double importInicial = 0, importRestant = 0;
	private boolean finalitzada = false;
	//Guardar dades per llistar
	private String nomCognomsEstudiant, nomServei;
	
	
	public Beca() {
		
	}

	public Beca(int codiEstudiant, int codiServei, String adjudicant, double importInicial) {
		this.codiEstudiant = codiEstudiant;
		this.codiServei = codiServei;
		this.adjudicant = adjudicant;
		this.importInicial = importInicial;
	}


	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
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

	public String getAdjudicant() {
		return adjudicant;
	}

	public void setAdjudicant(String adjudicant) {
		this.adjudicant = adjudicant;
	}

	public double getImportInicial() {
		return importInicial;
	}

	public void setImportInicial(double importInicial) {
		this.importInicial = importInicial;
	}

	public double getImportRestant() {
		return importRestant;
	}

	public void setImportRestant(double importRestant) {
		this.importRestant = importRestant;
	}

	public boolean isFinalitzada() {
		return finalitzada;
	}

	public void setFinalitzada(boolean finalitzada) {
		this.finalitzada = finalitzada;
	}
	
	public String getNomCognomsEstudiant() {
		return nomCognomsEstudiant;
	}

	public void setNomCognomsEstudiant(String nomCognomsEstudiant) {
		this.nomCognomsEstudiant = nomCognomsEstudiant;
	}

	public String getNomServei() {
		return nomServei;
	}

	public void setNomServei(String nomServei) {
		this.nomServei = nomServei;
	}


	
	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA BECA");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("adjudicant",this.getAdjudicant());	
		dades.put("importInicial",this.getImportInicial());
		dades.put("codiEstudiant",this.getCodiEstudiant());
		dades.put("codiServei",this.getCodiServei());
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String baixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA BECA");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiBeca", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI BECA");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("codiBeca",this.getCodi());			
		dades.put("adjudicant",this.getAdjudicant());	
		dades.put("importInicial",this.getImportInicial());
		dades.put("codiEstudiant",this.getCodiEstudiant());
		dades.put("codiServei",this.getCodiServei());
		dades.put("finalitzada",this.isFinalitzada());
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA BEQUES");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("camp", camp);
		dades.put("ordre", ordre);	
		dades.put("valor", valor);	
		json.put("dades", dades);
		return json.toString();
	}	

	
	public static String consultaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "CONSULTA BECA");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiBeca", codi);				
		json.put("dades", dades);
		return json.toString();
	}


	// {id, adjudicant, importInicial, importRestant, nomEstudiantComplet, nomServei, finalitzada}
	public Object[] getRow(String nomEstudiantComplet, String nomServei){
		return new Object[] {this.codi, this.adjudicant, this.importInicial, this.importRestant, nomEstudiantComplet, nomServei, this.finalitzada};
	}
}
