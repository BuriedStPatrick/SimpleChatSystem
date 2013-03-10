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
import javax.swing.event.EventListenerList;

public class Client extends Thread implements ChatClient {

    Socket socket;
    PrintWriter output;
    Scanner input;
    String username;
    protected EventListenerList listenerList = new EventListenerList();
    static Scanner DUMMYINPUT;

    @Override
    public void connect(String serverAddress, int port, String userName) throws UnknownHostException, IOException {
        socket = new Socket(serverAddress, port);
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new Scanner(socket.getInputStream());
        username = userName;
        output.println("CONNECT#" + username);
        start();
    }

    @Override
    public void sendMessage(String receiver, String msg) {
        output.println("SEND#" + receiver + "#" + msg);
    }
    
    public void serverMonitor(){
        ClientMonitorUDP cmudp = new ClientMonitorUDP();
        String out = new String(cmudp.getServerStatus().getData());
        System.out.println(out);
    }

    @Override
    public void disconnect() {
        output.println("CLOSE#");
    }

    @Override
    public void addMessageArivedEventListener(MessageArrivedListener listener) {
        listenerList.add(MessageArrivedListener.class, listener);
    }

    @Override
    public void removeMessageArivedEventListener(MessageArrivedListener listener) {
        listenerList.remove(MessageArrivedListener.class, listener);
    }

    void fireMessageArrivedEventEvent(MessageArrivedEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == MessageArrivedListener.class) {
                ((MessageArrivedListener) listeners[i + 1]).MessageArrived(evt);
            }
        }
    }

    //DUMMY MAIN METHOD FOR TESTING STATES
    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.addMessageArivedEventListener(new MessageArrivedListener() {
                @Override
                public void MessageArrived(MessageArrivedEvent event) {
                    System.out.println(event.getMessage());
                }
            });
            client.connect("localhost", 4242, "User1");
            client.sendMessage("*", "Hello World 1");
            client.sendMessage("*", "Hello World 2");
            client.sendMessage("*", "Hello World 3");
            client.sendMessage("*", "Hello World 4");
            client.serverMonitor();
            client.disconnect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        boolean keepRunning = true;
//        DummyScanner ds = new DummyScanner();
//        ds.start();
        while (keepRunning) {
            String msg = input.nextLine();
            if (msg.startsWith("CLOSE#")) {
                try {
                    socket.close();
                    keepRunning = false;
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                fireMessageArrivedEventEvent(new MessageArrivedEvent(this, msg));
                System.out.println(msg);
            }
        }

    }

//    private class DummyScanner extends Thread {
//
//        public DummyScanner() {
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                String msg = DUMMYINPUT.nextLine();
//                sendMessage("*", msg);
//            }
//        }
//    }
}
