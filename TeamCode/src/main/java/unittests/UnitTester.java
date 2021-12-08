package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

import teleutil.button.OnPressEventHandler;
import unittests.framework.CommonTest;
import unittests.framework.CoordinatePlaneTest;
import unittests.framework.FaultTest;
import unittests.framework.GamepadTest;
import unittests.framework.LoggerTest;
import unittests.framework.RobotFunctionsTest;
import unittests.framework.ThreadTest;
import unittests.hardware.CarouselTest;
import unittests.hardware.IntakeTest;
import unittests.hardware.LiftTest;
import unittests.hardware.OuttakeTest;
import unittests.hardware.TankDriveTest;
import unittests.hardware.TurrentTest;
import unittests.sensor.ColorTest;
import unittests.sensor.DistanceTest;
import unittests.sensor.GyroTest;
import unittests.sensor.OdometryTest;
import unittests.sensor.SensorTest;
import unittests.sensor.TouchTest;
import unittests.unused.MechDriveTest;
import util.Timer;

import global.Common;
import teleutil.button.Button;

import static global.General.*;

@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends OpMode implements Common {
    private ArrayList<UnitTest> allUnitTests = new ArrayList<>();
    private int currentTestNum = 0;
    private final Timer timer = new Timer();

    private final int delay = 5;
    private final TestType testingMode = TestType.CONTROL;

    private void createUnitTests(){
        allUnitTests = new ArrayList<>();
        // Framework
//        allUnitTests.add(new CommonTest());
//        allUnitTests.add(new CoordinatePlaneTest());
//        allUnitTests.add(new FaultTest());
//        allUnitTests.add(new GamepadTest());
//        allUnitTests.add(new LoggerTest());
//        allUnitTests.add(new RobotFunctionsTest());
//        allUnitTests.add(new ThreadTest());
        // Hardware
        allUnitTests.add(new TankDriveTest());
        allUnitTests.add(new IntakeTest());
        allUnitTests.add(new TurrentTest());
        allUnitTests.add(new LiftTest());
        allUnitTests.add(new OuttakeTest());
        allUnitTests.add(new CarouselTest());
        // Sensor
//        allUnitTests.add(new ColorTest());
//        allUnitTests.add(new DistanceTest());
//        allUnitTests.add(new GyroTest());
//        allUnitTests.add(new OdometryTest());
//        allUnitTests.add(new TouchTest());
//        allUnitTests.add(new OdometryTest());
    }

    @Override
    public void init() {
        reference(this);
        createUnitTests();
        for (UnitTest t: allUnitTests) {t.init();}
        if(testingMode.equals(TestType.CONTROL)) {
            gph1.link(Button.X, OnPressEventHandler.class, (double... args) -> nextTest());
        }
        log.watch("Testing Mode: " + testingMode.toString());
        activate();
    }

    @Override
    public void start() {
        for (UnitTest t : allUnitTests) {t.start();}
        timer.reset();
        ready();
    }

    @Override
    public void loop() {
        log.display("Testing " + getCurrentTestName() + "UnitTest type " + getCurrentTest().getType().toString());
        if(testingMode.equals(TestType.TIME)){
            if(timer.seconds() > delay){
                nextTest();
            }
        }
        if (currentTestNum < allUnitTests.size()) getCurrentTest().test();
        update(true);
    }

    @Override
    public void stop(){
        end();
    }


    private String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }

    private UnitTest getCurrentTest(){
        return allUnitTests.get(currentTestNum);
    }

    private void nextTest(){
        allUnitTests.get(currentTestNum).stop();
        currentTestNum++;
        if(currentTestNum >= allUnitTests.size()){
            requestOpModeStop();
        }
        log.clearTelemetry();
        timer.reset();
    }

    private enum TestType{
        CONTROL,
        TIME
    }
}
