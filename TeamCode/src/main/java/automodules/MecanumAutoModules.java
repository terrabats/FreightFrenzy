package automodules;

import static global.General.*;

public class MecanumAutoModules {

    /**
     * Basic StageLists – Usually not going to be used
     */
    public StageList LiftLift = new StageList(bot.mecanumLift.liftEncoder(0.2, 20));
    public StageList ResetLift = new StageList(bot.mecanumLift.liftEncoder(0.2, 0));

    public StageList OuttakeAlliance = bot.mecanumOuttake.moveForAlliance();
    public StageList OuttakeShared = bot.mecanumOuttake.moveForShared();
    public StageList ResetOuttake = bot.mecanumOuttake.reset();

    /**
     * Advanced StageLists – Will be used everywhere
     * Combines Basic StageLists
     */
    public StageList SetUpForAllianceShippingHub = new StageList().add(LiftLift, OuttakeAlliance);
    public StageList SetUpForSharedShippingHub = new StageList().add(LiftLift, OuttakeShared);

    public StageList ResetLiftAndOuttake = new StageList().add(ResetOuttake, ResetLift);

    public StageList SpinCarousel = new StageList(bot.mecanumCarousel.spin(3));

    public StageList IntakeUntilFreight = new StageList(bot.mecanumIntake.intakeUntilFreight());
    public StageList IntakeAndMoveForwardUntilFreight = new StageList(bot.mecanumIntake.intakeAndMoveForwardUntilFreight());

}
