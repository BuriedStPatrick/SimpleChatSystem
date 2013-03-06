package server;

public class ServerProtocol {
    
    public String processInput(String input, Server server){
        String output = null;
        
        if(input.equals("ONLINE#")){
            output = input;
            for(String user : server.getClientHandlers().keySet())
            {
                output += user;
            } 
        }
        else if(input.equals("MESSAGE#")){
            output = input;
            
        }
        else if(input.equals(this))
        
        return output;
    }
}
