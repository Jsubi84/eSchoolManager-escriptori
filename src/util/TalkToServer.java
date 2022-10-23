package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;


/**
 * @author Jordi Subirana
 *
 * Classe destinada a la comunicació
 */
public class TalkToServer {
	
    private static final int PORT=8080;
    //private static final String IPADDRESS="10.2.55.226";
    private static final String IPADDRESS="localhost";

	/**
     * Enviar crida i esperar la resposta del servidor.
     * @param Crida Format Json com a String, rep la crida a enviar.
     * @return Entrada Resposta del servidor a la crida enviada.
     * @throws IOException
     * @throws ConnectException
     * 
    */
    public static String connection(String crida) throws IOException, ConnectException{
    	
    	String entrada;
      
    	Socket socket = new Socket(IPADDRESS, PORT);
    	
    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    	out.println(crida);
    	
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	entrada = in.readLine();
 	
        socket.close();            
       
        return entrada;
    }	
}