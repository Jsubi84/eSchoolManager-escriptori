package controller;

import view.LoginView;
import view.MainView;


/**
 * @author Jordi Subirana
 *
 * Classe per poder controlar les vistes
 */
public class ControllerView {
	
	private LoginView loginview;
	private MainView mainview;
	private ControllerOperation controlOper;
	
	
	public ControllerView() {
	}

	/**
	 * Setters i Getters
	 */
	public LoginView getLoginview() {
		return loginview;
	}

	public void setLoginview(LoginView loginview) {
		this.loginview = loginview;
	}

	public MainView getMainview() {
		return mainview;
	}

	public void setMainview(MainView mainview) {
		this.mainview = mainview;
	}

	public ControllerOperation getControlOper() {
		return controlOper;
	}

	public void setControlOper(ControllerOperation controlOper) {
		this.controlOper = controlOper;
	}
	
	
	/**
	 * Carregar opcions segons departament que pertany l'usuari.
	 */
	public void carregarOpcionsUsuari() {
		String departament = null;
		
		if(controlOper.getLogin().getCodiDepartament() == 1){
			mainview.opcionsAdministrador();
			departament = "Administrador";
		}else if(controlOper.getLogin().getCodiDepartament() == 2) {
			mainview.opcionsAdministratiu();
			departament = "Administratiu";
		}else if (controlOper.getLogin().getCodiDepartament() == 3) {
			mainview.opcionsDocent();
			departament = "Docent";
		}
		
		mainview.setTextLblNomEmpleat( controlOper.getLogin().getNom().toUpperCase());
		mainview.setTextLblNomDepartament(departament);		
	}
	
}
