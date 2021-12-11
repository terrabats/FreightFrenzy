package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.R;

import global.Constants;
import robotparts.RobotPart;
import robotparts.custom.Encoder;

public class Lift extends RobotPart {
    private DcMotor li;
    private Encoder liEnc;

    @Override
    public void init() {
        li = createMotor("li", DcMotorSimple.Direction.FORWARD);
        liEnc = createEncoder("li", "liEnc", Encoder.Type.MOTOR);
        resetEncoder();
    }
    @Override
    public void move(double p){
        li.setPower(p);
    }
    @Override
    public void moveTele(double p) {super.moveTele(p);}

    public double getLiftPos(){
        return liEnc.getPos();
    }

    public void resetEncoder(){liEnc.reset();}

    public void setTarget(double h){
        //resetEncoder();
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
