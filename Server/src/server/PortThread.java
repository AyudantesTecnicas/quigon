package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PortThread implements Runnable {

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

            sendAnswer("Welcome!");

            goMonitoring();

        } catch (IOException e) {
            System.out.println("Unable to accept client!");
        }
    }

    private void sendAnswer(String answer) {
        try {
            dataOutputStream.writeUTF(answer);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Unable to send answer to client connected to port " + ss.getLocalPort());
        }
    }

    private void goMonitoring() {
        boolean isConnected = true;
        while (isConnected) {
            try {
                sendByClient = dataInputStream.readUTF();
                System.out.println("Port " + ss.getLocalPort() + " got a message: " + sendByClient);
                sendAnswer("[DUMB ANSWER]");
            } catch (IOException e) {
                System.out.println("Port " + ss.getLocalPort() + " has disconnected");
                isConnected = false;
            }
        }
    }
}
