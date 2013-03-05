package server;

public class ChatProtocol {
    
    private static final int NOTCONNECTED = 0;
    private static final int CONNECTED = 1;
    private static final int CLOSING = 2;
    
    private int state = NOTCONNECTED;
    
    public String processInput(String input){
        String output = null;
        if(state == NOTCONNECTED){
            if(input.startsWith("CONNECT #"))
                output = "ONLINE "+input.substring(8);
            state = CONNECTED;
        }
        else if(state == CONNECTED){
            if(input.startsWith("SEND #")){
                output = "MESSAGE "+input.substring(5);
            }
            else if(input.startsWith("CLOSE #")){
                output = input;
            }
        
                
        }
        
        return output;
    }
    
}
