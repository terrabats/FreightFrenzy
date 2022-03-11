package teleutil.independent;

import teleutil.independent.Independent;

import static global.General.automodules;
import static global.General.bot;

public class Independents {

    public Independent MoveForAllianceBackward = new Independent(i -> {
        bot.odometry.reset();
        i.addWaypoint(-5,-40,5);
        i.addDecision(automodules.SetUpForBoth);
        i.addWaypoint(-5,-50,5);
        i.addWaypoint(25,-50,0);
        i.addSetpoint(40, -60, -45);
    });

    public Independent MoveForAllianceForward = new Independent(i -> {
        i.addConcurrentAutoModule(automodules.ResetLiftAndOuttake);
        i.addPause(0.6);
        i.addWaypoint(25,-50,0);
        i.addWaypoint(-5,-50,-5);
        i.addWaypoint(-5,0,-5);
        i.addConcurrentAutoModule(automodules.IntakeCombined);
    });

    public Independent MoveForSharedBackward = new Independent(i -> {
        bot.odometry.reset();
        i.addWaypoint(5,-30,-5);
        i.addDecision(automodules.SetUpForBoth);
        i.addWaypoint(5,-40,-5);
        i.addWaypoint(-15,-40,0);
        i.addSetpoint(-30, -50, 30);
    });

    public Independent MoveForSharedForward = new Independent(i -> {
        i.addConcurrentAutoModule(automodules.ResetLiftAndOuttake);
        i.addPause(0.6);
        i.addWaypoint(-15,-40,0);
        i.addWaypoint(5,-40,5);
        i.addWaypoint(5,0,5);
        i.addConcurrentAutoModule(automodules.IntakeCombined);
    });

}
