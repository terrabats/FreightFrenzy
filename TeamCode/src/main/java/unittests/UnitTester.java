package unittests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import teleop.Tele;
import teleutil.Selector;
import unittests.framework.*;

import teleutil.button.Button;
import unittests.hardware.CarouselTest;
import unittests.hardware.IntakeTest;
import unittests.hardware.LiftTest;
import unittests.hardware.OuttakeTest;
import unittests.hardware.TankDriveTest;
import unittests.hardware.TurretTest;
import unittests.sensor.OdometryTest;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.condition.Status;
import util.store.Item;

import static global.General.*;

@SuppressWarnings("ALL")
@TeleOp(name = "UnitTester", group = "UnitTests")
public class UnitTester extends Tele {

    /**
     * Status of this unit tester
     * Used for stopping once all the tests are done
     */
    private Status status = Status.ACTIVE;
    /**
     * Selector object to select the unit test for selection test type
     */
    private final Selector<UnitTest> selector = new Selector<>(true);
    /**
     * Type of testing mode
     * @link TestType
     */
    private final TestType testingMode = TestType.CONTROL;

    /**
     * Creates all of the unit tests, comment in the ones you want
     * Use add(new <your unit test>) to add your own unit test
     */
    private void createUnitTests(){

        // TODO TEST
        // New unit tests to be created
        // 1. Selector Test
        // 2. Electronic Test
        // 3. RobotPart Test
        // 4. Mechnum Drive Test
        // 5. RobotFramework Test
        // 6. Synchroniser Test

        // Hello from shuhul from snehils computer
        // Hello from shuhul from shuhuls computer
        //  ee
        // Framework
        add(new AccessTest());
//        add(new CommonTest());
//        add(new AccessTest());
//        add(new CoordinatePlaneTest());
//        add(new LoggerTest());
//        add(new FaultTest());
//        add(new GamepadTest());
//        add(new RobotFunctionsTest());
//        add(new ThreadTest());
//        add(new StorageTest());
//        add(new StageTest());
//        add(new AutoModuleTest());
//          add(new LagTest());

        // Hardware
//        add(new TankDriveTest());
//        add(new IntakeTest());
//        add(new TurretTest());
//        add(new LiftTest());
        add(new OuttakeTest());
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
                selector.init(gph1, Button.DPAD_DOWN, Button.DPAD_UP);
                break;
        }
        /**
         * Run on the next test
         * stop the current test, halt the robot, idle the selector, unlink gamepadhandlers, and clear telemetry
         */
        selector.runOnNext(() -> {
            getCurrentTest().stop();
            getCurrentTest().reset();
            bot.halt();
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
        fault.check("No unit tests to be run", Expectation.OBVIOUS, Magnitude.NEGLIGIBLE, selector.getNumberOfItems() == 0, false);
        selector.runToAll(UnitTest::init);
        /**
         * Display the testing mode
         */
        log.show("Testing Mode: " + testingMode.toString());
        super.activate(FieldSide.UNKNOWN);
    }

    /**
     * Start method, reset the selctors update timer
     */
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
            /**
             * If all the tests are done then show that they are done
             */
            log.show("Done With All Tests");
        }
        super.loop();
    }

    /**
     * Add a unit test to the selector
     * @param test
     */
    private void add(UnitTest test){
        selector.addItem(new Item<>(test.getClass().getSimpleName(), test));
    }

    /**
     * Get the current test name
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
     * and when you are choosing it is idle
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
                /**
                 * Run the test if x is pressed in selector mode
                 */
                if(gamepad1.x){
                    selector.active();
                }
                /**
                 * List all the avaialble options
                 */
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
         * The next test will automatically run after a certian interval (set in init)
         */
        TIME,

        /**
         * A screen will pop up to show you all of the availible tests and you can select the ones you want from there
         */
        SELECTION
    }
}
