package main;

import java.time.LocalDateTime;

import controller.ControllerOperation;
import controller.ControllerView;
import model.Beca;
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
		//Ens logem per entrar les dades
		Login login = new Login ("p.gomez","passtest1");
		login.CheckLogin();	
		
		//Afegim el login al controllerOperations per tal de passar les
		//credencials per fer peticions
		cO.setLogin(login); 
		
		//Generar professors //Departament = 17
    	Empleat empleat = new Empleat("54462999C", "Josep", "Girbau", "1970-09-02", "C/Vigen del pilar 10", "777905039",
    			"jgirbau@hotmail.com", 17, true, "josep", "josep"); //Codi 22
    	Empleat empleat2 = new Empleat("54462978C", "Joan", "Prat", "1975-12-25", "C/marianico el corto", "744906639",
    			"jprat@hotmail.com", 17, true, "joan", "joan"); //Codi 24
	
		cO.altaEmpleat(empleat);
		cO.altaEmpleat(empleat2);
    	
		//Generar estudiants
    	Estudiant estudiant = new Estudiant("56562554C", "Jordi", "Agarrobo", "2000-10-04", "c/del sol 14 bcn", "667902039",
    			"algarrobo@gmail.com", true); //Codi 26
    	Estudiant estudiant2 = new Estudiant("54557744B", "Jony", "Deep", "1976-11-06", "c/brodway bcn", "666234252",
    			"jonyDeep@hotmail.com", true); //Codi 27
    	Estudiant estudiant3 = new Estudiant("95355334K", "Julia", "Manrique", "1999-11-07", "c/del riu 10", "650404040",
    			"jonyDeep@hotmail.com", true); //Codi 28
    	Estudiant estudiant4 = new Estudiant("77552244P", "Monica", "Lebinsqui", "1998-01-09", "c/miami", "666919191",
    			"jonyDeep@hotmail.com", true); //Codi 29
    	
		cO.altaEstudiant(estudiant);
		cO.altaEstudiant(estudiant2);
		cO.altaEstudiant(estudiant3);
		cO.altaEstudiant(estudiant4);
		
		//Generar Serveis
		Servei servei= new Servei("Logopedia", 45, 35.0 );//30
		Servei servei2= new Servei("Reeducació", 50, 45.0 );//31
		
		cO.altaServei(servei);
		cO.altaServei(servei2);

    	//Generar Becas
    	Beca beca = new Beca ( 26 , 20 , "Generalitat de Catalunya", 350.00); //28
    	Beca beca2 = new Beca ( 27 , 21 , "Diputacio de Barcelona", 650.00);	//29
       	Beca beca3 = new Beca ( 28 , 30 , "Generalitat Valenciana", 450.00); //30
    	Beca beca4 = new Beca ( 29 , 31 , "Diputacio de Lleida", 800.00);	//31
    	
		cO.altaBeca(beca);
		cO.altaBeca(beca2);
		cO.altaBeca(beca3);
		cO.altaBeca(beca4);
    	
    	//Generar Sessions codiEmpleat, codiEstudiant, codiServei, dataihora
		Sessio sessio = new Sessio (22, 26, 30, LocalDateTime.of(2022, 12, 15, 18, 00));
		Sessio sessio1 = new Sessio (24, 29, 31, LocalDateTime.of(2022, 12, 25, 17, 00));
		Sessio sessio2 = new Sessio (22, 29, 31, LocalDateTime.of(2022, 12, 16, 14, 00));
		Sessio sessio3 = new Sessio (24, 29, 31, LocalDateTime.of(2022, 12, 06, 12, 00));
		Sessio sessio4 = new Sessio (18, 28, 30, LocalDateTime.of(2022, 12, 10, 11, 00));
		
		
		cO.altaSessio(sessio);
		cO.altaSessio(sessio1);
		cO.altaSessio(sessio2);
		cO.altaSessio(sessio3);
		cO.altaSessio(sessio4);
		
		login.logoutJSon();
	}
	
}
