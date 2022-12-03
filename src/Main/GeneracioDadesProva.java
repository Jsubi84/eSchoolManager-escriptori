package main;

import controller.ControllerOperation;
import controller.ControllerView;
import model.Beca;
import model.Departament;
import model.Empleat;
import model.Estudiant;
import model.Login;
import model.Servei;
import model.Sessio;

public class GeneracioDadesProva {
	
	//Variables necesaries per al controllerOperation
	private ControllerView controlView = new ControllerView() ;
	private ControllerOperation cO = new ControllerOperation(controlView); 
	private Login login;
	private Servei servei;
	private Empleat empleat;
	private Departament departament;
	private Estudiant estudiant, estudiant2;
	private Beca beca;
	private Sessio sessio;
	
	
	
	/**
	 * Classe princial d'inici de l'aplicació
	 * @param args
	 */
	public static void main(String[] args) {	
		new GeneracioDadesProva().generar();
	}
	
	
	public void generar() {
		// Ens logem per entrar les dades
		login = new Login ("p.gomez","passtest1");
		login.CheckLogin();	
		
		
		
	}
}
