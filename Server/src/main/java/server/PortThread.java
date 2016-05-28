package server;

import creation.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class PortThread extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private GameBuilder gameBuilder;
    private Game game;

    private HashMap<ClientThread, Integer> clientThreads = new HashMap<>();

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
                if (clientThreads.size() < game.getNumberOfPlayers()) {
                    System.out.println("Port " + serverSocket.getLocalPort() + " got a client.");
                    ClientThread clientThread = new ClientThread(socket, this);
                    addNewClientThread(clientThread);
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

    private void addNewClientThread(ClientThread clientThread) {
        int currentNumber = 0;
        boolean inserted = false;
        while (!inserted) {
            if (clientThreads.values().contains(currentNumber)) {
                currentNumber++;
            } else {
                clientThreads.put(clientThread, currentNumber);
                inserted = true;
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

    public void newPlayerJoinedEvent(ClientThread clientThread) {
        clientThread.sendToClient("Welcome to game " + game.getName() + "! You are Player " + getNumberOfPlayer(clientThread) + ".");
        notifyOtherClients("Player " + getNumberOfPlayer(clientThread) + " joined!", clientThread);
    }

    public void playerSendCommandEvent(ClientThread clientThread, String cmd) {
        notifyOtherClients("Player " + getNumberOfPlayer(clientThread) + " send this: " + cmd, clientThread);

        String answer;
        if (cmd.matches("^(?i)/help$")) {
            answer = game.getHelp();
        } else {
            answer = game.receiveCommands(cmd);
        }

        if (answer.equals(GameBuilderImp.winText) || answer.equals(GameBuilderImp.loseText)) {
            notifyOtherClients("Player " + getNumberOfPlayer(clientThread) + " won (lose)!" + " Game reset.", clientThread);
            resetGame();
            answer = answer + " Game reset.";
        }

        clientThread.sendToClient(answer);
    }

    public void playerLeftGameEvent(ClientThread clientThread) {
        notifyOtherClients("Player " + getNumberOfPlayer(clientThread) + " escaped.", clientThread);

        clientThreads.remove(clientThread);
        if (clientThreads.size() == 0) {
            System.out.println("Last player abandoned " + serverSocket.getLocalPort() + ".");
            resetGame();
        }

        clientThread.interrupt();
    }

    public void resetGame() {
        game = gameBuilder.build();
        System.out.println(game.getName() + " reset.");
    }

    public int getNumberOfPlayer(ClientThread clientThread) {
        return clientThreads.get(clientThread);
    }

    public void notifyOtherClients(String msg, ClientThread informer) {
        for (ClientThread clientThread : clientThreads.keySet()) {
            if (clientThread != informer) {
                clientThread.sendToClient(msg);
            }
        }
    }

    public void interrupt() {
        super.interrupt();

        clientThreads.keySet().forEach(ClientThread::interrupt);

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Unable to close server socket!");
        }
    }
}
