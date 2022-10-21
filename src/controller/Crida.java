/**
 * 
 */
package controller;

import org.json.JSONObject;


/**
 * @author Jorsu
 *
 */

public class Crida {
	
	//Convertir a JSONObject
	public static JSONObject StringToJson(String resposta) {
		JSONObject jsonObject = new JSONObject(resposta);
		return jsonObject;
	}
	
	// Crear Json per a la crida Login
	public static String loginJSon (String usuari, String password) {
		JSONObject json = new JSONObject();		
		json.put("crida", "LOGIN");	
		JSONObject dades = new JSONObject();	
		dades.put("usuari",usuari);			
		dades.put("contrasenya", password);	
		json.put("dades", dades);
		return json.toString();
	}
	
	
	// Crear Json per a la crida Logout
	public static String logoutJSon (String codisessio) {
		
		// Create Json and serialize
		JSONObject json = new JSONObject();
			json.put("crida", "LOGOUT");
			json.put("codisessio", codisessio);
		return json.toString();
	}
	

		
}
