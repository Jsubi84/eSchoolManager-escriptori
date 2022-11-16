package controller;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Departament;
import model.Login;
import util.TalkToServer;

/**
 * @author Jordi Subirana
 *
 * Classe per poder executar les operacions
 */
public class ControllerOperation {
	
	private static final String RESPOSTA_OK= "OK";
	
	
	private Login login;
	private ControllerView controlView;
	private Departament depts[];
	
	
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
			TalkToServer.connection(login.logoutJSon());
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		login = null; 
	}
	
	
	/**
	 * Metode per agrupar l'enviament de les crides per a Create, Update, Delete.
	 * @param crida
	 * @return resposta
	 */
	private Boolean enviarCridaSimple(String crida) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(crida);
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	   	JSONObject jsonUsuari = new JSONObject(resposta);
		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			return true;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return false;
		}		
	}
	
	
	
	/**
	 * Metode per agrupar l'enviament de les crides Read.
	 * @param crida
	 * @return resposta
	 */
	public JSONArray enviarCridaRetornObjectes(String crida) {
		String resposta="";
		try {
			resposta = TalkToServer.connection(crida);
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	   	JSONObject jsonUsuari = new JSONObject(resposta);	
	   	JSONArray jsonArray = new JSONArray();

		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			JSONObject dades = jsonUsuari.getJSONObject("dades");
						
			Iterator<String> x = dades.keys();
			while (x.hasNext()){
			    String key = (String) x.next();
			    jsonArray.put(dades.get(key));
			}
		
			return jsonArray;
		} else {
			//Missatge d'error en la part del servidor
			getControlView().setIncidencia((String)jsonUsuari.get("missatge"));
			return null;
		}		
	}
	
	public Boolean altaDepartament(Departament departament) {
		return enviarCridaSimple(departament.altaJSon(login.getCodiSessio()));
	}
	
	public Boolean baixaDepartament(int codi) {		
		return enviarCridaSimple(Departament.baixaJSon(login.getCodiSessio(), codi));	
	}
	
	public Boolean modiDepartament(Departament departament) {
		return enviarCridaSimple(departament.modiJSon(login.getCodiSessio()));	
	}
	
	public Departament[] llistarDepartament(String camp, String valor, String ordre) {
		JSONArray arr = enviarCridaRetornObjectes(Departament.llistatJSon(login.getCodiSessio(), camp, valor, ordre));
		
		depts = new Departament[arr.length()];
		for(int i=0; i<arr.length(); i++){   
			  JSONObject o = arr.getJSONObject(i);
			  depts[i]= new Departament();
			  depts[i].setCodi(o.getInt("codiDepartament"));
			  depts[i].setNomDepartament(o.getString("nomDepartament"));
		}
		return depts; 
	}
	
	public Departament consultaIndDepartaments(int codi) {
		
		enviarCridaRetornObjectes(Departament.consultaJSon(login.getCodiSessio(), codi));
		return null;
	}
}
