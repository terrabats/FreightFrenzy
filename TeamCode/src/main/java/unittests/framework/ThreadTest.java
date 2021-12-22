package unittests.framework;

import robot.TerraBot;
import unittests.UnitTest;

import static global.General.*;


public class ThreadTest extends UnitTest {
    @Override
    public void loop() {
        TerraBot.robotFunctionsThread.setCode(() -> {
            log.showAndRecord("Robot Functions Code", "is running");
            log.record("Robot Functions Status", TerraBot.robotFunctionsThread.getStatus());
        });
        TerraBot.odometryThread.setCode(() -> {
            log.showAndRecord("Odometry Code", "is running");
            log.record("Odometry Thread Status", TerraBot.odometryThread.getStatus());
        });
    }
}
