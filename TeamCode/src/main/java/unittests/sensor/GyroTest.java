package unittests.sensor;

import unittests.UnitTest;
import static global.General.*;

/**
 * NOTE: Uncommented
 */

public class GyroTest extends UnitTest {

    @Override
    protected void loop() {
        log.show("gyro right", bot.gyroSensors.getRightHeadingDeg());
//        log.watch("gyro left", bot.gyro.getLeftHeading());
    }
}
