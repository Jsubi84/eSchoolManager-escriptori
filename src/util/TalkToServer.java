
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * @author Jordi Subirana
 *
 * Classe destinada a la comunicació
 */
public class TalkToServer {

	
    private static final int PORT=8080;
    //private static final String IPADDRESS="localhost";
    private static final String IPADDRESS="192.168.18.137";
    //private static final String IPADDRESS="10.2.55.226";

	/**
     * Enviar crida i esperar la resposta del servidor.
     * @param Crida Format Json com a String, rep la crida a enviar.
     * @return Entrada Resposta del servidor a la crida enviada.
	 * @throws ConnectException 
     * @throws IOException
     * @throws ConnectException
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
     * 
    */
    public static String connection(String crida) throws ConnectException, IOException{
    	String entrada;
    	
		// Resultat de la crida per consola per fer seguiment d'enviament.
		System.out.println(crida); 	
		
		//ncriptacio comunicació
		Encrip encriptar = new Encrip();
		String encriptat = encriptar.encriptar(crida);
		System.out.println(encriptat);
    	
    	Socket socket = new Socket(IPADDRESS, PORT);
    	
    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    	//out.println(crida); 
    	out.println(encriptat);
    	
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	entrada = in.readLine();
    	
    	System.out.println(entrada); 
    	String  entradaDEC = encriptar.desencriptar(entrada);
    	
 		// Resposta a la crida per consola per fer seguiment resposta.   
		System.out.println(entradaDEC);	
 	
        socket.close();           
     
        return entradaDEC;
    }		
 
}
