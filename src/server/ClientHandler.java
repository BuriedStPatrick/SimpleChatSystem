package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    
    Socket socket;
    
    
    public ClientHandler(Socket socket) throws IOException
    {
        System.out.println("HELLO?CLIENT HANDLER");
        this.socket = socket;
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        
        Scanner input = new Scanner(socket.getInputStream());
        
        String inputLine, outputLine;
        ChatProtocol cp = new ChatProtocol();
        System.out.println("I CAN GET HERE");
        inputLine = input.nextLine();
        System.out.println("I WANT TO BE HERE");
        cp.processInput(inputLine);
            
        socket.close();
    }
    
    
}
