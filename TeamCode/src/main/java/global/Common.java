package global;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import automodules.AutoModules;
import debugging.Synchroniser;
import elements.FieldSide;
import robot.TerraBot;
import teleutil.GamepadHandler;
import debugging.Fault;
import debugging.Logger;
import util.User;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.store.Storage;

import static global.General.*;
import static global.General.fieldSide;

public interface Common{
    /**
     * Reference initializes all of the robotParts using the opmode
     * @param thisOpMode
     */
    default void reference(OpMode thisOpMode){
        /**
         * Initialize all of the objects from the opmode
         */
        hardwareMap = thisOpMode.hardwareMap;
        telemetry = thisOpMode.telemetry;
        gamepad1 = thisOpMode.gamepad1;
        gamepad2 = thisOpMode.gamepad2;
        /**
         * Create the gamepadhanlders from the gamepads
         */
        gph1 = new GamepadHandler(gamepad1);
        gph2 = new GamepadHandler(gamepad2);
        /**
         * Create the debugging tools
         */
        fault = new Fault();
        sync = new Synchroniser();
        log = new Logger();
        /**
         * Create the gameTime
         */
        gameTime = new ElapsedTime();
        /**
         * Get the main user
         * NOTE: the user is automatically set from the type of opMode
         */
        mainUser = User.getUserFromTypeOfOpMode(thisOpMode);
        /**
         * Create the storage
         */
        storage = new Storage();
        /**
         * Create the robot, and then the modules, stages, and automodules
         */
        bot = new TerraBot();

        /**
         * Create the automodules
         */
        autoModules = new AutoModules();

        /**
         * Initialize the robot
         */
        bot.init();
        /**
         * Tell sync that a method in common was run
         */
        sync.ranMethodInCommon();
    }

    /**
     * Activate sets the field side and thus should only be used in teleop or auton
     * Also shows telemetry to display that the robot is ready
     * @param side
     */
    default void activate(FieldSide side){
        fieldSide = side;
        log.show("Ready");
        log.showTelemetry();
        sync.ranMethodInCommon();
    }

    /**
     * Starts the robot and clears the telemetry
     */
    default void ready(){
        bot.start();
        sync.resetDelay();
        log.clearTelemetry();
        sync.ranMethodInCommon();
    }

    /**
     * Updates the telemetry, checks the access for bot for the main user (either teleop or auton)
     * Runs the gamepad handlers
     * @param showTelemetry
     */
    default void update(boolean showTelemetry){
        if(sync.isFirstUpdate()){
            sync.ranMethodInCommon();
        }
        bot.checkAccess(mainUser);
        gph1.run();
        gph2.run();
        sync.update();
        if(showTelemetry){log.showTelemetry();}
    }

    /**
     * Stops the robot, halts all of the motors (in stop), checks if common is used properly, shows the logs, and saves the storage times
     */
    default void end(){
        bot.stop();
        sync.logDelay();
        sync.ranMethodInCommon();
        boolean isCommonBeingUsedProperly = sync.getNumberOfCommonMethodsRun() >= 4 && sync.getNumberOfCommonMethodsRun() <= 5;
        fault.check("You didn't call to super in one of the overriden methods for teleop", Expectation.EXPECTED, Magnitude.CRITICAL, isCommonBeingUsedProperly, true);
        log.showLogs();
        storage.saveItems();
    }
}
