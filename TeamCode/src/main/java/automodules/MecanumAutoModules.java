package automodules;

import static global.General.*;

public class MecanumAutoModules {

    /**
     * Low-Usage StageLists – Usually not going to be used
     */
    public static StageList LiftLift = new StageList(bot.lift.liftEncoder(0.2, 20));
    public static StageList ResetLift = new StageList(bot.lift.liftEncoder(0.2, 0));

    public static StageList OuttakeAlliance = bot.outtake.moveForAlliance();
    public static StageList OuttakeShared = bot.outtake.moveForShared();
    public static StageList ResetOuttake = bot.outtake.reset();

    /**
     * High-Usage StageLists – Will be used everywhere
     * Many Combine Basic StageLists
     */
    public static StageList SetUpForAllianceShippingHub = new StageList().add(LiftLift, OuttakeAlliance);
    public static StageList SetUpForSharedShippingHub = new StageList().add(LiftLift, OuttakeShared);
    public static StageList Release = new StageList().add(bot.outtake.release());

    public static StageList ResetLiftAndOuttake = new StageList().add(ResetOuttake, ResetLift);

    public static StageList SpinCarousel = new StageList(bot.carousel.spin(3));

    public static StageList IntakeUntilFreight = new StageList(bot.intake.intakeUntilFreight());
    public static StageList IntakeAndMoveForwardUntilFreight = new StageList(bot.intake.intakeAndMoveForwardUntilFreight());

}
