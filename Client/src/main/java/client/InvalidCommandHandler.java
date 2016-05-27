package client;

public class InvalidCommandHandler extends CommandHandler {

    public InvalidCommandHandler(Client client) {
        super(client);
    }

    public void handle() {
        if (!client.getLine().equals("")) {
            System.out.println("Invalid command!");
        }
    }
}
