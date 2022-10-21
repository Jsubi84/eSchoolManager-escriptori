package controller;

import java.io.IOException;

import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Login {
	
	private static final String RESPOSTA_OK= "OK";
	private static final String DATA_MISSING= "Falta introduir alguna dada per poder entrar";
	private static final String CONNECTION_MISSING= "No s'ha pogut establir la connexió";
	
	private String usuari, contrasenya, crida, resposta;
	JSONObject jsonUsuari;
	
	private String codiSessio;
	
	public Login (String usuari, String contrasenya){
		this.usuari = usuari;
		this.contrasenya= contrasenya;
	}
	

	public Boolean CheckLogin(){
		
		if (!(usuari.isBlank() || contrasenya.isBlank()) ) {
			
			//Crear conversor i obtenir missatge
			crida = Crida.loginJSon(usuari, contrasenya);
			
			// Resultat de la crida per consola per fer seguiment d'enviament.
			System.out.println(crida); 			
			
			//Tranferencia crida / recepcio de resposta
			try {
				resposta = SocketToServer.talkToServer(crida);
			} catch (IOException e) { 
				System.out.println(e.getMessage());
			}
			
			if (resposta != null) {
				// Resposta a la crida per consola per fer seguiment part rebuda.
				System.out.println(resposta); 	
				
				jsonUsuari = Crida.StringToJson(resposta);
				
				if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
					JSONObject dades = (JSONObject) jsonUsuari.get("dades");
					
					setCodiSessio((String) dades.get("codiSessio"));	
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


	public String getCodiSessio() {
		return codiSessio;
	}


	public void setCodiSessio(String codiSessio) {
		this.codiSessio = codiSessio;
	}
}



