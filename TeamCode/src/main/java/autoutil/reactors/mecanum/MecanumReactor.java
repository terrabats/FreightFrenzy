package autoutil.reactors.mecanum;

import java.util.Arrays;

import autoutil.reactors.Reactor;
import geometry.position.Vector2;

import static global.General.bot;
import static global.General.log;

public abstract class MecanumReactor extends Reactor {

    @Override
    public void init() {
        movementController.xController.setProcessError(() -> getPose()[0]);
        movementController.yController.setProcessError(() -> getPose()[1]);
        headingController.setProcessError(() -> getPose()[2]);
        headingController.setProcessError(() -> processThetaError(headingController.getRawError()));
        nextTarget();
    }

    public double processThetaError(double error){
        while (error < -180) {
            error += 360;
        }
        while (error > 180) {
            error -= 360;
        }
        return error;
    }

    @Override
    public double[] getPose() {
        return bot.odometry.getPose();
    }

    @Override
    public void setTarget(double[] target) {
        movementController.xController.setTarget(target[0]);
        movementController.yController.setTarget(target[1]);
        headingController.setTarget(target[2]);
    }

    @Override
    public void nextTarget() {
        movementController.xController.reset();
        movementController.yController.reset();
        headingController.reset();
    }

    @Override
    public boolean isAtTarget() {
        return movementController.xController.isAtTarget() && movementController.yController.isAtTarget() && headingController.isAtTarget();
    }

    @Override
    public void moveToTarget() {
//        controllers.get(0).update(); controllers.get(1).update(); controllers.get(2).update();
//        Vector2 powerVector = new Vector2(controllers.get(0).getOutput(), controllers.get(1).getOutput());
//        powerVector.rotate(getPose()[2]);
//        double xOut = powerVector.getX();
//        double yOut = powerVector.getY();
//        bot.drive.move(yOut, xOut, controllers.get(2).getOutput());
//        log.show("yPID state (Err, Int, Der)", Arrays.toString(controllers.get(1).getErrorState()));
//        log.show("xPID state (Err, Int, Der)", Arrays.toString(controllers.get(0).getErrorState()));
//        log.show("hPID state (Err, Int, Der)", Arrays.toString(controllers.get(2).getErrorState()));
    }
}