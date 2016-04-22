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

    public void useHandlers() {
        handlers.get(0).handle();
    }
}
