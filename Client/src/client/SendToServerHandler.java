package client;

public class SendToServerHandler extends CommandHandler {
    public SendToServerHandler(Client client) {
        super(client);
    }

    public void handle() {
        if (client.isCurrentlyConnected()) {
            client.sendLine();
            client.waitAnswer();
        } else {
            next.handle();
        }
    }
}
