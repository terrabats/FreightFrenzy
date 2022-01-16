package robotparts.sensors;

import geometry.circles.AngleType;
import robotparts.RobotPart;
import robotparts.electronics.input.IGyro;

/**
 * NOTE: Uncommented
 */

public class GyroSensors extends RobotPart {
    private IGyro gsr, gsl;

    @Override
    public void init() {
        gsr = createGyro("gsr");
        gsl = createGyro("gsl");
    }
    public double getRightHeadingDeg() {
        return gsr.getHeading();
    }
    public double getRightHeadingRad() { return AngleType.degToRad(getRightHeadingDeg()); }
    public double getLeftHeadingDeg() { return gsl.getHeading(); }
    public double getLeftHeadingRad() { return AngleType.degToRad(getRightHeadingDeg()); }

}
