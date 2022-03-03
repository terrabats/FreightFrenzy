package robotparts.hardware.mecanum;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import global.Constants;
import robotparts.electronics.positional.PMotor;
import robotparts.hardware.Lift;
import util.codeseg.ReturnParameterCodeSeg;

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
