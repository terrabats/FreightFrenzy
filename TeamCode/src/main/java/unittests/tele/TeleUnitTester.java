package unittests.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import teleop.Tele;

import teleutil.button.Button;
import unittests.UnitTester;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.condition.Status;

import static global.General.*;
import static global.General.gamepad1;

@SuppressWarnings("ALL")
@TeleOp(name = "TeleUnitTester", group = "UnitTests")
public class TeleUnitTester extends Tele implements UnitTester{
    /**
     * Status of this unit tester
     * Used for stopping once all the tests are done
     */
    private Status status = Status.ACTIVE;

    /**
     * Type of testing mode
     * @link TestType
     */
    private TestingMode testingMode = TestingMode.CONTROL;

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

    /**
     * Init method
     */
    @Override
    public void init() {
        super.init();

        /**
         * initialize the selector depending on the testing mode
         */
        switch (testingMode){
            case TIME:
                /**
                 * Change the time between unit tests here
                 */
                selector.init(5);
                break;
            case CONTROL:
                /**
                 * Change the button to move to the next test here
                 */
                selector.init(() -> gamepad1.x, () -> false);
                break;
            case SELECTION:
                /**
                 * In selection mode, use the dpad to move up and down through the list of unit tests
                 */
                selector.init(gph1, Button.DPAD_DOWN, Button.DPAD_UP, Button.X);
                break;
        }

        /**
         * When the tests are done, set the status to disabled (except for selection mode)
         */
        if(!testingMode.equals(TestingMode.SELECTION)) {
            selector.runOnEnd(() -> status = Status.DISABLED);
        }

        readyTestsAndSelector(testingMode);

        super.activate(FieldSide.UNKNOWN);
    }

    /**
     * Start method, reset the selectors update timer
     */
    @Override
    public void start() {
        selector.resetUpdateTimer();
        super.start();
    }

    /**
     * Loop method, update the selector, and run the current test
     */
    @Override
    public void loop() {
        if(!status.equals(Status.DISABLED)) {
            selector.update();
            runCurrentTest(testingMode);
        }else{
            log.show("Done With All Tests");
        }
        super.loop();
    }
}
