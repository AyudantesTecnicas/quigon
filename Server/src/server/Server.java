package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Server {

    private String line;
    private static final String exitCommandPattern = "/exit";
    private int port;

    private boolean commandIsExit() {
        return exitCommandPattern.equalsIgnoreCase(line);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class PortThread implements Runnable {

        private ServerSocket ss;

        public PortThread(ServerSocket ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            try {
                System.out.println("Ready and listening port " + ss.getLocalPort());
                ss.accept();
                System.out.println("Port " + ss.getLocalPort() + " got a client!");
            } catch (IOException e) {
                System.out.println("Unable to accept client!");
            }
        }
    }
}
