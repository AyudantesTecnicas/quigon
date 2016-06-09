package model.actions;

import java.util.Observable;
import java.util.Observer;

public class TimedMove extends Move implements Observer {

    //Methods
    public TimedMove(String name) {
        super(name);
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
        }
        System.out.println("me llamaron");
    }
}
