package robotparts.hardware.Tank;

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
import util.codeseg.ReturnParameterCodeSeg;

import static global.General.*;

public class TankLift extends Lift {
    @Override
    protected double getRestPow() { return Constants.LIFT_REST_POW; }

    @Override
    public void init() {
        super.init();
        motors[0].useStallDetector(0.2, Constants.LIFT_REST_POW,200,0.03, 2);
    }

    @Override
    protected double getAngle() {
        return Math.PI/2;
    }

    @Override
    public PMotor[] getMotors() {
        return new PMotor[] {
            createPMotor("li", DcMotorSimple.Direction.FORWARD)
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public ReturnParameterCodeSeg<Double, Double>[] getTargetConvertors() {
        return new ReturnParameterCodeSeg[] {
                h -> (Double)h * Constants.LIFT_CM_TO_TICKS
        };
    }

}
