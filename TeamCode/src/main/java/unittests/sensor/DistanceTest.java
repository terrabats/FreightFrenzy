package unittests.sensor;


import unittests.UnitTest;
import static global.General.*;

public class DistanceTest extends UnitTest {
    @Override
    protected void loop() {
        log.watch("left", bot.distanceSensors.getLeftDistance());
        log.watch("right", bot.distanceSensors.getRightDistance());
        log.watch("front left", bot.distanceSensors.getFrontLeftDistance());
        log.watch("front right", bot.distanceSensors.getFrontRightDistance());
        log.watch("outtake", bot.distanceSensors.getOuttakeDistance());
    }
}
