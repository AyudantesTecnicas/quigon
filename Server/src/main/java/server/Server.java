package server;

import creation.GameBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Server {

    private String line = "";
    private int actualPort = 8000;
    private ArrayList<PortThread> portThreads = new ArrayList<>();

    private boolean commandIsExit() {
        return line.equalsIgnoreCase("/exit");
    }

    protected void loadGame(String gamePath) {
        try {
            GameBuilder gameBuilder = BuilderLoader.load(gamePath);
            PortThread portThread = new PortThread(actualPort++, gameBuilder);
            portThreads.add(portThread);
            portThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        while (!commandIsExit()) {
            try {
                line = keyboard.readLine();
                if (line != null && line.matches("^(?i)/load game .+\\.jar$")) {
                    loadGame(line.split(" ")[2]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        portThreads.forEach(PortThread::interrupt);
    }
}
