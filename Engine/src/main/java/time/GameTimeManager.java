package time;

import model.actions.TimeCondition;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GameTimeManager {

    private ArrayList<Timer> timers = new ArrayList<>();

    public void createNewTimerTask(TimeCondition timeCondition) {
        Timer timer = new Timer(timeCondition.getTotalSeconds() * 1000, timeCondition);
        timers.add(timer);
    }

    public void shootTimeEvent(int number) {
        for (ActionListener actionListener : timers.get(number).getActionListeners()) {
            actionListener.actionPerformed(null);
        }
        timers.get(number).restart();
    }

    public void startTimers() {
        for (Timer timer : timers) {
            timer.start();
        }
    }

    public void stopTimers() {
        for (Timer timer : timers) {
            timer.stop();
        }
    }

}
