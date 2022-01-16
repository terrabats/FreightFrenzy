package auton;

import autoutil.executors.Executor;
import elements.FieldSide;
import geometry.circles.AngleType;

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
        executor.addSetpoint(70, 28, PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.SpinCarousel);
        executor.addSetpoint(-50, 28, -PI/2, AngleType.RADIANS);
        executor.addSetpoint(-60, 28, -PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.Intake);
        executor.addUnsynchronizedRF(autoModules.Backward);
        executor.addUnsynchronizedRF(autoModules.Forward);

        executor.complete();

        waitForStart();

        ready();

        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            update(true);
        }
        bot.tankDrive.move(0, 0);
    }
}
