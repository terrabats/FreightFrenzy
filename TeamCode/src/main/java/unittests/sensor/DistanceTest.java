package unittests.sensor;


import unittests.UnitTest;
import static global.General.*;

public class DistanceTest extends UnitTest {
    @Override
    protected void loop() {
        log.watch("left", bot.distance.getLeftDistance());
        log.watch("right", bot.distance.getRightDistance());
        log.watch("front left", bot.distance.getFrontLeftDistance());
        log.watch("front right", bot.distance.getFrontRightDistance());
        log.watch("outtake", bot.distance.getOuttakeDistance());
    }
}
