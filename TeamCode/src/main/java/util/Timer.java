package util;

import robot.TerraBot;

public class Timer {
    private double startTime = 0;
    public void reset(){
        startTime = TerraBot.gameTime.seconds();
    }
    public double seconds(){
        return TerraBot.gameTime.seconds()-startTime;
    }
}
