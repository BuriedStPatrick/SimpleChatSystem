
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMonitorUDP {
    
    public ServerMonitorUDP(){
        
    }
    
    public static void main(String[] args){
        try {
            while (true) {
        
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        
        byte[] buffer = new byte[255];
        
        DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
        
        datagramSocket.receive(inPacket);
        
        InetAddress address = inPacket.getAddress();
        int port = inPacket.getPort();
        byte[] message = ("Server Time: "+new Date().toString()+"\n"
                +    "Memory consumption: "+(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().freeMemory()/1024/1024)+"\n"
                +    "CPU-load: 10%\n"
                +    "Processes Running: 1\n"
                +    "Available Memory: "+Runtime.getRuntime().freeMemory()).getBytes();
        DatagramPacket outPacket = new DatagramPacket(message, message.length, address, port);
        datagramSocket.send(outPacket);
      }

    } catch (SocketException ex) {
      Logger.getLogger(ServerMonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ServerMonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
