package autoutil.reactors.mecanum;

import java.util.Arrays;

import autoutil.reactors.Reactor;
import geometry.position.Pose;
import geometry.position.Vector2;
import robotparts.sensors.GyroSensors;

import static global.General.bot;
import static global.General.log;

public abstract class MecanumReactor extends Reactor {

    @Override
    public void init() {
        movementController.xController.setProcessError(() -> getPose().p.x);
        movementController.yController.setProcessError(() -> getPose().p.y);
        headingController.setProcessError(() -> getPose().ang);
        headingController.setProcessError(() -> GyroSensors.processThetaError(headingController.getRawError()));
        nextTarget();
    }

    @Override
    public Pose getPose() {
        return new Pose(bot.odometry.getPose());
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
        movementController.update(getPose(), pathSegment);
        headingController.update(getPose(), pathSegment);
        bot.drive.move(movementController.getOutputY(), movementController.getOutputX(), headingController.getOutput());
//        log.show("yPID state (Err, Int, Der)", Arrays.toString(controllers.get(1).getErrorState()));
//        log.show("xPID state (Err, Int, Der)", Arrays.toString(controllers.get(0).getErrorState()));
//        log.show("hPID state (Err, Int, Der)", Arrays.toString(controllers.get(2).getErrorState()));
    }
}