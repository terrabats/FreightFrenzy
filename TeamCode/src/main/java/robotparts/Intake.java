package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake extends RobotPart{
    private DcMotor intakemotor;

    @Override
    public void init(){
        // TODO: SET DIRECTIONS
        intakemotor = createMotor("lift", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void lift(double power){
        intakemotor.setPower(power);
    }
}
