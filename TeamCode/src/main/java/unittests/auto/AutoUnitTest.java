package unittests.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import unittests.UnitTest;
import unittests.tele.TeleUnitTest;

public class AutoUnitTest extends UnitTest {
    public static LinearOpMode linearOpMode;
    /**
     * Unit test based on auto
     * For init and stop see UnitTest
     * @link UnitTest
     */

    /**
     * Run runs once after start
     * NOTE: This is equivalent to loop in TeleUnitTest except it runs once
     */
    public void run() {}
    // TODO TEST
    // Test if this really works?
    protected final boolean opModeIsActive(){return linearOpMode.opModeIsActive();}
}
