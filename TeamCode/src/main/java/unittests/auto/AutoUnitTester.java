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
import unittests.auto.framework.AutoMoveTest;
import unittests.auto.framework.EmptyTest;
import unittests.auto.framework.EmptyTest2;
import unittests.auto.framework.ExecutorTest;
import unittests.auto.framework.GyroTest;
import unittests.auto.framework.MoveTest;
import unittests.auto.framework.OdometryTest;
import unittests.auto.framework.PIDTest;
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
        /**
         * Framework
         */
        addAll(
//                new PIDTest(),
//                new EmptyTest(),
//                new MoveTest(),
//                new EmptyTest2(),
//                new ExecutorTest(),
//                new OdometryTest()
//                new ExecutorTest()
                new GyroTest()
        );
    }

    /**
     * Initialize by setting the linear op mode, and reading the selector
     */
    @Override
    public void initAuto() {
        AutoUnitTest.linearOpMode = this;
        readyTestsAndSelector(testingMode);
        activate(FieldSide.UNKNOWN);
    }

    /**
     * Run the tests
     */
    @Override
    public void runAuto() {
        /**
         * Reset the update timer and set update on show to true so that logs dont need to be updated manualy
         */
        selector.resetUpdateTimer();
        log.setShouldUpdateOnShow(true);
        while (opModeIsActive()){
            if(!isDoneWithAllTests(testingMode)) {
                selector.update();
                runCurrentTest(testingMode);
            }else{
                log.show("Done With All Tests");
            }
        }
    }

    /**
     * Reset the selector
     */
    @Override
    public void stopAuto() {
        selector.reset();
    }
}
