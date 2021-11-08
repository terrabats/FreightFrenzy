package unittests;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.reflections.Reflections;

import java.util.ArrayList;

import global.Common;

import static global.General.*;


// TODO
// Fix this so that testing all the unit tests is easy and you can easily find which classes extend UnitTest

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
//        gph1.link(Button.X, ButtonEventType.ON_PRESS, args -> nextTest());
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
//        Reflections reflections = new Reflections("unittests");
//        Set<Class<? extends UnitTest>> unitTests = reflections.getSubTypesOf(UnitTest.class);
//        for (Class<? extends UnitTest> unitTestClasses : unitTests) {
//            ExceptionCatcher.catchNewInstance(() -> allUnitTests.add(unitTestClasses.newInstance()));
//        }
    }
}
