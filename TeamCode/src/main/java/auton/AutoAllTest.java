package auton;

import autoutil.executors.Executor;
import elements.FieldSide;
import geometry.circles.AngleType;
import robotparts.electronics.positional.PMotor;
import util.Timer;

import static java.lang.Math.*;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoAllTest")
public class AutoAllTest extends Auto {

    @Override
    public void initAuto() {
        activate(FieldSide.BLUE);
    }

    @Override
    public void runAuto() {
        Executor executor = new Executor();
        executor.addSetpoint(70, 29, 0, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.SpinCarousel);
        executor.addSynchronizedRF(autoModules.Intake);
        executor.addSetpoint(-50, 40, PI, AngleType.RADIANS);
        executor.addSetpoint(-65, 40, PI, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.Backward);
        executor.addUnsynchronizedRF(autoModules.Forward);

        executor.complete();

        waitForStart();

        ready();

        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            if (bot.lift.getPower() == 0) {
                bot.lift.move(0);
            }
            update(true);
        }
        bot.tankDrive.raise();
        Timer timer = new Timer();
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 1) {
            // TODO NEW
            // ADD PARK HERE
            bot.tankDrive.move(0.2,0.0);
        }
        bot.tankDrive.move(0, 0);
    }
}
