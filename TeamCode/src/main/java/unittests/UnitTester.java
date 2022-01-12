package unittests;

import teleutil.Selector;

import unittests.tele.TeleUnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;
import util.condition.Status;
import util.store.Item;

import static global.General.*;

public interface UnitTester {

    /**
     * Selector object to select the unit test for selection test type
     */
    Selector<TeleUnitTest> selector = new Selector<>(true);

    /**
     * Creates all of the unit tests, comment in the ones you want
     * Use add(new <your unit test>) to add your own unit test
     */
    void createUnitTests();

    default void readyTestsAndSelector(TestingMode testingMode){
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
         * Create all the unit tests and initialize them
         */
        createUnitTests();
        fault.check("No unit tests to be run", Expectation.OBVIOUS, Magnitude.NEGLIGIBLE, selector.getNumberOfItems() == 0, false);
        selector.runToAll(TeleUnitTest::init);
        /**
         * Display the testing mode
         */
        log.show("Testing Mode: " + testingMode.toString());
    }

    /**
     * Adds a new test to the selector
     * NOTE: This also checks if the same test has been entered more than once
     * @param test
     */
    default void add(TeleUnitTest test){
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
    default String getCurrentTestName(){
        return getCurrentTest().getClass().getSimpleName();
    }

    /**
     * Get the current test
     * @return current test
     */
    default TeleUnitTest getCurrentTest(){
        return selector.getCurrentItem().getValue();
    }

    /**
     * Run the current test
     * NOTE: Selector mode will only run the test if its active
     * and when you are choosing it is idle
     */
    default void runCurrentTest(TestingMode testingMode){
        if(testingMode.equals(TestingMode.SELECTION) && selector.isInActive()){
            log.list(selector.getItemClassNames(), selector.getCurrentIndex());
        }else if(selector.isActive()){
            selector.runToCurrentItem(TeleUnitTest::run);
            log.showAndRecord("Testing" ,  getCurrentTestName());
        }
    }

    /**
     * Type of testing mode
     */
    enum TestingMode {
        /**
         * Move to the next test by clicking x so you can control when the next test occurs
         */
        CONTROL,

        /**
         * The next test will automatically run after a certain interval (set in init)
         */
        TIME,

        /**
         * A screen will pop up to show you all of the available tests and you can select the ones you want from there
         */
        SELECTION
    }
}
