package unittests.hardware;

import robot.TerraBot;
import unittests.UnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class TankDriveTest extends UnitTest {
    @Override
    public void loop() {
        bot.tankDrive.move(-gamepad1.right_stick_y, gamepad1.left_stick_x);
    }
}
