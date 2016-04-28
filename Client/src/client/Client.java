package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private String line = "";

    private Socket socket = null;

    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    OutputStream outputStream = null;
    DataOutputStream dataOutputStream = null;

    private CommandHandlersChain currentCommandHandlersChain = null;

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

    protected void connectToServer(String address, int port) {
        try {

            InetAddress ipAddress = InetAddress.getByName(address);
            Socket tempSocket = new Socket(ipAddress,port);

            if (isCurrentlyConnected()) {
                disconnect();
            }

            socket = tempSocket;

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream = new DataOutputStream(outputStream);

            waitAnswer();

        } catch (UnknownHostException e) {
            System.out.println("There is no such ip address!");
        } catch (IOException e) {
            System.out.println("Unable to connect to server! Port " + port);
        } catch (IllegalArgumentException e) {
            System.out.println("Port should be <= 65535!");
        }
    }

    protected void sendLine() {
        try {
            dataOutputStream.writeUTF(line);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Unable to send line! Server has closed.");
            disconnect();
        }
    }

    protected void waitAnswer() {
        try {
            String sendByServer = dataInputStream.readUTF();
            System.out.println(sendByServer);
        } catch (IOException e) {
            System.out.println("Unable to read answer from server! Server has closed.");
            disconnect();
        }
    }

    protected void disconnect() {
        try {
            int oldPort = socket.getPort();
            this.socket.close();    // after closing a socket, you cannot reuse it to share other data
            this.socket = null;
            System.out.println("Disconnected from server. Port: " + oldPort);
        } catch (IOException e) {
            System.out.println("Unable to close socket!");
        }
    }

    protected void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (currentCommandHandlersChain.useHandlers(line)) {
            try {
                line = keyboard.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        disconnect();
    }

}
