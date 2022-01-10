package unittests.framework;

import robot.RobotFramework;
import unittests.UnitTest;

import static global.General.*;


/**
 * NOTE: Uncommented
 */

public class LagTest extends UnitTest {
    @Override
    protected void loop() {
        log.show("Delay: ", sync.getDelay());
    }
}
