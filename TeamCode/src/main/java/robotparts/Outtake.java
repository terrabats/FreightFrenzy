package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake extends RobotPart{
    private DcMotor lift;
    private DcMotor turn;
    private Servo outtakeLock;

    @Override
    public void init(){
        // TODO: SET DIRECTIONS
        lift = createMotor("lift", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeLock = createServo("outtakeLock", Servo.Direction.FORWARD, 0, 1);
        turn = createMotor("turn", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void lift(double power){
        lift.setPower(power);
    }
    public void lock() {
        outtakeLock.setPosition(1);
    }
    public void unlock() {
        outtakeLock.setPosition(0);
    }
    public void turn(double pow)
    {
        turn.setPower(pow);
    }
}
