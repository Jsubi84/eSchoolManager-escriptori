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


	public Beca(int codi, int codiEstudiant, int codiServei, String adjudicant, double importInicial,
			double importRestant, boolean finalitzada, String nomCognomsEstudiant, String nomServei) {
		super();
		this.codi = codi;
		this.codiEstudiant = codiEstudiant;
		this.codiServei = codiServei;
		this.adjudicant = adjudicant;
		this.importInicial = importInicial;
		this.importRestant = importRestant;
		this.finalitzada = finalitzada;
		this.nomCognomsEstudiant = nomCognomsEstudiant;
		this.nomServei = nomServei;
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
		dades.put("importRestant",this.getImportRestant());
		dades.put("codiEstudiant",this.getCodiEstudiant());
		dades.put("codiServei",this.getCodiServei());
		dades.put("finalizada",this.isFinalitzada());
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA BECA");
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
		json.put("crida", "CONSULTA BECA");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiBeca", codi);				
		json.put("dades", dades);
		return json.toString();
	}

	// { "codiBeca","ImportInicial","nomEstudiant","cognomsEstudiant", "nomServei"}
	public Object[] getRow(String nomEstudiantComplet, String nomServei){
		return new Object[] {this.codi, this.importInicial, nomEstudiantComplet, nomServei};
	}
}
