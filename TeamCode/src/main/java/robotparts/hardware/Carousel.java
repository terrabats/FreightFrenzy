package robotparts.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import robotparts.RobotPart;
import robotparts.electronics.CServo;

public class Carousel extends RobotPart {
    public CServo cl;
    public CServo cr;

    @Override
    public void init() {
        cl = createCServo("cl", CRServo.Direction.REVERSE);
        cr = createCServo("cr", CRServo.Direction.FORWARD);
    }

    public void move(double p){
        cl.setPower(p);
        cr.setPower(p);
    }
}
