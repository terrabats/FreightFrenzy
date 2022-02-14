package robot.configs;

import robot.RobotFramework;
import robotparts.RobotPart;
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
import robotparts.hardware.MecanumDrive;
import robotparts.sensors.TwoOdometry;

public abstract class RobotConfig extends RobotFramework {

    public TankDrive tankDrive = new TankDrive();
    public Intake intake = new Intake();
    public Turret turret = new Turret();
    public Lift lift = new Lift();
    public Outtake outtake = new Outtake();
    public Carousel carousel = new Carousel();
    public ColorSensors colorSensors = new ColorSensors();
    public GyroSensors gyroSensors = new GyroSensors();
    public Odometry odometry = new Odometry(4.6);
    public TouchSensors touchSensors = new TouchSensors();
    public DistanceSensors distanceSensors = new DistanceSensors();
    public Led leds = new Led();
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public TwoOdometry odometry2 = new TwoOdometry();

    protected final void define(RobotPart... parts){
        for(RobotPart part:parts){ part.instantiate(); }
    }
}
