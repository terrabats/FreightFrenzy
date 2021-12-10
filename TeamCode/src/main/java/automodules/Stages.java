package automodules;

import automodules.stage.Main;
import automodules.stage.Stage;
import elements.Ball;
import elements.GameElement;

import static global.General.modules;

public class Stages {
    public Stage intakeUntilFreight(double power){return new Stage(
            modules.mainIntake(power),
            modules.exitFreight(),
            modules.stopIntake()
    );}

    public Stage liftTime(double power, double time){return new Stage(
            modules.mainLift(power),
            modules.exitTime(time),
            modules.stopLift()
    );}
    public Stage liftEncoder(double power, double height){return new Stage(
            modules.initialLiftSetTarget(height),
            modules.mainLift(power),
            modules.exitLiftReachedTarget()
    );}
    public Stage liftDown(double power){return new Stage(
            modules.mainLift(power),
            modules.exitLiftDown(),
            modules.stopLift()
    );}

    public Stage outtakeLock(GameElement freightType, double time){
        Main lock = null;
        if(freightType.equals(GameElement.CUBE)){lock = modules.mainLockIfCube();}
        else if(freightType.equals(GameElement.BALL)){lock = modules.mainLockIfBall();}
        return new Stage(
                lock,
                modules.exitTime(time)
        );
    }
}
