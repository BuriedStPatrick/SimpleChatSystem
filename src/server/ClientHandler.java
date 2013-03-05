package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    
    Socket socket;
    
    
    public ClientHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        
        Scanner input = new Scanner(socket.getInputStream());
        String msg = input.nextLine();
        
        String inputLine, outputLine;
        ChatProtocol cp = new ChatProtocol();
        cp.processInput(inputLine)
            
        socket.close();
    }
    
    
}
