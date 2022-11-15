package main;

import controller.ControllerOperation;
import controller.ControllerView;
import view.LoginView;
import view.MainView;

/**
 * @author Jordi Subirana
 *
 */
public class Principal {

	/**
	 * Classe princial d'inici de l'aplicació
	 * @param args
	 */
	public static void main(String[] args) {	
		new Principal().iniciar();
	}
	
	/**
	 * Iniciar l'aplicació creant les instancies necesaries. 
	 */
	private void iniciar() {
		
		//Relacions per control vistes
		LoginView loginView= new LoginView();
		MainView mainView = new MainView();
		ControllerView controllerView = new ControllerView();
		
		loginView.setControllerView(controllerView);
		mainView.setControllerView(controllerView);
		
		controllerView.setLoginview(loginView);
		controllerView.setMainview(mainView);
		
		
		//Relacions per control operacions
		ControllerOperation controlleroperations= new ControllerOperation();
		
		//Relacio entre vistes i control
		controllerView.setControlOper(controlleroperations);
		controlleroperations.setControlView(controllerView);
				
	}
}
