package time;

import java.util.Observable;

public abstract class GameTimer extends Observable {
    public abstract void start();

    public abstract void stop();
}
