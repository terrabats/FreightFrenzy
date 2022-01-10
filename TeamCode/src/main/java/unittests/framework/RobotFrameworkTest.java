package unittests.framework;

import robot.RobotFramework;
import robot.TerraBot;
import robotparts.electronics.CMotor;
import unittests.UnitTest;
import util.Timer;
import util.User;

import static global.General.bot;
import static global.General.log;

public class RobotFrameworkTest extends UnitTest {
    /**
     * Class that tests robotframework by running methods
     */


    /**
     * Test robot framework
     * NOTE: Since allrobotparts is a static arraylist all of the parts terrabot should be here
     */
    @Override
    protected void loop() {
        /**
         * Should be about 9 (or count how many robotparts are defined in terrabot)
         */
        log.show("Robot Parts Size", RobotFramework.allRobotParts.size());
        /**
         * Should be TELE
         */
        log.show("Test part current user", bot.intake.getUser());
    }
}
