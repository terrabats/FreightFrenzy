package robot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import robotparts.MecanumDrive;
import robotparts.Odometry;

// TODO
// Add all robotparts to this file

public class TerraBot extends RobotFramework {
    public static MecanumDrive mechDrive = new MecanumDrive();
    public static Odometry odometry = new Odometry();
}
