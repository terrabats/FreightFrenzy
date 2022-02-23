package auton;

import autoutil.executors.TankExecutor;
import elements.FieldSide;
import geometry.circles.AngleType;
import util.Timer;

import static java.lang.Math.*;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

// TODO FIX: CONVERT TO COMPLETE AUTO

@Disabled
@Autonomous(name="TerraAutoBlue")
public class TerraAutoBlue extends Auto {
    Timer timer = new Timer();

    @Override
    public void initAuto() {
        activate(FieldSide.BLUE);
    }

    @Override
    public void runAuto() {
        TankExecutor executor = new TankExecutor();
        executor.addSetpoint(70, 30, PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.SpinCarousel);
        executor.addSynchronizedRF(autoModules.IntakeAuto);
        executor.addSetpoint(-50, 43, -PI/2, AngleType.RADIANS);
        executor.addSetpoint(-65, 43, -PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.Backward);
        executor.addPause(2);
        executor.addUnsynchronizedRF(autoModules.Forward);
        executor.addSetpoint(-40, 43, -PI/2, AngleType.RADIANS);
        executor.addSetpoint(-70, 15, -PI/2, AngleType.RADIANS);
        executor.addSetpoint(-90, 15, -PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.LiftOdometry);
        executor.addUnsynchronizedRF(autoModules.MoveCWTime(0.5));
        executor.addUnsynchronizedRF(autoModules.MoveForwardTime(2));
        executor.addUnsynchronizedRF(autoModules.ResetTurretAndLift);

        executor.complete();

        waitForStart();

        ready();
        bot.tankOuttake.lockCube();
        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            if (bot.tankLift.getPower() == 0) {
                bot.tankLift.move(0);
            }
            update(true);
        }
        bot.tankDrive.raise();
        moveTime(-0.3, 0.0, 1);
        moveTime(0.0, -0.5, 0.8);
        moveTime(0.3, 0.0, 1);
        moveTime(0.0, 0.5, 0.7);
        moveTime(1, 0.0, 1.3);
        bot.tankDrive.move(0, 0);
    }

    private void moveTime(double f, double t, double time){
        timer.reset();
        while (opModeIsActive() && timer.seconds() < time) {
            bot.tankLift.move(0);
            bot.tankDrive.move(f,t);
        }
    }
}
