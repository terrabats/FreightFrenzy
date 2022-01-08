package unittests.framework;

import robot.RobotFramework;
import robot.TerraBot;
import robotparts.electronics.CMotor;
import unittests.UnitTest;
import util.Timer;
import util.User;

import static global.General.log;

public class RobotFrameworkTest extends UnitTest {
    /**
     * Class that tests robotframework by creating a test object
     */

    /**
     * Test robot framework
     * NOTE: Since allrobotparts is a static arraylist all of the parts in the normal terrabot will be in this too
     */
    TerraBot testRobotFramework = new TerraBot();
    /**
     * Timer object
     */
    final Timer timer = new Timer();

    @Override
    protected void start() {
        /**
         * Reset the timer and init the robot framework
         */
        timer.reset();
        testRobotFramework.init();
        /**
         * Move the intake at 0.2 power
         */
        testRobotFramework.intake.move(0.2);
        /**
         * Wait 0.5 seconds
         */
        while (timer.seconds() < 0.5){}
        /**
         * Halt the robot framework and set user to TELE
         */
        testRobotFramework.halt();
        testRobotFramework.setUser(User.TELE);
    }

    @Override
    protected void loop() {
        /**
         * Should be about 9 (or count how many robotparts are defined in terrabot)
         */
        log.show("Robot Parts Size", RobotFramework.allRobotParts.size());
        /**
         * Should be TELE
         */
        log.show("Test part current user", testRobotFramework.intake.getUser());
    }
}
