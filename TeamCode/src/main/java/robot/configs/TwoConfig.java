package robot.configs;

import robotparts.sensors.TwoOdometry;

public class TwoConfig extends RobotConfig{
    {
        odometry = new TwoOdometry(3, -0.5);
        define(
                intake,
                mecanumDrive,
                odometry,
                gyroSensors
        );
    }
}
