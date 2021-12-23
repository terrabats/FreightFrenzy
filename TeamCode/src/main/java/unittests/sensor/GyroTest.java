package unittests.sensor;

import unittests.UnitTest;
import static global.General.*;

public class GyroTest extends UnitTest {

    @Override
    protected void loop() {
        log.show("gyro right", bot.gyroSensor.getRightHeadingDeg());
//        log.watch("gyro left", bot.gyro.getLeftHeading());
    }
}
