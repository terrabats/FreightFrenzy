package robot;

import robotparts.hardware.Carousel;
import robotparts.hardware.Intake;
import robotparts.hardware.Lift;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.DistanceSensors;
import robotparts.sensors.GyroSensors;
import robotparts.sensors.Led;
import robotparts.sensors.Odometry;
import robotparts.hardware.Outtake;
import robotparts.hardware.TankDrive;
import robotparts.hardware.Turret;
import robotparts.sensors.TouchSensors;
import robotparts.unused.MecanumDrive;

public class TerraBot extends RobotFramework {

    /**
     *  Define RobotPart objects here. If you are not using a specific robot part
     *  define the object but don't instantiate it. All other methods related to the robot
     *  should go in robot framework.
     */

    public TankDrive tankDrive = new TankDrive();
    public Intake intake = new Intake();
    public Turret turret = new Turret();
    public Lift lift = new Lift();
    public Outtake outtake = new Outtake();
    public Carousel carousel = new Carousel();
    public ColorSensors colorSensors = new ColorSensors();
//    public TouchSensors touchSensors = new TouchSensors();
    public GyroSensors gyroSensors = new GyroSensors();
    public Odometry odometry = new Odometry();
//    public Distance distance = new Distance();
//    public Led led = new Led();
//    public MecanumDrive mecDrive = new MecanumDrive();


    // Empty Parts
//    public TankDrive tankDrive;
//    public Intake intake;
//    public Turret turret;
//    public Lift lift;
//    public Outtake outtake;
//    public Carousel carousel;
//    public ColorSensors colorSensors;
    public TouchSensors touchSensor;
//    public GyroSensors gyroSensors;
//    public Odometry odometry;
    public DistanceSensors distanceSensors;
    public Led led;
    public MecanumDrive mecanumDrive;
}
