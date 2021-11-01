package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.TreeMap;

import debugging.Fault;
import debugging.Logger;
import robot.TerraBot;
import teleutil.GamepadHandler;
import util.condition.Status;

import static global.General.bot;
import static global.General.fault;
import static global.General.gameTime;
import static global.General.gamepad1;
import static global.General.gamepad2;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.hardwareMap;
import static global.General.log;
import static global.General.telemetry;

public class UnitTest {
    protected Status testStatus = Status.IDLE;
    public UnitTest(){
        UnitTester.allUnitTests.add(this);
    }
    public void test(){
        testStatus = Status.ACTIVE;
    }
    public boolean active(){
        return testStatus.equals(Status.ACTIVE);
    }
    public void init() {}
    public void loop() {}
    public void stop() {}
    public void start() {}

    protected void reference(OpMode thisOpMode){
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

    protected void update(boolean showTelemetry){
        if(showTelemetry){log.showTelemetry();}
    }

    protected void end(){
        log.showLogs();
        bot.stop();
    }

}
