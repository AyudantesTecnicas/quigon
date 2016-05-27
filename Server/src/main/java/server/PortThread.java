package server;

import creation.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class PortThread extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private GameBuilder gameBuilder;
    private Game game;

    private ArrayList<ClientThread> clientThreads = new ArrayList<>();

    public PortThread(int port, GameBuilder gameBuilder) {
        this.port = port;
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void run() {
        game = gameBuilder.build();
        createServerSocket();
        System.out.println(game.getName() + " is ready and waiting clients in port " + serverSocket.getLocalPort() + ".");

        while (!this.isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Port " + serverSocket.getLocalPort() + " got a client.");
                ClientThread clientThread = new ClientThread(socket, this);
                clientThreads.add(clientThread);
                clientThread.start();
            } catch (SocketException e) {
                System.out.println("Server socket " + serverSocket.getLocalPort()
                                                    + " with game " + game.getName() + " has closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createServerSocket() {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Unable to create new server socket!");
        }
    }

    private void closeServerSocket() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Unable to close server socket!");
        }
    }

    public Game getGame() {
        return game;
    }

    public void resetGame() {
        game = gameBuilder.build();
    }

    public void interrupt() {
        super.interrupt();
        clientThreads.forEach(ClientThread::interrupt);
        closeServerSocket();
    }
}
