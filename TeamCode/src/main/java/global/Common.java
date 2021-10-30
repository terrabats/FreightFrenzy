package global;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import robot.TerraBot;
import teleutil.GamepadHandler;
import util.Fault;

import static global.General.*;

public interface Common{
    default void reference(OpMode thisOpMode){
        hardwareMap = thisOpMode.hardwareMap;
        telemetry = thisOpMode.telemetry;
        gamepad1 = thisOpMode.gamepad1;
        gamepad2 = thisOpMode.gamepad2;
        gph1 = new GamepadHandler(gamepad1);
        gph2 = new GamepadHandler(gamepad2);
        fault = new Fault();
        gameTime = new ElapsedTime();
        bot = new TerraBot();
    }
}
