package controller;

import view.LoginView;
import view.MainView;

public class ControllerView {
	
	private LoginView loginview;
	private MainView mainview;
	private ControllerOperation controlOper;
	
	
	public ControllerView() {

	}


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
	
	public void carregarOpcionsUsuari() {
		mainview.setTextLblNomEmpleat( controlOper.getLogin().getNom().toUpperCase());
		mainview.setTextLblNomDepartament(Integer.toString(controlOper.getLogin().getCodiDepartament()));
		
		if(controlOper.getLogin().getCodiDepartament() == 1){
			mainview.opcionsAdministrador();
		}else if(controlOper.getLogin().getCodiDepartament() == 2) {
			mainview.opcionsAdministratiu();
		}else if (controlOper.getLogin().getCodiDepartament() == 3) {
			mainview.opcionsDocent();
		}
	}
	
	
}
