package automodules;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stop;

import static global.General.*;

public class Modules {
    //Initials


    //Mains
    public Main mainTankDrive(double forward, double strafe){return new Main(() -> bot.tankDrive.move(forward, strafe));}
    public Main mainLift(double power){return new Main(() -> bot.lift.move(power));}
    public Main mainIntake(double power){return new Main(()-> bot.intake.spin(power));}

    //Exits
    public Exit exitTime(double s){
        return new Exit(() -> bot.rfsHandler.timer.seconds() > s);
    }
    public Exit exitLiftDown(){return new Exit(() -> bot.touch.isOuttakePressingTouchSensor());}
    public Exit exitBall(){return new Exit(()->bot.color.isBall());}

    //Stops
    public Stop stopTankDrive(){
        return new Stop(() -> bot.tankDrive.move(0,0));
    }
    public Stop stopLife(){return new Stop(() -> bot.lift.move(0));}
}
