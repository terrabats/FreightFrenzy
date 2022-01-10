package auton;

import autoutil.Executor;
import autoutil.MovementExecutor;
import elements.FieldSide;
import geometry.AngleType;

import static java.lang.Math.*;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.Arrays;

@Autonomous(name="AutoAllTest")
public class AutoAllTest extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        reference(this);

        activate(FieldSide.BLUE);

        Executor executor = new Executor();
        executor.addSetpoint(60, 60, PI/2, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.SpinCarousel);
        executor.addSetpoint(120, 0, PI, AngleType.RADIANS);
        executor.addUnsynchronizedRF(autoModules.Intake);
        executor.addSetpoint(60, -60, 3 * PI/2, AngleType.RADIANS);
        executor.addSetpoint(0, 0, 0, AngleType.RADIANS);

        executor.complete();

        waitForStart();

        ready();

        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            update(true);
        }

        bot.tankDrive.move(0, 0);

        end();
    }
}
