/**
 * 
 */
package controller;

import  javax.json.*;

/**
 * @author Jorsu
 *
 */

public class Crida {
	
	public static JsonObject StringToJson(String missatge) {
	//	JsonObject json= Json.createParser(null)
		
		return null;
	}
	

	// Crear Json per a la crida Login
	public static String loginJSon (String usuari, String password) {
		JsonObject json = Json.createObjectBuilder() 
				.add("crida", "LOGIN")
				.add("usuari",usuari)
				.add("contrasenya", password).build();
		return json.toString();
	}
	
	// Crear Json per a la crida Logout
	public static String logoutJSon (String codisessio) {
		
		// Create Json and serialize
		JsonObject json = Json.createObjectBuilder() 
				.add("crida", "LOGOUT")
				.add("codisessio", codisessio).build();
		return json.toString();
	}
		
}
