package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Server {

    public static final int PORT = 4242;
    HashMap<String, ClientHandler> clients;

    private void listen() {
        //Connect to server and accept connection
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
            Map<String, ClientHandler> clients = new HashMap<>();
            while (true) {
                Socket cs = ss.accept();
                
                ClientHandler ch = new ClientHandler(this, cs);
                clients.put(ch.getUserID(), ch);
                ch.start();
            }
        } catch (IOException ex) {
            System.out.println("Could not connect on port " + PORT);
            java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listen();
    }
    
    public HashMap<String, ClientHandler> getClientHandlers(){
        return clients;
    }
    
    public void online(String input){
        String out = null;
        
        if(input.equals("ONLINE#")){
            out = input;
            for(String user : clients.keySet())
            {
                out += user;
            }
        }
        for(ClientHandler handler : clients.values()){
            handler.sendMessage(out);
        }
    }
    
    public void message(){
        
    }
    
    public void close(){
        
    }
}
