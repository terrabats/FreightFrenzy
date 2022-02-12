package autoutil.reactors.mecanum;

import java.util.Arrays;

import autoutil.controllers.PID;
import geometry.position.Vector2;

import static global.General.bot;
import static global.General.log;

public class MecanumPIDReactor extends MecanumReactor{
//    public PID xPID = new PID(0.04,0.005,0.005, 0.05, 0.5, 50, 5);
//    public PID yPID = new PID(0.04,0.005,0.005, 0.05, 0.5, 50, 5);
//    public PID hPID = new PID(0.7,0,0.11, 0.07 , 0.5, 4, Math.toRadians(20));

    public PID xPID = new PID(0.04,0.005,0.0, 0.08, 0.5, 50, 5);
    public PID yPID = new PID(0.04,0.005,0.0, 0.08, 0.5, 50, 5);
    public PID hPID = new PID(0.4,0,0.0, 0.08 , 0.5, 50, Math.toRadians(20));

    public MecanumPIDReactor(){
//        xPID.setProcessError(() -> {
//            Vector2 errorVector = new Vector2(xPID.getRawError(), yPID.getRawError());
//            return errorVector.getRotated(getPose()[2]).getX();
//        });
//        yPID.setProcessError(() ->{
//            Vector2 errorVector = new Vector2(xPID.getRawError(), yPID.getRawError());
//            return errorVector.getRotated(getPose()[2]).getY();
//        });
//
        xPID.setToStandardForm(0.04, 5, 0.2);
        yPID.setToStandardForm(0.04, 5, 0.2);
        hPID.setToStandardForm(0.4, 6, 0.2);

        xPID.setRestOutput(0.05);
        yPID.setRestOutput(0.05);
        hPID.setRestOutput(0.06);
//
        xPID.setAccuracy(1);
        yPID.setAccuracy(1);
        hPID.setAccuracy(4);

        addControllers(xPID, yPID, hPID);
    }
}
