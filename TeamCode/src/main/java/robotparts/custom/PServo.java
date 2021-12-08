package robotparts.custom;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo.*;

public class PServo {
    private Servo servo;
    private Direction direction;
    private double lower = 0;
    private double upper = 1;

    public PServo(Servo s, Direction dir, double l , double u){
        servo = s;
        lower = l;
        upper = u;
        direction = dir;
        servo.setDirection(direction);
        servo.scaleRange(lower, upper);
        servo.setPosition(0);
    }

    public void setPosition(double pos){
        servo.setPosition(pos);
    }

    public double getPosition(){
        return servo.getPosition();
    }
    public double getLower(){
        return lower;
    }
    public double getUpper(){
        return upper;
    }

    public Direction getDirection(){
        return servo.getDirection();
    }

}
