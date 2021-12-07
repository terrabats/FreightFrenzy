package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class TurrentTest extends UnitTest {
    @Override
    public void loop() {
        bot.turret.spin(gamepad2.left_stick_x);
        log.watch("pos", bot.turret.getTurretPos());
    }
}
