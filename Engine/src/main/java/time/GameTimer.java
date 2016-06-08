package time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;

public class GameTimer extends Observable implements ActionListener {

    //Attributes
    private Timer timer;
    private static final Integer timeInterval = 1000;

    //Methods
    public GameTimer() {
        this.timer = new Timer(timeInterval, this);
    }


    public void actionPerformed(ActionEvent event) {
        setChanged();
        notifyObservers();
    }

    public void start() {
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }

    public Integer getTime() {
        return this.timer.getDelay();
    }

}
