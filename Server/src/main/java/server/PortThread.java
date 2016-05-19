package server;

import creation.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PortThread extends Thread {

    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private GameBuilder gameBuilder;
    private Game game;

    boolean clientConnected = false;

    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    OutputStream outputStream = null;
    DataOutputStream dataOutputStream = null;

    String sendByClient = "";

    public PortThread(int port, GameBuilder gameBuilder) {
        this.port = port;
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                setConnection();
            } catch (IOException e) {
                System.out.println( "Server socket " + serverSocket.getLocalPort()
                                    + " with game " + game.getName()
                                    + " has closed, no client." );
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

        game = gameBuilder.build();

        System.out.println(game.getName() + " is ready and listening port " + serverSocket.getLocalPort() + ".");
        socket = serverSocket.accept();    // waiting for client
        serverSocket.close();
        System.out.println("Port " + serverSocket.getLocalPort() + " got a client.");

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);

        sendAnswer("Welcome to game " + game.getName() + "!");
        clientConnected = true;
    }

    private void listenClient() {
        while (clientConnected) {
            try {
                sendByClient = dataInputStream.readUTF();
                System.out.println("Port " + serverSocket.getLocalPort() + " send a message: " + sendByClient);
                String answer = getAnswer();
                if (answer.equals(GameBuilderImp.winText)) {
                    answer = answer + " The game will be reset to initial state.";
                    game = gameBuilder.build();
                }
                sendAnswer(answer);

            } catch (EOFException e) {
                System.out.println("Client at port " + serverSocket.getLocalPort() + " has disconnected.");
                game = gameBuilder.build();
                clientConnected = false;
            } catch (SocketException e) {
                System.out.println("Client at port " + serverSocket.getLocalPort() + " was disconnected.");
                clientConnected = false;
            } catch (IOException e) {
                e.printStackTrace();
                clientConnected = false;
            }
        }
    }

    private String getAnswer() {
        if (this.sendByClient.matches("^(?i)/help$")) {
            return game.getHelp();
        } else {
            return game.receiveCommands(sendByClient);
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
