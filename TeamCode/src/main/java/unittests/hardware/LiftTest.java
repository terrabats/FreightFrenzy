package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class LiftTest extends UnitTest {
    @Override
    protected void start() {
        showConfig(bot.lift);
    }

    @Override
    public void loop() {
        bot.lift.move(-gamepad2.right_stick_y);
        log.show("pos", bot.lift.getLiftPos());
    }
}
