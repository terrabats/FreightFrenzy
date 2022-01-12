package unittests.tele.sensor;

import unittests.tele.TeleUnitTest;
import static global.General.*;

/**
 * NOTE: Uncommented
 */

public class TouchTest extends TeleUnitTest {
    @Override
    protected void loop() {
        log.show("pressed", bot.touchSensor.isOuttakePressingTouchSensor());
    }
}
