package robotparts.electronics;

import com.qualcomm.robotcore.hardware.DcMotor;

public class CMotor extends Electronic{
    private final DcMotor motor;

    public CMotor(DcMotor m){
        motor = m;
    }

    public void setPower(double p){
        if(isFreeToUse()){ motor.setPower(p); }
    }

    public void setPowerRF(double p){
        motor.setPower(p);
    }
}
