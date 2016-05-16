package client;

public class ExitGameHandler extends CommandHandler {
    public ExitGameHandler(Client client) {
        super(client);
    }

    public void handle() {
        String line = client.getLine();
        if (line.matches("^(?i)/exit game$") && client.isCurrentlyConnected()) {
            client.disconnect();
        } else {
            next.handle();
        }
    }
}
