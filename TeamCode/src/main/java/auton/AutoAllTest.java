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
        executor.addSynchronizedRF(autoModules.Intake);
        executor.addSetpoint(0, 0, 0, AngleType.RADIANS);

        executor.complete();

        waitForStart();

        ready();

        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            log.showAndRecord("cur path index", executor.curPath + " " + executor.curPose);
            log.showAndRecord("path length", executor.paths.size() + " " + executor.paths.get(executor.curPath).size());
            log.showAndRecord("Curpos", Arrays.toString(bot.odometry.curPos));
            log.showAndRecord("Target", executor.paths.get(executor.curPath).get(executor.paths.get(executor.curPath).size() - 1));
            update(true);
        }

        end();
    }
}
