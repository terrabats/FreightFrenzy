package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Objects;

import robotparts.RobotPart;
import robotparts.custom.PServo;

public class TankDrive extends RobotPart {
    private DcMotor fr,br,fl,bl;
    private PServo re;

    @Override
    public void init(){
        fr = createMotor("fr", DcMotorSimple.Direction.REVERSE);
        br = createMotor("br", DcMotorSimple.Direction.REVERSE);
        fl = createMotor("fl", DcMotorSimple.Direction.FORWARD);
        bl = createMotor("bl", DcMotorSimple.Direction.FORWARD);
        re = createPServo("re", Servo.Direction.FORWARD, 0, 1);
    }

    @Override
    public void move(double f, double t){
        fr.setPower(f-t);
        br.setPower(f-t);
        fl.setPower(f+t);
        bl.setPower(f+t);
    }

    @Override
    public void moveTele(double f, double t) {super.moveTele(f, Math.abs(t) < 0.2 ? 0 : t);}

    public void retract(double pos){
        re.setPosition(pos);
    }
}
