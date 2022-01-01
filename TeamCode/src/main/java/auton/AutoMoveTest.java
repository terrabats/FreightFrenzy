package auton;

import autoutil.Executor;
import elements.FieldSide;
import geometry.AngleType;

import static java.lang.Math.*;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.Arrays;

@Autonomous(name="AutoMoveTest")
public class AutoMoveTest extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        reference(this);

        activate(FieldSide.BLUE);

        Executor executor = new Executor(0, 0, PI/2, AngleType.RADIANS);
        executor.addSetpoint(60, 60, PI/2, AngleType.RADIANS);
//        executor.addSetpoint(0, 10, 0, AngleType.RADIANS);
        executor.complete();

        waitForStart();

        ready();

        executor.resume();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
//            log.show(executor.reactor.moveForward(executor.));
            log.showAndRecord("cur path index", executor.curPath + " " + executor.curPose);
            log.showAndRecord("path length", executor.paths.size() + " " + executor.paths.get(executor.curPath).size());
            log.showAndRecord("Curpos", Arrays.toString(bot.odometry.curPos));
            log.showAndRecord("Target", executor.paths.get(executor.curPath).get(executor.paths.get(executor.curPath).size() - 1));
            update(true);
        }
//        while (opModeIsActive()) { bot.tankDrive.move(0, 0.5); update(true); }
//        bot.tankDrive.move(0, 0);

        end();
    }
}
