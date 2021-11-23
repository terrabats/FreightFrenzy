package util;

import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Timer {
    private double startTime = 0;
    private boolean ready = false;
    public void reset(){
        startTime = gameTime.seconds();
        ready = true;
    }
    public double seconds(){
        fault.warn("Used timer before reset", Expectation.SURPRISING, Magnitude.CRITICAL, ready);
        return gameTime.seconds()-startTime;
    }
}
