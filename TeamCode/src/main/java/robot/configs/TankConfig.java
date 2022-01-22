package robot.configs;

import robotparts.hardware.Carousel;
import robotparts.hardware.Intake;
import robotparts.hardware.Lift;
import robotparts.hardware.Outtake;
import robotparts.hardware.TankDrive;
import robotparts.hardware.Turret;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.DistanceSensors;
import robotparts.sensors.GyroSensors;
import robotparts.sensors.Led;
import robotparts.sensors.Odometry;
import robotparts.sensors.TouchSensors;
import robotparts.unused.MecanumDrive;

public class TankConfig extends RobotConfig{
    // TODO FIX
    // Make the config system better
    /**
     *  Define RobotPart objects here. If you are not using a specific robot part
     *  define the object but don't instantiate it. All other methods related to the robot
     *  should go in robot framework.
     */
    public void create(){
        tankDrive = new TankDrive();
        intake = new Intake();
        turret = new Turret();
        lift = new Lift();
        outtake = new Outtake();
        carousel = new Carousel();
        colorSensors = new ColorSensors();
        gyroSensors = new GyroSensors();
    }
}
