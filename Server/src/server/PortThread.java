package server;

import gameCreation.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PortThread extends Thread {

    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private Game game;

    boolean clientConnected = false;

    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    OutputStream outputStream = null;
    DataOutputStream dataOutputStream = null;

    String sendByClient = "";

    public PortThread(int port, Game game) {
        this.port = port;
        this.game = game;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                setConnection();
            } catch (IOException e) {
                System.out.println("Server socket " + serverSocket.getLocalPort() + " has closed, no client accepted.");
            }

            listenClient();
        }
    }

    private void sendAnswer(String answer) {
        try {
            dataOutputStream.writeUTF(answer);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Unable to send answer to client connected to port " + serverSocket.getLocalPort());
        }
    }

    private void setConnection() throws IOException {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Unable to create new server socket!");
        }

        System.out.println("Ready and listening port " + serverSocket.getLocalPort() + ".");
        socket = serverSocket.accept();    // waiting for client
        serverSocket.close();
        System.out.println("Port " + serverSocket.getLocalPort() + " got a client.");

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);

        sendAnswer("Welcome to game ");
        clientConnected = true;
    }

    private void listenClient() {
        while (clientConnected) {
            try {
                sendByClient = dataInputStream.readUTF();
                System.out.println("Port " + serverSocket.getLocalPort() + " send a message: " + sendByClient);
                String answer = game.receiveCommands(sendByClient);
                sendAnswer(answer);
                if (sendByClient.equals("win")) {
                    clientConnected = false;
                    closeSocket();
                }
            } catch (IOException e) {
                System.out.println("Client at port " + serverSocket.getLocalPort() + " is disconnected.");
                clientConnected = false;
            }
        }
    }

    private void closeServerSocket() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Unable to close server socket!");
        }
    }

    private void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Unable to close client socket!");
        }
    }

    public void interrupt() {
        closeServerSocket();
        if (clientConnected) {
            closeSocket();
        }
        super.interrupt();
    }
}
