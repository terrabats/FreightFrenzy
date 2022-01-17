package unittests.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import teleop.Tele;

import unittests.UnitTester;
import unittests.tele.framework.*;
import unittests.tele.framework.CommonTest;
import unittests.tele.framework.FaultTest;
import unittests.tele.framework.GamepadTest;
import unittests.tele.framework.LoggerTest;
import unittests.tele.framework.StorageTest;
import unittests.tele.hardware.OdometryServoTest;
import unittests.tele.hardware.*;
import unittests.unused.CoordinatePlaneTest;

import static global.General.*;

@SuppressWarnings("ALL")
@TeleOp(name = "TeleUnitTester", group = "UnitTests")
public class TeleUnitTester extends Tele implements UnitTester{

    /**
     * Type of testing mode
     * @link TestType
     */
    private TestingMode testingMode = TestingMode.CONTROL;

    @Override
    public void createUnitTests(){
        // Framework
//        add(new OdometryServoTest());
//        add(new AccessTest());
//        add(new CommonTest());
//        add(new CoordinatePlaneTest());
//        add(new LoggerTest());
//        add(new FaultTest());
//        add(new GamepadTest());
//        add(new RobotFunctionsTest());
//        add(new ThreadTest());
//        add(new StorageTest());
//        add(new StageTest());
//        add(new AutoModuleTest());
//        add(new LagTest());
//        add(new SelectorTest());
//        add(new SynchroniserTest());
//        add(new RobotPartTest());
//        add(new RobotFrameworkTest());
//        add(new ElectronicsTest());

        // Hardware
//        add(new TankDriveTest());
//        add(new IntakeTest());
//        add(new LiftTest());
//        add(new TurretTest());
//        add(new OuttakeTest());
//        add(new CarouselTest());

//        // Sensor
//        add(new ColorTest());
//        add(new DistanceTest());
//        add(new GyroTest());
//        add(new OdometryTest());
//        add(new TouchTest());
//        add(new OdometryTest());
    }

    /**
     * Init method
     */
    @Override
    public void initTele() {
        readyTestsAndSelector(testingMode);
        activate(FieldSide.UNKNOWN);
    }

    /**
     * Start method, reset the selectors update timer
     */
    @Override
    public void startTele() {
        selector.resetUpdateTimer();
    }

    /**
     * Loop method, update the selector, and run the current test
     */
    @Override
    public void loopTele() {
        if(!isDoneWithAllTests(testingMode)) {
            selector.update();
            runCurrentTest(testingMode);
        }else{
            log.show("Done With All Tests");
        }
    }

    @Override
    public void stopTele() {
        selector.reset();
    }
}
