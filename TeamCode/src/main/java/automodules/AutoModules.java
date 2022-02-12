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
        return new StageList(bot.lift, bot.turret).define(
            bot.lift.liftEncoder(1, 48),
            bot.turret.turretEncoderTarget(1)
        );
    }

    public StageList ForwardTele() {
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



    public StageList ResetRiseAuto = new StageList(bot.lift).define(
            bot.turret.turretEncoder(1, 0),
            RobotPart.pause(1),
            bot.lift.liftTime(-0.2, 3)
    );

    public StageList IntakeRiseAuto = new StageList(bot.lift, bot.intake, bot.outtake).define(
            bot.outtake.outtakeReset(0.05),
            bot.lift.liftEncoder(0.8, 0),
            bot.intake.intakeTime(1,0.5),
            bot.mecanumDrive.moveTime(0.5, -0.4, 0.0, 0.0),
            bot.outtake.outtakeLockAndIntake(0.05),
            bot.lift.liftEncoderAndIntake(0.8, 115)
    );







    public StageList IntakeRiseTele = new StageList(bot.lift, bot.intake, bot.outtake).define(
            bot.outtake.outtakeReset(0.05),
            bot.lift.liftEncoder(0.8, 0),
            bot.intake.intakeTime(1,1.5),
            bot.outtake.outtakeLockAndIntake(0.05),
            bot.lift.liftEncoderAndIntake(0.8, 115)
    );
    public StageList ForwardRiseTele = new StageList(bot.lift, bot.outtake).define(
            bot.lift.liftEncoderAndIntake(0.8, 150),
            bot.outtake.outtakeDrop(0.5),
            bot.lift.liftEncoder(0.8, 0)
    );

    public StageList DuckRiseTele = new StageList(bot.carousel).define(
            bot.carousel.spinOneDuck(2,0.4,0.7)
    );
}
