package debugging;

import util.Timer;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.fault;
import static global.General.log;

public class Synchroniser {
    private int numUpdates = 0;
    private final Timer lagTimer = new Timer();
    public void resetDelay(){
        numUpdates = 0;
        lagTimer.reset();
    }
    public void update(){
        numUpdates += 1;
    }
    public void logDelay(){
        fault.check("Sync was never updated", Expectation.UNEXPECTED, Magnitude.CRITICAL, numUpdates == 0);
        log.save("Delay (ms)", (1000 * lagTimer.seconds()) / numUpdates);
    }
}
