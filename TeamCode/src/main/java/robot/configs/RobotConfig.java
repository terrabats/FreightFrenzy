package robot.configs;

import robot.RobotFramework;
import robotparts.RobotPart;
import robotparts.hardware.Tank.TankCarousel;
import robotparts.hardware.Tank.TankIntake;
import robotparts.hardware.Tank.TankLift;
import robotparts.hardware.Tank.TankOuttake;
import robotparts.hardware.Tank.TankDrive;
import robotparts.hardware.Tank.TankTurret;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.DistanceSensors;
import robotparts.sensors.GyroSensors;
import robotparts.sensors.Led;
import robotparts.sensors.Odometry;
import robotparts.sensors.TouchSensors;
import robotparts.hardware.Mecanum.MecanumDrive;

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
