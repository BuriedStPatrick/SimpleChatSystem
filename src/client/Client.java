package client;

import interfaces.ChatClient;
import interfaces.MessageArrivedEvent;
import interfaces.MessageArrivedListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements ChatClient{

    Socket socket;
    PrintWriter output;
    Scanner input;
    String username;
    
    @Override
    public void connect(String serverAddress, int port, String userName) throws UnknownHostException, IOException {
        socket = new Socket(serverAddress, port);
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new Scanner(socket.getInputStream());
        username = userName;
        output.print("CONNECT #"+username);
    }

    @Override
    public void sendMessage(String receiver, String msg) {
        
    }

    @Override
    public void disconnect() {
        
    }

    @Override
    public void addMessageArivedEventListener(MessageArrivedListener listener) {
        
    }

    @Override
    public void removeMessageArivedEventListener(MessageArrivedListener listener) {
        
    }
    
    //DUMMY MAIN METHOD
    public static void main(String[] args) {
        try {        
            Client client = new Client();
            client.addMessageArivedEventListener(new MessageArrivedListener() {

                @Override
                public void MessageArrived(MessageArrivedEvent event) {
                    System.out.println(event.getMessage());
                }
            });
            client.connect("localhost", 4242, "Username");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
