package model;

import java.time.LocalDate;

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


}
