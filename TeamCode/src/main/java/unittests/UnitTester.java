package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import util.Timer;

import global.Common;
import teleutil.button.Button;
import teleutil.button.ButtonEventType;

import static global.General.*;

@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends OpMode implements Common {
    private ArrayList<UnitTest> allUnitTests = new ArrayList<>();
    private int currentTestNum = 0;
    private Timer timer = new Timer();

    private final int testDelay = 5;
    private final TestType testingMode = TestType.TIME;

    private void createUnitTests(){
        allUnitTests = new ArrayList<>();
        allUnitTests.add(new CommonTest());
        allUnitTests.add(new CoordinatePlaneTest());
        allUnitTests.add(new DriveTest());
        allUnitTests.add(new FaultTest());
        allUnitTests.add(new GamepadTest());
        allUnitTests.add(new LoggerTest());
        allUnitTests.add(new RobotFunctionsTest());
        allUnitTests.add(new ThreadTest());
    }

    @Override
    public void init() {
        reference(this);
        createUnitTests();
        for (UnitTest t: allUnitTests) {t.init();}
        if(testingMode.equals(TestType.CONTROL)) {
            gph1.link(Button.X, ButtonEventType.ON_PRESS, (double... args) -> nextTest());
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
        log.display("Testing " + getCurrentTestName());
        if(testingMode.equals(TestType.TIME)){
            if(timer.seconds() > testDelay){
                nextTest();
            }
        }
        getCurrentTest().test();
        update(true);
    }

    @Override
    public void stop(){
        for (UnitTest t: allUnitTests) {t.stop();}
        end();
    }


    private String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }
    private UnitTest getCurrentTest(){
        return allUnitTests.get(currentTestNum);
    }
    private void nextTest(){
        currentTestNum++;
        if(currentTestNum >= allUnitTests.size()){
            requestOpModeStop();
        }
        timer.reset();
    }

    private enum TestType{
        CONTROL,
        TIME
    }
}
