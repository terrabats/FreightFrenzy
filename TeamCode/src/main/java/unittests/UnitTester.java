package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

import elements.FieldSide;
import teleop.Tele;
import teleutil.Selector;
import teleutil.button.OnPressEventHandler;
import unittests.framework.*;
import unittests.hardware.*;
import unittests.sensor.*;
import unittests.sensor.GyroTest;
import util.Timer;

import global.Common;
import teleutil.button.Button;
import util.codeseg.BooleanCodeSeg;
import util.codeseg.TypeParameterCodeSeg;
import util.condition.Status;
import util.store.Item;

import static global.General.*;

//@Disabled
@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends Tele {

    private final TestType testingMode = TestType.SELECTION;
    private final Selector<UnitTest> selector = new Selector<>(true);

    private void createUnitTests(){
        // Framework
//        add(new CommonTest());
//        add(new AccessTest());
//        add(new CoordinatePlaneTest());
//        add(new FaultTest());
//        add(new GamepadTest());
//        add(new LoggerTest());
//        add(new RobotFunctionsTest());
//        add(new ThreadTest());
//        add(new StorageTest());
        add(new ModulesTest());
//        add(new AutoModuleTest());

        // Hardware
        add(new TankDriveTest());
//        add(new IntakeTest());
//        add(new TurretTest());
//        add(new LiftTest());
//        add(new OuttakeTest());
//        add(new CarouselTest());

        // Sensor
//        add(new ColorTest());
//        add(new DistanceTest());
//        add(new GyroTest());
//        add(new OdometryTest());
//        add(new TouchTest());
//        add(new OdometryTest());
    }

    @Override
    public void init() {
        super.init();

        switch (testingMode){
            case TIME:
                selector.init(5);
                break;
            case CONTROL:
                selector.init(() -> gamepad1.x, () -> false);
                break;
            case SELECTION:
                selector.init(gph1, Button.DPAD_DOWN, Button.DPAD_UP);
                break;
        }

        selector.runOnNext(() -> {
            getCurrentTest().stop();
            selector.idle();
            gph1.unlinkAll();
            gph2.unlinkAll();
            log.clearTelemetry();
        });

        if(!testingMode.equals(TestType.SELECTION)){
            selector.runOnEnd(this::requestOpModeStop);
        }

        createUnitTests();
        selector.runToAll(UnitTest::init);

        log.watch("Testing Mode: " + testingMode.toString());
        super.activate(FieldSide.UNKNOWN);
    }

    @Override
    public void loop() {
        selector.update();
        showSelection();
        runCurrentTest();
        super.update(true);
    }


    public void add(UnitTest test){
        selector.addItem(new Item<>(test.getClass().getSimpleName(), test));
    }

    private String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }

    private UnitTest getCurrentTest(){
        return selector.getCurrentItem().getValue();
    }

    private void runCurrentTest(){
        if(!testingMode.equals(TestType.SELECTION)) {
            selector.runToCurrentItem(UnitTest::test);
        }else{
            if(selector.isActive()){
                selector.runToCurrentItem(UnitTest::test);
            }
        }
    }

    public void showSelection(){
        if(testingMode.equals(TestType.SELECTION)){
            if(selector.isInActive()){
                if(gamepad1.x){
                    selector.active();
                }
                log.list(selector.getItemClassNames(), selector.getCurrentIndex());
            }else{
                log.display("Testing " + getCurrentTestName());
            }
        }else {
            log.display("Testing " + getCurrentTestName());
        }
    }

    private enum TestType{
        CONTROL,
        TIME,
        SELECTION
    }
}
