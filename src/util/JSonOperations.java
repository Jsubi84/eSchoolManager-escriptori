package util;

import org.json.JSONObject;


/**
 * @author Jordi Subirana
 *
 * Classe de metodes estatics per gestionar les conversion al forma JSON
 */

public class JSonOperations {
	
	
	/**
	 * @param resposta  Rep una cadena string en format JSO
	 * @return JSONObject 
	 */
	public static JSONObject StringToJson(String resposta) {
		JSONObject jsonObject = new JSONObject(resposta);
		return jsonObject;
	}
	

	/**
	 * @param usuari Usuari passat a pantalla login
	 * @param password Password passat a pantalla login
	 * @return crida Envia String en format JSON que conte la crida per fer el Login
	 */
	public static String loginJSon (String usuari, String password) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LOGIN");	
		JSONObject dades = new JSONObject();	
		dades.put("usuari",usuari);			
		dades.put("contrasenya", password);	
		json.put("dades", dades);
		return json.toString();
	}
	
	
	/**
	 * @param codisessio
	 * @return crida String en format JSON que conte la crida per fer el Logout
	 */
	public static String logoutJSon (String codisessio) {
		
		// Create Json and serialize
		JSONObject json = new JSONObject();
			json.put("crida", "LOGOUT");
			json.put("codisessio", codisessio);
		return json.toString();
	}
		
}
