package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client {

    private String line = "";
    private Socket socket = null;
    private DataOutputStream dataOutputStream = null;
    private CommandHandlersChain currentCommandHandlersChain = null;
    private ServerListenerThread serverListenerThread;

    /* 'observer' and 'chain of responsibility' mix */
    public void setCurrentCommandHandlersChain(CommandHandlersChain chc) {
        this.currentCommandHandlersChain = chc;
    }

    public String getLine() {
        return line;
    }

    public boolean isCurrentlyConnected() {
        return (socket != null);
    }

    private boolean connectedSameAddress(String address, int port) {
        return isCurrentlyConnected() && socket.getInetAddress().toString().equals("/" + address) && socket.getPort() == port;
    }

    protected void connectToServer(String address, int port) {
        if (!connectedSameAddress(address, port)) {
            try {

                InetAddress ipAddress = InetAddress.getByName(address);
                Socket tempSocket = new Socket(ipAddress, port);

                if (isCurrentlyConnected()) {
                    disconnect();
                }

                socket = tempSocket;

                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                serverListenerThread = new ServerListenerThread(this, new DataInputStream(socket.getInputStream()));
                serverListenerThread.start();

            } catch (UnknownHostException e) {
                System.out.println("There is no such ip address!");
            } catch (IOException e) {
                System.out.println("Unable to connect to server! Port " + port);
            } catch (IllegalArgumentException e) {
                System.out.println("Port should be <= 65535!");
            }
        } else {
            System.out.println("Already connected!");
        }
    }

    protected void sendLine() {
        try {
            dataOutputStream.writeUTF(line);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Unable to send line!");
            disconnect();
        }
    }
    
    protected void disconnect() {
        try {
            serverListenerThread.interrupt();
            int oldPort = socket.getPort();
            this.socket.close();    // after closing a socket, you cannot reuse it to share other data
            this.socket = null;
            System.out.println("Disconnected from server. Port: " + oldPort);
        } catch (IOException e) {
            System.out.println("Unable to close socket!");
        }
    }

    public void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        while (currentCommandHandlersChain.useHandlers(line)) {
            try {
                line = keyboard.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (isCurrentlyConnected()) {
            disconnect();
        }
    }

}
