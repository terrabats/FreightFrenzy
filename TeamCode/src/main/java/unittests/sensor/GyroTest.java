package unittests.sensor;

import unittests.UnitTest;
import static global.General.*;

public class GyroTest extends SensorTest {

    @Override
    protected void loop() {
        log.watch("gyro right", bot.gyro.getRightHeading());
//        log.watch("gyro left", bot.gyro.getLeftHeading());
    }
}
