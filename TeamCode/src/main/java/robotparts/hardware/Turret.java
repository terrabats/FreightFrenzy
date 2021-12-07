package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import robotparts.RobotPart;
import robotparts.custom.Encoder;

import static java.lang.Math.*;

public class Turret extends RobotPart {
    private DcMotor tr;
    private Encoder trEnc;

    @Override
    public void init() {
        tr = createMotor("tr", DcMotorSimple.Direction.FORWARD);
        trEnc = createEncoder("tr", "trEnc", Encoder.Type.NORMAL);
    }

    public void spin(double pow) {tr.setPower(pow);}
}
