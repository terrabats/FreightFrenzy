package robotparts.electronics;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class PMotor extends Electronic{
    // TODO FIX
    // Fix this

    private final DcMotor motor;
    private final DcMotorSimple.Direction direction;
    private final DcMotor.ZeroPowerBehavior zeroPowerBehavior;

    public PMotor(DcMotor m, DcMotor.Direction dir, DcMotor.ZeroPowerBehavior zpb, DcMotor.RunMode mode){
        motor = m;
        direction = dir;
        zeroPowerBehavior = zpb;

        motor.setDirection(direction);
        motor.setZeroPowerBehavior(zeroPowerBehavior);
        motor.setMode(mode);

        motor.setPower(0);
    }

    public void setPower(double p){
        if(isFreeToUse()){ motor.setPower(p); }
    }

    public void setPowerRF(double p){
        motor.setPower(p);
    }

    public void setMode(DcMotor.RunMode mode){
        motor.setMode(mode);
    }
    public void setTargetPosition(int targetPosition){
        motor.setTargetPosition(targetPosition);
    }
    public boolean isBusy(){
        return motor.isBusy();
    }
}
