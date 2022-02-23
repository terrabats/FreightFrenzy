package robot.configs;

import robotparts.sensors.TwoOdometry;

public class NewMecanumConfig extends RobotConfig{
    {
        odometry = new TwoOdometry(3, -0.5);
        define(
                tankIntake,
                mecanumDrive,
                odometry,
                gyroSensors
        );
    }
}
