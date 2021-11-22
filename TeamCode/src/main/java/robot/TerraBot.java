package robot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import robotparts.MecanumDrive;
import robotparts.Odometry;

public class TerraBot extends RobotFramework {
    public MecanumDrive mechDrive = new MecanumDrive();
    public Odometry odometry = new Odometry();
}
