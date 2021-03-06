package unittests.framework;

import robot.TerraBot;
import unittests.UnitTest;
import util.User;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;


public class CommonTest extends UnitTest{
    /**
     * Tests if common is working (which it probably is if the code can run)
     * NOTE: This also check the main user
     */
    @Override
    public void start() {
        fault.check("robotPartsIsEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() == 0, false);
    }

    @Override
    public void loop() {
        log.show("Common is Working");
        /**
         * Check the main user (should be TELE)
         */
        fault.warn("Main user is not TELE", mainUser.toString().equals(User.TELE.toString()), true);
        log.show("Current User", mainUser.toString());
    }
}
