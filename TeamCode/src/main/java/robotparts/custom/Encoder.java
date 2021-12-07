package robotparts.custom;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Objects;

public class Encoder {
    private DcMotor motor;
    private Type type;

    public Encoder(DcMotor m, Type t) {
        motor = m;
        type = t;
    }
    public double getPos() {
        return motor.getCurrentPosition();
    }
    public Type getType(){
        return type;
    }

    public enum Type{
        NORMAL,
        MOTOR
    }
}
