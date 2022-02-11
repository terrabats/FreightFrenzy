package robotparts.sensors;

import geometry.circles.AngleType;
import robotparts.RobotPart;
import robotparts.electronics.input.IGyro;


public class GyroSensors extends RobotPart {
    /**
     * Two gyro sensors from each expansion hub on different sides on the robot
     */
    private IGyro gsr, gsl;

    @Override
    public void init() {
        gsr = createGyro("gsr");
        gsl = createGyro("gsl");
    }

    /**
     * Get headings in radians and degrees
     * @return heading
     */
    public double getRightHeadingDeg() { return -gsr.getHeading(); }
    public double getRightHeadingRad() { return AngleType.degToRad(getRightHeadingDeg()); }
    public double getLeftHeadingDeg() { return gsl.getHeading(); }
    public double getLeftHeadingRad() { return AngleType.degToRad(getRightHeadingDeg()); }

}
