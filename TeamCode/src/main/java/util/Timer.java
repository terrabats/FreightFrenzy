package util;

import static global.General.*;

public class Timer {
    private double startTime = 0;
    public void reset(){
        startTime = gameTime.seconds();
    }
    public double seconds(){
        telemetry.addData("GameTime", gameTime.seconds() - startTime);
        return gameTime.seconds()-startTime;
    }
}
