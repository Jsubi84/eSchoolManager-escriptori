package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Login {
	
	private static final String DATA_MISSING="Falta introduir alguna dada per poder entrar";
	
	private String usuari, contrasenya, missatge;
	
	public Login (String usuari, String contrasenya){
		this.usuari = usuari;
		this.contrasenya= contrasenya;
	}
	

	public Boolean CheckLogin(){
		
		if (!(usuari.isBlank() || contrasenya.isBlank()) ) {
			
			//Crear conversor i obtenir missatge
			missatge = Crida.loginJSon(usuari, contrasenya);
			
			try {
				SocketToServer.talkToServer(missatge);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(missatge);
			return true;
			
		}else {
			//Missatge informatiu falta alguna dada
			JOptionPane.showMessageDialog(null, DATA_MISSING);
			return false;
		}	
	}
}



