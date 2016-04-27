package server;

import gameCreation.Game;
import gameCreation.GameCreator;

import java.io.*;
import java.net.ServerSocket;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Server {

    private String line = "";
    private int port = 8000;
    private ArrayList<PortThread> threads = new ArrayList<>();

    private GameCreator gameCreator = new GameCreator();

    private boolean commandIsExit() {
        return line.equalsIgnoreCase("/exit");
    }

    protected void loadGame(String gameName) {
        try {
            gameCreator.createGame(gameName);
            Game game = gameCreator.getGame();
            setPort(game);
        } catch (InvalidParameterException e) {
            System.out.println("Invalid game name!");
        }

    }

    private void setPort(Game game) {
        ServerSocket ss;
        int newPort = this.port;
        this.port++;

        try {
            ss = new ServerSocket(newPort);
            PortThread portThread = new PortThread(ss,game);
            threads.add(portThread);
            portThread.start();
        } catch (IOException e) {
            System.out.println("Unable to create new server socket!");
        }
    }

    private void closeServer() {
        for (PortThread portThread : threads) {
            portThread.interrupt();
        }
    }

    protected void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (!commandIsExit()) {
            try {
                line = keyboard.readLine();
                if (line.matches("^(?i)/load game [a-zA-Z0-9_-]+$")) {
                    loadGame(line.split(" ")[2]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        closeServer();
    }
}
