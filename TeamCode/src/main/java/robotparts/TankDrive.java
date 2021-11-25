package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Objects;

public class TankDrive extends RobotPart{
    private DcMotor fr,br,fl,bl;

    @Override
    public void init(){
        // TODO: SET DIRECTIONS
        fr = createMotor("fr", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br = createMotor("br", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl = createMotor("fl", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl = createMotor("bl", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(double f, double t){
        fr.setPower(f-t);
        br.setPower(f-t);
        fl.setPower(f+t);
        bl.setPower(f+t);
    }

    public double getPos(String mot) {
        if (this.motors.containsKey(mot)) {
            return Objects.requireNonNull(this.motors.get(mot)).getCurrentPosition();
        } else {
            return -1;
        }
    }
}
