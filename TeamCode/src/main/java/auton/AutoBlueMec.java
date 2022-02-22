package auton;

import autoutil.executors.MecanumExecutorArcs;
import elements.FieldSide;
import geometry.circles.AngleType;

import static java.lang.Math.*;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="TerraAutoBlueMec")
public class AutoBlueMec extends CompleteAuto {
    @Override
    public void initAuto() {
        activate(FieldSide.BLUE);
        bot.outtake.lockCube();
    }

    @Override
    public void defineExecutorAndAddPoints() {
        executor = new MecanumExecutorArcs();
        addExecutorFuncs(
                setPoint(70, 30, PI/2),
                unsyncedRF(autoModules.SpinCarousel),
                syncedRF(autoModules.IntakeAuto),
                setPoint(-50, 43, -PI/2),
                setPoint(-65, 43, -PI/2),
                unsyncedRF(autoModules.Backward),
                custom(() -> executor.addPause(2)),
                unsyncedRF(autoModules.Forward),
                setPoint(-40, 43, -PI/2),
                setPoint(-70, 15, -PI/2),
                setPoint(-90, 15, -PI/2),
                unsyncedRF(autoModules.LiftOdometry),
                unsyncedRF(autoModules.MoveCWTime(0.5)),
                unsyncedRF(autoModules.MoveForwardTime(2)),
                unsyncedRF(autoModules.ResetTurretAndLift)
        );
//        executor.addSetpoint(70, 30, PI/2, AngleType.RADIANS);
//        executor.addUnsynchronizedRF(autoModules.SpinCarousel);
//        executor.addSynchronizedRF(autoModules.IntakeAuto);
//        executor.addSetpoint(-50, 43, -PI/2, AngleType.RADIANS);
//        executor.addSetpoint(-65, 43, -PI/2, AngleType.RADIANS);
//        executor.addUnsynchronizedRF(autoModules.Backward);
//        executor.addPause(2);
//        executor.addUnsynchronizedRF(autoModules.Forward);
//        executor.addSetpoint(-40, 43, -PI/2, AngleType.RADIANS);
//        executor.addSetpoint(-70, 15, -PI/2, AngleType.RADIANS);
//        executor.addSetpoint(-90, 15, -PI/2, AngleType.RADIANS);
//        executor.addUnsynchronizedRF(autoModules.LiftOdometry);
//        executor.addUnsynchronizedRF(autoModules.MoveCWTime(0.5));
//        executor.addUnsynchronizedRF(autoModules.MoveForwardTime(2));
//        executor.addUnsynchronizedRF(autoModules.ResetTurretAndLift);
    }

    @Override
    public void duringLoop() {
        if (bot.lift.getPower() == 0) bot.lift.move(0);
    }

    @Override
    public void onEnd() {
        bot.mecanumDrive.move(0, 0, 0);
    }
}
