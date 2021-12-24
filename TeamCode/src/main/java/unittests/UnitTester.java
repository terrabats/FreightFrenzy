package unittests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import teleop.Tele;
import teleutil.Selector;
import unittests.framework.*;

import teleutil.button.Button;
import unittests.sensor.OdometryTest;
import util.condition.Status;
import util.store.Item;

import static global.General.*;

@SuppressWarnings("ALL")
@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends Tele {

    private Status status = Status.ACTIVE;
    /**
     * Selector object to select the unit test for selection test type
     */
    private final Selector<UnitTest> selector = new Selector<>(true);
    /**
     * Type of testing mode
     * @link TestType
     */
    private final TestType testingMode = TestType.SELECTION;

    /**
     * Creates all of the unit tests, comment in the ones you want
     */
    private void createUnitTests(){
        // Framework
//        add(new CommonTest());
//        add(new AccessTest());
//        add(new CoordinatePlaneTest());
//        add(new FaultTest());
        add(new GamepadTest());
        add(new LoggerTest());
//        add(new RobotFunctionsTest());
//        add(new ThreadTest());
//        add(new StorageTest());
//        add(new ModulesTest());
        add(new AutoModuleTest());

        // Hardware
//        add(new TankDriveTest());
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
                selector.init(5);
                break;
            case CONTROL:
                selector.init(() -> gamepad1.x, () -> false);
                break;
            case SELECTION:
                selector.init(gph1, Button.DPAD_DOWN, Button.DPAD_UP);
                break;
        }
        /**
         * Run on the next test
         * stop the current test, idle the selector, unlink gamepadhandlers, and clear telemetry
         */
        selector.runOnNext(() -> {
            getCurrentTest().stop();
            selector.idle();
            gph1.unlinkAll();
            gph2.unlinkAll();
            log.clearTelemetry();
        });

        /**
         * When the tests are done, set the status to disabled (except for selection mode)
         */
        if(!testingMode.equals(TestType.SELECTION)) {
            selector.runOnEnd(() -> status = Status.DISABLED);
        }
        /**
         * Create all the unit tests and initialize them
         */
        createUnitTests();
        selector.runToAll(UnitTest::init);
        /**
         * Display the testing mode
         */
        log.show("Testing Mode: " + testingMode.toString());
        super.activate(FieldSide.UNKNOWN);
    }

    @Override
    public void start() {
        selector.resetUpdateTimer();
        super.start();
    }

    /**
     * Loop method, update the selector, show the current selection, run the current test
     */
    @Override
    public void loop() {
        if(!status.equals(Status.DISABLED)) {
            selector.update();
            showSelection();
            runCurrentTest();
        }else{
            log.show("Done With All Tests");
        }
        super.update(true);
    }

    /**
     * Add a unit test to the selector
     * @param test
     */
    private void add(UnitTest test){
        selector.addItem(new Item<>(test.getClass().getSimpleName(), test));
    }

    /**
     * get the current test name
     * @return test name
     */
    private String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }

    /**
     * Get the current test
     * @return current test
     */
    private UnitTest getCurrentTest(){
        return selector.getCurrentItem().getValue();
    }

    /**
     * Run the current test
     * NOTE: Selector mode will only run the test if its active
     */
    private void runCurrentTest(){
        if(!testingMode.equals(TestType.SELECTION)) {
            selector.runToCurrentItem(UnitTest::test);
        }else{
            if(selector.isActive()){
                selector.runToCurrentItem(UnitTest::test);
            }
        }
    }

    /**
     * Show the selection, using log.list if selector mode, and just displaying it otherwise
     */
    public void showSelection(){
        if(testingMode.equals(TestType.SELECTION)){
            if(selector.isInActive()){
                if(gamepad1.x){
                    selector.active();
                }
                log.list(selector.getItemClassNames(), selector.getCurrentIndex());
            }else{
                log.showAndRecord("Testing " ,  getCurrentTestName());
            }
        }else {
            log.showAndRecord("Testing " ,  getCurrentTestName());
        }
    }

    /**
     * Type of testing mode
     */
    private enum TestType{
        /**
         * Move to the next test by clicking x so you can control when the next test occurs
         */
        CONTROL,
        /**
         * The next test will automaticall run after a certian interval (set in init)
         */
        TIME,
        /**
         * A screen will pop up to show you all of the availible tests and you can select the ones you want from there
         */
        SELECTION
    }
}
