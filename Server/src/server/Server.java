package server;

import java.io.*;
import java.net.ServerSocket;

public class Server {

    private String line = "";
    private int port = 8000;

    private boolean commandIsExit() {
        return line.equalsIgnoreCase("/exit");
    }

    protected void loadGame() {
        ServerSocket ss;
        int newPort = this.port;
        this.port++;

        try {
            ss = new ServerSocket(newPort);
            PortThread pt = new PortThread(ss);
            new Thread(pt).start();
        } catch (IOException e) {
            System.out.println("Unable to create new server socket!");
        }
    }

    protected void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (!commandIsExit()) {
            try {
                line = keyboard.readLine();
                if (line.matches("^(?i)/load game .*$")) {
                    loadGame();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
