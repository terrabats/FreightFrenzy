package robotparts.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import robotparts.RobotPart;
import robotparts.electronics.CServo;

public class Carousel extends RobotPart {
    /**
     * Carousel left and right (cl and cr respectively)
     */
    public CServo cl;
    public CServo cr;

    /**
     * Init method creates the cservos (or continuous servos)
     */
    @Override
    public void init() {
        cl = createCServo("cl", CRServo.Direction.REVERSE);
        cr = createCServo("cr", CRServo.Direction.FORWARD);
    }

    /**
     * Move the carosels at the given power
     * NOTE: Postive should be so that the right carosel spins clockwise
     * @param p
     */
    public void move(double p){
        cl.setPower(p);
        cr.setPower(p);
    }
}
