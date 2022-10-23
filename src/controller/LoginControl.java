package controller;

import java.io.IOException;

import javax.swing.JOptionPane;
import org.json.JSONObject;

import model.Login;

public class LoginControl {
	
	private static final String RESPOSTA_OK= "OK";
	private static final String DATA_MISSING= "Falta introduir alguna dada per poder entrar";
	private static final String CONNECTION_MISSING= "No s'ha pogut establir la connexió";
	
	private String crida, resposta;
	JSONObject jsonUsuari;
	Login login;
	
	public LoginControl (Login login){
		this.login = login;
	}
	

	public Boolean CheckLogin(){
		
		if (!(login.getUsuari().isBlank() || login.getContrasenya().isBlank()) ) {
			
			//Crear conversor i obtenir missatge
			crida = JSonOperations.loginJSon(login.getUsuari(), login.getContrasenya());
			
			// Resultat de la crida per consola per fer seguiment d'enviament.
			System.out.println(crida); 			
			
			//Tranferencia crida / recepcio de resposta
			try {
				resposta = TalkToServer.connection(crida);
			} catch (IOException e) { 
				System.out.println(e.getMessage());
			}
			
			if (resposta != null) {
				// Resposta a la crida per consola per fer seguiment part rebuda.
				System.out.println(resposta); 	
				
				jsonUsuari = JSonOperations.StringToJson(resposta);
				
				if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
					JSONObject dades = (JSONObject) jsonUsuari.get("dades");
					
					login.setCodiSessio((String) dades.get("codiSessio"));
					login.setCodiDepartament((int) dades.get("codiDepartament"));
					login.setNom((String) dades.get("nom"));
					
					return true;
				} else {
					JOptionPane.showMessageDialog(null, jsonUsuari.get("missatge"));
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(null, CONNECTION_MISSING);
				return false;
			}

		}else {
			//Missatge informatiu falta alguna dada
			JOptionPane.showMessageDialog(null, DATA_MISSING);
			return false;
		}	
	}

}



