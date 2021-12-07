package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import java.util.Objects;

import robotparts.RobotPart;

public class TankDrive extends RobotPart {
    private DcMotor fr,br,fl,bl;

    @Override
    public void init(){
        fr = createMotor("fr", DcMotorSimple.Direction.REVERSE);
        br = createMotor("br", DcMotorSimple.Direction.REVERSE);
        fl = createMotor("fl", DcMotorSimple.Direction.FORWARD);
        bl = createMotor("bl", DcMotorSimple.Direction.FORWARD);
    }

    public void move(double f, double t){
        fr.setPower(f-t);
        br.setPower(f-t);
        fl.setPower(f+t);
        bl.setPower(f+t);
    }
}
