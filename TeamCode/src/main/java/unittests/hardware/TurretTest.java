package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class TurretTest extends UnitTest {
    @Override
    protected void start() {
        showConfig(bot.turret);
    }

    @Override
    public void loop() {
        bot.turret.move(gamepad2.left_stick_x);
        log.show("pos", bot.turret.getTurretPos());
    }
}
