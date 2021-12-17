package robotparts.electronics;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo.*;

import java.util.TreeMap;

public class PServo extends Electronic{
    private final Servo servo;
    private final Direction direction;
    private final double lower;
    private final double upper;
    private final TreeMap<String, Double> positions = new TreeMap<>();

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


    public void setPosition(String name){
        if(isFreeToUse()) { servo.setPosition(positions.get(name)); }
    }

    public void setPositionRF(String name){
        servo.setPosition(positions.get(name));
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
