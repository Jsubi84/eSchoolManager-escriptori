package model;

public class Beca {
	
	private int codi, codiEstudiant, codiServei;
	private String adjudicant;
	private double importInicial, importRestant;
	private boolean exaurida;
	
	
	public Beca() {
		super();
	}

	public Beca(int codi, int codiEstudiant, int codiServei, String adjudicat, double importInicial,
			double importRestant, boolean exaurida) {
		super();
		this.codi = codi;
		this.codiEstudiant = codiEstudiant;
		this.codiServei = codiServei;
		this.adjudicant = adjudicat;
		this.importInicial = importInicial;
		this.importRestant = importRestant;
		this.exaurida = exaurida;
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

	public String getAdjudicat() {
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

	public boolean isExaurida() {
		return exaurida;
	}

	public void setExaurida(boolean exaurida) {
		this.exaurida = exaurida;
	}
	

}
