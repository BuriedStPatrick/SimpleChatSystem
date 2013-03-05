package client;

import interfaces.ChatClient;
import interfaces.MessageArrivedListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class Client implements ChatClient{

    @Override
    public void connect(String serverAddress, int port, String userName) throws UnknownHostException, IOException {
        
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
    
    public static void main(String[] args) {
        
    }
    
}
