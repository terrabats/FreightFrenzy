package automodules;

import static global.General.*;

import elements.GameElement;

public class AutoModules{
    /**
     * Define automodules here as a stage list
     */
    public StageList Intake = new StageList(bot.lift, bot.intake, bot.outtake).define(
        bot.lift.liftEncoder(0.4, 0),
        bot.intake.intakeUntilFreight(1),
        bot.outtake.outtakeLock(1),
        bot.lift.liftEncoder(1, 15)
    );

    public StageList Backward = new StageList(bot.lift, bot.turret).define(
        bot.lift.liftEncoder(1, 48),
        bot.turret.turretEncoderTarget(1)
    );

    public StageList Forward = new StageList(bot.outtake, bot.turret, bot.lift).define(
        bot.outtake.outtakeDrop(0.6),
        bot.turret.turretEncoder(1, 0),
        bot.lift.liftEncoder(0.4, 10),
        bot.outtake.outtakeReset(0.7)
    );

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
        return MoveTime(0, 0.5, time);
    }

    public StageList MoveCCWTime(double time) {
        return MoveTime(0, -0.5, time);
    }

    public StageList MoveTime(double forward, double turn, double time) {
        return new StageList(bot.tankDrive).define(
            bot.tankDrive.moveTime(forward, turn, time)
        );
    }
}
