package unittests;

import static global.General.*;

public class DriveTest extends UnitTest {
    @Override
    public void loop() {
        bot.mechDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }
}
