package unittests.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import unittests.UnitTest;
import static global.General.*;

public class LiftTest extends UnitTest {
    @Override
    public void loop() {
        bot.lift.move(-gamepad2.right_stick_y);
    }
}
