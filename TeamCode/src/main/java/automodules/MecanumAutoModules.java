package automodules;

import static global.General.*;

public class MecanumAutoModules {

    /**
     * Low-Usage StageLists – Usually not going to be used
     */
    public static StageList LiftLift = new StageList(bot.mecanumLift.liftEncoder(0.2, 20));
    public static StageList ResetLift = new StageList(bot.mecanumLift.liftEncoder(0.2, 0));

    public static StageList OuttakeAlliance = bot.mecanumOuttake.moveForAlliance();
    public static StageList OuttakeShared = bot.mecanumOuttake.moveForShared();
    public static StageList ResetOuttake = bot.mecanumOuttake.reset();

    /**
     * High-Usage StageLists – Will be used everywhere
     * Many Combine Basic StageLists
     */
    public static StageList SetUpForAllianceShippingHub = new StageList().add(LiftLift, OuttakeAlliance);
    public static StageList SetUpForSharedShippingHub = new StageList().add(LiftLift, OuttakeShared);
    public static StageList Release = new StageList().add(bot.mecanumOuttake.release());

    public static StageList ResetLiftAndOuttake = new StageList().add(ResetOuttake, ResetLift);

    public static StageList SpinCarousel = new StageList(bot.mecanumCarousel.spin(3));

    public static StageList IntakeUntilFreight = new StageList(bot.mecanumIntake.intakeUntilFreight());
    public static StageList IntakeAndMoveForwardUntilFreight = new StageList(bot.mecanumIntake.intakeAndMoveForwardUntilFreight());

}
