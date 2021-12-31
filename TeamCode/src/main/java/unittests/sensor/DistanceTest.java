package unittests.sensor;


import unittests.UnitTest;
import static global.General.*;

/**
 * NOTE: Uncommented
 */

public class DistanceTest extends UnitTest {
    @Override
    protected void loop() {
        log.show("left", bot.distanceSensors.getLeftDistance());
        log.show("right", bot.distanceSensors.getRightDistance());
        log.show("front left", bot.distanceSensors.getFrontLeftDistance());
        log.show("front right", bot.distanceSensors.getFrontRightDistance());
        log.show("outtake", bot.distanceSensors.getOuttakeDistance());
    }
}
