package autoutil.reactors.mecanum;

import java.util.Arrays;

import autoutil.controllers.Controller;
import autoutil.reactors.Reactor;
import geometry.position.Vector2;

import static global.General.bot;
import static global.General.log;

public abstract class MecanumReactor extends Reactor {

    @Override
    public void init() {
        controllers.get(0).setProcessVariable(() -> getPose()[0]);
        controllers.get(1).setProcessVariable(() -> getPose()[1]);
        controllers.get(2).setProcessVariable(() -> getPose()[2]);
        nextTarget();
    }

    @Override
    public double[] getPose() {
        return bot.odometry2.getPose();
    }

    @Override
    public void setTarget(double[] target) {
        controllers.get(0).setTarget(target[0]);
        controllers.get(1).setTarget(target[1]);
        controllers.get(2).setTarget(target[2]);
    }

    @Override
    public void nextTarget() {
        controllers.get(0).reset();
        controllers.get(1).reset();
        controllers.get(2).reset();
    }

    @Override
    public boolean isAtTarget() {
        return controllers.get(0).isAtTarget() && controllers.get(1).isAtTarget() && controllers.get(2).isAtTarget();
    }

    @Override
    public void moveToTarget() {
        controllers.get(0).update(); controllers.get(1).update(); controllers.get(2).update();
        Vector2 powerVector = new Vector2(controllers.get(0).getOutput(), controllers.get(1).getOutput());
        powerVector.rotate(getPose()[2]);
        double xOut = powerVector.getX();
        double yOut = powerVector.getY();
        bot.mecanumDrive.move(yOut, xOut, controllers.get(2).getOutput());
        log.show("yPID state (Err, Int, Der)", Arrays.toString(controllers.get(1).getErrorState()));
        log.show("xPID state (Err, Int, Der)", Arrays.toString(controllers.get(0).getErrorState()));
        log.show("hPID state (Err, Int, Der)", Arrays.toString(controllers.get(2).getErrorState()));
    }
}
