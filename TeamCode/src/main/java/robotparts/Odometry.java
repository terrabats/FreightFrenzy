package robotparts;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Odometry extends RobotPart{
    public Servo odometry;

    @Override
    public void init() {
        odometry = createServo("odometry", Servo.Direction.FORWARD, 0, 1);
        //
    }
    public void move(double position)
    {
        odometry.setPosition(position);
    }
    public void stop()
    {
        odometry.setPosition(0);
    }
}
