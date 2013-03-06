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

    public ClientHandler(Server s, Socket socket) throws IOException {
        
        server = s;
        this.socket = socket;
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        Scanner input = new Scanner(socket.getInputStream());

        String inputLine, outputLine;
        ChatProtocol cp = new ChatProtocol();
        
        inputLine = input.nextLine();
        
        cp.processInput(inputLine);

        socket.close();
    }
    boolean keepRunning = true;

    @Override
    public void run() {
        String msg = input.nextLine();
        System.out.println(msg);
        while (keepRunning) {
            msg = input.nextLine();
        }
    }
}