package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

import global.Common;
import teleutil.button.Button;
import teleutil.button.ButtonEventType;

import static global.General.*;

@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends OpMode implements Common {
    public static ArrayList<UnitTest> allUnitTests = new ArrayList<>();
    public int testNum = 0;

    @Override
    public void init() {
        reference(this);
        createAllUnitTests();
        for (UnitTest t: allUnitTests) {t.init();}
        testAll();
    }

    @Override
    public void start() {
        for (UnitTest t : allUnitTests) {t.start();}
        ready();
    }

    @Override
    public void loop() {
        if(getCurrentTest().isActive()){
            log.display("Testing " + getCurrentTestName());
            getCurrentTest().loop();
        }else{
            nextTest();
        }
        if(gamepad1.x){
            nextTest();
        }
        update(true);
    }

    @Override
    public void stop(){
        for (UnitTest t: allUnitTests) {
            t.stop();
        }
        end();
    }


    private String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }
    private UnitTest getCurrentTest(){
        return allUnitTests.get(testNum);
    }
    private void nextTest(){
        testNum++;
        if(testNum >= allUnitTests.size()){
            requestOpModeStop();
        }
    }
    private void testAll(){
        for (UnitTest t: allUnitTests) {
            t.test();
        }
    }
    private void createAllUnitTests(){
        allUnitTests.add(new CommonTest());
        allUnitTests.add(new CoordinatePlaneTest());
        allUnitTests.add(new DriveTest());
        allUnitTests.add(new FaultTest());
        allUnitTests.add(new GamepadTest());
        allUnitTests.add(new LoggerTest());
        allUnitTests.add(new RobotFunctionsTest());
        allUnitTests.add(new ThreadTest());
    }
}
