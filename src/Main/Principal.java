package main;

import controller.ControllerOperation;
import controller.ControllerView;
import view.LoginView;
import view.MainView;

public class Principal {

	public static void main(String[] args) {	
		new Principal().iniciar();
	}
	
	/**
	 * Iniciar l'aplicació creant instancies necesaries. 
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
