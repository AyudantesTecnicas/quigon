package model.actions;

import java.util.Observable;
import java.util.Observer;

public class TimeCondition extends Observable implements Observer {

    //Attributes
    private Integer timeStampInSeconds;
    private Integer totalSeconds;
    private boolean initialized;
    private boolean repeatable;
    private boolean done;

    //Methods
    public TimeCondition(Integer totalSeconds, boolean repeatable) {
        this.totalSeconds = totalSeconds;
        this.timeStampInSeconds = 0;
        this.initialized = false;
        this.repeatable = repeatable;
        this.done = false;
    }

    public void initialize() {
        this.initialized = true;
        this.done = false;
    }

    private void updateTimeStamp() {
        if (initialized) {
            this.timeStampInSeconds++;
        }
    }

    public void stop() {
        this.initialized = false;
        this.done = true;
    }

    private void end() {
        this.timeStampInSeconds = 0;
        if (!repeatable) {
            stop();
        }
    }

    private boolean canAct() {
        return initialized && (repeatable || !done) && (timeStampInSeconds - totalSeconds) == 0;
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (arg != null && arg.equals(ActionConstants.initialize)) {
            initialize();
        } else {
            this.updateTimeStamp();
            if (canAct()) {
                //System.out.println("Se ejecuta");
                setChanged();
                notifyObservers();
                end();
            }
        }
    }

}
