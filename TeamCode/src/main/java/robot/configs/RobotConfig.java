package robot.configs;

import robot.RobotFramework;
import robotparts.RobotPart;
import robotparts.hardware.TankCarousel;
import robotparts.hardware.TankIntake;
import robotparts.hardware.TankLift;
import robotparts.hardware.TankOuttake;
import robotparts.hardware.TankDrive;
import robotparts.hardware.TankTurret;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.DistanceSensors;
import robotparts.sensors.GyroSensors;
import robotparts.sensors.Led;
import robotparts.sensors.Odometry;
import robotparts.sensors.TouchSensors;
import robotparts.hardware.MecanumDrive;

public abstract class RobotConfig extends RobotFramework {

    public final TankDrive tankDrive = new TankDrive();
    public final TankIntake tankIntake = new TankIntake();
    public final TankTurret tankTurret = new TankTurret();
    public final TankLift tankLift = new TankLift();
    public final TankOuttake tankOuttake = new TankOuttake();
    public final TankCarousel tankCarousel = new TankCarousel();
    public final ColorSensors colorSensors = new ColorSensors();
    public final GyroSensors gyroSensors = new GyroSensors();
    public Odometry odometry;
    public final TouchSensors touchSensors = new TouchSensors();
    public final DistanceSensors distanceSensors = new DistanceSensors();
    public final Led leds = new Led();
    public final MecanumDrive mecanumDrive = new MecanumDrive();

    protected final void define(RobotPart... parts){
        for(RobotPart part:parts){ part.instantiate(); }
    }
}
