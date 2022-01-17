package unittests.tele.hardware;

import static global.General.bot;
import static global.General.*;

import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;

public class OdometryServoTest extends UnitTest {
    /**
     * Unit test based on tele
     * For init and stop see UnitTest
     * @link UnitTest
     */

    // TODO COMMENT

    /**
     * Start method runs once
     */
    protected void start() {
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.tankDrive.lower());
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.tankDrive.raise());
    }

    protected void loop() {
        log.show("Press B to lower odometry");
        log.show("Press Y to raise odometry");
    }
}
