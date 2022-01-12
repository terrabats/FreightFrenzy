package unittests.tele.sensor;

import unittests.tele.TeleUnitTest;
import static global.General.*;

/**
 * NOTE: Uncommented
 */

public class GyroTest extends TeleUnitTest {

    @Override
    protected void loop() {
        log.show("gyro right", bot.gyroSensors.getRightHeadingDeg());
//        log.watch("gyro left", bot.gyro.getLeftHeading());
    }
}
