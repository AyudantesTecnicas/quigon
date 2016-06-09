package model.actions;

import java.util.Observable;
import java.util.Observer;

public class TimedMove extends Move implements Observer {

    //Attributes
    private TimeCondition timeCondition;

    //Methods
    public TimedMove(String name, TimeCondition timeCondition) {
        super(name);
        this.timeCondition = timeCondition;
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void update(Observable observable, Object arg) {
        this.execute();
    }

    @Override
    public void execute() {
        if (super.process()) {
            setChanged();
            notifyObservers(this.getResultMessage());
            this.timeCondition.end();
        }
    }

}
