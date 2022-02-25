package unittests.tele.hardware.Mecanum;

import static global.General.bot;
import static global.General.gamepad1;
import static global.General.gamepad2;

import unittests.tele.TeleUnitTest;

public class MecanumDriveTest extends TeleUnitTest {
    @Override
    protected void loop() {
        bot.mecanumDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }
}
