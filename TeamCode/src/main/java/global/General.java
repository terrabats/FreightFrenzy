package global;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import debugging.Synchroniser;
import robot.TerraBot;
import teleutil.GamepadHandler;
import debugging.Fault;
import debugging.Logger;

public class General {
    public static TerraBot bot;
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static Gamepad gamepad1;
    public static Gamepad gamepad2;
    public static ElapsedTime gameTime;
    public static Fault fault;
    public static GamepadHandler gph1;
    public static GamepadHandler gph2;
    public static Logger log;
    public static Synchroniser sync;
}
