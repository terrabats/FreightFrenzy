package global;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import automodules.AutoModules;
import automodules.Modules;
import automodules.Stages;
import debugging.Synchroniser;
import elements.FieldSide;
import robot.TerraBot;
import teleutil.GamepadHandler;
import debugging.Fault;
import debugging.Logger;
import util.User;
import util.store.Storage;

import static global.General.*;
import static global.General.fieldSide;

public interface Common{
    default void reference(OpMode thisOpMode){
        hardwareMap = thisOpMode.hardwareMap;
        telemetry = thisOpMode.telemetry;
        gamepad1 = thisOpMode.gamepad1;
        gamepad2 = thisOpMode.gamepad2;
        mainUser = User.getUserFromTypeOfOpMode(thisOpMode);
        gph1 = new GamepadHandler(gamepad1);
        gph2 = new GamepadHandler(gamepad2);
        fault = new Fault();
        sync = new Synchroniser();
        log = new Logger();
        storage = new Storage();
        gameTime = new ElapsedTime();
        bot = new TerraBot();
        modules = new Modules();
        stages = new Stages();
        autoModules = new AutoModules();
        bot.init();
    }

    default void activate(FieldSide side){
        fieldSide = side;
        log.watch("Ready");
        log.showTelemetry();
    }
    default void ready(){
        bot.start();
        sync.resetDelay();
        log.clearTelemetry();
    }
    default void update(boolean showTelemetry){
        bot.update();
        gph1.run();
        gph2.run();
        sync.update();
        if(showTelemetry){log.showTelemetry();}
    }
    default void end(){
        bot.stop();
        sync.logDelay();
        log.showLogs();
        storage.saveItems();
    }
}
