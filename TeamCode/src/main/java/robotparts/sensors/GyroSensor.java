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
    public double getRightHeadingRad() {
        return getRightHeadingDeg() * Math.PI/180;
    }
    public double getRightHeadingDeg() {
        return gsr.getHeading();
    }
    public double getLeftHeading() {
        return gsl.getHeading();
    }

}
