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
     * Uses the intake robotpart as the test part
     * NOTE: The robot must have an intake robotpart for this to work
     */
    @Override
    protected void loop() {
        /**
         * Should be 1
         */
        log.show("Test part electronics size", bot.intake.getElectronicsOfType(CMotor.class).size());
        /**
         * Should be TELE
         */
        log.show("Test part current user", bot.intake.getUser());
        /**
         * Should show config of test part properly (i.e. directions correct, run without encoder, etc.)
         */
        showConfig(bot.intake);
    }

    /**
     * When the program stops the logs should have the configuration of the intake
     */
}
