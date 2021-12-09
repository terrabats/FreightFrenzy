package robotparts.custom;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo.*;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.TreeMap;

public class PServo {
    private Servo servo;
    private Direction direction;
    private double lower;
    private double upper;
    private TreeMap<String, Double> positions = new TreeMap<>();

    public PServo(Servo s, Direction dir, double l , double u){
        servo = s;
        lower = l;
        upper = u;
        direction = dir;
        servo.setDirection(direction);
        servo.scaleRange(lower, upper);
        servo.setPosition(0);
        addPosition("lower", lower);
        addPosition("upper", upper);
    }
    public void addPosition(String name, double p){
        positions.put(name, p);
    }

    public void setPosition(double pos){
        servo.setPosition(pos);
    }
    public void setPosition(String name){
        setPosition(positions.get(name));
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
