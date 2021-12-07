package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.R;

import robotparts.RobotPart;
import robotparts.custom.Encoder;

public class Lift extends RobotPart {
    private DcMotor li;
    private Encoder liEnc;

    @Override
    public void init() {
        li = createMotor("li", DcMotorSimple.Direction.FORWARD);
        liEnc = createEncoder("li", "liEnc", Encoder.Type.MOTOR);
    }

    public void move(double p){
        li.setPower(p);
    }

    public double getLiftPos(){
        return liEnc.getPos();
    }
}
