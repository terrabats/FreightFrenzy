package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.RobotFramework;
import robot.TerraBot;
import static global.General.*;

public class DriveTest extends UnitTest {
    @Override
    public void init() {
        super.init();
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
