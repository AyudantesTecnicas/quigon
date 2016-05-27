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

    ClientThread clientThread;

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
                clientThread = new ClientThread(socket, this);
                clientThread.start();
            } catch (IOException e) {
                System.out.println("Server socket " + serverSocket.getLocalPort()
                                                    + " with game " + game.getName() + " has closed, no client.");
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
        closeServerSocket();
        clientThread.interrupt();
        super.interrupt();
    }
}
