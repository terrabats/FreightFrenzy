package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

public class MecanumDrive extends RobotPart{
    private DcMotor fr;
    private DcMotor br;
    private DcMotor fl;
    private DcMotor bl;

    public void init(HardwareMap hwMap){
        super.init(hwMap);
        fr = createMotor("fr", Direction.FORWARD, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
        br = createMotor("br", Direction.FORWARD, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
        fl = createMotor("fl", Direction.FORWARD, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
        bl = createMotor("bl", Direction.FORWARD, ZeroPowerBehavior.BRAKE, RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(double f, double s, double t){

    }


}
