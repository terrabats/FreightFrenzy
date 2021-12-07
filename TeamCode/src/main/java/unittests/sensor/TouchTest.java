package unittests.sensor;

import unittests.UnitTest;
import static global.General.*;

public class TouchTest extends UnitTest {
    @Override
    protected void loop() {
        log.watch("pressed", bot.touch.isOuttakePressingTouchSensor());
        log.watch("value", bot.touch.getValue());
    }
}
