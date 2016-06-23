package time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class InternalGameTimer extends GameTimer implements ActionListener {

    //Attributes
    private Timer timer;
    private static final Integer timeInterval = 1000;

    //Methods
    public InternalGameTimer() {
        this.timer = new Timer(timeInterval, this);
    }

    public void actionPerformed(ActionEvent event) {
        setChanged();
        notifyObservers();
        //System.out.println("paso un segundo");
    }

    public void start() {
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }

}
