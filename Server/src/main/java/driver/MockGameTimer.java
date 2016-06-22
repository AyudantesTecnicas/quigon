package driver;

import time.GameTimer;

public class MockGameTimer extends GameTimer {

    public void timePassed(int seconds) {
        for (int counter = seconds; counter > 0; counter--) {
            notifyObservers();
        }
    }

    @Override
    public void start() {
        // mock class: time is controlled manually
    }

    @Override
    public void stop() {
        // mock class: time is controlled manually
    }
}
