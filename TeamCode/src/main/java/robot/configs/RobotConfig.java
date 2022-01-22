package robot.configs;

import robot.RobotFramework;
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

public abstract class RobotConfig extends RobotFramework {

    public TankDrive tankDrive;
    public Intake intake;
    public Turret turret;
    public Lift lift;
    public Outtake outtake;
    public Carousel carousel;
    public ColorSensors colorSensors;
    public GyroSensors gyroSensors;
    public Odometry odometry;
    public TouchSensors touchSensors;
    public DistanceSensors distanceSensors;
    public Led leds;
    public MecanumDrive mecanumDrive;


    public abstract void create();
}
