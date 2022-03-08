package automodules;

import util.condition.DecisionList;

import static global.General.bot;
import static teleutil.Modes.OuttakeMode.ALLIANCE;
import static teleutil.Modes.OuttakeMode.SHARED;

public class AutoModules {

    public StageList OneDuck = new StageList(bot.carousel.spinOneDuck(2,0.4,0.7));

    public StageList LiftUpAlliance = new StageList(bot.lift.liftEncoderUp(0.5, 40));
    public StageList LiftUpShared = new StageList(bot.lift.liftEncoderUp(0.5, 20));
    public StageList LiftReset = new StageList(bot.lift.liftEncoderDown(-0.2, 0));

    public StageList OuttakeHorizontal = new StageList(bot.outtake.stageTurnToHorizontalFast());
    public StageList OuttakeAlliance = new StageList(bot.outtake.stageCenterTurretFast());
    public StageList OuttakeShared = bot.outtake.moveForShared();
    public StageList OuttakeReset = bot.outtake.moveForReset();
    public StageList OuttakeDrop = bot.outtake.moveForDrop();
    public StageList OuttakeLock = new StageList(bot.outtake.stageLock());
    public StageList IntakeOutAndLock = new StageList(bot.outtake.stageLockFast(), bot.intake.intakeOutAndLock());
    public StageList IntakeUntilFreight = new StageList(bot.intake.intakeUntilFreight());


    public StageList SetUpForAllianceShippingHub = new StageList().add(OuttakeHorizontal, LiftUpAlliance, OuttakeAlliance);
    public StageList SetUpForSharedShippingHub = new StageList().add(OuttakeHorizontal, LiftUpShared, OuttakeShared);

    public DecisionList SetUpForBoth = new DecisionList(bot.outtake::getOuttakeMode)
            .addOption(ALLIANCE, () -> bot.addAutoModule(SetUpForAllianceShippingHub))
            .addOption(SHARED, () -> bot.addAutoModule(SetUpForSharedShippingHub));

    public StageList ResetLiftAndOuttake = new StageList().add(OuttakeReset, LiftReset);
    public StageList IntakeCombined = new StageList().add(OuttakeDrop, IntakeUntilFreight, IntakeOutAndLock);

//    public StageList IntakeAndMoveForwardUntilFreight = new StageList(bot.intake.intakeAndMoveForwardUntilFreight());
}
