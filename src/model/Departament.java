package model;

public class Departament {
	
	private int codi;
	private String nomDepartament;
	private Boolean empleat, estudiant, servei, beca, sessio, informe;
	
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

	
}
