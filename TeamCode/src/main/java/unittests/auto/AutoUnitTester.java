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
import unittests.auto.framework.MoveTest;
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

    // TODO TEST
    // Test this unit tester

    @Override
    public void createUnitTests(){
        // Framework
        add(new MoveTest());
    }

    @Override
    public void initAuto() {
        AutoUnitTest.linearOpMode = this;
        readyTestsAndSelector(testingMode);
        activate(FieldSide.UNKNOWN);
    }

    @Override
    public void runAuto() {
        selector.resetUpdateTimer();
        while (opModeIsActive()){
            if(!isDoneWithAllTests(testingMode)) {
                selector.update();
                runCurrentTest(testingMode);
            }else{
                log.show("Done With All Tests");
            }
        }
    }

    @Override
    public void stopAuto() {

    }
}
