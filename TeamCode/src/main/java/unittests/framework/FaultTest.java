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
    @Override
    public void loop() {
        fault.warn("Is fault working?", false, true);
        fault.warn("Is fault really working?", Expectation.INCONCEIVABLE, Magnitude.CATASTROPHIC, false, true);
//        fault.check("Is fault really really working?", false, true);
//        fault.check("Is fault working?", Expectation.INCONCEIVABLE, Magnitude.CATASTROPHIC, false, true);
    }
}
