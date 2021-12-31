package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class TurretTest extends UnitTest {
    /**
     * Tests the turret
     */
    @Override
    protected void start() {
        showConfig(bot.turret);
    }

    @Override
    public void loop() {
        log.show("Use left stick x");
        bot.turret.move(gamepad2.left_stick_x);
        log.show("target pos", bot.turret.getTargetPos());
        log.show("pos", bot.turret.getTurretPos());
    }
}
