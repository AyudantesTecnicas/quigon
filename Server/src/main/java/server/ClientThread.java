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
            System.out.println(portAndRemoteBase() + ": unable to answer.");
        }
    }

    public void run() {
        portThread.newPlayerJoinedEvent(this);

        while (!this.isInterrupted()) {
            try {
                String sendByClient = dataInputStream.readUTF();
                System.out.println(portAndRemoteBase() + " send: " + sendByClient);
                portThread.playerSendCommandEvent(this, sendByClient);
            } catch (EOFException e) {  // remote socket is closed (client side)
                clientDisconnected();
            } catch (SocketException e) {   // it is ridiculous but Java throws the same exception when different things happens
                if (e.getMessage().equals("Connection reset")) {    // client closed program in inappropriate way ([x], CTRL-C, etc.)
                    clientDisconnected();
                } else {    // local socket is closed (server side)
                    System.out.println(portAndRemoteBase() + " was disconnected.");
                }
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

    private String portAndRemoteBase() {
        return "Port " + socket.getLocalPort() + ", " + socket.getRemoteSocketAddress();
    }

    private void clientDisconnected() {
        System.out.println(portAndRemoteBase() + " has disconnected.");
        portThread.playerLeftGameEvent(this);
    }
}
