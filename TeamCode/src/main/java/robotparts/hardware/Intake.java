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

    @Override
    public void move(double power){
        in.setPower(power);
    }

    @Override
    public void moveTele(double power) {
        super.move(power);
    }

    public double getIntakePos(){
        return inEnc.getPos();
    }
}
