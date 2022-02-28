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
import util.codeseg.ReturnParameterCodeSeg;

import static global.General.*;

public class MecanumLift extends Lift {
    @Override
    protected double getRestPow() {
        return 0;
    }

    @Override
    public void init() {
        super.init();
        motors[0].useStallDetector(0.2, 0,200,0.03, 2);
        motors[1].useStallDetector(0.2, 0,200,0.03, 2);
    }

    @Override
    protected double getAngle() {
        return Math.PI/4;
    }

    @Override
    public PMotor[] getMotors() {
        return new PMotor[] {
            createPMotor("lil", DcMotorSimple.Direction.FORWARD),
            createPMotor("lir", DcMotorSimple.Direction.REVERSE)
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public ReturnParameterCodeSeg<Double, Double>[] getTargetConvertors() {
        return new ReturnParameterCodeSeg[] {
            h -> (Double) h * Constants.LIFT_CM_TO_TICKS,
            h -> (Double) h * Constants.LIFT_CM_TO_TICKS
        };
    }
}
