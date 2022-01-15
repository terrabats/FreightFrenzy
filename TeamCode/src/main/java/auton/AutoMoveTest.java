package auton;

import autoutil.executors.MovementExecutor;
import elements.FieldSide;
import geometry.circles.AngleType;

import static java.lang.Math.*;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import java.util.Arrays;

@Disabled
@Autonomous(name="AutoMoveTest")
public class AutoMoveTest extends Auto {

    // TODO FIX
    // Turn this into a Unit Test

    @Override
    public void initAuto() {
        activate(FieldSide.BLUE);
    }

    @Override
    public void runAuto() {

        MovementExecutor movementExecutor = new MovementExecutor(0, 0, PI/2, AngleType.RADIANS);
        movementExecutor.addSetpoint(60, 60, PI/2, AngleType.RADIANS);
//        executor.addSetpoint(0, 10, 0, AngleType.RADIANS);
        movementExecutor.complete();

        waitForStart();

        ready();

        movementExecutor.resumeMove();

        while (opModeIsActive() && !movementExecutor.finishedMove()) {
            movementExecutor.updateMovement();
//            log.show(executor.reactor.moveForward(executor.));
            log.showAndRecord("cur path index", movementExecutor.curPath + " " + movementExecutor.curPose);
            log.showAndRecord("path length", movementExecutor.paths.size() + " " + movementExecutor.paths.get(movementExecutor.curPath).size());
            log.showAndRecord("Curpos", Arrays.toString(bot.odometry.curPos));
            log.showAndRecord("Target", movementExecutor.paths.get(movementExecutor.curPath).get(movementExecutor.paths.get(movementExecutor.curPath).size() - 1));
            update(true);
        }

//        while (opModeIsActive()) { bot.tankDrive.move(0, 0.5); update(true); }
//        bot.tankDrive.move(0, 0);
    }
}
