package autoutil.reactors.mecanum;

import java.util.Arrays;

import autoutil.controllers.Controller2D;
import autoutil.controllers.Default2D;
import autoutil.controllers.PID;
import geometry.position.Vector2;

import static global.General.bot;
import static global.General.log;

// TOD4 FIX
// Make this using contructor properlt
public class MecanumPIDReactor extends MecanumReactor{
//    public PID xPID = new PID(0.04,0.005,0.005, 0.05, 0.5, 50, 5);
//    public PID yPID = new PID(0.04,0.005,0.005, 0.05, 0.5, 50, 5);
//    public PID hPID = new PID(0.7,0,0.11, 0.07 , 0.5, 4, Math.toRadians(20));

    public PID xPID = new PID(PID.PIDParameterType.DEFAULT_ALL, 0.04,0.005,0.0, 0.2, 0.05, 50.0, 5.0);
    public PID yPID = new PID(PID.PIDParameterType.DEFAULT_ALL, 0.04,0.005,0.0, 0.2, 0.05, 50., 5.0);
    public PID hPID = new PID(PID.PIDParameterType.DEFAULT_ALL, 0.4,0.0,0.0, 0.2 , 0.05, 50.0, Math.toRadians(20));

    public MecanumPIDReactor(){
        xPID.setToStandardForm(0.04, 5, 0.2);
        yPID.setToStandardForm(0.04, 5, 0.2);
        hPID.setToStandardForm(0.4, 6, 0.2);

        xPID.setRestOutput(0.05);
        yPID.setRestOutput(0.05);
        hPID.setRestOutput(0.08);

        xPID.setAccuracy(2);
        yPID.setAccuracy(2);
        hPID.setAccuracy(4);

        setControllers(new Default2D(xPID, yPID), hPID);
    }
}