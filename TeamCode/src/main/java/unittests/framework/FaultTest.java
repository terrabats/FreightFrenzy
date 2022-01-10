package unittests.framework;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import unittests.UnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;



public class FaultTest extends UnitTest {
    /**
     * Tests if fault is working by using warn (switch to fault.check if you want to test that functionality)
     */
    @Override
    public void loop() {
        fault.warn("Is fault working?");
        fault.warn("Is fault really working?", Expectation.INCONCEIVABLE, Magnitude.CATASTROPHIC);
        fault.warn("Is fault really really really working?", false, true);
        fault.warn("Is fault really really really really working?", Expectation.INCONCEIVABLE, Magnitude.CATASTROPHIC, false, true);
    }
}
