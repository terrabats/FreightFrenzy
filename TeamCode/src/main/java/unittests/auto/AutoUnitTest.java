package unittests.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import unittests.UnitTest;
import unittests.tele.TeleUnitTest;
import util.Timer;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.condition.Iterator;
import util.condition.Status;

public class AutoUnitTest extends UnitTest implements Iterator {
    /**
     * Unit test based on auto
     * For init and stop see UnitTest
     * @link UnitTest
     */

    /**
     * Static linear opmode
     */
    public static LinearOpMode linearOpMode;

    // TODO FIX
    // Randomly at the end of auto test it says there are no items in arraylist?


    /**
     * Run runs once after start
     * NOTE: This is equivalent to loop in TeleUnitTest except it runs once
     */
    protected void run(){}

    /**
     * Test runs the test
     */
    @Override
    public final void test() {
        if(status.equals(Status.IDLE)){
            run();
            status = Status.ACTIVE;
        }
    }

    @Override
    public boolean condition() {
        return linearOpMode.opModeIsActive();
    }
}
