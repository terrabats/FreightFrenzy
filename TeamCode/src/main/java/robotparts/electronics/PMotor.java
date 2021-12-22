package robotparts.electronics;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Base64;

import global.Constants;
import robotparts.Electronic;

public class PMotor extends Electronic {

    private final DcMotor motor;
    private final DcMotorSimple.Direction direction;
    private final DcMotor.ZeroPowerBehavior zeroPowerBehavior;
    private final IEncoder motorEncoder;

    public PMotor(DcMotor m, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        motor = m;
        direction = dir;
        zeroPowerBehavior = zpb;

        motor.setDirection(direction);
        motor.setZeroPowerBehavior(zeroPowerBehavior);
        motor.setMode(mode);

        motor.setPower(0);

        motorEncoder = new IEncoder(motor, IEncoder.Type.MOTOR);

        motorEncoder.reset();
    }

    public void setPower(double p){ if(access.isAllowed()){ motor.setPower(p); } }
    public void setPosition(double pos){
        if(access.isAllowed()){
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition((int)(pos));
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
    public boolean hasReachedPosition(){
        return !motor.isBusy();
    }

    public double getPosition(){
        return motorEncoder.getPos();
    }

    public void stopAndReset(){
        setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void resetPosition(){
        motorEncoder.reset();
    }
}
