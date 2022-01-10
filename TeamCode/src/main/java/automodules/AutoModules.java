package automodules;

import static global.General.*;

import elements.GameElement;

public class AutoModules{
    /**
     * Define automodules here as a stage list
     */
    public StageList Intake = new StageList(
        bot.lift.liftEncoder(0.4, 0),
        bot.intake.intakeUntilFreight(1),
        bot.outtake.outtakeLock(0.5),
        bot.lift.liftEncoder(0.8, 15)
    );
    public StageList Backward = new StageList(
        bot.lift.liftEncoder(0.8, 55),
        bot.turret.turretEncoderTarget(0.6)
    );
    public StageList Forward = new StageList(
        bot.outtake.outtakeDrop(1),
        bot.turret.turretEncoder(0.8, 0),
        bot.lift.liftEncoder(0.4, 10),
        bot.outtake.outtakeReset(0.7)
    );



    public StageList SpinCarousel = new StageList(
        bot.carousel.spin(3)
    );
}
