package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import robotparts.RobotPart;

public class Outtake extends RobotPart {
    private Servo lo;

    @Override
    public void init(){
        lo = createServo("lo", Servo.Direction.FORWARD, 0, 1);
    }

    public void move(double pos){lo.setPosition(pos);}
}
