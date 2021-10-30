package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import static global.General.*;

@TeleOp(name = "CommonTest")
public class CommonTest extends OpMode implements Common {
    @Override
    public void init() {
        reference(this);
    }

    @Override
    public void loop() {
        telemetry.addData("Status: ", "Common is Working");
        telemetry.update();
    }
}
