package server;

import java.util.Arrays;

// specific type of array, always has fixed number of elements, and allocates new elements in free slots
public class SlotsOfClientThreads {
    private ClientThread[] clientThreads;

    public SlotsOfClientThreads(int size) {
        clientThreads = new ClientThread[size];
    }

    public int numberOfUsedSlots() {
        int usedSlots = 0;
        for (ClientThread clientThread : clientThreads) {
            if (clientThread != null) {
                usedSlots++;
            }
        }
        return usedSlots;
    }

    public int add(ClientThread newClientThread) {
        int index;
        for (index = 0; index < clientThreads.length; index++) {
            if (clientThreads[index] == null) {
                clientThreads[index] = newClientThread;
                return index;
            }
        }
        return -1;
    }

    public void remove(ClientThread clientThread) {
        int indexToRemove = getIndexOf(clientThread);
        if (indexToRemove != -1) {
            clientThreads[indexToRemove] = null;
        }
    }

    public int getIndexOf(ClientThread clientThread) {
        return Arrays.asList(clientThreads).indexOf(clientThread);
    }

    public void interruptAllClients() {
        for (ClientThread clientThread : clientThreads) {
            if (clientThread != null) {
                clientThread.interrupt();
            }
        }
    }

    public void notifyOtherClients(String msg, ClientThread informer) {
        for (ClientThread clientThread : clientThreads) {
            if ((clientThread != null) && (clientThread != informer)) {
                clientThread.sendToClient(msg);
            }
        }
    }
}
