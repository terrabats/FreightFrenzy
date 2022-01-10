package robotparts.sensors;

import robotparts.RobotPart;
import robotparts.electronics.IGyro;

/**
 * NOTE: Uncommented
 */

public class GyroSensors extends RobotPart {
    private IGyro gsr, gsl;

    @Override
    public void init() {
        gsr = createGyro("gsr");
//        gsl = createGyro("gsl");
    }
    public double getRightHeadingRad() {
        return getRightHeadingDeg() * Math.PI/180;
    }
    public double getRightHeadingDeg() {
        return gsr.getHeading();
    }
//    public double getLeftHeading() {
//        return gsl.getHeading();
//    }

}
