package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.TerraBot;
import static robot.General.*;

@TeleOp(name = "RobotFunctionsTest")
public class RobotFunctionsTest extends OpMode {
    @Override
    public void init() {
        bot = new TerraBot();
        bot.init(hardwareMap, telemetry);
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
