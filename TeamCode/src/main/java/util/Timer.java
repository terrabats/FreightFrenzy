package util;

import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Timer {
    /**
     * Timer class, to use create a timer object in your code as follows:
     * Timer timerName = new Timer();
     */

    /**
     * Start time in seconds (when the timer was last reset)
     */
    private double startTime = 0;
    /**
     * Has the timer ever been reset
     */
    private boolean hasBeenReset = false;
    /**
     * Reset the timer by setting the start time
     */
    public void reset(){
        startTime = gameTime.seconds();
        hasBeenReset = true;
    }

    /**
     * Gets the seconds since last time the timer was reset
     * NOTE: If the timer has never been reset and seconds is called then an warning will be issued
     * @return seconds
     */
    public double seconds(){
        fault.warn("Used timer before reset", Expectation.SURPRISING, Magnitude.CRITICAL, hasBeenReset);
        return gameTime.seconds()-startTime;
    }
}
