package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SocketToServer {
	
	
    private static final int PORT=1234;
    private static final String IPADDRESS="localhost";

   

	/**
     * Sends message to server and checks if its response is as expected
     * @param clientMessage message to be sent to the server
     * @param serverResponse expected server response
     * @throws IOException if an IO error occurs
    */
    
    public static void talkToServer(String crida) throws IOException{
        
        Socket socket = new Socket(	IPADDRESS, PORT);
        DataOutputStream out = new DataOutputStream (socket.getOutputStream());
        out.writeUTF(crida);;
                
        socket.close();
    }
    
   
	
}
