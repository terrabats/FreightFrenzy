package unittests.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.PMotor;
import unittests.UnitTest;
import util.Timer;
import util.User;

import static global.General.*;

public class RobotPartTest extends UnitTest {
    /**
     * Class that tests robot part by creating a test robot part
     */

    /**
     * Test object
     * Create a motor testMotor and initialize it
     * NOTE: The robot must have an intake motor called in for this to work
     * Set the power to 0.2
     */
    RobotPart testPart = new RobotPart(){
        CMotor testMotor;
        @Override
        public void init() {
            testMotor = createCMotor("in", DcMotorSimple.Direction.FORWARD);
            testMotor.setPower(0.2);
        }
    };
    /**
     * Timer object
     */
    final Timer timer = new Timer();


    @Override
    protected void start() {
        /**
         * Reset the timer and init the robot part
         */
        timer.reset();
        testPart.init();
        /**
         * Wait 0.5 seconds
         */
        while (timer.seconds() < 0.5){}
        /**
         * Stop the robot part and switch the user to tele
         */
        testPart.halt();
        testPart.switchUser(User.TELE);
    }

    @Override
    protected void loop() {
        /**
         * Should be 1
         */
        log.show("Test part electronics size", testPart.getElectronicsOfType(CMotor.class).size());
        /**
         * Should be TELE
         */
        log.show("Test part current user", testPart.getUser());
        /**
         * Should show config of test part properly (i.e. directions correct, run without encoder, etc.)
         */
        showConfig(testPart);
    }
}
