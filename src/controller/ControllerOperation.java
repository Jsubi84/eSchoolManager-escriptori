package controller;

import java.io.IOException;
import java.net.ConnectException;

import model.Login;

public class ControllerOperation {
	
	private Login login;
	private LoginControl logincontrol;
	private ControllerView controlView;
	
	
	
	public ControllerOperation() {
	}

	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public LoginControl getLogincontrol() {
		return logincontrol;
	}

	public void setLogincontrol(LoginControl logincontrol) {
		this.logincontrol = logincontrol;
	}

	public ControllerView getControlView() {
		return controlView;
	}

	public void setControlView(ControllerView controlView) {
		this.controlView = controlView;
	}
	
	
	/**
	 * Operació iniciar sessió d'usuari a l'aplicació.
	 * @param usuari 
	 * @param contrasenya
	 * @return retorna true si s'ha pogut iniciar sessió, false si per el contrari les credencials no eren correctes.
	 */
	public Boolean iniciarSessio(String usuari, String contrasenya) {
		login = new Login (usuari, contrasenya);
		logincontrol = new LoginControl(login);
		return logincontrol.CheckLogin();
	}
	
	/**
	 * Operació sortir sessió. Ens retorna a la pantalla d'inici de sessió. 
	 */
	public void sortirSessio() {
		try {
			TalkToServer.connection(JSonOperations.logoutJSon(login.getCodiSessio()));
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Destruir el registre del Login
		login = null; 
	}
	
}
