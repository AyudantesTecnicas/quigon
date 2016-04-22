package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String line;
    private static final String exitCommandPattern = "/exit";
    private int port = 8000;

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

        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;

        String sendByClient = "";

        public PortThread(ServerSocket ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            try {
                System.out.println("Ready and listening port " + ss.getLocalPort());
                Socket socket = ss.accept();
                System.out.println("Port " + ss.getLocalPort() + " got a client!");

                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                dataInputStream = new DataInputStream(inputStream);
                dataOutputStream = new DataOutputStream(outputStream);

                goMonitoring();

            } catch (IOException e) {
                System.out.println("Unable to accept client!");
            }
        }

        private void sendAnswer() {
            try {
                dataOutputStream.writeUTF("[DUMB ANSWER]");
                dataOutputStream.flush();
            } catch (IOException e) {
                System.out.println("Unable to send answer to client connected to port "+ ss.getLocalPort());
            }
        }

        private void goMonitoring() {
            boolean isConnected = true;
            while(isConnected) {
                try {
                    sendByClient = dataInputStream.readUTF();
                    System.out.println("Port " + ss.getLocalPort() + " got a message: " + sendByClient);
                    sendAnswer();
                } catch (IOException e) {
                    System.out.println("Port " + ss.getLocalPort() + " has disconnected");
                    isConnected = false;
                }
            }
        }
    }
}
