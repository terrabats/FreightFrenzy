package automodules;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stop;

import static global.General.*;

public class Modules {
    //Initials


    //Mains
    public Main mainTankDrive(double forward, double strafe){
        return new Main(() -> bot.tankDrive.move(forward, strafe));
    }

    //Exits
    public Exit exitTime(double s){
        return new Exit(() -> bot.rfsHandler.timer.seconds() > s);
    }

    //Stops
    public Stop stopTankDrive(){
        return new Stop(() -> bot.tankDrive.move(0,0));
    }
}
