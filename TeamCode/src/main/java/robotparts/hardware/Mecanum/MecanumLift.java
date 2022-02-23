package robotparts.hardware.Mecanum;

import static java.lang.Math.PI;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.positional.PMotor;
import robotparts.hardware.Lift;
import robotparts.hardware.Tank.TankLift;

import static global.General.*;

public class MecanumLift extends Lift {
    @Override
    protected double getAngle() {
        return PI/4;
    }
}
