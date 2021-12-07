package robotparts.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import robotparts.RobotPart;

public class Carousel extends RobotPart {
    public CRServo cl;
    public CRServo cr;

    @Override
    public void init() {
        cl = createCRServo("cl", CRServo.Direction.FORWARD);
        cr = createCRServo("cr", CRServo.Direction.REVERSE);
    }

    public void spin(double p)
    {
        cl.setPower(p);
        cr.setPower(p);
    }
}
