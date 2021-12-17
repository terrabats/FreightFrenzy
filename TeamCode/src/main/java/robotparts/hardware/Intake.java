package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.Encoder;

public class Intake extends RobotPart {
    private CMotor in;
//    private Encoder inEnc;

    @Override
    public void init(){
        in = createCMotor("in", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        inEnc = createEncoder("in", "inEnc", Encoder.Type.MOTOR);
    }

    public void move(double power){
        in.setPower(power);
    }


    public void moveRF(double power) { in.setPowerRF(power); }

//    public double getIntakePos(){
//        return inEnc.getPos();
//    }
}
