package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class TankDrive extends RobotPart{
    private DcMotor fr;
    private DcMotor br;
    private DcMotor fl;
    private DcMotor bl;

    public TankDrive(){
        super();
    }

    @Override
    public void init(){
        fr = createMotor("fr", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br = createMotor("br", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl = createMotor("fl", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl = createMotor("bl", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // BACKUP METHODS – ADD TO THIS FOR EACH MOTOR
    public DcMotor fr() { return motors.get("fr"); }
    public DcMotor br() { return motors.get("br"); }
    public DcMotor fl() { return motors.get("fl"); }
    public DcMotor bl() { return motors.get("bl"); }

    public void move(double f, double t){

    }
}
