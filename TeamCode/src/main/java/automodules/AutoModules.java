package automodules;

import teleutil.Modes.OuttakeMode;
import util.condition.DecisionList;

import static global.General.automodules;
import static global.General.bot;
import static teleutil.Modes.OuttakeMode.ALLIANCE;
import static teleutil.Modes.OuttakeMode.SHARED;

public class AutoModules {

    public StageList OneDuck = new StageList(bot.carousel.spinOneDuck(2,0.4,0.7));

    public StageList LiftUpAlliance = new StageList(bot.lift.liftEncoderUp(0.5, 30));
    public StageList LiftUpShared = new StageList(bot.lift.liftEncoderUp(0.5, 20));
    public StageList LiftReset = new StageList(bot.lift.liftEncoderDown(-0.2, 0));

    public StageList OuttakeAlliance = bot.outtake.moveForAlliance();
    public StageList OuttakeShared = bot.outtake.moveForShared();
    public StageList ResetOuttake = bot.outtake.moveForReset();

    public StageList SetUpForAllianceShippingHub = new StageList().add(LiftUpAlliance, OuttakeAlliance);
    public StageList SetUpForSharedShippingHub = new StageList().add(LiftUpShared, OuttakeShared);

    public DecisionList SetUpForBoth = new DecisionList(bot.outtake::getOuttakeMode)
            .addOption(ALLIANCE, () -> bot.addAutoModule(SetUpForAllianceShippingHub))
            .addOption(SHARED, () -> bot.addAutoModule(SetUpForSharedShippingHub));

    public StageList ResetLiftAndOuttake = new StageList().add(ResetOuttake, LiftReset);
    public StageList IntakeUntilFreight = new StageList(bot.intake.intakeUntilFreight());

//    public StageList IntakeAndMoveForwardUntilFreight = new StageList(bot.intake.intakeAndMoveForwardUntilFreight());
}
