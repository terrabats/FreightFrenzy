package robotparts.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;

import robotparts.RobotPart;

public class Gyro extends RobotPart {
    private BNO055IMU gsr, gsl;

    @Override
    public void init() {
        gsr = createGyro("gsr");
        gsl = createGyro("gsl");
    }

}
