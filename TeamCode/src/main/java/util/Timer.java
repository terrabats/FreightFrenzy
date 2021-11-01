package util;

import static global.General.*;

public class Timer {
    private double startTime = 0;
    public void reset(){
        startTime = gameTime.seconds();
    }
    public double seconds(){
        return gameTime.seconds()-startTime;
    }
}
