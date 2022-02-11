package autoutil.reactors.mecanum;

import java.util.Arrays;

import autoutil.controllers.PID;
import geometry.position.Vector2;

import static global.General.bot;
import static global.General.log;

public class MecanumPIDReactor extends MecanumReactor{
    public PID xPID = new PID(0.04,0.005,0.005, 0.05, 0.5, 50, 5);
    public PID yPID = new PID(0.04,0.005,0.005, 0.05, 0.5, 50, 5);
    public PID hPID = new PID(0.7,0,0.11, 0.07 , 0.5, 4, Math.toRadians(20));

    public MecanumPIDReactor(){
        xPID.setProcessVariable(() -> getPose()[0]); yPID.setProcessVariable(() -> getPose()[1]); hPID.setProcessVariable(() -> getPose()[2]);
//
//        xPID.setProcessError(() -> {
//            Vector2 errorVector = new Vector2(xPID.getRawError(), yPID.getRawError());
//            return errorVector.getRotated(getPose()[2]).getX();
//        });
//        yPID.setProcessError(() ->{
//            Vector2 errorVector = new Vector2(xPID.getRawError(), yPID.getRawError());
//            return errorVector.getRotated(getPose()[2]).getY();
//        });
//
//        xPID.setToStandardForm(0.04, 0.5, 0.5);
//        xPID.setToStandardForm(0.04, 0.5, 0.5);
//        xPID.setToStandardForm(0.7, 0.5, 0.5);

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
        Vector2 powerVector = new Vector2(xPID.getOutput(), yPID.getOutput());
        powerVector.rotate(getPose()[2]);
        double xOut = powerVector.getX();
        double yOut = powerVector.getY();
        bot.mecanumDrive.move(yOut, xOut, hPID.getOutput());
        log.show("yPID state (Err, Int, Der)", Arrays.toString(yPID.getState()));
        log.show("xPID state (Err, Int, Der)", Arrays.toString(xPID.getState()));
        log.show("hPID state (Err, Int, Der)", Arrays.toString(hPID.getState()));
//        bot.mecanumDrive.move(yPID.getOutput(), xPID.getOutput(), hPID.getOutput());
//        log.show("Error", Arrays.toString(getError()));
//        log.show("Gyro", bot.gyroSensors.getRightHeadingDeg());
    }
}
