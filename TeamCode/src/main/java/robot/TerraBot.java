package robot;

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
import robotparts.unused.MecanumDrive;

public class TerraBot extends RobotFramework {

    public TankDrive tankDrive = new TankDrive();
    public Intake intake = new Intake();
    public Turret turret = new Turret();
    public Lift lift = new Lift();
    public Outtake outtake = new Outtake();
    public Carousel carousel = new Carousel();
    public Color color = new Color();
    public Touch touch = new Touch();


    // Unused
    public Odometry odometry;
    public Gyro gyro = new Gyro();
    public Distance distance;
    public Led led;
    public MecanumDrive mecDrive;
}
