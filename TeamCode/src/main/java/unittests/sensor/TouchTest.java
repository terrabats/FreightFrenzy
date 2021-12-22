package unittests.sensor;

import unittests.UnitTest;
import static global.General.*;

public class TouchTest extends UnitTest {
    @Override
    protected void loop() {
        log.show("pressed", bot.touchSensor.isOuttakePressingTouchSensor());
    }
}
