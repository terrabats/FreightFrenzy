package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.Encoder;

public class Intake extends RobotPart {
    private CMotor in;

    @Override
    public void init(){
        in = createCMotor("in", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(double power){
        in.setPower(power);
    }

}
