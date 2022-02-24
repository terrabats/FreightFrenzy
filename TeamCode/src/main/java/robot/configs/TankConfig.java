package robot.configs;

import robotparts.sensors.Odometry;

public class TankConfig extends RobotConfig{
    /**
     *  Define RobotPart objects here. If you are not using a specific robot part
     *  define the object but don't instantiate it. All other methods related to the robot
     *  should go in robot framework.
     */
    {
        odometry = new Odometry(4.6);
        define(
                tankDrive,
                tankIntake,
                tankTurret,
                tankLift,
                tankOuttake,
                tankCarousel,
                colorSensors,
                gyroSensors,
                odometry,
                cameras
        );
    }
}
