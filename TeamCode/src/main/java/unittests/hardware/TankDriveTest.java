package unittests.hardware;

import robot.TerraBot;
import unittests.UnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class TankDriveTest extends UnitTest {
    /**
     * Tests the tank drive
     */

    @Override
    public void loop() {
//        showConfig(bot.tankDrive);
        log.show("Use right stick y (forward) and left stick x (turn)");
        bot.tankDrive.move(-gamepad1.right_stick_y, gamepad1.left_stick_x);
//        bot.tankDrive.moveSmooth(-gamepad1.right_stick_y, gamepad1.left_stick_x);
    }
}
