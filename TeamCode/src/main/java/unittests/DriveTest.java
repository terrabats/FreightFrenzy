package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.TerraBot;
import static robot.General.*;

// MOVEMENT IS WORKING!

@Disabled
@TeleOp(name = "DriveTest")
public class DriveTest extends OpMode {
    @Override
    public void init() {
        bot = new TerraBot();
        bot.init(hardwareMap, telemetry);
        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void loop() {
        bot.mechDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
