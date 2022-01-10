package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class TurretTest extends UnitTest {
    /**
     * Tests the turret
     */

    @Override
    public void loop() {
//        showConfig(bot.turret);
        log.show("Use left stick x");
        bot.turret.move(gamepad1.left_stick_x);
        log.show("target pos", bot.turret.getTargetPos());
        log.show("pos", bot.turret.getTurretPos());
    }
}
