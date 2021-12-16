package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

import elements.FieldSide;
import teleutil.button.OnPressEventHandler;
import unittests.framework.AutoModuleTest;
import unittests.framework.ModulesTest;
import unittests.framework.StorageTest;
import unittests.hardware.LiftTest;
import unittests.hardware.OuttakeTest;
import unittests.hardware.TurretTest;
import unittests.sensor.GyroTest;
import util.Timer;

import global.Common;
import teleutil.button.Button;

import static global.General.*;

//@Disabled
@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends OpMode implements Common {
    private ArrayList<UnitTest> allUnitTests = new ArrayList<>();
    private int currentTestNum = 0;
    private final Timer timer = new Timer();

    private final int delay = 5;
    private final TestType testingMode = TestType.CONTROL;

    private void createUnitTests(){
        // TODO
        // Make a framework to move around to select a specific unit test using dpad
        // Make it add every unit test


        allUnitTests = new ArrayList<>();
        // Framework
//        allUnitTests.add(new CommonTest());
//        allUnitTests.add(new CoordinatePlaneTest());
//        allUnitTests.add(new FaultTest());
//        allUnitTests.add(new GamepadTest());
//        allUnitTests.add(new LoggerTest());
//        allUnitTests.add(new RobotFunctionsTest());
//        allUnitTests.add(new ThreadTest());

//        allUnitTests.add(new StorageTest());
//        allUnitTests.add(new ModulesTest());
//        allUnitTests.add(new AutoModuleTest());

        // Hardware
//        allUnitTests.add(new TankDriveTest());
//        allUnitTests.add(new IntakeTest());
//        allUnitTests.add(new TurretTest());
//        allUnitTests.add(new LiftTest());
//        allUnitTests.add(new OuttakeTest());
//        allUnitTests.add(new CarouselTest());

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
        fieldSide = FieldSide.RED;
        reference(this);
        createUnitTests();
        for (UnitTest t: allUnitTests) {t.init();}
        linkXToNextTest();
        log.watch("Testing Mode: " + testingMode.toString());
        activate(FieldSide.UNKNOWN);
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

    private void linkXToNextTest(){
        if(testingMode.equals(TestType.CONTROL)) {
            gph1.link(Button.X, OnPressEventHandler.class, (double... args) -> nextTest());
        }
    }

    private void nextTest(){
        allUnitTests.get(currentTestNum).stop();
        currentTestNum++;
        if(currentTestNum >= allUnitTests.size()){
            requestOpModeStop();
        }
        log.clearTelemetry();
        gph1.unlinkAll();
        gph2.unlinkAll();
        linkXToNextTest();
        timer.reset();
    }

    private enum TestType{
        CONTROL,
        TIME
    }
}
