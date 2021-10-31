package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import static global.General.*;


@Disabled
@TeleOp(name = "RobotFunctionsTest")
public class RobotFunctionsTest extends OpMode implements Common {
    @Override
    public void init() {
        reference(this);
        bot.addRFsTeleOp();
        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void start() {
        bot.start();
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
