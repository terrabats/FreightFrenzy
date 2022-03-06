package automodules;

import static global.General.bot;

public class AutoModules {

    public StageList DuckTele = new StageList(bot.carousel.spinOneDuck(2,0.4,0.7));

    public StageList UpLift = new StageList(bot.lift.liftEncoder(0.2, 20));
    public StageList ResetLift = new StageList(bot.lift.liftEncoder(-0.2, 0));

    public static StageList OuttakeAlliance = bot.outtake.moveForAlliance();
    public static StageList OuttakeShared = bot.outtake.moveForShared();
    public static StageList ResetOuttake = bot.outtake.moveForReset();


    public StageList SetUpForAllianceShippingHub = new StageList().add(UpLift, OuttakeAlliance);
    public StageList SetUpForSharedShippingHub = new StageList().add(UpLift, OuttakeShared);
    public StageList Release = new StageList().add(bot.outtake.moveForDrop());

    public StageList ResetLiftAndOuttake = new StageList().add(ResetOuttake, ResetLift);

    public StageList IntakeUntilFreight = new StageList(bot.intake.intakeUntilFreight());

//    public StageList IntakeAndMoveForwardUntilFreight = new StageList(bot.intake.intakeAndMoveForwardUntilFreight());
}
