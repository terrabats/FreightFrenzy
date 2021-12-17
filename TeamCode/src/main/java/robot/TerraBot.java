package robot;

import robotparts.custom.LED;
import robotparts.hardware.Carousel;
import robotparts.hardware.Intake;
import robotparts.hardware.Lift;
import robotparts.sensors.Color;
import robotparts.sensors.Distance;
import robotparts.sensors.Gyro;
import robotparts.sensors.Led;
import robotparts.sensors.Odometry;
import robotparts.hardware.Outtake;
import robotparts.hardware.TankDrive;
import robotparts.hardware.Turret;
import robotparts.sensors.Touch;

public class TerraBot extends RobotFramework {
    public TankDrive tankDrive = new TankDrive();
    public Intake intake = new Intake();
    public Turret turret = new Turret();
    public Lift lift = new Lift();
    public Outtake outtake = new Outtake();
    public Carousel carousel = new Carousel();


    public Odometry odometry; //= new Odometry();
    public Gyro gyro = new Gyro();
    public Distance distance; // = new Distance();
    public Color color = new Color();
    public Touch touch = new Touch();
    public Led led; // = new Led();

    // TODO NEW
    // Make it so that it adds all of the robot parts as objects but only the active ones will be construced with the given name
    // Or something where a empty robot part is made
}
