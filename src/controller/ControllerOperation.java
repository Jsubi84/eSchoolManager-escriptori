package controller;

import java.io.IOException;
import java.net.ConnectException;
import model.Login;
import util.JSonOperations;
import util.TalkToServer;

/**
 * @author Jordi Subirana
 *
 * Classe per poder executar les operacions
 */
public class ControllerOperation {
	
	private Login login;
	private ControllerView controlView;
	
	
	/**
	 * Constructor per defecte
	 */
	public ControllerOperation() {
	}

	
	/**
	 * Setters i Getters
	 */
	public Login getLogin() {
		return login;
	}	

	public void setLogin(Login login) {
		this.login = login;
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
		return login.CheckLogin();
	}
	
	/**
	 * Operació sortir sessió. 
	 * Ens retorna a la pantalla d'inici de sessió.
	 * Destrueix el registre de login. 
	 */
	public void sortirSessio() {
		try {
			TalkToServer.connection(JSonOperations.logoutJSon(login.getCodiSessio()));
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		login = null; 
	}
	
}
