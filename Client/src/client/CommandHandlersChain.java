package client;

import java.util.ArrayList;

public class CommandHandlersChain {

    private ArrayList<CommandHandler> handlers = new ArrayList<>();

    public void add(CommandHandler handler) {
        if (handlers.isEmpty()) {
            handlers.add(handler);
        } else {
            handlers.get(handlers.size() - 1).setNextCommandHandler(handler);
            handlers.add(handler);
        }
    }

    public boolean useHandlers(String line) {
        if (line.equalsIgnoreCase("/exit")) {
            return false;
        } else {
            if (!handlers.isEmpty()) {
                handlers.get(0).handle();
            }
            return true;
        }
    }
}
