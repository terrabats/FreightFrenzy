package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.IEncoder;
import robotparts.electronics.PMotor;

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
        li.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        li.setTargetPosition((int)(h*Constants.LIFT_CM_TO_TICKS));
        li.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stopAndResetMode() {
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        li.setPower(0);
    }

    public void setMode(DcMotor.RunMode mode) {
        li.setMode(mode);
    }

    public boolean hasReachedTarget(){
        return !li.isBusy();
    }
}
