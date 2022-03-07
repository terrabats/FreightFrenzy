package robotparts.hardware.mecanum;

import static global.General.fault;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Initial;
import automodules.stage.Stage;
import global.Constants;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.positional.PMotor;
import robotparts.hardware.Lift;
import robotparts.hardware.TwoLift;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnParameterCodeSeg;

public class MecanumLift extends TwoLift {

    @Override
    public void move(double p){
        if(p > 0){
            motorUp.setPower(p + getRestPows()[0]);
            motorDown.setPower((0.3 * p) + getRestPows()[1]);
        }else if (p < 0){
            motorUp.setPower((0.5 * p) + getRestPows()[0]);
            motorDown.setPower(p + getRestPows()[1]);
        }else{
            motorUp.setPower(getRestPows()[0]);
            motorDown.setPower(getRestPows()[1]);
        }
    }


    @Override
    public void init() {
        super.init();
        // TODO FIX
        // Problem with stall detection
//        motorUp.useStallDetector(0.2, getRestPows()[0], 200,0.03, 2);
//        motorDown.useStallDetector(0.2, getRestPows()[1],200,0.03, 2);
    }

    @Override
    protected double[] getRestPows() {
        return new double[]{0.07, -0.05};
    }

    @Override
    protected double getAngle() {
        return Math.PI/4;
    }

    @Override
    public double CM_PER_TICK() {
        return 1 / Constants.LIFT_CM_TO_TICKS;
    }

    public Stage liftTime(double power, double time){return new Stage(
            usePart(),
            main(power),
            exitTime(time),
            stop(),
            returnPart()
    );}

    /**
     * Lift to a certain position
     * @param power
     * @param height
     * @return
     */
    public Stage liftEncoderUp(double power, double height){return new Stage(
            usePart(),
            initialSetTargetUp(height),
            main(power),
            exitReachedTargetUp(),
            stopEncoder(),
            returnPart()
    );}


    public Stage liftEncoderDown(double power, double height){return new Stage(
            usePart(),
            initialSetTargetDown(height),
            main(power),
            exitReachedTargetDown(),
            stopEncoder(),
            returnPart()
    );}
}
