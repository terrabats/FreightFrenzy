package unittests.framework;

import robot.RobotFramework;
import robot.TerraBot;
import unittests.UnitTest;
import util.Timer;

public class RobotFrameworkTest extends UnitTest {
    TerraBot testRobotFramework = new TerraBot();
    /**
     * Timer object
     */
    final Timer timer = new Timer();

    @Override
    protected void start() {
        testRobotFramework.init();
        testRobotFramework.intake.move(0.2);
    }
}
