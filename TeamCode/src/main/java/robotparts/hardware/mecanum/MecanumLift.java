package robotparts.hardware.mecanum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import global.Constants;
import robotparts.electronics.positional.PMotor;
import robotparts.hardware.Lift;
import util.codeseg.ReturnParameterCodeSeg;

public class MecanumLift extends Lift {

    /**
     * Move the lift motor at a certain power
     * @param p
     */
    @Override
    public void move(double p){
        if(p > 0){
            motors[0].setPower(p + getRestPows()[0]);
            motors[1].setPower((0.7 * p) + getRestPows()[1]);
        }else if (p < 0){
            motors[0].setPower((0.9 * p) + getRestPows()[0]);
            motors[1].setPower(p + getRestPows()[1]);
        }else{
            motors[0].setPower(getRestPows()[0]);
            motors[1].setPower(getRestPows()[1]);
        }
    }


    @Override
    public void init() {
        super.init();

        motors[0].useStallDetector(0.2, 0,200,0.03, 2);
        motors[1].useStallDetector(0.2, 0,200,0.03, 2);
    }

    @Override
    protected Double[] getRestPows() {
        return new Double[]{0.07, -0.05};
    }

    @Override
    protected double getAngle() {
        return Math.PI/4;
    }

    @Override
    public PMotor[] getMotors() {
        return new PMotor[] {
            createPMotor("lil", DcMotorSimple.Direction.REVERSE),
            createPMotor("lir", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.FLOAT)
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