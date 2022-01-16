package unittests.tele.hardware;

import unittests.tele.TeleUnitTest;
import static global.General.*;

public class TurretTest extends TeleUnitTest {
    /**
     * Tests the turret
     */

    @Override
    public void loop() {
        showConfig(bot.turret);
        /**
         * Should move the turret
         */
        log.show("Use left stick x");
        bot.turret.move(gamepad1.left_stick_x);
        /**
         * Should change when the turret moves
         */
        log.show("Turret pos", bot.turret.getTurretPos());
        /**
         * Should not change when the turret moves
         */
        log.show("Turret target pos", bot.turret.getTargetPos());
    }
}
