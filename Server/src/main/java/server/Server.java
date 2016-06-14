package server;

import creation.GameBuilderImp;
import driver.MockGameRandom;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {

    private String line = "";
    private int actualPort = 8000;
    private ArrayList<PortThread> portThreads = new ArrayList<>();

    private boolean commandIsExit() {
        return line.equalsIgnoreCase("/exit");
    }

    protected void loadGame(String gamePath) {
        try {
            GameBuilderImp gameBuilder = BuilderLoader.load(gamePath);
//            if (gameBuilder != null) {
//                gameBuilder.setGameRandom(new MockGameRandom(new ArrayList<>(Arrays.asList(0, 1))));
//            }
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
