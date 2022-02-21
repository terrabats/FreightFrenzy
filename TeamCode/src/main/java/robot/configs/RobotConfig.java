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

    public final TankDrive tankDrive = new TankDrive();
    public final Intake intake = new Intake();
    public final Turret turret = new Turret();
    public final Lift lift = new Lift();
    public final Outtake outtake = new Outtake();
    public final Carousel carousel = new Carousel();
    public final ColorSensors colorSensors = new ColorSensors();
    public final GyroSensors gyroSensors = new GyroSensors();
    public Odometry odometry;
    public final TouchSensors touchSensors = new TouchSensors();
    public final DistanceSensors distanceSensors = new DistanceSensors();
    public final Led leds = new Led();
    public final MecanumDrive mecanumDrive = new MecanumDrive();
//    public TwoOdometry odometry2 = new TwoOdometry(3, -0.5);

    protected final void define(RobotPart... parts){
        for(RobotPart part:parts){ part.instantiate(); }
    }
}
