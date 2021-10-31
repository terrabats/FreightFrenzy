package robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static java.lang.Math.*;

public class Turret extends RobotPart {
    private DcMotor turret;

    @Override
    public void init() {
        turret = createMotor("turret", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void rotate(double pow) {
        turret.setPower(pow);
    }

    public void rotateCW(double pow) {
        rotate(abs(pow));
    }

    public void rotateCCW(double pow) {
        rotate(-abs(pow));
    }
}
