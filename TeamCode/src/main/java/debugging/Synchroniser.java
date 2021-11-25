package debugging;

import util.Timer;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.fault;
import static global.General.log;
import static global.Constants.*;

public class Synchroniser {
    private int numUpdates = 0;
    private final Timer lagTimer = new Timer();
    private double delay = 0;
    public void resetDelay(){
        numUpdates = 0;
        lagTimer.reset();
    }
    public void update(){
        numUpdates++;
    }
    public void logDelay() {
        fault.warn("Sync was never updated", Expectation.UNEXPECTED, Magnitude.CRITICAL, numUpdates != 0);
        delay = (1000 * lagTimer.seconds()) / numUpdates;
        log.save("Delay (ms)", delay);
        fault.check("Robot is lagging", Expectation.EXPECTED, Magnitude.CRITICAL, delay < MAX_ALLOWED_DELAY);
    }
}
