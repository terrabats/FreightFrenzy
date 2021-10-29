package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.General;
import robot.RobotFramework;
import robot.TerraBot;
import static global.General.*;

@TeleOp(name = "InitOpTest")
public class InitOpTest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        General.telemetry.addData("Status: ", "Yes it worked!");
        General.telemetry.update();
    }
}
