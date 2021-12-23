package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stop;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.IEncoder;
import robotparts.electronics.PMotor;

import static global.General.bot;

public class Lift extends RobotPart {
    private PMotor li;
    private IEncoder liEnc;

    @Override
    public void init() {
        li = createPMotor("li", DcMotorSimple.Direction.FORWARD);
        liEnc = createEncoder("li", "liEnc", IEncoder.Type.MOTOR);
        resetEncoder();
    }

    public void move(double p){
        li.setPower(p);
    }

    public double getLiftPos(){
        return liEnc.getPos();
    }

    public void resetEncoder(){liEnc.reset();}

    public void setTarget(double h){
        li.setPosition(h*Constants.LIFT_CM_TO_TICKS);
    }

    public void stopAndResetMode() {
        li.stopAndReset();
    }

    public boolean hasReachedTarget(){
        return li.hasReachedPosition();
    }


    public Initial initialSetTarget(double height){return new Initial(() -> setTarget(height));}
    public Main main(double power){return new Main(() -> move(power));}

    public Exit exitDown(){return new Exit(() -> bot.touchSensor.isOuttakePressingTouchSensor());}

    public Exit exitReachedTarget(){return new Exit(this::hasReachedTarget);}

    public Stop stopLift(){return new Stop(() -> move(0));}

    public Stop stopLiftEncoder(){return new Stop(this::stopAndResetMode);}





}
