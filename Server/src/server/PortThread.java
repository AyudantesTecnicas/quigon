package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PortThread extends Thread {

    private ServerSocket ss;
    private Socket socket;

    boolean clientConnected = false;

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
            System.out.println("Ready and listening port " + ss.getLocalPort() + ".");
            socket = ss.accept();    // waiting for client
            ss.close();
            System.out.println("Port " + ss.getLocalPort() + " got a client.");

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream = new DataOutputStream(outputStream);

            sendAnswer("Welcome!");
            clientConnected = true;
            listenClient();

        } catch (IOException e) {
            System.out.println("Server socket " + ss.getLocalPort() + " has closed, no client accepted.");
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

    private void listenClient() {
        while (clientConnected) {
            try {
                sendByClient = dataInputStream.readUTF();
                System.out.println("Port " + ss.getLocalPort() + " send a message: " + sendByClient);
                sendAnswer("[DUMB ANSWER]");
            } catch (IOException e) {
                System.out.println("Client at port " + ss.getLocalPort() + " is disconnected.");
                clientConnected = false;
            }
        }
    }

    public void interrupt() {
        try {
            this.ss.close();
        } catch (IOException e) {
            System.out.println("Unable to close server socket!");
        }

        if (clientConnected) {
            try {
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Unable to close client socket!");
            }
        }

        super.interrupt();
    }
}
