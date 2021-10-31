package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

@TeleOp(name = "FaultTest", group = "UnitTests")
public class FaultTest extends OpMode implements Common {

    @Override
    public void init() {reference(this);}

    @Override
    public void loop() {
        fault.check("Is fault working?", Expectation.INCONCEIVABLE, Magnitude.CATASTROPHIC, false);
    }
    @Override
    public void stop(){end();}
}
