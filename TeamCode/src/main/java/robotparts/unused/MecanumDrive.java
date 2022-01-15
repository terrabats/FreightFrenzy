package robotparts.unused;

import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;

/**
 * NOTE: Uncommented
 */

public class MecanumDrive extends RobotPart {
    private CMotor fr,br,fl,bl;

    @Override
    public void init(){
        fr = createCMotor("fr", Direction.REVERSE);
        br = createCMotor("br", Direction.FORWARD);
        fl = createCMotor("fl", Direction.FORWARD);
        bl = createCMotor("bl", Direction.REVERSE);
    }

    public void move(double f, double s, double t){
        fr.setPower(f-s-t);
        br.setPower(-f-s+t);
        fl.setPower(f+s+t);
        bl.setPower(-f+s-t);
    }


}
