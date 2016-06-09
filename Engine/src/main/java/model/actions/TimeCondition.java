package model.actions;

import java.util.Observable;
import java.util.Observer;

public class TimeCondition extends Observable implements Observer {

    //Attributes
    private Integer timeStampInSeconds;
    private Integer totalSeconds;
    private Boolean initialized;
    private Integer repetitions;

    //Methods
    public TimeCondition(Integer totalSeconds, Integer repetitions) {
        this.totalSeconds = totalSeconds;
        this.timeStampInSeconds = 0;
        this.initialized = false;
        this.repetitions = repetitions;
    }

    public void initialize() {
        this.initialized = true;
    }

    public void end() {
        this.repetitions --;
        this.timeStampInSeconds = 0;

        if (this.repetitions <= 0) {
            this.initialized = false;
        }
    }

    private Boolean metTimeCondition() {
        return ( (this.timeStampInSeconds - this.totalSeconds) > 0 );
    }

    private void updateTimeStamp() {
        if (this.initialized) {
            this.timeStampInSeconds ++;
        }
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("paso un puto segundo");
        if (arg != null && arg.equals(ActionConstants.initialize)) {
            this.initialize();
        } else {
            this.updateTimeStamp();
            if (this.metTimeCondition()) {
                setChanged();
                notifyObservers();
            }
        }

    }

}
