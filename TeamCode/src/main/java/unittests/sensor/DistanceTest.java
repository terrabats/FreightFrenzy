package unittests.sensor;


import unittests.UnitTest;
import static global.General.*;

public class DistanceTest extends UnitTest {
    @Override
    protected void loop() {
        log.watch("left", bot.distanceSensor.getLeftDistance());
        log.watch("right", bot.distanceSensor.getRightDistance());
        log.watch("front left", bot.distanceSensor.getFrontLeftDistance());
        log.watch("front right", bot.distanceSensor.getFrontRightDistance());
        log.watch("outtake", bot.distanceSensor.getOuttakeDistance());
    }
}
