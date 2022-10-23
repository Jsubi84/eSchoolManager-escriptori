package model;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.JSONObject;

import util.JSonOperations;
import util.TalkToServer;


/**
 * @author Jordi Subirana
 *
 * Classe Login per gestionar l'acces a l'aplicació
 */
public class Login {
	
	private static final String RESPOSTA_OK= "OK";
	private static final String DATA_MISSING= "Falta introduir alguna dada per poder entrar";
	private static final String CONNECTION_MISSING= "No s'ha pogut establir la connexió";

	private String usuari, contrasenya, codiSessio, nom;
	private Integer codiDepartament; 
	
	/**
	 * Constructor per defecte.
	 */
	public Login() {
	}

	/**
	 * Constructor inicial
	 * @param usuari
	 * @param contrasenya
	 */
	public Login (String usuari, String contrasenya){
		this.setUsuari(usuari);
		this.setContrasenya(contrasenya);
	}
	
	/**
	 * Setters i Getters
	 */
	public String getCodiSessio() {
		return codiSessio;
	}

	public void setCodiSessio(String codiSessio) {
		this.codiSessio = codiSessio;
	}

	public Integer getCodiDepartament() {
		return codiDepartament;
	}

	public void setCodiDepartament(Integer codiDepartament) {
		this.codiDepartament = codiDepartament;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	

/**
 * Metode encarregat de verificar les credencials per accedir.
 * 
 * @return LoginOK En retorna si el Login ha estat exitos.
 */
public Boolean CheckLogin(){
		
		String crida = null, resposta = null;
		
		if (!(getUsuari().isBlank() || getContrasenya().isBlank()) ) {
			
			//Exectuar conversor i obtenir missatge
			crida = JSonOperations.loginJSon(getUsuari(), getContrasenya());
			
			// Resultat de la crida per consola per fer seguiment d'enviament.
			System.out.println(crida); 			
			
			//Tranferencia crida / recepcio de resposta
			try {
				resposta = TalkToServer.connection(crida);
			} catch (IOException e) { 
				System.out.println(e.getMessage());
			}
			
			if (resposta != null) {
				// Resposta a la crida per consola per fer seguiment resposta.
				System.out.println(resposta); 	
				
				return evaluaResposta(resposta);
				
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


	/**
	 * @param resposta
	 * @return si reposta és correcta True és incorrecte False.
	 */
	public Boolean evaluaResposta(String resposta) {
		
		JSONObject jsonUsuari = JSonOperations.StringToJson(resposta);
	
		if (jsonUsuari.get("resposta").equals(RESPOSTA_OK)) {
			JSONObject dades = (JSONObject) jsonUsuari.get("dades");
			
			setCodiSessio((String) dades.get("codiSessio"));
			setCodiDepartament((int) dades.get("codiDepartament"));
			setNom((String) dades.get("nom"));
			return true;
		} else {
			JOptionPane.showMessageDialog(null, jsonUsuari.get("missatge"));
			return false;
		}
	}
}



