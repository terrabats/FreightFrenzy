package automodules;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Pause;
import automodules.stage.Stage;
import automodules.stage.Stop;
import robotparts.RobotPart;

import static global.General.*;

public class Modules {
    //Initials

    public Initial initialLiftSetTarget(double height){return new Initial(() -> bot.lift.setTarget(height));}
    public Initial initialTurretSetTarget(double angle){return new Initial(() -> bot.turret.setTarget(angle));}
    public Initial initialTurretSetFieldSideTarget(){return new Initial(() -> bot.turret.setTarget(bot.turret.getTurretTargetPos()));}

    //Mains
    public Main mainTankDrive(double forward, double strafe){return new Main(() -> bot.tankDrive.move(forward, strafe));}
    public Main mainLift(double power){return new Main(() -> bot.lift.move(power));}
    public Main mainIntake(double power){return new Main(()-> bot.intake.move(power));}
    public Main mainTurret(double power){return new Main(()-> bot.turret.move(power));}
    public Main mainOuttakeLockIfBall(){return new Main(() -> {if (bot.color.isBall()) {bot.outtake.lockBall();}});}
    public Main mainOuttakeLockIfCube(){return new Main(() -> {if (bot.color.isCube()) {bot.outtake.lockCube();}});}
    public Main mainOuttakeDrop(){return new Main(()->bot.outtake.open());}
    public Main mainOuttakeReset(){return new Main(()->bot.outtake.start());}

    //Exits
    public Exit exitTime(double s){return new Exit(() -> bot.rfsHandler.timer.seconds() > s);}
    public Exit exitLiftDown(){return new Exit(() -> bot.touch.isOuttakePressingTouchSensor() || bot.rfsHandler.timer.seconds()>1);}
    public Exit exitLiftReachedTarget(){return new Exit(() -> bot.lift.hasReachedTarget());}
    public Exit exitTurretReachedTarget(){return new Exit(()->bot.turret.hasReachedTarget());}
    public Exit exitBall(){return new Exit(()->bot.color.isBall());}
    public Exit exitCube(){return new Exit(()->bot.color.isCube());}
    public Exit exitFreight(){return new Exit(()->bot.color.isFreight());}
    public Exit exitNever(){return new Exit(() -> false);}
    public Exit exitAlways(){return new Exit(() -> true);}

    //Stops
    public Stop stopTankDrive(){
        return new Stop(() -> bot.tankDrive.move(0,0));
    }
    public Stop stopLift(){return new Stop(() -> bot.lift.move(0));}
    public Stop stopLiftEncoder(){return new Stop(() -> bot.lift.stopAndResetMode());}
    public Stop stopIntake(){return new Stop(() -> bot.intake.move(0));}
    public Stop stopTurret(){return new Stop(()-> bot.turret.move(0));}
    public Stop stopTurretEncoder(){return new Stop(() -> bot.turret.stopAndResetMode());}


    //Pausing
    public Main pause() {return new Main(() -> bot.rfsHandler.addPause());}
    public Main resume(){return new Main(() -> bot.rfsHandler.resume());}

    //Possession
    public Initial usePart(RobotPart part){return new Initial(part::deactivate);}
    public Stop returnPart(RobotPart part){return new Stop(part::activate);}


    public Main setSlowMode(boolean val){return new Main(() -> bot.slowMode = val);}

}
