package unittests.tele.hardware;

import unittests.tele.TeleUnitTest;

import static global.General.*;

public class TankDriveTest extends TeleUnitTest {
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
