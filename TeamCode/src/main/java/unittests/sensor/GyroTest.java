package unittests.sensor;

import unittests.UnitTest;
import static global.General.*;

public class GyroTest extends UnitTest {

    @Override
    protected void loop() {
        log.watch("gyro right", bot.gyroSensor.getRightHeading());
//        log.watch("gyro left", bot.gyro.getLeftHeading());
    }
}
