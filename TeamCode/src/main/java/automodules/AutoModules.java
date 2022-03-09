package automodules;

import elements.FieldSide;
import util.condition.DecisionList;

import static global.General.bot;
import static global.General.fieldSide;
import static teleutil.Modes.OuttakeMode.ALLIANCE;
import static teleutil.Modes.OuttakeMode.SHARED;

public class AutoModules {

    // TODO FIX
    // Make automodules for other levels

    public StageList OneDuck = new StageList(bot.carousel.spinOneDuck(2,0.4,0.7));

    public StageList LiftUpAlliance = new StageList(bot.lift.liftEncoderUp(0.6, 42));
    public StageList LiftUpShared = new StageList(bot.lift.liftEncoderUp(0.5, 15));
    public StageList LiftReset = new StageList(bot.lift.liftEncoderDown(-0.3, 0));

    public StageList OuttakeHorizontal = new StageList(bot.outtake.stageTurnToHorizontal(0.15));
    public StageList OuttakeHorizontalFast = new StageList(bot.outtake.stageTurnToHorizontal(0.05));
    public StageList OuttakeAlliance = new StageList(bot.outtake.stageCenterTurret(0.05));
    public StageList OuttakeSharedRight = new StageList(bot.outtake.stageSharedTurretRight(0.5));
    public StageList OuttakeSharedLeft = new StageList(bot.outtake.stageSharedTurretLeft(0.5));
    public StageList OuttakeDrop = new StageList(bot.outtake.stageDrop(0.25));
    public StageList OuttakeDropFast = new StageList(bot.outtake.stageDrop(0.05));
    public StageList OuttakeLock = new StageList(bot.outtake.stageLock(0.25));

    public StageList OuttakeReset = new StageList(
            bot.outtake.stageDrop(0.15),
            bot.outtake.stageCenterTurret(0.25),
            bot.outtake.stageLock(0.05),
            bot.outtake.stageTurnToStart(0.05)
    );

    public StageList IntakeOutAndLock = new StageList(bot.outtake.stageLock(0.05), bot.intake.intakeOutAndLock());
    public StageList IntakeUntilFreight = new StageList(bot.intake.intakeUntilFreight());

    public StageList SetUpForAllianceShippingHub = new StageList().add(OuttakeHorizontalFast, LiftUpAlliance, OuttakeAlliance);
    public StageList SetUpForSharedShippingHubRight = new StageList().add(OuttakeHorizontal, LiftUpShared, OuttakeSharedRight);
    public StageList SetUpForSharedShippingHubLeft = new StageList().add(OuttakeHorizontal, LiftUpShared, OuttakeSharedLeft);

    public DecisionList SetUpForSharedShippingHubBoth = new DecisionList(() -> fieldSide)
            .addOption(FieldSide.BLUE, () -> bot.addAutoModule(SetUpForSharedShippingHubLeft))
            .addOption(FieldSide.RED, () -> bot.addAutoModule(SetUpForSharedShippingHubRight));

    public DecisionList SetUpForBoth = new DecisionList(bot.outtake::getOuttakeMode)
            .addOption(ALLIANCE, () -> bot.addAutoModule(SetUpForAllianceShippingHub))
            .addOption(SHARED, () -> SetUpForSharedShippingHubBoth.check());

    public StageList ResetLiftAndOuttake = new StageList().add(OuttakeReset, LiftReset);
    public StageList IntakeCombined = new StageList().add(OuttakeDropFast, IntakeUntilFreight, IntakeOutAndLock);
}
