package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    Socket socket;
    Server server;
    PrintWriter output;
    Scanner input;
    ChatProtocol cp;
    String userID;

    public ClientHandler(Server s, Socket socket) throws IOException {
        server = s;
        this.socket = socket;
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new Scanner(socket.getInputStream());

        String inputLine, outputLine;
        cp = new ChatProtocol();
        inputLine = input.nextLine();
        outputLine = cp.processInput(inputLine);
        userID = outputLine.substring(outputLine.lastIndexOf('#')+1);
        server.addClient(this);
        server.online(outputLine);
    }
    boolean keepRunning = true;

    @Override
    public void run() {
        while (keepRunning) {
            String msg = input.nextLine();
            String out = cp.processInput(msg);
            output.println(out);
        }
    }
    
    public void sendMessage(String out){
        output.println(out);
    }
    public String getUserID()
    {
        return userID;
    }
}
