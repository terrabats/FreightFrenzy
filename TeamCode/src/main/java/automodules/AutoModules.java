package automodules;

import java.util.ArrayList;
import static global.General.*;

import automodules.stage.Stage;
import elements.GameElement;
import global.Constants;
import robot.TerraBot;

public class AutoModules{
    public StageList Intake = new StageList(
        bot.lift.liftEncoder(0.4, 0),
        bot.intake.intakeUntilFreight(1),
        bot.outtake.outtakeLock(GameElement.BALL, 0.5),
        bot.lift.liftEncoder(0.8, 10),
        bot.outtake.outtakeLock(GameElement.CUBE, 0.5)
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
    public StageList test = new StageList(
        bot.lift.liftEncoder(0.6, 30)
    );

}
