package robotparts.electronics;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import robotparts.Electronic;

public class CServo extends Electronic {
    private final CRServo crservo;
    private final DcMotorSimple.Direction direction;

    public CServo(CRServo crs, DcMotorSimple.Direction dir){
        crservo = crs;
        direction = dir;
        crservo.setDirection(direction);
        crservo.setPower(0);
    }


    public void setPower(double power){
        if(access.isAllowed()){ crservo.setPower(power); }
    }

    public DcMotorSimple.Direction getDirection(){
        return direction;
    }
}
