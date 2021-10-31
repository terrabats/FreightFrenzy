package robotparts;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Carousel extends RobotPart{
    public CRServo carouselLeft;
    public CRServo carouselRight;

    @Override
    public void init() {
        carouselLeft = createCRServo("carouselLeft", CRServo.Direction.FORWARD);
        carouselRight = createCRServo("carouselRight", CRServo.Direction.REVERSE);
        //
    }
    public void move()
    {
        carouselLeft.setPower(1);
        carouselRight.setPower(1);
    }
    public void stop()
    {
        carouselLeft.setPower(0);
        carouselRight.setPower(0);
    }
}
