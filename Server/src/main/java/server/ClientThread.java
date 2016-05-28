package server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {

    private Socket socket;
    private PortThread portThread;

    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;

    public ClientThread(Socket socket, PortThread portThread) {
        this.socket = socket;
        this.portThread = portThread;

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToClient(String msg) {
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Unable to send answer to client connected to port " + socket.getLocalPort());
        }
    }

    public void run() {
        portThread.newPlayerJoinedEvent(this);

        while (!this.isInterrupted()) {
            try {
                String sendByClient = dataInputStream.readUTF();
                System.out.println("Port " + socket.getLocalPort() + " " + socket.getRemoteSocketAddress() + " send a message: " + sendByClient);
                portThread.playerSendCommandEvent(this, sendByClient);
            } catch (EOFException e) {
                System.out.println("Client at port " + socket.getLocalPort() + " has disconnected.");
                portThread.playerLeftGameEvent(this);
            } catch (SocketException e) {
                System.out.println("Client at port " + socket.getLocalPort() + " was disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
                this.interrupt();
            }
        }
    }

    public void interrupt() {
        super.interrupt();

        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Unable to close client socket!");
        }
    }

}
