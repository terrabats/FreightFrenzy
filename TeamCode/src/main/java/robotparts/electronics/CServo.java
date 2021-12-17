package robotparts.electronics;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CServo extends Electronic{
    private final CRServo crservo;

    public CServo(CRServo crs){
        crservo = crs;
    }


    public void setPower(double power){
        if(isFreeToUse()){ crservo.setPower(power); }
    }

    public void setPowerRF(double power){
        crservo.setPower(power);
    }
}
