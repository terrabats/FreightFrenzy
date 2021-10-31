package global;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import robot.TerraBot;
import teleutil.GamepadHandler;
import debugging.Fault;
import debugging.Logger;

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
        log = new Logger();
        gameTime = new ElapsedTime();
        bot = new TerraBot();
    }
    default void end(boolean showLogs){
        if(showLogs){log.showLogs();}
    }
}
