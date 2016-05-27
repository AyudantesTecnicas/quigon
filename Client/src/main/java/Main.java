import client.*;

public class Main {
    public static void main(String[] ar) {
        Client client = new Client();

        CommandHandlersChain commandHandlersChain = new CommandHandlersChain();

        commandHandlersChain.add(new ServerConnectHandler(client));
        commandHandlersChain.add(new ExitGameHandler(client));
        commandHandlersChain.add(new SendToServerHandler(client));
        commandHandlersChain.add(new InvalidCommandHandler(client));

        client.setCurrentCommandHandlersChain(commandHandlersChain);

        client.run();
    }
}
