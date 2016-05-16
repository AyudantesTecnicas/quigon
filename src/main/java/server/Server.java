package server;

import creation.GameBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Server {

    private String line = "";
    private int actualPort = 8000;
    private ArrayList<PortThread> threads = new ArrayList<>();

    private GameBuilder gameBuilder;

    private boolean commandIsExit() {
        return line.equalsIgnoreCase("/exit");
    }

    protected void loadGame(String gameName) {
        try {
            gameBuilder = BuilderLoader.load(gameName);
            setPort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPort() {
        PortThread portThread = new PortThread(actualPort++,gameBuilder);
        threads.add(portThread);
        portThread.start();
    }

    private void closeServer() {
        threads.forEach(PortThread::interrupt);
    }

    protected void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        while (!commandIsExit()) {
            try {
                line = keyboard.readLine();
                if (line != null && line.matches("^(?i)/load game [a-zA-Z0-9_-]+$")) {
                    loadGame(line.split(" ")[2]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        closeServer();
    }
}
