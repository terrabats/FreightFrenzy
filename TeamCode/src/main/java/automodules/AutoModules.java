package automodules;

import static global.General.*;

import automodules.stage.Exit;
import automodules.stage.Stage;
import elements.GameElement;
import robotparts.RobotPart;

public class AutoModules{
    /**
     * Define automodules here as a stage list
     */
    // TODO FIX
            // DOenst work properly
    public StageList IntakeAuto = new StageList(bot.lift, bot.intake, bot.outtake).define(
        bot.lift.liftEncoder(0.4, 0),
        bot.intake.intakeUntilFreight(1),
        bot.outtake.outtakeLock(1)
    );

    public StageList IntakeTele = new StageList(bot.turret, bot.lift, bot.intake, bot.outtake).define(
        bot.outtake.outtakeReset(0.05),
        bot.turret.turretEncoder(1, 0),
        bot.lift.liftEncoder(0.4, 0),
        bot.intake.intakeUntilFreightLiftDown(1),
        bot.outtake.outtakeLockAndIntake(1),
        bot.intake.intakeTime(-1, 0.8)
    );

    public StageList Forward = new StageList(bot.outtake, bot.turret, bot.lift).define(
        bot.outtake.outtakeDrop(0.6),
        bot.turret.turretEncoder(1, 0),
        bot.lift.liftEncoder(0.4, 10),
        bot.outtake.outtakeReset(0.7)
    );

    public StageList Backward = new StageList(bot.lift, bot.turret).define(
        bot.lift.liftEncoder(1, 48),
        bot.turret.turretEncoderTarget(1)
    );

    public StageList BackwardTele() {
//        if (bot.turret.freightPlaced == 0) {
//            return new StageList(bot.lift, bot.turret).define(
//                bot.lift.liftEncoder(1, 48),
//                bot.turret.turretEncoderTarget(1),
//                bot.lift.liftEncoder(0.4, 20)
//            );
//        }
        return new StageList(bot.lift, bot.turret).define(
            bot.lift.liftEncoder(1, 48),
            bot.turret.turretEncoderTarget(1)
        );
    }

    public StageList ForwardTele() {
//        if (bot.turret.freightPlaced == 1) {
//            return new StageList(bot.outtake, bot.turret, bot.lift).define(
//                bot.outtake.outtakeDrop(0.6),
//                bot.lift.liftEncoder(1, 48),
//                bot.turret.turretEncoder(1, 0),
//                bot.lift.liftEncoder(0.4, 10),
//                bot.outtake.outtakeReset(0.7)
//            );
//        }
        return new StageList(bot.outtake, bot.turret, bot.lift).define(
            bot.outtake.outtakeDrop(0.6),
            bot.turret.turretEncoder(1, 0),
            bot.lift.liftEncoder(0.4, 10),
            bot.outtake.outtakeReset(0.7)
        );
    }

    public StageList SpinCarousel = new StageList(bot.carousel).define(
        bot.carousel.spin(3)
    );

    public StageList ResetTurretAndLift = new StageList(bot.lift).define(
        bot.turret.turretEncoder(1, 0),
        RobotPart.pause(1),
        bot.lift.liftTime(-0.2, 3)
    );

    public StageList MoveForwardTime(double time) {
        return MoveTime(0.5, 0, time);
    }

    public StageList MoveBackwardTime(double time) {
        return MoveTime(-0.5, 0, time);
    }

    public StageList MoveCWTime(double time) {
        return MoveTime(0, 0.4, time);
    }

    public StageList MoveCCWTime(double time) {
        return MoveTime(0, -0.5, time);
    }

    public StageList MoveTime(double forward, double turn, double time) {
        return new StageList(bot.tankDrive).define(
            bot.tankDrive.moveTime(forward, turn, time)
        );
    }

    public StageList LiftOdometry = new StageList(bot.tankDrive).define(
        bot.tankDrive.liftOdo()
    );

    public StageList pause(double time) {
        return new StageList().define(
            RobotPart.pause(time)
        );
    }
}
