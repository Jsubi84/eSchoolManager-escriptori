package controller;

import javax.swing.JOptionPane;

import model.Login;
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
		Login login = controlOper.getLogin();
		mainview.permisos(login);
		
		mainview.setTextLblNomEmpleat(controlOper.getLogin().getNom().toUpperCase());
		mainview.setTextLblNomDepartament(controlOper.getLogin().getNomDepartament().toUpperCase());		
	}
	
	
	public void missatgeIncidencia(String missatge) {
		JOptionPane.showMessageDialog(null, missatge);
	}

	
}
