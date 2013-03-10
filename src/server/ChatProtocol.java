package server;

public class ChatProtocol {

    private static final int NOTCONNECTED = 0;
    private static final int CONNECTED = 1;
    private static final int CLOSING = 2;
    private int state = NOTCONNECTED;

    public String processInput(String input) {
        String output = null;
        if (state == NOTCONNECTED) {
            if (input.startsWith("CONNECT#")) {
                output = "ONLINE" + input.substring(7);
            }
            state = CONNECTED;
        } else if (state == CONNECTED) {
            if (input.startsWith("SEND#")) {
                output = "MESSAGE" + input.substring(4);
            } else if (input.startsWith("CLOSE#")) {
                output = input;
                state = CLOSING;
            }
            else if(input.startsWith("SERVERSTATUS#")){
                output = "SERVERSTATUS";
            }
        } else if (state == CLOSING)
        {
            output = "";
        }

        return output;
    }
}
