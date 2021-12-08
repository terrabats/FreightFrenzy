package unittests.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import unittests.UnitTest;
import static global.General.*;

public class LiftTest extends HardwareTest {
    @Override
    protected void run() {
        showConfig(bot.lift);
    }

    @Override
    public void loop() {
        bot.lift.move(-gamepad2.right_stick_y);
        log.watch("pos", bot.lift.getLiftPos());
    }
}
