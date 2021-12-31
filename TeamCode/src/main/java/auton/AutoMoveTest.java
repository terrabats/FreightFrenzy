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
        executor.addSetpoint(10, 10, PI/2, AngleType.RADIANS);
//        executor.addSetpoint(0, 10, 0, AngleType.RADIANS);
        executor.complete();

        waitForStart();

        ready();

        executor.resume();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
//            log.show(executor.reactor.moveForward(executor.));
            log.show(executor.curPath + " " + executor.curPose);
            log.show(executor.paths.size() + " " + executor.paths.get(executor.curPath).size());
//            log.show(executor.paths.get(executor.curPath).get(executor.curPose));
            update(true);
        }

        bot.tankDrive.move(0, 0);

        end();
    }
}
