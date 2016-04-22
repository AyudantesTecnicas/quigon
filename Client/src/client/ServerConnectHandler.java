package client;

public class ServerConnectHandler extends CommandHandler {

    public ServerConnectHandler(Client client) {
        super(client);
    }

    public void handle() {
        /* does it match "/connect X+.X+.X+.X+:X+" ? */
        String line = client.getLine();
        if (line.matches("^(?i)/connect [0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+:[0-9]+$")) {
            String completeAddress = line.split(" ")[1];
            String address = completeAddress.split(":")[0];
            String textPort = completeAddress.split(":")[1];
            int intPort = Integer.parseInt(textPort);
            client.connectToServer(address, intPort);
        } else {
            next.handle();
        }
    }
}
