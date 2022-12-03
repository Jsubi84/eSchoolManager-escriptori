package model;

import org.json.JSONObject;

public class Servei {
	
	private String nom;
	private int codi, durada;
	private double cost;

	
	public Servei() {
		
	}


	public Servei(String nom, int durada, double cost) {
		super();
		this.nom = nom;
		this.durada = durada;
		this.cost = cost;
	}

	
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public int getDurada() {
		return durada;
	}

	public void setDurada(int durada) {
		this.durada = durada;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	} 
	

	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA SERVEI");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("nomServei",this.getNom());	
		dades.put("durada",this.getDurada());
		dades.put("cost",this.getCost());
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String baixaJSon (String codiSessio, int codi) {
		JSONObject json = new JSONObject();		
		json.put("crida", "BAIXA SERVEI");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiServei", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	public String modiJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "MODI SERVEI");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("codiServei",this.getCodi());			
		dades.put("nomServei",this.getNom());	
		dades.put("durada",this.getDurada());
		dades.put("cost",this.getCost());
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA SERVEIS");
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
		json.put("crida", "CONSULTA SERVEI");
		json.put("codiSessio", codiSessio);	
		JSONObject dades = new JSONObject();	
		dades.put("codiServei", codi);				
		json.put("dades", dades);
		return json.toString();
	}
	
	
	// { "Id","Nom","Durada","Cost" }
	public Object[] getRow(){
		return new Object[] {this.codi, this.nom,this.durada,this.cost};
	}
	
}
