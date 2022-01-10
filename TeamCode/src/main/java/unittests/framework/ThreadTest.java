package unittests.framework;

import robot.TerraBot;
import unittests.UnitTest;

import static global.General.*;


public class ThreadTest extends UnitTest {
    /**
     * Tests the two threads on the robot by setting their execution code
     * NOTE: This test will break other tests so run it alone or at the end
     */
    @Override
    public void loop() {
        TerraBot.robotFunctionsThread.setExecutionCode(() -> {
            log.showAndRecord("Robot Functions Code", "is running");
            log.record("Robot Functions Status", TerraBot.robotFunctionsThread.getStatus());
        });
        TerraBot.odometryThread.setExecutionCode(() -> {
            log.showAndRecord("Odometry Code", "is running");
            log.record("Odometry Thread Status", TerraBot.odometryThread.getStatus());
        });
    }
}
