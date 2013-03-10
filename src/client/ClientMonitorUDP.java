
package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMonitorUDP {
    static int PORT = 4242;
  static String addr = "localhost";

  public DatagramPacket getServerStatus()
  {
      DatagramPacket inPacket = null;
    try {
      
      try (DatagramSocket dataGramSocket = new DatagramSocket()) {
        InetAddress address = InetAddress.getByName(addr);
        
        byte[] buffer = new byte[255];
        inPacket = new DatagramPacket(buffer, buffer.length);
        
        dataGramSocket.receive(inPacket);
        
        
        
      }
    } catch (UnknownHostException ex) {
      Logger.getLogger(ClientMonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SocketException ex) {
      Logger.getLogger(ClientMonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ClientMonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
    }
    return inPacket;
  }
}
