package server;

import creation.Notifier;

import java.util.HashMap;

public class ClientNotifier implements Notifier {

    private HashMap<ClientThread, Integer> clientThreads;

    public ClientNotifier(HashMap<ClientThread, Integer> clientThreads) {
        this.clientThreads = clientThreads;
    }

    @Override
    public void notifyPlayer(int number, String msg) {
        for (ClientThread clientThread : clientThreads.keySet()) {
            if (clientThreads.get(clientThread) == number) {
                clientThread.sendToClient(msg);
            }
        }
    }

    @Override
    public void notifyEveryone(String msg) {
        for (ClientThread clientThread : clientThreads.keySet()) {
            clientThread.sendToClient(msg);
        }
    }
}
