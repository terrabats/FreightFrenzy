package global;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import robot.TerraBot;
import teleutil.GamepadHandler;
import util.Fault;

public class General {
    public static TerraBot bot;
    public static Fault fault;
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static ElapsedTime gameTime = new ElapsedTime();
    public static GamepadHandler gph1;
    public static GamepadHandler gph2;
}
