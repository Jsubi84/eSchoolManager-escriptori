package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class Sessio {
	
	private int codi, codiEmpleat, codiEstudiant, codiServei;
	private String nomEmpleat, cognomEmpleat, nomEstudiant, cognomsEstudiant, nomServei;
	private LocalDateTime dataIHora;
	DateTimeFormatter isoDate = DateTimeFormatter.ISO_LOCAL_DATE;
	DateTimeFormatter isoTime = DateTimeFormatter.ISO_LOCAL_TIME;
	
	
	public Sessio() {
		super();
	}

	public Sessio(int codiEmpleat, int codiEstudiant, int codiServei, LocalDateTime dataIHora) {
		super();
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

	public LocalDateTime getDataIHora() {
		return dataIHora;
	}

	public void setDataIHora(LocalDateTime dataIHora) {
		this.dataIHora = dataIHora;
	}
	
	public String getNomEmpleat() {
		return nomEmpleat;
	}

	public void setNomEmpleat(String nomEmpleat) {
		this.nomEmpleat = nomEmpleat;
	}

	public String getCognomEmpleat() {
		return cognomEmpleat;
	}

	public void setCognomEmpleat(String cognomEmpleat) {
		this.cognomEmpleat = cognomEmpleat;
	}

	public String getNomEstudiant() {
		return nomEstudiant;
	}

	public void setNomEstudiant(String nomEstudiant) {
		this.nomEstudiant = nomEstudiant;
	}

	public String getCognomsEstudiant() {
		return cognomsEstudiant;
	}

	public void setCognomsEstudiant(String cognomsEstudiant) {
		this.cognomsEstudiant = cognomsEstudiant;
	}

	public String getNomServei() {
		return nomServei;
	}

	public void setNomServei(String nomServei) {
		this.nomServei = nomServei;
	}
	

	public String altaJSon (String codiSessio) {
		JSONObject json = new JSONObject();		
		json.put("crida", "ALTA SESSIO");
		json.put("codiSessio", codiSessio);
		JSONObject dades = new JSONObject();
		dades.put("codiEmpleat",this.getCodiEmpleat());	
		dades.put("codiEstudiant",this.getCodiEstudiant());
		dades.put("codiServei",this.getCodiServei());		
		dades.put("dataIHora", this.dataIHora.format(isoDate) + " " + this.dataIHora.format(isoTime));		
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
		dades.put("dataIHora", this.dataIHora.format(isoDate) + " " + this.dataIHora.format(isoTime));
		json.put("dades", dades);
		return json.toString();
	}

	
	public static String llistatJSon (String codiSessio, String camp, String valor, String ordre) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LLISTA SESSIONS");
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
	
	// { "Id","nomCognomEmpleat","nomCognomEstudiant","nomServei", "dataIHora" }
	public Object[] getRow(){
		return new Object[] {this.codi, this.nomEmpleat + " " + this.cognomEmpleat,
				this.nomEstudiant + " " + this.cognomsEstudiant, this.nomServei, this.dataIHora.format(isoDate), this.dataIHora.format(isoTime)};
	}
	
}
