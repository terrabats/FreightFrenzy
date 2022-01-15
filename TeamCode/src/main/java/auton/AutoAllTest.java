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
        executor.addSetpoint(42, 41, PI / 2, AngleType.RADIANS);
        executor.addSetpoint(125, 42, PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.SpinCarousel);
//        executor.addSynchronizedRF(autoModules.Intake);
//        executor.addSetpoint(0, 30, -PI / 2, AngleType.RADIANS);
//        executor.addSetpoint(60, -60, 3 * PI / 2, AngleType.RADIANS);
//        executor.addSetpoint(0, 0, 0, AngleType.RADIANS);

        executor.complete();

        waitForStart();

        ready();

        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            update(false);
        }
        bot.tankDrive.move(0, 0);
    }
}
