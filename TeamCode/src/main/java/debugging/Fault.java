package debugging;

import util.Timer;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Fault {
    private int checkNum = 0;
    private int numUpdates = 0;
    private final Timer lagTimer = new Timer();
    private final boolean unsafeMode = false;

    public void resetDelay(){
        numUpdates = 0;
        lagTimer.reset();
    }
    public void logDelay(){
        log.save("Delay (ms)", (1000*lagTimer.seconds())/numUpdates);
    }

    public void guess(String msg, boolean test){
        createFault(" Msg: " + msg, test, false);
    }
    public void guess(String msg, Expectation e, Magnitude m, boolean test){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test, false);
    }
    public void check(String msg, boolean test) {
        createFault(" Msg: " + msg, test, true);
    }
    public void check(String msg, Expectation e, Magnitude m, boolean test){
        createFault(" Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString(), test, true);
    }

    private void createFault(String out, boolean test, boolean createException){
        checkNum++;
        if(!test){
            log.display( "Fault: " + checkNum + out);
            if(createException && !unsafeMode) {
                throw new RuntimeException(out);
            }
        }
    }

}
