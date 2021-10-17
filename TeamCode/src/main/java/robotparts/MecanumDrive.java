package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

public class MecanumDrive extends RobotPart{
    private DcMotor fr,br,fl,bl;

    @Override
    public void init(){
        fr = createMotor("fr", Direction.REVERSE, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
        br = createMotor("br", Direction.FORWARD, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
        fl = createMotor("fl", Direction.FORWARD, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
        bl = createMotor("bl", Direction.REVERSE, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(double f, double s, double t){
        fr.setPower(f-s-t);
        br.setPower(-f-s+t);
        fl.setPower(f+s+t);
        bl.setPower(-f+s-t);
    }


}
