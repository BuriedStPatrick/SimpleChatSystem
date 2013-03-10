package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Server {

    public static final int PORT = 4242;
    ServerSocket ss;
    HashMap<String, ClientHandler> clients;
    private static int processes = 0;

    private void listen() {
        //Connect to server and accept connection
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Started the server: "+new Date().toString());
        clients = new HashMap<>();
        
        try {
            ss = new ServerSocket(PORT);
            
            clients = new HashMap<>();
            while (true) {
                Socket cs = ss.accept();
                
                ClientHandler ch = new ClientHandler(this, cs);
                ch.start();
            }
        } catch (IOException ex) {
            System.out.println("Could not connect on port " + PORT);
            java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addClient(ClientHandler ch)
    {
        clients.put(ch.getUserID(), ch);
    }

    public static void main(String[] args) {
        try {
            Logger logger = Logger.getLogger(Server.class.getName());
            FileHandler fileTxt = new FileHandler("Logging.txt");
            java.util.logging.Formatter formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);
            Server server = new Server();
            server.listen();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HashMap<String, ClientHandler> getClientHandlers(){
        return clients;
    }
    
    public void online(String input){
        String out = input.substring(0, input.indexOf('#')+1);
            for(String user : clients.keySet())
            {
                out += user+",";
            }

        for(ClientHandler handler : clients.values()){
            handler.sendMessage(out);
        }
    }
    
    public void message(String sender, String msg){
        String reciever = msg.substring(msg.indexOf("#")+1);
        reciever = reciever.substring(0, reciever.indexOf("#"));
        
        ArrayList<String> recievers = new ArrayList();
        recievers.add(sender);
        boolean more = true;
        while(more)
        {
            if (reciever.indexOf(",")<0)
            {
                recievers.add(reciever);
                more = false;
            }
            else{
                recievers.add(reciever.substring(0, reciever.indexOf(",")));
                reciever = reciever.substring(reciever.indexOf(",")+1);
            }
        }
        for (int i = 0; i < recievers.size(); i++) {
            System.out.println(i+": "+recievers.get(i));
        }
        for(ClientHandler handler : clients.values())
        {
            if(recievers.contains("*")||recievers.contains(handler.getUserID()))
            {
                handler.sendMessage("MESSAGE#"+sender+msg.substring(msg.lastIndexOf("#")));
            }
        }
        Logger.getLogger(Server.class.getName()).log(Level.INFO, sender
                +" sent to "
                +recievers.toString()
                +": "+msg+
                " - "
                +new Date().toString());
    }
    
    public void close(String userID){
        clients.get(userID).sendMessage("CLOSE#");
        clients.remove(userID);
        for(ClientHandler handler : clients.values()){
            handler.sendMessage("EXITS#"+userID);
            online("ONLINE#");
        }
        Logger.getLogger(Server.class.getName()).log(Level.INFO, userID+" exits - "+new Date().toString());
    }
}
