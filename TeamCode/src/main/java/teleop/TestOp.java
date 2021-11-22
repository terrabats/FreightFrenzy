package teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import util.condition.Expectation;
import util.condition.Magnitude;
import static global.General.*;

@TeleOp(name = "TestOp", group = "TeleOp")
public class TestOp extends OpMode implements Common {
    @Override
    public void init() {
        reference(this);
    }

    @Override
    public void start() {
        ready();
    }

    @Override
    public void loop() {
       update(true);
    }

    @Override
    public void stop() {
        end();
    }
}
