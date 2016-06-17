package model.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
public class TimeCondition extends Observable implements Observer, ActionListener {

    //Attributes
    private Integer totalSeconds;
    private boolean repeatable;
    private boolean initialized;
    private boolean done;
    private Timer timer;

    //Methods
    public TimeCondition(Integer totalSeconds, boolean repeatable) {
        this.totalSeconds = totalSeconds;
        this.repeatable = repeatable;
        this.initialized = false;
        this.done = false;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void initialize() {
        timer.start();
        this.initialized = true;
        this.done = false;
    }

    public void stop() {
        this.initialized = false;
        this.done = true;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    private boolean canAct() {
        return initialized && (repeatable || !done);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (!this.initialized) {
            initialize();
        }
        else {
            stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (canAct()) {
            //System.out.println("Se ejecuta");
            setChanged();
            notifyObservers();
            if (!repeatable) {
                stop();
            }
        }
    }
}
