package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final String exitCommandPattern = "/exit";
    private String line;
    private CommandHandlersChain currentCommandHandlersChain = null;

    public void setCurrentCommandHandlersChain(CommandHandlersChain chc) {
        this.currentCommandHandlersChain = chc;
    }

    private boolean commandIsExit() {
        return exitCommandPattern.equalsIgnoreCase(line);
    }

    public String getLine() {
        return line;
    }

    protected void connectToServer(String address, int port) {
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            new Socket(ipAddress,port);
        } catch (UnknownHostException e) {
            System.out.println("There is no such ip address!");
        } catch (IOException e) {
            System.out.println("Unable to connect to socket!");
        }
    }

    protected void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (!commandIsExit()) {
            try {
                line = keyboard.readLine();
                currentCommandHandlersChain.useHandlers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
