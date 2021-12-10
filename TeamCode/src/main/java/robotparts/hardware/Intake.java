package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import robotparts.RobotPart;
import robotparts.custom.Encoder;

public class Intake extends RobotPart {
    private DcMotor in;
    private Encoder inEnc;

    @Override
    public void init(){
        in = createMotor("in", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        inEnc = createEncoder("in", "inEnc", Encoder.Type.MOTOR);
    }

    public void spin(double power){
        if(isInactive()){return;}
        in.setPower(power);
    }

    public double getIntakePos(){
        return inEnc.getPos();
    }
}
