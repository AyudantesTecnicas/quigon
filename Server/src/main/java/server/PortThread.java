package server;

import creation.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PortThread extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private GameBuilder gameBuilder;
    private Game game;

    private SlotsOfClientThreads clientThreads;

    public PortThread(int port, GameBuilder gameBuilder) {
        this.port = port;
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void run() {
        game = gameBuilder.build();
        clientThreads = new SlotsOfClientThreads(game.getNumberOfPlayers());
        createServerSocket();
        System.out.println(game.getName() + " is ready and waiting clients in port " + serverSocket.getLocalPort() + ".");

        while (!this.isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                if (clientThreads.numberOfUsedSlots() < game.getNumberOfPlayers()) {
                    System.out.println("Port " + serverSocket.getLocalPort() + " got a client.");
                    ClientThread clientThread = new ClientThread(socket, this);
                    clientThreads.add(clientThread);
                    clientThread.start();
                } else {
                    socket.close();
                    System.out.println("Port " + serverSocket.getLocalPort() + " rejected a client: limit of players reached.");
                }
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

    public void excludeClient(ClientThread client) {
        clientThreads.remove(client);
        if (clientThreads.numberOfUsedSlots() == 0) {
            resetGame();
            System.out.println(game.getName() + " reset, last player abandoned the game.");
        }
    }

    public int getNumberOfPlayer(ClientThread clientThread) {
        return clientThreads.getIndexOf(clientThread);
    }

    public void notifyOtherClients(String msg, ClientThread informer) {
        clientThreads.notifyOtherClients(msg, informer);
    }

    public void interrupt() {
        super.interrupt();
        clientThreads.interruptAllClients();
        closeServerSocket();
    }
}
