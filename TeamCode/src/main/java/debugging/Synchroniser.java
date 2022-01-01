package debugging;

import util.Timer;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.fault;
import static global.General.log;
import static global.Constants.*;

public class Synchroniser {
    /**
     * Number of methods in common that were run
     */
    private int numberOfCommonMethodsRun = 0;
    private boolean firstUpdate = true;
    /**
     * The number of updates since the start
     */
    private int numUpdates = 0;
    /**
     * The timer to determine the lag
     */
    private final Timer lagTimer = new Timer();

    /**
     * Reset the delay to be updated
     */
    public void resetDelay(){
        numUpdates = 0;
        lagTimer.reset();
    }

    /**
     * Add to the number of updates
     */
    public void update(){
        if(firstUpdate){
            firstUpdate = false;
        }
        numUpdates++;
    }

    /**
     * Logs the delay and warns if either sync was not updated or the robot is experiencing lag as determined by the minimum refresh rate
     */
    public void logDelay() {
        fault.warn("Sync was never updated", Expectation.UNEXPECTED, Magnitude.CRITICAL, numUpdates == 0, false);
        /**
         * The amount of delay between refreshes
         */
        double delay = (1000 * lagTimer.seconds()) / numUpdates;
        log.record("Delay (ms)", delay);
        fault.warn("Robot is lagging", Expectation.EXPECTED, Magnitude.CRITICAL, delay < (1000/MINIMUM_REFRESH_RATE), true);
    }

    public void ranMethodInCommon(){
        numberOfCommonMethodsRun++;
    }

    public int getNumberOfCommonMethodsRun(){
        return numberOfCommonMethodsRun;
    }

    public boolean isFirstUpdate(){
        return firstUpdate;
    }
}
