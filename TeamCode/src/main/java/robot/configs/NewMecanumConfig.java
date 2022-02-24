package robot.configs;

import robotparts.sensors.TwoOdometry;

public class NewMecanumConfig extends RobotConfig{
    {
        odometry = new TwoOdometry(3, -0.5);
        define(
                mecanumDrive,
                odometry,
                gyroSensors,
                cameras,
                mecanumCarousel,
                mecanumIntake,
                mecanumLift,
                mecanumOuttake
        );
    }
}
