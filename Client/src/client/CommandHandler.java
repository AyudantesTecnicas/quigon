package client;

public abstract class CommandHandler {

    protected CommandHandler next = null;
    protected Client client = null;

    protected CommandHandler(Client client) {
        this.client = client;
    }

    protected void setNextCommandHandler(CommandHandler commandHandler) {
        this.next = commandHandler;
    }

    abstract void handle();

}
