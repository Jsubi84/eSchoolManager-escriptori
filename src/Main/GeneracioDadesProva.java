package main;

import org.junit.jupiter.api.Assertions;

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


	/**
	 * Classe princial d'inici de l'aplicació
	 * @param args
	 */
	public static void main(String[] args) {	
		new GeneracioDadesProva().generar();
	}
	
	
	public void generar() {
		// Ens logem per entrar les dades
		Login login = new Login ("p.gomez","passtest1");
		login.CheckLogin();	
		
		//Afegim el login al controllerOperations per tal de passar les
		//credencials per fer peticions
		cO.setLogin(login); 
		
		//Generar estudiants
    	Estudiant estudiant = new Estudiant("56562554A", "Jordi", "Agarrobo", "1978-10-04", "c/del sol 14 bcn", "354352435",
    			"algarrobo@gmail.com", true); //Codi 22
    	Estudiant estudiant2 = new Estudiant("55555444A", "Jony", "Deep", "1956-11-06", "c/brodway bcn", "54643374",
    			"jonyDeep@hotmail.com", true); //Codi 23
    	
		cO.altaEstudiant(estudiant);
		cO.altaEstudiant(estudiant2);
    	
    	//Generar Becas
    	Beca beca = new Beca ( 22 , 20 , "Generalitat", 600.00); //
    	Beca beca2 = new Beca ( 23 , 21 , "Diputacio", 650.00);	//
    	
		cO.altaBeca(beca);
		cO.altaBeca(beca2);
		
    	
//    	//Generar Sessions
//    	Sessio sessio = new Sessio ( 22 , 20 , "Generalitat", 600.00); //
//    	Beca beca2 = new Beca ( 23 , 21 , "Diputacio", 650.00);	//
//    	
//		cO.altaBeca(beca);
//		cO.altaBeca(beca2);		

		
		login.logoutJSon();
	}
}
