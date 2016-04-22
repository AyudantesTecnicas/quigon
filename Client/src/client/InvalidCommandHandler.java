package client;

public class InvalidCommandHandler extends CommandHandler {

    public InvalidCommandHandler(Client client) {
        super(client);
    }

    public void handle() {
        System.out.println("Invalid command!");
    }
}
