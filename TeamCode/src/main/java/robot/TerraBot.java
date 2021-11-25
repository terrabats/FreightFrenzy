package robot;

import robotparts.MecanumDrive;
import robotparts.Odometry;
import robotparts.TankDrive;

public class TerraBot extends RobotFramework {
    public TankDrive tankDrive = new TankDrive();
    public MecanumDrive mechDrive = new MecanumDrive();
    public Odometry odometry = new Odometry();
}
