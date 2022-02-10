package autoutil.reactors.mecanum;

import java.util.ArrayList;
import java.util.Arrays;

import autoutil.controllers.PID;
import geometry.position.Point;
import geometry.position.Pose;
import geometry.position.Vector;
import util.codeseg.ParameterCodeSeg;

import static global.General.bot;
import static global.General.log;

public class MecanumPIDReactor extends MecanumReactor{
//    public PID xPID = new PID(0.05,0.005,0.005, 0.05, 0.05, 10, 5);
//    public PID yPID = new PID(0.05,0.005,0.005, 0.05, 0.05, 10, 5);
//    public PID hPID = new PID(0.3,0.005,0.005, 0.05 , 0.05, 10, 5);
    public PID xPID = new PID(0.05,0.0,0.0, 0.05, 0.05, 10, 5);
    public PID yPID = new PID(0.05,0.0,0.0, 0.05, 0.05, 10, 5);
    public PID hPID = new PID(0.3,0.0,0.0, 0.05 , 0.05, 10, 5);

    public MecanumPIDReactor(){
        xPID.setProcessVariable(() -> getPose()[0]); yPID.setProcessVariable(() -> getPose()[1]); hPID.setProcessVariable(() -> getPose()[2]);
        addControllers(xPID, yPID, hPID);
    }



    @Override
    public void setTarget(double[] target) {
        xPID.setTarget(target[0]); yPID.setTarget(target[1]); hPID.setTarget(target[2]);
    }

    @Override
    public void nextTarget() {
        xPID.reset(); yPID.reset(); hPID.reset();
    }

    @Override
    public boolean isAtTarget() {
        return xPID.isAtTarget() && yPID.isAtTarget() && hPID.isAtTarget();
    }

    @Override
    public double[] getError(){
        return new double[]{xPID.getError(), yPID.getError(), hPID.getError()};
    }

    @Override
    public void moveToTarget() {
        xPID.update(); yPID.update(); hPID.update();
        Vector powerVector = new Vector(xPID.getOutput(), yPID.getOutput());
        powerVector = powerVector.getRelativeTo(new Pose(new Point(0,0),getPose()[2]));
        double xOut = powerVector.getX();
        double yOut = powerVector.getY();
        bot.mecanumDrive.move(yOut, xOut, -hPID.getOutput());
//        log.show("Error", Arrays.toString(getError()));
//        log.show("Gyro", bot.gyroSensors.getRightHeadingDeg());
    }
}
