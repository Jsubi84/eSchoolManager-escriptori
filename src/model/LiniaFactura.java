package model;


public class LiniaFactura {

	private String nomServei, dataIHora;
	private Double importBeca, importEstudiant;
	
	
	public LiniaFactura(String dataIHora, String nomServei, Double importBeca, Double importEstudiant) {
		super();
		this.dataIHora = dataIHora;
		this.nomServei = nomServei;
		this.importBeca = importBeca;
		this.importEstudiant = importEstudiant;
	}
	
	public LiniaFactura() {
	}


	/**
	 * Getters and setters
	 */

	public String getNomServei() {
		return nomServei;
	}

	public void setNomServei(String nomServei) {
		this.nomServei = nomServei;
	}

	public Double getImportBeca() {
		return importBeca;
	}

	public void setImportBeca(Double importBeca) {
		this.importBeca = importBeca;
	}

	public Double getImportEstudiant() {
		return importEstudiant;
	}

	public void setImportEstudiant(Double importEstudiant) {
		this.importEstudiant = importEstudiant;
	}

	public String getDataIHora() {
		return dataIHora;
	}

	public void setDataIHora(String dataIHora) {
		this.dataIHora = dataIHora;
	}

	
	public Object[] getRow(){
		return new Object[] { this.dataIHora, this.nomServei, this.importBeca, this.importEstudiant};
	}
}
