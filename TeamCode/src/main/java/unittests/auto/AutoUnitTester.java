package unittests.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.bot;
import static global.General.fault;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;

import auton.Auto;
import elements.FieldSide;
import teleutil.Selector;
import teleutil.button.Button;
import unittests.UnitTester;
import unittests.tele.TeleUnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.condition.Status;
import util.store.Item;

@SuppressWarnings("ALL")
@Autonomous(name = "AutoUnitTester", group = "UnitTests")
public class AutoUnitTester extends Auto implements UnitTester {
    /**
     * Status of this unit tester
     * Used for stopping once all the tests are done
     */
    Status status = Status.ACTIVE;

    /**
     * Type of testing mode
     * @link TestType
     */
    TestingMode testingMode = TestingMode.CONTROL;

    @Override
    public void createUnitTests(){
        // Framework
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
//
//        // Hardware
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

    @Override
    public void initAuto() {
        super.initAuto();
    }

    @Override
    public void runAuto() {
        super.runAuto();
    }

    @Override
    public void stopAuto() {
        super.stopAuto();
    }
}
