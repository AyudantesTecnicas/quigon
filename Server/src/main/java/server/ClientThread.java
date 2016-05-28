package server;

import creation.GameBuilderImp;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {

    private Socket socket;

    PortThread portThread;

    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    OutputStream outputStream = null;
    DataOutputStream dataOutputStream = null;

    String sendByClient = "";

    public ClientThread(Socket socket, PortThread portThread) {
        this.socket = socket;
        this.portThread = portThread;

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);
    }

    private String getAnswer() {
        if (this.sendByClient.matches("^(?i)/help$")) {
            return portThread.getGame().getHelp();
        } else {
            return portThread.getGame().receiveCommands(sendByClient);
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

    private void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Unable to close client socket!");
        }
    }

    public void run() {
        sendToClient("Welcome to game " + portThread.getGame().getName() + "! You are Player " + portThread.getNumberOfPlayer(this) + ".");
        portThread.notifyOtherClients("Player " + portThread.getNumberOfPlayer(this) + " joined!", this);

        while (!this.isInterrupted()) {
            try {
                sendByClient = dataInputStream.readUTF();
                System.out.println("Port " + socket.getLocalPort() + " send a message: " + sendByClient);
                String answer = getAnswer();
                if (answer.equals(GameBuilderImp.winText) || answer.equals(GameBuilderImp.loseText)) {
                    answer = answer + " The game will be reset to initial state.";
                    portThread.resetGame();
                    System.out.println(portThread.getGame().getName() + " reset.");
                    portThread.notifyOtherClients("We have a winner! The game will be reset to initial state.", this);
                }
                sendToClient(answer);

            } catch (EOFException e) {
                System.out.println("Client at port " + socket.getLocalPort() + " has disconnected.");
                portThread.excludeClient(this);
                this.interrupt();
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
        closeSocket();
    }

}
