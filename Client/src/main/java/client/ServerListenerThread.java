package client;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

public class ServerListenerThread extends Thread {

    private DataInputStream dataInputStream;
    private Client client;

    public ServerListenerThread(Client client, DataInputStream dataInputStream) {
        this.client = client;
        this.dataInputStream = dataInputStream;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                System.out.println(dataInputStream.readUTF());
            } catch (EOFException e) {
                System.out.println("Unable to read from server!");
                client.disconnect();
            } catch (SocketException e) {
//                System.out.println("Stopped listening server.");
            } catch (IOException e) {
                e.printStackTrace();
                client.disconnect();
            }
        }
    }
}
