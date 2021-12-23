package automodules;

import automodules.stage.Main;
import automodules.stage.Stage;
import elements.Ball;
import elements.GameElement;

import static global.General.bot;
import static global.General.modules;

public class Stages {
    // TODO FIX
    // Defined in robotparts
    //


    public Stage intakeUntilFreight(double power){return new Stage(
            bot.intake.usePart(),
            bot.intake.main(power),
            bot.colorSensors.exitFreight(),
            bot.intake.stop(),
            bot.intake.returnPart()
    );}

    public Stage liftTime(double power, double time){return new Stage(
//            modules.usePart(bot.lift),
//            modules.mainLift(power),
//            modules.exitTime(time),
//            modules.stopLift(),
//            modules.returnPart(bot.lift)
    );}
    public Stage liftEncoder(double power, double height){return new Stage(
//            modules.usePart(bot.lift),
//            modules.initialLiftSetTarget(height),
//            modules.mainLift(power),
//            modules.exitLiftReachedTarget(),
//            modules.stopLiftEncoder(),
//            modules.returnPart(bot.lift)
    );}
    public Stage liftDown(double power){return new Stage(
//            modules.usePart(bot.lift),
//            modules.mainLift(power),
//            modules.exitLiftDown(),
//            modules.stopLift(),
//            modules.returnPart(bot.lift)
    );}

    public Stage turretEncoder(double power, double angle){return new Stage(
//            modules.usePart(bot.turret),
//            modules.initialTurretSetTarget(angle),
//            modules.mainTurret(power),
//            modules.exitTurretReachedTarget(),
//            modules.stopTurretEncoder(),
//            modules.returnPart(bot.turret)
    );}
    public Stage turretEncoderTarget(double power){return new Stage(
//            modules.usePart(bot.turret),
//            modules.initialTurretSetFieldSideTarget(),
//            modules.mainTurret(power),
//            modules.exitTurretReachedTarget(),
//            modules.stopTurretEncoder(),
//            modules.returnPart(bot.turret)
    );}

    public Stage outtakeLock(GameElement freightType, double time){
//        Main lock = null;
//        if(freightType.equals(GameElement.CUBE)){lock = modules.mainOuttakeLockIfCube();}
//        else if(freightType.equals(GameElement.BALL)){lock = modules.mainOuttakeLockIfBall();}
        return new Stage(
//                modules.usePart(bot.outtake),
//                lock,
//                modules.exitTime(time),
//                modules.returnPart(bot.outtake)
        );
    }

    public Stage outtakeDrop(double time){ return new Stage(
//            modules.usePart(bot.outtake),
//            modules.mainOuttakeDrop(),
//            modules.exitTime(time),
//            modules.returnPart(bot.outtake)
    );}

    public Stage outtakeReset(double time){ return new Stage(
//            modules.usePart(bot.outtake),
//            modules.mainOuttakeReset(),
//            modules.exitTime(time),
//            modules.returnPart(bot.outtake)
    );}


}
