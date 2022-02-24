package robot.configs;

import robot.RobotFramework;
import robotparts.RobotPart;
import robotparts.hardware.Mecanum.MecanumCarousel;
import robotparts.hardware.Mecanum.MecanumIntake;
import robotparts.hardware.Mecanum.MecanumLift;
import robotparts.hardware.Mecanum.MecanumOuttake;
import robotparts.hardware.Tank.TankCarousel;
import robotparts.hardware.Tank.TankIntake;
import robotparts.hardware.Tank.TankLift;
import robotparts.hardware.Tank.TankOuttake;
import robotparts.hardware.Tank.TankDrive;
import robotparts.hardware.Tank.TankTurret;
import robotparts.sensors.Cameras;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.DistanceSensors;
import robotparts.sensors.GyroSensors;
import robotparts.sensors.Leds;
import robotparts.sensors.Odometry;
import robotparts.sensors.TouchSensors;
import robotparts.hardware.Mecanum.MecanumDrive;

public abstract class RobotConfig extends RobotFramework {

    /**
     * All of the tank-specific robot parts
     */
    public final TankDrive tankDrive = new TankDrive();
    public final TankIntake tankIntake = new TankIntake();
    public final TankTurret tankTurret = new TankTurret();
    public final TankLift tankLift = new TankLift();
    public final TankOuttake tankOuttake = new TankOuttake();
    public final TankCarousel tankCarousel = new TankCarousel();

    /**
     * All of the mecanum-specific robot parts
     */
    public final MecanumDrive mecanumDrive = new MecanumDrive();
    public final MecanumCarousel mecanumCarousel = new MecanumCarousel();
    public final MecanumIntake mecanumIntake = new MecanumIntake();
    public final MecanumLift mecanumLift = new MecanumLift();
    public final MecanumOuttake mecanumOuttake = new MecanumOuttake();

    /**
     * All of the robot parts in both tank and mecanum
     */
    public final ColorSensors colorSensors = new ColorSensors();
    public final GyroSensors gyroSensors = new GyroSensors();
    public Odometry odometry;
    public final TouchSensors touchSensors = new TouchSensors();
    public final DistanceSensors distanceSensors = new DistanceSensors();
    public final Leds leds = new Leds();
    public final Cameras cameras = new Cameras();

    protected final void define(RobotPart... parts){
        for(RobotPart part:parts){ part.instantiate(); }
    }
}
