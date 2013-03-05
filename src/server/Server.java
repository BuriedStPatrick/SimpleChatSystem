
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final int PORT = 4242;
    
    public static void main(String[] args) {
        //Connect to server and accept connection
        ServerSocket ss = null;
        try{
            ss = new ServerSocket(PORT);
            Map<String, Socket> users = new HashMap<>();
            while(true){
            Socket cs = ss.accept();
            
            users.put(null, cs);
            }
        }catch(IOException ioe){
            System.out.println("Could not connect on port " + PORT);
        }
        
        
    }
}
