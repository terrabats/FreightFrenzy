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
        bot.outtake.outtakeLock(0.8),
        bot.lift.liftEncoder(0.8, 15)
    );

    public StageList Backward = new StageList(bot.lift, bot.turret).define(
        bot.lift.liftEncoder(0.8, 48),
        bot.turret.turretEncoderTarget(0.6)
    );

    public StageList Forward = new StageList(bot.outtake, bot.turret, bot.lift).define(
        bot.outtake.outtakeDrop(0.6),
        bot.turret.turretEncoder(0.8, 0),
        bot.lift.liftEncoder(0.4, 10),
        bot.outtake.outtakeReset(0.7)
    );

    public StageList SpinCarousel = new StageList(bot.carousel).define(
        bot.carousel.spin(3)
    );
}
