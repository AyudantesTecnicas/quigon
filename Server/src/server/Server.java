package server;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    private String line = "";
    private int port = 8000;
    private ArrayList<PortThread> threads = new ArrayList<>();

    private boolean commandIsExit() {
        return line.equalsIgnoreCase("/exit");
    }

    protected void loadGame() {
        ServerSocket ss;
        int newPort = this.port;
        this.port++;

        try {
            ss = new ServerSocket(newPort);
            PortThread portThread = new PortThread(ss);
            threads.add(portThread);
            portThread.start();
        } catch (IOException e) {
            System.out.println("Unable to create new server socket!");
        }
    }

    private void closeServer() {
        for (PortThread portThread : threads) {
            portThread.interrupt();
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

        closeServer();
    }
}
