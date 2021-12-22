package robotparts.sensors;

import robotparts.RobotPart;
import robotparts.electronics.IGyro;

public class GyroSensor extends RobotPart {
    private IGyro gsr, gsl;

    // TODO TEST
    // This class takes forever to init, maybe calibration problem?
    // Run this and try to see what the problem is

    @Override
    public void init() {
        gsr = createGyro("gsr");
        gsl = createGyro("gsl");
    }

    public void resetRight() {
        //
    }


//    public double getLeftHeading(){
//        return gsl.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
//    }
    public double getRightHeading() {
        return gsr.getHeading();
    }
    public double getLeftHeading() {
        return gsl.getHeading();
    }

}
