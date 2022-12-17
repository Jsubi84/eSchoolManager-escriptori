package model;

import org.json.JSONObject;

/**
 * @author Jorsu
 *
 */
public class Factura {
	
	private int codiF, codiEstudiant, mes;
	private Boolean pagat;
	private String nomEstudiant, cognomEstudiant;
	private LiniaFactura[] linias;
	
	public Factura() {
	}

	public Factura(int codiF, int codiEstudiant, int mes, Boolean pagat, String nomEstudiant, String cognomEstudiant,
			LiniaFactura[] linias) {
		super();
		this.codiF = codiF;
		this.codiEstudiant = codiEstudiant;
		this.mes = mes;
		this.pagat = pagat;
		this.nomEstudiant = nomEstudiant;
		this.cognomEstudiant = cognomEstudiant;
		this.linias = linias;
	}

	
	/**
	 * Getters and setters
	 */
	public int getCodiF() {
		return codiF;
	}

	public void setCodiF(int codiF) {
		this.codiF = codiF;
	}

	public int getCodiEstudiant() {
		return codiEstudiant;
	}

	public void setCodiEstudiant(int codiEstudiant) {
		this.codiEstudiant = codiEstudiant;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public Boolean getPagat() {
		return pagat;
	}

	public void setPagat(Boolean pagat) {
		this.pagat = pagat;
	}

	public String getNomEstudiant() {
		return nomEstudiant;
	}

	public void setNomEstudiant(String nomEstudiant) {
		this.nomEstudiant = nomEstudiant;
	}

	public String getCognomEstudiant() {
		return cognomEstudiant;
	}

	public void setCognomEstudiant(String cognomEstudiant) {
		this.cognomEstudiant = cognomEstudiant;
	}

	public LiniaFactura[] getLinias() {
		return linias;
	}

	public void setLinias(LiniaFactura[] linias) {
		this.linias = linias;
	}


	
	public String facturarJSon(String codiSessio){
			JSONObject json = new JSONObject();		
			json.put("crida", "GENERA FACTURA");
			json.put("codiSessio", codiSessio);		
			JSONObject dades = new JSONObject();	
			dades.put("codiEstudiant",this.codiEstudiant);			
			dades.put("mes", this.mes);								
			json.put("dades", dades);
			return json.toString();
	}
	
	
	public static String pagarJSon(String codiSessio, Boolean pagat, int codiFactura){
		JSONObject json = new JSONObject();		
		json.put("crida", "PAGA FACTURA");
		json.put("codiSessio", codiSessio);		
		JSONObject dades = new JSONObject();	
		dades.put("codiFactura", codiFactura);			
		dades.put("pagat", pagat);								
		json.put("dades", dades);
		return json.toString();
	}
	
}
