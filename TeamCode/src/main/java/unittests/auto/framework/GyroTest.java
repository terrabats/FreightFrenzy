package unittests.auto.framework;

import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.log;

public class GyroTest extends AutoUnitTest {
    @Override
    protected void run() {
        whileActive(() -> {
            log.show("Heading Degrees", bot.gyroSensors.getRightHeadingDeg());
            log.show("Heading Radians", bot.gyroSensors.getRightHeadingRad());
        });
    }
}
