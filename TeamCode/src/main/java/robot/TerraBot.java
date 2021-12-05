package robot;

import geometry.AngleType;
import robotparts.Intake;
import robotparts.MecanumDrive;
import robotparts.Odometry;
import robotparts.TankDrive;

public class TerraBot extends RobotFramework {
    public TankDrive tankDrive = new TankDrive();
    public Intake intake = new Intake();
    public Odometry odometry = new Odometry();

//    public void updateCoordinates() {
//        double[] posChange = odometry.getPosChangeCenter();
//        localPlane.move(posChange[0], posChange[1]);
//        localPlane.rotate(posChange[2], AngleType.RADIANS);
//    }
}
