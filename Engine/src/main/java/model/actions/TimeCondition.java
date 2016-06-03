package model.actions;

import java.util.Observable;
import java.util.Observer;

public class TimeCondition extends Observable implements Observer {

    //Attributes
    private Integer timeStampInSeconds;
    private Integer totalSeconds;

    //Methods
    public TimeCondition(Integer totalSeconds) {
        this.totalSeconds = totalSeconds;
        timeStampInSeconds = -1;
    }

    public void setTotalSeconds(Integer totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public void setTimeStampInSeconds(Integer timeStampInSeconds) {
        this.timeStampInSeconds = timeStampInSeconds;
    }

    @Override
    public void update(Observable o, Object arg) {
        /*
        Esto seria: pedirle el tiempo al reloj, obtener los segundos de ese tiempo y fijarse si esta en el rango.
        Si lo cumple notifica a su TimedMove para que ejecute las acciones.
        if ( (o.getActualTime().seconds() - timeStampInSeconds).equals(totalSeconds)) {
            notifyObservers();
        }
         */
    }

}
