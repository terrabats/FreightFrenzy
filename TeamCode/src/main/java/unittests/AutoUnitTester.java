package unittests;

import static global.General.bot;
import static global.General.fault;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;

import auton.Auto;
import elements.FieldSide;
import teleutil.Selector;
import teleutil.button.Button;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.condition.Status;
import util.store.Item;

public class AutoUnitTester extends Auto {

    private Status status = Status.ACTIVE;
    /**
     * Selector object to select the unit test for selection test type
     */
    private final Selector<AutoUnitTest> selector = new Selector<>(true);
    /**
     * Type of testing mode
     * @link TestType
     */
    private final AutoUnitTester.TestType testingMode = AutoUnitTester.TestType.CONTROL;

    /**
     * Creates all of the unit tests, comment in the ones you want
     * Use add(new <your unit test>) to add your own unit test
     */

    @Override
    public void runOpMode() throws InterruptedException {

        reference(this);
        //init
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
        if(!testingMode.equals(AutoUnitTester.TestType.SELECTION)) {
            selector.runOnEnd(() -> status = Status.DISABLED);
        }
        /**
         * Create all the unit tests and initialize them
         */
        createUnitTests();
        fault.check("No unit tests to be run", Expectation.OBVIOUS, Magnitude.NEGLIGIBLE, selector.getNumberOfItems() == 0, false);
        selector.runToAll(AutoUnitTest::init);
        /**
         * Display the testing mode
         */
        log.show("Testing Mode: " + testingMode.toString());
        super.activate(FieldSide.UNKNOWN);
        activate(FieldSide.BLUE);

        waitForStart();
        ready();
        //start
        selector.resetUpdateTimer();
        super.start();
        //run
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
        //stop
        end();
    }
    private void createUnitTests(){

    }

    /**
    /**
     * Add a unit test to the selector
     * @param test
     */
    private void add(AutoUnitTest test){
        for(String testName: selector.getItemClassNames()){
            if(test.getClass().getSimpleName().equals(testName)){
                fault.check("Duplicate unit test found", Expectation.OBVIOUS, Magnitude.NEGLIGIBLE);
            };
        }
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
    private AutoUnitTest getCurrentTest(){
        return (AutoUnitTest) selector.getCurrentItem().getValue();
    }

    /**
     * Run the current test
     * NOTE: Selector mode will only run the test if its active
     * and when you are choosing it is idle
     */
    private void runCurrentTest(){
        if(!testingMode.equals(AutoUnitTester.TestType.SELECTION)) {
            selector.runToCurrentItem(AutoUnitTest::run);
        }else{
            if(selector.isActive()){
                selector.runToCurrentItem(AutoUnitTest::run);
            }
        }
    }

    /**
     * Show the selection, using log.list if selector mode, and just displaying it otherwise
     */
    public void showSelection(){
        if(testingMode.equals(AutoUnitTester.TestType.SELECTION)){
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
                log.showAndRecord("Testing" ,  getCurrentTestName());
            }
        }else {
            log.showAndRecord("Testing" ,  getCurrentTestName());
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
