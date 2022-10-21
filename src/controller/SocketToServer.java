package controller;

import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;


public class SocketToServer {
	
	
    private static final int PORT=1234;
    private static final String IPADDRESS="localhost";

	/**
     * Enviar crida i esperar la resposta del servidor
     * @param crida en format Json com a String 
     * @return
     * @throws IOException if an IO error occurs
    */
    
    public static String talkToServer(String crida) throws IOException, ConnectException{
    	
    	String entrada;
      
    	Socket socket = new Socket(IPADDRESS, PORT);
    	
    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    	out.println(crida);
    	
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	entrada = in.readLine();
    	
    	/**
        DataOutputStream out = new DataOutputStream (socket.getOutputStream());
        out.writeUTF(crida);   		
            
        DataInputStream in = new DataInputStream (socket.getInputStream());
        entrada = in.readUTF();
    	 */ 
    	
    	
        socket.close();            
       
        return entrada;
    }	
}
