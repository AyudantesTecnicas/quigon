package model.actions;

import java.util.Observable;
import java.util.Observer;

public class TimedMove extends Move implements Observer {

    public TimedMove(String name) {
        super(name);
    }

    //Methods
    @Override
    public void update(Observable o, Object arg) {
        this.execute();
    }

}
