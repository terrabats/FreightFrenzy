package global;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import debugging.Synchroniser;
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
        sync = new Synchroniser();
        log = new Logger();
        gameTime = new ElapsedTime();
        bot = new TerraBot();
        bot.init();
    }
    default void activate(){
        log.watch("Ready");
        log.showTelemetry();
    }
    default void ready(){
        bot.start();
        sync.resetDelay();
    }
    default void update(boolean showTelemetry){
        gph1.run();
        gph2.run();
        sync.update();
        if(showTelemetry){log.showTelemetry();}
    }
    default void end(){
        sync.logDelay();
        log.showLogs();
        bot.stop();
    }
}
